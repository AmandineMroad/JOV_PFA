/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import ihm.Menu;
import ihm.graphique.PanneauCode;
import java.awt.Color;
import util.Utilitaire;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
import util.Gestionnaire;

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
     *
     * @param pc
     * @throws IOException
     */
    public PanneauGraphique(PanneauCode pc) throws IOException {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        this.pc = pc;
        utilitaire = new Utilitaire(pc.getD());
    }

    public PanneauGraphique(PanneauCode pc, File f) throws IOException {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.pc = pc;
        utilitaire = new Utilitaire(pc.getD(), f);
    }

    /**
     * Redefinition de la fonction paintComponent
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        PanneauVariable pan;
        int size = utilitaire.getMesInt().size();
        this.removeAll();

        int marge = 5;
        Point position = new Point(5, 5);
        int newX, newY;

        for (int i = 0; i < size; i++) {
            pan = new PanneauVariable(utilitaire.getMesInt().get(i));
            pan.setBounds(position.x, position.y, pan.getWidth(), pan.getHeight());

            newX = position.x + pan.getWidth();
            newY = position.y;

            if (newX + pan.getWidth() > this.getWidth()) {
                newY = position.y + pan.getHeight() + marge;
                newX = 0;
            }
            position.setLocation(newX + marge, newY);
            this.add(pan);
        }

    }

    /**
     * Permet de realiser l'affichage du CS sur le panneauCode et l'affichage
     * des objet monInt en parralele sur le panneauGraphique
     *
     * @throws IOException
     */
    /*  public void affichage() throws IOException {
     for (int i = 0; i < utilitaire.getNbLignes(); i++) {
     utilitaire.execution();
     pc.getZoneCode().setText(pc.getZoneCode().getText() + "\n" + pc.getLignes().get(i));
     this.repaint();
            
     try {
     Thread.sleep(1000);
     } catch (InterruptedException ex) {
     Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
     }   
             
     }
        
     }
     }*/
    int ligneCourante = 0;

    public void affichageBoucle() throws IOException {
        for (int i = ligneCourante; i < utilitaire.getNbLignes(); i++) {
            utilitaire.execution();
            pc.getZoneCode().setText(pc.getZoneCode().getText() + "\n" + pc.getLignes().get(i));
            this.repaint();
        }
    
    }
    
    

    

    public void affichage() {
        if (ligneCourante < utilitaire.getNbLignes()) {
            System.out.println("PG_ Affichage()");
            try {
                utilitaire.execution();
            } catch (IOException ex) {
                System.out.println("ERREUR Affichage()");
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
            pc.getZoneCode().setText(pc.getZoneCode().getText() + "\n" + pc.getLignes().get(ligneCourante));
            ligneCourante++;
            this.repaint();
        } else {
            try {
                //disable nextButton
                Gestionnaire gest = Gestionnaire.getInstance();
                gest.getFVisualisation().getMenu().disableNext();
            } catch (IOException ex) {
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
