package Zone_miniere;
import java.math.BigDecimal;
import java.util.Random;
public class Point {
	private static Random random;
	private double x,y;
	public double x_contain,y_contain;
	public Point() {
		random=new Random();
		this.x=random.nextDouble();
		this.y=random.nextDouble();
		x_contain=0;
		y_contain=0;
	}
	public Point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public double distance(Point p) {
		double r = Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
		return r;
	}
	public double obtenir_abscisse() {
		return x;
	}
	public double obtenir_ordonne() {
		return y;
	}
	public boolean analyse_contain_segment (double x1, double y1, double x2, double y2, double minX, double minY, double maxX, double maxY) {  
	    double m = (y2 - y1) / (x2 - x1);
	    double y = m * (minX - x1) + y1;
	    boolean flag=false;
	    double []temp_dis=new double[2];
	    double []temp_x=new double[2];
	    double []temp_y=new double[2];
	    int index=0;
	    if (y > minY && y < maxY) {
	    	x_contain=minX;
	    	y_contain=y;
	    	temp_dis[index]=Math.sqrt((x_contain-x2)*(x_contain-x2)+(y_contain-y2)*(y_contain-y2));
	    	temp_x[index]=x_contain;temp_y[index]=y_contain;
	    	index+=1;
	    	System.out.println("le point croisse1 calcule est "+x_contain+" , "+y_contain);
	    	flag=true;


	    }

	    y = m * (maxX - x1) + y1;
	    if (y > minY && y < maxY) {
	    	x_contain=maxX;
	    	y_contain=y;
	    	temp_dis[index]=Math.sqrt((x_contain-x2)*(x_contain-x2)+(y_contain-y2)*(y_contain-y2));
	    	temp_x[index]=x_contain;temp_y[index]=y_contain;
	    	index+=1;
	    	System.out.println("le point croisse2 calcule est "+x_contain+" , "+y_contain);
		    flag=true;

	    }

	    double x = (minY - y1) / m + x1;
	    if (x > minX && x < maxX) {
	    	y_contain=minY;
	    	x_contain=x;
	    	temp_dis[index]=Math.sqrt((x_contain-x2)*(x_contain-x2)+(y_contain-y2)*(y_contain-y2));
	    	temp_x[index]=x_contain;temp_y[index]=y_contain;
	    	index+=1;
	    	System.out.println("le point croisse3 calcule est "+x_contain+" , "+y_contain);
		    flag=true;
	    	
	    }

	    x = (maxY - y1) / m + x1;
	    if (x > minX && x < maxX) {
	    	y_contain=maxY;
	    	x_contain=x;
	    	temp_dis[index]=Math.sqrt((x_contain-x2)*(x_contain-x2)+(y_contain-y2)*(y_contain-y2));
	    	temp_x[index]=x_contain;temp_y[index]=y_contain;
	    	index+=1;
	    	System.out.println("le point croisse4 calcule est "+x_contain+" , "+y_contain);
		    flag=true;
  	
	    }
	    if(index==2) {
	    	if(temp_dis[0]>temp_dis[1]) {
	    		x_contain=temp_x[1];y_contain=temp_y[1];
	    	}
	    	else {
	    		x_contain=temp_x[0];y_contain=temp_y[0];
	    	}
	    }
	    return flag;

	}


	public Point move(Point cible,double d) {
		System.out.println("le point avant est "+this.x+" , "+this.y);
		double dis=distance(cible);

		double xp=(d/dis)*(cible.x-this.x)+this.x;
		double yp=(d/dis)*(cible.y-this.y)+this.y;
		if(Math.abs(dis) < 1e-15) {
			xp=this.x;yp=this.y;
			System.out.println("il arrive le cible");
		}

		Point p=new Point(xp,yp);
		if(dis<d||(xp==cible.x&&yp==cible.y)) {
			p=cible;
		}
		System.out.println("le point actuel est "+p.x+" , "+p.y);
		System.out.println();
		if(!(p.x>=0&&p.x<=1&&p.y>=0&&p.y<=1)) {
			if(analyse_contain_segment(this.x,this.y,cible.x,cible.y,0,0,1,1)) {
				System.out.println("le point croisse est "+x_contain+" , "+y_contain);
				p=new Point(x_contain,y_contain);
			}
		}
		return p;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p=new Point(0.2,0.8);
		Point a=new Point(0.8,1.2);
		Point b=new Point(1.2,0.6);
		Point c=new Point(0.4,-0.2);
		Point pp=p.move(a,0.1);		
		System.out.println("le point change est "+pp.obtenir_abscisse()+" , "+pp.obtenir_ordonne());
		Point pp1=p.move(b,1);
		System.out.println("le point change est "+pp1.obtenir_abscisse()+" , "+pp1.obtenir_ordonne());
		Point pp2=p.move(c,0.4);
		System.out.println("le point change est "+pp2.obtenir_abscisse()+" , "+pp2.obtenir_ordonne());
	}

}
