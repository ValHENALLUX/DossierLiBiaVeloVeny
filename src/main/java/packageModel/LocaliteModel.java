package packageModel;

public class LocaliteModel {
    private Integer id;
    private String libelle;
    private Integer codePostal;

    public LocaliteModel(){
    }

    public Integer getId () {return id;}
    public void setId(Integer id) { this.id = id; }

    public String getLibelle () {return libelle;}
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Integer getCodePostal () {return codePostal;}
    public void setCodePostal(Integer codePostal) { this.codePostal = codePostal; }
    
    public String toString() 
    {
        return this.codePostal + " - " + this.libelle;
    }
}
