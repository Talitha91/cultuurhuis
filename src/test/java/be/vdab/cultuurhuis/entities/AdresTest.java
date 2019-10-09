package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class AdresTest {

    private Adres adres;
    private Validator validator;

    @Before
    public void before(){
        adres = new Adres();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void straatMagNietLeegZijn(){
       adres.setStraat("");

        validator.validateProperty(adres, "straat");
    }

}