package packageBusiness;

import packageDataAccess.StationDBAccess;
import packageDataAccess.StationDataAccess;
import packageException.ConnectionException;
import packageModel.StationModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import packageException.ValidatorException;
import packageTools.Validator;

public class GestionStationBusiness {

    private StationDataAccess stationAccess;
    private Validator validator;

    public GestionStationBusiness () throws ConnectionException 
    {

        stationAccess = new StationDBAccess();
        validator = new Validator();
    }

    public ArrayList<StationModel> listeStation () throws ConnectionException
    {
        return stationAccess.listeStation();
    }

    public boolean ajoutStation (StationModel station) throws ConnectionException, ValidatorException
    {    
        validator.verificationTexte(station.getNom(), "Nom de la station");
        int ucl = validator.verificationChiffre(station.getNbVeloMaxEx(), "Limite supérieur exceptionnelle");
        int uwl = validator.verificationChiffre(station.getNbVeloMaxNorm(), "Limite supérieur normale");
        int lcl = validator.verificationChiffre(station.getNbVeloMinEx(), "Limite inférieure exceptionnelle");
        int lwl = validator.verificationChiffre(station.getNbVeloMinNorm(), "Limite supérieur normale");
        validator.verificationBornes(lwl, lcl, uwl, ucl);
        validator.verificationTexte(station.getLibelleRue(), "Rue");
        validator.verificationTexte(station.getLocalite().getLibelle(), "Nom de la localite");
        
        GregorianCalendar dateCreation =(GregorianCalendar) GregorianCalendar.getInstance();
        station.setDateCreation(dateCreation);
        return stationAccess.ajoutStation(station);
    }

    public boolean suppressionStation (int identifiantStation) throws ConnectionException, ValidatorException
    {
        validator.verificationChiffre(identifiantStation, "Identifiant");
        return stationAccess.suppressionStation(identifiantStation);
    }

    public boolean modifierStation (StationModel station) throws ConnectionException, ValidatorException 
    { 
        validator.verificationChiffre(station.getIdStation(), "Identifiant");
        validator.verificationTexte(station.getNom(), "Nom de la station");
        int ucl = validator.verificationChiffre(station.getNbVeloMaxEx(), "Limite supérieur exceptionnelle");
        int uwl = validator.verificationChiffre(station.getNbVeloMaxNorm(), "Limite supérieur normale");
        int lcl = validator.verificationChiffre(station.getNbVeloMinEx(), "Limite inférieure exceptionnelle");
        int lwl = validator.verificationChiffre(station.getNbVeloMinNorm(), "Limite supérieur normale");
        validator.verificationBornes(lwl, lcl, uwl, ucl);
        validator.verificationTexte(station.getLibelleRue(), "Rue");
        validator.verificationTexte(station.getLocalite().getLibelle(), "Nom de la localite");
        
        return stationAccess.modifierStation(station);
    }

    public StationModel getStation(int idStation)  throws ConnectionException, ValidatorException 
    {
        validator.verificationChiffre(idStation, "Identifiant");
        return stationAccess.getStation(idStation);
    }
}
