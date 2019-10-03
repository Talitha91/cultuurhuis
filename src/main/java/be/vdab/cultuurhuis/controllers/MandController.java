package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mand")
public class MandController {

    private final MandSession mandSession;

    public MandController(MandSession mandSession) {
        this.mandSession = mandSession;
    }

    @GetMapping
    public String goToMandPagina(Model model){

        model.addAttribute("reservaties", mandSession.getAlleReservaties());
        model.addAttribute("deletelist", new ArrayList<>());

        return "mand";
    }

    @PostMapping("/verwijderen")
    public String VerwijderGekozenReservaties(Model model, @RequestParam List<Long> deletelist ){

        mandSession.deleteReservaties(deletelist);

        return "redirect:/";
    }
}