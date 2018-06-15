package packageBusiness;

import packageDataAccess.ReparationDBAccess;
import packageDataAccess.ReparationDataAccess;
import packageException.ComparaisonDatesException;
import packageException.ConnectionException;
import packageException.ValidatorException;
import packageModel.AtelierModel;
import packageModel.FicheReparationModel;
import packageModel.OrdreTransportModel;
import packageModel.VeloModel;
import packageTools.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GestionReparationsBusiness
{
    ReparationDataAccess reparationAccess;
    Validator validator;
    public GestionReparationsBusiness () throws ConnectionException
    {
        reparationAccess = new ReparationDBAccess();
        validator = new Validator();
    }

    public ArrayList<FicheReparationModel> listeFichesReparations(int statut) throws ConnectionException
    {
        return reparationAccess.listeFichesReparations(statut);
    }

    public FicheReparationModel getFicheReparation(int idFiche) throws ConnectionException
    {
        return reparationAccess.getFicheReparation(idFiche);
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

                estValide = reparationAccess.validationFicheReparation(ficheReparation);
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

            estValide = reparationAccess.ajoutOrdreTransport(ordreTransport);
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
