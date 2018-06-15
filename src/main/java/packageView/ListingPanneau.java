package packageView;

import packageController.Controller;
import packageModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import packageException.ConnectionException;
import packageException.ValidatorException;

public class ListingPanneau extends JPanel
{
    private Container conteneur;
    private JPanel panneauTitre, panneauListing, panneauBoutons;
    private JButton modifier, supprimer;
    private JLabel labelTitre;
    private ArrayList <StationModel> listeStations = new ArrayList();
    private Controller controller;
    private JTable tableListing;
    private FenetrePrincipale fenetrePrincipale;

    public ListingPanneau(FenetrePrincipale f) throws ConnectionException
    {
        conteneur = f.getContentPane();
        fenetrePrincipale = f;
        conteneur.removeAll();
        controller = new Controller();
        GestionnaireTableau gt = new GestionnaireTableau();

        //region panneaux
        panneauTitre = new JPanel();
        panneauListing = new JPanel();
        panneauBoutons = new JPanel();
        //endregion

        //region layouts
        conteneur.setLayout(new BorderLayout());
        panneauTitre.setLayout(new FlowLayout());
        panneauListing.setLayout(new BorderLayout());
        panneauBoutons.setLayout(new FlowLayout());
        //endregion

        //region titre
        labelTitre = new JLabel("Listing des stations");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region contenu
        listeStations = controller.listeStation();

        TableauStation modele = new TableauStation(listeStations);
        tableListing = new JTable(modele);

        TableColumn identifiant = tableListing.getColumnModel().getColumn(0);
        identifiant.setPreferredWidth(50);
        TableColumn nom = tableListing.getColumnModel().getColumn(1);
        nom.setPreferredWidth(100);
        TableColumn couverte = tableListing.getColumnModel().getColumn(2);
        couverte.setPreferredWidth(80);
        TableColumn localite = tableListing.getColumnModel().getColumn(3);
        localite.setPreferredWidth(100);
        TableColumn rue = tableListing.getColumnModel().getColumn(4);
        rue.setPreferredWidth(215);
        TableColumn dateCreation = tableListing.getColumnModel().getColumn(5);
        dateCreation.setPreferredWidth(100);
        TableColumn longitude = tableListing.getColumnModel().getColumn(6);
        longitude.setPreferredWidth(70);
        TableColumn latitude = tableListing.getColumnModel().getColumn(7);
        latitude.setPreferredWidth(70);
        TableColumn lSup = tableListing.getColumnModel().getColumn(8);
        lSup.setPreferredWidth(130);
        TableColumn lInf = tableListing.getColumnModel().getColumn(9);
        lInf.setPreferredWidth(130);

        tableListing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableListing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableListing.getSelectionModel().addListSelectionListener(gt); 
        tableListing.setModel(modele);
        
        JScrollPane barre = new JScrollPane();
        barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        barre.setViewportView(tableListing);
        barre.setWheelScrollingEnabled(true);
        tableListing.getTableHeader().setReorderingAllowed(false);

        
        panneauListing.add(tableListing.getTableHeader(), BorderLayout.PAGE_START);
        panneauListing.add(barre);


        conteneur.add(panneauListing, BorderLayout.CENTER);
        //endregion

        //region boutons
        modifier = new JButton("Modifier");
        supprimer = new JButton("Supprimer");
        
        modifier.setEnabled(false);
        supprimer.setEnabled(false);
        
        modifier.addActionListener(gt);
        supprimer.addActionListener(gt);
        
        panneauBoutons.add(modifier);
        panneauBoutons.add(supprimer);
        conteneur.add(panneauBoutons, BorderLayout.SOUTH);
        //endregion

        f.getContentPane().repaint();
        f.setVisible(true);
    }

    private class GestionnaireTableau implements ActionListener, ListSelectionListener    
    {
        public void actionPerformed(ActionEvent ae) 
        {
            try 
            {
                if (ae.getSource()== modifier)
                {
                    String idStation = tableListing.getValueAt(tableListing.getSelectedRow(),0).toString();
                    StationModel station = controller.getStation(Integer.parseInt(idStation));
                    GestionPanneau gp = new GestionPanneau(fenetrePrincipale, station );
                }
                if (ae.getSource()== supprimer)
                {
                    String idStation = tableListing.getValueAt(tableListing.getSelectedRow(),0).toString();
                    boolean estValide = controller.suppressionStation(Integer.parseInt(idStation));
                    if (estValide) JOptionPane.showMessageDialog(null, "Station supprimée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    ListingPanneau lp = new ListingPanneau(fenetrePrincipale);
                } 
            }
            catch (ConnectionException ex) 
            {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :"+ex.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
            } 
            catch (ValidatorException ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur de validation :"+ex.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
            } 
        }

        public void valueChanged(ListSelectionEvent e) {
            modifier.setEnabled(true);
            supprimer.setEnabled(true);
        }
    }
}
