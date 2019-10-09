package be.vdab.cultuurhuis.sessions;

import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.form.ReservatieForm;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@SessionScope
public class MandSession {

    private List<ReservatieForm> reservaties = new ArrayList<>();

    public MandSession() {}

    public void addReservatie(ReservatieForm reservatieForm) {
        if (!reservaties.contains(reservatieForm)){
            reservaties.add(reservatieForm);
        }
    }

    public List<ReservatieForm> getAlleReservaties() {
        return Collections.unmodifiableList(reservaties);
    }

    public ReservatieForm geefReservatieVoorVoorstellingOfMaakNieuweReservatie(Voorstelling voorstelling) {
        List<ReservatieForm> res = reservaties.stream()
                                        .filter(reservatie -> reservatie.getVoorstelling().equals(voorstelling))
                                        .collect(Collectors.toList());
        if (res.size()==1){
                return res.get(0);
        }else{
            return new ReservatieForm(voorstelling,0);
        }
    }

    public void deleteReservaties(List<Voorstelling> voorstellingToDelete){

        List<ReservatieForm> reservatiesToDelete = new ArrayList<>();

        for (ReservatieForm reservatie : reservaties) {
            if (voorstellingToDelete.contains(reservatie.getVoorstelling())){
                reservatiesToDelete.add(reservatie);
            }
        }
        for (ReservatieForm reservatie : reservatiesToDelete) {
            reservaties.remove(reservatie);
        }
    }

    public void clearMand(){
        this.reservaties = new ArrayList<>();
    }

    public int getMandSize(){
        return this.reservaties.size();
    }

    public BigDecimal getTotaalTeBetalen(){
        BigDecimal result = BigDecimal.ZERO;

        for (ReservatieForm reservatie : reservaties) {
           result = result.add(reservatie.getVoorstelling().getPrijs().multiply(BigDecimal.valueOf(reservatie.getPlaatsen())));
        }
        return result;
    }
}