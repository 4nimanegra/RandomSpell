import javax.swing.ImageIcon;


public class ComplexBrick1Right extends SimpleBrick {

	public ComplexBrick1Right(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/brider.png");
		
		this.image = ii.getImage();
	
	}

}
