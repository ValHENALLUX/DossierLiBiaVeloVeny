package packageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import packageException.ConnectionException;

public class FenetrePrincipale extends JFrame
{
    private JMenuBar barreMenu;
    private JMenu fichier, options, recherches;
    private JMenuItem accueil, ajout, listing, rechercheStationParEntreprise, rechercheVeloParLivreur,
            rechercheReparationParEntreprise, listingFichesReparationNonValides, listingFichesReparationsValides;
    private Container conteneurPrincipal;
    private JPanel panneauAccueil, panneauVelo;
    private JLabel messageAccueil;
    private ThreadVelo threadVelo;


    public FenetrePrincipale()
    {
        super("Li Bia Vélo - Gestion des stations");

        ActionMenu action = new ActionMenu();

        setBounds(0,0,1080,760);
        setLocation(300,150);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //region Menu
        barreMenu = new JMenuBar();
        setJMenuBar(barreMenu);

        fichier = new JMenu("Fichier");
        options = new JMenu("Options");
        recherches = new JMenu("Recherches");

        accueil = new JMenuItem("Accueil");
        ajout = new JMenuItem("Ajouter une station");
        listing = new JMenuItem("Listing des stations");

        listingFichesReparationNonValides = new JMenuItem("Listing fiches non validées");
        listingFichesReparationsValides = new JMenuItem("Listing fiches validées");

        rechercheStationParEntreprise = new JMenuItem("Recherche des stations contenant des vélos d'une entreprise");
        rechercheReparationParEntreprise = new JMenuItem("Recherche des vélos réparés d'une entreprise");
        rechercheVeloParLivreur = new JMenuItem("Recherche des vélos transportés par un livreur");

        barreMenu.add(fichier);
        barreMenu.add(options);
        barreMenu.add(recherches);

        fichier.add(accueil);
        fichier.add(ajout);
        fichier.add(listing);

        options.add(listingFichesReparationNonValides);
        options.add(listingFichesReparationsValides);

        recherches.add(rechercheStationParEntreprise);
        recherches.add(rechercheReparationParEntreprise);
        recherches.add(rechercheVeloParLivreur);

        accueil.addActionListener(action);
        ajout.addActionListener(action);
        listing.addActionListener(action);
        listingFichesReparationNonValides.addActionListener(action);
        listingFichesReparationsValides.addActionListener(action);
        rechercheStationParEntreprise.addActionListener(action);
        rechercheReparationParEntreprise.addActionListener(action);
        rechercheVeloParLivreur.addActionListener(action);

        //endregion

        //region Panneau Accueil
        panneauAccueil = new JPanel();
        panneauAccueil.setBounds(200, 200, 380, 200);
        panneauAccueil.setLayout(new FlowLayout());
        messageAccueil = new JLabel(
                "<html><body><div color='black'><center>Bienvenue sur l'application de gestion des stations de \"Li Bia Vélo\" </center><br/" +
                        "<p>Cette dernière permet :</p>" +
                        "<ul>" +
                        "<li>d'ajouter une station</li>" +
                        "<li>de modifier une station</li>" +
                        "<li>de supprimer une station</li>" +
                        "<li>de lister les différentes stations</li>" +
                        "</ul>" +
                        "<p>Ainsi que d'effectuer diverses recherches</p></div></body></html>"
        );
        messageAccueil.setBounds(50, 50, 300, 100);
        panneauAccueil.setSize(getWidth()/3, getHeight()/2);
        panneauAccueil.setPreferredSize(new Dimension(getWidth()/3, getHeight()/2));
        panneauAccueil.add(messageAccueil);
        //endregion

        //region Panneau Vélo
        panneauVelo = new JPanel();
        panneauVelo.setBounds(600, 200, 450, 430);
        panneauVelo.setSize(getWidth()/3, getHeight()/2);
        panneauVelo.setPreferredSize(new Dimension(getWidth()/3, getHeight()/2));

        //region Thread Vélo
        threadVelo = new ThreadVelo(this);
        threadVelo.start();
        //endregion

        //region Conteneur
        conteneurPrincipal = getContentPane();
        conteneurPrincipal.setLayout(new GridLayout(2,1));
        conteneurPrincipal.add(panneauAccueil);
        conteneurPrincipal.add(panneauVelo);
        setVisible(true);
        //endregion

    }

    public JPanel getPanneauAccueil() {
        return panneauAccueil;
    }

    public JPanel getPanneauVelo() { return panneauVelo; }

    private class ActionMenu implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try 
            {
                switch (e.getActionCommand())
                {
                    case "Accueil" :
                    {
                        FenetrePrincipale.this.getContentPane().removeAll();
                        FenetrePrincipale.this.getContentPane().setLayout(null);
                        FenetrePrincipale.this.getContentPane().add(panneauAccueil);
                        FenetrePrincipale.this.getContentPane().add(panneauVelo);
                        FenetrePrincipale.this.getContentPane().repaint();
                        FenetrePrincipale.this.getContentPane().validate();
                        break;
                    }
                    case "Ajouter une station" :
                    {
                        GestionPanneau a = new GestionPanneau(FenetrePrincipale.this, null);
                        break;
                    }
                    case "Listing des stations" :
                    {
                        ListingPanneau l = new ListingPanneau(FenetrePrincipale.this);
                        break;
                    }
                    case "Recherche des stations contenant des vélos d'une entreprise" :
                    {
                        RechercheStationParEntreprisePanneau s = new RechercheStationParEntreprisePanneau(FenetrePrincipale.this);
                        break;
                    }
                    case "Recherche des vélos réparés d'une entreprise" :
                    {
                        RechercheReparationParEntreprisePanneau r = new RechercheReparationParEntreprisePanneau(FenetrePrincipale.this);
                        break;
                    }
                    case "Recherche des vélos transportés par un livreur" :
                    {
                        RechercheVeloParLivreurPanneau v = new RechercheVeloParLivreurPanneau(FenetrePrincipale.this);
                        break;
                    }
                    case "Listing fiches non validées" :
                    {
                        ListingReparationsPanneau lrp = new ListingReparationsPanneau(FenetrePrincipale.this, 0);
                        break;
                    }
                    case "Listing fiches validées" :
                    {
                        ListingReparationsPanneau lrp = new ListingReparationsPanneau(FenetrePrincipale.this, 1);
                        break;
                    }
                }
            }
            catch (ConnectionException ex)
            {
                System.out.println("Erreur de connexion : " + ex);
            }
        }
    }
}

