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

    public Konte2022Pakke(Integer PID, Double VOLUM, Double VEKT, String EIER, Integer LID) {
        this.VOLUM = VOLUM;
        this.VEKT = VEKT;
        this.EIER = EIER;
        this.LID = LID;
        this.PID = PID;
    }

    public Konte2022Pakke(Double VOLUM, Double VEKT, String EIER, Integer LID) {
        this.VOLUM = VOLUM;
        this.VEKT = VEKT;
        this.EIER = EIER;
        this.LID = LID;
    }

    public Konte2022Pakke() {

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

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }
}
