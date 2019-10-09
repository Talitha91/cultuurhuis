package be.vdab.cultuurhuis.form;

import be.vdab.cultuurhuis.constraints.ReservatieConstraint;
import be.vdab.cultuurhuis.entities.Voorstelling;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@ReservatieConstraint
public class ReservatieForm {

    @NotNull
    private Voorstelling voorstelling;
    @Positive
    private int plaatsen;

    protected ReservatieForm() {}

    public ReservatieForm(Voorstelling voorstelling, @Positive int plaatsen) {
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public void setVoorstelling(Voorstelling voorstelling) {
        this.voorstelling = voorstelling;
    }

    public int getPlaatsen() {
        return plaatsen;
    }

    public void setPlaatsen(int plaatsen) {
        this.plaatsen = plaatsen;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservatieForm that = (ReservatieForm) o;
        return voorstelling.equals(that.voorstelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voorstelling);
    }
}