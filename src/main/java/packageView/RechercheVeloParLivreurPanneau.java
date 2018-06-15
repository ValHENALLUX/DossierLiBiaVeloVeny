package packageView;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import packageController.Controller;
import packageException.ConnectionException;
import packageModel.EmployeModel;
import packageModel.LivraisonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RechercheVeloParLivreurPanneau extends JPanel
{
    private Container conteneur;
    private JPanel panneauTitre, panneauFormulaire, panneauBoutons;
    private JLabel labelTitre, labelDateDebut, labelDateFin, labelNomLivreur;
    private JSpinner spinDateDebut, spinDateFin;
    private JComboBox livreurs;
    private ArrayList<EmployeModel> listeLivreurs = new ArrayList<>();
    private ArrayList<LivraisonModel> listeLivraisons = new ArrayList<>();
    private JButton annuler, valider;
    private Controller controller;
    private FenetrePrincipale fenetrePrincipale;

    public RechercheVeloParLivreurPanneau(FenetrePrincipale f) throws ConnectionException
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
        labelTitre = new JLabel("Réparation des vélos par entreprise");
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
        spinDateFin = new JSpinner();
        SpinnerDateModel formatDateFin = new SpinnerDateModel();
        spinDateFin.setModel(formatDateFin);
        JSpinner.DateEditor editeurDateFin = new JSpinner.DateEditor(spinDateFin, "dd/MM/yyyy");
        spinDateFin.setEditor(editeurDateFin);
        labelNomLivreur = new JLabel("Nom du livreur :", SwingConstants.RIGHT);
        livreurs = new JComboBox();

        try
        {
            controller = new Controller();
            listeLivreurs = controller.listeEmploye();
            for(EmployeModel livreur : listeLivreurs)
            {livreurs.addItem(livreur.getNom() + " "+livreur.getPrenom());}
        }
        catch(ConnectionException ce)
        { JOptionPane.showMessageDialog(null, ce.getMessage(), null, JOptionPane.ERROR_MESSAGE); }


        panneauFormulaire.add(labelDateDebut);
        panneauFormulaire.add(spinDateDebut);
        panneauFormulaire.add(labelDateFin);
        panneauFormulaire.add(spinDateFin);
        panneauFormulaire.add(labelNomLivreur);
        panneauFormulaire.add(livreurs);

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
            try
            {
                    listeLivraisons.clear();
                    GregorianCalendar gregDateDebut = new GregorianCalendar();
                    gregDateDebut.setTime((Date) spinDateDebut.getValue());
                    GregorianCalendar gregDateFin = new GregorianCalendar();
                    gregDateFin.setTime((Date) spinDateFin.getValue());
                   /* Pattern pattern = Pattern.compile("^([A-Z][a-z]+)(((( |-)([A-Z][a-z]+)){0,})( - )([A-Z][a-z]+))$");
                    Matcher matcher = pattern.matcher(livreurs.getSelectedItem().toString());
                    matcher.matches();
                    String prenom = matcher.group(1) + matcher.group(3);
                    String nom = matcher.group(8);
                    */
                    if (gregDateDebut.getTimeInMillis() < gregDateFin.getTimeInMillis()) {
                    listeLivraisons = controller.rechercheLivraison(livreurs.getSelectedItem().toString(),gregDateDebut, gregDateFin);
                    LivraisonPanneau p = new LivraisonPanneau(fenetrePrincipale, listeLivraisons,livreurs.getSelectedItem().toString(), gregDateDebut, gregDateFin);

                    }
                else
                {JOptionPane.showMessageDialog(null, "La date de début doit être antérieure à la date de fin.", "Erreur", JOptionPane.ERROR_MESSAGE);}
            }
            catch(ConnectionException ce)
            {JOptionPane.showMessageDialog(null, ce, "Erreur", JOptionPane.ERROR_MESSAGE);}
        }
    }
}
