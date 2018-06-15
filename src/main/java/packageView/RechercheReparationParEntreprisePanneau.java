package packageView;

import packageController.Controller;
import packageException.ConnectionException;
import packageModel.AtelierModel;
import packageModel.EntrepriseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheReparationParEntreprisePanneau extends JPanel
{
    private Container conteneur;
    private JPanel panneauTitre, panneauFormulaire, panneauBoutons;
    private JLabel labelTitre, labelAtelier, labelEntreprise;
    private JComboBox comboAtelier, comboEntreprise;
    private JButton annuler, valider;
    private ArrayList<EntrepriseModel> listeEntreprises = new ArrayList<>();
    private ArrayList<AtelierModel> listeAteliers = new ArrayList<>();
    private Controller controller;

    public RechercheReparationParEntreprisePanneau(FenetrePrincipale f)
    {
        conteneur = f.getContentPane();
        conteneur.removeAll();

        //region panneaux
        panneauTitre = new JPanel();
        panneauFormulaire = new JPanel();
        panneauBoutons = new JPanel();
        //endregion

        //region layouts
        conteneur.setLayout(new BorderLayout());
        panneauTitre.setLayout(new FlowLayout());
        panneauFormulaire.setLayout(new GridLayout(4,1));
        panneauBoutons.setLayout(new FlowLayout());
        //endregion

        //region titre
        labelTitre = new JLabel("Réparation des vélos par entreprise");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region formulaire
        labelAtelier = new JLabel("Identifiant de l'atelier :", SwingConstants.RIGHT);
        comboAtelier = new JComboBox();
        comboAtelier.addItem(null);
        try
        {
            controller = new Controller();
            listeAteliers = controller.listeAtelier();
            for(AtelierModel atelier : listeAteliers)
            {comboAtelier.addItem(atelier.getIdentifiant());}
        }
        catch(ConnectionException ce)
        { JOptionPane.showMessageDialog(null, ce.getMessage(), null, JOptionPane.ERROR_MESSAGE); }

        labelEntreprise = new JLabel("Nom de la société :", SwingConstants.RIGHT);
        comboEntreprise = new JComboBox();
        comboEntreprise.addItem(null);
        try
        {
            controller = new Controller();
            listeEntreprises = controller.listeEntreprise();
            for(EntrepriseModel entreprise : listeEntreprises)
            {comboEntreprise.addItem(entreprise.getNom());}
        }
        catch(ConnectionException ce)
        { JOptionPane.showMessageDialog(null, ce.getMessage(), null, JOptionPane.ERROR_MESSAGE); }

        panneauFormulaire.add(labelAtelier);
        panneauFormulaire.add(comboAtelier);
        panneauFormulaire.add(labelEntreprise);
        panneauFormulaire.add(comboEntreprise);

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
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private class BoutonValider implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }
}

