package packageDataAccess;

import packageModel.*;
import packageException.ConnectionException;
import java.util.ArrayList;

public interface StationDataAccess
{
    public ArrayList<StationModel> listeStation () throws ConnectionException;
    public boolean ajoutStation (StationModel station) throws ConnectionException;
    public boolean suppressionStation (int identifiant) throws ConnectionException;
    public boolean modifierStation (StationModel station) throws ConnectionException;
    public StationModel getStation(int idStation) throws ConnectionException;
}
