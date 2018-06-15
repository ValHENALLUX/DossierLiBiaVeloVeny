package packageView;

import packageModel.LivraisonModel;
import packageView.FenetrePrincipale;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TableauLivraisonModel extends AbstractTableModel
{
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<LivraisonModel> contenuLivraisons = new ArrayList<>();

    public TableauLivraisonModel(ArrayList<LivraisonModel> contenuLivraisons)
    {

        this.contenuLivraisons = contenuLivraisons;
        nomColonnes.add("Vélo n°");
        nomColonnes.add("Date de transport");
        nomColonnes.add("Station de départ");
        nomColonnes.add("Localite");
        nomColonnes.add("Rue");
        nomColonnes.add("Coordonnées");
    }

    @Override
    public int getColumnCount()
    { return nomColonnes.size(); }
    @Override
    public int getRowCount()
    { return contenuLivraisons.size(); }
    @Override
    public String getColumnName(int column)
    { return nomColonnes.get(column); }

    @Override
    public Object getValueAt(int ligne, int colonne)
    {
        LivraisonModel livraison = contenuLivraisons.get(ligne);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateTransport = dateFormat.format(livraison.getDateTransport().getTime());
        switch (colonne)
        {
            case 0 : return livraison.getNumVelo();
            case 1 : return dateTransport;
            case 2 : return livraison.getNomStation();
            case 3 : return livraison.getLibelleLocalite();
            case 4 : return livraison.getLibelleRueStation();
            case 5 : return livraison.getCoordGeoStation();
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
