/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.Color;
import util.Utilitaire;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import traitement.MonInt;
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

    private int ligneCourante;

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

    /**
     * Constructeur du panneau graphique
     *
     * @param pc : le panneau code auquel il est attaché
     * @param f : le fichier source à visualiser
     * @throws IOException
     */
    public PanneauGraphique(PanneauCode pc, File f) throws IOException {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.pc = pc;
        ligneCourante = 0;
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
        // PanneauVariable pan;
        JPanel pan;
        int size = utilitaire.getMesInt().size();
        this.removeAll();

        int marge = 5;
        Point position = new Point(marge, marge);
        int newX, newY;

        MonInt mi;
        for (int i = 0; i < size; i++) {
            mi = utilitaire.getMesInt().get(i);
            if (mi.isTabValue()) {
                int nb_val = 0;
                ArrayList<MonInt> list = new ArrayList();
                String[] tabName;
                tabName = mi.getCorrespondance().split("\\[");
                while (mi.isTabValue() && i<size && (mi.getCorrespondance().split("\\[")[0]).equals(tabName)) { //verif nom
                    nb_val++;
                    i++;
                    list.add(mi);
                    if (i<size)
                        mi = utilitaire.getMesInt().get(i);
                }
                
                int lignes = ((nb_val * PanneauVariable.DEFAULT_WIDTH) / this.getWidth()) + 1;
                if(position.x <=marge)  position.setLocation(0,position.y);
                else    position.setLocation(0, position.y + PanneauVariable.DEFAULT_HEIGHT + marge);
                marge = 15;
                
                pan = new PanneauTab(position, this.getWidth(), (lignes * PanneauVariable.DEFAULT_HEIGHT) + ((lignes + 1) * marge), Color.pink);
                PanneauVariable tmp_panVar;
                int tmp_x=marge, tmp_y=marge;
                position.setLocation(marge, marge);
               
                for (int j = 0; j < list.size(); j++) {
                    tmp_panVar = new PanneauVariable(list.get(j));
                    tmp_panVar.setBounds(tmp_x, tmp_y, tmp_panVar.getWidth(), tmp_panVar.getHeight());
                    
                    tmp_x = position.x + tmp_panVar.getWidth();
                    tmp_y = position.y;
                    if (tmp_x + tmp_panVar.getWidth() > this.getWidth()) {
                        tmp_y = position.y + tmp_panVar.getHeight() + marge;
                        tmp_x = marge;
                    }
                    position.setLocation(tmp_x, tmp_y);
                    pan.add(tmp_panVar);                    
                }
                newY = position.y + PanneauVariable.DEFAULT_HEIGHT + marge;
                position.setLocation(0, newY);
            } else {
                marge = 5;
                pan = new PanneauVariable(mi);
                pan.setBounds(position.x, position.y, pan.getWidth(), pan.getHeight());

                newX = position.x + pan.getWidth();
                newY = position.y;

                if (newX + pan.getWidth() > this.getWidth()) {
                    newY = position.y + pan.getHeight() + marge;
                    newX = 0;
                }
                position.setLocation(newX + marge, newY);
            }
            this.add(pan);

        }

    }

    /**
     * Traite et affiche l'ensemble des instructions restantes
     *
     * @throws IOException
     */
    public void affichageBoucle() throws IOException {
        for (int i = ligneCourante; i < utilitaire.getNbLignes(); i++) {
            utilitaire.execution();
            this.afficheLigne();
        }

    }

    /**
     * Traite et affiche la ligne courante
     */
    public void affichage() {
      //  System.out.println("PG_ Affichage() ligne courante = " + ligneCourante + "/" + utilitaire.getNbLignes());
        if (ligneCourante < utilitaire.getNbLignes()) {
            //System.out.println("PG_ Affichage() dans le if");
            try {
                utilitaire.execution();
            } catch (IOException ex) {
                System.out.println("ERREUR Affichage()");
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.afficheLigne();
        }

        if (ligneCourante == utilitaire.getNbLignes()) {
            try {
                //disable nextButton
                Gestionnaire gest = Gestionnaire.getInstance();
                gest.getFVisualisation().getMenu().disableNext();
            } catch (IOException ex) {
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      //  System.out.println("Fin affichage: ligne courante = " + ligneCourante);
    }

    /**
     * Affiche la ligne courante sur le panneau code
     */
    public void afficheLigne() {
        if (ligneCourante < utilitaire.getNbLignes()) {
            pc.getZoneCode().setText(pc.getZoneCode().getText() + "\n" + pc.getLignes().get(ligneCourante));
            ligneCourante++;
            this.repaint();
        }
    }

}
