/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frametest;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
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
public class fenChoixFic extends JFrame{
    
    JPanel pan;
    
    
    JButton boutonParcourir;
    JButton boutonReadMe;
    JButton exec;
    JTextField pathField;
    boolean ok;
    
    public fenChoixFic() throws HeadlessException {
        this.setTitle("Java Object Viewer - Choix du fichier");
        this.setResizable(false);
    
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();    
        this.setBounds((int)(d.getWidth()/2)-250,(int) (d.getHeight()/2)-100 , 600, 200);
    
        pan = new JPanel();
        LayoutManager lm = pan.getLayout(); //TODO
        
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
                if (ok){
                    Gestionnaire g = Gestionnaire.getInstance();
                    g.setFile(new File(pathField.getText()));
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Vous devez séléctionner un fichier.", "Java Object Viewer - ERREUR", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });
        
        
        
        JLabel lab = new JLabel("Veuillez séléctionner un fichier");
        JLabel consigne= new JLabel("Consignes: Fichier *.java; votre fichier doit être formaté comme décrit dans le readMe.txt.");
        
        
        pan.add(lab);
        pan.add(pathField); 
        pan.add(boutonParcourir);
        pan.add(consigne);
        pan.add(boutonReadMe);
        pan.add(exec);
        
        
    
    this.add(pan);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    }
    
}
