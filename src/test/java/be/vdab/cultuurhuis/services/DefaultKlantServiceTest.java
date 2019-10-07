package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class DefaultKlantServiceTest {

    @Mock
    private KlantRepository klantRepository;
    private DefaultKlantService defaultKlantService;

    private KlantForm KlantForm;

    @Before
    public void before(){
        KlantForm = new KlantForm("test","test",
                "test","test",
                "1000","test","test","test","test");

        defaultKlantService = new DefaultKlantService(klantRepository);
    }
    
}