package jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "album")
@DiscriminatorValue("album")
public class Album extends Item{
    private String artist;

}
