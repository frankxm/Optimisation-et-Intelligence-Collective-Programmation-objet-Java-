package politiques_recherche;

import robots.Behaviour;
import robots.Follow;
import robots.LocalBest;

public class SimplePolicy extends AbstractPolicy{
	private Behaviour []behaviour_liste;
	private int []temps_iteration;
	private int index;
	public SimplePolicy(int n) {
		super(n);
		// TODO Auto-generated constructor stub
		this.behaviour_liste=new Behaviour[n];
		this.temps_iteration=new int[n];
		this.index=0;
	}
	@Override
	public void add(Behaviour bh,int iter) {
		behaviour_liste[index]=bh;
		temps_iteration[index]=iter;
		index+=1;
	}

	@Override
	protected int obtenir_duree(int index) {
		// TODO Auto-generated method stub
		return this.temps_iteration[index];
	}
	@Override
	protected Behaviour obtenir_behaviour(int index) {
		// TODO Auto-generated method stub
		return this.behaviour_liste[index];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimplePolicy policy=new SimplePolicy(3);
		policy.add(new Behaviour(),60); // phase 0 de durée 60 itérations
		policy.add(new LocalBest(),20); // phase 1
		policy.add(new Follow(),20); // phase 2
		System.out.println(" le nombre de phase est "+policy.nombre_phase);
		for(int i=0;i<3;i++) {
			System.out.println(" le policy maintenant est "+policy.obtenir_behaviour(i));
			System.out.println(" la duree pour ce policy est "+policy.obtenir_duree(i));			
		}
	}

}
