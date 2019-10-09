package be.vdab.cultuurhuis.sessions;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.form.ReservatieForm;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MandSessionTest {

    private MandSession mandSession;

    @Before
    public void before(){

        mandSession = new MandSession();

    }

    @Test
    public void addReservatieVoegtReservatieToeAlsDieNogNietBestaat(){

        int before = mandSession.getMandSize();

        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));
        mandSession.addReservatie(new ReservatieForm(voorstelling,2));

        int after = mandSession.getMandSize();

        assertThat(before+1).isEqualTo(after);
    }

    @Test
    public void addReservatieVoegtReservatieNietToeAlsErAlEenReservatieVoorDezeVoorsetellingIs(){
        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));
        mandSession.addReservatie(new ReservatieForm(voorstelling,2));

        int before = mandSession.getMandSize();

        mandSession.addReservatie(new ReservatieForm(voorstelling,5));

        int after = mandSession.getMandSize();

        assertThat(before).isEqualTo(after);

    }

    @Test
    public void geefReservatieVoorVoorstellillingOfMaaktNieuweReservatieGeeftBestaandeReservatie(){
        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));
        mandSession.addReservatie(new ReservatieForm(voorstelling,2));

        assertThat(
                mandSession
                        .geefReservatieVoorVoorstellingOfMaakNieuweReservatie(voorstelling)
                        .getPlaatsen())
                .isEqualTo(2);
    }

    @Test
    public void geefReservatieVoorVoorstellillingOfMaaktNieuweReservatieGeeftnieuweReservatie(){

        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));

        assertThat(
                mandSession
                        .geefReservatieVoorVoorstellingOfMaakNieuweReservatie(voorstelling)
                        .getPlaatsen())
                .isEqualTo(0);
    }

    @Test
    public void deleteReservatieVerwijderdAlleReservatieInLijst(){
        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));
        Voorstelling voorstelling2 = new Voorstelling("test2","test2",
                new Date(1), BigDecimal.TEN,100,new Genre("test2"));
        Voorstelling voorstelling3 = new Voorstelling("test3","test3",
                new Date(1), BigDecimal.TEN,100,new Genre("test3"));


        mandSession.addReservatie(new ReservatieForm(voorstelling,1));
        mandSession.addReservatie(new ReservatieForm(voorstelling2,4));
        mandSession.addReservatie(new ReservatieForm(voorstelling3,2));

        System.out.println(mandSession.getMandSize());

        List<Voorstelling> listToDelete = new ArrayList<>();
        listToDelete.add(voorstelling);
        listToDelete.add(voorstelling3);


        mandSession.deleteReservaties(listToDelete);

        assertThat(mandSession.getMandSize()).isEqualTo(1);
    }

    @Test
    public void getTotaalTeBetalenGeeftBerekentTotaalCorrect(){

        Voorstelling voorstelling = new Voorstelling("test","test",
                new Date(1), BigDecimal.TEN,100,new Genre("test"));
        Voorstelling voorstelling2 = new Voorstelling("test2","test2",
                new Date(1), BigDecimal.ONE,100,new Genre("test2"));

        mandSession.addReservatie(new ReservatieForm(voorstelling,3));
        mandSession.addReservatie(new ReservatieForm(voorstelling2,5));

        assertThat(mandSession.getTotaalTeBetalen()).isEqualTo(BigDecimal.valueOf(35));

    }

}