package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mand")
public class MandController {

    private final MandSession mandSession;
    private final KlantService klantService;
    private final ReservatieService reservatieService;
    private final VoorstellingService voorstellingService;

    public MandController(MandSession mandSession, KlantService klantService, ReservatieService reservatieService, VoorstellingService voorstellingService) {
        this.mandSession = mandSession;
        this.klantService = klantService;
        this.reservatieService = reservatieService;
        this.voorstellingService = voorstellingService;
    }

    @GetMapping
    public String goToMandPagina(Model model){

        model.addAttribute("reservaties", mandSession.getAlleReservaties());
        model.addAttribute("totaalBetalen",mandSession.getTotaalTeBetalen());
        model.addAttribute("deletelist", new ArrayList<>());
        model.addAttribute("mandsize",mandSession.getMandSize());
        return "mand";
    }

    @PostMapping("/verwijderen")
    public String VerwijderGekozenReservaties(Model model, @RequestParam List<Long> deletelist ){

        mandSession.deleteReservaties(voorstellingService.findByIds(deletelist));
        return "redirect:/";
    }

    @GetMapping("/bevestigen")
    public String goToBevestigingPagina(Model model, Principal principal){

        model.addAttribute("klant",klantService.findByGebruikersnaam(principal.getName()).get());
        return "bevestigen";
    }

    @PostMapping("/opslaan")
    public String ReservatiesOpslaan(Model model, Principal principal){

        Klant klant = klantService.findByGebruikersnaam(principal.getName()).get();

        List<List<Reservatie>> gelukteEnMislukteReservatie = reservatieService.createAll(mandSession.getAlleReservaties(),klant);

        model.addAttribute("misluktereservaties", gelukteEnMislukteReservatie.get(1));
        model.addAttribute("allereservaties", gelukteEnMislukteReservatie.get(0));

        mandSession.clearMand();

        return "confirmatiepagina";
    }
}