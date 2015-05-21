/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm;

import ihm.listeners.Lis_Execute;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import util.Gestionnaire;

/**
 *  Barre d'outil du JOV
 * @author Amandine
 */
public class BarreOutil extends JToolBar{
    JButton next;
    JButton stop;
    JButton replay;

    /**
     *  Créé et initialise la barre d'outils
     * @param name le titre de la fenêtre créée lorsque la barre d'outils est détachée
     * @see JToolBar
     */
    public BarreOutil(String name) throws IOException, URISyntaxException {
        super(name);
        
        //ajout des boutons
        String docPath = Gestionnaire.getInstance().getDocPath();
        String image = docPath + "next.png";
        next = createToolBarButton(new ImageIcon(image),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Gestionnaire gest = Gestionnaire.getInstance();
                            gest.getPanGraph().affichage();
                        } catch (IOException ex) {
                            System.err.println("ERREUR _ Next\n\t" + ex.toString());
                        }
                    }
                }, "Passer à l'instruction suivante");
        this.add(next);
        
        image = docPath + "stop.png";
        stop = createToolBarButton(new ImageIcon(image),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Gestionnaire gest = Gestionnaire.getInstance();
                            gest.getFVisualisation().getToolBar().disableNext();
                            gest.getPanGraph().affichageBoucle();
                        } catch (IOException ex) {
                            System.err.println("ERREUR_Stop\n\t" + ex.toString());
                        }
                    }
                }, "Aller à la fin et arrêter");
        this.add(stop);
        
        image = docPath + "replay.png";
        replay =createToolBarButton(new ImageIcon(image),//fichier.jar
                new Lis_Execute(), "Rejouer depuis le début");
        this.add(replay);
        
        this.add(new JToolBar.Separator());
        
        
        
    }
    
    private JButton createToolBarButton (Icon icone, ActionListener listener, String tip_text){
        JButton tmp = new JButton();
        tmp.setIcon(icone);
        tmp.addActionListener(listener);
        tmp.setToolTipText(tip_text);
        tmp.setBackground(Color.white);
        tmp.setFocusable(false);
        return tmp;
    }
    
    /**
     *  Désactive le bouton next
     */
    public void disableNext(){
        next.setEnabled(false);
    }
    
}
