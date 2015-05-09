/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextArea;
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
    
    // Permet d'ouvrir un fichier en lecture
    private FileReader fr;
    
    // Permet de lire dans un fichier
    private BufferedReader br;
    
    private final String nomF = "CODE.java";    
    private File fichier;
    // Permet l'affichage du CS 
  //  private TextArea zoneCode;
      private JTextArea zoneCode;
    
    // Contient l'ensemble des lignes du code source
    private ArrayList<String> lignes;
    
    
    private Dimension d;
    
    /**
     * Constucteur
     * @param d
     * @throws FileNotFoundException
     * @throws IOException 
     */
  /*  public PanneauCode(Dimension d) throws FileNotFoundException, IOException
    {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        zoneCode = new TextArea();
        zoneCode.setEditable(false);
        zoneCode.setBackground(Color.WHITE);
        lignes = new ArrayList<>();
        this.add(zoneCode);
        
        
        this.d = d;
        this.d.width=this.d.width/3;
        
        fr = new FileReader(nomF);
        br = new BufferedReader(fr);
        zoneCode.setPreferredSize(d);
       
        enregistrement();
    }*/
    
    JScrollPane jsp;
    Highlighter hl;
    Highlighter.HighlightPainter hl_painter;
    
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
      //  zoneCode = new TextArea();
        lignes = new ArrayList<>();
        
        zoneCode = new JTextArea();
        zoneCode.setEditable(false);
        zoneCode.setBackground(Color.WHITE);  
        
        hl = new DefaultHighlighter();
        hl_painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        zoneCode.setHighlighter(hl);
        
        
        
        jsp = new JScrollPane(zoneCode);
        
        
        
    //    Highlighter hl = new DefaultHighlighter();
    //    Highlighter.HighlightPainter hl_painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        this.add(jsp);
       // this.add(zoneCode);
        
        this.d = d;
        this.d.width=this.d.width/3;
        
        
        this.fichier = f;
        
        fr = new FileReader(fichier);
        br = new BufferedReader(fr);
     //   zoneCode.setPreferredSize(d);
       jsp.setPreferredSize(d);
        enregistrement();
    }
    
    
    /**
     * Permet de lire les lignes dans le fichier source et de les ajouter à la suite dans l'AL<>
     * @throws IOException 
     */
    private void enregistrement() throws IOException
    {
        String tmp;
        int i = 0;
        while((tmp = br.readLine()) != null)
        {
            lignes.add(tmp);
            i++;
        }        
    }

    
    /*
        GET - SET
    */
    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }
/*
    public TextArea getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(TextArea zoneCode) {
        this.zoneCode = zoneCode;
    }
*/
    
    
    public JTextArea getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(JTextArea zoneCode) {
        this.zoneCode = zoneCode;
    }

    
    public ArrayList<String> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<String> lignes) {
        this.lignes = lignes;
    }

    public Dimension getD() {
        return d;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public Highlighter getHighLighter () {
        return hl;
    }
     public Highlighter.HighlightPainter getHlPainter(){
         return hl_painter;
     }
    
    @Override
    protected void paintComponent(Graphics g) {
        zoneCode.setSize(zoneCode.getWidth(), this.getHeight()- 50);   
        jsp.setSize(jsp.getWidth(), this.getHeight() -50);
    }
}
