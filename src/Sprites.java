import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Sprites {
	
	public static final int TYPE_SPRITE = 0;
	
	public static final int WIDHT=9;
	public static final int HEIGTH=9;
	
	ArrayList<Sprites> colisionlist;
	
	int x;
	int y;
	
	int type;
	
	int widht;
	int heigth;
	
	Image image;
	
	boolean finished;
	
	public Sprites(int x, int y){
		
		ImageIcon ii = new ImageIcon("sprites/brick.png");
		
		this.image = ii.getImage();
		
		this.x = x;
		this.y = y;
		
		this.widht = Sprites.WIDHT;
		this.heigth = Sprites.HEIGTH;
		
		this.colisionlist = new ArrayList<Sprites>();
		
		this.type = Sprites.TYPE_SPRITE;
		
		this.finished = false;
		
	}

	public boolean finished(){
		
		return this.finished;
		
	}
	
	public void paint(Graphics field) {

		field.drawImage(this.image,this.x,this.y,null);
		
	}
	
	public void move(ArrayList<Integer> teclas){
	
		
	
	}
	
	public boolean collision(Sprites other){
		
		Rectangle rect1, rect2;
		
		rect1 = new Rectangle(this.x,this.y,this.widht,this.heigth);
		rect2 = new Rectangle(other.x,other.y,other.widht,other.heigth);
		
		if(rect1.intersects(rect2)){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	public boolean collisionall(){
	
		int i;
		
		this.colisionlist.clear();
		
		i=0;
		
		while(i < GameScreen.list.size()){
		
			if(this != GameScreen.list.get(i)){
			
				if(this.collision(GameScreen.list.get(i))){
				
					if(GameScreen.list.get(i).type != Rabbit.TYPE_RABIT){
					
						this.colisionlist.add(GameScreen.list.get(i));
				
					}
					
				}
				
			}
			
			i=i+1;
			
		}
		
		return (this.colisionlist.size() > 0);
		
	}
	
	public void kill(){
	
		this.finished = true;
		
	}

}
