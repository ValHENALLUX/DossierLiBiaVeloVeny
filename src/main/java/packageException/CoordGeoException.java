package packageException;

public class CoordGeoException extends Exception
{
    private String message;

    public CoordGeoException(String exception)
    {
        switch (exception)
        {
            case "latitude" : message = "Erreur\n La latitude doit être comprise entre -90° et +90°";
                break;
            case "longitude" : message = "Erreur\n La longitude doit être comprise entre -180° et +180°";
                break;
            case "nombre" : message = "Erreur\n La latitude et la longitude doivent être des nombres";
                break;
            default : message = "Erreur de coordonnées géographiques inconnue";
                break;
        }
    }

    @Override
    public String toString()
    { return message; }
}
