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


/**
 * Barre de menu de la fenêtre de visualisation
 * @author Amandine
 */
public class Menu extends JMenuBar {
    JMenu menu = new JMenu("Menu");
    JMenu item_1 = new JMenu("Fichier");
    JMenuItem item_2 = new JMenuItem("Ouvrir le ReadMe");
    JMenuItem item_3 = new JMenuItem("Quitter");
    JMenuItem item_1_1 = new JMenuItem("Nouveau");
    JMenuItem item_1_2 = new JMenuItem("Recommencer");

    JMenuItem next;
    JMenuItem stop;
    JMenuItem replay;

    /**
     * Constructeur: ajoute les éléments de menu et attache les listeners
     */
    public Menu() {
        item_1_1.addActionListener(new Lis_NouveauFichier());
        item_1_2.addActionListener (new Lis_Execute());
        item_2.addActionListener(new Lis_OpenReadMe());
        item_3.addActionListener(new Lis_ExitJOV());
        item_1.add(item_1_1);
        item_1.add(item_1_2);
        menu.add(item_1);
        menu.add(item_2);
        menu.add(item_3);
        
        ImageIcon iconeNext = new ImageIcon(".\\src\\doc\\icon-next.gif");
        ImageIcon iconeStop = new ImageIcon(".\\src\\doc\\button-stop.png");
        ImageIcon iconeReplay = new ImageIcon(".\\src\\doc\\replay.png");
        next = new JMenuItem(iconeNext);
        
        stop = new JMenuItem(iconeStop);
        replay = new JMenuItem(iconeReplay);
       
        next.addActionListener(new Lis_Next());
        stop.addActionListener(new Lis_Stop());
        replay.addActionListener(new Lis_Execute());
        this.add(menu);
        this.add(next);
        this.add(stop);
        this.add(replay);
        
    }
    
    /**
     * Désactive le bouton next
     */
    public void disableNext(){
        next.setEnabled(false);
    }

}
