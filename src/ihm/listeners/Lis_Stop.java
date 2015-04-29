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
import util.Gestionnaire;

/**
 *
 * @author Amandine
 */
public class Lis_Stop implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
         System.out.println("Lis_stop()");
        try {
            Gestionnaire gest = Gestionnaire.getInstance();
            gest.getFVisualisation().getToolBar().disableNext();
            gest.getPanGraph().affichageBoucle();
        } catch (IOException ex) {
            System.out.println("ERREUR");//TODO
            Logger.getLogger(Lis_Next.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
