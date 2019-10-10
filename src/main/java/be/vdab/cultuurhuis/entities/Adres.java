package be.vdab.cultuurhuis.entities;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Embeddable
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "(\\p{L}|\\p{N}|'|-|\\.)+")
    @NotNull
    private String straat;
    @Pattern(regexp = "(\\p{L}|\\p{N}|_|-|\\.)+")
    @NotNull
    private String huisnr;
    @Pattern(regexp = "(\\p{N}){4}")
    @NotNull
    private String postcode;
    @Pattern(regexp = "(\\p{L}|\\p{N}|_|-|\\.)+")
    @NotNull
    private String gemeente;

    protected Adres() {}

    public Adres(String straat, String huisnr, String postcode, String gemeente) {
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnr(String huisnr) {
        this.huisnr = huisnr;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }
}
