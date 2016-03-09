package Cesar;

import java.util.ArrayList;

public class MessageCrypte extends Message {
	
	public MessageCrypte(String unMessage){
		super(unMessage);
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
		
		char[] message = super.getMessage().toCharArray();
		int i, j, index;
		for (i=0; i<super.getMessage().length(); i++){
			
			j = super.inAlphabet(message[i]);
			if (j-cle < 0){
				index = Message.ALPHABET.length+(j-cle);
				message[i]=Message.ALPHABET[index];
			}
			else message[i]=Message.ALPHABET[j-cle];
		}
		return new String(message);
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