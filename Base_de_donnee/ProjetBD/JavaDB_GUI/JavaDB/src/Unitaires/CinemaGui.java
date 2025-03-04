package Unitaires;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


class Database {
//	definir 5 attributs 
	private String id;
	private String name;
	private String attribut1;
	private String attribut2;
	private String attribut3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getattribut1() {
		return attribut1;
	}
	public void setattribut1(String attribut1) {
		this.attribut1 = attribut1;
	}
	public String getattribut2() {
		return attribut2;
	}
	public void setattribut2(String attribut2) {
		this.attribut2 = attribut2;
	}
	public String getattribut3() {
		return attribut3;
	}
	public void setattribut3(String attribut3) {
		this.attribut3 = attribut3;
	}

}


public class CinemaGui extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	bouton pour chercher ,effacer,montrer les informations
	private JButton ok,clear,before;
//	textes pour la premiere fenetre
	private JTextField text1,text2,text3,text4,text5,text6;
//	textes pour la deuxieme fenetre
	private JTextField text11,text12,text13,text14;
//	Menubar comporte menu,menu comporte menuitem
    private MenuBar bar;
	private Menu fileMenu;
	private MenuItem openItem;
//	attribut pour conserver l'instance en memoire
	private Connection isconnect;
//	listes pour stocker les valeurs obtenus et les afficher au tableau
	private ArrayList<Database> list ,childlist;
//	panneau avec barres de défilement pour afficher les informations autant que possible
	private JScrollPane jspane,childjspane;
//	attribut du tableau
	private JTable table ,childtable;
//	attribut necessaire pour inserer la programmation
	private String idprog;
//	attribut pour afficher le temps d'instant
	private JLabel timelabel,childtimelabel;

    public CinemaGui() {
    	
        this.setTitle("DBcinema");
        int DIALOG_WHITE = 600;
        int DIALOG_HEIGHT = 660;
//		Obtenir le point central de l'écran
        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
//      Faire apparaître la fenetre au milieu de l'ecran 
        this.setBounds(point.x - DIALOG_WHITE / 2, point.y - DIALOG_HEIGHT / 2, DIALOG_WHITE, DIALOG_HEIGHT);

//      Pas permit de changer taille
        this.setResizable(false);
//      Quitter l'application en utilisant la méthode de sortie du système
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//      Mise en page des conteneurs de la premiere fenetre
        completeTop();
//      Mise en menu MenuBar comporte Menu, Menu comporte MenuItem
        bar = new MenuBar();
    	fileMenu = new Menu("Menu");
    	openItem = new MenuItem("Fonction 2");
    	fileMenu.add(openItem);
    	bar.add(fileMenu);
    	this.setMenuBar(bar);
//    	Attribut de garantir la persistance de la connexion
    	this.isconnect=null;
//    	Créer une liste de type arraylist pour stocker les données interrogées pour premier fenetre
    	this.list= new ArrayList<Database>();
    	
//    	Definir l'icone de GUI
    	ImageIcon icon=new ImageIcon("./Images/Polytech_Logo.jpg"); 
		this.setIconImage(icon.getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));

 
//    	Réalisez les effets des clics sur les boutons grâce à la surveillance des événements 
    	openItem.addActionListener(this);
    	
