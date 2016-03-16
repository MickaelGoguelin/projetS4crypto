package Cesar;

public class Test3 {
	
	public static void main(String[] args) {
		
		MessageClair m2 = new MessageClair("Bonjour");
		
		System.out.println(m2.chiffrer(6));
		
		MessageCrypte m3 = new MessageCrypte(m2.chiffrer(6));
		
		System.out.println(m3.decrypterSuivantCle(6));
		
		System.out.println(Message.ALPHABET.length);
	}

}
