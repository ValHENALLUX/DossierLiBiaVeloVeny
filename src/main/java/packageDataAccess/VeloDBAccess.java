package packageDataAccess;

import packageException.BornesException;
import packageException.ConnectionException;
import packageException.CoordGeoException;
import packageModel.EntrepriseModel;
import packageModel.InformationMarquesVeloModel;
import packageModel.StationModel;
import packageModel.VeloModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.sql.Date;

public class VeloDBAccess implements VeloDataAccess
{

    private Connection connection;

    public VeloDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList <VeloModel> rechercheVelo(int numeroVelo) throws ConnectionException, CoordGeoException, BornesException
    {
        String sql =
                "SELECT *  FROM velo v JOIN entreprise e ON e.idEntreprise = v.id_Entreprise JOIN station s ON v.id_Station = s.idStation WHERE idVelo = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,numeroVelo);
            ResultSet data = statement.executeQuery();
            StationModel station = new StationModel();
            ArrayList <VeloModel> arrayVelo = new ArrayList<>() ;
            GregorianCalendar dateAchat = new GregorianCalendar();
            GregorianCalendar dateCreationStation = new GregorianCalendar();

            while (data.next())
            {
                VeloModel velo = new VeloModel();
                velo.setNumVelo(data.getInt("numVelo"));
                velo.setEstEndommage(data.getBoolean("estEndommage"));

                //Conversion Date en GregorianCalendar
                dateAchat.setTime(data.getDate("dateAchat"));
                velo.setDateAchat(dateAchat);

                // Gestion foreign key station
                station.setIdStation(data.getInt("identifiant"));
                station.setNom(data.getString("nom"));
                station.setNbVeloMinEx(data.getInt("nbVeloMinEx"));
                station.setNbVeloMaxNorm(data.getInt("nbVeloMaxNorm"));
                station.setNbVeloMaxEx(data.getInt("nbVeloMaxEx"));
                //Conversion Date en GregorianCalendar
                dateCreationStation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(dateCreationStation);

                station.setCouverte(data.getBoolean("estCouverte"));
                station.setLibelleRue(data.getString("libelleRue"));

                station.setLatitude(data.getString("latitude"));
                station.setLongitude(data.getString("longitude"));

                velo.setStation(station);
                arrayVelo.add(velo);
            }

            statement.close();
            data.close();
            return arrayVelo;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public ArrayList<VeloModel> listeVelo() throws ConnectionException
    {
        String sql = "SELECT *  FROM velo";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList <VeloModel> arrayVelo = new ArrayList<>() ;
            GregorianCalendar gregDateAchat = new GregorianCalendar();
            VeloModel velo;
            StationModel station = new StationModel();
            EntrepriseModel entreprise = new EntrepriseModel();

            while (data.next())
            {
                velo = new VeloModel();
                velo.setNumVelo(data.getInt("idVelo"));
                velo.setEstEndommage(data.getBoolean("estEndommage"));
                //Conversion date en GregorianCalendar
                gregDateAchat.setTime(data.getDate("dateAchat"));
                velo.setDateAchat(gregDateAchat);

                station.setIdStation(data.getInt("id_Station"));
                velo.setStation(station);

                entreprise.setIdEntreprise(data.getInt("id_Entreprise"));
                velo.setEntreprise(entreprise);

                arrayVelo.add(velo);
            }

            statement.close();
            data.close();
            return arrayVelo;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

}
