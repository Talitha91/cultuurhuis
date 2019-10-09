package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.form.ReservatieForm;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class ReservatieControllerTest {

    private ReservatieController reservatieController;

    private MandSession mandSession;


    @Before
    public void before(){
        mandSession = new MandSession();reservatieController = new ReservatieController(mandSession);
    }

    @Test
    public void goToReservatiePaginaGaatNaarJuistePagine(){
        Model model = new ExtendedModelMap();
        Optional<Voorstelling> optionalTest = Optional.of(new Voorstelling("test","test",new Date(1), BigDecimal.TEN,100,new Genre("test")));

        assertThat(reservatieController.goToReservatiePagina(model,optionalTest)).isEqualTo("plaatsreservatie");
    }

    @Test
    public void goToReservatiePaginaGeeftReservatieFormMee(){

        Model model = new ExtendedModelMap();
        Optional<Voorstelling> optionalTest = Optional.of(new Voorstelling("test","test",new Date(1), BigDecimal.TEN,100,new Genre("test")));

        reservatieController.goToReservatiePagina(model,optionalTest);

        assertThat(model.asMap().get("reservatie")).isInstanceOf(ReservatieForm.class);

        ReservatieForm reservatieForm = (ReservatieForm) model.asMap().get("reservatie");

        assertThat(reservatieForm.getVoorstelling().getTitel()).isEqualTo("test");
    }

    @Test
    public void opslaanReservatieRedirectNaarWelkomPagina(){
        Model model = new ExtendedModelMap();
        Voorstelling voorstelling = (new Voorstelling("test","test",new Date(1), BigDecimal.TEN,100,new Genre("test")));
        ReservatieForm reservatieForm = new ReservatieForm(voorstelling,5);
        BindingResult result = mock(BindingResult.class);
        SessionStatus sessionStatus = mock(SessionStatus.class);


        assertThat(reservatieController.opslaanReservatie(model,reservatieForm,result,sessionStatus)).isEqualTo("redirect:/");


    }


}