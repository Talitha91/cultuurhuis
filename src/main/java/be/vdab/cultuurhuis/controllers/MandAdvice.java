package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.sessions.MandSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MandAdvice {

    private MandSession mandSession;

    public MandAdvice(MandSession mandSession) {
        this.mandSession = mandSession;
    }

    @ModelAttribute
    public void handleRequest(Model model){
        model.addAttribute("mandsize",mandSession.getMandSize());
    }

}
