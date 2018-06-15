package packageDataAccess;

import packageException.*;
import packageModel.*;
import java.util.ArrayList;

public interface VeloDataAccess
{
    public ArrayList<VeloModel> rechercheVelo(int numeroVelo) throws ConnectionException, CoordGeoException, BornesException;
    public ArrayList<VeloModel> listeVelo() throws ConnectionException;
}
