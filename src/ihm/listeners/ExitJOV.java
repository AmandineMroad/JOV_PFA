/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Amandine
 */
public class ExitJOV extends WindowAdapter implements ActionListener{

    public void windowClosing(WindowEvent e) {
        int conf = JOptionPane.showConfirmDialog(e.getComponent(), "Vous allez quitter le programme.\nEtes-vous sûr?", "Arrêt du programme", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (conf == JOptionPane.YES_OPTION) {
            System.out.println("fermeture JOV");
            System.exit(conf);
        } 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int conf = JOptionPane.showConfirmDialog(null, "Vous allez quitter le programme.\nEtes-vous sûr?", "Arrêt du programme", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (conf == JOptionPane.YES_OPTION) {
            System.out.println("fermeture JOV");
            System.exit(conf);
        }
    }

}
