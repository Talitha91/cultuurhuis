package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.entities.Voorstelling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoorstellingRepository extends JpaRepository<Voorstelling,Long> {

    List<Voorstelling> findAllByGenreOrderByDatum(Genre genre);
}

