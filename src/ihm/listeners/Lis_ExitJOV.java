/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 * Listener : fermeture de l'application
 * @author Amandine
 */
public class Lis_ExitJOV extends WindowAdapter implements ActionListener{

    public void windowClosing(WindowEvent e) {
        int conf = JOptionPane.showConfirmDialog(e.getComponent(), "Vous allez quitter le programme.\nEtes-vous sûr?", "Arrêt du programme", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (conf == JOptionPane.YES_OPTION) {
            System.exit(conf);
        } 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int conf = JOptionPane.showConfirmDialog(null, "Vous allez quitter le programme.\nEtes-vous sûr?", "Arrêt du programme", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (conf == JOptionPane.YES_OPTION) {
            System.exit(conf);
        }
    }

}
