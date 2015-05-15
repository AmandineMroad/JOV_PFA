ReadMe

Veuillez trouver ci-dessous les syntaxes précise de l'ensemble des instructions que l'application gére. Les syntaxes sont à respecter rigoureusement pour que le programme fonctionne : 

Legende : 
	int : indique le type primitif JAVA entier
	variable : indique le nom d'une variable (simple ou tableau) respectant la norme JAVA
	chiffre : indique un chiffre positif ou négatif (précédé d'un signe -)
	OPERATEUR_OPERATION : indique l'un des 5 opérateurs à deux opérandes  (+ - * / %)
	OPERATEUR_CONDITION : indique l'un des 6 opérateurs booléens (== != < > <= >=)
	{}* : indique la repetition à l'infinis du motif qu'il contient (pour les tableaux)
	INSTRUCTIONS : indique l'ensemble des instructions gérées par le programme
	LIGNE_VIDE : indique la présence obligatoire d'une ligne vide
	Les espaces ne sont pas indiqués dans les syntaxes pour ne pas les allourdir 

======


Type d'instruction : INSTANCIATION SIMPLE
Syntaxe à respecter : int variable;


======


Type d'instruction : INITIALISATION AVEC ENTIER SIMPLE
Syntaxe à respecter : int variable = chiffre;


======


Type d'instruction : INITIALISATION AVEC VARIABLE SIMPLE
Syntaxe à respecter : int variable = variable;


======


Type d'instruction : AFFECTATION AVEC ENTIER SIMPLE
Syntaxe à respecter : variable = chiffre;


======


Type d'instruction : AFFECTATION AVEC VARIABLE SIMPLE
Syntaxe à respecter : variable = variable;


======


Type d'instruction : AFFECTATION AVEC UNE OPERATION A DEUX ENTIERS
Syntaxe à respecter : variable = chiffre OPERATEUR_OPERATION chiffre;


======


Type d'instruction :  AFFECTATION AVEC UNE OPERATION A DEUX VARIABLES
Syntaxe à respecter : variable1 = variable OPERATEUR_OPERATION variable;


======


Type d'instruction : INITIALISATION D'UN TABLEAU SIMPLE
Syntaxe à respecter : variable [] = {variableOUchiffre,variableOUchiffre}*;


======


Type d'instruction : DECLARATION D'UN TABLEAU SIMPLE
Syntaxe à respecter : int variable[] = new int[chiffre];


======


Type d'instruction : INITIALISATION DE LA CASE D'UN TABLEAU SIMPLE
Syntaxe à respecter : variable[chiffre] = chiffre;


======


Type d'instruction : BOUCLE WHILE
Syntaxe à respecter :
LIGNE_VIDE
while(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF
Syntaxe1 à respecter : 

if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF-ELSE
Syntaxe à respecter : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
} else {
	INSTRUCTIONS
}








