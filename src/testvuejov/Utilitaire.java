/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvuejov;

import java.awt.geom.Ellipse2D;
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

        int tmp2;
        int tmp3;
        int res;
        String droite;
        String gauche;
        String vrb;
        String operation;
        if((tmp = br.readLine()) != null)
        {
            res = parser.correspondRegex(tmp);
            if(res!=-1)
                switch(res)
                {
                    case 0: // INSTANCIATION
                        mesInt.add(new MonInt(0, parser.extraireVariable(tmp), new Ellipse2D.Double(200,200,20,20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;   
                    case 1: // INITIALISATION_ENTIER_SIMPLE
                        mesInt.add(new MonInt(parser.extraireValeur(tmp), parser.extraireVariable(tmp), new Ellipse2D.Double(250,250,20,20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 2: // INITIALISATION_VARIABLE_SIMPLE
                        gauche = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        droite = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));
                        if((tmp2 = rechercheObjet(droite))!=-1)
                            mesInt.add(new MonInt(mesInt.get(tmp2).getMonInt(), gauche, new Ellipse2D.Double(300,300,20,20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 3: // AFFECTATION_ENTIER_SIMPLE
                        if((tmp2 = rechercheObjet(parser.extraireVariable(tmp)))!=-1)
                                mesInt.get(tmp2).setMonInt(parser.extraireValeur(tmp));
                        else
                            System.out.println("ERREUR");
                        break;
                    case 4: // AFFECTATION_VARIABLE_SIMPLE
                        droite = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        gauche = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));
                        if((tmp2 = rechercheObjet(droite))!=-1)
                            if((tmp3 = rechercheObjet(gauche))!=-1)
                               mesInt.get(tmp2).setMonInt( mesInt.get(tmp3).getMonInt());
                        break;
                    case 5: // AFFECTATION_ENTIER_VARIABLE_DOUBLE
                        System.out.println("AFFECTATION_ENTIER_VARIABLE_DOUBLE");
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        System.out.println("vrb: "+vrb);
                        if((tmp2 =rechercheObjet(parser.extraireVariable(vrb)))!=-1)
                        {
                            operation = tmp.substring(tmp.indexOf("=")+1);
                            System.out.println("operation : "+operation);

                                if(parser.extraireOperation(tmp).equals("+"))
                                    mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(operation, 0));
                        }
                        
                        
                        break;
                    /*case 5: // AFFECTATION_ENTIER_DOUBLE
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
                    */
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
