package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;

import java.util.List;
import java.util.Optional;

public interface VoorstellingService {

    List<Voorstelling> findAllVoorstellingVoorGenre(Genre genre);

    Optional<Voorstelling> findById(long id);

    List<Voorstelling> findByIds(List<Long> ids);

}
