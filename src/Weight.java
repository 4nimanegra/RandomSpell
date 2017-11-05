import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Weight extends Sprites {

	public static final int TYPE_SPELL = 4;

	int vely;
	
	boolean onfloor;
	Explosion explosion;
	
	public Weight(int x, int y) {
		
		super(x, y);

		ImageIcon ii;
		
		ii = new ImageIcon("sprites/weight.png");
		
		this.image = ii.getImage();
		
		this.onfloor = false;
		
		this.widht = this.image.getWidth(null);
		this.heigth = this.image.getHeight(null);
		
		this.explosion = null;
	
	}
	
	@Override
	public boolean finished(){
		
		if(this.explosion != null){
		
			return this.explosion.finished();
		
		}else{
			
			return false;
			
		}
	}
	
	@Override
	public void paint(Graphics field){
		
		if(this.finished == false){
			
			super.paint(field);
			
		}else{
			
			this.explosion.paint(field);
			
		}
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){

		int endy;
		ArrayList<Sprites> colisionlist;
		
		if(this.y > RandomSpell.HEIGHT*2){
			
			this.explosion = new Explosion(this.x, this.y);
			
			this.finished = true;
			
		}
		
		if(this.finished != true){
		
			this.vely = this.vely + Player.GRAVITY;
			
			colisionlist = null;			
					
			endy = this.y + this.vely;
					
			while(this.y != endy){
				
				while(this.collisionall()){
					
					Player.killPlayer(this.colisionlist);
					
					Enemy1.killEnemy(this.colisionlist);
									
					if(this.vely > 0){
						
						this.y = this.y-1;
						
					}else{
						
						this.y = this.y+1;
						
					}
					
					colisionlist = (ArrayList<Sprites>) this.colisionlist.clone();
					
				}
				
				if(colisionlist != null){
				
					this.colisionlist = colisionlist;
					
				}
					
				if(this.colisionlist.size() > 0){
					
					if(this.vely > 0){
						
						this.onfloor = true;
						
					}
					
					this.vely = 0;
					
					this.explosion = new Explosion(this.x, this.y);
					
					this.finished = true;
						
					break;
					
				}
				
				if(this.vely > 0){
					
					this.y = this.y+1;
					
				}else if(this.vely < 0){
					
					this.y = this.y-1;
					
				}
				
			}
			
			while(this.collisionall()){
				
				Player.killPlayer(this.colisionlist);
				
				Enemy1.killEnemy(this.colisionlist);

				
				if(this.vely > 0){
					
					this.y = this.y-1;
					
				}else{
					
					this.y = this.y+1;
					
				}
				
				colisionlist = (ArrayList<Sprites>) this.colisionlist.clone();
				
			}
			
			if(colisionlist != null){
			
				this.colisionlist = colisionlist;
				
			}
		
		}else{
			
			this.explosion.move(keys);
			
		}
		
	}

}
