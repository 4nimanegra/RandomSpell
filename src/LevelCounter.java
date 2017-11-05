import java.awt.Graphics;


public class LevelCounter extends Sprites {

	public LevelCounter(int x, int y) {
		super(x, y);
	}

	@Override
	public void paint(Graphics field){
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSELECTED);
		
		field.drawString("Level: ", this.x,this.y);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(String.format("%02d",GameScreen.level), this.x+40, this.y);
		
	}
	
}
