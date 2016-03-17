package Cesar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		
		String filePath = "/home/mickael/Documents/ProjetS4/projetS4crypto/Fichiers_cryptés/eB.txt";
		String line;
		try {
			Scanner scan = new Scanner(new File(filePath));
			
			if (scan.hasNextLine()){
				line = scan.nextLine();
				System.out.println(line);
				MessageCrypte m1 = new MessageCrypte(line);
				ArrayList<String> test = m1.decrypterParPaquets(8);
				
				for (String m : test){
					System.out.println(m);
					System.out.println("---------------------------------------------------------------");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
