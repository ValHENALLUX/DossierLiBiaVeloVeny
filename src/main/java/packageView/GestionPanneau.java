package packageView;

import packageModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import packageController.Controller;
import packageException.ConnectionException;
import packageException.ValidatorException;
import packageTools.Validator;


public class GestionPanneau extends JPanel
{
    private JPanel panneauTitre;
    private JLabel labelTitre;
    private JPanel panneauFormulaire;
    private JPanel panneauIdentification;
    private JLabel labelNom, labelDate;
    private JTextField champNom, champLCL, champLWL, champUWL, champUCL;
    private JSpinner spinDateCreation;
    private JCheckBox stationCouverte;
    private JPanel panneauInfos;
    private JPanel panneauBornes;
    private JLabel labelBornes, labelLCL, labelLWL, labelUWL, labelUCL;
    private JPanel panneauAdresse;
    private JLabel labelAdresse, labelLocalite, labelRue, labelLongitude, labelLatitude;
    private JComboBox localites;
    private ArrayList <LocaliteModel> listeLocalites = new ArrayList<LocaliteModel>();
    private LocaliteModel localite;
    private JTextField champRue, champLongitude, champLatitude;
    private JPanel panneauBoutons;
    private JButton annuler, ajouter;
    private Container conteneur;
    private Controller controller;
    private boolean estModifie;
    private StationModel station;
    private FenetrePrincipale fenetrePrincipale;

