package packageModel;

import java.util.GregorianCalendar;

public class InformationMarquesVeloModel {

    private String localite;
    private String nomStation;
    private String rueStation;
    private String longitude;
    private String latitude;
    private Boolean couverteStation;
    private String numVelo;
    private GregorianCalendar dateAchatVelo;
    private GregorianCalendar dateArriveeVelo;

    public InformationMarquesVeloModel(
            String localite,
            String nomStation,
            String rueStation,
            String longitude,
            String latitude,
            Boolean couverteStation,
            String numVelo,
            GregorianCalendar dateAchatVelo,
            GregorianCalendar dateArriveeVelo)
    {
        this.localite = localite;
        this.nomStation = nomStation;
        this.rueStation = rueStation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.couverteStation = couverteStation;
        this.numVelo = numVelo;
        this.dateAchatVelo = dateAchatVelo;
        this.dateArriveeVelo = dateArriveeVelo;
    }

    public InformationMarquesVeloModel() {

    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getNomStation() {
        return nomStation;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public String getRueStation() {
        return rueStation;
    }

    public void setRueStation(String rueStation) {
        this.rueStation = rueStation;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Boolean getCouverteStation() {
        return couverteStation;
    }

    public void setCouverteStation(Boolean couverteStation) {
        this.couverteStation = couverteStation;
    }

    public String getNumVelo() {
        return numVelo;
    }

    public void setNumVelo(String numVelo) {
        this.numVelo = numVelo;
    }

    public GregorianCalendar getDateAchatVelo() {
        return dateAchatVelo;
    }

    public void setDateAchatVelo(GregorianCalendar dateAchatVelo) {
        this.dateAchatVelo = dateAchatVelo;
    }

    public GregorianCalendar getDateArriveeVelo() { return dateArriveeVelo; }

    public void setDateArriveeVelo(GregorianCalendar dateArriveeVelo) { this.dateArriveeVelo = dateArriveeVelo; }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

}
