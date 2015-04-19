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
    private final String INITIALISATION_ENTIER_DOUBLE = "";
    private final String INITIALISATION_VARIABLE_DOUBLE = "";
    private final String AFFECTATION_ENTIER_SIMPLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]chiffre[ESP];
    private final String AFFECTATION_VARIABLE_SIMPLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]variable[ESP];
    private final String AFFECTATION_ENTIER_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*[0-9]*\\s*[+-/\\*%][0-9]*\\s*;$"; // [ESP]variable[ESP]=[ESP]chiffre[ESP]+-*/%[ESP]chiffre[ESP];
    private final String AFFECTATION_VARIABLE_DOUBLE = "^\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*=\\s*-?\\s*[a-zA-Z]{1}[a-zA-Z_0-9]*\\s*[+-/\\*%][a-zA-Z]{1}[a-zA-Z_0-9]*\\s*;$"; // [ESP]variable1[ESP]=[ESP]variable[ESP]+-*/%[ESP]variable[ESP];
    private final String AFFECTATION_VARIABLE_ENTIER_DOUBLE = "";
    private final String AFFECTATION_ENTIER_VARIABLE_DOUBLE = "";
    
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
        regex.add(AFFECTATION_ENTIER_SIMPLE);
        regex.add(AFFECTATION_VARIABLE_SIMPLE);
        regex.add(AFFECTATION_ENTIER_DOUBLE);
        regex.add(AFFECTATION_VARIABLE_DOUBLE);
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
        return ligne.replace(";", " ").
                replaceAll("\\w", " ").
                replace("=", " ").
                replaceAll("\\s", "");
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
        else if(type == 1)  // SOUSTRACTION
        {
            String s[] = ligne.split("-");
            return (Integer.parseInt(s[0])-Integer.parseInt(s[1]));
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