    public GestionPanneau(FenetrePrincipale f, StationModel station) throws ConnectionException
    {
        fenetrePrincipale = f;
        conteneur = fenetrePrincipale.getContentPane();
        conteneur.removeAll();
        controller = new Controller();        
        this.station = (station == null) ? new StationModel() : station;
        estModifie = (station != null);
        
        //region panneaux
        panneauTitre = new JPanel();
        panneauFormulaire = new JPanel();
        panneauIdentification = new JPanel();
        panneauInfos = new JPanel();
        panneauBornes = new JPanel();
        panneauBornes.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panneauAdresse = new JPanel();
        panneauAdresse.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panneauBoutons = new JPanel();
        //endregion

        //region layouts
        conteneur.setLayout(new BorderLayout());
        panneauTitre.setLayout(new FlowLayout());
        panneauFormulaire.setLayout(new GridLayout(2,1));
        panneauIdentification.setLayout(new GridLayout(4,2));
        panneauInfos.setLayout(new GridLayout(1,2));
        panneauBornes.setLayout(new GridLayout(5,2));
        panneauAdresse.setLayout(new GridLayout(5, 2));
        panneauBoutons.setLayout(new FlowLayout());
        //endregion

        //region titre
        labelTitre = (estModifie) ? new JLabel("Modification d'une station") : new JLabel("Ajout d'une station");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region formulaire

        //region identification
        labelNom = new JLabel("Nom de la station :", SwingConstants.RIGHT);
        champNom = new JTextField(20);
        champNom.setToolTipText("maximum 20 caractères.");
        labelDate = new JLabel("Date de création", SwingConstants.RIGHT);
        spinDateCreation = new JSpinner();
        SpinnerDateModel formatDateCreation = new SpinnerDateModel();
        spinDateCreation.setModel(formatDateCreation);
        JSpinner.DateEditor editeurDateCreation = new JSpinner.DateEditor(spinDateCreation, "dd/MM/yyyy");
        spinDateCreation.setEditor(editeurDateCreation);
        spinDateCreation.setEnabled(false);

        stationCouverte = new JCheckBox("Station couverte");

        panneauIdentification.add(labelNom);
        panneauIdentification.add(champNom);
        panneauIdentification.add(labelDate);
        panneauIdentification.add(spinDateCreation);
        panneauIdentification.add(new JLabel(""));
        panneauIdentification.add(stationCouverte);

        panneauFormulaire.add(panneauIdentification);
        //endregion

        //region bornes
        labelBornes = new JLabel("Bornes");
        labelUCL = new JLabel("Limite supérieure exceptionnelle :", SwingConstants.RIGHT);
        champUCL = new JTextField(3);
        champUCL.setToolTipText("Entrez un nombre");
        labelUWL = new JLabel("Limite supérieure normale :", SwingConstants.RIGHT);
        champUWL = new JTextField(3);
        champUWL.setToolTipText("Entrez un nombre");
        labelLWL = new JLabel("Limite inférieure normale :", SwingConstants.RIGHT);
        champLWL = new JTextField(3);
        champLWL.setToolTipText("Entrez un nombre");
        labelLCL = new JLabel("Limite inférieure exceptionnelle :", SwingConstants.RIGHT);
        champLCL = new JTextField(3);
        champLCL.setToolTipText("Entrez un nombre");
        
        panneauBornes.add(labelBornes);
        panneauBornes.add(new JLabel(""));
        panneauBornes.add(labelUCL);
        panneauBornes.add(champUCL);
        panneauBornes.add(labelUWL);
        panneauBornes.add(champUWL);
        panneauBornes.add(labelLWL);
        panneauBornes.add(champLWL);
        panneauBornes.add(labelLCL);
        panneauBornes.add(champLCL);
        //endregion

        panneauInfos.add(panneauBornes);

        //region adresse
        labelAdresse = new JLabel("Adresse");
        labelLocalite = new JLabel("Localité :");
        localites = new JComboBox();

        listeLocalites = controller.listeLocalite();
        listeLocalites.forEach((localite) -> {
            localites.addItem(localite);
        });
            
        labelRue = new JLabel("Rue :");
        champRue = new JTextField(30);
        champRue.setToolTipText("maximum 30 caractères");
        labelLatitude = new JLabel("(Optionnel) Latitude :");
        champLatitude = new JTextField(7);
        champLatitude.setToolTipText("coordonnées au format cc,ccl");
        labelLongitude = new JLabel("(Optionnel) Longitude :");
        champLongitude = new JTextField(7);
        champLongitude.setToolTipText("coordonnées au format cc,ccl");

        panneauAdresse.add(labelAdresse);
        panneauAdresse.add(new JLabel());
        panneauAdresse.add(labelLocalite);
        panneauAdresse.add(localites);
        panneauAdresse.add(labelRue);
        panneauAdresse.add(champRue);

        panneauAdresse.add(labelLatitude);
        panneauAdresse.add(champLatitude);
        panneauAdresse.add(labelLongitude);
        panneauAdresse.add(champLongitude);
        //endregion

        panneauInfos.add(panneauAdresse);

        panneauFormulaire.add(panneauInfos);
        conteneur.add(panneauFormulaire,BorderLayout.CENTER);

        if(estModifie) {
            //Remplissage des données
            champNom.setText(station.getNom());
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(station.getDateCreation().getTime());
            spinDateCreation.setValue(gregorianCalendar.getTime());
            stationCouverte.setSelected(station.estCouverte());
            localites.getModel().setSelectedItem(station.getLocalite());
            champRue.setText(station.getLibelleRue());
            champUCL.setText(station.getNbVeloMaxEx()+"");
            champUWL.setText(station.getNbVeloMaxNorm()+"");
            champLCL.setText(station.getNbVeloMinEx()+"");
            champLWL.setText(station.getNbVeloMinNorm()+"");
            champLongitude.setText(station.getLongitude());
            champLatitude.setText(station.getLatitude());
        }
        
        //region bouton
        
        annuler = new JButton("Annuler");
        ajouter = (estModifie) ? new JButton("Modifier") : new JButton("Ajouter");

        panneauBoutons.add(annuler);
        panneauBoutons.add(ajouter);
        //endregion

        conteneur.add(panneauBoutons,BorderLayout.SOUTH);
        
        f.getContentPane().repaint();
        f.setVisible(true);
        
        GestionnaireEvent ge = new GestionnaireEvent();
        ajouter.addActionListener(ge);
        annuler.addActionListener(ge);
    }
    private class GestionnaireEvent implements ActionListener 
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                if (event.getSource() == annuler)
                {
                    ListingPanneau l = new ListingPanneau(fenetrePrincipale);
                }
                if (event.getSource() == ajouter)
                {
                    Validator validator = new Validator();

                    String nom = validator.verificationTexte(champNom.getText(), "Nom de la station");
                    int ucl = validator.verificationChiffreAvecTexte(champUCL.getText(), "Limite supérieur exceptionnelle");
                    int uwl = validator.verificationChiffreAvecTexte(champUWL.getText(), "Limite supérieur normale");
                    int lcl = validator.verificationChiffreAvecTexte(champLCL.getText(), "Limite inférieure exceptionnelle");
                    int lwl = validator.verificationChiffreAvecTexte(champLWL.getText(), "Limite supérieur normale");
                    validator.verificationBornes(lwl, lcl, uwl, ucl);

                    LocaliteModel localite = (LocaliteModel) localites.getSelectedItem();
                    String rue = validator.verificationTexte(champRue.getText(), "Rue");

                    String longitude = validator.verificationCoordonnee(champLongitude.getText(), "Longitude", false);
                    String latitude = validator.verificationCoordonnee(champLatitude.getText(), "Latitude", true);

                    station.setNom(nom);
                    station.setNbVeloMaxEx(ucl);
                    station.setNbVeloMaxNorm(uwl);
                    station.setNbVeloMinEx(lcl);
                    station.setNbVeloMinNorm(lwl);
                    station.setLongitude(longitude);
                    station.setLatitude(latitude);
                    station.setLocalite(localite);
                    station.setLibelleRue(rue);
                    station.setCouverte(stationCouverte.isSelected());

                    boolean estValide = false;
                    String message = "";
                    if (estModifie)
                    {
                        estValide = controller.modifierStation(station);
                        message = "La station a bien été modifiée";
                    }
                    else
                    {
                        estValide = controller.ajoutStation(station);
                        message = "La station a bien été ajoutée";
                    }

                    if (estValide)
                    {
                        JOptionPane.showMessageDialog(null, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        ListingPanneau lp = new ListingPanneau(fenetrePrincipale);
                    }
                }

            }
            catch (ValidatorException erreur) {
                JOptionPane.showMessageDialog(null, erreur, "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            }
            catch (ConnectionException erreur) {
                JOptionPane.showMessageDialog(null, erreur, "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
