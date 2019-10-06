package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private List<Genre> genres = new ArrayList<>();
    private List<Voorstelling> voorstellingen = new ArrayList<>();

    private IndexController indexController;

    @Mock
    private GenreService genreService;
    @Mock
    private VoorstellingService voorstellingService;
    @Mock
    private MandSession mandSession;

    @Before
    public void before() {
        genres.add(new Genre("aNaam"));
        genres.add(new Genre("bNaam"));
        voorstellingen.add(new Voorstelling("atest","atest",new Date(2000,1,1), BigDecimal.ONE,1,genres.get(0)));
        when(genreService.findAllOrderAlphabetical()).thenReturn(genres);

        when(voorstellingService.findAllVoorstellingVoorGenre(any())).thenReturn(voorstellingen);
        indexController = new IndexController(genreService,voorstellingService,mandSession);
    }

    @Test
    public void goToIndexPaginaVerwijstNaarDeJuistePagina(){
        assertThat(indexController.goToWelkomPagina(new ExtendedModelMap())).isEqualTo("welkompagina");
    }

    @Test
    public void GoToIndexPaginaGeeftModelMetLijstGenresCorrectMee(){
        Model model = new ExtendedModelMap();
        indexController.goToWelkomPagina(model);
        assertThat(model.asMap().get("genres")).isInstanceOf(List.class);

        List<Genre> testGenres = (ArrayList<Genre>)  model.asMap().get("genres");

        assertThat(testGenres.get(0).getNaam()).isEqualTo("aNaam");
        assertThat(testGenres.get(1).getNaam()).isEqualTo("bNaam");
        assertThat(testGenres.size()).isEqualTo(2);
    }

    @Test
    public void getVoorstellingGekozenGenreGeeftJuistePaginaTerug(){
        Model model = new ExtendedModelMap();
        assertThat(indexController.getVoorstellingenVoorGenre(model, Optional.of(genres.get(0)))).isEqualTo("welkompagina");
    }

    @Test
    public void getVoorstellingenGekozenGenreGeeftAlleVoorstellingenEnGenreMee(){
        Model model = new ExtendedModelMap();
        indexController.getVoorstellingenVoorGenre(model, Optional.of(genres.get(0)));

        assertThat(model.asMap().get("voorstellingen")).isInstanceOf(List.class);
        List<Voorstelling> testVoorstelling = (ArrayList<Voorstelling>)  model.asMap().get("voorstellingen");
        assertThat(testVoorstelling.get(0).getTitel()).isEqualTo("atest");
        assertThat(model.asMap().get("gekozengenre")).isInstanceOf(Genre.class);
        assertThat(model.asMap().get("genres")).isInstanceOf(List.class);
    }

}
