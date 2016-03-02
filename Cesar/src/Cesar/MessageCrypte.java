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

}