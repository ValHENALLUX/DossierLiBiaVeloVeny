package packageModel;

public class EntrepriseModel {
    private Integer idEntreprise;
    private String nom;
    private String lieu;

    public Integer getIdEntreprise(){ return idEntreprise; }
    public void setIdEntreprise(Integer idEntreprise) { this.idEntreprise = idEntreprise; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
}
