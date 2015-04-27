/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Amandine
 */
public class PanneauTransition extends JPanel{

    public PanneauTransition(){
        Color c = new Color( 255, 255, 255, 1);
        this.setBackground(c);
        //this.setBackground(Color.BLUE);
    }
    
    public PanneauTransition(Dimension dim) {
        this();
        this.setSize(dim);
        
    }
    
    
}
