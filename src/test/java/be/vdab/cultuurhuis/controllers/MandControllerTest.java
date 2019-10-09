package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.*;
import be.vdab.cultuurhuis.form.ReservatieForm;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MandControllerTest {

    @Mock
    private MandSession mandSession;
    @Mock
    private KlantService klantService;
    @Mock
    private ReservatieService reservatieService;
    @Mock
    private VoorstellingService voorstellingService;

    private MandController mandController;


    @Before
    public void before(){
        when(klantService.findByGebruikersnaam("testnaam"))
                .thenReturn(Optional.of(new Klant("testnaam",
                        "testnaam",
                        new Adres("teststraat","3","3000","TestG"),
                        "testnaam","123")));

        mandController = new MandController(mandSession,klantService,reservatieService,voorstellingService);

        Map<String,List<Reservatie>> testReservaties = new HashMap<>();
        testReservaties.put("gelukt",new ArrayList<>());
        testReservaties.put("mislukt",new ArrayList<>());

        when(reservatieService.createAll(any(),any())).thenReturn(testReservaties);

    }

    @Test
    public void goToMandPaginaGaatNaarJuistePagina(){
        Model model = new ExtendedModelMap();
        assertThat(mandController.goToMandPagina(model)).isEqualTo("mand");
    }

    @Test
    public void goToMandPaginaGeeftJuisteAttributenMee(){
        Model model = new ExtendedModelMap();
        mandController.goToMandPagina(model);

        assertThat(model.asMap().get("reservaties")).isInstanceOf(List.class);
        assertThat(model.asMap().get("deletelist")).isInstanceOf(List.class);
    }

    @Test
    public void verwijderGekozenReservatiesGaatNaarJuistePagina(){
        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1),BigDecimal.TEN,10,new Genre("test"));
        mandSession.addReservatie(new ReservatieForm(voorstelling,2));
        Model model = new ExtendedModelMap();
        List<Long> deleteList = new ArrayList<>();

        assertThat(mandController.verwijderGekozenReservaties(model,deleteList)).isEqualTo("redirect:/mand");
    }

    @Test
    public void goToBevestiginPaginaGaatNaarJuistePagina(){
        Model model = new ExtendedModelMap();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("testnaam");

        assertThat(mandController.goToBevestigingPagina(model,mockPrincipal)).isEqualTo("bevestigen");
    }

    @Test
    public void goToBevestiginPaginaGeeftKlantInfoMee(){
        Model model = new ExtendedModelMap();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("testnaam");

        mandController.goToBevestigingPagina(model,mockPrincipal);

        assertThat(model.asMap().get("klant")).isInstanceOf(Klant.class);

        Klant klant = (Klant) model.asMap().get("klant");

    }

    @Test
    public void ReservatieOpslaanGaanNaarConfirmatiePagina(){
        Model model = new ExtendedModelMap();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("testnaam");

        assertThat(mandController.reservatiesOpslaan(model,mockPrincipal)).isEqualTo("confirmatiepagina");
    }

    @Test
    public void ReservatieOpslaanGeeftLijstVanGelukteEnMislukteReservaties(){
        Model model = new ExtendedModelMap();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("testnaam");

        mandController.reservatiesOpslaan(model,mockPrincipal);

        assertThat(model.asMap().get("misluktereservaties")).isInstanceOf(List.class);
        assertThat(model.asMap().get("geluktereservaties")).isInstanceOf(List.class);
    }


}