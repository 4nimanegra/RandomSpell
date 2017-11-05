import java.awt.Graphics;
import java.awt.Graphics2D;


public class MenuScreen extends Screen {
	
	public static final String TITLE = "Random Spell";
	public static final String NEWGAME = "Start New Game";
	public static final String HOF = "Hall Of Fame";
	public static final String OPTIONS = "Options Menu";
	public static final String EXITGAME = "Exit the Game";
	
	public static final int NEWGAME_OPTION = 0;
	public static final int OPTION_OPTION = 1;
	public static final int HOF_OPTION = 2;
	public static final int EXIT_OPTION = 3;
	
	private int selection;
	private int total_selection=4;
	
	public MenuScreen(){
		
		super();
				
		this.selection = 0;
		this.num = RandomSpell.MENU_SCREEN;
		
	}
	
	@Override
	public void move(){
				
		if(this.keys.contains(new Integer(Player.UP))){
			
			this.selection = (selection - 1 + this.total_selection)%this.total_selection;
			
		}else if(this.keys.contains(new Integer(Player.DOWN))){
			
			this.selection = (this.selection + 1)%this.total_selection;
			
		}
		
		if((this.keys.contains(new Integer(Player.FIRE))) || (this.keys.contains(new Integer(Player.JUMP)))){
			
			switch(this.selection){
			
			case MenuScreen.NEWGAME_OPTION:
			{
			
				RandomSpell.nextscreen = RandomSpell.GAME_SCREEN;

				break;
			}
			case MenuScreen.OPTION_OPTION:
			{
				
				RandomSpell.nextscreen = RandomSpell.OPTION_SCREEN;
				
				break;
			}
			case MenuScreen.HOF_OPTION:
			{
				
				RandomSpell.nextscreen = RandomSpell.HOF_SCREEN;
				
				break;
			}
			case MenuScreen.EXIT_OPTION:
			{
				
				System.exit(0);
				
				break;
			}	
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
		field.drawString(MenuScreen.TITLE, 90, 70);
		
		field.setColor(RandomSpell.COLOR4);
		if(MenuScreen.NEWGAME_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		field.drawString(MenuScreen.NEWGAME, 115, 100);
		
		field.setColor(RandomSpell.COLOR4);
		
		if(MenuScreen.OPTION_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		
		field.drawString(MenuScreen.OPTIONS, 120, 120);
		
		
		field.setColor(RandomSpell.COLOR4);
		if(MenuScreen.HOF_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		field.drawString(MenuScreen.HOF, 125, 140);
		
		if(MenuScreen.EXIT_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		
		field.drawString(MenuScreen.EXITGAME, 120, 160);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}

}
