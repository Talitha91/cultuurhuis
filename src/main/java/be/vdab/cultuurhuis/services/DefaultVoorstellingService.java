package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultVoorstellingService implements VoorstellingService {

    private VoorstellingRepository voorstellingRepository;

    public DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }

    @Override
    public List<Voorstelling> findAllVoorstellingVoorGenre(Genre genre) {
        return voorstellingRepository.findAllByGenreOrderByDatum(genre);
    }

    @Override
    public Optional<Voorstelling> findById(long id) {
        return voorstellingRepository.findById(id);
    }

    @Override
    public List<Voorstelling> findByIds(List<Long> ids) {
        return voorstellingRepository.findAllById(ids);
    }
}
