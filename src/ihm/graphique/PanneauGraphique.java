/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import util.Utilitaire;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.text.BadLocationException;
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
    RepaintManager rm;

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
        rm = RepaintManager.currentManager(this);
        utilitaire = new Utilitaire(pc.getD(), f);
    }

    /**
     * Redefinition de la fonction paintComponent
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        // System.out.println("Appel PanneauGraphique.paintComponent()");
        super.paintComponent(g);
        JPanel pan;
        int size = utilitaire.getMesInt().size();
        this.removeAll();

        int marge = 5;
        Point position = new Point(marge, marge);
        int newX, newY;
        /* Boucle d'affichage */
        MonInt mi;
        for (int i = 0; i < size; i++) {
            mi = utilitaire.getMesInt().get(i);
            /* Affichage tableau */
            if (mi.isTabValue()) {
                int nb_val = 0;
                ArrayList<MonInt> list = new ArrayList();

                String[] tabName;
                tabName = mi.getCorrespondance().split("\\[");
                String tabName_tmp = tabName[0];

                //Extraction de la taille et du nom du tableau
                while (mi.isTabValue() && i < size && tabName_tmp.equals(tabName[0])) { //verif nom
                    nb_val++;
                    i++;
                    list.add(mi);
                    if (i < size) {
                        mi = utilitaire.getMesInt().get(i);
                        tabName_tmp = mi.getCorrespondance().split("\\[")[0];
                    }
                }
                i--;

                //Dimensionnement et placement du panneau
                int lignes = ((nb_val * PanneauVariable.DEFAULT_WIDTH) / this.getWidth()) + 1;
                Point tmp_position = new Point();

                if (position.x <= marge) {
                    tmp_position.setLocation(0, position.y);
                } else {
                    position.setLocation(0, position.y + PanneauVariable.DEFAULT_HEIGHT + marge);
                }
                marge = 15;

                pan = new PanneauTab(position, this.getWidth(), (lignes * PanneauVariable.DEFAULT_HEIGHT) + ((lignes + 1) * marge), tabName[0]);

                //Insertion des valeurs
                PanneauVariable tmp_panVar;
                int tmp_x = marge, tmp_y = marge + 10;
                tmp_position.setLocation(marge, marge + 10);

                for (MonInt myInt : list) {
                    tmp_panVar = new PanneauVariable(myInt);
                    tmp_panVar.setBounds(tmp_x, tmp_y, tmp_panVar.getWidth(), tmp_panVar.getHeight());
                    tmp_x = tmp_position.x + tmp_panVar.getWidth();
                    tmp_y = tmp_position.y;
                    if (tmp_x + tmp_panVar.getWidth() > this.getWidth()) {
                        tmp_y = tmp_position.y + tmp_panVar.getHeight() + marge;
                        tmp_x = marge;
                    }
                    tmp_position.setLocation(tmp_x, tmp_y);
                    pan.add(tmp_panVar);
                }

                //Repositionnement pour prochain objet à afficher
                newY = position.y + pan.getHeight() + marge;
                position.setLocation(0, newY);
            } /* Affichage variable */ else {
                marge = 5;
                pan = new PanneauVariable(mi);
                pan.setBounds(position.x + marge, position.y, pan.getWidth(), pan.getHeight());

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
            affichage();
            try {
                // System.out.println("GO TO SLEEP");
                Thread.sleep(1000);
                //System.out.println("Réveillé");
            } catch (InterruptedException ex) {
                System.err.println("ERREUR !!! Don't want to sleep");
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Traite et affiche la ligne courante
     *
     * @throws java.io.IOException
     */
    public void affichage() throws IOException {
        int totalLigne = utilitaire.getNbLignes();

        boolean regex = utilitaire.execution();
        afficheLigne();

        //Tant que utilitaire.execution() renvoie false (id. la ligne ne correspond à aucun regex)
        while (!regex && ligneCourante < totalLigne) {
            regex = utilitaire.execution();
        }

        if (ligneCourante == totalLigne) {
            Gestionnaire gest = Gestionnaire.getInstance();
            gest.getFVisualisation().getToolBar().disableNext();
        }
        rm.paintDirtyRegions();
    }

    /**
     * Affiche la ligne courante sur le panneau code
     */
    private int ligne_tmp;
    public void afficheLigne() {
        if (ligneCourante < utilitaire.getNbLignes()) {
            if (utilitaire.isFirstWhile()) {
                ligne_tmp = ligneCourante;
                utilitaire.CancelFirstWhile();
            }
            //TODO ajouter test pour créer uniquement si besoin
            int while_size = utilitaire.getWhileSize() + 2; //ajout accolade + ligne condition
            boolean affiche = (utilitaire.isWhileLine()) && (ligneCourante < ligne_tmp + while_size);
            
//            System.out.println("affiche = " + affiche 
//                    + "\n\tligne courante = " + ligneCourante
//                    + "\n\tligne tmp = "+ligne_tmp 
//                    + "\n\twhile size = " + utilitaire.getWhileSize());
//            
            if (!utilitaire.isWhileLine() || affiche) {
                //System.out.println("affiche ligne alpha at: " + ligneCourante);
                JTextArea zoneCode = pc.getZoneCode();
                zoneCode.setText(zoneCode.getText() + "\n" + pc.getLignes().get(ligneCourante));
                highLightLine(ligneCourante);
                ligneCourante++;
            } else {
                int ind = utilitaire.getIndWhile();
                int tmp = ligne_tmp + ind;
                System.out.println("affiche ligne beta at: " + tmp);
                highLightLine(tmp);
            }
            this.repaint();
            rm.isCompletelyDirty(this);

        }
    }
    
    /**
     * Fait apparaitre la ligne <i>line number</i> en surbrillance dans la zone de texte du PanneauCode
     * @param lineNumber la ligne à surligner
     */
    
    public void highLightLine(int lineNumber) {
        JTextArea zoneCode = pc.getZoneCode();
        pc.getHighLighter().removeAllHighlights();
        //System.out.println("HIGHLIGHT line : "+lineNumber);
        try {
            pc.getHighLighter().addHighlight(zoneCode.getLineStartOffset(lineNumber + 1), zoneCode.getLineEndOffset(lineNumber + 1), pc.getHlPainter());
        } catch (BadLocationException e) {
            System.err.println("HighLight failed");
        }
    }

}
