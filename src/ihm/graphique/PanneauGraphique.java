/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
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

    /**
     * Utilitaire pour le traitement du fichier source
     */
    private final Utilitaire utilitaire;

    /**
     * PanneauCode pour gérer à partir du PanneauGraphique l'affichage des
     * lignes du fichier source
     */
    private final PanneauCode pc;

    /**
     * ligne courante d'exécution
     */
    private int ligneCourante;

    /**
     * RepaintManager pour contrôle des repaint
     */
    RepaintManager rm;

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
        super.paintComponent(g);

        this.setSize(this.getParent().getWidth() * 2 / 3, this.getParent().getHeight());
        this.setLocation(this.getParent().getWidth() / 3, pc.getY());
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
                tmp_position.setLocation(tmp_x, tmp_y);

                for (MonInt myInt : list) {
                    tmp_panVar = new PanneauVariable(myInt);
                    tmp_panVar.setLocation(tmp_position);
                    pan.add(tmp_panVar);
                    
                    //Position du prochain panneau
                    tmp_x = tmp_position.x + tmp_panVar.getWidth();
                    if (tmp_x + tmp_panVar.getWidth() > this.getWidth()) {
                        tmp_y = tmp_position.y + tmp_panVar.getHeight() + marge;
                        tmp_x = marge;
                    }
                    tmp_position.setLocation(tmp_x, tmp_y);
                }

                //Repositionnement pour prochain objet à afficher
                newY = position.y + pan.getHeight() + marge;
                position.setLocation(5, newY);
            } /* Affichage variable */ else {
                marge = 5;
                pan = new PanneauVariable(mi);
                pan.setLocation(position);
                
                newX = position.x + pan.getWidth();
                newY = position.y;

                if (newX + pan.getWidth() > this.getWidth()) {
                    newY = position.y + pan.getHeight() + marge;
                    newX = marge;
                }
                position.setLocation(newX, newY);
            }
            this.add(pan);
        }
    }
    /**
     * Traite et affiche l'ensemble des instructions restantes
     *
     * @throws IOException
     */
    @SuppressWarnings("SleepWhileInLoop")
    public void affichageBoucle() throws IOException {
        while (ligneCourante < utilitaire.getNbLignes()) {
            affichage();
            try {                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("ERREUR !!! Don't want to sleep");
                Logger.getLogger(PanneauGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean regex = false;

    /**
     * Traite et affiche la ligne courante
     *
     * @throws java.io.IOException
     */
    public void affichage() throws IOException {
        int totalLigne = utilitaire.getNbLignes();

        regex = utilitaire.execution();
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

    private int ligne_tmp;

    /**
     * Affiche et/ou met en surbrillance la ligne couramment exécutée
     */

    public void afficheLigne() {
        if (ligneCourante < utilitaire.getNbLignes()) {
            boolean affiche = false;
            int while_size = 0;
            /* Affichage while */
            if (utilitaire.isWhileLine()) {
                /* premier passage dans le while */
                if (utilitaire.isFirstWhile()) {
                    ligne_tmp = ligneCourante -1;
                    utilitaire.CancelFirstWhile();
                }

                while_size = utilitaire.getWhileSize() + 2; //ajout accolade + ligne condition
                affiche = (ligneCourante < (ligne_tmp + while_size-1));

            }

            /* Affichage classique*/
            if (!utilitaire.isWhileLine() || affiche) {
                JTextArea zoneCode = pc.getZoneCode();
                zoneCode.setText(zoneCode.getText() + "\n" + pc.getLignes().get(ligneCourante));
                highLightLine(ligneCourante);
                ligneCourante++;
               
                if ((affiche) && (ligneCourante == ligne_tmp + while_size - 1)) {
                    zoneCode.setText(zoneCode.getText() + "\n" + pc.getLignes().get(ligneCourante));
                    highLightLine(ligneCourante - 1);
                }
                
            } else { /* boucle : surbrillance des lignes */
                int ind = utilitaire.getIndWhile();
                int tmp = ligne_tmp + ind;
                highLightLine(tmp);
            }
            /* Refresh forcé de l'affichage */
            if (regex) {
                try {
                    Gestionnaire.getInstance().getFVisualisation().getContentPane().repaint();
                } catch (IOException ex) {
                    Logger.getLogger(PanneauCode.class.getName()).log(Level.SEVERE, null, ex);
                }
                rm.isCompletelyDirty(this);
            }
        }
    }

    /**
     * Fait apparaitre la ligne <i>line number</i> en surbrillance dans la zone
     * de texte du PanneauCode
     *
     * @param lineNumber la ligne à surligner
     */
    private void highLightLine(int lineNumber) {
        JTextArea zoneCode = pc.getZoneCode();
        pc.getHighLighter().removeAllHighlights();
        try {
            pc.getHighLighter().addHighlight(zoneCode.getLineStartOffset(lineNumber + 1), zoneCode.getLineEndOffset(lineNumber + 1), pc.getHlPainter());
        } catch (BadLocationException e) {
            System.err.println("HighLight failed");
        }
        pc.repaint();
    }   
}
