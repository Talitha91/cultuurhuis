package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAllOrderAlphabetical();

}
