package robots;

import Zone_miniere.AbstractProblem;
import Zone_miniere.Point;
import Zone_miniere.Sphere;
import utilitaires.Printer;

public class BasicMission {
	protected AbstractProblem gisement;
	protected int nombre_robots;
	protected Robot [] robot_liste;
	protected Printer printer;
	protected String source="data/robots.txt";

	public BasicMission(AbstractProblem gise,int nb) {
		this.gisement=gise;this.nombre_robots=nb;
		this.robot_liste=new Robot[nb];
		robots_initialisation();
		
		printer=new Printer(source);

	
	}
	public void robots_initialisation() {
		for(int i=0;i<this.nombre_robots;i++) {
			Point p=new Point(0.5,0.5);
			Robot r=new Robot(p);
			r.setBehavior(new Behaviour(this.gisement));
			set(i,r);
		}
	}
	public void set(int index,Robot r) {
		robot_liste[index]=r;
	}
	public Robot get(int index) {
		return this.robot_liste[index];
	}
	
	public void run() {
//		3轮调试，100轮看图
		for(int i=0;i<3;i++) {
			System.out.println("epoch "+(i+1));
			System.out.println("la situation de teneur: ");
			collect();
			store();
			System.out.println("le teneur meilleur dans ce epoch cherche par tous les robots est "+Robot.getAllBestGain());
			System.out.println();
			System.out.println("la situation de deplacement de point: ");
			walk();
		}
		System.out.println("le teneur meilleur dans tous les epochs cherche par tous les robots est "+Robot.getAllBestGain());
		printer.close();
	}
	public void walk() {
		System.out.println("dans la fonction walk:");
		for(int i=0;i<this.nombre_robots;i++) {
			Robot robot_courant=robot_liste[i];
			System.out.println("robot "+i+": ");
			robot_courant.walk();
			System.out.println("le robot "+i+" deplace au point "+robot_courant.getPosition().obtenir_abscisse()+" , "+robot_courant.getPosition().obtenir_ordonne());
			System.out.println();
		}
	}
	public void collect() {
		System.out.println("dans la fonction collect:");
		for(int i=0;i<this.nombre_robots;i++) {
			System.out.println("robot "+i+": ");
			Robot robot_courant=robot_liste[i];
			Point p_courant=robot_courant.getPosition();
			double gain=gisement.teneur(p_courant);
			System.out.println("teneur maintenant "+gain);
			if(gain>robot_courant.getBestGain()) {
				robot_courant.changeteneur(gain);
				robot_courant.changelocation(p_courant);
			}
			if (robot_courant.getBestGain()>Robot.getAllBestGain()) {
				robot_courant.changeteneur_meilleur(robot_courant.getBestGain());
				robot_courant.changelocation_meilleur(p_courant);
				System.out.println("la location meilleur globale maintenant est "+p_courant.obtenir_abscisse()+","+p_courant.obtenir_ordonne());
			}
			System.out.println("le teneur meilleur cherche par robot"+i+" est "+robot_courant.getBestGain());
			System.out.println();
		}
	}
	public void store() {
		printer.println("-1");
		for(int i=0;i<this.nombre_robots;i++) {
			Robot robot_courant=robot_liste[i];
			printer.println(robot_courant.getPosition().obtenir_abscisse()+"\t"+robot_courant.getPosition().obtenir_ordonne());
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sphere pb=new Sphere();
		BasicMission bm=new BasicMission(pb,20);
		bm.run();
		Viewer.display(pb);
	}

}
