package be.vdab.cultuurhuis.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titel;
    @NotBlank
    private String uitvoerders;
    @NotNull
    private Date datum;
    @NotNull
    @Positive
    private BigDecimal prijs;
    @PositiveOrZero
    private long vrijeplaatsen;
    @Version
    private long versie;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genreid", nullable = false)
    private Genre genre;


    protected Voorstelling() {}

    public Voorstelling(String titel, String uitvoerders, Date datum, BigDecimal prijs, long vrijeplaatsen, Genre genre) {
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = new Date(datum.getYear(),datum.getMonth(),datum.getDay());
        this.prijs = prijs;
        this.vrijeplaatsen = vrijeplaatsen;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public Genre getGenre() {
        return genre;
    }

    public void verminderenPlaatsen(int aantal){
        this.vrijeplaatsen -= aantal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voorstelling that = (Voorstelling) o;
        return titel.equals(that.titel) &&
                uitvoerders.equals(that.uitvoerders) &&
                datum.equals(that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, uitvoerders);
    }

    @Override
    public String toString() {
        return "Voorstelling{" +
                "id=" + id +
                ", titel='" + titel + '\'' +
                ", uitvoerders='" + uitvoerders + '\'' +
                ", datum=" + datum +
                ", prijs=" + prijs +
                ", vrijeplaatsten=" + vrijeplaatsen +
                ", genre=" + genre +
                '}';
    }
}