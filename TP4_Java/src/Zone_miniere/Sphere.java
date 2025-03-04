	package Zone_miniere;

import java.awt.Dimension;

import robots.Viewer;

public class Sphere implements AbstractProblem{
	public Sphere() {
	}
	
	@Override
	public double teneur(Point position) {
		// TODO Auto-generated method stub
//		minimum de T est 0 en position (1,1),
//		o maximum de T est 1.0 en position (0,0).
		double T=1-(Math.pow(position.obtenir_abscisse(),2)+Math.pow(position.obtenir_ordonne(), 2))/2; // T in(0,1)
		double couleur=T*255;// T in(0,255)
		return couleur;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sphere pb=new Sphere();
		Viewer.display(pb);
	}

}
