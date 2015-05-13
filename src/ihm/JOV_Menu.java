/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm;

import ihm.graphique.FenVisualisation;
import ihm.listeners.Lis_Execute;
import ihm.listeners.Lis_ExitJOV;
import ihm.listeners.Lis_OpenReadMe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import util.Gestionnaire;

/**
 * Barre de menu JOV
 * @author Amandine
 */
public class JOV_Menu extends JMenuBar{
    
    /** Menu principal */
    private JMenu menu = new JMenu("Menu"); 
    /** Sous-menu fichier */
    private JMenu fic = new JMenu("Fichier");
    /** Item */
    private JMenuItem item_2 = new JMenuItem("Ouvrir le ReadMe");
    /** Item */
    private JMenuItem item_3 = new JMenuItem("Quitter");    
    /** Item du sous menu fichier */
    private JMenuItem item_1_1 = new JMenuItem("Nouveau"); 
    /** Item du sous menu fichier */
    private JMenuItem item_1_2 = new JMenuItem("Recommencer");
    
    /**
     * Constructeur: ajoute les éléments de menu et attache les listeners
     */
    public JOV_Menu(){
        item_1_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
            Gestionnaire gest = Gestionnaire.getInstance();
            FenVisualisation fenetre = gest.getFVisualisation();
            fenetre.getPc().removeAll();
            fenetre.getPg().removeAll();
            fenetre.setVisible(false);
            gest.getChoixDiag().setVisible(true);
        } catch (IOException ex) {
            System.err.println("ERREUR _ nouveau fichier\n\t" + ex.toString());
        }
            }
        });
        item_1_2.addActionListener (new Lis_Execute());
        item_2.addActionListener(new Lis_OpenReadMe());
        item_3.addActionListener(new Lis_ExitJOV());
        fic.add(item_1_1);
        fic.add(item_1_2);
        menu.add(fic);
        menu.add(item_2);
        menu.add(item_3);

        this.add(menu);
    }
}
