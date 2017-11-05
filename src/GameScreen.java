import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class GameScreen extends Screen {
	
	public static final int WIDTH = RandomSpell.WIDTH*20; 
	public static final int SPACE = RandomSpell.WIDTH/6;
	public static final int ADVANCECAMERA = 5;
	private static final int GET_POINTS = 100;
	private static final int LIVES = 5;
	
	public static ArrayList<Sprites> list;
	public ArrayList<Sprites> backgroundlist;
	public ArrayList<Sprites> interfacelist;
	
	String[] level_files;
	
	static int level;
	static int points;
	static int lives;
	
	int playx;
	int playy;
	
	static int cards;
	
	int x;
	
	int velcamera;
	
	public GameScreen(){
		
		super();
		
		RandomSpell.actualpoints = 0;
		
		this.timer.cancel();
				
		this.velcamera = 0;
		
		this.num = RandomSpell.GAME_SCREEN;
		
		GameScreen.list = new ArrayList<Sprites>();
		this.interfacelist = new ArrayList<Sprites>();
		this.backgroundlist = new ArrayList<Sprites>();
		
		GameScreen.level = -1;
		GameScreen.points = 0;
		GameScreen.lives = GameScreen.LIVES;
		
		this.interfacelist.add(new CardsCounter(RandomSpell.WIDTH/2+RandomSpell.WIDTH/6, 10));
		this.interfacelist.add(new PointsCounter(RandomSpell.WIDTH/6, 10));
		this.interfacelist.add(new LevelCounter(RandomSpell.WIDTH/5, RandomSpell.HEIGHT-10));
		this.interfacelist.add(new LivesCounter(RandomSpell.WIDTH/2+RandomSpell.WIDTH/6, RandomSpell.HEIGHT-10));
		this.interfacelist.add(new SpellCounter(RandomSpell.WIDTH/2, RandomSpell.HEIGHT-10));
		
		this.interfacelist.add(new LevelNotificator(RandomSpell.WIDTH/2-50, RandomSpell.HEIGHT/2));
		
		this.bi = new BufferedImage(GameScreen.WIDTH+100, RandomSpell.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		
		this.buffer = bi.getGraphics();
		
		this.bi2 = new BufferedImage(RandomSpell.WIDTH, RandomSpell.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		
		this.buffer2 = bi2.getGraphics();
		
		this.level_files = this.read_filename();
		
		GameScreen.cards = 0;
		
		this.preparelevel();
				
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new RepeatMe(), 100, 40);
				
	}
	
	private String[] read_filename() {

		int i;
		
		String line;
		String[] files;
		
		files = null;
		
		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		try {
			fi = new FileInputStream("maps/levels.txt");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			i=0;
			
			try {
				line = br.readLine();
				
				while(line != null){
				
					i=i+1;
					
					line = br.readLine();
					
				}
				
				br.close();
				
				files = new String[i];
				
				fi = new FileInputStream("maps/levels.txt");
				is = new InputStreamReader(fi);
				br = new BufferedReader(is);
				
				i=0;
				
				line = br.readLine();
				
				while(line != null){
				
					files[i]=line;
					
					i=i+1;
					
					line = br.readLine();
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return files;
		
	}

	public void preparelevel(){
		
		GameScreen.level = GameScreen.level+1;
		
		if(GameScreen.level == this.level_files.length){
			
			RandomSpell.actualpoints = GameScreen.points;
			
			RandomSpell.nextscreen = RandomSpell.WINNER_SCREEN;
			
			return;
			
		}
		
		GameScreen.list.clear();
		
		BlockTypes.get_level_from_file_to_Array("maps/"+this.level_files[this.level%this.level_files.length],GameScreen.list);
		
		int i;
		
		i=0;
		
		while(i < GameScreen.list.size()){
			
			if(GameScreen.list.get(i).type == Player.TYPE_PLAYER){
				
				this.x = GameScreen.list.get(i).x-GameScreen.SPACE*2;
				
				this.playx = GameScreen.list.get(i).x;
				this.playy = GameScreen.list.get(i).y;
				
			}
			
			i=i+1;
			
		}
		
	}
	
	@Override
	public void move(){
		
		int i;
		
		i=0;
		
		while(i < this.backgroundlist.size()){
			
			this.backgroundlist.get(i).move(this.keys);
			
			i=i+1;
		
		}
		
		i=0;
		
		while(i < GameScreen.list.size()){
			
			GameScreen.list.get(i).move(this.keys);
			
			if(GameScreen.list.get(i).type == Player.TYPE_PLAYER){
				
				if((this.x+RandomSpell.WIDTH/2 < GameScreen.list.get(i).x-GameScreen.SPACE)){
					
					if(this.velcamera < GameScreen.ADVANCECAMERA){
					
						this.velcamera = this.velcamera + 1;
					
					}
					
					this.x = this.x+this.velcamera;
					
				}else if(this.x+RandomSpell.WIDTH/2 > GameScreen.list.get(i).x+GameScreen.SPACE){
					
					if(this.velcamera > -GameScreen.ADVANCECAMERA){
						
						this.velcamera = this.velcamera - 1;
					
					}
					
					this.x = this.x+this.velcamera;
					
				}
				
				if(this.x < 0){
					
					this.x=0;
					
				}
				
				if(this.x > GameScreen.WIDTH-RandomSpell.WIDTH){
					
					this.x = GameScreen.WIDTH-RandomSpell.WIDTH;
					
				}
				
			}
			
			if(GameScreen.list.get(i).finished()){
				
				if(GameScreen.list.get(i).type == Card.TYPE_COLLECTABLE){
					
					GameScreen.cards = GameScreen.cards - 1;
					
					GameScreen.points = GameScreen.points + GameScreen.GET_POINTS;
					
				}
				
				if(GameScreen.list.get(i).type == Player.TYPE_PLAYER){
					
					if(GameScreen.lives >= 0){
					
						GameScreen.list.add(new Player(this.playx,this.playy));
					
						GameScreen.lives = GameScreen.lives - 1;
						
						this.x = this.playx-GameScreen.SPACE*2;
						
						if(this.lives == -1){
							
							RandomSpell.actualpoints = GameScreen.points;
							
							RandomSpell.nextscreen = RandomSpell.GAMEOVER_SCREEN;
							
						}
						
					}
				}
				
				GameScreen.list.remove(i);
				
			}else{
			
				i=i+1;
				
			}
		
		}
		
		if(GameScreen.cards == 0){
			
			this.preparelevel();
			
		}
		
	}
	
	@Override
	public void paint(Graphics field){
		
		this.buffer.setColor(RandomSpell.COLOR1);
		this.buffer.fillRect(0, 0, GameScreen.WIDTH+100, RandomSpell.HEIGHT);
				
		int i;
		
		i=0;
		
		while(i < this.backgroundlist.size()){
			
			this.backgroundlist.get(i).paint(this.buffer);
			
			i=i+1;
		
		}
		
		i=0;
		
		while(i < GameScreen.list.size()){
			
			GameScreen.list.get(i).paint(this.buffer);
			
			i=i+1;
		
		}
		
		this.buffer2.drawImage(bi, -this.x, 0, null);
		
		i=0;
		
		while(i < this.interfacelist.size()){
			
			this.interfacelist.get(i).paint(this.buffer2);
			
			i=i+1;
		
		}
		
		((Graphics2D)field).scale(RandomSpell.scale,RandomSpell.scale);
		
		field.drawImage(bi2,0,0,null);
		
	}

}
