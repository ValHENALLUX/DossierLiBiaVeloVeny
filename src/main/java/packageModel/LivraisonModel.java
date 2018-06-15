package packageModel;

import java.util.GregorianCalendar;

public class LivraisonModel {

    private GregorianCalendar dateTransport;
    private Integer numVelo;
    private String nomStation;
    private String libelleRueStation;
    private String coordGeoStation;
    private Integer codePostalLocalite;
    private String libelleLocalite;

    public LivraisonModel (
            GregorianCalendar dateTransport,
            Integer numVelo,
            String nomStation,
            String libelleRueStation,
            String coordGeoStation,
            Integer codePostalLocalite,
            String libelleLocalite
    )
    {
        this.dateTransport = dateTransport;
        this.numVelo = numVelo;
        this.nomStation = nomStation;
        this.libelleRueStation = libelleRueStation;
        this.coordGeoStation = coordGeoStation;
        this.codePostalLocalite = codePostalLocalite;
        this.libelleLocalite = libelleLocalite;
    }
    public LivraisonModel(){

    }


    public GregorianCalendar getDateTransport() {
        return dateTransport;
    }

    public void setDateTransport(GregorianCalendar dateTransport) {
        this.dateTransport = dateTransport;
    }

    public Integer getNumVelo() {
        return numVelo;
    }

    public void setNumVelo(Integer numVelo) {
        this.numVelo = numVelo;
    }

    public String getNomStation() {
        return nomStation;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public String getLibelleRueStation() {
        return libelleRueStation;
    }

    public void setLibelleRueStation(String libelleRueStation) {
        this.libelleRueStation = libelleRueStation;
    }

    public String getCoordGeoStation() {
        return coordGeoStation;
    }

    public void setCoordGeoStation(String coordGeoStation) {
        this.coordGeoStation = coordGeoStation;
    }

    public Integer getCodePostalLocalite() {
        return codePostalLocalite;
    }

    public void setCodePostalLocalite(Integer codePostalLocalite) {
        this.codePostalLocalite = codePostalLocalite;
    }

    public String getLibelleLocalite() {
        return libelleLocalite;
    }

    public void setLibelleLocalite(String libelleLocalite) {
        this.libelleLocalite = libelleLocalite;
    }
}
