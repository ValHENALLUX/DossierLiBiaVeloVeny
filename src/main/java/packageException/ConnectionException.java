package packageException;



public class ConnectionException extends Exception {

    private String error;
    public ConnectionException(String e) {error = e;}
    public String toString() {return "Erreur de connexion : "+ error;}
}
