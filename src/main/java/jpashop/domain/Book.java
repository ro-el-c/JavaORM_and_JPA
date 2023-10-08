package jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@DiscriminatorValue("book")
public class Book extends Item {
    private String author;
    private String isbn;
}
