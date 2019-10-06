package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public void create(Reservatie reservatie) {
        reservatieRepository.save(reservatie);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public List<Reservatie> createAll(List<Reservatie> reservaties) {

        List<Reservatie> mislukteReservaties = new ArrayList<>();

        for (Reservatie reservatie : reservaties) {

            if (reservatie.getVoorstelling().getVrijeplaatsen()>=reservatie.getPlaatsen()){

                System.out.println("OKE vrijeplaatsen: " + reservatie.getVoorstelling().getVrijeplaatsen() + "vraag plaatsen: " + reservatie.getPlaatsen() );

                reservatie.getVoorstelling().verminderenplaatsen(reservatie.getPlaatsen());
                voorstellingRepository.save(reservatie.getVoorstelling());
                reservatieRepository.save(reservatie);

            }else{
                mislukteReservaties.add(reservatie);
            }
        }

    return mislukteReservaties;

    }
}
