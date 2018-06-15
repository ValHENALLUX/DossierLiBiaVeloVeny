package packageDataAccess;

import packageException.ConnectionException;
import packageModel.EntrepriseModel;
import packageModel.InformationMarquesVeloModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class InformationMarquesVeloDBAccess implements InformationMarquesVeloDataAccess
{
    private Connection connection;

    public InformationMarquesVeloDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<InformationMarquesVeloModel> rechercheInformationsMarques(String nomEntreprise, GregorianCalendar dateDebutGrego, GregorianCalendar dateFinGrego) throws ConnectionException {
        String sql = "SELECT DISTINCT l.libelle, s.nom, s.libelleRue, s.estCouverte, s.longitude,s.latitude, v.idVelo, v.dateAchat,h.dateArrivee " +
                "FROM velo v " +
                "LEFT OUTER JOIN entreprise e ON e.idEntreprise = v.id_entreprise " +
                "LEFT OUTER JOIN historique h ON h.id_Velo = v.idVelo " +
                "LEFT OUTER JOIN station s ON h.id_Station = s.idStation " +
                "LEFT OUTER JOIN localite l ON l.idLocalite = s.id_Localite " +
                "WHERE e.nom = ? AND (h.dateArrivee BETWEEN ? AND ? ) " +
                "ORDER BY v.idVelo";
        /* h.dateArrivee IN( SELECT MAX(dateArrivee) FROM historique GROUP BY id_Velo ) AND*/

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomEntreprise);

            //Conversion GregorianCalendar en Date
            Date dateDebut = new java.sql.Date(dateDebutGrego.getTimeInMillis());
            statement.setDate(2, dateDebut);

            Date dateFin = new Date(dateFinGrego.getTimeInMillis());
            statement.setDate(3, dateFin);

            ResultSet data = statement.executeQuery();

            ArrayList<InformationMarquesVeloModel> results = new ArrayList<InformationMarquesVeloModel>();
            String latitude;
            String longitude;
            GregorianCalendar dateAchat;
            GregorianCalendar dateArrivee;

            while (data.next()) {

                dateAchat = new GregorianCalendar();
                dateArrivee = new GregorianCalendar();

                InformationMarquesVeloModel informationMarquesVeloModel = new InformationMarquesVeloModel();
                informationMarquesVeloModel.setLocalite(data.getString("libelle"));
                informationMarquesVeloModel.setNomStation(data.getString("nom"));
                informationMarquesVeloModel.setRueStation(data.getString("libelleRue"));
                informationMarquesVeloModel.setCouverteStation(data.getBoolean("estCouverte"));
                informationMarquesVeloModel.setNumVelo(data.getString("idVelo"));

                //Conversion Date en GregorianCalendar
                dateAchat.setTime(data.getDate("dateAchat"));
                informationMarquesVeloModel.setDateAchatVelo(dateAchat);

                dateArrivee.setTime(data.getDate("dateArrivee"));
                informationMarquesVeloModel.setDateArriveeVelo(dateArrivee);

                //Gestion du facultatif
                longitude = data.getString("longitude");
                if (!data.wasNull())
                {
                    informationMarquesVeloModel.setLongitude(longitude);
                }

                latitude = data.getString("latitude");
                if (!data.wasNull())
                {
                    informationMarquesVeloModel.setLatitude(latitude);
                }
                results.add(informationMarquesVeloModel);
            }

            statement.close();
            data.close();

            return results;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public ArrayList<EntrepriseModel> listeEntreprise() throws ConnectionException
    {
        String sql ="SELECT * FROM entreprise";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList <EntrepriseModel> arrayEntreprise = new ArrayList<>() ;




            while (data.next())
            {
                EntrepriseModel entreprise = new EntrepriseModel();
                entreprise.setNom(data.getString("nom"));
                entreprise.setLieu(data.getString("lieu"));
                arrayEntreprise.add(entreprise);
            }

            statement.close();
            data.close();
            return arrayEntreprise;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

}
