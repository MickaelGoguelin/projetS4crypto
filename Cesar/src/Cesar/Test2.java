package Cesar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		
		String filePath = "/home/mickael/Documents/ProjetS4/Fichiers_cryptés/eA.txt";
		String line;
		try {
			Scanner scan = new Scanner(new File(filePath));
			
			if (scan.hasNextLine()){
				line = scan.nextLine();
				MessageCrypte m1 = new MessageCrypte(line);
				ArrayList<String> messageDecryptes = m1.decrypter();
				for (String m : messageDecryptes){
					System.out.println(m);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
