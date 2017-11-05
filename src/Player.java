import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Player extends Sprites{

	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	public static final int FIRE = KeyEvent.VK_SPACE;
	public static final int JUMP = KeyEvent.VK_SHIFT;	
	
	public static final int MOVE_STOP = 0;
	public static final int MOVE_WALK = 1;
	public static final int MOVE_JUMP = 2;
	public static final int MOVE_DIE = 3;
	
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 0;
	
	public static final int VEL_MAX = 5;
	public static final int ACC = 3;
	public static final int DEC = 1;
	
	public static final int GRAVITY = 1;
	public static final int JUMP_POWER = 10;
	private static final int DISTANCE_FIRE = 20;
	public static final int TYPE_PLAYER = 5;
	public static int spell;
	
	Image[][] image;
	
	int animation;
	int state;
	
	int velx;
	int vely;
	boolean onfloor;
	
	int direction;
	
	boolean casting;
	private Explosion explosion;
	
	public Player(int x, int y) {
		super(x, y);
		
		this.type = Player.TYPE_PLAYER;
		
		this.animation = 0;
		this.state = Player.MOVE_STOP;
		
		this.image = new Image[8][];
		
		this.image[0] = new Image[1];
		this.image[1] = new Image[2];
		this.image[2] = new Image[2];
		this.image[3] = new Image[2];
		
		this.image[4] = new Image[1];
		this.image[5] = new Image[2];
		this.image[6] = new Image[2];
		this.image[7] = new Image[2];
		
		ImageIcon ii;
		
		ii=new ImageIcon("sprites/hero.png");
		
		this.image[0][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero.png");
		
		this.image[1][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2.png");
		
		this.image[1][1] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero.png");
		
		this.image[2][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2.png");
		
		this.image[2][1] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero.png");
		
		this.image[3][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2.png");
		
		this.image[3][1] = ii.getImage();
		
		ii=new ImageIcon("sprites/herol.png");
		
		this.image[4][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/herol.png");
		
		this.image[5][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2l.png");
		
		this.image[5][1] = ii.getImage();
		
		ii=new ImageIcon("sprites/herol.png");
		
		this.image[6][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2l.png");
		
		this.image[6][1] = ii.getImage();
		
		ii=new ImageIcon("sprites/herol.png");
		
		this.image[7][0] = ii.getImage();
		
		ii=new ImageIcon("sprites/hero2l.png");
		
		this.image[7][1] = ii.getImage();
		
		this.onfloor = false;
		
		this.widht = this.image[0][0].getWidth(null);
		this.heigth = this.image[0][0].getHeight(null);
		
		this.state = Player.MOVE_STOP;
		
		this.direction = Player.DIR_RIGHT;
		
		this.casting = false;
		
		Player.spell = ((int)(4*(Math.random()))%3);
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		int endx;
		int endy;
		
		if(this.y > RandomSpell.HEIGHT*2){
			
			this.kill();
			
		}
		
		if(this.finished == true){
			
			this.explosion.move(keys);
			
			return;
			
		}
				
		ArrayList<Sprites> colisionlist;
		
		this.use_keys(keys);
				
		if(this.velx > 0){
			
			if(this.velx > Player.DEC){
				
				this.velx = this.velx - Player.DEC;
				
			}else{
			
				this.velx = 0;
								
			}
			
		}
		
		if(this.velx < 0){
			
			if(this.velx < Player.DEC){
				
				this.velx = this.velx + Player.DEC;
				
			}else{
				
				this.velx = 0;
								
			}
			
			
		}
		
		endx = this.x + this.velx;
		
		if(endx < 0){
			
			endx = 0;
			
		}
		
		if(endx > GameScreen.WIDTH-this.widht){
			
			endx = GameScreen.WIDTH-this.widht;
			
		}
		
		colisionlist = null;
		
		while(this.x != endx){
			
			while(this.collisionall()){
				
				if(Card.getCard(this.colisionlist)){
					
					continue;
					
				}
								
				if(this.velx > 0){
					
					this.x = this.x-1;
					
				}else{
					
					this.x = this.x+1;
					
				}
				
				colisionlist = (ArrayList<Sprites>) this.colisionlist.clone();
				
			}
			
			if(colisionlist != null){
			
				this.colisionlist = colisionlist;
			
			}
				
			if(this.colisionlist.size() > 0){
				
				int i;
				
				i=0;
				
				while(i<this.colisionlist.size()){
					
					if((this.colisionlist.get(i).type == Enemy1.TYPE_ENEMY) || 
							(this.colisionlist.get(i).type == Weight.TYPE_SPELL)){
						
						this.finished=true;
						
						this.explosion = new Explosion(this.x,this.y);
						
					}
					
					i=i+1;
					
				}
				
				this.velx = 0;
				
				break;
				
			}
			
			if(this.velx > 0){
				
				this.x = this.x+1;
				
			}else{
				
				this.x = this.x-1;
				
			}
			
		}
		
		while(this.collisionall()){
			
			if(this.velx > 0){
				
				this.x = this.x-1;
				
			}else{
				
				this.x = this.x+1;
				
			}
			
			colisionlist = (ArrayList<Sprites>) this.colisionlist.clone();
			
		}
		
		if(colisionlist != null){
		
			this.colisionlist = colisionlist;
		
		}
				
		this.vely = this.vely + Player.GRAVITY;
			
		colisionlist = null;			
				
		endy = this.y + this.vely;
				
		while(this.y != endy){
			
			while(this.collisionall()){
				
				if(Card.getCard(this.colisionlist)){
					
					continue;
					
				}
								
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
					
				break;
				
			}
			
			if(this.vely > 0){
				
				this.y = this.y+1;
				
			}else if(this.vely < 0){
				
				this.y = this.y-1;
				
			}
			
		}
		
		while(this.collisionall()){
			
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
		
		if(this.state == Player.MOVE_WALK){
			
			if(this.velx == 0){
			
				this.state = Player.MOVE_STOP;
			
			}
			
		}
		
		if(this.state == Player.MOVE_JUMP){
			
			if(this.vely == 0){
			
				this.state = Player.MOVE_STOP;
			
			}
			
		}
		
	}

	private void use_keys(ArrayList<Integer> key) {
		
		int randspell;
		
		if(key.contains(new Integer(Player.LEFT))){
			
			this.state = Player.MOVE_WALK;
			
			this.direction = Player.DIR_LEFT;
			
			if(this.velx > -Player.VEL_MAX){
			
				this.velx = this.velx - Player.ACC;
			
			}
		}
		
		if(key.contains(new Integer(Player.RIGHT))){
			
			this.state = Player.MOVE_WALK;
			
			this.direction = Player.DIR_RIGHT;
			
			if(this.velx < Player.VEL_MAX){
				
				this.velx = this.velx + Player.ACC;
			
			}
			
		}
		
		if(key.contains(new Integer(Player.JUMP))){
						
			if(this.onfloor){
				
				this.vely = this.vely - Player.JUMP_POWER;
				
				this.onfloor = false;
				
				this.state = Player.MOVE_JUMP;
			
			}
			
		}
		
		if(key.contains(new Integer(Player.FIRE))){
			
			if(this.casting == false){
			
				this.casting = true;
				
				Brush brush;
				
				if(this.direction == Player.DIR_LEFT){
				
					if(Player.spell == 0){
					
						GameScreen.list.add(new Weight(this.x-Player.DISTANCE_FIRE, -10));
			
					}else if(Player.spell == 1){
						
						brush = new Brush(this.x-Player.DISTANCE_FIRE*2, this.y);
						
						brush.setDirtection(this.x, this.y);
						
						GameScreen.list.add(brush);
						
					}else{
						
						GameScreen.list.add(new Rabbit(this.x, this.y));
						
						
					}
			
				}else{
				
					if(Player.spell == 0){
					
						GameScreen.list.add(new Weight(this.x+Player.DISTANCE_FIRE, -10));
				
					}else if(Player.spell == 1){
						
						brush = new Brush(this.x+this.widht+5, this.y);
						
						brush.setDirtection(this.x, this.y);
						
						GameScreen.list.add(brush);
						
						
					}else{
						
						GameScreen.list.add(new Rabbit(this.x, this.y));
						
					}
				}
				
				Player.spell = ((int)(4*(Math.random()))%3);

			}
			
		}else{
			
			this.casting = false;
			
		}
		
	}
	
	@Override
	public boolean finished(){
		
		if(this.explosion == null){
		
			return false;
			
		}else{
			
			return this.explosion.finished();
			
		}
		
	}

	@Override
	public void paint(Graphics field){
		
		if(this.finished == true){
			
			this.explosion.paint(field);
			
			return;
			
		}
				
		field.drawImage(this.image[this.state+this.direction*4][this.animation%this.image[this.state].length], this.x, this.y, null);
		
		this.animation = (this.animation + 1)%this.image[this.state].length;
				
	}
	
	public static void killPlayer(ArrayList<Sprites> list){
		
		int i;
		
		i=0;
		
		while(i < list.size()){
			
			if(list.get(i).type == Player.TYPE_PLAYER){
				
				list.get(i).kill();
				
			}
			
			i=i+1;
			
		}
		
	}
	
	@Override
	public void kill(){
		
		if(this.finished == true){
			
			return;
			
		}
		
		this.finished=true;
		
		this.explosion = new Explosion(this.x,this.y);
		
	}

}
