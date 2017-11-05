import java.awt.Graphics;


public class CardsCounter extends Sprites {

	public CardsCounter(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void paint(Graphics field){
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSELECTED);
		
		field.drawString("Cards Left: ", this.x,this.y);
		
		field.setFont(RandomSpell.FONTNORMAL);
		
		field.drawString(String.format("%02d",GameScreen.cards), this.x+60, this.y);
		
	}

}
