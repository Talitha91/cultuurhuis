package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.domain.Reservatie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReservatieConstraintValidator implements ConstraintValidator<ReservatieConstraint, Reservatie> {

    @Override
    public void initialize(ReservatieConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Reservatie reservatie, ConstraintValidatorContext constraintValidatorContext) {
        return reservatie.getPlaatsen() < reservatie.getVoorstelling().getVrijeplaatsen();
    }
}
