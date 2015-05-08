/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

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
    
    // Contiens la valeur de l'entier primitif du CS
    private int monInt;
    
    // Contiens le nom de la variable associé à l'objet MonInt
    private String correspondance;
    
    // Permet la représentation graphique de l'objet
    private Shape forme;
    
    // Permet de détecter si l'élément est modifié
    private boolean modif;
	private boolean used;
    
    //Précise si c'est un élément de tableau
    private final boolean tabVal;
    
    
    /**
     * Constucteur initialisant tous les attributs de classe
     * @param monInt
     * @param correspondance
     * @param forme 
     */
    public MonInt(int monInt, String correspondance, Shape forme)
    {
        this.monInt = monInt;
        this.correspondance = correspondance;
        this.forme = forme;
        this.modif = true;
        this.tabVal = false;
		this.used = false;
    }
    
    /**
     * Constucteur initialisant tous les attributs de classe
     * @param monInt
     * @param correspondance
     * @param forme 
     */
    public MonInt(int monInt, String correspondance, Shape forme, boolean isTabValue)
    {
        this.monInt = monInt;
        this.correspondance = correspondance;
        this.forme = forme;
        this.modif = true;
        this.tabVal = isTabValue;
		this.used = false;
    }
    /**
     * Redefinition de la fonction compareTo() de l'interface Comparable
     * @param o
     * @return 
     */
    @Override
    public int compareTo(MonInt o) { //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    /**
     * Permet d'aditionner deux objet MonInt
     * @param i 
     */
    public void additionner(int i)
    {
        this.monInt = this.monInt + i;
        this.modif = true;
    }
    
    /**
     * 
     * @param i 
     */
    public void soustraire(int i)
    {
        
    }
    
    /**
     * 
     * @param i 
     */
    public void multiplier(int i)
    {
        
    }
    
    /**
     * 
     * @param i 
     */
    public void diviser(int i)
    {
        
    }    
    
    /**
     * 
     * @param i 
     */
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
        modif = true;
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

    public boolean isTabValue() {
        return tabVal;
    }
    
	public boolean isModified(){
        return modif;
    }
    
    public void setModified(boolean b){
        modif = b;
    }
    
    public boolean isUsed(){
        return used;
    }
    
public void setUsed(boolean b){
        used=b;
    }    

}
