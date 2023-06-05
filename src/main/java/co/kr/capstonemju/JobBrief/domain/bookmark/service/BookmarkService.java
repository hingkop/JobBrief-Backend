package co.kr.capstonemju.JobBrief.domain.bookmark.service;


import co.kr.capstonemju.JobBrief.domain.bookmark.controller.dto.BookmarkDto;
import co.kr.capstonemju.JobBrief.domain.bookmark.model.Bookmark;
import co.kr.capstonemju.JobBrief.domain.bookmark.repository.BookmarkRepository;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final NewsRepository newsRepository;
    private static final int PAGE_SIZE = 10;


    public void clickBookmark(BookmarkDto bookmarkDto, @CurrentUser Member member) {
        News news = newsRepository.findById(bookmarkDto.getNewsId())
                .orElseThrow(() -> new NotFoundException("News Not Found"));

        Bookmark existingBookmark = bookmarkRepository.findByNewsAndMember(news, member);
        if (existingBookmark != null) {
            // 이미 북마크된 경우, 삭제 처리
            bookmarkRepository.delete(existingBookmark);
        } else {
            // 북마크 추가
            Bookmark bookmark = new Bookmark(news, member, LocalDateTime.now());
            bookmarkRepository.save(bookmark);
        }
    }

    public NewsListDto getBookmarkList(Member member, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        List<Bookmark> bookmarkList = bookmarkRepository.findByMember(member, pageRequest);
        List<News> newsList = bookmarkList.stream().map(Bookmark::getNews).toList();
        List<NewsDto> newsDtoList = newsList.stream().map(NewsDto::new).toList();
        long totalItems = bookmarkRepository.countByMember(member);
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
        return new NewsListDto(newsDtoList, page, totalPages);
    }
}