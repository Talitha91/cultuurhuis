package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.repositories.GenreRepository;
import net.bytebuddy.description.type.TypeDefinition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultGenreServiceTest {

    private List<Genre> genres = new ArrayList<>();

    @Mock
    private GenreRepository genreRepository;
    private DefaultGenreService defaultGenreService;

    @Before
    public void before(){
        genres.add(new Genre("bNaam"));
        genres.add(new Genre("aNaam"));

        when(genreRepository.findAll(any(Sort.class))).thenReturn(genres);
        defaultGenreService = new DefaultGenreService(genreRepository);
    }

    @Test
    public void findAllGeeftAlleGenresTerugInEenLijstGesorteerdOpNaam(){
        assertThat(defaultGenreService.findAllOrderAlphabetical().size()).isEqualTo(2);
    }

}
