package jpashop.domain.delivery;

import jpashop.domain.BaseEntity;
import jpashop.domain.order.Order;
import value_object_ex.Address;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order; // 일대일 양방향
}
