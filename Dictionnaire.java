import java.io.*;

public class Dictionnaire {
	
	  char c;                 // le caractère
	  Dictionnaire suffixes;  // les suites possibles à ce caractère
	  Dictionnaire suivants;  // les autres dictionnaires de même niveau

	  static Dictionnaire dico = null;

	  Dictionnaire(char c, Dictionnaire suffixes, Dictionnaire suivants) {
	    this.c = c;
	    this.suffixes = suffixes;
	    this.suivants = suivants;
	  }

	  static Dictionnaire d(char c, Dictionnaire suffixes, Dictionnaire suivants) {
	    return new Dictionnaire(c, suffixes, suivants);
	  }

	  // construit explicitement un dictionnaire
	  static void init() {
	    dico =
	      d('a', d(' ', null, null),
	      d('b', d('a', d('c', d(' ', null, null),
	                    d('l', d('l', d('e', d(' ', null, null),
	                                  d('o', d('n', d(' ', null, null),
	                                         null),
	                                  null)),
	                           null),
	                    d('s', d(' ', null,
	                           d('e', d(' ', null, null),
	                           null)),
	                    null))),
	             d('u', d('s', d(' ', null, null),
	                    null),//maths/frequence/complexite/comparaison att. force brut et analyse frequentielle/ attaque a clair coonu
	             null)),
	      d('s', d('a', d('c', d(' ', null, null),
	                    null),
	             null),
	      null)));
	  }

	//teste si mot est dans d
	static boolean existe(String mot, Dictionnaire d) {
	 // COMPLETER
	 return false;
	}
	
	static void test_existe(String mot, Dictionnaire d) {
	 if ( existe(mot, d) )
	   System.out.println("\"" + mot + "\" existe");
	 else
	   System.out.println("\"" + mot + "\" n'existe pas");
	}
}