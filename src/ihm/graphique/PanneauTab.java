/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.Color;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 *
 * @author Amandine
 */
public class PanneauTab extends JPanel{
    public PanneauTab(Point position, int width, int height, String tabName){
        this.setBounds(position.x, position.y, width, height);
        this.setBorder(BorderFactory.createTitledBorder(tabName));
    }

    
}