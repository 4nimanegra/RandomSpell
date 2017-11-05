import javax.swing.ImageIcon;


public class ComplexBrick1Center extends SimpleBrick {

	public ComplexBrick1Center(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/bricen.png");
		
		this.image = ii.getImage();
	}

}
