package be.vdab.cultuurhuis.form;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

public class ReservatieFormTest {

    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void VoorstellingMagNietNullZijn(){
        assertFalse(validator.validateValue(ReservatieForm.class,"voorstelling",null).isEmpty());
    }

    @Test
    public void CorrectAantalPlaatsen(){
        assertTrue(validator.validateValue(ReservatieForm.class,"plaatsen",5).isEmpty());
    }

    @Test
    public void AantalPlaatsenMagNietNulZijn(){
        assertFalse(validator.validateValue(ReservatieForm.class,"plaatsen",0).isEmpty());
    }

    @Test
    public void AantalPlaatsenMagNietNegatiefZijn(){
        assertFalse(validator.validateValue(ReservatieForm.class,"plaatsen",-5).isEmpty());
    }

    @Test
    public void aantalPlaatsenIsMinderGrootOfGelijkAanAantalVrijePlaatsen(){
        ReservatieForm reservatieForm = new ReservatieForm(new Voorstelling("test",
                "test",new Date(1l), BigDecimal.TEN,10,new Genre("test")),5);

        assertTrue(validator.validate(reservatieForm).isEmpty());
    }

    @Test
    public void aantalPlaatsenMagNietGroterZijnDanAantalVrijePlaatsenVoorstelling(){

        ReservatieForm reservatieForm = new ReservatieForm(new Voorstelling("test",
                "test",new Date(1l), BigDecimal.TEN,10,new Genre("test")),11);

        assertFalse(validator.validate(reservatieForm).isEmpty());
    }



}