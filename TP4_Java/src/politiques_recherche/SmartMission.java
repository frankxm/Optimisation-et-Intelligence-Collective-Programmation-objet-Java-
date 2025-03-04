package politiques_recherche;

import Zone_miniere.AbstractProblem;
import Zone_miniere.Eggholder;
import Zone_miniere.Sphere;
import robots.BasicMission;
import robots.Behaviour;
import robots.Follow;
import robots.LocalBest;
import robots.Robot;
import robots.Viewer;

public class SmartMission extends BasicMission {
	private SimplePolicy policy;
	private int iteration;
	public SmartMission(AbstractProblem gise, int nb,int n_policy) {
		super(gise, nb);
		// TODO Auto-generated constructor stub
		this.policy=new SimplePolicy(n_policy);
		this.iteration=0;

	}

	
	
	@Override
	public void run() {
		for(int i=0;i<this.policy.nombre_phase;i++) {
			System.out.println("strategie "+(i+1)+":");
			Behaviour behaviour=this.policy.obtenir_behaviour(i);
			this.iteration=this.policy.obtenir_duree(i);
			for(int j=0;j<nombre_robots;j++) {
				get(j).setBehavior(behaviour);;
			}

			for(int j=0;j<this.iteration;j++) {
				System.out.println("epoch "+(j));

				collect();
				store();
				walk();
			}

		}
		printer.close();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Eggholder pb=new Eggholder();
		SmartMission smartmission=new SmartMission(pb,60,3);
		smartmission.policy.add(new Behaviour(pb),50);
		smartmission.policy.add(new LocalBest(pb),30);
		smartmission.policy.add(new Follow(pb),30); 
		smartmission.run();
		Viewer.display(pb);
		
	}

}

