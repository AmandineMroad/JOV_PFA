/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
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
    
    /**
     * 
     * @throws IOException 
     */
    public FenVisualisation() throws IOException
    {
        d = new Dimension(850, 400);
        this.setSize(d);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);      
        
        this.setLayout(new BorderLayout(2, 2));
      
        pc = new PanneauCode(d);
        pg = new PanneauGraphique(pc);        
        
        this.add(pc, BorderLayout.WEST);
        this.add(pg, BorderLayout.CENTER);
        
        this.setVisible(true);    
    }

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
}
