import java.awt.Graphics;


public class LevelNotificator extends Sprites {

	private static final int REPEATS = 3;

	private static final int CONT = 10;

	int level;
	int lives;
	
	int counter;
	int repeat;
	
	public LevelNotificator(int x, int y) {
		super(x, y);
		
		this.level = GameScreen.level;
		this.counter = 0;
		this.repeat = 0;
		this.lives = GameScreen.lives;
		
	}
	
	@Override
	public void paint(Graphics field){
		
		if(this.repeat < LevelNotificator.REPEATS*2){
		
			if(this.repeat%2 == 0){
				
				field.setColor(RandomSpell.COLOR4);
				field.setFont(RandomSpell.FONTTITLE);
				
				field.drawString("Level ", this.x,this.y);
				
				field.setFont(RandomSpell.FONTTITLE);
				
				field.drawString(String.format("%02d",GameScreen.level), this.x+20, this.y+50);
				
			}
			
			this.counter = this.counter + 1;
			
			if(this.counter == LevelNotificator.CONT){
				
				this.counter = 0;
				
				this.repeat = this.repeat + 1;
				
			}
		
		}
		
		if(this.level != GameScreen.level){
			
			this.level = GameScreen.level;
			
			this.counter = 0;
			
			this.repeat = 0;
			
		}
		
		if(this.lives != GameScreen.lives){
			
			this.counter = 0;
			
			this.repeat = 0;
			
			this.lives = GameScreen.lives;
			
		}
		
	}

}
