/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class Lis_OpenReadMe implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().open(new File(".\\src\\doc\\JOV_ReadMe.txt"));
                //Desktop.getDesktop().open(new File(".\\JOV_ReadMe.txt"));
            } catch (IOException ex) {
                System.out.println("Erreur open ReadMe");
                Logger.getLogger(Lis_OpenReadMe.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    

    
}
