package packageException;

public class ComparaisonDatesException extends Exception
{
    private String message;

    public ComparaisonDatesException(String date1, String date2)
    {
        message = "La " + date1 + " ne peut être inférieure à la " + date2;
    }

    @Override
    public String toString()
    { return message; }
}
