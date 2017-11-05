import java.awt.Graphics;
import java.awt.Graphics2D;


public class OptionScreen extends Screen {
	
	public static final String TITLE = "Options";
	public static final String LINE1 = "Scale X1";
	public static final String LINE2 = "Scale X2";
	public static final String LINE3 = "Scale X3";
	public static final String LINE4 = "Back";
	
	public static final int X1_OPTION = 0;
	public static final int X2_OPTION = 1;
	public static final int X3_OPTION = 2;
	public static final int BACK_OPTION = 3;
	
	private int selection;
	private int total_selection=4;
	
	public OptionScreen(){
		
		super();
		this.num = RandomSpell.OPTION_SCREEN;
		this.selection = 0;
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
			
				case OptionScreen.X1_OPTION:
				{
					
					RandomSpell.MyJFrame.setSize(RandomSpell.WIDTH, RandomSpell.HEIGHT);
					
					RandomSpell.scale = 1;
				
					break;
				}
				case OptionScreen.X2_OPTION:
				{
					
					RandomSpell.MyJFrame.setSize(RandomSpell.WIDTH*2, RandomSpell.HEIGHT*2);
					
					RandomSpell.scale = 2;
					
					break;
				}
				case OptionScreen.X3_OPTION:
				{
					

					RandomSpell.MyJFrame.setSize(RandomSpell.WIDTH*3, RandomSpell.HEIGHT*3);
					
					RandomSpell.scale = 3;
					
					break;
				}
				case OptionScreen.BACK_OPTION:
				{
					
					RandomSpell.nextscreen = RandomSpell.MENU_SCREEN;
					
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
		field.drawString(OptionScreen.TITLE, 110, 70);
		
		field.setColor(RandomSpell.COLOR4);
		if(OptionScreen.X1_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		field.drawString(OptionScreen.LINE1, 125, 100);
		
		field.setColor(RandomSpell.COLOR4);
		
		if(OptionScreen.X2_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		
		field.drawString(OptionScreen.LINE2, 125, 120);
		
		
		field.setColor(RandomSpell.COLOR4);
		if(OptionScreen.X3_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		field.drawString(OptionScreen.LINE3, 125, 140);
		
		if(OptionScreen.BACK_OPTION!=this.selection){
			field.setFont(RandomSpell.FONTNORMAL);
		}else{
			field.setFont(RandomSpell.FONTSELECTED);
		}
		
		field.drawString(OptionScreen.LINE4, 125, 160);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		System.out.println(RandomSpell.scale+" ");
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}

}
