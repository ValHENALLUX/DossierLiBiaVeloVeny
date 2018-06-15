package packageDataAccess;

import packageException.ConnectionException;
import packageModel.LocaliteModel;
import java.util.ArrayList;

public interface LocaliteDataAccess
{
    public ArrayList<LocaliteModel> listeLocalite () throws ConnectionException;
}
