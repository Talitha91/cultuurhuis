package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Adres;
import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultKlantServiceTest {

    @Mock
    private KlantRepository klantRepository;
    private KlantService defaultKlantService;

    private KlantForm klantForm;
    private KlantForm klantForm2;

    @Before
    public void before(){
        klantForm = new KlantForm("test","test",
                "test","test",
                "1000","test","test","test","test");

        klantForm2 = new KlantForm("test2","test2",
                "test2","test2",
                "1000","test2","test2","test2","test2");

        when(klantRepository.save(any(Klant.class))).then(returnsFirstArg());
        when(klantRepository.findByGebruikersnaam(klantForm2.getGebruikersnaam()))
                .thenReturn(Optional.of(new Klant("test2","test2",
                        new Adres("test2","test2","1000","test2"),
                        "test2","test2")));

        defaultKlantService = new DefaultKlantService(klantRepository);
    }

    @Test
    public void CreateKlantFromKlantFormMaaktKlantVanKlantFormEnSlaapDitOp(){
        assertThat(defaultKlantService.createKlantFromKlantForm(klantForm)).isInstanceOf(Klant.class);

        Klant klant = defaultKlantService.createKlantFromKlantForm(klantForm);
        assertThat(klant.getGebruikersnaam()).isEqualTo("test");
    }

    @Test(expected = KlantBestaatAlException.class)
    public void CreateKlantAlBestaandeKlantGooitEenKlantBestaatAlException(){
        defaultKlantService.createKlantFromKlantForm(klantForm2);
    }

}