package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.form.ReservatieForm;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/reserveren")
@SessionAttributes("reservatie")
public class ReservatieController {

    private final MandSession mandSession;

    public ReservatieController(MandSession mandSession) {
        this.mandSession = mandSession;
    }

    @GetMapping("/{optionalVoorstelling}")
    public String goToReservatiePagina(Model model, @PathVariable Optional<Voorstelling> optionalVoorstelling){
        Voorstelling voorstelling = optionalVoorstelling.get();

        ReservatieForm reservatie = mandSession.geefReservatieVoorVoorstellingOfMaakNieuweReservatie(voorstelling);

        model.addAttribute("reservatie", reservatie);
        model.addAttribute("mandsize",mandSession.getMandSize());

        return "plaatsreservatie";
    }

    @PostMapping("/opslaan")
    public String opslaanReservatie(Model model, @Valid @ModelAttribute("reservatie") ReservatieForm reservatie, BindingResult result, SessionStatus sessionStatus, Errors errors){
        if (result.hasErrors()){
            model.addAttribute("mandsize",mandSession.getMandSize());
            return "plaatsreservatie";
        }
        mandSession.addReservatie(reservatie);
        sessionStatus.setComplete();
        model.addAttribute("mandsize",mandSession.getMandSize());

        return "redirect:/";
    }
}