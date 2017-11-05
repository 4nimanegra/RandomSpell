import java.awt.Graphics;
import java.util.ArrayList;


public class Explosion extends Sprites {

	ArrayList<Explode> parts;
	
	public Explosion(int x, int y) {
		super(x, y);
		
		int i;
		
		this.parts = new ArrayList<Explode>();
		
		i=0;
		
		while(i < 8){
			
			this.parts.add(new Explode(this.x,this.y,i));
			
			i=i+1;
			
		}
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		int i;
		
		i=0;
		
		while(i<this.parts.size()){
			
			this.parts.get(i).move(keys);
			
			i=i+1;
			
			
		}
		
		if(this.parts.get(0).finished()){
			
			this.finished = true;
			
		}
		
	}
	
	@Override
	public void paint(Graphics field){
		
		int i;
		
		i=0;
		
		while(i<this.parts.size()){
			
			this.parts.get(i).paint(field);
			
			i=i+1;
			
			
		}
		
	}

}
