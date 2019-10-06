package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultKlantServiceTest {

    @Mock
    private KlantRepository klantRepository;
    private DefaultKlantService defaultKlantService;

    @Before
    public void before(){
        KlantForm KlantForm = new KlantForm("test","test",
                "test","test",
                "1000","test","test","test","test");

    }

    @Test
    public void createKlantFromKlantenFormGooitExceptionAlsKlantAlBestaat(){

    }


}