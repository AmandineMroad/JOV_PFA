/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvuejov;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class Utilitaire 
{

    /*
        ATTRIBUTS
    */
    
    private Parser parser;
    ArrayList<MonInt> mesInt;
    FileReader fr;
    BufferedReader br;   
    final String nomF = "CODE.java";    
    int nbLignes;
    /*
        CONSTRUCTEUR
    */
    
    public Utilitaire() throws FileNotFoundException, IOException
    {
        parser = new Parser();
        mesInt = new ArrayList<>();        
        //fr = null;
        //br = null;
        fr = new FileReader(nomF);
        br = new BufferedReader(fr);
        NombreLignes();
    }
    
    /*
        FONCTIONS
    */
    
    public void NombreLignes() throws IOException
    {
        while(br.readLine() != null)
            nbLignes++;
        
        br.close();
        fr.close();
        
        fr = new FileReader(nomF);
        br = new BufferedReader(fr);        
    }
   
    
    public void execution() throws FileNotFoundException, IOException
    {
        String tmp;
        String s[];
        String vrb;
        String operation;
        
        int tmp2;
        int tmp3;
        int tmp4;
        int res;
        
        if((tmp = br.readLine()) != null)
        {
            res = parser.correspondRegex(tmp);
            if(res!=-1)
                switch(res)
                {
                    case 0: // INSTANCIATION
                        mesInt.add(new MonInt(0, parser.extraireVariable(tmp))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 1: // INITIALISATION_ENTIER_SIMPLE
                        mesInt.add(new MonInt(parser.extraireValeur(tmp), parser.extraireVariable(tmp))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 2: // INITIALISATION_VARIABLE_SIMPLE
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        operation = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));
                        if((tmp2 = rechercheObjet(operation))!=-1)
                                mesInt.add(new MonInt(mesInt.get(tmp2).getMonInt(), vrb)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 3: // AFFECTATION_ENTIER_SIMPLE
                        if((tmp2 = rechercheObjet(parser.extraireVariable(tmp)))!=-1)
                                mesInt.get(tmp2).setMonInt(parser.extraireValeur(tmp));
                        break;
                    case 4: // AFFECTATION_VARIABLE_SIMPLE
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        operation = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));
                        if((tmp2 = rechercheObjet(vrb))!=-1)
                            if((tmp3 = rechercheObjet(operation))!=-1)
                               mesInt.get(tmp2).setMonInt( mesInt.get(tmp3).getMonInt());
                        break;
                    case 5: // AFFECTATION_ENTIER_DOUBLE
                            if((tmp2 =rechercheObjet(parser.extraireVariable(tmp)))!=-1)
                            {
                                if(parser.extraireOperation(tmp).equals("+"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 0));
                                else if(parser.extraireOperation(tmp).equals("-"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 1));
                                else if(parser.extraireOperation(tmp).equals("*"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 2));
                                else if(parser.extraireOperation(tmp).equals("/"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 3));                                
                                else if(parser.extraireOperation(tmp).equals("%"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 4));                                     
                            }   
                        break;
                    case 6: // AFFECTATION_VARIABLE_DOUBLE
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        if((tmp2 =rechercheObjet(vrb))!=-1)
                        {
                            operation = tmp.substring(tmp.indexOf("=")+1).replace(";", "").replaceAll("\\s", "");;
                                if(parser.extraireOperation(tmp).equals("+"))
                                {
                                    s = operation.split("\\+");
                                    if((tmp3=rechercheObjet(parser.extraireVariable(s[0]))) != -1 && (tmp4=rechercheObjet(parser.extraireVariable(s[1])))!=-1)
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt()+mesInt.get(tmp4).getMonInt());
                                }  
                        }
                        break;
                }           
        }
    }
    
    public int rechercheObjet(String vb)
    {
        for(int i=0; i<mesInt.size(); i++)
        {
            if(mesInt.get(i).getCorrespondance().equals(vb))
            {
                return i;
            }
        }
        
        return -1;
    }
    
    
    /*
        GET-SET
    */

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public ArrayList<MonInt> getMesInt() {
        return mesInt;
    }

    public void setMesInt(ArrayList<MonInt> mesInt) {
        this.mesInt = mesInt;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }
    
    
    
}
