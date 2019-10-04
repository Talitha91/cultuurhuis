package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Reservatie;

import java.util.List;

public interface ReservatieService {

    void create(Reservatie reservatie);
    void createAll(List<Reservatie> reservaties);

}
