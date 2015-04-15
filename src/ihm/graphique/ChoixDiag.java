/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import ihm.listeners.ExitJOV;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.Gestionnaire;
import util.OpenReadMe;

/**
 *
 * @author Amandine
 */
public class ChoixDiag extends JDialog{
    JButton boutonParcourir;
    JButton boutonReadMe;
    JButton exec;
    JTextField pathField;
    boolean ok;
    
    public ChoixDiag() {
        this.setTitle("Java Object Viewer - Choix du fichier");
    
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();    
        this.setBounds((int)(d.getWidth()/2)-250,(int) (d.getHeight()/2)-100 , 600, 200);
        
        
         pathField = new JTextField(30);
        pathField.setEditable(false);
        
        ok = false;
        boutonParcourir = new JButton("Parcourir");
        boutonParcourir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ok = false;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier JAVA", "java");
                chooser.addChoosableFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                pathField.setText(chooser.getSelectedFile().getAbsolutePath());
                ok = true;
                }
            }
        });
    
        
          boutonReadMe = new JButton("Consulter le ReadMe");
        boutonReadMe.addActionListener(new OpenReadMe());
        
        exec = new JButton("Démarrer la visualisation");
        exec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 System.out.println("bouton exec\naction performed : ok = "+ok);
                if (ok){
                    Gestionnaire g;
                    try {
                        g = Gestionnaire.getInstance();
                        g.setFileAndExecute(new File(pathField.getText()));
                    } catch (IOException ex) {
                        System.out.println("ERREUR GESTIONNAIRE");
                        Logger.getLogger(ChoixDiag.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Vous devez séléctionner un fichier.", "Java Object Viewer - ERREUR", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });
        
        
        JLabel lab = new JLabel("Veuillez séléctionner un fichier");
        JLabel consigne= new JLabel("Consignes: Fichier *.java; votre fichier doit être formaté comme décrit dans le readMe.txt.");
        
        
      //  Container c = this.getContentPane();
        JPanel pan = new JPanel();
        
        pan.add(lab);
        pan.add(pathField); 
        pan.add(boutonParcourir);
        pan.add(consigne);
        pan.add(boutonReadMe);
        pan.add(exec);
        
        
    
    this.add(pan);
    this.addWindowListener(new ExitJOV());
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
   // this.setVisible(true);
        
        
        
    }
    
}
