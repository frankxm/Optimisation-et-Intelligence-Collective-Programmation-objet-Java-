package TP2;
import utilitaires.TabFileReader;
import utilitaires.TermList;
import tube.Network;
public class Lines {
	private TermList station_lines;
	public static boolean existe=false;
	public Lines(Network network) {
		this.station_lines=new TermList();
		this.parcours(network);
	}
	public void analyse(String l) {
    	for (int i=0;i<this.station_lines.size();i++) {
    		if(this.station_lines.termAt(i).equals(l)) {
    			existe=true;
    		}
    	}
    }
	private void parcours(Network network) {
		for(int i=0;i<network.nsteps();i++) {
			String l=network.stepAt(i).getLigne();
			existe=false;
			analyse(l);
			if(!existe) {
				this.station_lines.add(l);
			}
		}
	}

	public TermList findStations(int numLigne) {
		TabFileReader.readTextFile("stations.txt",'\t',"data");
		TermList tl=new TermList();
		for (int i=0;i<TabFileReader.nrow();i++) {
			int n1=Integer.parseInt(TabFileReader.wordAt(i, 1));
			int n2=Integer.parseInt(TabFileReader.wordAt(i, 2));
			if(numLigne==n1||numLigne==n2) {
				tl.add(TabFileReader.wordAt(i,0));
			}
		}
		return tl;
	}
	public String find_concrete_station(int index) {
		return this.station_lines.termAt(index);
	}
	public TermList getStationlines() {
		return this.station_lines;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TabFileReader.main(args);
		Network network=new Network();
		network.lecture_reseau();
		network.creer_liste();
		Lines lines=new Lines(network);
		for (int i=0;i<lines.station_lines.size();i++){
    		String term=lines.station_lines.termAt(i);
    		System.out.println(i+" :"+""+term+"");
    	}	
	  	System.out.println(lines.station_lines.size());
//		TermList tl=lines.findStations(126);
//		for (int i=0;i<tl.size();i++){
//    		String term=tl.termAt(i);
//    		System.out.println(term);
//    	}	
//	  	System.out.println(tl.size());

	}

}
