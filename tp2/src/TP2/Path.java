package TP2;
import utilitaires.TabFileReader;
import utilitaires.TermList;
import tube.gui.TubeView;
import tube.Network;
public class Path {
	private TermList visited;
	private String current;
	private Network network;
	private TubeView tubeview;
	public Path(){
		this.visited=new TermList();
		this.current=null;
		this.network=new Network();
		network.lecture_reseau();
		network.creer_liste();
		this.tubeview=new TubeView(this.network);
	}
	public boolean analyse(String s) {
		boolean flag=false;
		for(int i=0;i<this.visited.size();i++) {
			if(this.visited.termAt(i).equals(s)) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	public String recherche_suivante(String arrivee) {
		String next=null;
		double angleMin=6.284;	
		for(int i=0;i<network.nsteps();i++) {
			Step sp=network.stepAt(i);
			String nextS=sp.getNext(this.current);
			boolean existe=analyse(nextS);
			if(nextS!=null&&!existe) {
				double angle=this.tubeview.angle(this.current, nextS,arrivee );
				if(angle<angleMin) {
					angleMin=angle;
					next=nextS;
				}
			}
		}
		return next;
	}
	public TermList recherche(String depart,String arrivee ) {
		this.current=depart;
		boolean fini=false;
		while(!fini) {
			visited.add(current);
			if(current.equals(arrivee)) {
				return visited;
			}
			else {
				String next=recherche_suivante(arrivee);
				if(next==null) {
					for (int i=0;i<visited.size();i++){
	     	    		String term=visited.termAt(i);
	     	    		System.out.print(i+": "+term);
	     	    		System.out.print(" ");
	     	    	}	
	     			System.out.println(" ");
	     		  	System.out.println("le nombre de visited est "+visited.size());
					fini=true;
				}
				else {
					this.current=next;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TabFileReader.main(args);
		Path path=new Path();
		TermList visited=path.recherche("Bond Street","Leicester Square");
		for (int i=0;i<visited.size();i++){
    		String term=visited.termAt(i);
    		System.out.println(term);
    	}	
	  	System.out.println(visited.size());
	}

}
