package oslomet.data1700.eksamensoppgaver;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Konte2022Controller {

    @Autowired
    private JdbcTemplate db;

    @PostMapping("/lagrepakke")
    public void lagrepakke(Konte2022Pakke pakke) {
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
