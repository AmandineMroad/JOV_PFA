/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */

package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import util.Gestionnaire;

/**
 * Listener d'exécution
 * @author Amandine
 */
public class Lis_Execute implements ActionListener {

    private JTextField textField;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean replay;

    /**
     * Constructeur 
     * @param jtf : le JTextField de la fenêtre de choix de fichier
     */
    public Lis_Execute(JTextField jtf) {
        textField = jtf;
        replay = false;
    }
    /**
     * Constructeur
     */
    public Lis_Execute() {
        replay = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Gestionnaire g = Gestionnaire.getInstance();
            if (replay && g.isReady()){
                g.execute();
            }
            else if (!textField.getText().isEmpty()){
                g.setFileAndExecute(new File(textField.getText()));
            }
            else {
                JOptionPane.showMessageDialog(null, "Le gestionnaire ne contient aucun fichier.", "Java Object Viewer - ERREUR", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Lis_Execute.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
