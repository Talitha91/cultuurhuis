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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String verwijderGekozenReservaties(Model model, @RequestParam List<Long> deletelist ){

        if (deletelist.size()>0) {
            mandSession.deleteReservaties(voorstellingService.findByIds(deletelist));
        }
        return "redirect:/mand";
    }

    @GetMapping("/bevestigen")
    public String goToBevestigingPagina(Model model, Principal principal){

        if (mandSession.getMandSize()==0){
            return "redirect:/mand";
        }

        model.addAttribute("klant",klantService.findByGebruikersnaam(principal.getName()).get());
        return "bevestigen";
    }

    @PostMapping("/opslaan")
    public String reservatiesOpslaan(Model model, Principal principal, RedirectAttributes attributes){

        if (mandSession.getMandSize()<=0){
            return "500";
        }

        Klant klant = klantService.findByGebruikersnaam(principal.getName()).get();

        Map<String,List<Reservatie>> gelukteEnMislukteReservatie = reservatieService.createAll(mandSession.getAlleReservaties(),klant);

        attributes.addFlashAttribute("misluktereservaties", gelukteEnMislukteReservatie.get("mislukt"));
        attributes.addFlashAttribute("geluktereservaties", gelukteEnMislukteReservatie.get("gelukt"));


        mandSession.clearMand();

        return "redirect:/mand/confirmatiepagina";
    }

    @GetMapping("/confirmatiepagina")
    public String goToConfimatiePagina(Model model,
                                       @ModelAttribute("misluktereservaties") List<Reservatie> mislukteReservaties,
                                       @ModelAttribute("geluktereservaties") List<Reservatie> gelukteReservaties ){


        model.addAttribute("misluktereservaties",mislukteReservaties);
        model.addAttribute("geluktereservaties", gelukteReservaties);

        return "confirmatiepagina";
    }
}