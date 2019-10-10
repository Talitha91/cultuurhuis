package be.vdab.cultuurhuis.form;

import be.vdab.cultuurhuis.entities.Adres;
import be.vdab.cultuurhuis.entities.Klant;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class KlantFormTest {


    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void KlantFormHeeftCorrecteVoornaam(){
        assertTrue(validator.validateValue(KlantForm.class,"voornaam","bert").isEmpty());
    }

    @Test
    public void voornaamMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"voornaam","").isEmpty());
    }

    @Test
    public void voornaamMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"voornaam","").isEmpty());
    }

    @Test
    public void KlantFormHeeftCorrecteFamilienaam(){
        assertTrue(validator.validateValue(KlantForm.class,"familienaam","peeters").isEmpty());
    }

    @Test
    public void familienaamMagNietleegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"familienaam","").isEmpty());
    }

    @Test
    public void familienaamMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"familienaam",null).isEmpty());
    }

    @Test
    public void straatMetCorrecteNaam(){
        assertTrue(validator.validateValue(KlantForm.class,"straat","s-teststraat").isEmpty());
    }

    @Test
    public void straatMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"straat","").isEmpty());
    }

    @Test
    public void straatMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"straat",null).isEmpty());
    }

    @Test
    public void HuisnrMetCorrecteNaam(){
        assertTrue(validator.validateValue(KlantForm.class,"huisnr","3").isEmpty());
    }

    @Test
    public void HuisnrMetCorrecteNaam2(){
        assertTrue(validator.validateValue(KlantForm.class,"huisnr","3b").isEmpty());
    }

    @Test
    public void HuisnrMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"huisnr","").isEmpty());
    }

    @Test
    public void PostcodeMetCorrectePostcode(){
        assertTrue(validator.validateValue(KlantForm.class,"postcode","3000").isEmpty());
    }

    @Test
    public void PostcodeMetNietCorrectePostcode(){
        assertFalse(validator.validateValue(KlantForm.class,"postcode","12345").isEmpty());
    }

    @Test
    public void PostcodeMagNietUitLettersBestaan(){
        assertFalse(validator.validateValue(KlantForm.class,"postcode","duizend").isEmpty());
    }

    @Test
    public void PostcodMagNietLeefZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"postcode","").isEmpty());
    }

    @Test
    public void GemeenteMetCorrecteNaam(){
        assertTrue(validator.validateValue(KlantForm.class,"gemeente","Leuven").isEmpty());
    }

    @Test
    public void GemeenteMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"gemeente","").isEmpty());
    }

    @Test
    public void KlantFormHeeftCorrecteGebruikersnaam(){
        assertTrue(validator.validateValue(KlantForm.class,"gebruikersnaam","bert").isEmpty());
    }

    @Test
    public void gebruikersnaamMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"gebruikersnaam","").isEmpty());
    }

    @Test
    public void gebruikersnaamMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"gebruikersnaam","").isEmpty());
    }

    @Test
    public void KlantFormHeeftCorrectePaswoord(){
        assertTrue(validator.validateValue(KlantForm.class,"paswoord","paswoord123").isEmpty());
    }

    @Test
    public void paswoordMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"paswoord","").isEmpty());
    }

    @Test
    public void paswoordMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"paswoord","").isEmpty());
    }


    @Test
    public void KlantFormHeeftCorrectePaswoordCheck(){
        assertTrue(validator.validateValue(KlantForm.class,"paswoordcheck","paswoord123").isEmpty());
    }

    @Test
    public void paswoordCheckMagNietLeegZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"paswoordcheck","").isEmpty());
    }

    @Test
    public void paswoordCheckMagNietNullZijn(){
        assertFalse(validator.validateValue(KlantForm.class,"paswoordcheck","").isEmpty());
    }

    @Test
    public void paswoordAndPasWoordCheckMagNietVerschillendZijn(){

        KlantForm klantForm = new KlantForm("test","test",
                "test","test","1000",
                "test","test","test","test2");

        assertFalse(validator.validate(klantForm).isEmpty());
    }

    @Test
    public void paswoordAndPasWoordCheckZijnHetzelfde(){

        KlantForm klantForm = new KlantForm("test","test",
                "test","test","1000",
                "test","test","test","test");

        assertTrue(validator.validate(klantForm).isEmpty());
    }

}