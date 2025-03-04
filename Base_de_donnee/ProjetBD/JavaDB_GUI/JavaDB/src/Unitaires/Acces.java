package Unitaires;

//Bibliotheques pour effectuer des operations 
//des fichiers et la base de donnees et le clavier
import java.io.File;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Acces {
	private Connection isconnect;
	public Acces() {
//		l'attribut stocke en memoire
		this.isconnect=null;
		try 
	     {  
	    	 String location=getLocationOfDataBase("BDcinema.db");
	         System.out.println("Location of database: "+location);
	         String url="jdbc:sqlite:"+location;
	         Class.forName("org.sqlite.JDBC");  
	         
	         isconnect = DriverManager.getConnection(url); 
	         System.out.println("Connexion reussie");
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println("echec de connexion");
	         e.printStackTrace();  
	     }
	}
//	lorsque l'objet n'est plus utilise,liberer les ressources associes
	public void Close() {
        try 
        {  
            this.isconnect.close();  
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        } 
	}
	public ResultSet request(String sql) {
		ResultSet rs=null;
		try {
            Statement stmt = this.isconnect.createStatement();
            rs = stmt.executeQuery(sql);  
    	    
        } 
        catch (SQLException e) {
            System.out.println("Il y a des erreurs pendant la requete！"+e.getMessage());
            System.out.println("La requete SQL est "+sql);
        }
		return rs;
	}
	public void choisirfilm(String date,String genre) {
		String sql="SELECT ID_film, titre FROM Film Where annee='%s' And genre='%s'";
		sql=String.format(sql, date,genre);
		System.out.println("ID_film"+ "\t"+"titre"+ "\t");
		ResultSet rs=request(sql);
		try {
			while (rs.next()) {
			    System.out.println(rs.getString("ID_film") + "\t" + rs.getString("titre")+ "\t" );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
        
	}
	public void choisircinema(String id) {
		String sql="SELECT Cinema.nom, Cinema.localisation\r\n"
				+ "FROM Cinema\r\n"
				+ "JOIN Programmation ON Programmation.Ref_cine=Cinema.ID_cine\r\n"
				+ "JOIN Film ON Programmation.Ref_film = Film.ID_film\r\n"
				+ "WHERE Film.ID_film = '%s'";
		sql=String.format(sql,id);
		ResultSet rs=request(sql);
		System.out.println("nom"+ "\t"+"localisation"+ "\t");
		try {
			while (rs.next()) {
			    System.out.println(rs.getString("nom") + "\t" + rs.getString("localisation")+ "\t" );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getLocationOfDataBase(String dbFileName){
		//1. Retrieve the working directory: 
		//   the location of the java project (identical to Project/Properties/Resource/Location
        String path=System.getProperty("user.dir");
        File file = new File(path);
        // 2. Retrieve the parent directory
        String parentPath = file.getAbsoluteFile().getParent();
        // 3. concatenate the database file name
        String result=parentPath+System.getProperty("file.separator")+dbFileName;
        return result;
	}

	public static void main(String[] args) 
	  {  

		
	    Acces acces=new Acces();
	    Scanner myObj = new Scanner(System.in);  
	    System.out.println("Entrez date et genre de film");
	    String date = myObj.nextLine();
	    String genre = myObj.nextLine();
	    System.out.println("Date est " + date+" Genre est "+genre);
	    acces.choisirfilm(date,genre);
	    System.out.println("Entrez numero de film");
	    String id = myObj.nextLine();
	    System.out.println("Numero de film est " + id);
		acces.choisircinema(id);
		acces.Close();
	 }  

}
