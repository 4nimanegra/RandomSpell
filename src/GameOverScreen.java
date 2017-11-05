import java.awt.Graphics;
import java.awt.Graphics2D;


public class GameOverScreen extends WinnerScreen {
	private static final String TITLE = "Game Over";
	private static final String LINE1 = "Try another time because you";
	private static final String LINE2 = "are the worst wizard I have";
	private static final String LINE3 = "ever seen.";
	
	public GameOverScreen(){
		
		super();
		
		this.num = RandomSpell.GAMEOVER_SCREEN;
		
	}
	
	@Override
	public void paint(Graphics fieldin){
		
		Graphics field;
		
		field = this.buffer;
		
		this.paint_back(field);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(GameOverScreen.TITLE, 100, 70);
		
		field.setColor(RandomSpell.COLOR4);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(GameOverScreen.LINE1, 90, 100);
		field.drawString(GameOverScreen.LINE2, 90, 120);
		field.drawString(GameOverScreen.LINE3, 90, 140);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(Screen.press, 60, 190);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}
}
