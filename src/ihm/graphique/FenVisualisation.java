/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import ihm.BarreOutil;
import ihm.JOV_Menu;
import ihm.listeners.Lis_ExitJOV;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Simon
 */
public class FenVisualisation extends JFrame
{
    /*
        ATTRIBUTS
    */
    // Contient un PanneauCode
    private PanneauCode pc;
    
    // Contient un PanneauGraphique
    private PanneauGraphique pg;
    
    // 
    private Dimension d;
    
    private BarreOutil toolBar;
    
    /**
     * A SUPPRIMER
     * @throws IOException 
     */
  /*  public FenVisualisation() throws IOException
    {
        d = new Dimension(850, 400);
        this.setSize(d);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new Lis_ExitJOV());
        this.setLocationRelativeTo(null);      
        
        this.setLayout(new BorderLayout(2, 2));
      
        pc = new PanneauCode(d);
        pg = new PanneauGraphique(pc);        
        
        this.add(pc, BorderLayout.WEST);
        this.add(pg, BorderLayout.CENTER);
        
        this.setVisible(true);    
    }
*/
    /**
     * Constructeur
     * @param f : le fichier à visualiser
     * @throws IOException 
     */
    public FenVisualisation(File f) throws IOException
    {
        d = new Dimension(850, 400);
        this.setSize(d);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new Lis_ExitJOV());
        this.setLocationRelativeTo(null);      
        
        this.setLayout(new BorderLayout(2, 2));
      
        pc = new PanneauCode(d, f);
        pg = new PanneauGraphique(pc, f);        
        

        this.add(pc, BorderLayout.WEST);
        this.add(pg);
        
        JOV_Menu menuBar = new JOV_Menu();
        this.setMenuBar(menuBar);
        toolBar = new BarreOutil("Tools");
        this.add(toolBar, BorderLayout.PAGE_START);

        this.setVisible(false);  
    }

    /* Accesseurs */
    
    public PanneauCode getPc() {
        return pc;
    }

    public void setPc(PanneauCode pc) {
        this.pc = pc;
    }

    public PanneauGraphique getPg() {
        return pg;
    }

    public void setPg(PanneauGraphique pg) {
        this.pg = pg;
    }
    
    public BarreOutil getToolBar(){
        return toolBar;
    }
}
