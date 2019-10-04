package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.constraints.ReservatieConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "reservaties")
@ReservatieConstraint
public class Reservatie implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservatie that = (Reservatie) o;
        return klant.equals(that.klant) &&
                voorstelling.equals(that.voorstelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klant, voorstelling);
    }

    @Override
    public String toString() {
        return "ReservatieConstraint{" +
                "id=" + id +
                ", klant=" + klant +
                ", voorstelling=" + voorstelling +
                ", ReservatieConstraint=" + plaatsen +
                '}';
    }


}

