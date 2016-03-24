import java.io.*;
import java.*;

public class ArbreLexicographique {
	
	char lettre;
	ArbreLexicographique filsGauche;
	ArbreLexicographique frereDroit;
	
	static ArbreLexicographique dico = null;
	
	public ArbreLexicographique(String mot) {
	    if (mot == null || mot.length() == 0) {
	      this.lettre = '*';
	      this.filsGauche = null;
	    } else {
	      this.lettre = mot.charAt(0);
	      this.filsGauche = new ArbreLexicographique(mot.substring(1));
	    }
	    this.frereDroit = null;
	}
	
	ArbreLexicographique()
	{
		this.lettre = '#';
		this.filsGauche =null;
		this.frereDroit = null;
	}
	
	ArbreLexicographique(char lettre, ArbreLexicographique filsGauche, ArbreLexicographique frereDroit)
	{
		this.lettre = lettre;
		this.filsGauche = filsGauche;
	    this.frereDroit = frereDroit;
	}
	
	public void afficher()
	{
		System.out.print(this.lettre);
		
		if(filsGauche != null)
			filsGauche.afficher();
		if(frereDroit != null)
			frereDroit.afficher();
	}
	
	public void inserer(char lettre)
	{
		if(this.lettre == 0)
			this.lettre = lettre;
		else if(lettre < this.lettre)
		{//Si la lettre à insérer arrive avant dans l'odre lexicographique, on descend dans le sous-arbre gauche
			if(filsGauche == null)
				filsGauche = new ArbreLexicographique(lettre,null,null);
			else
				frereDroit.inserer(lettre);
				
		}
		
	}
	
	
	public static ArbreLexicographique ajouter(String mot, ArbreLexicographique precedent) {
		// Si le dictionnaire est vide construire un dictionnaire
	    // contenant uniquement mot.
	    if (precedent == null) {
	      return new ArbreLexicographique(mot);
	    }
	    // Si mot null ne pas modifier le dictionnaire
	    if(mot == null) {
	      return precedent;
	    }
	    // Si longueur du mot nulle et '*' déjà à cet endroit
	    // ne pas modifier le dictionnaire ('*' plus petit que
	    // toutes les lettres dans l'ordre < ), sinon ajouter '*'
	    // avant toutes les autres lettres contenu dans le dictionnaire
	    // précédent.
	    if(mot.length() == 0) {
	      if (precedent.lettre == '*') {
	        return precedent;
	      } else {
	        return new ArbreLexicographique('*',null,precedent);
	      }
	    }
	    // Si la première lettre du mot est plus petite que toutes les premières
	    // lettres des autres mots du dictionnaire ajouter le mot avant tous 
	    // les autres mots.
	    if (precedent.lettre > mot.charAt(0)) {
	      ArbreLexicographique temp = new ArbreLexicographique(mot);
	      temp.frereDroit = precedent;
	      return temp;
	    }
	    // Si la première lettre du mot est plus grande que la première lettre
	    // du dictionnaire, ajouter le mot au dictionnaire des mots commençants
	    // par d'autres lettres
	    if (precedent.lettre < mot.charAt(0)) {
	      precedent.frereDroit = ajouter(mot,precedent.frereDroit);
	      return precedent;
	    }
	    // Si la première lettre du mot est déjà dans le dictionnaire, 
	    // ajouter le suffie du mot au dictionnaire des suffixes de cette lettre.
	    precedent.filsGauche = ajouter(mot.substring(1),precedent.filsGauche);
	    return precedent;
	  }
	
	public void stocker(String nomDeFichier) throws FileNotFoundException, IOException
	{//On utilise la méthode de sérialization -> Très efficace et utile en Java !
		ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream(nomDeFichier));
		sortie.writeObject(this);
		sortie.flush();
		sortie.close();
	}
	
	public static ArbreLexicographique charger(String nomDeFichier) throws FileNotFoundException, IOException, ClassNotFoundException
	{//Méthode de désérialization
		
		ArbreLexicographique tmp;
		
		ObjectInputStream entree = new ObjectInputStream(new FileInputStream(nomDeFichier));
		tmp = (ArbreLexicographique)entree.readObject();
		entree.close();
		
		return tmp;
	}
	
	// insere tous les mots d'un fichier
	  public void lecture(String fileName) {
		  BufferedReader reader;
		  String s;

		  // ouverture du fichier en lecture
		  try {
			  reader = new BufferedReader(new FileReader(fileName));
		  }
		  catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + fileName + "' impossible : " + e);
	      return;
		  }

		  s = null;
		  System.out.println("lecture du fichier "+fileName);
		  try {
	      // test si fin de fichier
	      while ( reader.ready() ) {
	        s = reader.readLine();
	        // coupure sur les espaces
	        while ( s.length() > 0 ) {
	          // avance sur les espaces
	          while ( s.length() > 0 && s.charAt(0) == ' ' )
	            s = s.substring(1);
	          // mot suivant
	          if ( s.length() > 0 )
	            if ( s.indexOf(' ') > 0 ) {
	              ajouter(s.substring(0, s.indexOf(' ')), dico);
	              s = s.substring(s.indexOf(' ')+1);
	              System.out.println("Damn");
	            }
	            else {  // fin de ligne
	            	ajouter(s, dico);
	            	s = "";
	            	System.out.println("Doum");
	            }
	        }
	      }
	    }
	    catch (IOException e) {
	      System.out.println("erreur fichier : " + e);
	    }
	    System.out.println("fin de la lecture");
	  }
}
