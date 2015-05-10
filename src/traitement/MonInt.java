/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
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
    
    /** Valeur de l'entier primitif du CS*/
    private int monInt;
    
    /**Nom de la variable associé à l'objet MonInt*/
    private String correspondance;
    
    /** Permet la représentation graphique de l'objet*/
    private Shape forme;
    
    /** Permet de détecter si l'élément est modifié*/
    private boolean modif;
    /** Permet de détecter si l'élément est utilisé*/
    private boolean used;
    
    /**Précise si c'est un élément de tableau*/
    private final boolean tabVal;
    
    
    /**
     * Constucteur initialisant tous les attributs de classe
     * @param monInt    valeur de l'entier
     * @param correspondance    nom de la variable
     * @param forme représentation graphique de l'objet
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
     * @param isTabValue 
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
    /**
     * Renvoie la valeur de l'entier
     * @return 
     */
    public int getMonInt() {
        return monInt;
    }

    /**
     * Modifie la valeur de l'entier
     * @param monInt : la nouvelle valeur
     */
    public void setMonInt(int monInt) {
        this.monInt = monInt;
        modif = true;
    }       

    /**
     * Renvoie le nom de la variable
     * @return 
     */
    public String getCorrespondance() {
        return correspondance;
    }

    /**
     * Modifie le nom de la variable
     * @param correspondance : le nouveau nom
     */
    public void setCorrespondance(String correspondance) {
        this.correspondance = correspondance;
    }
    
    /**
     * Renvoie la forme associée à l'objet
     * @return 
     */
    public Shape getForme() {
        return forme;
    }

    /**
     * Indique si l'objet représente une case de tableau
     * @return true si c'est une case de tableau, false sinon
     */
    public boolean isTabValue() {
        return tabVal;
    }
    
    /**
     * Indique si l'objet a été modifié depuis le dernier affichage
     * @return true si l'objet a été modifié, false sinon
     */
	public boolean isModified(){
        return modif;
    }
    
    /**
     * Modifie la valeur du booleen modif
     * @param b : true si la valeur a été modifiée, false sinon
     */
    public void setModified(boolean b) {
        modif = b;
    }
    /**
     * Indique si l'objet a été utilisé depuis le dernier affichage
     * @return true si l'objet a été modifié, false sinn
     */
    public boolean isUsed(){
        return used;
    }
    /**
     * Modifie la valeur du booleen used
     * @param b : true si l'objet a été utilisé, false sinon
     */
    public void setUsed(boolean b){
        used=b;
    }    

}
