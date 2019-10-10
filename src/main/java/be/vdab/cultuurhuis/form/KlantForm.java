package be.vdab.cultuurhuis.form;

import be.vdab.cultuurhuis.constraints.PasswordMatches;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatches
public class KlantForm {

    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String voornaam;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String familienaam;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String straat;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String huisnr;
    @Pattern(regexp = "(\\p{N}){4}")
    @NotNull
    private String postcode;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String gemeente;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String gebruikersnaam;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String paswoord;
    @Pattern(regexp = "(\\p{L}|\\p{N}|-|\\.)+")
    @NotNull
    private String paswoordcheck;


    public KlantForm(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String paswoord, String paswoordcheck) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
        this.paswoordcheck = paswoordcheck;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(String huisnr) {
        this.huisnr = huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public String getPaswoordcheck() {
        return paswoordcheck;
    }

    public void setPaswoordcheck(String paswoordcheck) {
        this.paswoordcheck = paswoordcheck;
    }

    @Override
    public String toString() {
        return "KlantForm{" +
                "voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnr='" + huisnr + '\'' +
                ", postcode='" + postcode + '\'' +
                ", gemeente='" + gemeente + '\'' +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", paswoord='" + paswoord + '\'' +
                ", paswoordcheck='" + paswoordcheck + '\'' +
                '}';
    }
}
