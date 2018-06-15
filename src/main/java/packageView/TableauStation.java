package packageView;


import packageModel.StationModel;

import java.text.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;


public class TableauStation extends AbstractTableModel
{
    private ArrayList<StationModel> stations = new ArrayList<>();
    private ArrayList<String> nomsColonne = new ArrayList<>();
    
    public TableauStation(ArrayList<StationModel> stations)
    {
        //Nomination des colonnes
        this.stations = stations;
        nomsColonne.add("ID");
        nomsColonne.add("Nom");
        nomsColonne.add("Couverte");
        nomsColonne.add("Localite");
        nomsColonne.add("Rue");
        nomsColonne.add("DateCreation");
        nomsColonne.add("Longitude");    
        nomsColonne.add("Latitude");        
        nomsColonne.add("Limites supérieures");        
        nomsColonne.add("Limites inférieures");        

    }
    
    //Retourne le nombre de colonnne
    public int getColumnCount() 
    {
         return nomsColonne.size();                                 
    }
    
    //Retourne le nom de la colonne
    public String getColumnName (int colonne)
    {
        return nomsColonne.get(colonne);                            
    }
    
    public Object getValueAt(int ligne, int col) 
    {
        StationModel station = stations.get(ligne);  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateCreation = dateFormat.format(station.getDateCreation().getTime());

        switch (col)
        {
            //En fonction de la colonne selectionnée retourne la valeur
            case 0: return station.getIdStation();                    
            case 1: return station.getNom();
            case 2: return (station.estCouverte()) ? "Oui": "Non";
            case 3: return station.getLocalite();
            case 4: return station.getLibelleRue();
            case 5: return dateCreation;
            case 6: return station.getLongitude();
            case 7: return station.getLatitude();
            case 8: return station.getNbVeloMaxEx() + " - " + station.getNbVeloMaxNorm();
            case 9: return station.getNbVeloMinEx() + " - " + station.getNbVeloMinNorm();
            default : return null;
        }
    }
    //Retourne la classe de la colonne selectionnée
    public Class getColumnClass(int col)
    {
        Class c;                                                    
        switch(col)
        {
            case 0: c = Integer.class;
                break;
            case 1: c = String.class;
                break;
            case 2: c = String.class;
                break;
            case 3: c = String.class;
                break;
            case 4: c = String.class;
                break;
            case 5: c = String.class;
                break;
            case 6: c = String.class;
                break;
            case 7: c = String.class;
                break;
            case 8: c = String.class;
                break;
            case 9: c = String.class;
                break;
            default: c= String.class;
        }
        return c;
    }
     //Retourne le nombre de ligne
    public int getRowCount() 
    {
       return stations.size();                         
    }
}
