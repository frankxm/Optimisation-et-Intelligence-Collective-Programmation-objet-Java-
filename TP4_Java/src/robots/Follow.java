package robots;

import Zone_miniere.AbstractProblem;
import Zone_miniere.Point;
import Zone_miniere.Sphere;

public class Follow extends Behaviour{
	public Follow(AbstractProblem g) {
		super(g);
	}
	public Follow() {
		// TODO Auto-generated constructor stub
	}

	public void move(Robot robot) {
		Point courant=robot.getPosition();
		Point cible =Robot.getbestlocation();
		Point deplacement=courant.move(cible, 0.05);
		robot.setPosition(deplacement);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
