/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Gestionnaire;

/**
 *
 * @author Amandine
 */
public class Lis_Next implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Lis_next()");
        try {
            Gestionnaire gest = Gestionnaire.getInstance();
            gest.getPanGraph().affichage();
        } catch (IOException ex) {
            System.out.println("ERREUR");//TODO
            // JOptionPane.showMessageDialog(root, "ERREUR FATALE", "ERREUR", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Lis_Next.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
