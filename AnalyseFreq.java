import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalyseFreq {
	
	private ArrayList<Cellule> tabRef ;
	private ArrayList<CelluleString> tabRefS;
	private ArrayList<Cellule> tabCrypt;
	private ArrayList<CelluleString> tabCryptS;
	private String msg;
	private String msgEssai;
	private double compteur;
	
	public AnalyseFreq()
	{
		this.tabRef  = new ArrayList<Cellule>();
		this.tabRefS  = new ArrayList<CelluleString>();
		this.tabCrypt  = new ArrayList<Cellule>();
		this.tabCryptS  = new ArrayList<CelluleString>();
		this.compteur = 0;
	}
	
	public AnalyseFreq(ArrayList f1, ArrayList f2, ArrayList f3, int compteur)
	{
		this.tabRef  = f1;
		this.tabCrypt = f2;
		this.tabCrypt = f3;
		this.compteur = compteur;
	}
	
	public void transform(String nomFichier) throws IOException
	{
		  BufferedReader reader;
		  FileWriter fw = new FileWriter ("new_en.txt");
		  char c = '\0';
		  String s = "";
		  String normalized;
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  s = s+(char)reader.read();
			  }
			        StringBuffer result = new StringBuffer();
			        if(s!=null && s.length()!=0) {
			            int index = -1;
			            char c1 = (char)0;
			            //String chars1	= "ÆæŒœ";
			            //String replace1	= "AEaeOEoe";
			            String chars2	= "ÀàÂâÄäÆæÉéÈèÊêËëÎîÏïÔôÖöÙùÛûÜüÇç";
			            String replace2	= "AaAaAaAaEeEeEeEeIiIiOoOoUuUuUuCc";
			            System.out.println("C'est parti");
			            for(int i=0; i<s.length(); i++) {
			                c1 = s.charAt(i);
			                if( (index=chars2.indexOf(c1))!=-1 )
			                    result.append(replace2.charAt(index));
			                else
			                    result.append(c1);
			            }
			        }
			        fw.write(result.toString());
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	public void initTab(String nomDeFichier) throws IOException
	{
		lecture(nomDeFichier,true);
		
		ArrayList<Cellule> tabTmp = new ArrayList<Cellule>();
		Cellule max = new Cellule();
		int i;
		int pos=0;
		int j=0;
		
		do//On va trier le tableau par ordre décroissante du nb d'occurence
		{
			max.setCaractere(tabRef .get(0).getCaractere());
			max.setNbOccur((tabRef .get(0).getNbOccur()/compteur)*100);
			
			for(i=0; i<tabRef .size(); i++)
			{
				if((this.tabRef .get(i).getNbOccur()/compteur)*100 >= max.getNbOccur())
				{
					max.setCaractere(tabRef .get(i).getCaractere());
					max.setNbOccur((tabRef .get(i).getNbOccur()/compteur)*100);
					pos = i;
				}
			}
			tabTmp.add(j,new Cellule(max));//On stocke tout dans une variable temporaire
			this.tabRef .remove(pos);
			j++;
		}while(tabRef .size()>0);
		
		this.tabRef .clear();//On supprimme l'ancien tableau non trié
		this.tabRef  = new ArrayList<Cellule>(tabTmp);//On crée le nouveau tabRef trié
	}
	
	public void initTabRefS(String nomDeFichier) throws IOException
	{
		lecture(nomDeFichier,true);
		
		ArrayList<CelluleString> tabTmp = new ArrayList<CelluleString>();
		CelluleString max = new CelluleString();
		int i;
		int pos=0;
		int j=0;
		
		do//On va trier le tableau par ordre décroissante du nb d'occurence
		{
			max.setCaractere(tabRefS .get(0).getCaractere());
			max.setNbOccur((tabRefS .get(0).getNbOccur()/compteur)*100);
			
			for(i=0; i<tabRefS .size(); i++)
			{
				if((this.tabRefS .get(i).getNbOccur()/compteur)*100 >= max.getNbOccur())
				{
					max.setCaractere(tabRefS .get(i).getCaractere());
					max.setNbOccur((tabRefS .get(i).getNbOccur()/compteur)*100);
					pos = i;
				}
			}
			tabTmp.add(j,new CelluleString(max));//On stocke tout dans une variable temporaire
			this.tabRefS .remove(pos);
			j++;
		}while(tabRefS .size()>0);
		
		this.tabRefS .clear();//On supprimme l'ancien tableau non trié
		this.tabRefS  = new ArrayList<CelluleString>(tabTmp);//On crée le nouveau tabRef trié
	}
	
	public void initTabCrypt(String nomDeFichier) throws IOException
	{
		lecture(nomDeFichier,false);
		
		ArrayList<Cellule> tabTmp = new ArrayList<Cellule>();
		Cellule max = new Cellule();
		int i;
		int pos=0;
		int j=0;
		
		do
		{
			max.setCaractere(tabCrypt.get(0).getCaractere());
			max.setNbOccur((tabCrypt.get(0).getNbOccur()/compteur)*100);
			
			for(i=0; i<tabCrypt.size(); i++)
			{
				if((this.tabCrypt.get(i).getNbOccur()/compteur)*100 >= max.getNbOccur())
				{
					max.setCaractere(tabCrypt.get(i).getCaractere());
					max.setNbOccur((tabCrypt.get(i).getNbOccur()/compteur)*100);
					pos = i;
				}
			}
			tabTmp.add(j,new Cellule(max));
			this.tabCrypt.remove(pos);
			j++;
		}while(tabCrypt .size()>0);
		
		this.tabCrypt .clear();
		this.tabCrypt  = new ArrayList<Cellule>(tabTmp);
	}
	
	public void initTabCryptS(String nomDeFichier) throws IOException
	{
		this.lectureParQuatre(nomDeFichier);
		//lectureSpecial(nomDeFichier2);
		
		ArrayList<CelluleString> tabTmp = new ArrayList<CelluleString>();
		CelluleString max = new CelluleString();
		int i;
		int pos=0;
		int j=0;
		do
		{
			max.setCaractere(tabCryptS.get(0).getCaractere());
			max.setNbOccur((tabCryptS.get(0).getNbOccur()/compteur)*100);
			
			for(i=0; i<tabCryptS.size(); i++)
			{
				if((this.tabCryptS.get(i).getNbOccur()/compteur)*100 >= max.getNbOccur())
				{
					max.setCaractere(tabCryptS.get(i).getCaractere());
					max.setNbOccur((tabCryptS.get(i).getNbOccur()/compteur)*100);
					pos = i;
				}
			}
			tabTmp.add(j,new CelluleString(max));
			this.tabCryptS.remove(pos);
			j++;
		}while(tabCryptS .size()>0);
		
		this.tabCryptS .clear();
		this.tabCryptS  = new ArrayList<CelluleString>(tabTmp);
	}
	
	
	public void lecture(String nomFichier, boolean v) throws IOException
	{
		  BufferedReader reader;
		  char c;
		  String s = "";
		  int cmpt=0;
		  this.compteur = 0;
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  /*NORMAL
				  c = (char)reader.read();
				  if(v)//Si true, on initialise tabRef
					  stocker(c,this.tabRef);
				  else//Sinon on initialise tabCrypt
					  stocker(c,this.tabCrypt);
				  compteur++;
				  */
				  c = (char)reader.read();
				  if(cmpt==1)
				  {
					  s = s+c;
					  stockerSpecial(s,this.tabRefS);
					  s = "";
					  compteur++;
					  cmpt = 0;
				  }
				  else
				  {
					  s = s+c;
					  cmpt++;
				  }
			  }
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	public void lectureAvecCle(String nomFichier, int cle) throws IOException
	{
		  BufferedReader reader;
		  MessageClair m = new MessageClair();
		  int cmpt = 0;
		  char c;
		  int nb=0;;
		  String s = "";
		  String s1 = "";
		  String s2 = "";
		  String test = "";
		  this.compteur = 0;
		  this.msg = "";
		  boolean b = false;//Pour savoir si on rencontre un séparateur
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  if(c == '-')
				  {
					  /*
						  nb = Integer.parseInt(s);//"4033" -> 4033
						  nb = nb - cle;//4033 - 62 = 3961
						  s = ""+nb;//3961 -> "3961"
						  if(s.length()==4)
						  {
							  s1 = ""+s.charAt(s.length()-4)+s.charAt(s.length()-3);
							  s2 = ""+s.charAt(s.length()-2)+s.charAt(s.length()-1);//"3961" -> "61" Car seuls les deux derniers chiffres nous intéressent
							  nb = Integer.parseInt(s)%74;//"61"->61
							  msg = msg + s1 + m.inPos(nb);//61 -> \t
						  }
						  s = "";
						  */
					  nb = Integer.parseInt(s);
					  nb = (nb - cle)%74;
					  s = ""+nb;
					  if(s.length()==3)
						  s = "0"+s;
					  else if(s.length()==2)
					  	  s = "00"+s;
					  else if(s.length()==1)
						  s = "000"+s;
					  else
						  s = "0000";
					  System.out.println(s);
					  msg = msg+
							  m.inPos(s.charAt(0))+
							  m.inPos(s.charAt(1))+
							  m.inPos(s.charAt(2))+
							  m.inPos(s.charAt(3));
				  }
				  else
				  {	
					  s = s+c;
				  }
					  /*
					  if(!msg.equals(""))
					  {
						  nb = Integer.parseInt(s);
						  s = "";
						  cmpt=0;
					  		if(nb>=cle)
					  		{
					  			nb = nb - cle;
					  			msg = msg + m.inPos(nb);
					  		}
					  		else//Si le nombre est 33 on fait comme si l'on avait à faire à 133-62 pour retomber sur un nombre positif 71 & non -71
					  		{
					  			msg = msg + nb;
					  		}
					  }
					 else
					 {	
					  	cmpt=0;
					  	msg = msg+c;
					 }
				  }
				  else if(cmpt<2)
				  {
					 msg = msg+c;
					 cmpt++;
				  }
				  else if(cmpt>=2)
				  {
					 s = s+c;
					 cmpt++;
				  }/*
				  //msg = msg + " ";*/
			  }
			  
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
		  
		  System.out.println(msg);
	}
	
	public void lectureParQuatre(String nomFichier) throws IOException
	{
		  BufferedReader reader;
		  int cmpt = 0;
		  char c;
		  String s = "";
		  String test = "";
		  this.compteur = 0;
		  boolean b = false;//Pour savoir si on rencontre un séparateur
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  if(c == '-')
				  {
					 stockerSpecial(s,tabCryptS);
					 s="";
					 compteur++;
				  }
				  else
				  {
					  s = s+c;
				  }
			  }
			  
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	public void lectureSpecial(String nomFichier) throws IOException
	{
		  BufferedReader reader;
		  int cmpt = 0;
		  char c;
		  String s = "";
		  String test = "";
		  this.compteur = 0;
		  
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  if(cmpt<2 && c!='-')
				  {
					 s = s+c;
					 cmpt++;
				  }
				  else//On coupe tous les deux morceaux
				  {  
					  if(c == '-')
					  {
						  stockerSpecial(s,this.tabCryptS);
						  test = test + s + " ";
						  s = "";
						  cmpt = 0;
						  compteur++;
					  }
					  else
					  {
						  stockerSpecial(s,this.tabCryptS);
						  test = test + s + " ";
						  s = ""+c;
						  cmpt = 1;
						  compteur++;
					  }
				  }
			  }
			  
			  System.out.println("Test: \n"+test);
			  
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
	}
	
	public void stocker(char c, ArrayList<Cellule> tab)
	{
		boolean existe = false;
		
		for(int i=0; i<tab.size(); i++)
		{
			if(tab.get(i).getCaractere()==c)//On parcourt tout le tableau à la recherche du caractère
			{
				tab.get(i).incrementer();//On incrémente le nombre d'occurence
				i=tab.size();
				existe = true;
			}
		}
		if(!existe)//Sinon on rajoute le charactère
		{
			tab.add(new Cellule(c,1));
		}
	}
	
	public void stockerSpecial(String s, ArrayList<CelluleString> tab)
	{
		boolean existe = false;
			
		for(int i=0; i<tab.size(); i++)
		{
			if(tab.get(i).getCaractere().equals(s))//On parcourt tout le tableau à la recherche du caractère
			{
				tab.get(i).incrementer();//On incrémente le nombre d'occurence
				i=tab.size();
				existe = true;
			}
		}
		if(!existe)//Sinon on rajoute le charactère
		{
			tab.add(new CelluleString(s,1));
		}
	}
	
	public ArrayList<CelluleComparaison> echange()
	{
		ArrayList<CelluleComparaison> tabE = new ArrayList<CelluleComparaison>();
		
		int taille;
		
		if(tabRef.size()>tabCrypt.size())
			taille = tabCrypt.size();
		else
			taille = tabRef.size();
		
		for(int i=0; i<taille; i++)
		{
			tabE.add(i,new CelluleComparaison(this.tabRef.get(i).getCaractere(),this.tabCrypt.get(i).getCaractere()));
		}
		
		return tabE;
	}

	public ArrayList<CelluleComparaison> echangeSpecial()//On crée un tableau contenant les caractères des deux tableaux réunis
	{
		ArrayList<CelluleComparaison> tabE = new ArrayList<CelluleComparaison>();
		
		int taille;
		
		if(tabRef.size()>tabCryptS.size())
			taille = tabCryptS.size();
		else
			taille = tabRef.size();
		
		for(int i=0; i<taille; i++)
		{
			tabE.add(i,new CelluleComparaison(this.tabRef.get(i).getCaractere(),this.tabCryptS.get(i).getCaractere()));
		}
		
		return tabE;
	}
	
	public void cesar(String nomFichier) throws IOException
	{
		//On initialise le tableau crypté
		initTabCrypt(nomFichier);
		//On crée le tableau prenant en compte les permutations
		ArrayList<CelluleComparaison> tabEchange = new ArrayList<CelluleComparaison>(echange());
		//On instancie Msg Crypté
		MessageCrypte msg = new MessageCrypte();
		msg.lire(nomFichier);
		
		/**Calcul**/
		int a = msg.inAlphabet(this.tabRef.get(0).getCaractere());
		int b = msg.inAlphabet(this.tabCrypt.get(0).getCaractere());
		int res = (a-b)%26;
		
		//System.out.println("Message crypté:\n"+msgCrypté);
		System.out.println("Message décrypté:\n"+msg.decrypterSuivantCle(res));
	}
	
	public void analyse(String nomFichier, int degre) throws IOException
	{
		
		initTabCrypt(nomFichier);
		ArrayList<CelluleComparaison> tabE = new ArrayList<CelluleComparaison>(echange());
		MessageCrypte msg = new MessageCrypte();
		
		//msg.lire(nomFichier);
		
		BufferedReader reader;
		 char c;
		 boolean ok = false;
		 String phrase = "";
		 
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  for(int i=0;i<tabE.size();i++)
				  {
					  if(i<degre && c == tabE.get(i).getC2())
					  {
						  phrase = phrase+tabE.get(i).getC1();
						  i = 1000;
						  ok = true;
					  }
					  else if(i==tabE.size()-1)
					  {
						  phrase = phrase+c;
					  	  i = 1000;
					  	  ok = true;
					  }
				  }
				  if(ok == false)
					  phrase = phrase + c;
				  else 
					  ok = true;
			  }
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
		  this.msg = phrase;
		  System.out.println("Message décrypté:\n"+phrase);
	}

	public void analyseSpecial(String nomFichier, int degre) throws IOException
	{
		ArrayList<CelluleComparaison> tabE = new ArrayList<CelluleComparaison>(echangeSpecial());//Ce tableau contiendra les caractères des deux autres tableaux réunis
		//MessageCrypte msg = new MessageCrypte();
		
		//msg.lire(nomFichier);
		
		BufferedReader reader;
		 int n;
		 int cmpt = 0;
		 boolean ok = false;
		 String s = "";
		 char c;
		 String phrase = "";
		 
		 //initTabCryptS(nomFichier);
		 
		  try
		  {
			  reader = new BufferedReader(new FileReader(nomFichier));
			  
			  while(reader.ready())//test si fin de fichier
			  {
				  c = (char)reader.read();
				  if(cmpt<2 && c!='-')
				  {
					 s = s+c;
					 cmpt++;
					 //System.out.println(s);
				  }
				  else//On coupe tous les deux morceaux
				  {  
					  for(int i=0;i<tabE.size()&&!(ok);i++)
					  {
						  if(i<degre && s.equals(tabE.get(i).getS1()))//On cherche...
						  {
							  phrase = phrase+tabE.get(i).getC1();
							  ok = true;
						  }
					  }
					  if(ok == false)//Si on a pas trouvé...
					  {
						  if(c == '-')
						  {
							  phrase = phrase + s;// + " ";
							  s = "";
							  cmpt = 0;
						  }
						  else
						  {
							  phrase = phrase + s;// + " ";
							  s = ""+c;
							  cmpt = 1;
						  }
					  }
					  else 
					  {
						  ok = false;
						  if(c == '-')
						  {
							  s = "";
							  cmpt = 0;
						  }
						  else
						  { 
							  s = ""+c;
							  cmpt = 1;
						  }
					  }
				  }
			  }
			  
		  }catch (FileNotFoundException e) {
			  System.err.println("lecture de '" + nomFichier + "' impossible : " + e);
		  }
		this.msg = phrase;
		  System.out.println("Message décrypté:\n"+phrase);
	}
	
	public void remplace(char c1, char c2)
	{
		if(msgEssai=="")
		{
			this.msgEssai = new String(msg);
		}
		else
		{
			msgEssai = msg;
			msgEssai = msgEssai.replace(c1,c2);
		}
		int reponse=1;
		String c11="";
		String c22="";
		char c111;
		char c222;
		do
		{
			System.out.println("char 1?");
			Scanner scan = new Scanner(System.in);
			c11 = scan.nextLine();
			System.out.println("char 2?");
			c22 = scan.nextLine();
			c111 = c11.charAt(0);
			c222 = c22.charAt(0);
			msgEssai = msgEssai.replace(c111,c222);
			System.out.println("Message decrypté:\n"+msgEssai+"\nVoulez-vous continuer (0-stop)?");
			reponse = scan.nextInt();
			System.out.println("ok");
		}while(reponse!=0);
		
		System.out.println("Message decrypté:\n"+msgEssai);
	}
	
	public void remplaceS(String s1, String s2)
	{
		if(msgEssai=="")
		{
			this.msgEssai = new String(msg);
		}
		else
		{
			msgEssai = msg;
			msgEssai = msgEssai.replace(s1,s2);
		}
		int reponse=1;
		String c11="";
		String c22="";
		char c111;
		char c222;
		do
		{
			System.out.println("char 1?");
			Scanner scan = new Scanner(System.in);
			c11 = scan.nextLine();
			System.out.println("char 2?");
			c22 = scan.nextLine();
			c111 = c11.charAt(0);
			c222 = c22.charAt(0);
			msgEssai = msgEssai.replace(c111,c222);
			System.out.println("Message decrypté:\n"+msgEssai+"\nVoulez-vous continuer (0-stop)?");
			reponse = scan.nextInt();
			System.out.println("ok");
		}while(reponse!=0);
		
		System.out.println("Message decrypté:\n"+msgEssai);
	}
	
	public void afficheTab()
	{
		System.out.println("TabRef\n");
		for(int i=0; i<tabRef .size(); i++)
		{
			tabRef .get(i).affiche();
		}
		System.out.println("TabCrypt\n");
		for(int i=0; i<tabCrypt.size(); i++)
		{
			tabCrypt.get(i).affiche();
		}
	}
	
	public void afficheTabSpecial()
	{
		double somme=0.0;
		System.out.println("TabRef\n");
		for(int i=0; i<tabRef .size(); i++)
		{
			tabRef .get(i).affiche();
		}
		System.out.println("TabRefS\n");
		for(int i=0; i<tabRefS .size(); i++)
		{
			tabRefS .get(i).affiche();
		}
		System.out.println("TabCryptS\n");
		for(int i=0; i<tabCryptS.size(); i++)
		{
			tabCryptS.get(i).affiche();
		}
	}
	
	public void afficheTabRef()
	{
		System.out.println("TabRef\n");
		for(int i=0; i<tabRef .size(); i++)
		{
			tabRef .get(i).affiche();
		}
	}

	public void afficheMsg()
	{
		System.out.println("Message décrypté:\n");
		System.out.println(this.getMsg());
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<Cellule> getTabRef() {
		return tabRef;
	}

	public void setTabRef(ArrayList<Cellule> tabRef) {
		this.tabRef = tabRef;
	}

	public ArrayList<Cellule> getTabCrypt() {
		return tabCrypt;
	}

	public void setTabCrypt(ArrayList<Cellule> tabCrypt) {
		this.tabCrypt = tabCrypt;
	}

	public ArrayList<CelluleString> getTabCryptS() {
		return tabCryptS;
	}

	public void setTabCryptS(ArrayList<CelluleString> tabCryptS) {
		this.tabCryptS = tabCryptS;
	}

	public double getCompteur() {
		return compteur;
	}

	public void setCompteur(double compteur) {
		this.compteur = compteur;
	}
	
	
	
	
}
