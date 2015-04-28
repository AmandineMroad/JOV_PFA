/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import traitement.MonInt;

/**
 *
 * @author Amandine
 */
public class PanneauVariable extends JPanel {

    MonInt mi;

    /**
     *
     */
    public static final int DEFAULT_WIDTH = 110;
    public static final int DEFAULT_HEIGHT = 40;
    /**
     * Constructeur 
     * @param monInt: l'objet MonInt à modéliser 
     */
    public PanneauVariable(MonInt monInt) {
        mi = monInt;
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        Color c = new Color( 255, 255, 255, 1);
        this.setBackground(c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (mi.isModified()){
           // System.out.println("PanneauVariable.paint: MODIF");
            g2.setColor(Color.red);
            mi.setModified(false);
            mi.setUsed(false);
        }
        else if (mi.isUsed()){
            g2.setColor(Color.blue);
            mi.setUsed(false);
        }
        g2.draw(mi.getForme());
        g2.drawString(String.valueOf(mi.getMonInt()), (int) (mi.getForme().getBounds().getX() + 2), (int) (mi.getForme().getBounds().getY() + 15));
        g2.drawString(mi.getCorrespondance(), (int) (mi.getForme().getBounds().getX() - 5), (int) (mi.getForme().getBounds().getY() - 5));
        

    }

}
