package value_object_ex;

import jpashop.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue // strategy: AUTO
    @Column(name = "member_id")
    private Long id;
    private String name;

    // 근무 기간
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address address;
    /*@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;*/

    private Set<String> favoriteFoods = new HashSet<>();

    private List<Address> addressHistory = new ArrayList<>();
}