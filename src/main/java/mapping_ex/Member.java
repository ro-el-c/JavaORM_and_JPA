package mapping_ex;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

//    @Column(name = "team_id")
//    private Long teamId;

    /**
     * Member 의 Team 이 연관관계의 주인
     * -> Team 의 id 값을 외래키로 가지기 때문
     * */
    @ManyToOne // Member : Team = N : 1
    @JoinColumn(name = "team_id") // 외래키 매핑
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
