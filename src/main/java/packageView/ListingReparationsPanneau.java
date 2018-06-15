package packageView;

import packageController.Controller;
import packageException.ConnectionException;
import packageModel.FicheReparationModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingReparationsPanneau extends JPanel
{
    private Container conteneur;
    private JPanel panneauTitre, panneauListing, panneauBoutons;
    private JButton modifier;
    private JLabel labelTitre;
    private ArrayList <FicheReparationModel> listeReparations = new ArrayList<>();
    private Controller controller;
    private JTable tableReparations;
    private FenetrePrincipale fenetrePrincipale;
    private GestionnaireTableau gt;

    public ListingReparationsPanneau(FenetrePrincipale f, Integer statut) throws ConnectionException
    {
        conteneur = f.getContentPane();
        fenetrePrincipale = f;
        conteneur.removeAll();
        controller = new Controller();
        gt = new GestionnaireTableau();

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
        labelTitre = new JLabel("Listing des Fiches de réparations non validées");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region contenu
        listeReparations = controller.listeFichesReparations(statut);

        TableauFichesReparationsModel modele = new TableauFichesReparationsModel(listeReparations);
        tableReparations = new JTable(modele);

        tableReparations.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableReparations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableReparations.getSelectionModel().addListSelectionListener(gt);

        tableReparations.setModel(modele);

        JScrollPane barre = new JScrollPane();
        barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        barre.setViewportView(tableReparations);
        barre.setWheelScrollingEnabled(true);
        tableReparations.getTableHeader().setReorderingAllowed(false);

        panneauListing.add(tableReparations.getTableHeader(), BorderLayout.PAGE_START);
        panneauListing.add(barre);
        conteneur.add(panneauListing, BorderLayout.CENTER);
        //endregion

        if(statut == 0)
        {
            //region boutons
            modifier = new JButton("Modifier");
            modifier.setEnabled(false);
            modifier.addActionListener(gt);

            panneauBoutons.add(modifier);
            conteneur.add(panneauBoutons, BorderLayout.SOUTH);
            //endregion
        }
        f.getContentPane().repaint();
        f.setVisible(true);
    }

    private class GestionnaireTableau implements ActionListener, ListSelectionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try
            {
                if(ae.getSource() == modifier)
                {
                    String idFiche = tableReparations.getValueAt(tableReparations.getSelectedRow(),0).toString();
                    FicheReparationModel fiche = controller.getFicheReparation(Integer.parseInt(idFiche));
                    GestionFicheReparationPanneau gfp = new GestionFicheReparationPanneau(fenetrePrincipale, fiche);
                }
            }
            catch(ConnectionException ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :"+ex.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }

        public void valueChanged(ListSelectionEvent e)
        {
            modifier.setEnabled(true);
        }
    }
}
