import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalyseFreq {
	
	private ArrayList<Cellule> tab1;
	private ArrayList<Cellule> tab2;
	private double compteur;
	
	public AnalyseFreq()
	{
		this.tab1 = new ArrayList<Cellule>();
		this.tab2 = new ArrayList<Cellule>();
		this.compteur = 0;
	}
	
	public AnalyseFreq(ArrayList f1, ArrayList f2, int compteur)
	{
		this.tab1 = f1;
		this.tab2 = f2;
		this.compteur = compteur;
	}
	
	public void initTab2()
	{
		Cellule max = new Cellule();
		int i;
		int pos=0;
		int j=0;
		
		do
		{
			max.setCaractere(tab1.get(0).getCaractere());
			max.setNbOccur((tab1.get(0).getNbOccur()/compteur)*100);
			
			for(i=0; i<tab1.size(); i++)
			{
				if((this.tab1.get(i).getNbOccur()/compteur)*100 >= max.getNbOccur())
				{
					max.setCaractere(tab1.get(i).getCaractere());
					max.setNbOccur((tab1.get(i).getNbOccur()/compteur)*100);
					pos = i;
				}
			}
			this.tab2.add(j,new Cellule(max));
			this.tab1.remove(pos);
			j++;
		}while(tab1.size()>0);
	}
	
	public void stocker(char c)
	{
		boolean existe = false;
		
		for(int i=0; i<tab1.size(); i++)
		{
			if(this.tab1.get(i).getCaractere()==c)
			{
				this.tab1.get(i).incrementer();
				i=tab1.size();
				existe = true;
			}
		}
		if(!existe)
		{
			this.tab1.add(new Cellule(c,1));
		}
	}
	
	public void lecture(String nomFichier) throws IOException
	{
		  BufferedReader reader;
		  char c;
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  stocker(c);
				  compteur++;
			  }
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	public void afficheTab1()
	{
		for(int i=0; i<tab1.size(); i++)
		{
			tab1.get(i).affiche();
		}
	}
	
	public void afficheTab2()
	{
		for(int i=0; i<tab2.size(); i++)
		{
			tab2.get(i).affiche();
		}
	}
	
	
}
