package packageModel;


import java.util.Date;
import java.util.GregorianCalendar;

public class OrdreTransportModel {

    private Integer numOrdre;
    private GregorianCalendar dateTransport;
    private VeloModel velo;
    private StationModel station;
    private StationModel stationEmettrice;
    private StationModel stationOrigine;
    private StationModel stationDestination;
    private EmployeModel employe;


    public int getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(int numOrdre) {
        this.numOrdre = numOrdre;
    }

    public GregorianCalendar getDateTransport() {
        return dateTransport;
    }

    public void setDateTransport(GregorianCalendar dateTransport) {
        this.dateTransport = dateTransport;
    }

    public VeloModel getVelo() {
        return velo;
    }

    public void setVelo(VeloModel velo) {
        this.velo = velo;
    }

    public StationModel getStation() {
        return station;
    }

    public void setStation(StationModel station) {
        this.station = station;
    }

    public StationModel getStationEmettrice() {
        return stationEmettrice;
    }

    public void setStationEmettrice(StationModel stationEmettrice) {
        this.stationEmettrice = stationEmettrice;
    }

    public StationModel getStationOrigine() {
        return stationOrigine;
    }

    public void setStationOrigine(StationModel stationOrigine) {
        this.stationOrigine = stationOrigine;
    }

    public StationModel getStationDestination() {
        return stationDestination;
    }

    public void setStationDestination(StationModel stationDestination) {
        this.stationDestination = stationDestination;
    }

    public EmployeModel getEmploye() {
        return employe;
    }

    public void setEmploye(EmployeModel employe) {
        this.employe = employe;
    }
}
