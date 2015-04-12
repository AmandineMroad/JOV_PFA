/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

/**
 *
 * @author Amandine
 */
public final class Gestionnaire {
    private File f;
    private boolean ready;
    private static Gestionnaire i = null;
    
    private Gestionnaire(){ ready = false; }
    
    public File getFile(){
        return f;
    }
    
    public  void setFile(File ff){
        f = ff;
        ready = true;
    }
    
    public static Gestionnaire getInstance(){
        if (i == null){
            i = new Gestionnaire();
        }
        
        return i;
    }
    
    public boolean isReady(){
        return ready;
    }
    
}
