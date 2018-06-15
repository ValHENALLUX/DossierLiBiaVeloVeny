package packageBusiness;

import packageDataAccess.*;
import packageException.ConnectionException;
import packageModel.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GestionVeloBusiness {

    private InformationMarquesVeloDataAccess informationMarquesVeloAccess;
    private ReparationDataAccess reparationAccess;
    private LivraisonDataAccess livraisonAccess;
    private VeloDataAccess veloAccess;

    public GestionVeloBusiness () throws ConnectionException {
        informationMarquesVeloAccess = new InformationMarquesVeloDBAccess();
        reparationAccess = new ReparationDBAccess();
        livraisonAccess = new LivraisonDBAccess();
        veloAccess = new VeloDBAccess();
    }

    public ArrayList<EntrepriseModel> listeEntreprise() throws ConnectionException {
        return informationMarquesVeloAccess.listeEntreprise();
    }

    public ArrayList<AtelierModel> listeAtelier() throws ConnectionException {
        return reparationAccess.listeAtelier();
    }

    public  ArrayList<EmployeModel> listeEmploye() throws ConnectionException{
        return livraisonAccess.listeEmploye();
    }

    public ArrayList<InformationMarquesVeloModel> rechercheInformationsMarques(String nomEntreprise, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException{
        return informationMarquesVeloAccess.rechercheInformationsMarques(nomEntreprise, dateArriveGrego, dateDepartGrego);
    }

    public ArrayList<LivraisonModel> rechercheLivraison(String nomLivreur, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException{
        return livraisonAccess.rechercheLivraison(nomLivreur, dateArriveGrego, dateDepartGrego);
    }

    public ArrayList<VeloModel> listeVelo() throws ConnectionException {
        return veloAccess.listeVelo();
    }
}
