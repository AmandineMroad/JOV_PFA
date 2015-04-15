/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frametest;

import ihm.graphique.PanneauCode;
import ihm.graphique.PanneauGraphique;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
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
public class FenChoixFic extends JFrame {
    
    JPanel pchoix;
    /*PanneauCode pcode;
    PanneauGraphique pgraphique;*/
    
    JButton boutonParcourir;
    JButton boutonReadMe;
    JButton exec;
    JTextField pathField;
    boolean ok;
    
    public FenChoixFic() throws HeadlessException, IOException 
    {
        this.setTitle("Java Object Viewer - Choix du fichier");
        this.setResizable(false);
    
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();    
        this.setBounds((int)(d.getWidth()/2)-250,(int) (d.getHeight()/2)-100 , 600, 200);
    
        pchoix = new JPanel();
        /*pcode = new PanneauCode(new Dimension(400, 350));
        pgraphique = new PanneauGraphique(pcode);*/
        LayoutManager lm = pchoix.getLayout(); //TODO
        
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
                if (ok)
                {
                    try {
                        Gestionnaire g = Gestionnaire.getInstance();
                        g.setFile(new File(pathField.getText()));
                    } catch (HeadlessException ex) {
                        Logger.getLogger(FenChoixFic.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FenChoixFic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Vous devez séléctionner un fichier.", "Java Object Viewer - ERREUR", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });
        
        
        
        JLabel lab = new JLabel("Veuillez séléctionner un fichier");
        JLabel consigne= new JLabel("Consignes: Fichier *.java; votre fichier doit être formaté comme décrit dans le readMe.txt.");
        
        
        pchoix.add(lab);
        pchoix.add(pathField); 
        pchoix.add(boutonParcourir);
        pchoix.add(consigne);
        pchoix.add(boutonReadMe);
        pchoix.add(exec);
        
        
    
    this.add(pchoix);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    }
    
    
    /*private void switchPanneaux() throws IOException
    {
        this.setLayout(new BorderLayout(2, 2));
        this.remove(pchoix);
        this.add(pcode, BorderLayout.WEST);
        this.add(pgraphique, BorderLayout.CENTER);
        this.validate();
        this.setBounds(0, 0, 850, 400);
        this.setLocationRelativeTo(null);    
        this.setResizable(true);
        pgraphique.affichage();
        pgraphique.repaint(); 
        this.setVisible(true);
    }
    */
}
