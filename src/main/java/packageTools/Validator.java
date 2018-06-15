package packageTools;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packageException.ComparaisonDatesException;
import packageException.ValidatorException;

public class Validator {

    private String regexString = "^[\\p{L} .'-]+$";
    private String regexDate = "^(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})$";
    private String regexLatitude = "^(((\\d{1,2})|(\\d{1,2},\\d{1,4}))([ns]))$";
    private String regexLongitude = "^(((\\d{1,2})|(\\d{1,2},\\d{1,4}))([oe]))$";
		
    public Validator(){}

    public String verificationTexte(String texte, String champ) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexString);

        if(texte == null) throw new ValidatorException("Le texte est vide", champ);
        if(texte.isEmpty()) throw new ValidatorException("Le texte est vide", champ);
        if(texte.length() < 2 || texte.length() > 50) throw new ValidatorException("Le texte doit etre composé de minimum 2 et de maximum 50 caractères", champ);
        Matcher m = r.matcher(texte); 
        if (!m.find()) throw new ValidatorException("Le texte doit être composé uniquement de lettre", champ);
        else return texte;
    }
    
    public GregorianCalendar verificationDate(String texteDate, String champ) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexDate);
        Matcher m = r.matcher(texteDate);
        if(texteDate.isEmpty()) throw new ValidatorException("La date ne peut pas être vide", champ);
        if(!m.find()) throw new ValidatorException("La date ne respecte pas le format dd/mm/yyyy", champ);
        else {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = df.parse(texteDate);
                GregorianCalendar maintenant =(GregorianCalendar) GregorianCalendar.getInstance();
                GregorianCalendar dateResultat =(GregorianCalendar) GregorianCalendar.getInstance();
                dateResultat.setTime(date);
                if (dateResultat.compareTo(maintenant) > 0)throw new ValidatorException("La date est plus grande que la date actuelle", champ);
                if (dateResultat.get(Calendar.YEAR) < 1920) throw new ValidatorException("L'année de la date doit être plus grande que 1920", champ);
                return dateResultat;
            } 
            catch (ParseException ex) {
                throw new ValidatorException("La date ne respecte pas le format dd/mm/yyyy", champ);
            }            
        }
    }

    public GregorianCalendar verificationDateFin(String texteDate, String champ) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexDate);
        Matcher m = r.matcher(texteDate);
        if(texteDate.isEmpty()) throw new ValidatorException("La date ne peut pas être vide", champ);
        if(!m.find()) throw new ValidatorException("La date ne respecte pas le format dd/mm/yyyy", champ);
        else {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = df.parse(texteDate);
                GregorianCalendar maintenant =(GregorianCalendar) GregorianCalendar.getInstance();
                GregorianCalendar dateResultat =(GregorianCalendar) GregorianCalendar.getInstance();
                dateResultat.setTime(date);
                if (dateResultat.get(Calendar.YEAR) < 1920) throw new ValidatorException("L'année de la date doit être plus grande que 1920", champ);
                return dateResultat;
            }
            catch (ParseException ex) {
                throw new ValidatorException("La date ne respecte pas le format dd/mm/yyyy", champ);
            }
        }
    }

    public int verificationChiffreAvecTexte(String nombre, String champ) throws ValidatorException
    {        
        if (nombre.isEmpty()) throw new ValidatorException("Le nombre est vide",champ);
        else {
            try {
                int resultat = Integer.parseInt(nombre);
                if (resultat < 0) throw new ValidatorException("Le nombre doit être positif",champ);
                return resultat;
            }   
            catch (NumberFormatException e) {
                throw new ValidatorException("Le nombre doit être composé de chiffre", champ);
            }
        }
    }
    
    public int verificationChiffre (int nombre, String champ) throws ValidatorException
    {        
        if (nombre < 0) throw new ValidatorException("Le nombre doit être positif",champ);
        return nombre;
    }

    public void verificationBornes (int minNorm, int minEx, int  maxNorm, int maxEx) throws ValidatorException
    {
        if (minNorm < minEx) throw new ValidatorException("La borne inferieur exceptionelle doit être inferieur à la borne inférieur normale");
        if (maxNorm > maxEx) throw new ValidatorException( "La borne superieur exceptionelle doit être superieur à la borne superieur normale");
    }

    public String verificationCoordonnee(String coordGeo, String champ, boolean estLatitude) throws ValidatorException
    {
        Pattern r = (estLatitude) ? Pattern.compile(regexLatitude) : Pattern.compile(regexLongitude);
        Matcher m = r.matcher(coordGeo);

        if (coordGeo.isEmpty())
        {
            coordGeo = null;
        }
        else
        {
            if (!m.find()) throw new ValidatorException("La coordonee geographique doit respecter le format cc.ccl", champ);
        }
        return coordGeo;
    }

    public boolean compareDates(GregorianCalendar date1, String champ1, GregorianCalendar date2, String champ2) throws ComparaisonDatesException
    {
        if(date1.after(date2))
        { throw new ComparaisonDatesException(champ1, champ2); }
        else
        { return true; }
    }
}