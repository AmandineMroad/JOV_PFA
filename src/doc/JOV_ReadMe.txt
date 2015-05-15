ReadMe

Veuillez trouver ci-dessous les syntaxes precise de l'ensemble des instructions que l'application gere. Les syntaxes sont a respecter rigoureusement pour que le programme fonctionne : 

Legende : 
	int : indique le type primitif JAVA entier
	variable : indique le nom d'une variable (simple ou tableau) respectant la norme JAVA
	chiffre : indique un chiffre positif ou negatif (precede d'un signe -)
	OPERATEUR_OPERATION : indique l'un des 5 operateurs a deux operandes  (+ - * / %)
	OPERATEUR_CONDITION : indique l'un des 6 operateurs booleens (== != < > <= >=)
	{}* : indique la repetition a l'infinis du motif qu'il contient (pour les tableaux)
	INSTRUCTIONS : indique l'ensemble des instructions geres par le programme
	LIGNE_VIDE : indique la presence obligatoire d'une ligne vide
	Les espaces ne sont pas indiques dans les syntaxes pour ne pas les allourdir

======


Type d'instruction : INSTANCIATION SIMPLE
Syntaxe a respecter : int variable;


======


Type d'instruction : INITIALISATION AVEC ENTIER SIMPLE
Syntaxe a respecter : int variable = chiffre;


======


Type d'instruction : INITIALISATION AVEC VARIABLE SIMPLE
Syntaxe a respecter : int variable = variable;


======


Type d'instruction : AFFECTATION AVEC ENTIER SIMPLE
Syntaxe a respecter : variable = chiffre;


======


Type d'instruction : AFFECTATION AVEC VARIABLE SIMPLE
Syntaxe a respecter : variable = variable;


======


Type d'instruction : AFFECTATION AVEC UNE OPERATION A DEUX ENTIERS
Syntaxe a respecter : variable = chiffre OPERATEUR_OPERATION chiffre;


======


Type d'instruction :  AFFECTATION AVEC UNE OPERATION A DEUX VARIABLES
Syntaxe a respecter : variable1 = variable OPERATEUR_OPERATION variable;
Exception : 

======


Type d'instruction : INITIALISATION D'UN TABLEAU SIMPLE
Syntaxe a respecter : variable [] = {variableOUchiffre,variableOUchiffre}*;


======


Type d'instruction : DECLARATION D'UN TABLEAU SIMPLE
Syntaxe a respecter : int variable[] = new int[chiffre];


======


Type d'instruction : INITIALISATION DE LA CASE D'UN TABLEAU SIMPLE
Syntaxe a respecter : variable[chiffre] = chiffre;


======


Type d'instruction : BOUCLE WHILE
Syntaxe a respecter :
LIGNE_VIDE
while(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF
Syntaxe1 a respecter : 

if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}


======


Type d'instruction : CONDITION IF-ELSE
Syntaxe a respecter : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
} else {
	INSTRUCTIONS
}








