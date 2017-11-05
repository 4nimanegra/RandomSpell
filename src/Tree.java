import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Tree extends Sprites {

	public Tree(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/tree.png");
		
		this.image = ii.getImage();
		
	}

}
