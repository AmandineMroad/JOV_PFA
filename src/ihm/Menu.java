/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import ihm.listeners.Lis_Execute;
import ihm.listeners.Lis_ExitJOV;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import util.Lis_OpenReadMe;


/**
 *
 * @author Amandine
 */
public class Menu extends JMenuBar {
//    JPopupMenu.setDefaultLightWeightPopupEnabled(false);

    JMenu menu = new JMenu("Menu");
//    JMenuItem item_1 = new JMenuItem("Fichier");
    JMenu item_1 = new JMenu("Fichier");
    JMenuItem item_2 = new JMenuItem("Ouvrir le ReadMe");
    JMenuItem item_3 = new JMenuItem("Quitter");
    JMenuItem item_1_1 = new JMenuItem("Nouveau");
    JMenuItem item_1_2 = new JMenuItem("Recommencer");

//    JPopupMenu jpm_fichier = new JPopupMenu("Fichier"),
//            jpm_openRM = new JPopupMenu("ReadMe"),
//            jpm_quit = new JPopupMenu("Quitter");
    public Menu() {
        //item_1_1.addActionListener(new NouveauFichier());
        item_1_2.addActionListener (new Lis_Execute());
        item_2.addActionListener(new Lis_OpenReadMe());
        item_3.addActionListener(new Lis_ExitJOV());
        item_1.add(item_1_1);
        item_1.add(item_1_2);
        menu.add(item_1);
        menu.add(item_2);
        menu.add(item_3);
        this.add(menu);
        //this.setVisible(true);
    }

}
