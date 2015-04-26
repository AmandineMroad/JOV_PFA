/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private final String INITIALISATION_TABLEAU_SIMPLE = "^\\s*int\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*\\[\\s*\\]\\s*=\\s*\\{-?[0-9]*[,-?[0-9]*]*\\}\\s*;$"; // int t[] = {x, x, x};
    private final String DECLARATION_TABLEAU_SIMPLE = ""; // int t[] = new int[6];
    
    // AL<> qui va contenir les regex
    private ArrayList<String>regex;
    
    // Permet de transformer la string en regex
    private Pattern pattern;
    
    // Permet de tester si une regex match avec une ligne
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
            //replace("-", " ").   // Enleve le "-"
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
        
        System.out.println("extraireOperation:"+ligne);
        
        if(Pattern.compile("^[a-zA-Z_0-9]*[+-/\\*%][a-zA-Z_0-9]*$").matcher(ligne).find())    // Si nb OP nb
        {
            System.out.println("nb OP nb");
            ligne = ligne.replaceAll("\\w", " ").replaceAll("\\s", "");
            return ligne;
        }
        else if(Pattern.compile("^-[a-zA-Z_0-9]*[+-/\\*%][a-zA-Z_0-9]*$").matcher(ligne).find())   // Si - nb OP nb
        {
            System.out.println("- nb OP nb");
            ligne = ligne.replaceFirst("-", " ").replaceAll("\\w", " ").replaceAll("\\s", "");
            System.out.println(ligne);
            if("-".equals(ligne))
                return ligne+"1";
            else
                return ligne;
        }
        else if(Pattern.compile("^[a-zA-Z_0-9]*[+-/\\*%]*-[a-zA-Z_0-9]*$").matcher(ligne).find())   // Si nb OP - nb
        {
            System.out.println("nb OP - nb");
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
            System.out.println("- nb OP - nb");
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
        {System.out.println("NULL");
            return null;
        }
        /*return ligne.replace(";", " ").
                replaceAll("\\w", " ").
                replace("=", " ").
                replaceAll("\\s", "");*/
    }
    
    /**
     * Reçoit une ligne et en extrait le resultat de l'operation à deux opérandes 
     * @param ligne du fichier
     * @param type d'operation
     * @return le resultat de l'operation
     */
    public int extraireOperationADeuxOp(String ligne, int type)
    {
        //System.out.println("ligne1: "+ligne);
        
        ligne = ligne.substring(ligne.indexOf("=")+1);
        
        //System.out.println(ligne);
        
        ligne = ligne.replace(";", "").replaceAll("\\s", "");
        
        //System.out.println("ligne2: "+ligne);
        
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
            retour.add(vlr[i]);
        
        return retour;
    }
    
    /*
        GET-SET
    */

    public ArrayList<String> getRegex() {
        return regex;
    }

    public void setRegex(ArrayList<String> regex) {
        this.regex = regex;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
    
    
    
}
