package be.vdab.cultuurhuis.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String naam;

    protected Genre() {
    }

    public Genre(String naam) {
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
