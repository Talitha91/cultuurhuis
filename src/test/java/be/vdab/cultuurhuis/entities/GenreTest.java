package be.vdab.cultuurhuis.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.*;


public class GenreTest {

    private Validator validator;


    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void GenreMetCorrecteNaam(){
        assertTrue(validator.validateValue(Genre.class,"naam","horror").isEmpty());
    }

    @Test
    public void NaamGenreMagNietLeegZijn(){
        assertFalse(validator.validateValue(Genre.class,"naam","").isEmpty());
    }

    @Test
    public void NaamGenreMagNietNullZijn(){
        assertFalse(validator.validateValue(Genre.class,"naam",null).isEmpty());
    }





}