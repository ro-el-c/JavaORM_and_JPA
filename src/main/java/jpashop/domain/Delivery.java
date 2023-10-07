package jpashop.domain;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order; // 일대일 양방향
}
