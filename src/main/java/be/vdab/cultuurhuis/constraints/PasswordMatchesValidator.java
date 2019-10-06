package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.form.KlantForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, KlantForm> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(KlantForm klantForm, ConstraintValidatorContext constraintValidatorContext) {
        return klantForm.getPaswoord().equals(klantForm.getPaswoordcheck());
    }


}
