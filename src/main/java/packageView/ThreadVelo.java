package packageView;

import javax.swing.*;

public class ThreadVelo extends Thread
{
    private FenetrePrincipale fenetre;
    private JLabel[] images = new JLabel[87];

    public ThreadVelo (FenetrePrincipale fenetre)
    {
        this.fenetre = fenetre;
        for (int i = 0 ; i<images.length ; i++)
        {
            images[i] = new JLabel(new ImageIcon("./src/main/java/packageResources/Images/frame_" + ((i<10)?"0":"") + i + "_delay-0.03s.gif"));
        }
    }
    public void run()
    {
        try
        {
            while(true)
            {
                for (int i = 0; i<images.length; i++)
                {
                    fenetre.getPanneauVelo().add(images[i]);
                    fenetre.getPanneauVelo().repaint();
                    fenetre.getPanneauVelo().validate();
                    Thread.sleep(30);
                    fenetre.getPanneauVelo().removeAll();
                }
            }
        }
        catch(InterruptedException ie)
        {
            JOptionPane.showMessageDialog(null, ie, "Erreur lors de l'animation du vélo!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
