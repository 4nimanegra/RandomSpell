import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class SpellCounter extends LevelCounter {

	Image image[];
	
	public SpellCounter(int x, int y) {
		super(x, y);
		
		this.image = new Image[3];
		
		ImageIcon ii;
		
		ii = new ImageIcon("sprites/weight.png");
		
		this.image[0] = ii.getImage();
		
		ii = new ImageIcon("sprites/brushl.png");
		
		this.image[1] = ii.getImage();
		
		ii = new ImageIcon("sprites/rabit1l.png");
		
		this.image[2] = ii.getImage();
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.drawImage(this.image[Player.spell], this.x-this.image[0].getWidth(null)/2, this.y-this.image[0].getHeight(null), null);
		
	}

}
