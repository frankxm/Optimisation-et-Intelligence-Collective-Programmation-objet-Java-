package Zone_miniere;

import robots.Viewer;

public class Eggholder implements AbstractProblem {
	public double f(double a,double b) {
		int k=1024;
		double x=k*(a-0.5);
		double y=k*(b-0.5);
		double res=-(y+47)*Math.sin(Math.sqrt(Math.abs(x/2+(y+47))))-x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));
		return res;
	}
	@Override
	public double teneur(Point position) {
		// TODO Auto-generated method stub
		double T=f(position.obtenir_abscisse(),position.obtenir_ordonne())*(-1);// T in(-1048.13,959)
		double Min=-1049.13;double Max=959.64;
		T=(T-Min)/(Max-Min); //T in (0,1)
		double couleur=  T*255 ; //T in(0,255)
		return couleur;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Eggholder pb=new Eggholder();
		Viewer.display(pb);
	}

	

}
