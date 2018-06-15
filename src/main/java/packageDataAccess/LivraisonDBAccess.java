package packageDataAccess;

import packageException.ConnectionException;
import packageModel.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LivraisonDBAccess implements LivraisonDataAccess
{
    private Connection connection;

    public LivraisonDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<LivraisonModel> rechercheLivraison(String nomLivreur, GregorianCalendar dateArriveGrego, GregorianCalendar dateDepartGrego) throws ConnectionException {
        String sql =
                "SELECT o.dateTransport, v.idVelo, s.nom,s.libelleRue,s.latitude,s.longitude,l.libelle,l.codePostal FROM station s JOIN ordre_transport o ON o.id_Station_emettrice = s.idStation JOIN historique h ON h.id_Station = s.idStation JOIN velo v ON v.idVelo = o.id_Velo JOIN localite l ON l.idLocalite = s.id_Localite JOIN employe e ON e.id_Station = s.idStation  WHERE e.nom = ?  AND (h.dateArrivee BETWEEN ? AND ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomLivreur);


            //Conversion GregorianCalendar en Date
            Date dateArrive = new java.sql.Date(dateArriveGrego.getTimeInMillis());
            statement.setDate(2, dateArrive);

            Date dateDepart = new Date(dateDepartGrego.getTimeInMillis());
            statement.setDate(3, dateDepart);

            ResultSet data = statement.executeQuery();

            ArrayList<LivraisonModel> resultat = new ArrayList<LivraisonModel>();
            String coordGeo;


            while (data.next()) {
                LivraisonModel livraisonModele = new LivraisonModel();
                livraisonModele.setLibelleLocalite(data.getString("libelle"));
                livraisonModele.setNomStation(data.getString("nom"));
                livraisonModele.setLibelleRueStation(data.getString("libelleRue"));
                livraisonModele.setNumVelo(data.getInt("numVelo"));


                //Gestion du facultatif
                coordGeo = data.getString("coordGeo"); //Condition du facultatif dans model?
                if (!data.wasNull())
                {
                    livraisonModele.setCoordGeoStation(coordGeo);
                }
                resultat.add(livraisonModele);
            }

            statement.close();
            data.close();
            return resultat;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public ArrayList<EmployeModel> listeEmploye () throws ConnectionException
    {
        String sql ="SELECT e.nom,e.prenom FROM employe e";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList <EmployeModel> arrayEmploye = new ArrayList<>() ;


            while (data.next())
            {
                EmployeModel employe = new EmployeModel();
                employe.setNom(data.getString("nom"));
                employe.setPrenom(data.getString("prenom"));

                arrayEmploye.add(employe);
            }

            statement.close();
            data.close();
            return arrayEmploye;
        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

}
