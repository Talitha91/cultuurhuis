package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultVoorstellingService implements VoorstellingService {

    private VoorstellingRepository voorstellingRepository;

    public DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }

    @Override
    public List<Voorstelling> findAllVoorstellingVoorGenre(Genre genre) {
        return voorstellingRepository.findAllByGenreOrderByDatum(genre);
    }
}
