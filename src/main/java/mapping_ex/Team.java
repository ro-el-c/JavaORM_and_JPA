package mapping_ex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    //== 연관 관계 편의 메서드 2 ==// -> 한쪽만 두기
    /*public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }*/

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
}
