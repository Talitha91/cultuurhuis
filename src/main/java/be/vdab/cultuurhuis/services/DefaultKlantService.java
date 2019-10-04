package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.form.NieuweKlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultKlantService implements KlantService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public Optional<Klant> findByGebruikersnaam(String gebruikersnaam) {
        return klantRepository.findByGebruikersnaam(gebruikersnaam);
    }

    @Override
    public boolean bestaatGebruikersnaamAl(String gebruikersnaam) {
        Optional<Klant> klant = klantRepository.findByGebruikersnaam(gebruikersnaam);

        if (klant.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Klant createKlantFromKlantForm(NieuweKlantForm klantForm) {

        if (gebruikersnaamBestaalAl(klantForm.getGebruikersnaam())){
            throw new KlantBestaatAlException();
        }

        Klant klant = new Klant(
                klantForm.getVoornaam(),
                klantForm.getFamilienaam(),
                new Adres(klantForm.getStraat(),klantForm.getHuisnr(),klantForm.getPostcode(),klantForm.getGemeente()),
                klantForm.getGebruikersnaam(),
                passwordEncoder.encode(klantForm.getPaswoord())
        );

        return klantRepository.save(klant);
    }

    private boolean gebruikersnaamBestaalAl(String gebruikersnaam) {

        if (findByGebruikersnaam(gebruikersnaam).isPresent()) {
            return true;
        }
        return false;
    }
}
