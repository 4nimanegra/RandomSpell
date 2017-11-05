import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Enemy2 extends Enemy1 {
	
	public static final int STEPSX = RandomSpell.WIDTH;
	public static final int STEPSY = RandomSpell.HEIGHT;
	
	public static final int VELOCITY = 5;
	
	int xpath[];
	int ypath[];
	
	int movement;
	
	int animation;

	public Enemy2(int x, int y) {
		super(x, y);

		this.ypath = new int[4];
		this.xpath = new int[4];
		
		this.image = new Image[1][2];
		
		ImageIcon ii;
		
		ii = new ImageIcon("sprites/bad2.png");
		
		this.image[0][0] = ii.getImage();
		
		ii = new ImageIcon("sprites/bad22.png");
		
		this.image[0][1] = ii.getImage();
		
		this.xpath[0]=this.x;
		this.xpath[1]=this.x+Enemy2.STEPSX/2;
		this.xpath[2]=this.x+Enemy2.STEPSX;
		this.xpath[3]=this.x+Enemy2.STEPSX/2;
		
		this.ypath[0]=this.y;
		this.ypath[1]=this.y-Enemy2.STEPSY/2;
		this.ypath[2]=this.y;
		this.ypath[3]=this.y+Enemy2.STEPSY/2;
		
		this.movement = 0;
		
		this.animation = 0;
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.drawImage(this.image[0][this.animation % this.image[0].length], this.x, this.y, null);
				
		this.animation = this.animation  + 1;
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		int i;
		
		i=0;
		
		while(i < Enemy2.VELOCITY){
		
			if((this.x == this.xpath[this.movement]) &&
				(this.y == this.ypath[this.movement])){
				
				
				this.movement = (this.movement + 1) % this.xpath.length;
				
			}
			
			if(this.x < this.xpath[this.movement]){
				
	
				
				this.x = this.x + 1;
				
			}
			
			if(this.x > this.xpath[this.movement]){
				
						
						this.x = this.x - 1;
						
			}
					
			if(this.y < this.ypath[this.movement]){
				
				this.y = this.y + 1;
				
			}
			
			if(this.y > this.ypath[this.movement]){
					
				this.y = this.y -1;
				
			}
			
			this.collisionall();
			
			if(this.colisionlist.size() > 0){

				int ii;
				
				ii=0;
				
				while(ii < this.colisionlist.size()){
					
					if(this.colisionlist.get(ii).type == Player.TYPE_PLAYER){
				
						Player.killPlayer(GameScreen.list);
					
					}
					
					ii=ii+1;
					
				}
					
			}
				
			i=i+1;
			
		}
			
	}

}
