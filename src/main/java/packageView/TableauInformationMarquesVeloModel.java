package packageView;

import packageModel.InformationMarquesVeloModel;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TableauInformationMarquesVeloModel extends AbstractTableModel
{
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList <InformationMarquesVeloModel> contenuVelo = new ArrayList<>();

    public TableauInformationMarquesVeloModel(ArrayList<InformationMarquesVeloModel> contenuVelo)
    {
        this.contenuVelo = contenuVelo;
        nomColonnes.add("Station");
        nomColonnes.add("Localité");
        nomColonnes.add("Rue");
        nomColonnes.add("Coordonnées");
        nomColonnes.add("Couverte");
        nomColonnes.add("Vélo n°");
        nomColonnes.add("Date d'achat");
        nomColonnes.add("Date d'arrivée");
    }

    @Override
    public int getColumnCount()
    { return nomColonnes.size(); }
    @Override
    public int getRowCount()
    { return contenuVelo.size(); }
    @Override
    public String getColumnName(int column)
    { return nomColonnes.get(column); }

    @Override
    public Object getValueAt(int ligne, int colonne)
    {
        InformationMarquesVeloModel velo = contenuVelo.get(ligne);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateAchat = dateFormat.format(velo.getDateAchatVelo().getTime());
        String dateArrivee = dateFormat.format(velo.getDateArriveeVelo().getTime());
        switch (colonne)
        {
            case 0 : return velo.getNomStation();
            case 1 : return velo.getLocalite();
            case 2 : return velo.getRueStation();
            case 3 : return velo.getLongitude() + " - " +velo.getLatitude();
            case 4: return (velo.getCouverteStation()) ? "Oui": "Non";
            case 5 : return velo.getNumVelo();
            case 6 : return dateAchat;
            case 7 : return dateArrivee;
            default : return null;
        }
    }

    @Override
    public Class getColumnClass(int col)
    {
        Class c;
        switch(col)
        {
            case 0 : c = String.class;
                break;
            case 1 : c = String.class;
                break;
            case 2 : c = String.class;
                break;
            case 3 : c = String.class;
                break;
            case 4 : c = String.class;
                break;
            case 5 : c = Integer.class;
                break;
            case 6 : c = String.class;
                break;
            case 7 : c = String.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
