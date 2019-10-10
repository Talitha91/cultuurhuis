package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    public void straatMetCorrecteNaam(){
        assertTrue(validator.validateValue(Adres.class,"straat","s-teststraat").isEmpty());
    }

    @Test
    public void straatMagNietLeegZijn(){
       assertFalse(validator.validateValue(Adres.class,"straat","").isEmpty());
    }

    @Test
    public void straatMagNietNullZijn(){
        assertFalse(validator.validateValue(Adres.class,"straat",null).isEmpty());
    }

    @Test
    public void HuisnrMetCorrecteNaam(){
        assertTrue(validator.validateValue(Adres.class,"huisnr","3").isEmpty());
    }

    @Test
    public void HuisnrMetCorrecteNaam2(){
        assertTrue(validator.validateValue(Adres.class,"huisnr","3b").isEmpty());
    }

    @Test
    public void HuisnrMagNietLeegZijn(){
        assertFalse(validator.validateValue(Adres.class,"huisnr","").isEmpty());
    }

    @Test
    public void PostcodeMetCorrectePostcode(){
        assertTrue(validator.validateValue(Adres.class,"postcode","3000").isEmpty());
    }

    @Test
    public void PostcodeMetNietCorrectePostcode(){
        assertFalse(validator.validateValue(Adres.class,"postcode","12345").isEmpty());
    }

    @Test
    public void PostcodeMagNietUitLettersBestaan(){
        assertFalse(validator.validateValue(Adres.class,"postcode","duizend").isEmpty());
    }

    @Test
    public void PostcodMagNietLeefZijn(){
        assertFalse(validator.validateValue(Adres.class,"postcode","").isEmpty());
    }

    @Test
    public void GemeenteMetCorrecteNaam(){
        assertTrue(validator.validateValue(Adres.class,"gemeente","Leuven").isEmpty());
    }

    @Test
    public void GemeenteMagNietLeegZijn(){
        assertFalse(validator.validateValue(Adres.class,"gemeente","").isEmpty());
    }


}