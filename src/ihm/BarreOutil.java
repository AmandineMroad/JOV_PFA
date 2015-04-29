/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import ihm.listeners.Lis_Execute;
import ihm.listeners.Lis_Next;
import ihm.listeners.Lis_Stop;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Amandine
 */
public class BarreOutil extends JToolBar{
    JButton next;
    JButton stop;
    JButton replay;

    public BarreOutil(String name) {
        super(name);
        
        //ajout des boutons
        next = createToolBarButton(new ImageIcon(".\\src\\doc\\next.png"),
                new Lis_Next(), "Passer à l'instruction suivante");
        this.add(next);
        
        stop = createToolBarButton(new ImageIcon(".\\src\\doc\\stop.png"),
                new Lis_Stop(), "Aller à la fin et arrêter");
        this.add(stop);
        
        replay =createToolBarButton(new ImageIcon(".\\src\\doc\\replay.png"),
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
    
    
    public void disableNext(){
        next.setEnabled(false);
    }
    
}
