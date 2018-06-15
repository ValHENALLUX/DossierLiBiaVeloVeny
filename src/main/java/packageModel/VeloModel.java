package packageModel;


import java.util.Date;
import java.util.GregorianCalendar;

public class VeloModel {

    private Integer numVelo;
    private Boolean estEndommage;
    private GregorianCalendar dateAchat;
    private StationModel station;
    private EntrepriseModel entreprise;

    public Integer getNumVelo() {
        return numVelo;
    }

    public void setNumVelo(Integer numVelo) {
        this.numVelo = numVelo;
    }

    public Boolean isEstEndommage() {
        return estEndommage;
    }

    public void setEstEndommage(Boolean estEndommage) {
        this.estEndommage = estEndommage;
    }

    public GregorianCalendar getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(GregorianCalendar dateAchat) {
        this.dateAchat = dateAchat;
    }

    public StationModel getStation() {
        return station;
    }

    public void setStation(StationModel station) {
        this.station = station;
    }

    public EntrepriseModel getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(EntrepriseModel entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public String toString() {
        return numVelo.toString();
    }
}
