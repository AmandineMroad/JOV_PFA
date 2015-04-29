/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import ihm.listeners.Lis_Execute;
import ihm.listeners.Lis_ExitJOV;
import ihm.listeners.Lis_Next;
import ihm.listeners.Lis_NouveauFichier;
import ihm.listeners.Lis_Stop;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import ihm.listeners.Lis_OpenReadMe;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;


/**
 * Barre de menu de la fenêtre de visualisation
 * @author Amandine
 */
public class JOV_Menu extends MenuBar {
    Menu menu = new Menu("Menu");
    Menu item_1 = new Menu("Fichier");
    MenuItem item_2 = new MenuItem("Ouvrir le ReadMe");
    MenuItem item_3 = new MenuItem("Quitter");
    MenuItem item_1_1 = new MenuItem("Nouveau");
    MenuItem item_1_2 = new MenuItem("Recommencer");


    /**
     * Constructeur: ajoute les éléments de menu et attache les listeners
     */
    public JOV_Menu() {
        item_1_1.addActionListener(new Lis_NouveauFichier());
        item_1_2.addActionListener (new Lis_Execute());
        item_2.addActionListener(new Lis_OpenReadMe());
        item_3.addActionListener(new Lis_ExitJOV());
        item_1.add(item_1_1);
        item_1.add(item_1_2);
        menu.add(item_1);
        menu.add(item_2);
        menu.add(item_3);

        this.add(menu);
    }
   
}
