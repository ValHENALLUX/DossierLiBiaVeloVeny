package packageTools;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ValidatorTest
{
    private Validator validateur;

    @Before
    public void setUp() throws Exception
    {
        validateur = new Validator();
    }

    @Test
    public void verificationTexte() throws Exception
    {
        String texte = validateur.verificationTexte("bonjour","texte");
        assertEquals("bonjour", texte);
    }

    @Test
    public void verificationDate() throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = "05/01/2018";
        GregorianCalendar gregDate = new GregorianCalendar();
        gregDate = validateur.verificationDate(strDate, "Date");

        Date reponse = dateFormat.parse("05/01/2018");
        assertEquals(reponse, gregDate.getTime());
    }

    @Test
    public void verificationDateFin() throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = "05/08/2018";
        GregorianCalendar gregDate = new GregorianCalendar();
        gregDate = validateur.verificationDateFin(strDate, "Date");

        Date reponse = dateFormat.parse("05/08/2018");
        assertEquals(reponse, gregDate.getTime());
    }

    @Test
    public void verificationChiffreAvecTexte() throws Exception
    {
        String strChiffre = "5";
        Integer intChiffre = validateur.verificationChiffreAvecTexte(strChiffre, "Chiffre");

        assertEquals((Integer) 5, intChiffre);
    }

    @Test
    public void verificationChiffre() throws Exception
    {
        Integer chiffre = 9;
        Integer resultat = validateur.verificationChiffre(chiffre, "Chiffre");

        assertEquals((Integer) 9, resultat);
    }

    @Test
    public void compareDates() throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate1 = "05/01/2018";
        GregorianCalendar gregDate1 = new GregorianCalendar();
        gregDate1 = validateur.verificationDate(strDate1, "Date");

        String strDate2 = "06/01/2018";
        GregorianCalendar gregDate2 = new GregorianCalendar();
        gregDate2 = validateur.verificationDate(strDate2, "Date");

        Boolean reponse = validateur.compareDates(gregDate1, "date 1", gregDate2, "date 2");

        assertEquals(true, reponse);
    }

}