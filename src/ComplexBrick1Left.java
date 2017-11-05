import javax.swing.ImageIcon;


public class ComplexBrick1Left extends SimpleBrick {

	public ComplexBrick1Left(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/brizizq.png");
		
		this.image = ii.getImage();
	}

}
