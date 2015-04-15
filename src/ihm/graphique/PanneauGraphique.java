/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import ihm.graphique.PanneauCode;
import util.Utilitaire;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Simon
 */
public class PanneauGraphique extends JPanel {

    // Contient un Utilitaire pour le traitement du fichier source
    private Utilitaire utilitaire;
    
    // Posséde un PanneauCode pour gérer à partir du PanneauGraphique l'affiche des lignes du fichier source
    private PanneauCode pc;
    
    /**
     * Constructeur recevant simplement un PanneauCode en parametre
     * @param pc
     * @throws IOException 
     */
    public PanneauGraphique(PanneauCode pc) throws IOException 
    {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        this.pc = pc;
        utilitaire = new Utilitaire(pc.getD());
    }
    
    /**
     * Redefinition de la fonction paintComponent
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) 
    { 
        super.paintComponent(g); 
	Graphics2D  g2  =  (Graphics2D)  g;   
        
        for(int i=0; i<utilitaire.getMesInt().size(); i++)
        {
           g2.draw(utilitaire.getMesInt().get(i).getForme());
           g2.drawString(String.valueOf(utilitaire.getMesInt().get(i).getMonInt()), (int) (utilitaire.getMesInt().get(i).getForme().getBounds().getX()+2), (int) (utilitaire.getMesInt().get(i).getForme().getBounds().getY()+15));
           g2.drawString(utilitaire.getMesInt().get(i).getCorrespondance(), (int) (utilitaire.getMesInt().get(i).getForme().getBounds().getX()-5), (int) (utilitaire.getMesInt().get(i).getForme().getBounds().getY()-5));
        }         
    }
    
    
    /**
     * Permet de realiser l'affichage du CS sur le panneauCode et l'affichage des objet monInt en parralele sur 
     * le panneauGraphique
     * @throws IOException 
     */
    public void affichage() throws IOException
    {
        for(int i=0; i<utilitaire.getNbLignes(); i++)
        {
            utilitaire.execution();
            pc.getZoneCode().setText(pc.getZoneCode().getText()+"\n"+pc.getLignes().get(i));
            this.repaint();
            /*
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }   
            */         
        }        
    }
}
