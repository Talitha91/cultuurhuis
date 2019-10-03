package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface VoorstellingService {

    public List<Voorstelling> findAllVoorstellingVoorGenre(Genre genre);

    public Optional<Voorstelling> findById(long id);

    public List<Voorstelling> findByIds(List<Long> ids);

}
