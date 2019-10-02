package be.vdab.cultuurhuis.controllers;

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

        model.addAttribute("reservatieform",reservatieForm);

        return "plaatsreservatie";
    }

    @PostMapping("/opslaan")
    public String opslaanReservatie(Model model, @Valid @ModelAttribute("reservatieform") ReservatieForm reservatieForm, BindingResult result){
        if (result.hasErrors()){
            System.out.println(reservatieForm);

        }

        return "redirect:/";
    }



}
