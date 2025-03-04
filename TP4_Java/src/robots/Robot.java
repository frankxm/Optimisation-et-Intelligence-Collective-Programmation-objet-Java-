package robots;
import Zone_miniere.Point;

public class Robot {
	private static Point G;
	private static double teneur_meilleur;
	private double teneur_par_robot;
	private Behaviour behaviour;
	private Point courant;
	private Point locale;
	public Robot(Point p) {
		behaviour=null;

		courant=p;
		locale=p;
		teneur_meilleur=Double.NEGATIVE_INFINITY;
		teneur_par_robot=Double.NEGATIVE_INFINITY;
		G=new Point(0.5,0.5);
	}
	public void walk(){
		if (behaviour!=null)
			behaviour.move(this); 
		}
	public void setBehavior(Behaviour behav) {
		behaviour=behav;
	}
	public Point getPosition() {
		return this.courant;
	}
	public void setPosition(Point p) {
		this.courant=p;
	}
	public double getBestGain() {
		return this.teneur_par_robot;
	}
	public static double getAllBestGain() {
		return Robot.teneur_meilleur;
	}
	public static Point getbestlocation() {
		return Robot.G;
	}
	public Point getlocation_par_robot() {
		return this.locale;
	}
	public void changeteneur(double g) {
		this.teneur_par_robot=g;
	}
	public void changeteneur_meilleur(double g) {
		Robot.teneur_meilleur=g;
	}
	public void changelocation_meilleur(Point p) {
		Robot.G=p;
	}
	public void changelocation(Point p) {
		this.locale=p;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
