import javax.swing.ImageIcon;


public class SimpleBrick2 extends SimpleBrick {

	public SimpleBrick2(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/brick1.png");
		
		this.image = ii.getImage();
		
	}

}
