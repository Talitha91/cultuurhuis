package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
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

        return "mand";
    }

    @PostMapping("/verwijderen")
    public String VerwijderGekozenReservaties(Model model, @RequestParam List<Long> deletelist ){

        List<Voorstelling> voorstellingenToDelete = voorstellingService.findByIds(deletelist);

        mandSession.deleteReservaties(voorstellingenToDelete);

        return "redirect:/";
    }

    @GetMapping("/bevestigen")
    public String goToBevestigingPagina(Model model, Principal principal){

        System.out.println(klantService.findByGebruikersnaam(principal.getName()));

        model.addAttribute("klant",klantService.findByGebruikersnaam(principal.getName()).get());

        return "bevestigen";
    }

    @PostMapping("/opslaan")
    public String ReservatiesOpslaan(Model model, Principal principal){

        Klant klant = klantService.findByGebruikersnaam(principal.getName()).get();
        mandSession.addKlantenToReservaties(klant);

        List<Reservatie> mislukteReservaties = reservatieService.createAll(mandSession.getAlleReservaties());

        model.addAttribute("misluktereservaties", mislukteReservaties);
        model.addAttribute("allereservaties", mandSession.getAlleReservaties());

        return "confirmatiepagina";
    }
}