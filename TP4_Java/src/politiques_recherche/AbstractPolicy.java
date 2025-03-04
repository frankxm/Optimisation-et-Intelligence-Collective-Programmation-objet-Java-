package politiques_recherche;
import java.util.Date;
import robots.Behaviour;

public abstract class AbstractPolicy {
	protected int nombre_phase;
	public AbstractPolicy(int n) {
		this.nombre_phase=n;
	}
	protected abstract int obtenir_duree(int index);
	protected abstract Behaviour obtenir_behaviour(int index);
	protected abstract void add(Behaviour bh,int inter);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
