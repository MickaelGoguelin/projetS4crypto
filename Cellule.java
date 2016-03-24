
public class Cellule {
	
	private char caractere;
	private double nbOccur;
	
	public Cellule()
	{
		this.caractere = '\0';
		this.nbOccur = 0;
	}
	
	public Cellule(char caractere, int nbOccur)
	{
		this.caractere = caractere;
		this.nbOccur = nbOccur;
	}
	
	public Cellule(Cellule c)
	{
		this.caractere = c.getCaractere();
		this.nbOccur = c.getNbOccur();
	}
	
	public Cellule comparaison(char c, char c2)
	{
		return new Cellule(c2,0);
	}
	
	public void incrementer()
	{
		this.nbOccur++;
	}
	
	public void affiche()
	{
		System.out.println((int)this.caractere+"-"+this.caractere+" : "+this.nbOccur);
	}
	
	public char getCaractere() {
		return caractere;
	}
	public void setCaractere(char caractere) {
		this.caractere = caractere;
	}
	public double getNbOccur() {
		return nbOccur;
	}
	public void setNbOccur(double nbOccur) {
		this.nbOccur = nbOccur;
	}
	
}
