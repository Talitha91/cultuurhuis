package be.vdab.cultuurhuis.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "reservaties")
public class Reservatie implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klantid")
    @NotNull
    private Klant klant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voorstellingid")
    @NotNull()
    private Voorstelling voorstelling;
    @Positive()
    private int plaatsen;

    protected Reservatie() {}

    public Reservatie(Klant klant, Voorstelling voorstelling, int plaatsen) {
        this.klant = klant;
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
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

    public int getPlaatsen() {
        return plaatsen;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public void setPlaatsen(int plaatsen) {
        this.plaatsen = plaatsen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservatie that = (Reservatie) o;
        return Objects.equals(klant, that.klant) &&
                Objects.equals(voorstelling, that.voorstelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klant, voorstelling);
    }
}

