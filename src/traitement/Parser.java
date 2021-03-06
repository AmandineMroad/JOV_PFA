/**
 * @name : JAVA OBJECT VIEWER
 * @author : Simon LACHKAR, Amandine ROGER
 * @company : Polytech Marseille
 * @date: mai 2015
 */
package traitement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Simon
 */
public class Parser 
{
    /*
        ATTRIBUTS
    */
    
    // Ensemble des regex
    private final String INSTANCIATION = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; /* [ESP]int[ESP]variable[ESP]; */
    private final String INITIALISATION_ENTIER_SIMPLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*;$"; /* [ESP]int[ESP]variable[ESP]=[ESP]chiffre[ESP]; */
    private final String INITIALISATION_VARIABLE_SIMPLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; /* [ESP]int[ESP]variable[ESP]=[ESP]variable[ESP]; */
        private final String INITIALISATION_ENTIER_DOUBLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*[+-/\\*%]\\s*-?\\s*[0-9]*\\s*;$";
        private final String INITIALISATION_VARIABLE_DOUBLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*[+-/\\*%]\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$";
    private final String AFFECTATION_ENTIER_SIMPLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]chiffre[ESP];
    private final String AFFECTATION_VARIABLE_SIMPLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]variable[ESP];
    private final String AFFECTATION_ENTIER_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*[+-/\\*%]\\s*-?\\s*[0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]chiffre[ESP]+-*/%[ESP]chiffre[ESP];
    private final String AFFECTATION_VARIABLE_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*[+-/\\*%]\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; // [ESP]variable1[ESP]=[ESP]variable[ESP]+-*/%[ESP]variable[ESP];
        private final String AFFECTATION_VARIABLE_ENTIER_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*[+-/\\*%]\\s*-?\\s*[0-9]*\\s*;$";
        private final String AFFECTATION_ENTIER_VARIABLE_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*[+-/\\*%]\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$";
    private final String INITIALISATION_TABLEAU_SIMPLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*\\[\\s*\\]\\s*=\\s*\\{-?\\s*[a-zA-Z_0-9]*\\s*[,-?\\s*[a-zA-Z_0-9]*]*\\s*\\}\\s*;$"; // int t[] = {x, x, x}; "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*\\[\\s*\\]\\s*=\\s*\\{-?\\s*[0-9]*[,-?\\s*[0-9]*]*\\}\\s*;$";
    private final String DECLARATION_TABLEAU_SIMPLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*\\[\\s*\\]\\s*=\\s*new\\s*int\\s*\\[\\s*[0-9]*\\s*\\]\\s*;$"; // int t[] = new int[6]; 
    private final String INITIALISATION_TABLEAU_CASE_SIMPLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*\\[\\s*[a-zA-Z_0-9]*\\s*\\]\\s*=\\s*-?\\s*[a-zA-Z_0-9]*\\s*;$";   // t[0] = 4;  
    private final String BOUCLE_WHILE = "^\\s*while\\s*\\([a-zA-Z]{1}[a-zA-Z_0-9]*\\s*==|!=|>|<|>=|<=\\s*[a-zA-Z_0-9]*\\)\\s*\\{\\s*$";
    private final String CONDITION_IF = "^\\s*if\\s*\\([a-zA-Z]{1}[a-zA-Z_0-9]*\\s*(==)|(!=)|(>)|(<)|(>=)|(<=)\\s*[a-zA-Z_0-9]*\\)\\s*\\{\\s*$";
    private final String CONDITION_ELSE = "^\\s*\\}\\s*else\\s*\\{";
    
    /** AL<> qui va contenir les regex*/
    private ArrayList<String>regex;
    
    /** Permet de transformer la string en regex*/
    private Pattern pattern;
    
    /** Permet de tester si une regex match avec une ligne*/
    private Matcher matcher;
    
    
    /**
     * CONSTRUCTEUR
     */
    public Parser()
    {
        pattern = null;
        matcher = null;
        regex = new ArrayList<>();
        
        // Ajout des regex à l'AL<>
        regex.add(INSTANCIATION);
        regex.add(INITIALISATION_ENTIER_SIMPLE);
        regex.add(INITIALISATION_VARIABLE_SIMPLE);
        regex.add(INITIALISATION_ENTIER_DOUBLE);
        regex.add(INITIALISATION_VARIABLE_DOUBLE);
        regex.add(AFFECTATION_ENTIER_SIMPLE);
        regex.add(AFFECTATION_VARIABLE_SIMPLE);
        regex.add(AFFECTATION_ENTIER_DOUBLE);
        regex.add(AFFECTATION_VARIABLE_DOUBLE);
        regex.add(AFFECTATION_VARIABLE_ENTIER_DOUBLE);
        regex.add(AFFECTATION_ENTIER_VARIABLE_DOUBLE);
        regex.add(INITIALISATION_TABLEAU_SIMPLE);
        regex.add(DECLARATION_TABLEAU_SIMPLE);
        regex.add(INITIALISATION_TABLEAU_CASE_SIMPLE);
        regex.add(BOUCLE_WHILE);
        regex.add(CONDITION_IF);
        regex.add(CONDITION_ELSE);
    }
 
    
    /**
     * Permet de determiner si la ligne reçus en parametre match avec une regex.
     * @param ligne du fichier
     * @return l'indice si oui, -1 sinon
     */
    public int correspondRegex(String ligne)
    {
        for(int i=0; i<regex.size(); i++)
        {
            pattern = Pattern.compile(regex.get(i));
            matcher = pattern.matcher(ligne);
            
            if(matcher.find())
                return i;
        }
        
        return -1;
    }
    
    
    /**
     * Reçoit une ligne reconnus par un regex et en extrait uniquement la variable
     * @param ligne du fichier
     * @return la variable de la ligne
     */
    public String extraireVariable(String ligne)
    {
        return ligne.replace(";", " ").  // Enleve le ";"
            replaceFirst("^\\s*int\\s*", " ").    // Enleve le 1er "int" au cas ou "int" soit contenus dans le nom de la variable
            replace("=", " ").   // Enleve le "="
            replace("+", " ").   // Enleve le "+"
            replace("*", " ").   // Enleve le "*"
            replace("/", " ").   // Enleve le "/"
            replace("%", " ").   // Enleve le "%"
            replaceAll("\\d", " ").  // Enleve tous les chiffres 
            replaceAll("\\s", "");  // Enleve tous les espaces     
    }
    
    /**
     * Reçoit une ligne reconnus par un regex et en extrait uniquement le chiffre
     * @param ligne du fichier
     * @return la valeur de la ligne
     */
    public int extraireValeur(String ligne)
    {
        return Integer.parseInt(
                ligne.replace(";", " ").
                replaceFirst("int", " ").
                replace("=", " ").
                replaceAll("[a-zA-Z]{1}[a-zA-Z_0-9]*", " ").
                replaceAll("\\s", "")
        );        
    }
    
    /**
     * Reçoit une ligne et en extrait l'operation
     * @param ligne du fichier
     * @return l'operation de la ligne
     */
    public String extraireOperation(String ligne)
    {
        ligne = ligne.substring(ligne.indexOf("=")+1);    // Garde que ce qui est à droite de "="
        ligne = ligne.replaceAll(";", "");  // Vire les ";"
        ligne = ligne.replaceAll("\\s", "");  // Vire les espaces
        
        if(Pattern.compile("^[a-zA-Z_0-9]*[+-/\\*%][a-zA-Z_0-9]*$").matcher(ligne).find())    // Si nb OP nb
        {
            ligne = ligne.replaceAll("\\w", " ").replaceAll("\\s", "");
            return ligne;
        }
        else if(Pattern.compile("^-[a-zA-Z_0-9]*[+-/\\*%][a-zA-Z_0-9]*$").matcher(ligne).find())   // Si - nb OP nb
        {
            ligne = ligne.replaceFirst("-", " ").replaceAll("\\w", " ").replaceAll("\\s", "");
            System.out.println(ligne);
            if("-".equals(ligne))
                return ligne+"1";
            else
                return ligne;
        }
        else if(Pattern.compile("^[a-zA-Z_0-9]*[+-/\\*%]*-[a-zA-Z_0-9]*$").matcher(ligne).find())   // Si nb OP - nb
        {
            ligne  = ligne.replaceAll("\\w", " ").replaceAll("\\s", "");
            ligne = String.valueOf(ligne.charAt(0));
            System.out.println(ligne);
            if("-".equals(ligne))
                return ligne+"2";
            else
                return ligne;
        }
        else if((Pattern.compile("^-[a-zA-Z_0-9]*[+-/\\*%]*-[a-zA-Z_0-9]*$").matcher(ligne).find()))  // Si - nb OP - nb
        {
            ligne  = ligne.replaceAll("\\w", " ").replaceAll("\\s", ""); // -+-
            ligne = ligne.replaceFirst("-", " ").replaceAll("\\s", "");  // +-
            System.out.println("LIGNE : "+ligne);
            ligne = String.valueOf(ligne.charAt(0));
            System.out.println("LIGNE FINALE : "+ligne);
            if("-".equals(ligne))
                return ligne+"3";
            else
                return ligne;           
        }        
        else
        {
            return null;
        }
    }
    
    /**
     * Reçoit une ligne et en extrait le resultat de l'operation à deux opérandes 
     * @param ligne du fichier
     * @param type d'operation
     * @return le resultat de l'operation
     */
    public int extraireOperationADeuxOp(String ligne, int type)
    {
        
        ligne = ligne.substring(ligne.indexOf("=")+1);
        
        
        ligne = ligne.replace(";", "").replaceAll("\\s", "");
        
        
        if(type == 0)   // ADDITION
        {
            String s[] = ligne.split("\\+");
            return (Integer.parseInt(s[0])+Integer.parseInt(s[1]));
        }
        else if(type == 1)  // SOUSTRACTION nb OP nb
        {
            String s[] = ligne.split("-");
            //System.out.println(s[0]+" "+s[1]);
            return (Integer.parseInt(s[0])-Integer.parseInt(s[1]));
        }
        else if(type == 11) // SOUSTRACTON - nb OP nb
        {
            ligne = ligne.replaceFirst("-", "");
            String s[] = ligne.split("-");
            //System.out.println(s[0]+" "+s[1]);
            return (-Integer.parseInt(s[0])-Integer.parseInt(s[1]));            
        }
        else if(type == 12) // SOUSTRACTON nb OP - nb "2 - - 1"
        {
            String d = ligne.substring(0, ligne.indexOf("-"));
            String f = ligne.substring(ligne.indexOf("-")+1);
            return (Integer.parseInt(d) - (Integer.parseInt(f)));             
        }
        else if(type == 13) // SOUSTRACTON - nb OP - nb "- 10 - - 5"
        {
            ligne = ligne.replaceFirst("-", "");
            String d = ligne.substring(0, ligne.indexOf("-"));
            String f = ligne.substring(ligne.indexOf("-")+1);
            System.out.println("d:"+d+" f:"+f);
            return (-Integer.parseInt(d) - (Integer.parseInt(f)));             
        }        
        else if(type == 2)  // MUTIPLICATION
        {
            String s[] = ligne.split("\\*");
            return (Integer.parseInt(s[0])*Integer.parseInt(s[1]));        
        }
        else if(type == 3)  // DIVISION
        {
            String s[] = ligne.split("/");
            return (Integer.parseInt(s[0])/Integer.parseInt(s[1]));        
        }
        else if(type == 4)  // MODULO
        {
            String s[] = ligne.split("%");
            return (Integer.parseInt(s[0])%Integer.parseInt(s[1]));        
        }
        else
            return -1;
    }
    
    /**
     * Permet d'extraire les valeurs (variables ou chiffres) assignées à un tableau
     * 
     * @param ligne
     * @return 
     */
    public ArrayList<String> extraireTableauSimple(String ligne)
    {
        ArrayList<String> retour = new ArrayList<>();
        String variable = ligne.substring(0, ligne.indexOf("="));
        String valeurs = ligne.substring(ligne.indexOf("=")+1);
        
        variable = variable.replaceFirst("^\\s*int\\s*", " ").replace("["," ").replace("]"," ").replaceAll("\\s", "");
        retour.add(variable);
        
        valeurs = valeurs.replace(";", " ").replace("{"," ").replace("}"," ").replaceAll("\\s", "");
        
        String[] vlr = valeurs.split(",");
        for(int i=0; i<vlr.length; i++)
        {
            retour.add(vlr[i]);
        }
        
        return retour;
    }
    
    /**
     * Permet d'extraire la taille lors de la declaration d'un tableau
     * 
     * @param ligne
     * @return 
     */
    public int extraireTailleDeclarationTableau(String ligne)
    {
        String taille = ligne.substring(ligne.indexOf("=")+1).replace("new", " ").replaceFirst("^\\s*int\\s*", " ").replace("["," ").replace("]"," ").replace(";", " ").replaceAll("\\s", "");
        
        return Integer.valueOf(taille);
    }
    
    /**
     * Permet d'extraire le nom du tableau lors de sa declaration
     * 
     * @param ligne
     * @return 
     */
    public String extraireNomDeclarationTableau(String ligne)
    {
        return ligne.substring(0, ligne.indexOf("=")).replaceFirst("^\\s*int\\s*", " ").replace("["," ").replace("]"," ").replace(";", " ").replaceAll("\\s", "");
    }  
    
    /**
     * Permet d'extraire le nom du tableau lors de l'initialisation d'une de ses cases
     * 
     * @param ligne
     * @return 
     */
    public String extraireNomInitialisationCase(String ligne)
    {
        return ligne.substring(0, ligne.indexOf("[")).replaceAll("\\d", " ").replace("["," ").replace("]"," ").replaceAll("\\s", "");
    }     
    
   
    /**
     * Permet d'extraire l'indice d'une case de tableau lors son l'initialisation
     * 
     * @param ligne
     * @return 
     */
    public String extraireIndiceInitialisationCase(String ligne)
    {
        String taille = ligne.substring(ligne.indexOf("[")+1, ligne.indexOf("=")).replace("]"," ").replace(";", " ").replaceAll("\\s", "");
        
        return taille;
    }
    
    /**
     * Permet d'extraire la valeur (variable ou chiffre) assignée à la case d'un tableau
     * 
     * @param ligne
     * @return 
     */
    public String extraireValeurInitialisationCase(String ligne)
    {
        String valeur = ligne.substring(ligne.indexOf("=")+1).replace(";", " ").replaceAll("\\s", "");

        return valeur;
    }    
    
    /**
     * Permet d'extraire d'une condition (while ou if) les deux operandes et l'operateur booleen en les separant chacun dans
     * la case d'un tableau de String
     * 
     * @param ligne
     * @return 
     */
    public String[] extraireCondition(String ligne)
    {
        String cond[] = new String[3];
        
        // recupere uniquement la condition : [vb]OP[vbOUint]
        ligne = ligne.replace("while", " ").replace("if", " ").replace("(", " ").replace(")", " ").replace("{", " ").replaceAll("\\s", "");

        // recupere la variable
        cond[0] = ligne.replaceAll("=", " ").replace(">", " ").replace("<", " ").replace("!", " ").replaceAll("[0-9]*", " ").replaceAll("\\s", "");
       
        // recup le type de test binaire
        cond[1] = ligne.replaceAll("\\w", " ").replaceAll("\\s", "");
        
        // recup la valeur/variable testée
        cond[2] = ligne.replaceAll("=", " ").replace(">", " ").replace("<", " ").replace("!", " ").replaceAll("[a-zA-Z_]*", " ").replaceAll("\\s", "");;
        
        return cond;
    }
    
    /**
     * Permet d'extraire la variable d'une condition if
     * 
     * @param condition
     * @return 
     */
    public String extraireVariableWhileIf(String condition)
    {
        return condition.replaceAll("==", " ").replaceAll("[0-9]*", " ").replaceAll("\\s", "");
    }
}
