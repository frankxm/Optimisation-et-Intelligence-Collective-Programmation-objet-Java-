package tube;

import javax.swing.JOptionPane;

import TP2.Lines;
import TP2.Path;
import tube.gui.TubeView;
import utilitaires.TabFileReader;
import utilitaires.TermList;
public class Control {
	private TubeView tv;
	private String begin, end;
	private Network network;
	private Lines lines;
	private boolean existe1;
	private boolean existe2;
//	private Path path;
	public Control(Network tub, TubeView tuv){
		tv=tuv;
		begin=end=null;
		this.network=new Network();
		network.lecture_reseau();
		network.creer_liste();
		lines=new Lines(network);
		this.existe1=false;
		this.existe2=false;
//		this.path=new Path();
	}
	public void clearItinerary(){
      	System.out.println("USER ACTION: clear the map");
		begin=end=null;
	}
	public void analyse(String s1,String s2,TermList stations_showed) {
    	for (int i=0;i<stations_showed.size();i++) {
    		if(stations_showed.termAt(i).equals(s1)) {
    			existe1=true;
    		}
    		else if(stations_showed.termAt(i).equals(s2)) {
    			existe2=true;
    		}
    	}
    }
	public TermList find_all_stations(String station,TermList stations_showed) {
		TabFileReader.readTextFile("steps.txt",'\t',"data");
		for(int i=0;i<TabFileReader.nrow();i++) {
			if(TabFileReader.wordAt(i, 1).equals(station)) {
				existe1=false;existe2=false;
				analyse(TabFileReader.wordAt(i, 0),TabFileReader.wordAt(i, 2),stations_showed);
				if(!existe1) {
					stations_showed.add(TabFileReader.wordAt(i, 0));
				}
				if(!existe2) {
					stations_showed.add(TabFileReader.wordAt(i, 2));
				}
			}
		}
		return stations_showed;
	}
	 /* -----------------------------------------------------------
	  * show the stations belonging to a line.
	  * -----------------------------------------------------------
	  */
	  public void showLine(int numLigne){
		TermList stations_showed=new TermList();
     	TermList list=null;
     	String station=this.lines.find_concrete_station(numLigne);
     	System.out.println("USER ACTION: line selection = "+station+" et index= "+numLigne+"");
     	list=find_all_stations(station,stations_showed);
     	for (int i=0;i<stations_showed.size();i++){
    		String term=stations_showed.termAt(i);
    		System.out.print(i+" :"+""+term+"");
    	}	
     	System.out.println(" ");
		System.out.println("le nombre de stations dans ce ligne est "+stations_showed.size());
     	tv.show(list);
	  }
	/* -----------------------------------------------------------
	 * show an itinerary between two stations.
	 * -----------------------------------------------------------
	 */	  
	  public void showItinerary(int x, int y){
		TabFileReader.readTextFile("steps.txt",'\t',"data");
     	String station=tv.findClosestStation(x,y);
     	System.out.println("USER ACTION: station selection = "+station);
     	TermList sel=new TermList();
     	Path path=new Path();
     	if (begin==null) {
//     		System.out.println("enter beginnull");
     		begin=station;
     		sel.add(begin);
     		System.out.println("Le depart est "+begin);
     		tv.show(sel);
     	}
     	else if (end==null) {
//     		System.out.println("enter endnull");
     		end=station;
    		TermList selection=null;		
    		TermList visited=path.recherche(begin,end);
    		selection=visited;
     		if (selection==null) {
     			JOptionPane.showMessageDialog(tv, "No path has been found.");
     			sel=null;
     			begin=null;
     			end=null;
     		}
     		else {
     			JOptionPane.showMessageDialog(tv, "Path has been found ");
     			System.out.println("Path est ci-desouss :");
     			for (int i=0;i<visited.size();i++){
     	    		String term=visited.termAt(i);
     	    		System.out.print(i+": "+term);
     	    		System.out.print(" ");
     	    	}	
     			System.out.println(" ");
     		  	System.out.println("le nombre de visited est "+visited.size());
     		  	sel=null;
     			begin=null;
     			end=null;
     		}
     		tv.show(selection);
     	}
	  }

}
