package co.kr.capstonemju.JobBrief.domain.news.controller.dto;

import co.kr.capstonemju.JobBrief.domain.news.model.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NewsDetailDto {
    private Long id;
    private String title;
    private String reporter;
    private String press;
    private LocalDateTime pub_date;
    private String content;
    private String summary;
    private String scrap_opinion;


    public NewsDetailDto(News news, String scrap_opinion) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.press = news.getPress();
        this.reporter = news.getReporter();
        this.pub_date = news.getPub_date();
        this.content = news.getContent();
        this.summary = news.getSummary();
        this.scrap_opinion = scrap_opinion;
    }
}