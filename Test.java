import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.Normalizer;
import java.util.ArrayList;

public class Test {
	
	public static void main(String[]args) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		AnalyseFreq a1 = new AnalyseFreq();
		
		String nomDeFichier = "miserables_en.txt";//"eA.txt";//"miserables_fr.txt";
		
		File unFichier = new File(nomDeFichier);

		if(unFichier.exists())
		{
			int a = 10%10;
			System.out.println("Hello");
			Message m1 = new MessageClair ();
			System.out.println(m1.inAlphabet('e'));
			System.out.println(m1.inAlphabet(' '));
			//a1.transform(nomDeFichier);
			//a1.initTab("miserables_fr.txt");
			//a1.initTabCrypt("eA.txt");
			a1.initTabRefS("miserables_en.txt");
			a1.initTabCryptS("eZ.txt");
			a1.afficheTabSpecial();
			a1.lectureAvecCle("eZ.txt", 62);
			//a1.afficheTab();
			System.out.println("done");
			//a1.analyse("eA.txt",0);
			//a1.remplace('q',' ');
			//a1.analyseSpecial("eC.txt",3);
			//System.out.println(a1.getTabCryptS().get(0).getCaractere());
			//a1.cesar(nomDeFichier);
			//a1.afficheTabRef();
		}
		System.out.println("bye");
	}
}
