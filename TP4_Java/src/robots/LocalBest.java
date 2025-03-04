package robots;

import Zone_miniere.Point;
import Zone_miniere.Sphere;
import Zone_miniere.AbstractProblem;

public class LocalBest extends Behaviour{
	public LocalBest(AbstractProblem g) {
		super(g);
	}
	public LocalBest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Robot robot) {
		System.out.println("enter localbest");
		Point courant=robot.getPosition();
		Point deplacement=courant.move(robot.getlocation_par_robot(), 0.05);
		robot.setPosition(deplacement);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
