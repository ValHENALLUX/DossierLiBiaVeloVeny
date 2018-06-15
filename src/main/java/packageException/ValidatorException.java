package packageException;

import packageTools.Validator;

public class ValidatorException extends Exception{

    private String propriete;
    private String erreur;

    public ValidatorException(String erreur, String propriete) {
        this.propriete = propriete;
        this.erreur = erreur;
    }
    
    public ValidatorException(String erreur) {
        this.propriete = propriete;
        this.erreur = erreur;
    }

    public String toString() {
        return "Erreur de validation du champ "+ propriete + " - erreur : "+ erreur;
    }
}
