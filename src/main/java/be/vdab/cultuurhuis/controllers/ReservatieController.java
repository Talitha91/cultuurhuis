package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Reservaties;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.ReservatieForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/reserveren")
public class ReservatieController {


    @GetMapping("/{optionalVoorstelling}")
    public String goToReservatiePagina(Model model, @PathVariable Optional<Voorstelling> optionalVoorstelling){
        Voorstelling voorstelling = optionalVoorstelling.get();
        ReservatieForm reservatieForm = new ReservatieForm(voorstelling,0);

        //TODO inplaats van reservatieForm reservatie met groups gebruiken

        Reservaties reservaties = new Reservaties(null,voorstelling,0);
        model.addAttribute("reservatie",reservaties);

        model.addAttribute("voorstelling",voorstelling);
        model.addAttribute("reservatieform",reservatieForm);

        return "plaatsreservatie";
    }

    @PostMapping("/opslaan")
    public String opslaanReservatie(Model model, @Valid @ModelAttribute("reservatieform") ReservatieForm reservatieForm, BindingResult result){
        if (result.hasErrors()){
            System.out.println("ERRORS VOOR" + reservatieForm.toString());

        }
        System.out.println(reservatieForm.toString());
        return "redirect:/";
    }


}
