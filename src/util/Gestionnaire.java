/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package util;

import ihm.graphique.ChoixDiag;
import ihm.graphique.FenVisualisation;
import ihm.graphique.PanneauCode;
import ihm.graphique.PanneauGraphique;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Amandine
 */
public final class Gestionnaire {

    private File f;
    private boolean ready;
    private static Gestionnaire i = null;
    private final ChoixDiag fchoix;
    private FenVisualisation fvisualisation;

    private Gestionnaire() throws IOException {
        ready = false;
        fchoix = new ChoixDiag();
        fchoix.setVisible(true);
    }
    
    /**
     * prépare le gestionnaire pour l'exécution
     * @param ff : le fichier à visualiser
     * @throws IOException 
     */
    public void setFileAndExecute(File ff) throws IOException {
        f = ff;
        ready = true;
        this.execute();
    }

    /**
     * Masque la fenetre de choix de fichier, initialise la fenetre de
     * visualisation et lance l'exécution
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        if (ready) {
            fchoix.dispose();
            
            // Cas du replay
            if (fvisualisation != null) {
                FenVisualisation fen_tmp = fvisualisation;
                fen_tmp.dispose();
                fen_tmp = null;
                System.gc(); //garbage collector
            }

            fvisualisation = new FenVisualisation(f);
            fvisualisation.setVisible(true);
            fvisualisation.setExtendedState(FenVisualisation.MAXIMIZED_BOTH);
            fvisualisation.getPg().affichage();

        } else {
            JOptionPane.showMessageDialog(fvisualisation, "ERREUR FATALE \n Gestionnaire.execute()", "ERREUR", JOptionPane.ERROR_MESSAGE);
            fchoix.setVisible(true);
        }
    }
    /**
     * Renvoie le gestionnaire
     * Gestionnaire est un singleton
     * @return l'unique instance de Gestionnaire
     * @throws IOException 
     */
    public static Gestionnaire getInstance() throws IOException {
        if (i == null) {
            i = new Gestionnaire();
        }

        return i;
    }
    /**
     * Indique si le gestionnaire est prêt pour l'exécution
     * @return true s'il est prêt, false sinon
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Renvoie la fenetre principale du programme
     * @return 
     */
    public FenVisualisation getFVisualisation() {
        return fvisualisation;
    }

    /**
     * Renvoie le panneau graphique
     * @return 
     */
    public PanneauGraphique getPanGraph() {
        return fvisualisation.getPg();
    }

    /**
     * renvoie la boite de dialogue de choix de fichier
     * @return 
     */
    public ChoixDiag getChoixDiag() {
        return fchoix;
    }
}
