package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "klanten")
public class Klant implements Serializable {

    private final static long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    private String voornaam;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    private String familienaam;
    @Valid
    @Embedded
    private Adres adres;

    protected Klant() {
    }

    public Klant(@Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+") String voornaam, @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+") String familienaam, @Valid Adres adres) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", adres=" + adres +
                '}';
    }
}
