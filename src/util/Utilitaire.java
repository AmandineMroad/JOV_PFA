/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ihm.listeners.Lis_Next;
import java.awt.Dimension;
import traitement.Parser;
import traitement.MonInt;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon
 */
public class Utilitaire {

    /*
     ATTRIBUTS
     */
    // Objet contenant l'ensemble des regex
    private Parser parser;

    // Contiendra l'ensemble des objet MonInt crée durant la lecture du fichier source
    private ArrayList<MonInt> mesInt;

    // Permet d'ouvrir un fichier en lecture
    private FileReader fr;

    // Permet de lire dans un fichier ouvert
    private BufferedReader br;

    //
    private final String nomF = "CODE.java";
    private File fichier;

    // Contiens le nombre de lignes du fichier source
    private int nbLignes;
    
    boolean int_while = false;
    ArrayList<String> string_while = new ArrayList<>();
    String[] cond = null;
    int i_while = 0;
    int indice_while = 0;
    //BufferedReader brtmp;
    // 
    private Dimension d;

    /**
     * Constructeur
     *
     * @param d
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Utilitaire(Dimension d) throws FileNotFoundException, IOException {
        parser = new Parser();
        mesInt = new ArrayList<>();
        fr = new FileReader(nomF);
        // fr = new FileReader(fichier);
        br = new BufferedReader(fr);
        this.d = d;
        NombreLignes();
    }

    /**
     * Constructeur
     *
     * @param d
     * @param f
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Utilitaire(Dimension d, File f) throws FileNotFoundException, IOException {
        parser = new Parser();
        mesInt = new ArrayList<>();
        fichier = f;
        fr = new FileReader(fichier);
        br = new BufferedReader(fr);
        this.d = d;
        NombreLignes();
    }

    /**
     * Permet de compter le nombre de lignes du fichier
     *
     * @throws IOException
     */
    public void NombreLignes() throws IOException {
        while (br.readLine() != null) {
            nbLignes++;
        }

        br.close();
        fr.close();

        fr = new FileReader(fichier);
        br = new BufferedReader(fr);
        //brtmp = new BufferedReader(fr);
    }

    /**
     * Fonction centrale du programme. A chaque appelle elle lit la ligne
     * suivante, l'envoie au parser qui lui indique quel type de ligne c'est
     * (initialisation, instanciation ..). La ligne peut ne pas être reconnus si
     * ce n'est pas une opération sur les entiers
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void execution() throws FileNotFoundException, IOException {
        //System.out.println("Utilitaire.execution()");
        String tmp = null;
        String s[];
        String vrb;
        String operation;
        
        
        boolean execution = false;
        
        Rectangle2D.Double r = null;

        MonInt mi;

        int tmp2=0;
        int tmp3=0;
        int tmp4=0;
        int res;
        
        if(int_while==true)
        {
            execution = true;
            System.out.println("WHILE DETECTE");
            if(mesInt.get(indice_while).getMonInt()!=Integer.parseInt(cond[2]))
            {
                System.out.println("CONDITION OK");
                if(i_while==string_while.size())
                    i_while=0;
                
                tmp = string_while.get(i_while);
                i_while++;
            }
            else
            {
                int_while = false;
                execution = false;
            }
        }
        else if ((tmp = br.readLine()) != null) 
            execution = true;
            
        if(execution == true)
        {
            //System.out.println(tmp);
            /*if(tmp.replaceAll("\\s", "").equals("}") && int_while==true)       
            {
                System.out.println("BLA:"+brtmp.readLine());
                //br = brtmp;
                return;
            }*/
            
