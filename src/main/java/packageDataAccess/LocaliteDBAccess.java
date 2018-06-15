package packageDataAccess;

import packageException.ConnectionException;
import packageModel.LocaliteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocaliteDBAccess implements LocaliteDataAccess
{
    private Connection connection;

    public LocaliteDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<LocaliteModel> listeLocalite () throws ConnectionException
    {
        String sql ="SELECT * FROM localite";
        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList <LocaliteModel> arrayLocalite = new ArrayList<>() ;




            while (data.next())
            {
                LocaliteModel localite = new LocaliteModel();
                localite.setId(data.getInt("idLocalite"));
                localite.setLibelle(data.getString("libelle"));
                localite.setCodePostal(data.getInt("codePostal"));
                arrayLocalite.add(localite);
            }

            statement.close();
            data.close();
            return arrayLocalite;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

}
