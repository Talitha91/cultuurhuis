package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.form.NieuweKlantForm;

import java.util.Optional;

public interface KlantService {

    public Optional<Klant> findByGebruikersnaam(String gebruikersnaam);

    public boolean bestaatGebruikersnaamAl(String gebruikersnaam);

    public Klant createKlantFromKlantForm(NieuweKlantForm klantForm);

}
