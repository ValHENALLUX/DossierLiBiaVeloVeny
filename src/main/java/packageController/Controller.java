package packageController;

import packageBusiness.*;
import packageException.ComparaisonDatesException;
import packageException.ConnectionException;
import packageModel.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import packageBusiness.GestionLocaliteBusiness;
import packageException.ValidatorException;
import packageTools.Validator;


public class Controller {

    private GestionStationBusiness gestionStationBusiness;
    private GestionVeloBusiness gestionVeloBusiness;
    private GestionLocaliteBusiness gestionLocaliteBusiness;
    private GestionReparationsBusiness gestionReparationsBusiness;
    private Validator validator;

    public Controller() throws ConnectionException{

        gestionStationBusiness = new GestionStationBusiness();
        gestionLocaliteBusiness = new GestionLocaliteBusiness();
        gestionVeloBusiness = new GestionVeloBusiness();
        gestionReparationsBusiness = new GestionReparationsBusiness();
        validator = new Validator();
    }


    public ArrayList<StationModel> listeStation () throws ConnectionException {
        return gestionStationBusiness.listeStation();
    }

    public boolean ajoutStation (StationModel station) throws ConnectionException, ValidatorException{
        
        validator.verificationTexte(station.getNom(), "Nom de la station");
        int ucl = validator.verificationChiffre(station.getNbVeloMaxEx(), "Limite supérieur exceptionnelle");
        int uwl = validator.verificationChiffre(station.getNbVeloMaxNorm(), "Limite supérieur normale");
        int lcl = validator.verificationChiffre(station.getNbVeloMinEx(), "Limite inférieure exceptionnelle");
        int lwl = validator.verificationChiffre(station.getNbVeloMinNorm(), "Limite supérieur normale");
        validator.verificationBornes(lwl, lcl, uwl, ucl);
        validator.verificationTexte(station.getLibelleRue(), "Rue");
        validator.verificationTexte(station.getLocalite().getLibelle(), "Nom de la localite");
        
        return gestionStationBusiness.ajoutStation(station);
    }

    public boolean suppressionStation (int identifiantStation) throws ConnectionException, ValidatorException
    {
        validator.verificationChiffre(identifiantStation, "Identifiant");
        return gestionStationBusiness.suppressionStation(identifiantStation);
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
        
        return gestionStationBusiness.modifierStation(station);
    }

    public ArrayList<LocaliteModel> listeLocalite() throws ConnectionException{
        return gestionLocaliteBusiness.listeLocalite();
    }

    public ArrayList<EntrepriseModel> listeEntreprise() throws ConnectionException {
        return gestionVeloBusiness.listeEntreprise();
    }

    public ArrayList<AtelierModel> listeAtelier() throws ConnectionException {
        return gestionVeloBusiness.listeAtelier();
    }

    public  ArrayList<EmployeModel> listeEmploye() throws ConnectionException{
        return gestionVeloBusiness.listeEmploye();
    }

    public ArrayList<InformationMarquesVeloModel> rechercheInformationsMarques(String nomEntreprise, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException{
        return gestionVeloBusiness.rechercheInformationsMarques(nomEntreprise, dateArriveGrego, dateDepartGrego);
    }

    public ArrayList<LivraisonModel> rechercheLivraison(String nomLivreur, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException{
        return gestionVeloBusiness.rechercheLivraison(nomLivreur, dateArriveGrego, dateDepartGrego);
    }

    public StationModel getStation(int idStation) throws ValidatorException, ConnectionException {
        validator.verificationChiffre(idStation, "Identifiant");
        return gestionStationBusiness.getStation(idStation);
    }

    public ArrayList<FicheReparationModel> listeFichesReparations(int statut) throws ConnectionException {
        return gestionReparationsBusiness.listeFichesReparations(statut);
    }

    public FicheReparationModel getFicheReparation(int idFiche) throws ConnectionException {
        return gestionReparationsBusiness.getFicheReparation(idFiche);
    }

    public ArrayList<VeloModel> listeVelo() throws ConnectionException {
        return gestionVeloBusiness.listeVelo();
    }

    public boolean validationFicheReparation(FicheReparationModel fiche) throws ConnectionException, ValidatorException, ComparaisonDatesException
    {
        Boolean datesOk = false;
        Boolean estValide = false;
        FicheReparationModel ficheReparation = new FicheReparationModel();
        GregorianCalendar gregDateDebut = new GregorianCalendar();
        GregorianCalendar gregDateFin = new GregorianCalendar();
        String strDateDebut = null;
        String strDateFin = null;

        try {
            Integer numFiche = validator.verificationChiffre(fiche.getNumFiche(), "Numéro de la fiche");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            strDateDebut = dateFormat.format(fiche.getDateDebut().getTime());
            gregDateDebut = validator.verificationDate(strDateDebut, "Date de début");
            strDateFin = dateFormat.format(fiche.getDateDebut().getTime());
            gregDateFin = validator.verificationDateFin(strDateFin, "Date de fin");

            datesOk = validator.compareDates(gregDateDebut, "date de début", gregDateFin, "date de fin");

            String remarque = fiche.getRemarque();
            String ordreTravail = validator.verificationTexte(fiche.getOrdreTravail(), "Ordre de travail");
            Boolean estDeclasse = fiche.isEstDeclasse();

            VeloModel velo = fiche.getVelo();
            AtelierModel atelier = fiche.getAtelier();

            if(datesOk)
            {
                ficheReparation.setNumFiche(numFiche);
                ficheReparation.setDateDebut(gregDateDebut);
                ficheReparation.setDateFin(gregDateFin);
                ficheReparation.setRemarque(remarque);
                ficheReparation.setOrdreTravail(ordreTravail);
                ficheReparation.setEstDeclasse(estDeclasse);
                ficheReparation.setVelo(velo);
                ficheReparation.setAtelier(atelier);

                estValide = gestionReparationsBusiness.validationFicheReparation(ficheReparation);
            }
            return estValide;
        }
        catch(ConnectionException ce)
        {
            throw new ConnectionException(ce.toString());
        }
        catch(ValidatorException ve)
        {
            throw new ValidatorException(ve.toString());
        }
        catch(ComparaisonDatesException cde)
        {
            throw new ComparaisonDatesException(strDateDebut, strDateFin);
        }
    }

    public Boolean ajoutOrdreTransport(OrdreTransportModel ordreTransport) throws ConnectionException, ValidatorException
    {
        GregorianCalendar gregDateTransport = new GregorianCalendar();
        String strDateTransport = null;
        try
        {
            Boolean estValide = false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            strDateTransport = dateFormat.format(ordreTransport.getDateTransport().getTime());
            gregDateTransport = validator.verificationDateFin(strDateTransport, "Date de transport");

            if(ordreTransport.getStationOrigine() == null)
            {
                throw new ValidatorException("La station d'origine ne peut être nulle");
            }
            if(ordreTransport.getVelo() == null)
            {
                throw new ValidatorException("Un vélo doit être sélectionné");
            }

            ordreTransport.setDateTransport(gregDateTransport);

            estValide = gestionReparationsBusiness.ajoutOrdreTransport(ordreTransport);
            return estValide;

        }
        catch(ConnectionException ce)
        {
            throw new ConnectionException(ce.toString());
        }
        catch(ValidatorException ve)
        {
            throw new ValidatorException(ve.toString());
        }
    }
}
