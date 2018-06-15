package packageView;

import packageController.Controller;
import packageException.ComparaisonDatesException;
import packageException.ConnectionException;
import packageException.ValidatorException;
import packageModel.AtelierModel;
import packageModel.FicheReparationModel;
import packageModel.OrdreTransportModel;
import packageModel.VeloModel;
import packageTools.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GestionFicheReparationPanneau extends JPanel
{
    private JPanel panneauTitre;
    private JLabel labelTitre;
    private JPanel panneauFormulaire;
    private JPanel panneauIdentification;
    private JLabel labelIdFiche, labelDateDebut, labelDateFin;
    private JTextField champIdFiche;
    private JSpinner spinDateDebut, spinDateFin;
    private JPanel panneauReparation;
    private JLabel labelRemarque, labelOrdreTravail;
    private JTextField champRemarque, champOrdretravail;
    private JCheckBox checkEstDeclasse;
    private JPanel panneauInfos;
    private JLabel labelVelo, labelAtelier;
    private JComboBox comboVelos, comboAteliers;
    private ArrayList<VeloModel> listeVelos = new ArrayList<>();
    private ArrayList<AtelierModel> listeAteliers = new ArrayList<>();
    private JPanel panneauBoutons;
    private JButton annuler, valider;
    private Container conteneur;
    private Controller controller;
    private FicheReparationModel ficheReparation;
    private FenetrePrincipale fenetrePrincipale;

    public GestionFicheReparationPanneau(FenetrePrincipale f, FicheReparationModel fiche) throws ConnectionException
    {
        fenetrePrincipale = f;
        conteneur = f.getContentPane();
        conteneur.removeAll();
        controller = new Controller();
        ficheReparation = fiche;

        //region panneaux
        panneauTitre = new JPanel();
        panneauFormulaire = new JPanel();
        panneauIdentification = new JPanel();
        panneauReparation = new JPanel();
        panneauInfos = new JPanel();
        panneauBoutons = new JPanel();
        //endregion

        //region layouts
        conteneur.setLayout(new BorderLayout());
        panneauTitre.setLayout(new FlowLayout());
        panneauFormulaire.setLayout(new BorderLayout());
        panneauIdentification.setLayout(new GridLayout(1,6));
        panneauReparation.setLayout(new GridLayout(2,4));
        panneauInfos.setLayout(new GridLayout(1,4));
        panneauBoutons.setLayout(new FlowLayout());
        //endregion

        //region titre
        labelTitre = new JLabel("Validation d'une fiche de réparation");
        panneauTitre.add(labelTitre);
        conteneur.add(panneauTitre, BorderLayout.NORTH);
        //endregion

        //region formulaire

        //region identification
        labelIdFiche = new JLabel("Numéro de fiche :", SwingConstants.RIGHT);
        champIdFiche = new JTextField(20);
        champIdFiche.setText(ficheReparation.getNumFiche().toString());

        SpinnerDateModel formatDateDebut = new SpinnerDateModel();
        SpinnerDateModel formatDateFin = new SpinnerDateModel();

        //region date début
        labelDateDebut = new JLabel("Date de début :", SwingConstants.RIGHT);
        spinDateDebut = new JSpinner();
        spinDateDebut.setModel(formatDateDebut);
        JSpinner.DateEditor editeurDateDebut = new JSpinner.DateEditor(spinDateDebut,"dd/MM/yyyy");
        spinDateDebut.setEditor(editeurDateDebut);
        spinDateDebut.setEnabled(false);
        GregorianCalendar gregDateDebut = new GregorianCalendar();
        gregDateDebut.setTime(ficheReparation.getDateDebut().getTime());
        spinDateDebut.setValue(gregDateDebut.getTime());
        //endregion

        //region date fin
        labelDateFin = new JLabel("Date de fin :", SwingConstants.RIGHT);
        spinDateFin = new JSpinner();
        spinDateFin.setModel(formatDateFin);
        JSpinner.DateEditor editeurDateFin = new JSpinner.DateEditor(spinDateFin,"dd/MM/yyyy");
        spinDateFin.setEditor(editeurDateFin);
        spinDateFin.setEnabled(true);
        GregorianCalendar gregDateFin = new GregorianCalendar();
        gregDateFin.setTime(ficheReparation.getDateFin().getTime());
        spinDateFin.setValue(gregDateFin.getTime());
        //endregion

        panneauIdentification.add(labelIdFiche);
        panneauIdentification.add(champIdFiche);
        panneauIdentification.add(labelDateDebut);
        panneauIdentification.add(spinDateDebut);
        panneauIdentification.add(labelDateFin);
        panneauIdentification.add(spinDateFin);

        panneauFormulaire.add(panneauIdentification, BorderLayout.NORTH);
        //endregion

        //region réparations
        labelRemarque = new JLabel("Remarque :",SwingConstants.RIGHT);
        champRemarque = new JTextField(45);
        champRemarque.setText(ficheReparation.getRemarque());
        labelOrdreTravail = new JLabel("Ordre de travail :",SwingConstants.RIGHT);
        champOrdretravail = new JTextField(45);
        champOrdretravail.setText(ficheReparation.getOrdreTravail());

        checkEstDeclasse = new JCheckBox("Déclassé");
        checkEstDeclasse.setSelected(ficheReparation.isEstDeclasse());

        panneauReparation.add(labelRemarque);
        panneauReparation.add(champRemarque);
        panneauReparation.add(labelOrdreTravail);
        panneauReparation.add(champOrdretravail);
        panneauReparation.add(new JLabel(""));
        panneauReparation.add(checkEstDeclasse);
        panneauReparation.add(new JLabel(""));
        panneauReparation.add(new JLabel(""));

        panneauFormulaire.add(panneauReparation, BorderLayout.CENTER);
        //endregion

        //region infos
        labelVelo = new JLabel("Vélo :", SwingConstants.RIGHT);
        comboVelos = new JComboBox();

        listeVelos = controller.listeVelo();
        listeVelos.forEach((velo) -> {comboVelos.addItem(velo);});
        comboVelos.getModel().setSelectedItem(ficheReparation.getVelo());
        comboVelos.setEnabled(false);
        labelAtelier = new JLabel("Atelier :", SwingConstants.RIGHT);
        comboAteliers = new JComboBox();

        listeAteliers = controller.listeAtelier();
        listeAteliers.forEach((atelier) -> {comboAteliers.addItem(atelier);});
        comboAteliers.getModel().setSelectedItem(ficheReparation.getAtelier());

        panneauInfos.add(labelVelo);
        panneauInfos.add(comboVelos);
        panneauInfos.add(labelAtelier);
        panneauInfos.add(comboAteliers);

        panneauFormulaire.add(panneauInfos, BorderLayout.SOUTH);
        //endregion

        conteneur.add(panneauFormulaire, BorderLayout.CENTER);

        //endregion

        //region boutons
        annuler = new JButton("Annuler");
        valider = new JButton("Valider");

        panneauBoutons.add(annuler);
        panneauBoutons.add(valider);

        conteneur.add(panneauBoutons, BorderLayout.SOUTH);

        GestionnaireEvenement ge = new GestionnaireEvenement();
        valider.addActionListener(ge);
        annuler.addActionListener(ge);
        //endregion

        f.getContentPane().repaint();
        f.setVisible(true);
    }

    private class GestionnaireEvenement implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try {
                if (ae.getSource() == annuler)
                {

                    ListingReparationsPanneau lrp = new ListingReparationsPanneau(fenetrePrincipale, 0);
                }

                if (ae.getSource() == valider)
                {
                    Boolean datesOk = false;
                    Validator validateur = new Validator();
                    Integer numFiche = validateur.verificationChiffreAvecTexte(champIdFiche.getText(), "Numéro de la fiche");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    String strDateDebut = dateFormat.format(spinDateDebut.getValue());
                    GregorianCalendar gregDateDebut = new GregorianCalendar();
                    gregDateDebut = validateur.verificationDate(strDateDebut, "Date de début");
                    String strDateFin = dateFormat.format(spinDateFin.getValue());
                    GregorianCalendar gregDateFin = new GregorianCalendar();
                    gregDateFin = validateur.verificationDateFin(strDateFin, "Date de fin");
                    datesOk = validateur.compareDates(gregDateDebut, "date de début", gregDateFin, "date de fin");

                    String remarque = champRemarque.getText();
                    String ordreTravail = validateur.verificationTexte(champOrdretravail.getText(),"Ordre de travail");
                    Boolean estDeclasse = checkEstDeclasse.isSelected();

                    VeloModel velo = (VeloModel) comboVelos.getSelectedItem();
                    AtelierModel atelier = (AtelierModel) comboAteliers.getSelectedItem();

                    Boolean estValide = false;
                    if(datesOk)
                    {
                        ficheReparation.setNumFiche(numFiche);
                        ficheReparation.setDateDebut(gregDateDebut);
                        ficheReparation.setDateFin(gregDateFin);
                        ficheReparation.setRemarque(remarque);
                        ficheReparation.setOrdreTravail(ordreTravail);
                        ficheReparation.setEstDeclasse(estDeclasse);
                        ficheReparation.setVelo(velo);
                        ficheReparation.setAtelier(atelier);

                        estValide = controller.validationFicheReparation(ficheReparation);
                        if(estValide)
                        {
                            Boolean ordreValide = false;
                            JOptionPane.showMessageDialog(null, "La fiche a bien été validée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            OrdreTransportModel ordreTransport = new OrdreTransportModel();
                            ordreTransport.setDateTransport(gregDateDebut);
                            ordreTransport.setVelo(ficheReparation.getVelo());
                            ordreTransport.setStationOrigine(ficheReparation.getVelo().getStation());
                            ordreValide = controller.ajoutOrdreTransport(ordreTransport);
                            if(ordreValide)
                            {
                                ListingReparationsPanneau lrp = new ListingReparationsPanneau(fenetrePrincipale, 0);
                            }
                        }
                    }
                }
            }
            catch (ComparaisonDatesException cde)
            {
                JOptionPane.showMessageDialog(null, cde, "Erreur de dates", JOptionPane.ERROR_MESSAGE);
            }
            catch (ValidatorException ve)
            {
                JOptionPane.showMessageDialog(null, ve, "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            }
            catch (ConnectionException ce)
            {
                JOptionPane.showMessageDialog(null, ce, "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
