package packageDataAccess;

import packageException.ConnectionException;
import packageModel.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface LivraisonDataAccess
{
    public ArrayList<LivraisonModel> rechercheLivraison(String nomLivreur, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException;
    public ArrayList<EmployeModel> listeEmploye () throws ConnectionException;
}
