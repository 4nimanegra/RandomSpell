import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Enemy3 extends Enemy1 {
	
	public static final int LEFT=0;
	public static final int RIGHT=1;
	
	public static final int GRAVITY=3;
	public static final int POWERY=10;
	public static final int POWERX=6;
	
	
	int animation;
	int direction;
	
	int vely;

	public Enemy3(int x, int y) {
		super(x, y);
		
		this.image = new Image[2][3];
		
		ImageIcon ii;
		
		ii = new ImageIcon("sprites/bad3.png");
		
		this.image[0][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad32.png");
		
		this.image[0][1] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad32.png");
		
		this.image[0][2] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad3.png");
		
		this.image[1][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad32.png");
		
		this.image[1][1] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad32.png");
		
		this.image[1][2] = ii.getImage();
		
		this.heigth = this.image[0][0].getHeight(null);
		this.widht = this.image[0][0].getWidth(null);
		
		this.animation = 0;
		this.direction = Enemy3.LEFT;
		
		this.vely = 0;
		
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
		
		this.vely = this.vely + Enemy3.GRAVITY;
						
		if(this.direction == Enemy3.LEFT){
			
			endx = this.x - Enemy3.POWERX;
			
		}else{
			
			endx = this.x + Enemy3.POWERX;
			
		}
		
		while(this.x != endx){
			
			this.collisionall();
			
			if(this.colisionlist.size()>0){
				
				Player.killPlayer(this.colisionlist);
				
				int i;
				
				found = false;
				
				i=0;
				
				while(i < this.colisionlist.size()){
					
					if(this.colisionlist.get(i).type == Weight.TYPE_SPELL){
						
						this.kill();
						
					}else if((this.colisionlist.get(i).type == SimpleBrick.TYPE_BLOCK)){
						
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
				
				Player.killPlayer(this.colisionlist);
				
				int i;
				
				found = false;
				
				i=0;
				
				while(i < this.colisionlist.size()){
					
					if((this.colisionlist.get(i).type == Weight.TYPE_SPELL) ||
							(this.colisionlist.get(i).type == Brush.TYPE_BRUSH)){
						
						this.kill();
						
					}else if((this.colisionlist.get(i).type == SimpleBrick.TYPE_BLOCK)){
						
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
