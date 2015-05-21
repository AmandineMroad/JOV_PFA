/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm.graphique;

import ihm.BarreOutil;
import ihm.JOV_Menu;
import ihm.listeners.Lis_ExitJOV;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFrame;

/**
 * Fenêtre principale JOV
 * @author Simon
 */
public class FenVisualisation extends JFrame
{
    /*
        ATTRIBUTS
    */
    /** PanneauCode pour affichage contenu du fichier source */
    private PanneauCode pc;
    
    /** PanneauGraphique pour affichage modélisation*/
    private PanneauGraphique pg;
    
    private Dimension d;
    
    private BarreOutil toolBar;
    
     /**
     * Constructeur
     * @param f : le fichier à visualiser
     * @throws IOException 
     */
    public FenVisualisation(File f) throws IOException, URISyntaxException
    {
        d = new Dimension(850, 400);
        this.setSize(d);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new Lis_ExitJOV());
        this.setLocationRelativeTo(null);
        this.setTitle(" JAVA Object Viewer ");
       
        pc = new PanneauCode(d, f);
        pg = new PanneauGraphique(pc, f);

        this.add(pc, BorderLayout.WEST);
        this.add(pg);
               
        JOV_Menu menuBar = new JOV_Menu();
        this.setJMenuBar(menuBar);
        
        toolBar = new BarreOutil("Tools");
        this.add(toolBar, BorderLayout.PAGE_START);
        this.setVisible(false);  
    }

    /* Accesseurs */

    /**
     * Renvoie le panneau code de la fenetre
     * @return le panneau code
     */
    
    
    public PanneauCode getPc() {
        return pc;
    }


    /**
     * Renvoie le panneau graphique de la fenetre
     * @return le panneau graphique
     */
    public PanneauGraphique getPg() {
        return pg;
    }

    /**
     * Renvoie la barre d'outils JOV
     * @return barre d'outil
     */
    public BarreOutil getToolBar(){
        return toolBar;
    }
}
