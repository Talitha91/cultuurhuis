package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

public class VoorstellingTest {


    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void VoorstellingHeeftCorrectetitel(){
        assertTrue(validator.validateValue(Voorstelling.class,"titel","bert").isEmpty());
    }

    @Test
    public void titelMagNietLeegZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"titel","").isEmpty());
    }

    @Test
    public void titelMagNietNullZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"titel","").isEmpty());
    }

    @Test
    public void VoorstellingHeeftCorrecteUitvoerders(){
        assertTrue(validator.validateValue(Voorstelling.class,"uitvoerders","bert").isEmpty());
    }

    @Test
    public void uitvoerdersMagNietLeegZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"uitvoerders","").isEmpty());
    }

    @Test
    public void uitvoerdersMagNietNullZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"uitvoerders","").isEmpty());
    }

    @Test
    public void datumMagNietNullZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"datum", null ).isEmpty());
    }

    @Test
    public void CorrectePrijs(){
        assertTrue(validator.validateValue(Voorstelling.class,"prijs", BigDecimal.TEN).isEmpty());
    }

    @Test
    public void prijsMagNietNullZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"prijs", null ).isEmpty());
    }

    @Test
    public void prijsMagNietNegatiefZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"prijs", BigDecimal.valueOf(-1) ).isEmpty());
    }

    @Test
    public void correcteVrijePlaatsen(){
        assertTrue(validator.validateValue(Voorstelling.class,"vrijeplaatsen", 100L).isEmpty());
    }

    @Test
    public void correcteVrijePlaatsen2(){
        assertTrue(validator.validateValue(Voorstelling.class,"vrijeplaatsen", 0L).isEmpty());
    }

    @Test
    public void vrijePlaatsenMagNietNullZijn(){
        assertFalse(validator.validateValue(Voorstelling.class,"vrijeplaatsen", -10L).isEmpty());
    }


}