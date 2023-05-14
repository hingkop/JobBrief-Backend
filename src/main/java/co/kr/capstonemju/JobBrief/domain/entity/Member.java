package co.kr.capstonemju.JobBrief.domain.entity;

//import co.kr.capstonemju.JobBrief.domain.Authority;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity  {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "idx")
    private Integer idx;
    @Column(name = "id",unique = true, length = 100, nullable = false) // unique 제약조건 추가
    private String id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "phoneNumber",length = 20)
    private String phoneNumber;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "studentId", length = 10)
    private String studentId;

    @Builder
    public Member(String id, String name, String password, String phoneNumber, String email, String studentId){
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studentId = studentId;
    }

    public Member updateMember(String name, String password, String phoneNumber, String email){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        return this;
    }
}