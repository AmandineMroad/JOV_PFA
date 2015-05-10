/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm.graphique;

import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * Panneau pour affichage tableaux
 * @author Amandine
 */
public class PanneauTab extends JPanel{
    /**
     * Constructeur
     * @param position position de l'angle supérieur gauche
     * @param width largeur
     * @param height longueur
     * @param tabName nom du tableau
     */
    public PanneauTab(Point position, int width, int height, String tabName){
        this.setBounds(position.x, position.y, width, height);
        this.setBorder(BorderFactory.createTitledBorder(tabName));
    }

    
}
