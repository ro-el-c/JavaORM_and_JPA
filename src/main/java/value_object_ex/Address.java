package value_object_ex;

import javax.persistence.Embeddable;
import java.util.Objects;

//@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    /**
     * 값 타입은 불변 객체로 만들어 부작용을 막을 수 있다.
     * 생성자를 통해서만 값을 설정하거나, 내부에서만 사용(private)할 수 있도록 한다.
     * -> 수정자(setter)를 생성하지 않는다.
     * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
