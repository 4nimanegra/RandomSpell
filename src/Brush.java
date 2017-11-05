import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Brush extends SimpleBrick {

	private static final int STEPS = 5;
	
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	
	public static final int TYPE_BRUSH = 7;
	
	public static final int TIMER = 2000;
	
	Image image[][];
	
	int direction;
	
	int delete_timer;
	
	public Brush(int x, int y) {
		
		super(x, y);
		
		this.type = Brush.TYPE_BRUSH;
		
		this.image = new Image[2][1];
		
		ImageIcon ii;
		
		ii = new ImageIcon("sprites/brushr.png");

		this.image[0][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/brushl.png");

		this.image[1][0] = ii.getImage();
		
		this.delete_timer = Brush.TIMER;
				
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.drawImage(this.image[this.direction][0], this.x, this.y, null);
		
		
	}
	
	public void setDirtection(int x, int y){
		
		if(this.x > x){
			
			this.direction = Brush.RIGHT;
			
		}else{
			
			this.direction  = Brush.LEFT;
			
		}
		
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		int endx;
		
		if(this.delete_timer == 0){
			
			this.kill();
			
		}
		
		this.delete_timer = this.delete_timer - 1;
		
		
		if(this.direction == Brush.RIGHT){
		
			endx = this.x + Brush.STEPS;
	
		} else {

			endx = this.x - Brush.STEPS;
			
		}
		
		while(this.x != endx){
			
			this.collisionall();
			
			int i;
			
			i=0;
			
			if(this.colisionlist.size() > 0){
			
				Enemy1.killEnemy(this.colisionlist);
			
			}
				
			while(i < this.colisionlist.size()){
				
				if(this.colisionlist.get(i).type == SimpleBrick.TYPE_BLOCK){
					
					if(this.direction == Brush.RIGHT){
						
						this.x = this.x - 1;
				
						this.direction = Brush.LEFT;
						
					} else {

						this.x = this.x + 1;
						
						this.direction = Brush.RIGHT;
						
					}
					
					break;
					
				}
				
				i=i+1;
				
			}
			
			if(i < this.colisionlist.size()){
				
				break;
				
			}
			
			if(this.direction == Brush.RIGHT){
				
				this.x = this.x + 1;
		
			} else {

				this.x = this.x - 1;
				
			}
			
		}
			
	}
	
}
