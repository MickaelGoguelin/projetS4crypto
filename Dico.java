
import java.io.*;

public class Dico {
  // Lettre à stocker
  char lettre;
  // Dictionnaire des suffixes
  Dico suffixe;
  // Dictionnaire des mots commençants par une lettre plus grande
  // que 'lettre' dans l'ordre alphabétique
  Dico autreLettre;
  
  // Constructeur simple initialisant chacun des attributs d'un
  // objet Dico.
  private Dico(char lettre, Dico suffixe, Dico autreLettre) {
    this.lettre = lettre;
    this.suffixe = suffixe;
    this.autreLettre = autreLettre;
  }

  // Constructeur récursif construisant un dictionnaire contenant
  // un unique mot passé en argument
  public Dico(String mot) {
    if (mot == null || mot.length() == 0) {
      this.lettre = '*';
      this.suffixe = null;
    } else {
      this.lettre = mot.charAt(0);
      this.suffixe = new Dico(mot.substring(1));
    }
    this.autreLettre = null;
  }
  // Méthode statique ajoutant mot au dictionnaire passé
  // en second argument
  public static Dico ajouter(String mot, Dico precedent) {
    // Si le dictionnaire est vide construire un dictionnaire
    // contenant uniquement mot.
    if (precedent == null) {
      return new Dico(mot);
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
        return new Dico('*',null,precedent);
      }
    }
    // Si la première lettre du mot est plus petite que toutes les premières
    // lettres des autres mots du dictionnaire ajouter le mot avant tous 
    // les autres mots.
    if (precedent.lettre > mot.charAt(0)) {
      Dico temp = new Dico(mot);
      temp.autreLettre = precedent;
      return temp;
    }
    // Si la première lettre du mot est plus grande que la première lettre
    // du dictionnaire, ajouter le mot au dictionnaire des mots commençants
    // par d'autres lettres
    if (precedent.lettre < mot.charAt(0)) {
      precedent.autreLettre = ajouter(mot,precedent.autreLettre);
      return precedent;
    }
    // Si la première lettre du mot est déjà dans le dictionnaire, 
    // ajouter le suffie du mot au dictionnaire des suffixes de cette lettre.
    precedent.suffixe = ajouter(mot.substring(1),precedent.suffixe);
    return precedent;
  }

  // Méthode de recherche du mot dans le dictionnaire.
  public boolean rechercher(String mot) {
    // Si le mot ou le dictionnaire sont vide retourner faux
    if (mot == null) {
      return false;
    }
    // Si la longueur du mot est nulle, retourner vrai si 
    // la lettre courante est '*', sinon retourner faux.
    if (mot.length() == 0){
      return (lettre == '*');
    }
    // Si la première lettre du mot est plus petite que la 
    // première lettre de tous les mots du dictionnaire le 
    // mot n'est pas dans le dictionnaire.
    if (lettre > mot.charAt(0)) {
      return false;
    }
    // Si la première lettre du mot est plus grande que la lettre courante,
    // rechercher le mot dans le dictionnaire des mots commençants par une autre lettre
    if (lettre < mot.charAt(0)) {
      return autreLettre.rechercher(mot);
    }
    // Si la première lettre du mot est la lettre courante, rechercher le suffixe du mot 
    // dans le dictionnaire.
    return suffixe.rechercher(mot.substring(1));
  }
  // Méthode utilisée par println() pour afficher le dictionnaire. 
  // Appelle l'autre méthode toString avec un suffixe vide.
  public String toString(){
    return this.toString("");
  }
  // Méthode utilisée pour afficher le dictionnaire.
  public String toString(String prefix){
    String tmp = "";
    if (lettre == '*'){
      // Si '*' est la lettre courante le préfixe est un mot du dictionnaire,
      // le stocker pour l'affichage suivi du caractère '\n' (retour à la ligne).
      tmp = prefix + "\n";
    } else {
      // Sinon ajouter la lettre courante au préfixe pour afficher le dictionnaire des
      // suffixes.
      tmp = suffixe.toString(prefix+lettre);
    }
    // Si le dictionnaire des mots commençants par une autre lettre n'est pas vide
    // l'afficher.
    if(autreLettre != null) {
      tmp += autreLettre.toString(prefix);
    }
    return tmp;
  }
  
  // Méthode statique qui retire un mot du dictionnaire passé en argument 
  public static Dico retirer(String mot, Dico precedent){
    if (mot == null) {
      return precedent;
    }
    if (precedent == null) {
      return null;
    }
    // Si mot vide retirer '*'
    if (mot.length() == 0) {
      if (precedent.lettre == '*') {
        return precedent.autreLettre;
      } else {
        return precedent;
      }
    }
    // Si la première lettre du mot plus petite que la lettre courante,
    // le mot n'est pas dans le dictionnaire, retourner le dictionnaire 
    // inchangé.
    if (precedent.lettre > mot.charAt(0)) {
      return precedent;
    }
    // Si la première lettre du mot est supérieure à la lettre courante,
    // mettre à jour le dictionnaire des mots commençants par une autre 
    // lettre
    if (precedent.lettre < mot.charAt(0)) {
      precedent.autreLettre = retirer(mot,precedent.autreLettre);
      return precedent;
    }
    // Si la première lettre du mot est égale à la lettre courante, 
    // mettre à jour le dictionnaire des suffixe en retirant le suffixe du mot.
    // Si le dictionnaire des suffixe est non vide retourner le dictionnaire 
    // précédent mis à jour sinon, il n'y plus de mot dans le dictionnaire commençant 
    // par la lettre courante elle est donc éliminée en retournant le dictionnaire des 
    // mots commençants par une autre lettre. 
    precedent.suffixe = retirer(mot.substring(1),precedent.suffixe);
    if(precedent.suffixe==null) {
      return precedent.autreLettre;
    }
    return precedent;
  }
  
  // Vérificateur orthographique qui lit l'ensemble des mots du dictionnaire dans un fichier
  // passé en premier argument et vérifie que les mots du fichier passé en second argument font 
  // partie du dictionnaire.
  public static void main(String args[]) throws Exception {
	  
	  System.out.println("Bonjour");
	  
	  if(args.length != 2) {
      System.err.println("Usage: java Dico <dico> <fichier>");
      System.exit(1);
    }
    Dico d = null;
    FileReader dico = new FileReader(args[0]);
    StreamTokenizer st = new StreamTokenizer(dico);
    st.ordinaryChar('.');
    while (st.nextToken() != StreamTokenizer.TT_EOF){
      if(st.ttype == StreamTokenizer.TT_WORD)
        d = ajouter(st.sval,d);
    }
    FileReader fichier = new FileReader(args[1]);
    st = new StreamTokenizer(fichier);
    st.ordinaryChar('.');
    //while (st.nextToken() != StreamTokenizer.TT_EOF){
      //if(st.ttype == StreamTokenizer.TT_WORD)
        //if(!rechercher(st.sval,d))
          //System.out.println(st.sval);
    //}
  }
}