//      Afficher tous les conteneurs de GUI
        this.setVisible(true);

    }
    public void completeTop(){
		
		
    	JLabel label1 = new JLabel("Date de film:");
    	JLabel label2=new JLabel("Genre de film:");
    	JLabel label3=new JLabel("Titre de film:");
    	JLabel label4=new JLabel("Realisateur de film:");
    	JLabel label5=new JLabel("Public concerne de film:");
    	JLabel label6=new JLabel("ID de film:");
    	
//    	Définir la bordure de ligne du composant JLabel
    	label1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label5.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label6.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	
    
    	text1=new JTextField("");
    	text2=new JTextField("");
    	text3=new JTextField("");
    	text4=new JTextField("");
    	text5=new JTextField("");
    	text6=new JTextField("");
    	
//    	disposition des boutons
    	ok=new JButton("Chercher");
    	ok.setSize(1,1);
    	clear = new JButton("Effacer");
    	clear.setSize(1,1);
    	ok.addActionListener(this);
    	clear.addActionListener(this);

//    	Trois composants du panneau JPpanel implémentent différentes dispositions
//    	premier panneau pour la disposition de label et text
    	JPanel mainPanel1 = new JPanel(new GridLayout(6,2,0,0));
        mainPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel1.add(label1);
        mainPanel1.add(text1);
        mainPanel1.add(label2);
        mainPanel1.add(text2);
        mainPanel1.add(label3);
        mainPanel1.add(text3);
        mainPanel1.add(label4);
        mainPanel1.add(text4);
        mainPanel1.add(label5);
        mainPanel1.add(text5);
        mainPanel1.add(label6);
        mainPanel1.add(text6);
        mainPanel1.setBackground(new Color(0xFF9933));
//		deuxieme panneau pour la dispostion des 2 boutons
        JPanel mainPanel2 = new JPanel(new GridLayout(1,2));        
        mainPanel2.add(ok);
        mainPanel2.add(clear);
//      troisieme panneau pour l'affiachage de table de rechereche
        JPanel mainPanel3 = new JPanel(); 
//      disposition du temps
		timelabel=new JLabel("");
		mainPanel3.add(timelabel);
//		disposition du panneau de la barre de défilement
        jspane = new JScrollPane();	
    	table = new JTable();
    	
//		disposition de table,interdire de la tirer et les colonnes ne peuvent pas être déplacées.
 		table.getTableHeader().setReorderingAllowed(false);
 		table.getTableHeader().setResizingAllowed(false);
// 		interdire de modifier les donnees de table
 		table.setEnabled(false);
// 		jspane cotient table,afficher les donnees
 		jspane.setViewportView(table);
 		mainPanel3.add(jspane);

//		La disposition des bordures BorderLayout divise le conteneur en cinq zones :
// 		EST, SUD, OUEST, NORD et CENTRE
        this.getContentPane().add(mainPanel1, BorderLayout.NORTH);
        this.getContentPane().add(mainPanel2, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel3, BorderLayout.CENTER);
        
    }
//    Mise en page de la deuxieme fenetre
    public void completeTop2(Container pane2){

    	this.childlist= new ArrayList<Database>();
    	JLabel label1 = new JLabel("ID de film:");
    	JLabel label2=new JLabel("ID de cinema:");
    	JLabel label3=new JLabel("Date debut:");
    	JLabel label4=new JLabel("Date fin:");
    	
    	label1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	label4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	
    
    	text11=new JTextField("");
    	text12=new JTextField("");
    	text13=new JTextField("");
    	text14=new JTextField("");
    	
    	
    	ok=new JButton("Inserer");
    	clear = new JButton("Effacer");
    	before=new JButton("Montrer");
    	ok.addActionListener(this);
    	clear.addActionListener(this);
    	before.addActionListener(this);

    	
    	JPanel mainPanel1 = new JPanel(new GridLayout(4,2,0,0));
        mainPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel1.add(label1);
        mainPanel1.add(text11);
        mainPanel1.add(label2);
        mainPanel1.add(text12);
        mainPanel1.add(label3);
        mainPanel1.add(text13);
        mainPanel1.add(label4);
        mainPanel1.add(text14);
        mainPanel1.setBackground(new Color(0xFF9933));

        JPanel mainPanel2 = new JPanel(new GridLayout(1,3));   
        mainPanel2.add(before);
        mainPanel2.add(ok);
        mainPanel2.add(clear);
        
        
        JPanel mainPanel3 = new JPanel();  
        
        
		childtimelabel=new JLabel("");
		mainPanel3.add(childtimelabel);
		
        this.childtable=new JTable();
        this.childjspane=new JScrollPane();

 		childtable.getTableHeader().setReorderingAllowed(false);
 		childtable.getTableHeader().setResizingAllowed(false);
 		childtable.setEnabled(false);
 		childjspane.setViewportView(childtable);
 		mainPanel3.add(childjspane);
 		
 		pane2.add(mainPanel1, BorderLayout.NORTH);
        pane2.add(mainPanel2, BorderLayout.SOUTH);
        pane2.add(mainPanel3, BorderLayout.CENTER);

        
    }
    public void connect() {
    	try 
	     {  
//    		 localiser la base de donnée
	    	 String location="D:\\BasedeDonne\\BDcinema.db";
	         System.out.println("Location of database: "+location);
//	         connexion vers SQLITE
	         String url="jdbc:sqlite:"+location;
//	         il se charge des pilotes permettant de communiquer avec la base de donnees particuliere
	         Class.forName("org.sqlite.JDBC");  
//	         creer l'instance de la connection à la base de donnee
	         this.isconnect = DriverManager.getConnection(url); 
	         System.out.println("Connexion reussie");
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println("echec de connexion");
//	    	 Imprimer tous les messages d'erreur sur la console
	         e.printStackTrace();  
	     }
    	
    }
    public ResultSet request(String sql) {
		ResultSet rs=null;
		try {
//			lancer une requete par l'instruction sql
            Statement stmt = this.isconnect.createStatement();
//          recuperer l'information
            rs = stmt.executeQuery(sql);  
    	    
        } 
        catch (SQLException e) {
            System.out.println("Il y a des erreurs pendant la requete！"+e.getMessage());
            System.out.println("La requete SQL est "+sql);
        }
		return rs;
	}
