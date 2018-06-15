package packageDataAccess;

import packageException.ConnectionException;
import packageModel.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface InformationMarquesVeloDataAccess
{
    public ArrayList<InformationMarquesVeloModel> rechercheInformationsMarques(String nomEntreprise, GregorianCalendar dateDebutGrego, GregorianCalendar dateFinGrego) throws ConnectionException;
    public ArrayList<EntrepriseModel> listeEntreprise() throws ConnectionException;
}
