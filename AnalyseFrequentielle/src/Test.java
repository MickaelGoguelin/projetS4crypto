import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	
	public static void main(String[]args) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		System.out.println("Hello\n");
		
		AnalyseFreq a1 = new AnalyseFreq();
		
		String nomDeFichier = "miserables_fr.txt";
		
		File unFichier = new File(nomDeFichier);

		if(unFichier.exists())
		{
			a1.lecture(nomDeFichier);
			a1.afficheTab1();
			System.out.println("done");
			a1.initTab2();
			a1.afficheTab2();
			System.out.println("done");
		}
		System.out.println("bye");
	}
}
