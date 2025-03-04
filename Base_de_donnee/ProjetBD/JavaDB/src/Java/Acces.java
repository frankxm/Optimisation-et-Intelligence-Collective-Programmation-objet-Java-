/* -------------------------------------------------------------------------------------------------
 * Acces a une base de donnees dont le nom est contenu dans la chaine dbFileName.
 * On suppose que la base de donnees est dans le meme dossier que le projet Java.
 * Verifiez bien que c'est le cas en consultant Eclipse :
 * La localisation du projet est donne par le menu Project/Properties/Resource/Location.
 * Ce dossier doit etre le meme que celui renfermant la base de donnees.
 * -------------------------------------------------------------------------------------------------
 */
package Java;
import java.beans.Statement;
import java.io.File;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;  

public class Acces {
	
	//attributs
	private Connection connection;
	
	//constructeur
	public Acces(String dbFileName) {
	
	     try 
	     {  
	    	 String location=getLocationOfDataBase(dbFileName);
	         System.out.println("Location of database: "+location);
	         String url="jdbc:sqlite:"+location;
	         Class.forName("org.sqlite.JDBC");  
	         connection = DriverManager.getConnection(url); 
	         System.out.println("Connexion reussie");
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println("echec de connexion");
	         e.printStackTrace();  
	     }
	}
	
	public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = (Statement) connection.createStatement();
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error executing query: " + query);
            e.printStackTrace();
        }
        return resultSet;
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
	
	public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public Connection getConnection() {
        return connection;
    }


	public static void main(String[] args) 
	  {  
		
		 
		 // Demander à l'utilisateur de saisir la date et le genre
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Entrez la date (au format YYYY-MM-DD) : ");
	        String date = scanner.nextLine();

	        System.out.print("Entrez le genre de film : ");
	        String genre = scanner.nextLine();

	        // Établir la connexion à la base de données
	        Acces acces = new Acces("BDcinema.db");

	        // Construire et exécuter la requête SQL
	        String query = "SELECT * FROM cinemas WHERE date ="+date+"AND genre ="+genre;
	        try {
	            PreparedStatement preparedStatement = acces.getConnection().prepareStatement(query);
	            preparedStatement.setString(1, date);
	            preparedStatement.setString(2, genre);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            // Afficher les résultats
	            while (resultSet.next()) {
	                System.out.println("Cinéma trouvé : " + resultSet.getString("nom_cinema"));
	                // Vous pouvez ajouter d'autres informations sur le cinéma selon votre modèle de données
	            }
	        } catch (Exception e) {
	            System.out.println("Erreur lors de la recherche de cinéma.");
	            e.printStackTrace();
	        } finally {
	            // Fermer la connexion
	            acces.close();
	            scanner.close();
	        }
		 
	 }  

}
