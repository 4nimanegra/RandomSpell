import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Enemy1 extends Sprites {

	public static final int TYPE_ENEMY=3;
	
	public static final int LEFT = 1;
	public static final int RIGHT = 0;
	
	public static final int MOVEMENT = 5;
	
	Image[][] image;
	private int direction;
	private int animation;

	private Explosion explosion;
	
	public Enemy1(int x, int y) {
		
		super(x, y);
		
		this.explosion = null;
		
		this.type = Enemy1.TYPE_ENEMY;

		this.image = new Image[2][2];
		
		ImageIcon ii = new ImageIcon("sprites/bad1r.png");
		
		this.image[0][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad1r.png");
		
		this.image[0][1] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad1.png");
		
		this.image[1][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad1.png");
		
		this.image[1][1] = ii.getImage();
		
		this.heigth = this.image[0][0].getHeight(null);
		this.widht = this.image[0][0].getWidth(null);
				
		this.direction = Enemy1.LEFT;
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		if(this.explosion != null){
			
			this.explosion.move(keys);
			
			return;
			
		}
		
		int endx;
		ArrayList<Sprites> colisionlist;
		
		colisionlist = null;
		
		if(this.direction == Enemy1.LEFT){
			
			endx = this.x - Enemy1.MOVEMENT;
			
		}else{
			
			endx = this.x + Enemy1.MOVEMENT;
			
		}
		
		while(this.x != endx){
			
			this.collisionall();
			
			if(this.colisionlist.size()>0){
				
				Player.killPlayer(this.colisionlist);
				
				Boolean found;
				
				int i;
				
				found=false;
				
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
				
				if(!found){
				
					if(this.direction == Enemy1.LEFT){
						
						this.x = this.x + 1;
				
						this.direction = Enemy1.RIGHT;
					
					} else {
						
						this.x = this.x - 1;
					
						this.direction = Enemy1.LEFT;
					
					}
					
					break;
					
				}
				
			}else{
				
				if(this.direction == Enemy1.LEFT){
					
					this.x = this.x + 1;
					
					this.direction = Enemy1.RIGHT;
					
				} else {
					
					this.x = this.x - 1;
					
					this.direction = Enemy1.LEFT;
					
				}
				
				if(this.direction == Enemy1.LEFT){
					
					endx = this.x - Enemy1.MOVEMENT;
					
				}else{
					
					endx = this.x + Enemy1.MOVEMENT;
					
				}
				
			}
		
			if(this.direction == Enemy1.LEFT){
				
				this.x = this.x - 1;
				
			}else{
			
				this.x = this.x + 1;
				
			}
		
		}
		
	}
	
	@Override
	public void paint(Graphics field){
		
		if(this.explosion != null){
			
			this.explosion.paint(field);
			
			return;
			
		}
		
		field.drawImage(this.image[this.direction][this.animation], this.x,this.y, null);
		
		this.animation = (this.animation + 1)%this.image[0].length;
		
	}
	
	@Override
	public void kill(){
		
		if(this.finished){
			
			return;
			
		}
		
		this.finished = true;
		
		this.explosion = new Explosion(this.x, this.y);
		
	}
	
	public static void killEnemy(ArrayList<Sprites> list){
		
		int i;
		
		i=0;
		
		while(i < list.size()){
			
			if(list.get(i).type == Enemy1.TYPE_ENEMY){
				
				list.get(i).kill();
				
			}
			
			i=i+1;
			
		}
		
	}
	

}
