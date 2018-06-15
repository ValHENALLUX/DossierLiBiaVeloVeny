package packageModel;


import java.util.Date;
import java.util.GregorianCalendar;

public class HistoriqueModel {

    private GregorianCalendar dateArrivee;
    private GregorianCalendar dateDepart;
    private StationModel station;
    private VeloModel velo;

    public GregorianCalendar getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(GregorianCalendar dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public GregorianCalendar getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(GregorianCalendar dateDepart) {
        this.dateDepart = dateDepart;
    }

    public StationModel getStation() {
        return station;
    }

    public void setStation(StationModel station) {
        this.station = station;
    }

    public VeloModel getVelo() {
        return velo;
    }

    public void setVelo(VeloModel velo) {
        this.velo = velo;
    }
}
