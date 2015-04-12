/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvuejov;

import java.awt.Shape;

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
    
    public MonInt(int monInt, String correspondance, Shape forme)
    {
        this.monInt = monInt;
        this.correspondance = correspondance;
        this.forme = forme;
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
    
    public void division(int i)
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
