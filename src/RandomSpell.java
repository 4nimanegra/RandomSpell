import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;


public class RandomSpell extends JFrame {
	
	public static final int WIDTH=320;
	public static final int HEIGHT=240;
	
	public static final Color COLOR1= new Color(255,255,255);
	public static final Color COLOR2= new Color(150,150,150);
	public static final Color COLOR3= new Color(80,80,80);
	public static final Color COLOR4= new Color(0,0,0);
	
	public static final Font FONTTITLE = new Font("Arial", Font.BOLD, 40);
	public static final Font FONTSUBTITLE = new Font("Arial", Font.BOLD, 20);
	public static final Font FONTNORMAL = new Font("Arial", Font.PLAIN, 10);
	public static final Font FONTSELECTED = new Font("Arial", Font.BOLD, 10);
	
	public static final int EXITKEY = KeyEvent.VK_ESCAPE;
	
	public static final int TITLE_SCREEN = 0;
	public static final int MENU_SCREEN = 1;
	public static final int GAME_SCREEN = 2;
	public static final int OPTION_SCREEN = 3;
	public static final int HOF_SCREEN = 4;
	public static final int WINNER_SCREEN = 5;
	public static final int GAMEOVER_SCREEN = 6;
	public static final int SCORE_SCREEN = 7;
	
	public static int nextscreen;
	public static int actualpoints;
	public static int minpoints;
	public Screen screen;
	
	public static JFrame MyJFrame;
	public static double scale;
	
	public RandomSpell(){
		
		RandomSpell.MyJFrame = this;
		
		this.scale = 1;
		
		this.setSize(RandomSpell.WIDTH,RandomSpell.HEIGHT);
		this.setTitle("Random Spell");

		this.setUndecorated(true);
		
		this.nextscreen = RandomSpell.TITLE_SCREEN;
		this.screen = new Screen();
		
		this.add(this.screen);
		
		this.requestFocusInWindow();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.getMinimumPoints();
		
		this.loopgame();
		
	}
	
	private void getMinimumPoints() {

		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		String line;
		String[] words;
		
		try {
			
			fi = new FileInputStream("data/scores");
			
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			try {
				
				line = br.readLine();
				
				while(line != null){
					

					words = line.split(" ");
					
					RandomSpell.minpoints = new Integer(words[1]);
					
					line = br.readLine();

				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return;
	}

	private void loopgame(){
		
		while(true){
						
			if(this.screen.num != RandomSpell.nextscreen){
				
				this.remove(this.screen);
				
				this.screen.destroytimer();
				
				switch(RandomSpell.nextscreen){
				
					case RandomSpell.TITLE_SCREEN:
					{
						
						this.screen = new Screen();
						
						break;
					}
					case RandomSpell.GAME_SCREEN:
					{
						this.screen = new GameScreen();
						
						break;
					}
					case RandomSpell.OPTION_SCREEN:
					{
						
						this.screen = new OptionScreen();
						
						break;
					}
					case RandomSpell.HOF_SCREEN:
					{
						
						this.screen = new HofScreen();
						
						
						break;
					}
					case RandomSpell.MENU_SCREEN:
					{
												
						this.screen = new MenuScreen();
						
						break;
						
					}
					case RandomSpell.WINNER_SCREEN:
					{
												
						this.screen = new WinnerScreen();
						
						break;
						
					}
					case RandomSpell.GAMEOVER_SCREEN:
					{
												
						this.screen = new GameOverScreen();
						
						break;
						
					}
					case RandomSpell.SCORE_SCREEN:
					{
												
						this.screen = new InsertScoreScreen();
						
						break;
						
					}
			
				}
				
				this.add(this.screen);
				
				this.requestFocusInWindow();
				this.validate();
				
				this.screen.requestFocus();
				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		
		}
		
		
	}
	
	public static void main(String[] args) {

		new RandomSpell();
		
	}

}
