import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class HofScreen extends Screen {

	private static final String TITLE = "Hall of Fame";

	public HofScreen(){
		
		super();
		this.num = RandomSpell.HOF_SCREEN;
		
	}
	
	@Override
	public void paint(Graphics fieldin) {

		Graphics field;
		
		field = this.buffer;
		
		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		String line;
		String[] words;
		
		int i;
		
		this.paint_back(field);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(HofScreen.TITLE, 90, 70);
		
		field.setColor(RandomSpell.COLOR4);
		
		field.setFont(RandomSpell.FONTSELECTED);
		
		i=85;
		
		try {
			
			fi = new FileInputStream("data/scores");
			
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			try {
				
				line = br.readLine();
				
				while(line != null){

					field.drawString(line, 115, i);
					
					i=i+10;
					
					line = br.readLine();

				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(Screen.press, 60, 190);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}
	
}
