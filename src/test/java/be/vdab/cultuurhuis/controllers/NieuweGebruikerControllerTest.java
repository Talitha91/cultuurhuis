package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.services.KlantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NieuweGebruikerControllerTest {

    private NieuweGebruikerController controller;

    @Mock
    private KlantService klantService;

    private KlantForm validKlantForm;

    private KlantForm inValidKlantForm;

    @Before
    public void before(){
        validKlantForm = new KlantForm("test1","test1","test1",
                                "test1","1000","test1","test1",
                            "test1","test1");

        inValidKlantForm = new KlantForm("","",
                "","","","",
                "","","");

        controller = new NieuweGebruikerController(klantService);

        when(klantService.findByGebruikersnaam(any())).thenReturn(null);
    }

    @Test
    public void goToNieuweGebruikerPaginaGaatNaarJuistePagina(){
        ExtendedModelMap model = new ExtendedModelMap();

        assertThat(controller.goToNieuweGebruikerPagina(model)).isEqualTo("nieuwegebruikerform");
    }

    @Test
    public void geToNieuweGebruikerPaginaVoegtLegeKlantFormToeAanModel(){
        ExtendedModelMap model = new ExtendedModelMap();
        controller.goToNieuweGebruikerPagina(model);

        assertThat(model.asMap().get("klant")).isInstanceOf(KlantForm.class);
    }

    @Test
    public void slaNieuweKlantOpMetValidKlantFormGaatNaarLoginPagina(){
        ExtendedModelMap model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);

        assertThat(controller.slaNieuweKlantOp(model, validKlantForm,result)).isEqualTo("redirect:/login");
    }

}