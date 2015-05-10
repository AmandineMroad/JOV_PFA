/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm;

import ihm.listeners.Lis_Execute;
import ihm.listeners.Lis_ExitJOV;
import ihm.listeners.Lis_NouveauFichier;
import ihm.listeners.Lis_OpenReadMe;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
        item_1_1.addActionListener(new Lis_NouveauFichier());
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
