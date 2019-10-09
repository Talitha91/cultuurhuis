package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.Genre;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    private final GenreService genreService;
    private final VoorstellingService voorstellingService;
    private final MandSession mandSession;

    public IndexController(GenreService genreService, VoorstellingService voorstellingService, MandSession mandSession) {
        this.genreService = genreService;
        this.voorstellingService = voorstellingService;
        this.mandSession = mandSession;
    }

    @GetMapping
    public String goToWelkomPagina(Model model){

        model.addAttribute("genres",genreService.findAllOrderAlphabetical());
        return "welkompagina";
    }

    @GetMapping("/{optionalGenre}")
    public String getVoorstellingenVoorGenre(Model model, @PathVariable Optional<Genre> optionalGenre){

        if (optionalGenre.isPresent()){
            Genre gekozenGenre = optionalGenre.get();
            model.addAttribute("gekozengenre",gekozenGenre);
            model.addAttribute("voorstellingen", voorstellingService.findAllVoorstellingVoorGenre(gekozenGenre));
        }
        return goToWelkomPagina(model);
    }

}