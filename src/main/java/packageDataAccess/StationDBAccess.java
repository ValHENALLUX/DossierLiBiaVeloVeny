package packageDataAccess;

import packageException.ConnectionException;
import packageModel.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class StationDBAccess implements StationDataAccess
{
    private Connection connection;

    public StationDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<StationModel> listeStation () throws ConnectionException
    {
        String sql ="SELECT * FROM station s JOIN localite l ON s.id_Localite = l.idLocalite ORDER BY idStation";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList <StationModel> arrayStation = new ArrayList<>() ;
            GregorianCalendar dateCreationStation;
            StationModel station;
            LocaliteModel localite;

            while (data.next())
            {
                dateCreationStation = new GregorianCalendar();
                station = new StationModel();
                station.setIdStation(data.getInt("idStation"));
                station.setNom(data.getString("nom"));
                station.setNbVeloMinNorm(data.getInt("nbVeloMinNorm"));
                station.setNbVeloMinEx(data.getInt("nbVeloMinEx"));
                station.setNbVeloMaxNorm(data.getInt("nbVeloMaxNorm"));
                station.setNbVeloMaxEx(data.getInt("nbVeloMaxEx"));
                //Conversion Date en GregorianCalendar
                dateCreationStation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(dateCreationStation);

                station.setCouverte(data.getBoolean("estCouverte"));

                //Gestion du facultatif
                String longitude = data.getString("longitude");
                if (!data.wasNull())
                {
                    station.setLongitude(longitude);
                }

                String latitude = data.getString("latitude");
                if (!data.wasNull())
                {
                    station.setLatitude(latitude);
                }

                /*station.setLatitude(data.getString("latitude"));
                station.setLongitude(data.getString("longitude"));*/

                station.setLibelleRue(data.getString("libelleRue"));
                localite = new LocaliteModel();

                localite.setId(data.getInt("idLocalite"));
                localite.setLibelle(data.getString("libelle"));
                localite.setCodePostal(data.getInt("codePostal"));
                station.setLocalite(localite);

                arrayStation.add(station);
            }

            statement.close();
            data.close();

            return arrayStation;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public boolean ajoutStation (StationModel station) throws ConnectionException {
        String sql = "INSERT INTO station (nom, nbVeloMinNorm, nbVeloMinEx, nbVeloMaxNorm, nbVeloMaxEx, dateCreation, estCouverte, latitude, longitude , libelleRue, id_Localite) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            Date dateCreationStation;

            statement.setString(1,station.getNom() );
            statement.setInt(2,station.getNbVeloMinNorm() );
            statement.setInt(3,station.getNbVeloMinEx() );
            statement.setInt(4,station.getNbVeloMaxNorm() );
            statement.setInt(5,station.getNbVeloMaxEx() );

            //Conversion GregorianCalendar en Date
            dateCreationStation =new java.sql.Date(station.getDateCreation().getTimeInMillis());
            statement.setDate(6,dateCreationStation );

            statement.setBoolean(7,station.estCouverte() );

            // Gestion du facultatif
            if (station.getLatitude() != null){
                statement.setString(8,station.getLatitude());
            } else {
                statement.setNull(8,Types.VARCHAR);
            }
            
            if (station.getLongitude() != null){
                statement.setString(9,station.getLongitude());
            } else {
                statement.setNull(9,Types.VARCHAR);
            }

            statement.setString(10,station.getLibelleRue() );
            statement.setInt(11, station.getLocalite().getId() );

            statement.executeUpdate();
            statement.close();
            return true;
        }

        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public boolean suppressionStation (int identifiant) throws ConnectionException {
     String sql = "DELETE FROM station WHERE idStation=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, identifiant);
            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }

    }

    public boolean modifierStation (StationModel station) throws ConnectionException {
        String sql = "UPDATE station SET nom = ?, nbVeloMinNorm = ?, nbVeloMinEx = ?, nbVeloMaxNorm = ?, nbVeloMaxEx = ?, estCouverte = ?, latitude = ?, longitude = ?, libelleRue = ?, id_Localite = ? WHERE idStation = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,station.getNom() );
            statement.setInt(2,station.getNbVeloMinNorm() );
            statement.setInt(3,station.getNbVeloMinEx() );
            statement.setInt(4,station.getNbVeloMaxNorm() );
            statement.setInt(5,station.getNbVeloMaxEx() );
            statement.setBoolean(6,station.estCouverte() );
 
            if (station.getLatitude() != null){
                statement.setString(7,station.getLatitude());
            } else {
                statement.setNull(7,Types.VARCHAR);
            }
            
            if (station.getLongitude() != null){
                statement.setString(8,station.getLongitude());
            } else {
                statement.setNull(8,Types.VARCHAR);
            }

            statement.setString(9,station.getLibelleRue() );
            statement.setInt(10, station.getLocalite().getId() );

            statement.setInt(11,station.getIdStation());

            statement.executeUpdate();
            statement.close();
            return true;
        }

        catch (SQLException e) 
        {
            throw new ConnectionException(e.toString());
        }        
    }

    public StationModel getStation(int idStation) throws ConnectionException {
        
        String sql ="SELECT * FROM station s JOIN localite l ON s.id_Localite = l.idLocalite WHERE s.idStation = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idStation);
            ResultSet data = statement.executeQuery();
            LocaliteModel localite = new LocaliteModel();
            GregorianCalendar dateCreationStation = new GregorianCalendar();
            StationModel station = new StationModel();

            while (data.next())
            {
                station.setIdStation(data.getInt("idStation"));
                station.setNom(data.getString("nom"));
                station.setNbVeloMinNorm(data.getInt("nbVeloMinNorm"));
                station.setNbVeloMinEx(data.getInt("nbVeloMinEx"));
                station.setNbVeloMaxNorm(data.getInt("nbVeloMaxNorm"));
                station.setNbVeloMaxEx(data.getInt("nbVeloMaxEx"));
                //Conversion Date en GregorianCalendar
                dateCreationStation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(dateCreationStation);

                station.setCouverte(data.getBoolean("estCouverte"));
                station.setLatitude(data.getString("latitude"));
                station.setLongitude(data.getString("longitude"));

                station.setLibelleRue(data.getString("libelleRue"));

                localite.setId(data.getInt("idLocalite"));
                localite.setLibelle(data.getString("libelle"));
                localite.setCodePostal(data.getInt("codePostal"));
                station.setLocalite(localite);
            }

            statement.close();
            data.close();
            return station;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }
}
