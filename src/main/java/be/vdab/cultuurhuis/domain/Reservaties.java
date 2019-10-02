package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "reservaties")
public class Reservaties implements Serializable {

    public interface reservatieToevoegen{}
    public interface klantToevoegen{}


    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klantid")
    @NotNull(groups = klantToevoegen.class)
    private Klant klant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voorstellingid")
    @NotNull(groups = reservatieToevoegen.class)
    private Voorstelling voorstelling;
    @Positive(groups = reservatieToevoegen.class)
    private int plaatsten;

    protected Reservaties() {}

    public Reservaties(Klant klant, Voorstelling voorstelling, int plaatsten) {
        this.klant = klant;
        this.voorstelling = voorstelling;
        this.plaatsten = plaatsten;
    }

    public long getId() {
        return id;
    }

    public Klant getKlant() {
        return klant;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public int getPlaatsten() {
        return plaatsten;
    }

    @Override
    public String toString() {
        return "Reservaties{" +
                "id=" + id +
                ", klant=" + klant +
                ", voorstelling=" + voorstelling +
                ", plaatsten=" + plaatsten +
                '}';
    }
}
