import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Card extends Sprites {

	public static final int TYPE_COLLECTABLE = 6;

	public Card(int x, int y) {
		super(x, y);

		ImageIcon ii = new ImageIcon("sprites/card.png");
		
		this.image = ii.getImage();
		
		this.type = Card.TYPE_COLLECTABLE;
		
		this.heigth = this.image.getHeight(null);
		this.widht = this.image.getWidth(null);
		
	}
	
	public static boolean getCard(ArrayList<Sprites> list){
		
		int i;
		boolean ret;
		
		ret = false;
		
		i=0;
		
		while(i < list.size()){
			
			if(list.get(i).type == Card.TYPE_COLLECTABLE){
				
				if(!list.get(i).finished()){
				
					list.get(i).kill();
				
					ret = true;
				
				}
			}
			
			i=i+1;
			
		}
		
		return ret;
		
	}
	
	@Override
	public void kill(){
		
		if(this.finished == true){
			
			return;
			
		}
		
		this.finished=true;
				
	}

}
