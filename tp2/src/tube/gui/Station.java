package tube.gui;

public class Station {
	private String nom;
	private int x,y;

	
	public Station(String nm,int a,int b){
		nom=nm;
		x=a;
		y=b;
	}
	public String getName() {
		return nom;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public double norm() {
		return Math.sqrt(x*x+y*y);
	}
	
	public double angle(Station s1, Station s2) {
		double dot=(s1.x-x)*(s2.x-x)+(s1.y-y)*(s2.y-y);
		double norm1=Math.sqrt((s1.x-x)*(s1.x-x)+(s1.y-y)*(s1.y-y));
		double norm2=Math.sqrt((s2.x-x)*(s2.x-x)+(s2.y-y)*(s2.y-y));
		if ((norm1==0)||(norm2==0)) {
			System.out.println("Angle only defined for 3 distinct stations");
			System.exit(-1);
		}
		
		double cosinus=dot/(norm1*norm2);
		if (cosinus>1)
			return 0.0; // in case of floating point error
		return Math.acos(cosinus);
		
	}
}
