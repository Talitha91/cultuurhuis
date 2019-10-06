package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.form.KlantForm;

import java.util.Optional;

public interface KlantService {

    Optional<Klant> findByGebruikersnaam(String gebruikersnaam);

    Klant createKlantFromKlantForm(KlantForm klantForm);

}
