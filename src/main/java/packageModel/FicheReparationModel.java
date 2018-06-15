package packageModel;


import java.util.Date;
import java.util.GregorianCalendar;

public class FicheReparationModel {

    private Integer numFiche;
    private GregorianCalendar dateDebut;
    private GregorianCalendar dateFin;
    private String remarque;
    private String ordreTravail;
    private Boolean estDeclasse;
    private VeloModel velo;
    private AtelierModel atelier;
    private Boolean valide;

    public Integer getNumFiche() {
        return numFiche;
    }

    public void setNumFiche(Integer numFiche) {
        this.numFiche = numFiche;
    }

    public GregorianCalendar getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(GregorianCalendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    public GregorianCalendar getDateFin() {
        return dateFin;
    }

    public void setDateFin(GregorianCalendar dateFin) {
        this.dateFin = dateFin;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getOrdreTravail() {
        return ordreTravail;
    }

    public void setOrdreTravail(String ordreTravail) {
        this.ordreTravail = ordreTravail;
    }

    public Boolean isEstDeclasse() {
        return estDeclasse;
    }

    public void setEstDeclasse(Boolean estDeclasse) {
        this.estDeclasse = estDeclasse;
    }

    public VeloModel getVelo() {
        return velo;
    }

    public void setVelo(VeloModel velo) {
        this.velo = velo;
    }

    public AtelierModel getAtelier() {
        return atelier;
    }

    public void setAtelier(AtelierModel atelier) {
        this.atelier = atelier;
    }

    public Boolean getValide() { return valide; }

    public void setValide(Boolean valide) { this.valide = valide; }
}
