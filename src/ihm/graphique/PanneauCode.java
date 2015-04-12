/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Simon
 */
public class PanneauCode extends JPanel
{

    FileReader fr;
    BufferedReader br;
    final String nomF = "CODE.java";    
    TextArea zoneCode;
    ArrayList<String> lignes;
    //JTextPane zc;
    
    public PanneauCode(Dimension d) throws FileNotFoundException, IOException
    {
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        zoneCode = new TextArea();
        zoneCode.setEditable(false);
        zoneCode.setBackground(Color.WHITE);
        lignes = new ArrayList<>();
        this.add(zoneCode);
        
        /*zc = new JTextPane();
         zc.setPreferredSize(new Dimension(400, 350));
        this.add(zc);
        */
        
        
        fr = new FileReader(nomF);
        br = new BufferedReader(fr);
        //zoneCode.setPreferredSize(new Dimension(d.width/3, d.height));
        zoneCode.setPreferredSize(new Dimension(400, 350));
       
        enregistrement();
    }
    
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

    public TextArea getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(TextArea zoneCode) {
        this.zoneCode = zoneCode;
    }

    public ArrayList<String> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<String> lignes) {
        this.lignes = lignes;
    }
    
    
    
    
}
