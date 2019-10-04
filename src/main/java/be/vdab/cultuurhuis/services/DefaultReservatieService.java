package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultReservatieService implements ReservatieService {

    private final ReservatieRepository reservatieRepository;

    public DefaultReservatieService(ReservatieRepository reservatieRepository) {
        this.reservatieRepository = reservatieRepository;
    }

    @Override
    public void create(Reservatie reservatie) {
        reservatieRepository.save(reservatie);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void createAll(List<Reservatie> reservaties) {
        reservatieRepository.saveAll(reservaties);
    }
}
