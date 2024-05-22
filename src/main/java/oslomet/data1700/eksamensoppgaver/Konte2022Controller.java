package oslomet.data1700.eksamensoppgaver;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Konte2022Controller {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(Konte2022Controller.class);

    // Oppgave 4
    @PostMapping("/lagrepakke")
    public void lagrepakke(Konte2022Pakke pakke, HttpServletResponse response) throws IOException {
        // Oppgave 5 med valideringen
        if (valider(pakke, response)) {
            String sql = "SELECT COUNT(*) FROM Lager WHERE LID=?";
            String insert = "INSERT INTO Pakke (LID, EIER, VEKT, VOLUM) VALUES (?,?,?,?)";
            try {
                int funnetLID = db.queryForObject(sql, Integer.class, pakke.getLID());
                if (funnetLID > 0){
                    db.update(insert, pakke.getLID(), pakke.getEIER(), pakke.getVEKT(), pakke.getVOLUM());
                } else {

                }
            }
            catch (Exception e) {

            }
        }
    }

    // Oppgave 5
    public boolean valider(Konte2022Pakke pakke, HttpServletResponse response) throws IOException {
        String navnRegex = "^[A-Za-z\\s]+";
        boolean navnOK = pakke.getEIER().matches(navnRegex);
        boolean vektOK = pakke.getVEKT() > 0;
        boolean volumOK = pakke.getVOLUM() > 0;

        if (navnOK && vektOK && volumOK) {
            return true;
        } else {
            // Ha med bilde av dette
            logger.error("pakke ble ikke validert riktig");
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke validere");
            return false;
        }
    }

    // Oppgave 6 og 8
    @GetMapping("/hentallepakker")
    public List<Konte2022Pakke> hentallepakker(HttpServletResponse response) throws IOException {
        if (session.getAttribute("innlogget") != null) {
            List<Konte2022Pakke> allePakker = db.query("SELECT * FROM Pakke", new BeanPropertyRowMapper<>(Konte2022Pakke.class));
            return allePakker;
        } else {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kan ikke hente pakker før brukeren er logget inn");
            return null;
        }
    }

    // Oppgave 7
    @GetMapping("/logginn")
    public boolean logginn(Bruker bruker) {
        if (sjekkNavnOgPassord(bruker)) {
            session.setAttribute("innlogget");
            return true;;
        } else {
            return false;
        }
    }

    public boolean sjekkNavnOgPassord(Bruker bruker) {
        String sql = "SELECT * FROM Bruker WHERE Brukernavn=?";
        try {
            Bruker DBbruker = db.queryForObject(sql, new BeanPropertyRowMapper.newInstance(Bruker.class), new Object()){bruker.getBrukernavn()});
        } catch (Exception e) {
            if (sjekkPassord(DBbruker.getPassord(), bruker.getPassord())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean sjekkPassord(String hashpassord, String passord) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if (bcrypt.matches(passord, hashpassord)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/lagerstatestikk")
    public String lagerstatestikk(Pakke pakke) {
        String antallLager1 = "SELECT COUNT(*) FROM Pakke WHERE LID=1";
        String antallLager2 = "SELECT COUNT(*) FROM Pakke WHERE LID=2";

        String volumLager1 = "SELECT SUM(VOLUM) FROM Pakke WHERE LID=1";
        String volumLager2 = "SELECT SUM(VOLUM) FROM Pakke WHERE LID=2";

        String vektLager1 = "SELECT SUM(VEKT) FROM Pakke WHERE LID=1";
        String vektLager2 = "SELECT SUM(VEKT) FROM Pakke WHERE LID=2";

        String hentNavn1 = "SELECT Navn FROM Pakke WHERE LID=1";
        String hentNavn2 = "SELECT Navn FROM Pakke WHERE LID=2";

        try {
            int ANTALLL1 = db.queryForObject(antallLager1, Integer.class);
            int ANTALLL2 = db.queryForObject(antallLager2, Integer.class);

            int VOLUM1 = db.queryForObject(volumLager1, Integer.class);
            int VOLUM2 = db.queryForObject(volumLager2, Integer.class);

            int VEKT1 = db.queryForObject(vektLager1, Integer.class);
            int VEKT2 = db.queryForObject(vektLager2, Integer.class);

            String NAVN1 = db.queryForObject(hentNavn1, String.class);
            String NAVN2 = db.queryForObject(hentNavn2, String.class);

            String ut = NAVN1+" inneholder "+ANTALLL1+" pakker med et totalvolum på "+VOLUM1+" kubikkmeter og en totalvekt på "+VEKT1+" kg." +
                    NAVN2+" inneholder "+ANTALLL2+" pakker med et totalvolum på "+VOLUM2+" kubikkmeter og en totalvekt på "+VEKT2+" kg";
            return ut;
        } catch (Exception e) {
            return null;
        }
    }
}
