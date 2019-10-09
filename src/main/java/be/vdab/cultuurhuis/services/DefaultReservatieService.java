package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.form.ReservatieForm;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultReservatieService implements ReservatieService {

    private final ReservatieRepository reservatieRepository;
    private final VoorstellingRepository voorstellingRepository;

    public DefaultReservatieService(ReservatieRepository reservatieRepository, VoorstellingRepository voorstellingRepository) {
        this.reservatieRepository = reservatieRepository;
        this.voorstellingRepository = voorstellingRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Map<String,List<Reservatie>> createAll(List<ReservatieForm> reservatieForms, Klant klant) {

        List<Reservatie> reservaties = convertFormToReservatie(reservatieForms,klant);

        Map<String,List<Reservatie>> gelukteEnMislukteReservaties = new HashMap<>();
        List<Reservatie> mislukteReservaties = new ArrayList<>();
        List<Reservatie> gelukteReservaties = new ArrayList<>();

        for (Reservatie reservatie : reservaties) {

            if (reservatie.getVoorstelling().getVrijeplaatsen()>=reservatie.getPlaatsen()){

                reservatie.getVoorstelling().verminderenPlaatsen(reservatie.getPlaatsen());
                voorstellingRepository.save(reservatie.getVoorstelling());
                reservatieRepository.save(reservatie);
                gelukteReservaties.add(reservatie);
            }else{
                mislukteReservaties.add(reservatie);
            }
        }

        gelukteEnMislukteReservaties.put("gelukt",gelukteReservaties);
        gelukteEnMislukteReservaties.put("mislukt",mislukteReservaties);
        return gelukteEnMislukteReservaties;
    }

    private List<Reservatie> convertFormToReservatie(List<ReservatieForm> forms, Klant klant){
        List<Reservatie> reservaties = new ArrayList<>();

        for (ReservatieForm form : forms) {
            reservaties.add(new Reservatie(klant, voorstellingRepository.findById(form.getVoorstelling().getId()).get() ,form.getPlaatsen()));
        }
        return reservaties;
    }
}
