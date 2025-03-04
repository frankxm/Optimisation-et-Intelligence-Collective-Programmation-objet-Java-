package tube;
import java.util.Arrays;
import utilitaires.TabFileReader;
import utilitaires.TermList;
import TP2.Step;
public class Network {
	private Step []steps;
	private int nombre_step;
	public static int row;
	private TermList terms;
	public static boolean existe1=false;
	public static boolean existe2=false;
	public Network() {
		row=TabFileReader.nrow();
		steps=new Step[row];
		Step s=new Step("","","");
		Arrays.fill(this.steps,s);
		this.nombre_step=0;
		terms=new TermList();
	}
	public void lecture_reseau() {
		for(int i=0;i<row;i++) {
			String s1=TabFileReader.wordAt(i, 0);
			String s2=TabFileReader.wordAt(i, 2);
			String l=TabFileReader.wordAt(i, 1);
			Step step=new Step(s1,l,s2);
			this.steps[i]=step;
		}
		this.nombre_step=row;
	}
	public int nsteps() {
		return this.nombre_step;
	}
	public Step stepAt(int index) {
		return this.steps[index];
	}
    public void analyse(String s1,String s2) {
    	for (int i=0;i<terms.size();i++) {
    		if(terms.termAt(i).equals(s1)) {
    			existe1=true;
    		}
    		if(terms.termAt(i).equals(s2)) {
    			existe2=true;
    		}
    	}
    }
    public void creer_liste() {
		for(int i=0;i<row;i++) {
			String s1=TabFileReader.wordAt(i, 0);
			String s2=TabFileReader.wordAt(i, 2);
			existe1=false;existe2=false;
			analyse(s1,s2);
			if(!existe1) {
				terms.add(s1);
			}
			if(!existe2) {
				terms.add(s2);
			}
		}
	}
    public int numberOfStations(){
		return this.terms.size();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TabFileReader.main(args);
		Network network=new Network();
		network.lecture_reseau();
		network.creer_liste();
	  	for (int i=0;i<network.terms.size();i++){
    		String term=network.terms.termAt(i);
    		System.out.println(term);
    	}	
	  	System.out.println(network.terms.size());
	}

}
