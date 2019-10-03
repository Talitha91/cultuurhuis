package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
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

    @InitBinder("reservatie")
    void initBinder(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @GetMapping("/{optionalVoorstelling}")
    public String goToReservatiePagina(Model model, @PathVariable Optional<Voorstelling> optionalVoorstelling){
        Voorstelling voorstelling = optionalVoorstelling.get();

        Reservatie reservatie = mandSession.geefReservatieVoorVoorstellingOfMaakNieuweReservatie(voorstelling);

        model.addAttribute("reservatie", reservatie);
        return "plaatsreservatie";
    }

    @PostMapping("/opslaan")
    public String opslaanReservatie(Model model, @Valid Reservatie reservatie, BindingResult result, SessionStatus sessionStatus){
        if (result.hasErrors()){
            return "plaatsreservatie";
        }
        mandSession.addReservatie(reservatie);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}