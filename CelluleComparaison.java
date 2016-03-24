
public class CelluleComparaison {
	
	private char c1;
	private char c2;
	private String s1;
	
	public CelluleComparaison()
	{
		this.c1 = '\0';
		this.c2 = '\0';
		this.s1 = "";
	}
	
	public CelluleComparaison(char c, char c2)
	{
		this.c1 = c;
		this.c2 = c2;		
	}
	
	public CelluleComparaison(char c, String s1)
	{
		this.c1 = c;
		this.s1 = s1;		
	}
	
	public char getC1()
	{
		return this.c1;
	}
	
	public char getC2()
	{
		return this.c2;
	}
	
	public String getS1()
	{
		return this.s1;
	}
}
