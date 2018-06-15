package packageDataAccess;

import packageException.ConnectionException;
import packageModel.*;

import java.util.ArrayList;

public interface ReparationDataAccess
{
    public ArrayList<ReparationModel> rechercheReparation(String nomEntreprise, int identifiantAtelier) throws ConnectionException;
    public ArrayList<AtelierModel> listeAtelier () throws ConnectionException;
    public ArrayList<FicheReparationModel> listeFichesReparations(int statut) throws ConnectionException;
    public FicheReparationModel getFicheReparation(int idFiche) throws ConnectionException;
    public Boolean validationFicheReparation(FicheReparationModel fiche) throws ConnectionException;
    public Boolean ajoutOrdreTransport(OrdreTransportModel ordreTransport) throws ConnectionException;
}
