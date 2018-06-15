package packageView;

import packageController.Controller;
import packageException.ConnectionException;
import packageModel.EntrepriseModel;
import packageModel.InformationMarquesVeloModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class RechercheStationParEntreprisePanneau
{
    private Container conteneur;
    private JPanel panneauTitre, panneauFormulaire, panneauBoutons;
    private JLabel labelTitre, labelDateDebut, labelDateFin, labelNomSociete;
    private JSpinner spinDateDebut, spinDateFin;
    private JComboBox societes;
    private ArrayList<EntrepriseModel> listeEntreprises = new ArrayList<>();
    private ArrayList<InformationMarquesVeloModel> listeStations = new ArrayList<>();
    private JButton annuler, valider;
    private Controller controller;
    private FenetrePrincipale fenetrePrincipale;

    public RechercheStationParEntreprisePanneau(FenetrePrincipale f) throws ConnectionException
    {
        fenetrePrincipale = f;
        conteneur = f.getContentPane();
        conteneur.removeAll();
        controller = new Controller();

        //region panneaux
        panneauTitre = new JPanel();
        panneauFormulaire = new JPanel();
        panneauBoutons = new JPanel();
        //endregion

        //region layouts
        conteneur.setLayout(new BorderLayout());
        panneauTitre.setLayout(new FlowLayout());
        panneauFormulaire.setLayout(new GridLayout(3,2));
        panneauBoutons.setLayout(new FlowLayout());
        //endregion

        //region titre
        labelTitre = new JLabel("Stations contenant des vélos provenant d'une entreprise");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region formulaire
        labelDateDebut = new JLabel("Date de début :", SwingConstants.RIGHT);
        spinDateDebut = new JSpinner();
        SpinnerDateModel formatDateDebut = new SpinnerDateModel();
        spinDateDebut.setModel(formatDateDebut);
        JSpinner.DateEditor editeurDateDebut = new JSpinner.DateEditor(spinDateDebut, "dd/MM/yyyy");
        spinDateDebut.setEditor(editeurDateDebut);
        labelDateFin = new JLabel("Date de fin :", SwingConstants.RIGHT);
        spinDateFin = new JSpinner();
        SpinnerDateModel formatDateFin = new SpinnerDateModel();
        spinDateFin.setModel(formatDateFin);
        JSpinner.DateEditor editeurDateFin = new JSpinner.DateEditor(spinDateFin, "dd/MM/yyyy");
        spinDateFin.setEditor(editeurDateFin);
        labelNomSociete = new JLabel("Nom de la société :", SwingConstants.RIGHT);
        societes = new JComboBox();

        try
        {
            controller = new Controller();
            listeEntreprises = controller.listeEntreprise();
            for(EntrepriseModel entreprise : listeEntreprises)
            {societes.addItem(entreprise.getNom());}
        }
        catch(ConnectionException ce)
        { JOptionPane.showMessageDialog(null, ce.getMessage(), null, JOptionPane.ERROR_MESSAGE); }


        panneauFormulaire.add(labelDateDebut);
        panneauFormulaire.add(spinDateDebut);
        panneauFormulaire.add(labelDateFin);
        panneauFormulaire.add(spinDateFin);
        panneauFormulaire.add(labelNomSociete);
        panneauFormulaire.add(societes);

        conteneur.add(panneauFormulaire, BorderLayout.CENTER);
        //endregion

        //region boutons
        annuler = new JButton("Annuler");
        BoutonAnnuler boutonAnnuler = new BoutonAnnuler();
        annuler.addActionListener(boutonAnnuler);
        valider = new JButton("Valider");
        BoutonValider boutonValider = new  BoutonValider();
        valider.addActionListener(boutonValider);

        panneauBoutons.add(annuler);
        panneauBoutons.add(valider);

        conteneur.add(panneauBoutons, BorderLayout.SOUTH);
        //endregion

        f.getContentPane().repaint();
        f.setVisible(true);
    }

    private class BoutonAnnuler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {

                if (ae.getSource() == annuler) {
                    fenetrePrincipale.getPanneauAccueil();
                }


        }
    }

    private class BoutonValider implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            try {
                listeStations.clear();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                GregorianCalendar gregDateDebut = new GregorianCalendar();
                gregDateDebut.setTime((Date) spinDateDebut.getValue());
                GregorianCalendar gregDateFin = new GregorianCalendar();
                gregDateFin.setTime((Date) spinDateFin.getValue());
                if (gregDateDebut.getTimeInMillis() < gregDateFin.getTimeInMillis()) {

                    listeStations = controller.rechercheInformationsMarques(societes.getSelectedItem().toString(), gregDateDebut, gregDateFin);
                    InformationMarquesVeloPanneau p = new InformationMarquesVeloPanneau(fenetrePrincipale, listeStations,societes.getSelectedItem().toString(), gregDateDebut, gregDateFin);
                }
                else
                {JOptionPane.showMessageDialog(null, "La date de début doit être antérieure à la date de fin.", "Erreur", JOptionPane.ERROR_MESSAGE);}
            }
            catch (ConnectionException ce)
            {JOptionPane.showMessageDialog(null, ce, "Erreur", JOptionPane.ERROR_MESSAGE);}
        }
    }
}

