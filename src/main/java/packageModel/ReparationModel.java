package packageModel;

import java.util.GregorianCalendar;

public class ReparationModel {

    private String ordreTravailFiche;
    private Integer numVelo;
    private Boolean estDeclasseFiche;
    private GregorianCalendar dateAchat;
    private Boolean estEndommageFiche;
    private String nomStation;
    private Boolean estCouverte;

    public ReparationModel(

            String ordreTravailFiche,
            Integer numVelo,
            Boolean estDeclasseFiche,
            GregorianCalendar dateAchat,
            Boolean estEndommageFiche,
            String nomStation,
            Boolean estCouverte)
    {
        this.ordreTravailFiche = ordreTravailFiche;
        this.numVelo = numVelo;
        this.estDeclasseFiche = estDeclasseFiche;
        this.dateAchat = dateAchat;
        this.estEndommageFiche = estEndommageFiche;
        this.nomStation = nomStation;
        this.estCouverte = estCouverte;
    }

    public ReparationModel(){

    }

    public String getOrdreTravailFiche() {
        return ordreTravailFiche;
    }

    public void setOrdreTravailFiche(String ordreTravailFiche) {
        this.ordreTravailFiche = ordreTravailFiche;
    }

    public Integer getNumVelo() { return numVelo; }

    public void setNumVelo(Integer numVelo) { this.numVelo = numVelo; }

    public Boolean getEstDeclasseFiche() {
        return estDeclasseFiche;
    }

    public void setEstDeclasseFiche(Boolean estDeclasseFiche) {
        this.estDeclasseFiche = estDeclasseFiche;
    }

    public GregorianCalendar getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(GregorianCalendar dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Boolean getEstEndommageFiche() {
        return estEndommageFiche;
    }

    public void setEstEndommageFiche(Boolean estEndommageFiche) {
        this.estEndommageFiche = estEndommageFiche;
    }

    public String getNomStation() {
        return nomStation;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public Boolean getEstCouverte() {
        return estCouverte;
    }

    public void setEstCouverte(Boolean estCouverte) {
        this.estCouverte = estCouverte;
    }

}
