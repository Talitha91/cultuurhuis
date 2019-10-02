package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ReservatieForm {

    @NotNull
    private Voorstelling voorstelling;
    @PositiveOrZero
    private int plaatsten;

    public ReservatieForm() {
    }

    public ReservatieForm(@NotNull Voorstelling voorstelling, @PositiveOrZero int plaatsten) {
        this.voorstelling = voorstelling;
        this.plaatsten = plaatsten;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public void setVoorstelling(Voorstelling voorstelling) {
        this.voorstelling = voorstelling;
    }

    public int getPlaatsten() {
        return plaatsten;
    }

    public void setPlaatsten(int plaatsten) {
        this.plaatsten = plaatsten;
    }

    @Override
    public String toString() {
        return "ReservatieForm{" +
                "voorstelling=" + voorstelling +
                ", plaatsten=" + plaatsten +
                '}';
    }
}
