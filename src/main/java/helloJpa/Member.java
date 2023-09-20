package helloJpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String userName;

    private Integer age;

    @Enumerated(EnumType.STRING) // Enum 타입 / STRING, ORDINAL
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // DATE, TIME, TIMESTAMP
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDateTime dateTime;

    @Lob // varchar 을 넘는 큰 컨텐츠
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }
}
