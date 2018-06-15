package packageDataAccess;
import packageException.ConnectionException;
import packageModel.*;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReparationDBAccess implements ReparationDataAccess
{
    private Connection connection;

    public ReparationDBAccess() throws ConnectionException
    {
        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<ReparationModel> rechercheReparation(String nomEntreprise, int identifiantAtelier) throws ConnectionException {
        String sql = "SELECT f.ordreTravail, f.estDeclasse, v.numVelo, v.dateAchat, v.estEndommage, s.nom, s.estCouverte\n" +
                "FROM velo v JOIN entreprise e ON e.nom = v.nom_Entreprise\n" +
                "JOIN fiche_reparation f ON f.numVelo_Velo = v.numVelo\n" +
                "JOIN station s ON v.identifiant_Station = s.identifiant\n" +
                "JOIN atelier a ON a.identifiant = f.identifiant_Atelier\n" +
                "WHERE a.identifiant = ? AND e.nom = ? " ;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,identifiantAtelier);
            statement.setString(2, nomEntreprise);



            ResultSet data = statement.executeQuery();

            ArrayList<ReparationModel> resultat = new ArrayList<ReparationModel>();
            GregorianCalendar dateAchat = new GregorianCalendar();

            while (data.next()) {
                ReparationModel reparation = new ReparationModel();
                reparation.setOrdreTravailFiche(data.getString("ordreTravail"));
                reparation.setNumVelo(Integer.parseInt(data.getString("numVelo")));
                reparation.setEstDeclasseFiche(data.getBoolean("estDeclasse"));
                reparation.setNomStation(data.getString("nom"));
                reparation.setEstCouverte(data.getBoolean("estCouverte"));
                reparation.setEstEndommageFiche(data.getBoolean("estEndommage"));

                //Conversion Date en GregorianCalendar
                dateAchat.setTime(data.getDate("dateAchat"));
                reparation.setDateAchat(dateAchat);

                resultat.add(reparation);
            }

            statement.close();
            data.close();
            return resultat;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public ArrayList<FicheReparationModel> listeFichesReparations(int statut) throws ConnectionException
    {
        String sql ="SELECT * FROM fiche_reparation f\n" +
                    "LEFT OUTER JOIN velo v ON f.id_Velo = v.idVelo\n" +
                    "LEFT OUTER JOIN station s ON v.id_Station = s.idStation\n" +
                    "LEFT OUTER JOIN localite l ON l.idLocalite = s.id_Localite\n" +
                    "LEFT OUTER JOIN atelier a ON f.id_Atelier = a.idAtelier\n" +
                    "LEFT OUTER JOIN entreprise e ON v.id_Entreprise = e.idEntreprise\n" +
                    "WHERE f.valide = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, statut);
            ResultSet data = statement.executeQuery();

            ArrayList<FicheReparationModel> arrayReparation = new ArrayList<>();
            GregorianCalendar gregDateDebut;
            GregorianCalendar gregDateFin;
            GregorianCalendar gregDateAchat;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            GregorianCalendar gregDateCreationStation;
            FicheReparationModel fiche;
            VeloModel velo;
            StationModel station;
            LocaliteModel localite;
            EntrepriseModel entreprise;
            AtelierModel atelier;


            while(data.next())
            {
                gregDateDebut = new GregorianCalendar();
                gregDateFin = new GregorianCalendar();
                gregDateAchat = new GregorianCalendar();
                gregDateCreationStation = new GregorianCalendar();

                fiche = new FicheReparationModel();
                fiche.setNumFiche(data.getInt("idFiche"));
                //Conversion Dates en GregorianCalendar
                gregDateDebut.setTime(data.getDate("dateDebut"));
                fiche.setDateDebut(gregDateDebut);
                gregDateFin.setTime(data.getDate("dateFin"));
                fiche.setDateFin(gregDateFin);
                fiche.setRemarque(data.getString("remarque"));
                fiche.setOrdreTravail(data.getString("ordreTravail"));
                fiche.setEstDeclasse(data.getBoolean("estDeclasse"));

                //region Velo
                    velo = new VeloModel();
                    velo.setNumVelo(data.getInt("idvelo"));
                    velo.setEstEndommage(data.getBoolean("estEndommage"));
                    //Conversion Date en GregorianCalendar
                    gregDateAchat.setTime(data.getDate("dateAchat"));
                    velo.setDateAchat(gregDateAchat);

                    //region station
                        station = new StationModel();
                        station.setIdStation(data.getInt("idStation"));
                        station.setNom(data.getString("nom"));
                        station.setNbVeloMinNorm(data.getInt("nbVeloMinNorm"));
                        station.setNbVeloMinEx(data.getInt("nbVeloMinEx"));
                        station.setNbVeloMaxNorm(data.getInt("nbVeloMaxNorm"));
                        station.setNbVeloMaxEx(data.getInt("nbVeloMinEx"));
                        //Conversion Date en GregorainCalendar
                        gregDateCreationStation.setTime(data.getDate("dateCreation"));
                        station.setDateCreation(gregDateCreationStation);
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

                        //region localite
                            localite = new LocaliteModel();
                            localite.setId(data.getInt("idlocalite"));
                            localite.setLibelle(data.getString("libelle"));
                            localite.setCodePostal(data.getInt("codePostal"));
                            station.setLocalite(localite);
                        //endregion

                        velo.setStation(station);

                    //endregion

                    //region entreprise
                        entreprise = new EntrepriseModel();
                        entreprise.setIdEntreprise(data.getInt("id_Entreprise"));
                        entreprise.setNom(data.getString("nom"));
                        entreprise.setLieu(data.getString("lieu"));
                        velo.setEntreprise(entreprise);
                    //endregion

                    fiche.setVelo(velo);

                //endregion

                //region atelier
                atelier = new AtelierModel();
                atelier.setIdentifiant(data.getInt("idAtelier"));
                atelier.setLieu(data.getString("lieu"));
                fiche.setAtelier(atelier);
                //endregion

                fiche.setValide(data.getBoolean("valide"));

                arrayReparation.add(fiche);
            }

            statement.close();
            data.close();
            return arrayReparation;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public FicheReparationModel getFicheReparation(int idFiche) throws ConnectionException
    {
        String sql ="SELECT * FROM fiche_reparation f\n" +
                    "LEFT OUTER JOIN velo v ON f.id_Velo = v.idVelo\n" +
                    "LEFT OUTER JOIN station s ON v.id_Station = s.idStation\n" +
                    "LEFT OUTER JOIN localite l ON l.idLocalite = s.id_Localite\n" +
                    "LEFT OUTER JOIN atelier a ON f.id_Atelier = a.idAtelier\n" +
                    "LEFT OUTER JOIN entreprise e ON v.id_Entreprise = e.idEntreprise\n" +
                    "WHERE f.idFiche = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idFiche);
            ResultSet data = statement.executeQuery();

            FicheReparationModel fiche = new FicheReparationModel();
            GregorianCalendar gregDateDebut;
            GregorianCalendar gregDateFin;
            GregorianCalendar gregDateAchat;
            GregorianCalendar gregDateCreationStation;
            VeloModel velo;
            StationModel station;
            LocaliteModel localite;
            EntrepriseModel entreprise;
            AtelierModel atelier;

            while(data.next())
            {
                gregDateDebut = new GregorianCalendar();
                gregDateFin = new GregorianCalendar();
                gregDateAchat = new GregorianCalendar();
                gregDateCreationStation = new GregorianCalendar();

                fiche.setNumFiche(data.getInt("idFiche"));
                //Conversion Dates en GregorianCalendar
                gregDateDebut.setTime(data.getDate("dateDebut"));
                fiche.setDateDebut(gregDateDebut);
                gregDateFin.setTime(data.getDate("dateFin"));
                fiche.setDateFin(gregDateFin);
                fiche.setRemarque(data.getString("remarque"));
                fiche.setOrdreTravail(data.getString("ordreTravail"));
                fiche.setEstDeclasse(data.getBoolean("estDeclasse"));

                //region Velo
                velo = new VeloModel();
                velo.setNumVelo(data.getInt("idvelo"));
                velo.setEstEndommage(data.getBoolean("estEndommage"));
                //Conversion Date en GregorianCalendar
                gregDateAchat.setTime(data.getDate("dateAchat"));
                velo.setDateAchat(gregDateAchat);

                //region station
                station = new StationModel();
                station.setIdStation(data.getInt("idStation"));
                station.setNom(data.getString("nom"));
                station.setNbVeloMinNorm(data.getInt("nbVeloMinNorm"));
                station.setNbVeloMinEx(data.getInt("nbVeloMinEx"));
                station.setNbVeloMaxNorm(data.getInt("nbVeloMaxNorm"));
                station.setNbVeloMaxEx(data.getInt("nbVeloMinEx"));
                //Conversion Date en GregorainCalendar
                gregDateCreationStation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(gregDateCreationStation);
                station.setCouverte(data.getBoolean("estCouverte"));
                station.setLatitude(data.getString("latitude"));
                station.setLongitude(data.getString("longitude"));
                station.setLibelleRue(data.getString("libelleRue"));

                //region localite
                localite = new LocaliteModel();
                localite.setId(data.getInt("idlocalite"));
                localite.setLibelle(data.getString("libelle"));
                localite.setCodePostal(data.getInt("codePostal"));
                station.setLocalite(localite);
                //endregion

                velo.setStation(station);

                //endregion

                //region entreprise
                entreprise = new EntrepriseModel();
                entreprise.setIdEntreprise(data.getInt("id_Entreprise"));
                entreprise.setNom(data.getString("nom"));
                entreprise.setLieu(data.getString("lieu"));
                velo.setEntreprise(entreprise);
                //endregion

                fiche.setVelo(velo);

                //endregion

                //region atelier
                atelier = new AtelierModel();
                atelier.setIdentifiant(data.getInt("idAtelier"));
                atelier.setLieu(data.getString("lieu"));
                fiche.setAtelier(atelier);
                //endregion

                fiche.setValide(data.getBoolean("valide"));
            }
            statement.close();
            data.close();
            return fiche;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public ArrayList<AtelierModel> listeAtelier() throws ConnectionException
    {
        String sql = "SELECT * FROM atelier";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<AtelierModel> arrayAtelier = new ArrayList<>();
            AtelierModel atelier;

            while (data.next())
            {
                atelier = new AtelierModel();
                atelier.setIdentifiant(data.getInt("idAtelier"));
                atelier.setLieu(data.getString("lieu"));

                arrayAtelier.add(atelier);
            }
            statement.close();
            data.close();

            return arrayAtelier;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public Boolean validationFicheReparation(FicheReparationModel fiche) throws ConnectionException
    {
        String sql = "UPDATE fiche_reparation SET dateFin = ?, remarque = ?, ordreTravail = ?, estDeclasse = ?, id_Atelier = ?, valide = ? where idFiche = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            Date dateFin = new java.sql.Date(fiche.getDateFin().getTimeInMillis());

            statement.setDate(1, dateFin);
            statement.setString(2, fiche.getRemarque());
            statement.setString(3, fiche.getOrdreTravail());
            statement.setBoolean(4, fiche.isEstDeclasse());
            statement.setInt(5, fiche.getAtelier().getIdentifiant());
            statement.setBoolean(6, true);
            statement.setInt(7, fiche.getNumFiche());

            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

    public Boolean ajoutOrdreTransport(OrdreTransportModel ordreTransport) throws ConnectionException
    {
        String sql = "INSERT INTO ordre_transport (dateTransport, id_velo, id_employe, id_Station_emettrice, id_Station_origine, id_Station_destination)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            Date dateTransport;

            //Conversion Gregoriancalendar en Date
            dateTransport = new java.sql.Date(ordreTransport.getDateTransport().getTimeInMillis());
            statement.setDate(1, dateTransport);

            statement.setInt(2, ordreTransport.getVelo().getNumVelo());
            if(ordreTransport.getEmploye() != null)
            {
                statement.setInt(3, ordreTransport.getEmploye().getIdentifiant());
            }
            else
            {
                statement.setNull(3, Types.INTEGER);
            }
            if (ordreTransport.getStationEmettrice() != null)
            {
                statement.setInt(4, ordreTransport.getStationEmettrice().getIdStation());
            }
            else
            {
                statement.setNull(4, Types.INTEGER);
            }
            statement.setInt(5, ordreTransport.getStationOrigine().getIdStation());
            if(ordreTransport.getStationDestination() != null)
            {
                statement.setInt(6, ordreTransport.getStationDestination().getIdStation());
            }
            else
            {
                statement.setNull(6, Types.INTEGER);
            }

            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }

}
