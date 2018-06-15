package packageException;

public class BornesException extends Exception
{
    private String message;

    public BornesException(String exception)
    {
        switch (exception)
        {
            case "minExc" : message = "Erreur\n Le nombre minimum exceptionnel de vélos doit être strictement inférieur au nombre minimum normal";
                break;
            case "maxExc" : message = "Erreur\n Le nombre maximum exceptionnel de vélos doit être strictement supérieur au nombre maximum normal";
                break;
            case "norm" : message = "Erreur\n Le nombre minimum normal de vélo doit être inférieur ou égal au nombre maximum normal";
                break;
            case "nombre" : message = "Erreur\n Les nombres de vélos doivent être des nombres";
                break;
            default : message = "Erreur de bornes inconnue";
                break;
        }
        this.message = exception;
    }

    @Override
    public String toString()
    { return message; }
}
