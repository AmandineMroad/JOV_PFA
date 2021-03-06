/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Gestionnaire;

/**
 * Ouvrir le fichier JOV_ReadMe.txt
 * @author Amandine
 */
public class Lis_OpenReadMe implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()){
            try {
//                Desktop.getDesktop().open(new File(".\\src\\doc\\JOV_ReadMe.txt")); //IDE

//                Desktop.getDesktop().open(new File("..\\src\\doc\\JOV_ReadMe.txt")); //fichier.jar

            String path = Gestionnaire.getInstance().getDocPath();
            path += "JOV_ReadMe.txt";  
                System.out.println("PATH = " + path);
                Desktop.getDesktop().open(new File(path));
                
            } catch (IOException ex) {
                System.err.println("Erreur open ReadMe");
                Logger.getLogger(Lis_OpenReadMe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Lis_OpenReadMe.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    

    
}
