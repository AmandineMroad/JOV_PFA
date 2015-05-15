ReadMe

Veuillez trouver ci-dessous les syntaxes pr�cise de l'ensemble des instructions que l'application g�re. Les syntaxes sont � respecter rigoureusement pour que le programme fonctionne : 

Legende : 
	int : indique le type primitif JAVA entier
	variable : indique le nom d'une variable (simple ou tableau) respectant la norme JAVA
	chiffre : indique un chiffre positif ou n�gatif (pr�c�d� d'un signe -)
	OPERATEUR_OPERATION : indique l'un des 5 op�rateurs � deux op�randes  (+ - * / %)
	OPERATEUR_CONDITION : indique l'un des 6 op�rateurs bool�ens (== != < > <= >=)
	{}* : indique la repetition � l'infinis du motif qu'il contient (pour les tableaux)
	INSTRUCTIONS : indique l'ensemble des instructions g�r�es par le programme
	LIGNE_VIDE : indique la pr�sence obligatoire d'une ligne vide
	Les espaces ne sont pas indiqu�s dans les syntaxes pour ne pas les allourdir 

======


Type d'instruction : INSTANCIATION SIMPLE
Syntaxe � respecter : int variable;


======


Type d'instruction : INITIALISATION AVEC ENTIER SIMPLE
Syntaxe � respecter : int variable = chiffre;


======


Type d'instruction : INITIALISATION AVEC VARIABLE SIMPLE
Syntaxe � respecter : int variable = variable;


======


Type d'instruction : AFFECTATION AVEC ENTIER SIMPLE
Syntaxe � respecter : variable = chiffre;


======


Type d'instruction : AFFECTATION AVEC VARIABLE SIMPLE
Syntaxe � respecter : variable = variable;


======


Type d'instruction : AFFECTATION AVEC UNE OPERATION A DEUX ENTIERS
Syntaxe � respecter : variable = chiffre OPERATEUR_OPERATION chiffre;


======


Type d'instruction :  AFFECTATION AVEC UNE OPERATION A DEUX VARIABLES
Syntaxe � respecter : variable1 = variable OPERATEUR_OPERATION variable;


======


Type d'instruction : INITIALISATION D'UN TABLEAU SIMPLE
Syntaxe � respecter : variable [] = {variableOUchiffre,variableOUchiffre}*;


======


Type d'instruction : DECLARATION D'UN TABLEAU SIMPLE
Syntaxe � respecter : int variable[] = new int[chiffre];


======


Type d'instruction : INITIALISATION DE LA CASE D'UN TABLEAU SIMPLE
Syntaxe � respecter : variable[chiffre] = chiffre;


======


Type d'instruction : BOUCLE WHILE
Syntaxe � respecter :
LIGNE_VIDE
while(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF
Syntaxe1 � respecter : 

if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF-ELSE
Syntaxe � respecter : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
} else {
	INSTRUCTIONS
}








