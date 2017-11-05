import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Screen extends JPanel{
	
	public static final String title="Random";
	public static final String title2="Spell";
	public static final String press="Pres any key...";
	
	BufferedImage bi;
	Graphics buffer;
	
	BufferedImage bi2;
	Graphics buffer2;
	
	int num;
	
	Timer timer;
	ArrayList<Integer> keys;
	
	public Screen(){
		
		this.num = RandomSpell.TITLE_SCREEN;
		
		this.keys = new ArrayList<Integer>();
		
		this.bi = new BufferedImage(RandomSpell.WIDTH, RandomSpell.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		
		this.buffer = bi.getGraphics();
		
		this.bi2 = new BufferedImage(RandomSpell.WIDTH, RandomSpell.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		
		this.buffer2 = bi2.getGraphics();
		
		this.addKeyListener(new Keys());
		this.setFocusable(true);
			
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new RepeatMe(), 100, 40);
		
	}
	
	public void move(){
		
		if(this.keys.size()!=0){

			RandomSpell.nextscreen = RandomSpell.MENU_SCREEN;
						
		}
		
	}
	
	public void paint_back(Graphics field){
		
		field.setColor(RandomSpell.COLOR1);
		field.fillRect(0, 0, RandomSpell.WIDTH, RandomSpell.HEIGHT);
		
		field.setColor(RandomSpell.COLOR2);
		field.fillRect(10, 10, RandomSpell.WIDTH-2*10, RandomSpell.HEIGHT-2*10);
		
		field.setColor(RandomSpell.COLOR3);
		field.fillRect(20, 20, RandomSpell.WIDTH-2*20, RandomSpell.HEIGHT-2*20);
		
		field.setColor(RandomSpell.COLOR4);
		field.fillRect(30, 30, RandomSpell.WIDTH-2*30, RandomSpell.HEIGHT-2*30);
		
		field.setColor(RandomSpell.COLOR1);
		field.fillRect(40, 40, RandomSpell.WIDTH-2*40, RandomSpell.HEIGHT-2*40);
		
	}
	
	@Override
	public void paint(Graphics fieldin){
		
		Graphics field;
		
		field = this.buffer;
		
		super.paint(field);
		
		this.paint_back(field);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTTITLE);
		field.drawString(Screen.title, 80, 70);
		field.drawString(Screen.title2, 110, 100);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(Screen.press, 60, 190);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0,0,null);
		
	}
	
	private class Keys extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent key){
						
			Integer keycode = key.getKeyCode();
			
			if(keycode == RandomSpell.EXITKEY){
				
				System.exit(0);
				
			}
						
			if(!Screen.this.keys.contains(keycode)){
				
				Screen.this.keys.add(keycode);
				
			}
			
		}
		
		@Override
		public void keyReleased(KeyEvent key){
			
			Integer keycode = key.getKeyCode();
			
			if(Screen.this.keys.contains(keycode)){
			
				Screen.this.keys.remove(keycode);
				
			}
			
		}
		
	}
	
	protected class RepeatMe extends TimerTask{

		@Override
		public void run() {

			Screen.this.move();
			Screen.this.repaint();
			
		}
		
		
	}
	
	public void destroytimer(){
		
		this.timer.cancel();
		
	}
	

}
