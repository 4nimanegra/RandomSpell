import java.awt.Graphics;


public class LivesCounter extends LevelCounter {

	public LivesCounter(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void paint(Graphics field){
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSELECTED);
		
		field.drawString("Lives: ", this.x,this.y);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(String.format("%02d",GameScreen.lives), this.x+40, this.y);
		
	}

}
