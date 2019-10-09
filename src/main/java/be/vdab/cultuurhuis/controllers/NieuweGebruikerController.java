package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/nieuwegebruiker")
public class NieuweGebruikerController {


    private final KlantService klantService;

    public NieuweGebruikerController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping
    public String goToNieuweGebruikerPagina(Model model) {

        model.addAttribute("klant", new KlantForm(null,null,null,null,null,null,null,null,null));

        return "nieuwegebruikerform";
    }

    @PostMapping("/opslaan")
    public String slaNieuweKlantOp(Model model, @Valid @ModelAttribute("klant") KlantForm klant, BindingResult result){

        if (result.hasErrors()){
            return "nieuwegebruikerform";
        }
        try{
            klantService.createKlantFromKlantForm(klant);
            return "redirect:/login";
        }catch (KlantBestaatAlException ex){
            result.rejectValue("gebruikersnaam","Gebruikerns.BestaatAl","gebruikersnaam bestaat al");
            return "nieuwegebruikerform";
        }
    }



}
