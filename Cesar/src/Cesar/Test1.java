package Cesar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test1 {
public static void main(String[] args) {
		
		String filePath = "/home/mickael/Documents/ProjetS4/Fichiers_cryptés/eA.txt";
		String line;
		try {
			Scanner scan = new Scanner(new File(filePath));
			
			while (scan.hasNextLine()){
				line = scan.nextLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		MessageCrypte m1 = new MessageCrypte("bcd");
		System.out.println(Message.ALPHABET);
		System.out.println(MessageCrypte.ALPHABET[72]);
		System.out.println(-2%26);
		System.out.println("\n");
		
		System.out.println(m1.decrypterSuivantCle(1));
		
		Integer a = 100;
		System.out.println(a/100);
		
		String nb = "143";
		int nbf = Integer.parseInt(nb);
		System.out.println(nbf);
		
	}
}
