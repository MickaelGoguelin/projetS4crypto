import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Simplexe {
	
	private double n;
	private double m;
	private double[][] tab;
	
	public Simplexe(){
		
	}
	//----------------------------------------Lecture------------------------------------------
	public void lecture(String nom){
		
		try{
			
			InputStream ips=new FileInputStream(nom); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			String ligne=br.readLine();
			String[] stab = ligne.trim().split("\\s+");
			
			this.n = Double.parseDouble(stab[0]);
			this.m = Double.parseDouble(stab[1]);
			this.tab = new double[(int) (m+1)][(int) (n+m+2)];
			
		//-------------------------Fonction objectif-------------------------
			
			ligne=br.readLine();
			stab = ligne.trim().split("\\s+");
			for(int j = 0; j < stab.length ; j++){
				tab[0][j+1] = Double.parseDouble(stab[j]);
			}
		//--------------------------DonnÃ©es-----------------------------------
			
			int i = 1;
			while ((ligne=br.readLine())!=null){
				stab = ligne.trim().split("\\s+");
				
				for (int j = 0; j < stab.length-1; j++) {
					tab[i][j+1] = Double.parseDouble(stab[j]);
				}
				tab[i][(int) (n+m+1)] = Double.parseDouble(stab[(int) (stab.length-1)]);
				i++;
			}
			br.close(); 
			

			for(i = 1 ; i < m+1 ; i++){
				tab[i][(int) (n+i)] = 1.0;
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	
	public void affiche(){
		for(int i = 0 ; i < (this.m+1) ; i++){
			for (int j = 0; j < (this.n+m+2); j++) {
				System.out.print(tab[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	
	public int varEntrante()
	{
		for( int j=1 ; j<n+m+1; j++)//Les variables se situent à partir de l'indice 1
		{	
			if(tab[0][j]>0)//Lorsqu'on trouve la première positif car ordre LEXICO
				return j;
		}
		
		return -1;//A ce retour l'algo est finit
	}
	
	public int varSortante(int varE)//Il nous faut l'indice de la variable entrante
	{
		int n = (int)this.n;
		int m = (int)this.m;
		int cmpt = 0;
		int min = -1;//On définit un minimum pour choisir notre variable d'écart qui va sortir
		
		if(varE == -1)
		{
			return -1;
		}
		for( int i=1 ; i<m+1; i++)
		{
			if( tab[i][varE]!=0.0 )//On regarde si l'équation contient la variable entrante
			{
				if(cmpt==0)//Pour s'assurer qu'on ne fasse l'initialisation qu'une seule fois
				{
					min = n+i;//Par exemple, pour avoir X3 on fait 2 variables + 1 etc...
					cmpt++;
				}
				tab[i][0] = tab[i][n+m+1]/tab[i][varE];//On divise la valeur entière en fin de tableau par le nombre de x dans cette même ligne
				if( tab[i][0] < tab[min][0] )//On regarde la variable d'écart la plus petite
					min = i;
			}
				
		}
		
		return min;
	}
	
	public void pivot(int entrant, int sortant)
	{
		for(int i=0; i<m+1; i++)
		{
			for(int j=entrant+1; j<n+m+2; j++)
			{
				if(i!=(int)((sortant-m)+1))//Si on est pas dans la ligne du pivot
				{	
					tab[i][j] = tab[i][j]-((tab[i][entrant]*tab[(int) ((sortant-m)+1)][j])/(tab[entrant][(int) ((sortant-m)+1)]));//La formule que j'effectue pour faire le pivot de gauss sous forme de produit en croix
				}
			}
		}
		
		double pivot = tab[(int)(sortant-m)+1][entrant];//On lui attribue la valeur du pivot
		for(int j=1; j<n+m+2; j++)
		{
			tab[(int)(sortant-m)+1][j] = tab[(int)(sortant-m)+1][j]/pivot;//On divise chaque valeur de la ligne par le pivot 
		}
		for(int i=0; i<m+1; i++)//On met la colonne du pivot à 0
		{
			tab[i][0] = 0.0;
			if(i!=(sortant-m)+1)
				tab[i][entrant] = 0.0;
		}
	}
	
	public boolean solve(Simplexe s)
	{
		int val = 0;//Condition d'arrêt
		while(val!=-1)
		{
			if(val!=-1)
			{
				val = s.varSortante(s.varEntrante());
				s.pivot(s.varEntrante(),s.varSortante(s.varEntrante()));
			}
		}
		
		return true;
	}
	
	public static void main(String[] args){
		Simplexe s = new Simplexe();
		s.lecture("exemple.txt");
		//s.solve(s);
		s.affiche();
		int a = s.varEntrante();
		System.out.println("L'indice de la variable entrante est : " + a);
		int  c = s.varSortante(a);
		System.out.println("L'indice de la variable sortante est : " +c);
		s.pivot(a,c);
		s.affiche();
		a = s.varEntrante();
		System.out.println("L'indice de la variable entrante est : " + a);
		c = s.varSortante(a);
		System.out.println("L'indice de la variable sortante est : " +c);
		//s.pivot(a,c);
		//s.affiche();
	}
	
	
	
}
