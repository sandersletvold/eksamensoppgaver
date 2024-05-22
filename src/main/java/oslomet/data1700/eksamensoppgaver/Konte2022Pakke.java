package oslomet.data1700.eksamensoppgaver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pakke")
public class Konte2022Pakke {
    @Id
    @GeneratedValue
    private Integer PID;
    private Integer LID;
    private String EIER;
    private Double VEKT;
    private Double VOLUM;

    public Konte2022Pakke(Integer PID, Integer LID, String EIER, Double VEKT, Double VOLUM) {
        this.PID = PID;
        this.LID = LID;
        this.EIER = EIER;
        this.VEKT = VEKT;
        this.VOLUM = VOLUM;
    }

    public Konte2022Pakke() {

    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public Integer getLID() {
        return LID;
    }

    public void setLID(Integer LID) {
        this.LID = LID;
    }

    public String getEIER() {
        return EIER;
    }

    public void setEIER(String EIER) {
        this.EIER = EIER;
    }

    public Double getVEKT() {
        return VEKT;
    }

    public void setVEKT(Double VEKT) {
        this.VEKT = VEKT;
    }

    public Double getVOLUM() {
        return VOLUM;
    }

    public void setVOLUM(Double VOLUM) {
        this.VOLUM = VOLUM;
    }
}
