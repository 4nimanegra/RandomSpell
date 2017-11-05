import java.awt.Graphics;
import java.util.ArrayList;


public class Explode extends Sprites {
	
	private static final int SIZE = 10;
	private static final int MOVEMENT = 10;
	private static final int STEPS = 10;
	
	int step;
	int direction;
	
	public Explode(int x, int y,int direction) {
		super(x, y);
		
		this.direction = direction;
		this.step = 0;
		
	}
	
	@Override
	public void paint(Graphics field){
		
		field.setColor(RandomSpell.COLOR4);
		field.fillOval(this.x,this.y,Explode.SIZE,Explode.SIZE);
		
		field.setColor(RandomSpell.COLOR1);
		field.fillOval(this.x+1,this.y+1,Explode.SIZE-2,Explode.SIZE-2);
		
	}
	
	@Override
	public boolean finished(){
		
		return this.step == Explode.STEPS;
		
	}
	
	@Override
	public void move(ArrayList<Integer> keys){
		
		switch(this.direction){
		
		case 0:
		{
			
			this.x=this.x+Explode.MOVEMENT;
			
			break;
			
		}
		case 1:
		{
			
			this.x=this.x+Explode.MOVEMENT/2;
			this.y=this.y+Explode.MOVEMENT/2;
			
			break;
			
		}
		case 2:
		{
			
			this.y=this.y+Explode.MOVEMENT;
			
			break;
			
		}
		case 3:
		{
			
			this.y=this.y+Explode.MOVEMENT/2;
			this.x=this.x-Explode.MOVEMENT/2;
			
			break;
			
		}
		case 4:
		{
			
			this.x=this.x-Explode.MOVEMENT;
			
			break;
			
		}
		case 5:
		{
			
			this.x=this.x-Explode.MOVEMENT/2;
			this.y=this.y-Explode.MOVEMENT/2;
			
			break;
			
		}
		case 6:
		{
			
			this.y=this.y-Explode.MOVEMENT;
			
			break;
			
		}
		case 7:
		{
			
			this.y=this.y-Explode.MOVEMENT/2;
			this.x=this.x+Explode.MOVEMENT/2;
			
			break;
			
		}
		}
		
		this.step = this.step+1;
		
	}
	
}
