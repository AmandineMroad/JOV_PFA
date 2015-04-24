/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
                System.out.println("Lis_execute : replay");
                g.execute();
            }
            else if (!textField.getText().isEmpty()){
                System.err.println("Lis_execute : elseIf; file = "+textField.getText());
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
