import java.awt.Graphics;
import java.awt.Graphics2D;


public class WinnerScreen extends Screen {

	private static final String TITLE = "You have win the game";
	private static final String LINE1 = "In the epic battle between the";
	private static final String LINE2 = "good and evil you are the worst";
	private static final String LINE3 = "wizard ever. But you know, at least";
	private static final String LINE4 = "now you have all your cards back.";
	
	public WinnerScreen(){
		
		super();
		
		this.num = RandomSpell.WINNER_SCREEN;
		
	}
	
	@Override
	public void move(){
		
		if(this.keys.size()!=0){
			
			if(RandomSpell.actualpoints <= RandomSpell.minpoints){
			
				RandomSpell.nextscreen = RandomSpell.MENU_SCREEN;
				
			}else{
				
				RandomSpell.nextscreen = RandomSpell.SCORE_SCREEN;
				
			}
						
		}
		
	}

	@Override
	public void paint(Graphics fieldin){
		
		Graphics field;
		
		field = this.buffer;
		
		this.paint_back(field);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(WinnerScreen.TITLE, 50, 70);
		
		field.setColor(RandomSpell.COLOR4);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(WinnerScreen.LINE1, 90, 100);
		field.drawString(WinnerScreen.LINE2, 90, 120);
		field.drawString(WinnerScreen.LINE3, 90, 140);
		field.drawString(WinnerScreen.LINE4, 90, 160);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(Screen.press, 60, 190);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}
	
}