//    Il ne faut que entrer date et genre,si l'utilisateur entre plus,il n'y a pas d'erreurs mais pas de resultats
    public void choisirfilm(String date,String genre,String titre,String realisateur,String public_concerne,String ID) {
//    	requete de sql pour obtenir ID_film,titre de film
    	String sql="SELECT ID_film, titre FROM Film Where annee='%s' And genre='%s'";
    	sql=String.format(sql, date,genre);
//    	si l'utilisateur entre plus tels que titre, realisateur..., modifier la requete sql
    	if(titre!=null&&titre.length()>0) {
    		sql=sql+" And titre='%s'";
    		sql=String.format(sql, titre);
    	}
    	if(realisateur!=null&&realisateur.length()>0) {
    		sql=sql+" And realisateur='%s'";
    		sql=String.format(sql, realisateur);
    	}
    	if(public_concerne!=null&&public_concerne.length()>0) {
    		sql=sql+" And public_concerne='%s'";
    		sql=String.format(sql, public_concerne);
    	}
    	if(ID!=null&&ID.length()>0) {
    		sql=sql+" And ID_film='%s'";
    		sql=String.format(sql, ID);
    	}
    
		System.out.println("Requete SQL: "+sql);
		System.out.println("ID_film"+ "\t"+"titre"+ "\t");
//		obtenir la valeur de la requete
		ResultSet rs=request(sql);
		
		try {
//			si il y a des valeurs, renvoyer la valeur courante de l'attribut
			while (rs.next()) {
			    System.out.println(rs.getString("ID_film") + "\t" + rs.getString("titre")+ "\t" );
			    String id = rs.getString("ID_film");
				String name =  rs.getString("titre");
//				Crer une instance de type Database pour stocker les differents valeurs de requete
				Database ui = new Database();
//				stocker la valeur au Database
				ui.setId(id);
				ui.setName(name);
//				ajouter chaque donnee au liste
				this.list.add(ui);
			}
			System.out.println("nombre"+this.list.size());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
        
	}
//    il ne faut que entrer ID_cinema
    public void choisircinema(String date,String genre,String titre,String realisateur,String public_concerne,String ID) {

    	String sql="SELECT Cinema.nom, Cinema.localisation\r\n"
    			+ "FROM Cinema\r\n"
				+ "JOIN Programmation ON Programmation.Ref_cine=Cinema.ID_cine\r\n"
				+ "JOIN Film ON Programmation.Ref_film = Film.ID_film\r\n"
				+ "WHERE Film.ID_film = '%s'";
    	sql=String.format(sql,ID);
//    	si entre plus,modifier l'instruction sql
    	if(titre!=null&&titre.length()>0) {
    		sql=sql+" And titre='%s'";
    		sql=String.format(sql, titre);
    	}
    	if(realisateur!=null&&realisateur.length()>0) {
    		sql=sql+" And realisateur='%s'";
    		sql=String.format(sql, realisateur);
    	}
    	if(public_concerne!=null&&public_concerne.length()>0) {
    		sql=sql+" And public_concerne='%s'";
    		sql=String.format(sql, public_concerne);
    	}
    	if(date!=null&&date.length()>0) {
    		sql=sql+" And Date_debut='%s'";
    		sql=String.format(sql, date);
    	}
    	if(genre!=null&&genre.length()>0) {
    		sql=sql+" And Date_fin='%s'";
    		sql=String.format(sql, genre);
    	}
    
		System.out.println("Requete SQL: "+sql);
		System.out.println("Nom_cinema"+ "\t"+"Ville_cinema"+ "\t");
		ResultSet rs=request(sql);
		
		try {
			while (rs.next()) {
			    System.out.println(rs.getString("nom") + "\t" + rs.getString("localisation")+ "\t" );
			    String nom = rs.getString("nom");
				String localisation =  rs.getString("localisation");
				Database ui = new Database();
				ui.setId(nom);
				ui.setName(localisation);
				this.list.add(ui);
			}
			System.out.println("nombre"+this.list.size());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
        
	}
//    afficher tous les programmation au courante
    public void display() {
    	String sql="SELECT * FROM Programmation";
    	ResultSet rs=request(sql);	
		try {
			while (rs.next()) {
			    System.out.println( rs.getString("ID_prog")+ "\t"+rs.getString("Ref_cine")+ "\t" +
			    		rs.getString("Ref_film") + "\t" + rs.getString("Date_debut")+ "\t"+
			    		rs.getString("Date_fin") + "\t");
			    String id_prog =  rs.getString("ID_prog");
				String ref_cine =  rs.getString("Ref_cine");
				String ref_film = rs.getString("Ref_film");
				String date_debut =  rs.getString("Date_debut");
				String date_fin = rs.getString("Date_fin");
				Database ui = new Database();
				ui.setId(id_prog);
				ui.setName(ref_cine);
				ui.setattribut1(ref_film);
				ui.setattribut2(date_debut);
				ui.setattribut3(date_fin);
				this.childlist.add(ui);
			}
			System.out.println("nombre"+this.childlist.size());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    }
//    stocker les informations sur la base de donnees
    public void insert(String sql) {
    	try {
            Statement stmt = this.isconnect.createStatement();
//            mise a jour les donnees
            stmt.executeUpdate(sql);  
    	    
        } 
        catch (SQLException e) {
            System.out.println("Il y a des erreurs pendant la mise a jour！"+e.getMessage());
            System.out.println("La mise a jour SQL est "+sql);
        }
    }
//	preparation d'instruction sql surtout l'attribut id_programmation
    public void Insererprogramme(String ID_cine,String ID_film,String Debut,String Fin) {
    	String sql="INSERT INTO Programmation VALUES ('%s','%s','%s','%s','%s')";
    	idprog=idprog+'0';
    	sql=String.format(sql,idprog,ID_cine,ID_film,Debut,Fin);   	
    
		System.out.println("Requete SQL: "+sql);
		insert(sql);
		
	
    }
//    fonction d'écoute d'événements pour le menu,le bouton
    public void actionPerformed(ActionEvent e) {
//    	obtenir le temps d'instant
    	LocalDateTime dateTime = LocalDateTime.now(); 
//    	afficher le temps sous une forme
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		System.out.println(dateTime.format(formatter)); 
		String timestring="Le temps d'operation est '%s'";
		timestring=String.format(timestring,dateTime.format(formatter));
//		mise a jour la valeur dans le texte
		timelabel.setText(timestring);
//		obtenir le type d'evenement
    	String event=e.getActionCommand();
		if (event.equals("Chercher")){
//			tout d'abord connecter la base de donnees
			connect();
//			obtenir les valeurs du texte
			String input=text1.getText();
			String input2=text2.getText();
			String input3=text3.getText();
			String input4=text4.getText();
			String input5=text5.getText();
			String input6=text6.getText();
//			cas 1,utilisateur entrer la date et le genre du film pour chercher les informations du film
			if(input.length()>0&&input2.length()>0) {
				System.out.println("Date est " + input+" Genre est "+input2);
			    choisirfilm(input,input2,input3,input4,input5,input6);
//			    la première ligne de texte du tableau
			    String title[] = { "ID_film", "Titre" };
			    int rows=this.list.size();int col=2;
//			    créer un tableau bidimensionnel pour stocker des données
			    String rowdata[][] = new String[rows][col];
				for (int i = 0; i < rows; i++) {
					
					Database ee = this.list.get(i);
					for (int j = 0; j < col; j++) {
//						Récupérez les données et les placez dans le tableau rowdata
						if (j % 2 == 0) {
							rowdata[i][j] = ee.getId();
						} else {
							rowdata[i][j] = ee.getName();
						}
					}
				}
			
//				mise a jour les donnees du tableau rapidement
				TableModel dataModel = new DefaultTableModel(rowdata, title);
		 		table.setModel(dataModel);
			}
//			cas 2,enter id_film pour chercher le nom et la ville 
			else if(input6.length()>0) {
				System.out.println("ID est " + input6);
			    choisircinema(input,input2,input3,input4,input5,input6);
			    String title[] = { "Nom_cinema", "Ville_cinema" };
			   
			    int rows=this.list.size();int col=2;
			    String rowdata[][] = new String[rows][col];
				for (int i = 0; i < rows; i++) {
					Database ee = this.list.get(i);
					for (int j = 0; j < col; j++) {
						if (j % 2 == 0) {
							rowdata[i][j] = ee.getId();
						} else {
							rowdata[i][j] = ee.getName();
						}
					}
				}
			
				TableModel dataModel = new DefaultTableModel(rowdata, title);
		 		table.setModel(dataModel);
			}
//			n'oublie pas de fermer la connexion si on n'utilise plus
			Close();
	
		}
		else if (event.equals("Effacer")){
			String []title=null;
			String [][]rowdata=null;
			TableModel dataModel = new DefaultTableModel(rowdata, title);
			if(text1!=null) {
				text1.setText("");
				text2.setText("");
				text3.setText("");
				text4.setText("");
				text5.setText("");
				table.setModel(dataModel);	
		 		this.list.clear();
		 		
			}
			if(text11!=null) {
				text11.setText("");
				text12.setText("");
				text13.setText("");
				text14.setText("");
				childtable.setModel(dataModel);
				this.childlist.clear();
			}
			
			
	 		
		}

//		Si on presse le menu Fonction2, c'est la deuxieme fonction pour inserer la programmation manuellement
		else if(event.equals("Fonction 2")) {
//			Construire un nouveau JDialog en tant que nouvelle fenêtre.
	        JDialog childframe = new JDialog();
//	        si on ferme cette fenetre, il permet de masquer la fenêtre actuelle et libérer les ressources occupées
	        childframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	        décalez la nouvelle fenêtre de 50 pixels par rapport à celle CinemaGui
	        childframe.setBounds(
	                new Rectangle(
	                        (int) this.getBounds().getX() + 50,
	                        (int) this.getBounds().getY() + 50, 
	                        (int) this.getBounds().getWidth(), 
	                        (int) this.getBounds().getHeight()
	                )
	            );
			
//			obtenir le panneau pour la disposition de la deuxieme fenetre
	        Container pane2=childframe.getContentPane();
	        completeTop2(pane2);	
//	        initialisation du tableau
	        String []title=null;
			String [][]rowdata=null;
	        TableModel dataModel = new DefaultTableModel(rowdata, title);
	 		childtable.setModel(dataModel);
	 		
	        childframe.setTitle("Inserer Programmation");
//			bloquer toutes les fenêtres de niveau supérieur  dans la même application Java
	        childframe.setModalityType(ModalityType.APPLICATION_MODAL);  
	        childframe.setResizable(false);
	        childframe.setVisible(true);
	  
		}
		else if(event.equals("Inserer")) {
			connect();
			String input=text11.getText();
			String input2=text12.getText();
			String input3=text13.getText();
			String input4=text14.getText();
//			si on entre 4 textes, on peut inserer la programmation
			if(input.length()>0&&input2.length()>0&&input3.length()>0&&input4.length()>0) {
				System.out.println("ID_cinema est " + input+" ID_film est "+input2+ " Date debut est "+input3+" Date fin est "+input4);
			    Insererprogramme(input,input2,input3,input4);
			}
			childtimelabel.setText(timestring);
			Close();
			
		}
		else if(event.equals("Montrer")) {
			connect();
			childtimelabel.setText(timestring);
			display();
			
		    String title[] = { "ID_prog", "ID_film", "ID_cinema","Date Debut","Date Fin" };
		    
		    int rows=this.childlist.size();int col=5;
		    String rowdata[][] = new String[rows][col];
			for (int i = 0; i < rows; i++) {
				Database ee = this.childlist.get(i);
				for (int j = 0; j < col; j++) {
//					obtenir separement les attributs
					if(j%5==0) {
						rowdata[i][j] = ee.getId();
						this.idprog=ee.getId();
					}
					else if (j % 5 == 1) {
						rowdata[i][j] = ee.getName();
					}
					else if (j % 5 == 2) {
						rowdata[i][j] = ee.getattribut1();

					}
					else if (j % 5 == 3) {
						rowdata[i][j] = ee.getattribut2();
					}
					else if (j % 5 == 4) {
						rowdata[i][j] = ee.getattribut3();
					}
				}
			}
//			mise a jour la valeur dans le tableau
			TableModel dataModel = new DefaultTableModel(rowdata, title);
	 		childtable.setModel(dataModel);
	 		Close();
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
    public static void main(String args[]) {
        CinemaGui s = new CinemaGui();
    }
}