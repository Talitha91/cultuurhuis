package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.entities.*;
import be.vdab.cultuurhuis.form.ReservatieForm;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultReservatieServiceTest {

    @Mock
    private ReservatieRepository reservatieRepository;
    @Mock
    private VoorstellingRepository voorstellingRepository;

    private ReservatieService reservatieService;

    private Voorstelling voorstelling;

    @Before
    public void before(){
        voorstelling =new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,10,new Genre("test"));

        reservatieService = new DefaultReservatieService(reservatieRepository,voorstellingRepository);
        when(voorstellingRepository.findById(any())).thenReturn(Optional.of(voorstelling));
    }

    @Test
    public void createAllGeeftEenMapMetgelukteEnMislukteReservaties(){
        List<ReservatieForm> reservatieForms = new ArrayList<>();
        ReservatieForm reservatieForm = new ReservatieForm(voorstelling,5);
        reservatieForms.add(reservatieForm);

        Klant klant = new Klant("test","test",
                new Adres("test","1","1000","test"),
                "test","test");

        Map<String,List<Reservatie>> creates = reservatieService.createAll(reservatieForms,klant);

        assertThat(creates.get("gelukt").size()).isEqualTo(1);
        assertThat(creates.get("mislukt").size()).isEqualTo(0);

    }

    @Test
    public void CreateAlVoegtVoorstellingToeAanMislukteReservatiesAlsTeWeinigVrijePLaatsen(){
        List<ReservatieForm> reservatieForms = new ArrayList<>();
        ReservatieForm reservatieForm = new ReservatieForm(voorstelling,100);
        reservatieForms.add(reservatieForm);

        Klant klant = new Klant("test","test",
                new Adres("test","1","1000","test"),
                "test","test");

        Map<String,List<Reservatie>> creates = reservatieService.createAll(reservatieForms,klant);

        assertThat(creates.get("gelukt").size()).isEqualTo(0);
        assertThat(creates.get("mislukt").size()).isEqualTo(1);

    }


}