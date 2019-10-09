package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.form.ReservatieForm;

import java.util.List;
import java.util.Map;

public interface ReservatieService {

    Map<String,List<Reservatie>> createAll(List<ReservatieForm> reservatiesForm, Klant klant);

}
