package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.form.ReservatieForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReservatieConstraintValidator implements ConstraintValidator<ReservatieConstraint, ReservatieForm> {

    @Override
    public void initialize(ReservatieConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(ReservatieForm reservatie, ConstraintValidatorContext constraintValidatorContext) {
        return reservatie.getPlaatsen() <= reservatie.getVoorstelling().getVrijeplaatsen();
    }
}
