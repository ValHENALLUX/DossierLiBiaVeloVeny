package packageModel;

import java.util.GregorianCalendar;

public class StationModel {

    private Integer idStation;
    private String nom;
    private Integer nbVeloMinNorm;
    private Integer nbVeloMinEx;
    private Integer nbVeloMaxNorm;
    private Integer nbVeloMaxEx;
    private GregorianCalendar dateCreation;
    private Boolean couverte;
    private String libelleRue;
    private LocaliteModel localite;
    private String longitude;
    private String latitude;

    public StationModel()
    {}

    public Integer getIdStation() {return idStation;}
    public void setIdStation(Integer idStation) {this.idStation = idStation;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public Integer getNbVeloMinNorm () {return nbVeloMinNorm;}
    public void setNbVeloMinNorm(Integer nbVeloMinNorm) {this.nbVeloMinNorm = nbVeloMinNorm;}

    public Integer getNbVeloMinEx () {return nbVeloMinEx;}
    public void setNbVeloMinEx (Integer nbVeloMinEx) {this.nbVeloMinEx = nbVeloMinEx;}

    public Integer getNbVeloMaxNorm() {return nbVeloMaxNorm;}
    public void setNbVeloMaxNorm (Integer nbVeloMaxNorm) {this.nbVeloMaxNorm = nbVeloMaxNorm;}

    public Integer getNbVeloMaxEx () {return nbVeloMaxEx;}
    public void setNbVeloMaxEx (Integer nbVeloMaxEx) {this.nbVeloMaxEx = nbVeloMaxEx;}

    public GregorianCalendar getDateCreation () {return dateCreation;}
    public void setDateCreation (GregorianCalendar dateCreation) {this.dateCreation = dateCreation;}

    public String getLibelleRue () {return libelleRue;}
    public void setLibelleRue(String libelleRue) { this.libelleRue = libelleRue; }

    public LocaliteModel getLocalite() {
        return localite;
    }

    public void setLocalite(LocaliteModel localite) { this.localite = localite; }

    public Boolean estCouverte() {
        return couverte;
    }

    public void setCouverte(Boolean couverte) {
        this.couverte = couverte;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
