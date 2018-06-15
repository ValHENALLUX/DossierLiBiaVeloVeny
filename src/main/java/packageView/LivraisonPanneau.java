package packageView;

import packageException.ConnectionException;
import packageModel.LivraisonModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LivraisonPanneau
{
    private Container conteneur;
    private JPanel panneauTitre, panneauListing, panneauBoutons;
    private JButton retour;
    private JLabel labelTitre;
    private ArrayList<LivraisonModel> listeLivraisons = new ArrayList<>();
    private FenetrePrincipale fenetrePrincipale;

    public LivraisonPanneau(FenetrePrincipale f, ArrayList<LivraisonModel> listeLivraisons, String nomEmploye, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws ConnectionException
    {
        fenetrePrincipale = f;
        conteneur = f.getContentPane();
        conteneur.removeAll();

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

        //region listing
        this.listeLivraisons = listeLivraisons;
        TableauLivraisonModel modele = new TableauLivraisonModel(this.listeLivraisons);
        JTable tableListing = new JTable(modele);

        TableColumn numVelo = tableListing.getColumnModel().getColumn(0);
        numVelo.setPreferredWidth(100);
        TableColumn dateTransport = tableListing.getColumnModel().getColumn(1);
        dateTransport.setPreferredWidth(100);
        TableColumn stationDepart = tableListing.getColumnModel().getColumn(2);
        stationDepart.setPreferredWidth(100);
        TableColumn localite = tableListing.getColumnModel().getColumn(3);
        localite.setPreferredWidth(100);
        TableColumn rue = tableListing.getColumnModel().getColumn(4);
        rue.setPreferredWidth(100);
        TableColumn coordonnees = tableListing.getColumnModel().getColumn(5);
        coordonnees.setPreferredWidth(100);

        tableListing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
        retour = new JButton("Retour");
        BoutonRetour boutonRetour = new BoutonRetour();
        retour.addActionListener(boutonRetour);
        panneauBoutons.add(retour);
        conteneur.add(panneauBoutons, BorderLayout.SOUTH);
        //endregion

        f.getContentPane().repaint();
        f.setVisible(true);
    }

    private class BoutonRetour implements ActionListener,ListSelectionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                if (ae.getSource() == retour) {
                    RechercheVeloParLivreurPanneau v = new RechercheVeloParLivreurPanneau(fenetrePrincipale);
                }

            } catch (ConnectionException ex) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :" + ex.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            retour.setEnabled(true);
        }
    }

}
