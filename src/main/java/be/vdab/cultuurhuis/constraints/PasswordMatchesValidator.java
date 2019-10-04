package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.form.NieuweKlantForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, NieuweKlantForm> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(NieuweKlantForm klantForm, ConstraintValidatorContext constraintValidatorContext) {
        return klantForm.getPaswoord().equals(klantForm.getPaswoordcheck());
    }


}
