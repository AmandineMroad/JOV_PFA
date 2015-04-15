/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import frametest.FenChoixFic;
import ihm.graphique.FenVisualisation;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Amandine
 */
public final class Gestionnaire {
    private File f;
    private boolean ready;
    private static Gestionnaire i = null;
    private FenChoixFic fchoix;
    private FenVisualisation fvisualisation;
    
    private Gestionnaire() 
    { 
        ready = false; 
        
    }
    
    public File getFile(){
        return f;
    }
    
    public  void setFile(File ff){
        f = ff;
        ready = true;
    }
    
    public static Gestionnaire getInstance() throws HeadlessException, IOException{
        if (i == null){
            i = new Gestionnaire();
        }
        
        return i;
    }
    
    public void choixF() throws HeadlessException, IOException
    {
        fchoix = new FenChoixFic();
    }
    
    public void visualisationF() throws IOException
    {
        fchoix.setVisible(false);
        
        fvisualisation = new FenVisualisation();
    }
    
    public boolean isReady(){
        return ready;
    }
    
    public FenVisualisation getFVisualisation()
    {
        return fvisualisation;
    }
    
    public FenChoixFic getFchoix()
    {
        return fchoix;
    }
}
