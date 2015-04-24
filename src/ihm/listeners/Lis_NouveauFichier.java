/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.listeners;

import ihm.graphique.FenVisualisation;
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
public class Lis_NouveauFichier implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Gestionnaire gest = Gestionnaire.getInstance();
            FenVisualisation fenetre = gest.getFVisualisation();
            fenetre.getPc().removeAll();
            fenetre.getPg().removeAll();
            fenetre.setVisible(false);
            gest.getChoixDiag().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Lis_NouveauFichier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
