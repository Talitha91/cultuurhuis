package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class KlantTest {

    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void KlantHeeftCorrecteVoornaam(){
        assertTrue(validator.validateValue(Klant.class,"voornaam","bert").isEmpty());
    }

    @Test
    public void voornaamMagNietLeegZijn(){
        assertFalse(validator.validateValue(Klant.class,"voornaam","").isEmpty());
    }

    @Test
    public void voornaamMagNietNullZijn(){
        assertFalse(validator.validateValue(Klant.class,"voornaam","").isEmpty());
    }

    @Test
    public void KlantHeeftCorrecteFamilienaam(){
        assertTrue(validator.validateValue(Klant.class,"familienaam","peeters").isEmpty());
    }

    @Test
    public void familienaamMagNietleegZijn(){
        assertFalse(validator.validateValue(Klant.class,"familienaam","").isEmpty());
    }

    @Test
    public void familienaamMagNietNullZijn(){
        assertFalse(validator.validateValue(Klant.class,"familienaam",null).isEmpty());
    }

    @Test
    public void AdresMagNietNullZijn(){
        assertFalse(validator.validateValue(Klant.class,"adres",null).isEmpty());

    }

    @Test
    public void KlantHeeftCorrecteGebruikersnaam(){
        assertTrue(validator.validateValue(Klant.class,"gebruikersnaam","bert").isEmpty());
    }

    @Test
    public void gebruikersnaamMagNietLeegZijn(){
        assertFalse(validator.validateValue(Klant.class,"gebruikersnaam","").isEmpty());
    }

    @Test
    public void gebruikersnaamMagNietNullZijn(){
        assertFalse(validator.validateValue(Klant.class,"gebruikersnaam","").isEmpty());
    }

    @Test
    public void KlantHeeftCorrectePaswoord(){
        assertTrue(validator.validateValue(Klant.class,"paswoord","paswoord123").isEmpty());
    }

    @Test
    public void paswoordMagNietLeegZijn(){
        assertFalse(validator.validateValue(Klant.class,"paswoord","").isEmpty());
    }

    @Test
    public void paswoordMagNietNullZijn(){
        assertFalse(validator.validateValue(Klant.class,"paswoord","").isEmpty());
    }


}