package TP2;
import java.util.Scanner;
import utilitaires.TabFileReader;
public class Step {

	private String station_premier,station_seconde,station_ligne;
	public int row=TabFileReader.ncol();
	public int col=TabFileReader.nrow();
	public Step(String station1,String ligne,String station2){
		this.station_premier=station1;this.station_seconde=station2;this.station_ligne=ligne;
	}
	public String getNext(String station) {
		String renvoi_station = null;
		if(this.station_premier.equals(station)) {
			renvoi_station=this.station_seconde;
		}
		else if(this.station_seconde.equals(station)) {
			renvoi_station=this.station_premier;
		}
		return renvoi_station;
	}
	public String getLigne() {
		return this.station_ligne;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TabFileReader.main(args);
		String station1,ligne,station2;
		Scanner myObj = new Scanner(System.in); 
		System.out.println("Entrez des parametres");
	    String userName = myObj.nextLine();  
	    System.out.println("UserName = " + userName); 	
	    String [] station_list=userName.split("\t");
	    station1=station_list[0];station2=station_list[2];ligne=station_list[1];
		Step step=new Step(station1, ligne, station2); 
	    System.out.println("Entrez station pour consulter");
	    String userName1 = myObj.nextLine();  
	    String r_station=step.getNext(userName1);
	    System.out.println(" r_station "+r_station);
	}

}
