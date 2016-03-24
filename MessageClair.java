
public class MessageClair extends Message {
	
	public MessageClair(){
		super();
	}

	public MessageClair(String unMessage){
		super(unMessage);
	}
	
	public String chiffrer(int cle){
		
		char[] message = super.getMessage().toCharArray();
		int i, j;
		for (i=0; i<super.getMessage().length(); i++){
			
			j = super.inAlphabet(message[i]);
			message[i]=Message.ALPHABET[(j+cle)%Message.ALPHABET.length];
		}
		
		return new String(message);
	}
	
}
