package robots;

import Zone_miniere.AbstractProblem;
import Zone_miniere.Point;
import Zone_miniere.Sphere;

public class Behaviour {
	protected AbstractProblem gisement;
	public Behaviour(AbstractProblem g) {
		this.gisement=g;
	}
	public Behaviour() {
		// TODO Auto-generated constructor stub
	}
	void move(Robot robot) {
		System.out.println("enter behaviour");
		Point A=new Point();
		Point O=new Point(0.5,0.5);
		Point courant=robot.getPosition();
		double xp=courant.obtenir_abscisse();double yp=courant.obtenir_ordonne();
		double xa=A.obtenir_abscisse();double ya=A.obtenir_ordonne();
		double xo=O.obtenir_abscisse();double yo=O.obtenir_ordonne();
		double xoa=xa-xo;double yoa=ya-yo;
		Point cible=new Point(xp+xoa,yp+yoa);
		Point deplacement=courant.move(cible, 0.05);
		robot.setPosition(deplacement);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p=new Point(0.5,0.5); // position initiale pour le robot 
		Robot robot=new Robot(p);
		Sphere pb=new Sphere();
		Behaviour explore=new Behaviour(pb);
		robot.setBehavior(explore); // le robot possède le comportement explore
		for (int i=0;i<20;i++){
			robot.walk(); 
			System.out.println(i+" : "+robot.getPosition().obtenir_abscisse()+" , "+robot.getPosition().obtenir_ordonne());
		}
	}

}
