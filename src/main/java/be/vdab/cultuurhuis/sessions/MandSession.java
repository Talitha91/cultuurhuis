package be.vdab.cultuurhuis.sessions;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@SessionScope
public class MandSession {

    private List<Reservatie> reservaties = new ArrayList<>();

    public MandSession() {
    }

    public void addReservatie(Reservatie reservatie) {
        reservaties.add(reservatie);
    }

    public void removeReservatie(Reservatie reservatie) {
        reservaties.remove(reservatie);
    }

    public List<Reservatie> getAlleReservaties() {
        return Collections.unmodifiableList(reservaties);
    }

    public Reservatie geefReservatieVoorVoorstellingOfMaakNieuweReservatie(Voorstelling voorstelling) {
        List<Reservatie> res = reservaties.stream()
                                        .filter(reservatie -> reservatie.getVoorstelling().equals(voorstelling))
                                        .collect(Collectors.toList());
        if (res.size()==1){
            return res.get(0);
        }else{
            return new Reservatie(null,voorstelling,0);
        }
    }

    public void deleteReservaties(List<Long> voorstellingIds){

        for (Long id : voorstellingIds) {
            reservaties.stream().forEach(e -> voorstellingIds.contains(e.getVoorstelling().getId()));
        }

        List<Reservatie> reservatiesToDelete = new ArrayList<>();

        for (Reservatie reservatie : reservaties) {
            if (voorstellingIds.contains(reservatie.getVoorstelling().getId())){
                reservatiesToDelete.add(reservatie);
            }
        }
        reservatiesToDelete.stream().forEach(r -> reservaties.remove(r));
    }

    public BigDecimal getTotaalTeBetalen(){
        BigDecimal result = BigDecimal.ZERO;

        for (Reservatie reservatie : reservaties) {
            result.add(reservatie.getVoorstelling().getPrijs().multiply(BigDecimal.valueOf(reservatie.getPlaatsen())));
        }
        return result;
    }

    public void addKlantenToReservaties(Klant klant){

        for (Reservatie reservatie : reservaties) {
            reservatie.setKlant(klant);
        }
    }
}