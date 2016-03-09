package Cesar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		
		String filePath = "/home/mickael/Documents/ProjetS4/Fichiers_cryptés/eB.txt";
		String line;
		try {
			Scanner scan = new Scanner(new File(filePath));
			
			if (scan.hasNextLine()){
				line = scan.nextLine();
				MessageCrypte m1 = new MessageCrypte(line);
				ArrayList<String> test = m1.decrypterParPaquetDe2();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
