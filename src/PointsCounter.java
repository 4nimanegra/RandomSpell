import java.awt.Graphics;


public class PointsCounter extends Sprites {

	public PointsCounter(int x, int y) {
	
		super(x, y);
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSELECTED);
		
		field.drawString("Points: ", this.x,this.y);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(String.format("%05d",GameScreen.points), this.x+40, this.y);
		
	}

}
