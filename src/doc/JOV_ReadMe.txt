ReadMe

Voici la commande a executer pour lancer le programme, une fois positionne dans le dossier courant du fichier.

java -jar PFA-JOV.jar

==========================================
==========================================


Veuillez trouver ci-dessous les syntaxes precise de l'ensemble des instructions que l'application gere. Les syntaxes sont a respecter rigoureusement pour que le programme fonctionne : 

Legende : 
	int : indique le type primitif JAVA entier
	variable : indique le nom d'une variable (simple ou tableau) respectant la norme JAVA en terme de nommage a l'exeption des chiffres à l'interieur des noms de variable qui ne sont pas acceptes. Chaque variable utilisee doit avoir ete prealablement declaree pour etre reconnus par le programme.
	chiffre : indique un chiffre positif ou negatif (precede d'un signe -)
	variableOUchiffre : indique que les variables et les chiffres sont acceptes (en respectant les regles precedentes)
	OPERATEUR_OPERATION : indique l'un des 5 operateurs a deux operandes  (+ - * / %)
	OPERATEUR_CONDITION : indique l'un des 6 operateurs booleens (== != < > <= >=)
	{}* : indique la repetition a l'infinis du motif qu'il contient (pour les tableaux)
	INSTRUCTIONS : indique l'ensemble des instructions geres par le programme
	LIGNE_VIDE : indique la presence obligatoire d'une ligne vide
	Les espaces ne sont pas indiques dans les syntaxes pour ne pas les allourdir


======


Type d'instruction : INSTANCIATION SIMPLE
Syntaxe a respecter : int variable;
Exemple : int a;

======


Type d'instruction : INITIALISATION AVEC UN ENTIER SIMPLE
Syntaxe a respecter : int variable = chiffre;
Exemple : int a = -5;

======


Type d'instruction : INITIALISATION AVEC UNE VARIABLE SIMPLE
Syntaxe a respecter : int variable = variable;
Exemple : int a = b;	

======


Type d'instruction : AFFECTATION AVEC ENTIER SIMPLE
Syntaxe a respecter : variable = chiffre;
Exemple : a = 19;

======


Type d'instruction : AFFECTATION AVEC VARIABLE SIMPLE
Syntaxe a respecter : variable = variable;
Exemple : a = t_int;

======


Type d'instruction : AFFECTATION AVEC UNE OPERATION A DEUX ENTIERS
Syntaxe a respecter : variable = chiffre OPERATEUR_OPERATION chiffre;
Exemple : a = -50 / 4;

======


Type d'instruction :  AFFECTATION AVEC UNE OPERATION A DEUX VARIABLES
Syntaxe a respecter : variable1 = variable OPERATEUR_OPERATION variable;
Exceptions (les operations suivantes ne sont pas gerees) : -variable - variable ; -variable - -variable ; variable - -variable
Exemple : a = -b * a;


======


Type d'instruction : INITIALISATION D'UN TABLEAU SIMPLE
Syntaxe a respecter : int variable [] = {variableOUchiffre,variableOUchiffre}*;
Exemple : int tab_entiers [] = {a,-b, 5, -7, mon_int, -8};

======


Type d'instruction : DECLARATION D'UN TABLEAU SIMPLE
Syntaxe a respecter : int variable[] = new int[chiffre];
Exemple : int tableau [] = new int[8];

======


Type d'instruction : INITIALISATION DE LA CASE D'UN TABLEAU SIMPLE
Syntaxe a respecter : variable[variableOUchiffre] = variableOUchiffre;
Exemple : tab[i] = 8;

======


Type d'instruction : BOUCLE WHILE
Syntaxe a respecter :
LIGNE_VIDE
while(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}
Exception : Les variables doivent etre declaree une seul fois. La portee des variables (locale a une boucle) n'est pas geree.
Exemple : while(a<=10){
		b = b + a;
	}

======


Type d'instruction : CONDITION IF
Syntaxe a respecter : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
}
Exemple : if(c>100){
		c =  c % -a;
	}

======


Type d'instruction : CONDITION IF-ELSE
Syntaxe a respecter : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
} else {
	INSTRUCTIONS
}
Exemple : if(c>100){
		c =  c % -a;
	}else{
		c = c + -b;
	}


==========================================
==========================================


Veuillez trouver ci-dessous l'ensemble des instructions que l'application ne gere pas par manque de temps :


======


Type d'instruction : INITIALISATION AVEC UNE OPERATION A DEUX ENTIERS
Syntaxe : int variable = chiffre OPERATEUR_OPERATION chiffre;

======


Type d'instruction : INITIALISATION AVEC UNE OPERATION A DEUX VARIABLES
Syntaxe : int variable = variable OPERATEUR_OPERATION variable;

======


Type d'instruction : INITIALISATION AVEC UNE OPERATION ENTRE UNE VARIABLE ET UN ENTIER
Syntaxe : int variable = variable OPERATEUR_OPERATION chiffre;


======


Type d'instruction : INITIALISATION AVEC UNE OPERATION ENTRE UN ENTIER ET UNE VARIABLE
Syntaxe : int variable = chiffre OPERATEUR_OPERATION variable;


======


Type d'instruction : AFFECTATION AVEC UNE OPERATION ENTRE UNE VARIABLE ET UN ENTIER
Syntaxe : variable = variable OPERATEUR_OPERATION chiffre;


======


Type d'instruction : AFFECTATION AVEC UNE OPERATION ENTRE UN ENTIER ET UNE VARIABLE
Syntaxe : variable = chiffre OPERATEUR_OPERATION variable;

======


Type d'instruction : BOUCLES WHILE IMBRIQUES
Syntaxe : 
while(chiffre OPERATEUR_CONDITION chiffre) {
	while(chiffre OPERATEUR_CONDITION chiffre) {
		INSTRUCTIONS
	}
}


======


Type d'instruction : ELSE IF
Syntaxe : 
if(chiffre OPERATEUR_CONDITION chiffre) {
	INSTRUCTIONS
} else if {
	INSTRUCTIONS
}


======