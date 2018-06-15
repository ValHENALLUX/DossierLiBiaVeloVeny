package packageBusiness;

import java.util.ArrayList;
import packageDataAccess.LocaliteDBAccess;
import packageDataAccess.LocaliteDataAccess;
import packageException.ConnectionException;
import packageModel.LocaliteModel;

public class GestionLocaliteBusiness {
    private LocaliteDataAccess localiteAccess;
    
    public GestionLocaliteBusiness() throws ConnectionException 
    {
        localiteAccess = new LocaliteDBAccess();
    }
    
    public ArrayList<LocaliteModel> listeLocalite() throws ConnectionException{
        return localiteAccess.listeLocalite();
    }
}
