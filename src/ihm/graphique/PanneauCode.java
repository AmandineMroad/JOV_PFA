/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Simon
 */
public class PanneauCode extends JPanel
{
    /*
        ATTRIBUT
    */
    
    /** Permet d'ouvrir un fichier en lecture*/
    private FileReader fr;
    
    /** Permet de lire dans un fichier*/
    private BufferedReader br;
    
    /** Fichier à visualiser */
    private File fichier;
    
    /** Permet l'affichage du CS */
      private JTextArea zoneCode;
    
    /** Contient l'ensemble des lignes du code source*/
    private ArrayList<String> lignes;
    
    /** Dimension par défaut*/
    private Dimension d;
    
    /** Contient la zone de texte */
    private JScrollPane jsp;
    
    /** Outils pour surlignement des lignes */
    private Highlighter hl;
    /** Outils pour surlignement des lignes */
    private Highlighter.HighlightPainter hl_painter;
    
    /**
     * Constructeur
     * @param d : dimension 
     * @param f : fichier à visualiser
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public PanneauCode(Dimension d, File f) throws FileNotFoundException, IOException
    {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lignes = new ArrayList<>();
        this.setOpaque(true);
        zoneCode = new JTextArea();
        zoneCode.setEditable(false);
        zoneCode.setBackground(Color.WHITE);  
        zoneCode.setOpaque(true);
        hl = new DefaultHighlighter();
        hl_painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        zoneCode.setHighlighter(hl);
        
        jsp = new JScrollPane(zoneCode);
        jsp.setSize(d);
        jsp.setOpaque(true);
        this.add(jsp);
        
        
        this.d = d;
        this.d.width=this.d.width/3;
        
        this.fichier = f;
        
        fr = new FileReader(fichier);
        br = new BufferedReader(fr);
        
        enregistrement();
    }
    
    
    /**
     * Permet de lire les lignes dans le fichier source et de les ajouter à la suite dans l'AL<>
     * @throws IOException 
     */
    private void enregistrement() throws IOException
    {
        String tmp;
        while((tmp = br.readLine()) != null)
        {
            lignes.add(tmp);
        }        
    }

    
    /*
        GET - SET
    */

    /**
     *  Renvoie le file reader du panneau code
     * @return file reader
     */
    
    public FileReader getFr() {
        return fr;
    }

    /**
     * Change le file reader
     * @param fr le nouveau file reader
     */
    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    /**
     *
     * @return
     */
    public BufferedReader getBr() {
        return br;
    }

    /**
     *
     * @param br
     */
    public void setBr(BufferedReader br) {
        this.br = br;
    }
    
    /**
     *
     * @return
     */
    public JTextArea getZoneCode() {
        return zoneCode;
    }

    /**
     *
     * @param zoneCode
     */
    public void setZoneCode(JTextArea zoneCode) {
        this.zoneCode = zoneCode;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getLignes() {
        return lignes;
    }

    /**
     *
     * @param lignes
     */
    public void setLignes(ArrayList<String> lignes) {
        this.lignes = lignes;
    }

    /**
     *
     * @return
     */
    public Dimension getD() {
        return d;
    }

    /**
     *
     * @param d
     */
    public void setD(Dimension d) {
        this.d = d;
    }

    /**
     *
     * @return
     */
    public Highlighter getHighLighter () {
        return hl;
    }

    /**
     * Renvoie le HightLightPainter pour surlignement
     * @return
     */
    public Highlighter.HighlightPainter getHlPainter(){
         return hl_painter;
     }
    
    @Override
    protected void paintComponent(Graphics g) {
        this.setSize(this.getParent().getWidth() / 3, this.getParent().getHeight());
        int w = zoneCode.getWidth();
        int h = zoneCode.getHeight();
        int pcw = this.getWidth() - 10;
        int pch = this.getHeight() - 50;
        
        if (pcw > w) {  w = pcw;  }
        if (pch > h) {  h = pch;  }

        zoneCode.setSize(w, h);
        jsp.setSize(pcw, pch);

    }
    
    public void eraseLastLine(){
        String [] contenu = zoneCode.getText().split("\n");
        String s = "";
        for (int i = 0; i < contenu.length-1; i++){
            if (i != 0) s += "\n";
            s += contenu[i];
        }
        zoneCode.setText(s);
    }
}