            //System.out.println(tmp);
            // res recupere le type de regex de la ligne
            res = parser.correspondRegex(tmp);
            // -1 correspond à aucune regex
            if (res != -1) {
                switch (res) {
                    case 0: // INSTANCIATION
                        // mesInt.add(new MonInt(0, parser.extraireVariable(tmp), new Rectangle2D.Double(100,100,100,20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                        
                        mesInt.add(new MonInt(0, parser.extraireVariable(tmp), new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                        
                        break;
                    case 1: // INITIALISATION_ENTIER_SIMPLE
                        //mesInt.add(new MonInt(parser.extraireValeur(tmp), parser.extraireVariable(tmp), new Rectangle2D.Double(200,200,100,20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        mesInt.add(new MonInt(parser.extraireValeur(tmp), parser.extraireVariable(tmp).replace("-", ""), new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        break;
                    case 2: // INITIALISATION_VARIABLE_SIMPLE
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        operation = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));

                        // Gestion des negatifs
                        if (operation.indexOf("-") != -1) {
                            operation = operation.replace("-", "");
                            if ((tmp2 = rechercheObjet(operation)) != -1) {
                                mesInt.add(new MonInt(-mesInt.get(tmp2).getMonInt(), vrb, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                            }
                        } else if ((tmp2 = rechercheObjet(operation)) != -1) {
                            mesInt.add(new MonInt(mesInt.get(tmp2).getMonInt(), vrb, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>
                        }
                        break;
                    case 3: // INITIALISATION_ENTIER_DOUBLE

                        break;
                    case 4: //  INITIALISATION_VARIABLE_DOUBLE

                        break;
                    case 5: // AFFECTATION_ENTIER_SIMPLE
                        if ((tmp2 = rechercheObjet(parser.extraireVariable(tmp).replace("-", ""))) != -1) {
                            mesInt.get(tmp2).setMonInt(parser.extraireValeur(tmp));
                        }
                        break;
                    case 6: // AFFECTATION_VARIABLE_SIMPLE
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        operation = parser.extraireVariable(tmp.substring(tmp.indexOf("=")));

                        // Gestion des negatifs
                        if (operation.indexOf("-") != -1) {
                            operation = operation.replace("-", "");
                            if ((tmp2 = rechercheObjet(vrb)) != -1) {
                                if ((tmp3 = rechercheObjet(operation)) != -1) {
                                    mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt());
                                }
                            }
                        } else if ((tmp2 = rechercheObjet(vrb)) != -1) {
                            if ((tmp3 = rechercheObjet(operation)) != -1) {
                                mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt());
                            }
                        }
                        break;
                    case 7: // AFFECTATION_ENTIER_DOUBLE
                        if ((tmp2 = rechercheObjet(parser.extraireVariable(tmp).replace("-", ""))) != -1) {
                            if ((parser.extraireOperation(tmp)).equals("+")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 0));
                            } else if ((parser.extraireOperation(tmp)).equals("-")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 1));
                            } else if ((parser.extraireOperation(tmp)).equals("-1")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 11));
                            } else if ((parser.extraireOperation(tmp)).equals("-2")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 12));
                            } else if ((parser.extraireOperation(tmp)).equals("-3")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 13));
                            } else if ((parser.extraireOperation(tmp)).equals("*")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 2));
                            } else if ((parser.extraireOperation(tmp)).equals("/")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 3));
                            } else if ((parser.extraireOperation(tmp)).equals("%")) {
                                mesInt.get(tmp2).setMonInt(parser.extraireOperationADeuxOp(tmp, 4));
                            }
                        }
                        break;
                    case 8: // AFFECTATION_VARIABLE_DOUBLE
                        //System.out.println("AFFECTATION_VARIABLE_DOUBLE");
                        vrb = parser.extraireVariable(tmp.substring(0, tmp.indexOf("=")));
                        if ((tmp2 = rechercheObjet(vrb)) != -1) {
                            operation = tmp.substring(tmp.indexOf("=") + 1).replace(";", "").replaceAll("\\s", "");
                            if (parser.extraireOperation(tmp).equals("+")) {
                                s = operation.split("\\+");
                                if ((tmp3 = rechercheObjet(parser.extraireVariable(s[0].replace("-", "")))) != -1 && (tmp4 = rechercheObjet(parser.extraireVariable(s[1].replace("-", "")))) != -1) {
                                    
                                    if(s[0].indexOf("-")==-1 && s[1].indexOf("-")==-1)   // Si les deux sont POSITIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() + mesInt.get(tmp4).getMonInt());
                                    else if((s[0].indexOf("-")!=-1) && s[1].indexOf("-")!=-1)
                                      mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() + -mesInt.get(tmp4).getMonInt());                                    
                                    else if(s[0].indexOf("-")!=-1)   // Si 1er NEGATIF
                                        mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() + mesInt.get(tmp4).getMonInt());
                                    else if(s[1].indexOf("-")!=-1)   // Si 2em NEGATIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() + -mesInt.get(tmp4).getMonInt());                                    
                                }
                            }
                            else if (parser.extraireOperation(tmp).equals("-")) {
                                s = operation.split("\\-");
                                if ((tmp3 = rechercheObjet(parser.extraireVariable(s[0]))) != -1 && (tmp4 = rechercheObjet(parser.extraireVariable(s[1]))) != -1) {
                                    mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() - mesInt.get(tmp4).getMonInt());
                                }
                            }
                            else if (parser.extraireOperation(tmp).equals("-1")) {
                                
                            }
                            else if (parser.extraireOperation(tmp).equals("-2")) {
                                
                            }
                            else if (parser.extraireOperation(tmp).equals("-3")) {
                                
                            }                            
                            
                            else if (parser.extraireOperation(tmp).equals("*")) {
                                s = operation.split("\\*");
                                if ((tmp3 = rechercheObjet(parser.extraireVariable(s[0].replace("-", "")))) != -1 && (tmp4 = rechercheObjet(parser.extraireVariable(s[1].replace("-", "")))) != -1) {
                                    
                                    if(s[0].indexOf("-")==-1 && s[1].indexOf("-")==-1)   // Si les deux sont POSITIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() * mesInt.get(tmp4).getMonInt());
                                    else if((s[0].indexOf("-")!=-1) && s[1].indexOf("-")!=-1)
                                      mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() * -mesInt.get(tmp4).getMonInt());                                    
                                    else if(s[0].indexOf("-")!=-1)   // Si 1er NEGATIF
                                        mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() * mesInt.get(tmp4).getMonInt());
                                    else if(s[1].indexOf("-")!=-1)   // Si 2em NEGATIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() * -mesInt.get(tmp4).getMonInt());                                    
                                }
                            }
                            else if (parser.extraireOperation(tmp).equals("/")) {
                                s = operation.split("\\/");
                                if ((tmp3 = rechercheObjet(parser.extraireVariable(s[0].replace("-", "")))) != -1 && (tmp4 = rechercheObjet(parser.extraireVariable(s[1].replace("-", "")))) != -1) {
                                    
                                    if(s[0].indexOf("-")==-1 && s[1].indexOf("-")==-1)   // Si les deux sont POSITIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() / mesInt.get(tmp4).getMonInt());
                                    else if((s[0].indexOf("-")!=-1) && s[1].indexOf("-")!=-1)
                                      mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() / -mesInt.get(tmp4).getMonInt());                                    
                                    else if(s[0].indexOf("-")!=-1)   // Si 1er NEGATIF
                                        mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() / mesInt.get(tmp4).getMonInt());
                                    else if(s[1].indexOf("-")!=-1)   // Si 2em NEGATIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() / -mesInt.get(tmp4).getMonInt());                                                                        
                                }
                            }
                            else if (parser.extraireOperation(tmp).equals("%")) {
                                s = operation.split("\\%");
                                if ((tmp3 = rechercheObjet(parser.extraireVariable(s[0].replace("-", "")))) != -1 && (tmp4 = rechercheObjet(parser.extraireVariable(s[1].replace("-", "")))) != -1) {
                                    
                                    if(s[0].indexOf("-")==-1 && s[1].indexOf("-")==-1)   // Si les deux sont POSITIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() % mesInt.get(tmp4).getMonInt());
                                    else if((s[0].indexOf("-")!=-1) && s[1].indexOf("-")!=-1)
                                      mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() % -mesInt.get(tmp4).getMonInt());                                    
                                    else if(s[0].indexOf("-")!=-1)   // Si 1er NEGATIF
                                        mesInt.get(tmp2).setMonInt(-mesInt.get(tmp3).getMonInt() % mesInt.get(tmp4).getMonInt());
                                    else if(s[1].indexOf("-")!=-1)   // Si 2em NEGATIF
                                        mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() % -mesInt.get(tmp4).getMonInt());                                                                        


