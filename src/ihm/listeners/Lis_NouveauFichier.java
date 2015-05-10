/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
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
 * Listener: ouvrir un nouveau fichier
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
