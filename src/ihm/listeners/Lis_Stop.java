/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
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
        try {
            Gestionnaire gest = Gestionnaire.getInstance();
            gest.getFVisualisation().getToolBar().disableNext();
            gest.getPanGraph().affichageBoucle();
        } catch (IOException ex) {
            System.err.println("ERREUR_Stop");
            Logger.getLogger(Lis_Next.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
