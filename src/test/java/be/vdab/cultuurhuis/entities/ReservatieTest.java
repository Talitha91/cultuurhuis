package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class ReservatieTest {

    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void klantMagNietNullZijn(){
        assertFalse(validator.validateValue(Reservatie.class,"klant",null).isEmpty());
    }

    @Test
    public void voorstellingMagNietNullZijn(){
        assertFalse(validator.validateValue(Reservatie.class,"voorstelling",null).isEmpty());
    }

    @Test
    public void correcteAantalGereserveerdePlaatsen(){
        assertTrue(validator.validateValue(Reservatie.class,"plaatsen",5).isEmpty());
    }

    @Test
    public void PlaatsenMagNietNullZijn(){
        assertFalse(validator.validateValue(Reservatie.class,"plaatsen",0).isEmpty());
    }
}