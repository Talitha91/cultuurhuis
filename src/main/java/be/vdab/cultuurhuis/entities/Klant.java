package be.vdab.cultuurhuis.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    private String gebruikersnaam;
    @NotBlank
    private String paswoord;

    protected Klant() {
    }

    public Klant( String voornaam, String familienaam, Adres adres,  String gebruikersnaam, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
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

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
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
