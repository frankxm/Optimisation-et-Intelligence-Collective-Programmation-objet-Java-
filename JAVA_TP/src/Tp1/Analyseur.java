package Tp1;
import java.util.Arrays;
import java.util.Scanner;  // 导入Scanner
import Utilitaires.TabFileReader;
public class Analyseur {
	public static int nombre_ville=0;
	public static int nombre_list=0;
	public static int nombre_tousvilles=0;
	public static String [] ville_liste;
	public static boolean erreur=false;
	public static boolean erreur2=true;
	public static String[] tous_villes;

	public static int toMinutes(String heure){
		String []tokens=heure.split(":");
		int h=Integer.parseInt(tokens[0]);
		int m=Integer.parseInt(tokens[1]);
		return h*60+m;
		}
    public static void analyse(String ville_travail) {
    	boolean nonexiste=true;
    	for (int i=0;i<nombre_list+1;i++) {
    		if(ville_liste[i].equals(ville_travail)) {
    			nonexiste=false;
    			break;
    			}
    		}
    	if(nonexiste) {
    		ville_liste[nombre_list++]=ville_travail;
    	}
    }
    public static int comparer(String s1,String s2) {
    	int len=s1.length()<s2.length()?s1.length():s2.length();
    	int i=0;
    	int diff=0;
    	for(i=0;i<len;i++) {
    		if(s1.charAt(i)-s2.charAt(i)!=0) {
    			diff=s1.charAt(i)-s2.charAt(i);
    			break;
    		}
    	}
    	if(i==len)
    		diff=s1.length()-s2.length();
    	return diff;
    }
    public static void sort_liste() {
		for(int i=0;i<nombre_list-1;i++) {
			for(int j=i+1;j<nombre_list;j++) {
				if(comparer(ville_liste[i],ville_liste[j])>0) {
					String temp=ville_liste[j];
					ville_liste[j]=ville_liste[i];
					ville_liste[i]=temp;
				}
			}

	}
    }
    public static void chercher(TabFileReader tabfileread) {
	    int row=TabFileReader.nrow();
	    tous_villes=new String[2*row];
	    Arrays.fill(tous_villes,"");
	    boolean v1_flag=false;boolean v2_flag=false;
    	for(int i=0;i<row;i++) {
    		v1_flag=false;v2_flag=false;
    		String v1=tabfileread.wordAt(i, 1);
    		String v2=tabfileread.wordAt(i, 2);
    		for(int j=0;j<tous_villes.length;j++) {
    			if(tous_villes[j].equals(v1)) {
    				v1_flag=true;
    			}
    			else if(tous_villes[j].equals(v2)) {
    				v2_flag=true;
    			}
    		}
    		if(!v1_flag) {
    			tous_villes[nombre_tousvilles++]=v1;
    		}
    		if(!v2_flag) {
    			tous_villes[nombre_tousvilles++]=v2;

    		}
    	}
    	
    }

    public static void chercher_code(TabFileReader tabfileread,String ville) {
	    int row=TabFileReader.nrow();
		for (int i=0;i<row;i++){
			if(TabFileReader.wordAt(i,1).equals(ville)||TabFileReader.wordAt(i,2).equals(ville)) {
				System.out.print(ville+" vol "+TabFileReader.wordAt(i,0)+"\n");
				nombre_ville++;
			}
		}
		if(nombre_ville==0) {
			System.out.println("y'a pas ce vol");
			erreur=true;
		}
			
		else {
			System.out.println("nombre de ville est "+nombre_ville+"\n");
			ville_liste=new String[nombre_ville];
			Arrays.fill(ville_liste, "");
			erreur=false;
		}
			
    }
    public static void chercher_deuxville(String ville1,String ville2,TabFileReader tabfileread) {
	    int row=TabFileReader.nrow();
		for (int i=0;i<row;i++){
			if(TabFileReader.wordAt(i,1).equals(ville1)&&TabFileReader.wordAt(i,2).equals(ville2)) {
				System.out.println(ville1+" "+ville2+" vol "+TabFileReader.wordAt(i,0));
				erreur2=false;
			}		
		}
		if(!erreur2) {
			int heure1=0;
			int heure2=0;
			String escale_courant="";
			int heure_travail=0;
			int heure_mini=toMinutes("06:00");
			int heure_maxi=toMinutes("13:00");
			for (int i=0;i<row;i++){
				heure_travail=toMinutes(TabFileReader.wordAt(i, 3));
				if(heure_travail>=heure_mini&&heure_travail<=heure_maxi) {
					String ville_travail=TabFileReader.wordAt(i, 1);
					analyse(ville_travail);
				}
				if(TabFileReader.wordAt(i,1).equals(ville1)) {
					heure1=toMinutes(TabFileReader.wordAt(i,4));
					escale_courant=TabFileReader.wordAt(i,2);
					for(int j=0;j<row;j++) {
						heure_travail=toMinutes(TabFileReader.wordAt(j, 4));
						if(heure_travail>=heure_mini&&heure_travail<=heure_maxi) {
							String ville_travail=TabFileReader.wordAt(j, 2);
							analyse(ville_travail);
						}
						if(TabFileReader.wordAt(j,1).equals(escale_courant)&&TabFileReader.wordAt(j,2).equals(ville2)) {
							heure2=toMinutes(TabFileReader.wordAt(j,3));
							int ecart=heure2-heure1;
							if(ecart>=60) {
								System.out.println(" ville1 "+ville1+" et "+" ville2 "+ville2+" et "
							+" escale "+TabFileReader.wordAt(j,1)+" vol entre "+TabFileReader.wordAt(i,0)+" et "
							+TabFileReader.wordAt(j, 0));
							}
						}
					}
				}
			}
		}

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TabFileReader.main(args);
		TabFileReader tabfileread=new TabFileReader();
		chercher(tabfileread);
		System.out.print("tous les villes:\n");
		for(int i=0;i<nombre_tousvilles;i++) {			
			System.out.print(tous_villes[i]+" ");
		}
		System.out.println("nombre de ville au total est "+nombre_tousvilles);

		while(true) {
			while(true){
				nombre_ville=0;nombre_list=0;
				Scanner myObj = new Scanner(System.in);  // 创建Scanner对象
			    System.out.println("请输入城市名");
			    String userName = myObj.nextLine();  // 读取用户输入
			    System.out.println("UserName = " + userName);  // 输出用户的输入	    
			    chercher_code(tabfileread,userName);
			    if(erreur) {
			    	System.out.println("有误，请重输");
			    	break;
			    }   	
			    System.out.println("请输入两个城市名");
			    String ville1 = myObj.nextLine();  // 读取用户输入
			    String ville2 = myObj.nextLine();
			    System.out.println("ville1 = " + ville1+" ville2 = "+ville2);  // 输出用户的输入	 
			    chercher_deuxville(ville1,ville2,tabfileread);
			    if(erreur2) {
			    	System.out.println("有误，请重输");
			    	break;
			    } 
			    sort_liste();
				System.out.println("白天提供服务的城市列表数量为"+nombre_list);
				for(int i=0;i<nombre_list;i++) {
					System.out.println(ville_liste[i]);
				}
				System.out.println("");
				
			}
		}
	}

}


