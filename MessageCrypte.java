
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MessageCrypte extends Message {
	
	public MessageCrypte()
	{
		super(null);
	}
	
	public MessageCrypte(String unMessage){
		super(unMessage);
	}
	
	public void lire(String nomFichier) throws IOException
	{
		  BufferedReader reader;
		  char c;
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  setMessage(getMessage() + c);
			  }
			  System.out.println("Bon"+this.getMessage());
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	
	public ArrayList<String> decrypter(){
		
		ArrayList<String> listeDeMessage = new ArrayList<String>();
		int i;
		for (i = 1; i<=25; i++){
			
			listeDeMessage.add(this.decrypterSuivantCle(i));
		}
		return listeDeMessage;
	}
	
	public String decrypterSuivantCle(int cle){
		
		String s = "";
		
		for (int i=0; i<this.getMessage().length(); i++)
		{
			
			s = s + this.inPos(this.inAlphabet(this.getMessage().charAt(i))+cle);
		}
		return s;
	}
	
	/*
	 * Pour décrypter le message eB.txt :
	 * 		+ lire la ligne du message
	 * 		+ faire un split pour avoir chaque valeur dans le tableau
	 * 		+ attaque par force brute (on teste chacune des clés possibles en exécutant les lignes suivantes)
	 * 		+ on retire la clé à chaque valeur (nécessite un ParseInt() pour chacune des valeurs, voir Test1)
	 * 		+ la couper en 2 (exemple : 2887 devient 28 et 87)int a = 2887; int b = a / 100; int c = a % 100;
	 * => un objet de type Integer peut être considéré et utilisé comme un int.
	 * 		+ ajouter dans une variable String message la lettre correspondante au nombre suivant l'alphabet
	 */
	
	public ArrayList<String> decrypterParPaquetDe2(){
		
		String [] message = super.getMessage().split("-");
		ArrayList<String> listeDeMessage = new ArrayList<String>();
		
		for (String i : message){
			System.out.println(i);
		}
		
		return listeDeMessage;
	}

}