//mesInt.get(tmp2).setMonInt(mesInt.get(tmp3).getMonInt() % mesInt.get(tmp4).getMonInt());
                                }
                            }
                        }
                        break;

                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:    //  INITIALISATION_TABLEAU_SIMPLE
                        System.out.println("INITIALISATION_TABLEAU_SIMPLE : " + tmp);

                        ArrayList<String> rtr = parser.extraireTableauSimple(tmp);

                        int c = 0;
                        int c1 = 0;
                        for (int i = 1; i < rtr.size(); i++) {
                            System.out.println(rtr.get(i));
                            c = (int) rtr.get(i).charAt(0);

                            if (c == 45) // Si negatif
                            {
                                c1 = (int) rtr.get(i).charAt(1);

                                if ((c1 >= 65 && c1 <= 90) || (c1 >= 97 && c1 <= 122)) {
                                    //System.out.println("VB NETAGIVE : "+c1+" "+i);
                                    if ((tmp2 = rechercheObjet(rtr.get(i).replace("-", ""))) != -1) {
                                        //mesInt.add(new MonInt(-mesInt.get(tmp2).getMonInt(), rtr.get(0)+i, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                                                            
                                        mesInt.add(new MonInt(-mesInt.get(tmp2).getMonInt(), rtr.get(0) + '['+ (i-1) + ']', new Rectangle2D.Double(5, 15, 100, 20), true)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                                                            
                                    }
                                } else
                                {
                                     //mesInt.add(new MonInt(Integer.parseInt(rtr.get(i)), rtr.get(0)+i, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                    
                                    mesInt.add(new MonInt(Integer.parseInt(rtr.get(i)), rtr.get(0) + '['+ (i-1) + ']', new Rectangle2D.Double(5, 15, 100, 20), true)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                    
                                }
                            } else if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                                //System.out.println("VB POSITIVE : "+c+" "+i);
                                if ((tmp2 = rechercheObjet(rtr.get(i))) != -1) 
                                {
                                    // mesInt.add(new MonInt(mesInt.get(tmp2).getMonInt(), rtr.get(0)+i, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                    
                                    mesInt.add(new MonInt(mesInt.get(tmp2).getMonInt(), rtr.get(0) + '['+ (i-1) + ']', new Rectangle2D.Double(5, 15, 100, 20), true)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                                                        
                                }
                            } else 
                            {
                                // mesInt.add(new MonInt(Integer.parseInt(rtr.get(i)), rtr.get(0)+i, new Rectangle2D.Double(5, 15, 100, 20))); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                    
                                mesInt.add(new MonInt(Integer.parseInt(rtr.get(i)), rtr.get(0) + '['+ (i-1) + ']', new Rectangle2D.Double(5, 15, 100, 20), true)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                                                    
                            }
                        }

                        break;
                    case 12:    //  DECLARATION_TABLEAU_SIMPLE
                        System.out.println("DECLARATION_TABLEAU_SIMPLE");
                        
                        for(int i=0; i<parser.extraireTailleDeclarationTableau(tmp); i++)
                            mesInt.add(new MonInt(0, parser.extraireNomDeclarationTableau(tmp)+ '['+ i + ']', new Rectangle2D.Double(5, 15, 100, 20), true)); // Instancie un MonInt correspondant et l'ajoute à l'AL<>                        
                        
                        break; 
                    case 13:    //  INITIALISATION_TABLEAU_CASE_SIMPLE
                        System.out.println("INITIALISATION_TABLEAU_CASE_SIMPLE");
                                                
                        if((tmp2 = rechercheTableau(parser.extraireNomInitialisationCase(tmp)+'['+parser.extraireIndiceInitialisationCase(tmp)+']')) !=-1)
                        {
                             mesInt.get(tmp2).setMonInt(parser.extraireValeurInitialisationCase(tmp));  
                        }
                        
                        break;
                    case 14:    // BOUCLE_WHILE
                        System.out.println("BOUCLE_WHILE");
                        
                       
                        cond = parser.extraireConditionWhile(tmp);
//                        int_while = true;
                        if((tmp2=rechercheObjet(parser.extraireVariableWhile(cond[0]))) != -1)
                        {
                            indice_while = tmp2;
                            if(cond[1].equals("=="))
                            {
                                if(mesInt.get(tmp2).getMonInt()!=Integer.parseInt(cond[2]))
                                {
                                    ConditionPasRespectee();  
                                }
                                else
                                {
                                    while(mesInt.get(tmp2).getMonInt()==Integer.parseInt(cond[2]))
                                    {   
                                        //System.out.println("Respectée");
                                        this.execution();
                                    }
                                    
                                    ConditionPasRespectee();  
                                }
                            }
                            if(cond[1].equals("!="))
                            {
                                //System.out.println("!=");
                                if(mesInt.get(tmp2).getMonInt()==Integer.parseInt(cond[2]))
                                {
                                    ConditionPasRespectee();  
//                                    int_while = false;
                                }
                                else
                                {
                                    int_while = true;
                                    //while(mesInt.get(tmp2).getMonInt()!=Integer.parseInt(cond[2]))
                                    //{   
                                        tmp = br.readLine();
                                        tmp = br.readLine().replaceAll("\\s*", "");
                                        string_while.add(tmp);
                                        //System.out.println(tmp);
                                        while(!"}".equals(tmp = br.readLine().replaceAll("\\s*", "")))
                                        {
                                            //System.out.println(tmp);
                                            string_while.add(tmp);
                                        }
                                        
                                        //System.out.println("Respectée : "+mesInt.get(tmp2).getMonInt());
                                        //this.execution();
                                    //}
                                    
                                    //ConditionPasRespectee(); 
//                                    int_while = false;
                                }
                            }     
                            
                        }
                        break;
                    case 15:
                        
                        break;
                }
            } else {
                try {
                    Gestionnaire gest = Gestionnaire.getInstance();
                    gest.getPanGraph().affichage();
                } catch (IOException ex) {
                    System.out.println("ERREUR");//TODO
                    // JOptionPane.showMessageDialog(root, "ERREUR FATALE", "ERREUR", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(Lis_Next.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Permet de determiner si le string passé en parametre correspond à un
     * objet MonInt déjà crée
     *
     * @param vb
     * @return
     */
    public int rechercheObjet(String vb) {
        for (int i = 0; i < mesInt.size(); i++) {
            if (mesInt.get(i).getCorrespondance().equals(vb)) {
                return i;
            }
        }
        return -1;
    }
        
        
    /**
     * 
     * @param tab
     * @return 
     */
   public int rechercheTableau(String tab)
    {
        for (int i = 0; i < mesInt.size(); i++) {
            if (mesInt.get(i).getCorrespondance().equals(tab)) {
                return i;
            }
        }    
            return -1;
        
    }

   public void ConditionPasRespectee() throws IOException
   {
        System.out.println("Condition pas respectée");
        BufferedReader brtmp = br;
        String wh;
        int cpt=0;
        wh = brtmp.readLine();
        System.out.println(wh);
        if(wh.replaceAll("\\s", "").equals("{"))
        {
            System.out.println("Accolade ouvrante");
            cpt++;
        }
        while(cpt!=0)
        {
             wh = brtmp.readLine();
             System.out.println(wh);
             if(wh.replaceAll("\\s", "").equals("{"))
                 cpt++;
             else if(wh.replaceAll("\\s", "").equals("}"))
                 cpt--;
        }

        br = brtmp;        
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
