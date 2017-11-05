import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Rabbit extends Sprites {
	
	public static final int LIFE_TIME = 200;
	
	Image image[][];
	
	int animation;
	int vely;
	int direction;
	
	int life;
	
	public static final int TYPE_RABIT=8;

	public Rabbit(int x, int y) {
		
		super(x, y);
		
		ImageIcon ii;
		
		this.image = new Image[2][2];
		
		ii = new ImageIcon("sprites/rabit1l.png");
		
		this.image[0][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/rabit2l.png");
		
		this.image[0][1] = ii.getImage();
		
		ii = new ImageIcon("sprites/rabit1r.png");
		
		this.image[1][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/rabit2r.png");
		
		this.image[1][1] = ii.getImage();
		
		this.life = Rabbit.LIFE_TIME;
		
		if(Math.random() > 0.5){
			
			this.direction = 0;
			
		}else{
			
			this.direction = 1;
			
		}
		
		this.type = Rabbit.TYPE_RABIT;
		
		this.heigth = this.image[0][0].getHeight(null);
		this.widht = this.image[0][0].getWidth(null);
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.drawImage(this.image[this.direction][this.animation % this.image[0].length], this.x, this.y, null);
		
		if(this.animation < this.image[0].length){
			
			this.animation = this.animation + 1;
			
		}
						
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		boolean found;
		
		int endx;
		int endy;
		
		this.life = this.life - 1;
		
		if(this.life == 0){
			
			this.kill();
			
		}
		
		this.vely = this.vely + Enemy3.GRAVITY;
						
		if(this.direction == Enemy3.LEFT){
			
			endx = this.x - Enemy3.POWERX;
			
		}else{
			
			endx = this.x + Enemy3.POWERX;
			
		}
		
		while(this.x != endx){
			
			this.collisionall();
			
			if(this.colisionlist.size()>0){
								
				int i;
				
				found = false;
				
				i=0;
				
				while(i < this.colisionlist.size()){
					
					if((this.colisionlist.get(i).type == SimpleBrick.TYPE_BLOCK)){
						
						found = true;
						
					}
					
					i=i+1;
					
				}
				
				if(found){
				
					
					
					if(this.direction == Enemy3.LEFT){
						
						this.x = this.x + 1;
						
						this.direction = Enemy3.RIGHT;
						
					}else{
						
						this.x = this.x - 1;
						
						this.direction = Enemy3.LEFT;
						
					}
					
					endx = this.x;
					
					break;
					
				}
				
			}
			
			if(this.direction == Enemy3.LEFT){
				
				this.x = this.x - 1;
								
			}else{
				
				this.x= this.x + 1;
				
			}
			
		}
				
		endy = this.y + this.vely;
		
		while(this.y != endy){
			
			this.collisionall();
			
			if(this.colisionlist.size()>0){
								
				int i;
				
				found = false;
				
				i=0;
				
				while(i < this.colisionlist.size()){
					
					if((this.colisionlist.get(i).type == SimpleBrick.TYPE_BLOCK)){
						
						found = true;
						
					}
					
					i=i+1;
					
				}
				
				if(found){
									
					this.vely = -Enemy3.POWERY;
					
					this.animation = 0;
					
					if(this.y < endy){
						
						this.y = this.y - 1;
						
					}else if(this.y > endy){
						
						this.y = this.y + 1;
						
					}
					
					endy = this.y;
					
					break;
					
				}
				
			}
		
			if(this.y < endy){
				
				this.y = this.y + 1;
				
			}else if(this.y > endy){
				
				this.y = this.y - 1;
				
			}
		
		}
		
	}

}
