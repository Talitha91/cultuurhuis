package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.entities.Adres;
import be.vdab.cultuurhuis.entities.Klant;
import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.form.KlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultKlantService implements KlantService {

    private PasswordEncoder passwordEncoder;
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Klant createKlantFromKlantForm(KlantForm klantForm) {

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

    @Override
    public Optional<Klant> findByGebruikersnaam(String gebruikersnaam) {
        return klantRepository.findByGebruikersnaam(gebruikersnaam);
    }

    private boolean gebruikersnaamBestaalAl(String gebruikersnaam) {
        return findByGebruikersnaam(gebruikersnaam).isPresent();
    }
}
