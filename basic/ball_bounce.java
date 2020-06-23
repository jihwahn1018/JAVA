package hw4;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.ArrayList;

public class s20141362hw4 extends Frame implements ActionListener {
	private Canvas canvas;
	Ball[] b=new Ball[1000];
	int da,db;
	
	public s20141362hw4(String title) {
		super(title);
		canvas = new Canvas();
		add("Center",canvas);
		
		Panel p = new Panel();
		Button s = new Button("Start");
		Button c= new Button("Close");
		s.addActionListener(this);
		c.addActionListener(this);
		p.add(s);
		p.add(c);
		
		add("South",p);
	}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand()=="Start") {
			
			for(int i=0;i<5;i++) {
				da=(int)(Math.random()*6)-3;
				db=(int)(Math.random()*6)-3;
		         Ballarr.balls.add(new Ball(canvas,200,150,da,db,20));
		         Ballarr.balls.get(i).start();
			}
		}
		else if(evt.getActionCommand()=="Close") {
			System.exit(0);
		}
	}
		public static void main(String[] args) {
			Frame f = new s20141362hw4("s20141362hw4");
			f.setSize(400,300);
			WindowDestroyer listener = new WindowDestroyer();
			f.addWindowListener(listener);
			f.setVisible(true);
		}

}

class Ballarr {

	public static ArrayList<Ball> balls = new ArrayList<Ball>();

}


class Ball extends Thread{
	private Canvas box;
	private int diameter = 20;
	private boolean runflag = true;
	private int x=0;
	private int y=0;
	private int dx=2;
	private int dy=2;
	public Ball(Canvas c,int x,int y, int dx,int dy, int diameter) {
		box=c;
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.diameter=diameter;
	}
	public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval(this.x, this.y, this.diameter, this.diameter);
		g.dispose();
	}
	public void move() {
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(this.x, this.y, this.diameter, this.diameter);
		
		this.x+=dx;
		this.y+=dy;
		Dimension d = box.getSize();
		if(this.x<0) {this.x=0;dx=-dx;}
		if(x+this.diameter>=d.width) {
			this.x=d.width-this.diameter;
			dx=-dx;
		}
		if(y<0) {y=0;dy=-dy;}
		if(y+this.diameter>=d.height) {
			this.y=d.height-this.diameter;
			dy=-dy;
		}

	      for(int i=0; i<Ballarr.balls.size(); i++){
	            Ball tball = Ballarr.balls.get(i);
	            if(this.colliding(tball) && i != Ballarr.balls.indexOf(this)){
	             this.ball_dtb(this);
	             tball.ball_dtb(this);
	            }
	       }     	
	    g.setPaintMode();
		g.fillOval(this.x, this.y, this.diameter, this.diameter);
		g.dispose();
	}
	//충돌 판단
	boolean colliding(Ball b1) {
		if(b1.x<=this.x && this.x<=b1.x+b1.diameter) {
			return true;
		}
		if(b1.y<=this.y && this.y<=b1.y+b1.diameter) {
			return true;
		}
		return false;
	}
	
		
		void ball_dtb(Ball pball) {
			
			if(pball.diameter/2>1) {
				int da=(int)(Math.random()*6)-3;
				int db=(int)(Math.random()*6)-3;
				dx+=da;
				dy+=db;
				Ball cball1 = new Ball(pball.box,pball.x, pball.y,dx,dy, pball.diameter/2);
				da=(int)(Math.random()*100)-50;
				db=(int)(Math.random()*100)-50;
				dx+=da;
				dy+=db;
				Ball cball2 = new Ball(pball.box,pball.x, pball.y,dx,dy, pball.diameter/2);
				Ballarr.balls.add(cball1);
		        Ballarr.balls.add(cball2);



		         int indexball = Ballarr.balls.indexOf(pball); 
		         pball.runflag = false;
		         Ballarr.balls.remove(indexball);

		            

		         cball1.start();
		         cball2.start();
			}
			
			 else{

		          int indexball = Ballarr.balls.indexOf(pball);
		          pball.runflag = false;
		          Ballarr.balls.remove(indexball);

		      }
			
		}
	public void run() {
		draw();
		while(this.runflag) {
			move();
			if(diameter<=1) this.runflag=false;
			try{Thread.sleep(30);}
			catch(InterruptedException e) {}
		}
	}	
}

class WindowDestroyer extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

