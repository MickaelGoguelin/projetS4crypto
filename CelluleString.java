
public class CelluleString {
	
	public String caractere;
	public double nbOccur;

	public CelluleString()
	{
		this.caractere = "";
		this.nbOccur = 0;
	}
	
	public CelluleString(String caractere, int nbOccur)
	{
		this.caractere = caractere;
		this.nbOccur = nbOccur;
	}
	
	public CelluleString(CelluleString c)
	{
		this.caractere = c.getCaractere();
		this.nbOccur = c.getNbOccur();
	}
	
	public CelluleString comparaison(String s, String s2)
	{
		return new CelluleString(s2,0);
	}
	
	public void incrementer()
	{
		this.nbOccur++;
	}
	
	public void affiche()
	{
		System.out.println(this.caractere+" : "+this.nbOccur);
	}

	public String getCaractere() {
		return caractere;
	}

	public void setCaractere(String caractere) {
		this.caractere = caractere;
	}

	public double getNbOccur() {
		return nbOccur;
	}

	public void setNbOccur(double nbOccur) {
		this.nbOccur = nbOccur;
	}

	
	
}
