package packageModel;


import java.util.Date;
import java.util.GregorianCalendar;

public class EmployeModel {

    private Integer identifiant;
    private String nom;
    private String prenom;
    private Character initialPrenomSupp;
    private GregorianCalendar dateEmbauche;
    private Integer telephone;
    private Integer telephonePro;
    private String email;
    private GregorianCalendar dateNaissance;
    private Boolean estATempsPartiel;
    private String libelleRue;
    private Integer numRue;
    private String typeEmploi;
    private Integer nbVeloDeclasse;
    private Boolean permiplateau;
    private Boolean estResponsableZone;
    private AtelierModel atelier;
    private LocaliteModel localite;
    private Integer responsable;

    public EmployeModel(){};


    public Integer  getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getInitialPrenomSupp() {
        return initialPrenomSupp;
    }

    public void setInitialPrenomSupp(Character initialPrenomSupp) {
        this.initialPrenomSupp = initialPrenomSupp;
    }

    public GregorianCalendar getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(GregorianCalendar dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getTelephonePro() {
        return telephonePro;
    }

    public void setTelephonePro(Integer telephonePro) {
        this.telephonePro = telephonePro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Boolean isEstATempsPartiel() {
        return estATempsPartiel;
    }

    public void setEstATempsPartiel(Boolean estATempsPartiel) {
        this.estATempsPartiel = estATempsPartiel;
    }

    public String getLibelleRue() {
        return libelleRue;
    }

    public void setLibelleRue(String libelleRue) {
        this.libelleRue = libelleRue;
    }

    public Integer getNumRue() {
        return numRue;
    }

    public void setNumRue(Integer numRue) {
        this.numRue = numRue;
    }

    public String getTypeEmploi() {
        return typeEmploi;
    }

    public void setTypeEmploi(String typeEmploi) {
        this.typeEmploi = typeEmploi;
    }

    public Integer getNbVeloDeclasse() {
        return nbVeloDeclasse;
    }

    public void setNbVeloDeclasse(Integer nbVeloDeclasse) {
        this.nbVeloDeclasse = nbVeloDeclasse;
    }

    public Boolean isPermiplateau() {
        return permiplateau;
    }

    public void setPermiplateau(Boolean permiplateau) {
        this.permiplateau = permiplateau;
    }

    public Boolean isEstResponsableZone() {
        return estResponsableZone;
    }

    public void setEstResponsableZone(Boolean estResponsableZone) {
        this.estResponsableZone = estResponsableZone;
    }

    public AtelierModel getAtelier() {
        return atelier;
    }

    public void setAtelier(AtelierModel atelier) {
        this.atelier = atelier;
    }

    public LocaliteModel getLocalite() {
        return localite;
    }

    public void setLocalite(LocaliteModel localite) {
        this.localite = localite;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String toString()
    {
        return this.nom +" "+this.prenom;
    }
}
