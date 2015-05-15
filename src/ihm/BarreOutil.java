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
    public BarreOutil(String name) {
        super(name);
        
        //ajout des boutons
        next = createToolBarButton(new ImageIcon(".\\src\\doc\\next.png"),//IDE
//        next = createToolBarButton(new ImageIcon("..\\src\\doc\\next.png"),//fichier.jar
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
        
        stop = createToolBarButton(new ImageIcon(".\\src\\doc\\stop.png"),//IDE
//        stop = createToolBarButton(new ImageIcon("..\\src\\doc\\stop.png"),//fichier.jar
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
        
        replay =createToolBarButton(new ImageIcon(".\\src\\doc\\replay.png"),//IDE
//        replay =createToolBarButton(new ImageIcon("..\\src\\doc\\replay.png"),//fichier.jar
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
