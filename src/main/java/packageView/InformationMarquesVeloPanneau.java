package packageView;

import packageController.Controller;
import packageException.ConnectionException;
import packageModel.InformationMarquesVeloModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class InformationMarquesVeloPanneau extends JPanel
{
    private Container conteneur;
    private JPanel panneauTitre, panneauListing, panneauBoutons;
    private JButton retour;
    private JLabel labelTitre;
    private ArrayList<InformationMarquesVeloModel> listeStations = new ArrayList<>();
    private FenetrePrincipale fenetrePrincipale;
    private Controller controller;
    private JTable tableListing;

    public InformationMarquesVeloPanneau(FenetrePrincipale f, ArrayList<InformationMarquesVeloModel> listeStations, String nomEntreprise, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws ConnectionException
    {
        fenetrePrincipale = f;
        conteneur = f.getContentPane();
        conteneur.removeAll();
        controller = new Controller();
        BoutonRetour br = new BoutonRetour();


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
        //listeStations = controller.rechercheInformationsMarques(nomEntreprise,dateDebut,dateFin);
        TableauInformationMarquesVeloModel modele = new TableauInformationMarquesVeloModel(listeStations);
        tableListing = new JTable(modele);

        TableColumn nomStation = tableListing.getColumnModel().getColumn(0);
        nomStation.setPreferredWidth(100);
        TableColumn localite = tableListing.getColumnModel().getColumn(1);
        localite.setPreferredWidth(100);
        TableColumn rue = tableListing.getColumnModel().getColumn(2);
        rue.setPreferredWidth(100);
        TableColumn longitude = tableListing.getColumnModel().getColumn(3);
        longitude.setPreferredWidth(100);
        TableColumn latitude = tableListing.getColumnModel().getColumn(3);
        latitude.setPreferredWidth(100);
        TableColumn couverte = tableListing.getColumnModel().getColumn(4);
        couverte.setPreferredWidth(100);
        TableColumn numVelo = tableListing.getColumnModel().getColumn(5);
        numVelo.setPreferredWidth(100);
        TableColumn dateAchat = tableListing.getColumnModel().getColumn(6);
        dateAchat.setPreferredWidth(100);
        TableColumn dateArrivee = tableListing.getColumnModel().getColumn(7);
        dateArrivee.setPreferredWidth(100);

        tableListing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane barre = new JScrollPane();
        barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        barre.setViewportView(tableListing);
        barre.setWheelScrollingEnabled(true);

        tableListing.setModel(modele);

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
                    RechercheStationParEntreprisePanneau v = new RechercheStationParEntreprisePanneau(fenetrePrincipale);
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
