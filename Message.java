
public abstract class Message {
	
	public static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.',';',':','-','!','?','\'', '"', ' ','\n', '\t'};
	private String message;

	public Message()
	{
		this.setMessage(null);
	}
	
	public Message(String unMessage){
		this.setMessage(unMessage);
	}
	
	public int inAlphabet(char c){
		
		int i;
		for (i=0; i<Message.ALPHABET.length; i++){
			if (c==Message.ALPHABET[i]) return i;
		}
		return i;
	}
	
	public char inPos(int i){
		return Message.ALPHABET[i];
	}
	
	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
