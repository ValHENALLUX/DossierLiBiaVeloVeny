package packageView;

import packageModel.*;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TableauFichesReparationsModel extends AbstractTableModel
{
    private ArrayList<FicheReparationModel> fiches = new ArrayList<>();
    private ArrayList<String> nomsColonnes = new ArrayList<>();

    public TableauFichesReparationsModel(ArrayList<FicheReparationModel> fiches)
    {
        //Nomination des colonnes
        this.fiches = fiches;
        nomsColonnes.add("Identifiant");
        nomsColonnes.add("Date début");
        nomsColonnes.add("Date fin");
        nomsColonnes.add("Remarque");
        nomsColonnes.add("Ordre de travail");
        nomsColonnes.add("Déclassé");
        //Vélo
        nomsColonnes.add("Vélo");
        nomsColonnes.add("Endommagé");
        nomsColonnes.add("Date d'achat");
        //Station
        nomsColonnes.add("Station");
        nomsColonnes.add("Couverte");
        nomsColonnes.add("Rue");
        //Localité
        nomsColonnes.add("Localité");
        //Atelier
        nomsColonnes.add("Atelier");
    }

    @Override
    public int getColumnCount()
    { return nomsColonnes.size(); }

    @Override
    public String getColumnName(int colonne)
    { return nomsColonnes.get(colonne); }

    @Override
    public int getRowCount()
    { return fiches.size(); }

    @Override
    public Object getValueAt(int ligne, int colonne)
    {
        FicheReparationModel fiche = fiches.get(ligne);
        //VeloModel velo = fiche.getVelo();
        //StationModel station = velo.getStation();
        //LocaliteModel localite = station.getLocalite();
        //AtelierModel atelier = fiche.getAtelier();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dateDebut = dateFormat.format(fiche.getDateDebut().getTime());
        String dateFin = dateFormat.format(fiche.getDateFin().getTime());
        String dateAchatVelo = dateFormat.format(fiche.getVelo().getDateAchat().getTime());

        switch (colonne)
        {
            case 0 : return fiche.getNumFiche();
            case 1 : return dateDebut;
            case 2 : return dateFin;
            case 3 : return fiche.getRemarque();
            case 4 : return fiche.getOrdreTravail();
            case 5 : return (fiche.isEstDeclasse())? "Oui" : "Non";
            case 6 : return fiche.getVelo().getNumVelo();
            case 7 : return (fiche.getVelo().isEstEndommage())?"Oui" : "Non";
            case 8 : return dateAchatVelo;
            case 9 : return fiche.getVelo().getStation().getNom();
            case 10 : return (fiche.getVelo().getStation().estCouverte())?"Oui" : "Non";
            case 11 : return fiche.getVelo().getStation().getLibelleRue();
            case 12 : return fiche.getVelo().getStation().getLocalite().toString();
            case 13 : return fiche.getAtelier().getLieu();
            default : return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int colonne)
    {
        Class c;
        switch (colonne)
        {
            case 0 : c = Integer.class;
                break;
            case 1 : c = String.class;
                break;
            case 2 : c = String.class;
                break;
            case 3 : c = String.class;
                break;
            case 4 : c = String.class;
                break;
            case 5 : c = String.class;
                break;
            case 6 : c = Integer.class;
                break;
            case 7 : c = String.class;
                break;
            case 8 : c = String.class;
                break;
            case 9 : c = String.class;
                break;
            case 10 : c = String.class;
                break;
            case 11 : c = String.class;
                break;
            case 12 : c = String.class;
                break;
            case 13 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }
}
