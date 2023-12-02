package jpashop.domain.member;

import jpashop.domain.BaseEntity;
import jpashop.domain.order.Order;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id @GeneratedValue // strategy: AUTO
    @Column(name = "member_id")
    private Long id;
    private String name;

    // 근무 기간
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 양방향 연관 관계
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        order.setMember(this);
        orders.add(order);
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

    public Period getWorkPeriodPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period period) {
        this.workPeriod = period;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
