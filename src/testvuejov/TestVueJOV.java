/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvuejov;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Simon
 */
public class TestVueJOV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        JFrame f = new JFrame();
        

        
        //Toolkit tk = Toolkit.getDefaultToolkit();
	//Dimension d = tk.getScreenSize();        
        //f.setSize(d);
        f.setBounds(0, 0, 850, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        
        f.setLayout(new BorderLayout(2, 2));
        //f.setExtendedState(Frame.MAXIMIZED_BOTH); 
      
        //PanneauCode pc = new PanneauCode(d);
        PanneauCode pc = new PanneauCode(new Dimension(400, 350));
        PanneauGraphique pg = new PanneauGraphique(pc);        
        
        f.add(pc, BorderLayout.WEST);
        f.add(pg, BorderLayout.CENTER);
        
        pg.affichage();
        pg.repaint();
        f.setVisible(true);  
    }
    
    

    
}
