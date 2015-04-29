/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private ChoixDiag fchoix;
    private FenVisualisation fvisualisation;

    private Gestionnaire() throws IOException {
        ready = false;
        fchoix = new ChoixDiag();
        fchoix.setVisible(true);
    }

    public File getFile() {
        return f;
    }

    public void setFile(File ff) {
        f = ff;
        ready = true;
    }

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
            System.out.println("Gestionnaire.execute()");
            fchoix.dispose();
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
            System.out.println("ERREUR");//TODO
            JOptionPane.showMessageDialog(fvisualisation, "ERREUR FATALE", "ERREUR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static Gestionnaire getInstance() throws IOException {
        if (i == null) {
            i = new Gestionnaire();
        }

        return i;
    }

    public boolean isReady() {
        return ready;
    }

    public FenVisualisation getFVisualisation() {
        return fvisualisation;
    }

    public void reset() {
        f = null;
        ready = false;
    }

    public PanneauCode getPanCode() {
        return fvisualisation.getPc();
    }

    public PanneauGraphique getPanGraph() {
        return fvisualisation.getPg();
    }

    public ChoixDiag getChoixDiag() {
        return fchoix;
    }
}
