/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Simon
 */
public class MonInt implements Comparable<MonInt>
{   
   
    /*
        ATTRIBUTS
    */
    
    private int monInt;
    private String correspondance;
    private Shape forme;
    
    /*
        CONSTRUCTEUR
    */
    
    public MonInt()
    {
        
    }
    
    public MonInt(int monInt, String correspondance)
    {
        this.monInt = monInt;
        this.correspondance = correspondance;
        this.forme = new Rectangle2D.Double(200,200,String.valueOf(monInt).length()*10/*10*(Math.log10(monInt)+1)*/,20);
    }
    
    
    /*
        FONCTIONS
    */
    
    @Override
    public int compareTo(MonInt o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public void additionner(int i)
    {
        this.monInt = this.monInt + i;
    }
    
    public void soustraire(int i)
    {
        
    }
    
    public void multiplier(int i)
    {
        
    }
    
    public void diviser(int i)
    {
        
    }    
    
    public void modulo(int i)
    {
        
    }
    /* 
        GET-SET
    */

    public int getMonInt() {
        return monInt;
    }

    public void setMonInt(int monInt) {
        this.monInt = monInt;
    }       



    public String getCorrespondance() {
        return correspondance;
    }

    public void setCorrespondance(String correspondance) {
        this.correspondance = correspondance;
    }

    public Shape getForme() {
        return forme;
    }

    public void setForme(Shape forme) {
        this.forme = forme;
    }
}
