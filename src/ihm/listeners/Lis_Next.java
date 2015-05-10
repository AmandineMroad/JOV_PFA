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
 *  Listener: instruction suivante
 * @author Amandine
 */
public class Lis_Next implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Gestionnaire gest = Gestionnaire.getInstance();
            gest.getPanGraph().affichage();
        } catch (IOException ex) {
            Logger.getLogger(Lis_Next.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
