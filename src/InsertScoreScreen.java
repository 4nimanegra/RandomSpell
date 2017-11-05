import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class InsertScoreScreen extends Screen {
	
	private static final String TITLE = "Great Score";
	
	
	private static final String LINE = "You are on the Hall of Fame";


	private static final String LINE2 = "Your Score is: ";
	
	char[] name;
	int select;
	int previous;
	
	public InsertScoreScreen(){
		
		super();
		this.name = new char[3];
		this.name[0]='A';
		this.name[1]='A';
		this.name[2]='A';
		
		this.select=0;
		this.previous = 0;
		
		this.num = RandomSpell.SCORE_SCREEN;
		
	}
	
	@Override
	public void move(){
				
		if(((this.keys.contains(new Integer(Player.UP))) || 
				(this.keys.contains(new Integer(Player.LEFT))))
				&& (this.previous == 0)){
			
			this.name[this.select] = (char)(((int)this.name[this.select])-1);
			
			if(this.name[this.select] == (char)(((int)'A')-1)){
				
				this.name[this.select] = 'Z';
				
			}
			
			this.previous = 1;
			
		}else if(((this.keys.contains(new Integer(Player.DOWN))) || 
				(this.keys.contains(new Integer(Player.RIGHT))))
				&& (this.previous == 0)){
			
			this.name[this.select] = (char)(((int)this.name[this.select])+1);
			
			if(this.name[this.select] == (char)(((int)'Z')+1)){
				
				this.name[this.select] = 'A';
				
			}
			
			this.previous = 1;
			
		} if(((this.keys.contains(new Integer(Player.FIRE))) || (this.keys.contains(new Integer(Player.JUMP))))
				&& (this.previous == 0)){
			
			System.out.println("dskljfsdlkjdsf"+this.select);
			
			this.select = this.select + 1;
			
			if(this.select == 3){
				
				this.InsertScore();
				
				RandomSpell.nextscreen = RandomSpell.HOF_SCREEN;
				
			}
			
			System.out.println("dskljfsdlkjdsf"+this.select);

			
			this.previous = 1;
			
		} else {
		
			this.previous = 0;
			
		}
		
	}
	
	private void InsertScore() {

		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		FileOutputStream fo;
		OutputStreamWriter os;
		BufferedWriter bw;
		
		int i,num;
		
		String line;
		String[] scores;
		String[] words;
		
		scores = new String[10];
		
		try {
			fi = new FileInputStream("data/scores");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			i=0;
			
			try {
				
				line = br.readLine();
				
				while(line != null){
					
					words = line.split(" ");
					
					num = new Integer(words[1]);
					
					if(RandomSpell.actualpoints < num){
					
						scores[i] = line;
					
						i=i+1;
						
					}else{
						
						scores[i] = new String(this.name)+" "+RandomSpell.actualpoints;
						
						RandomSpell.actualpoints = 0;
						
						i=i+1;
						
						if(i < 10){
							
							scores[i] = line;
							
							i=i+1;
							
						}
						
					}
					
					if(i == 10){
						
						break;
					}
					
					line = br.readLine();
					
				}
				
				br.close();
				
				fo = new FileOutputStream("data/scores");
				os = new OutputStreamWriter(fo);
				bw = new BufferedWriter(os);
				
				i=0;
				
				while(i < 10){
					
					bw.write(scores[i]+"\n");
					
					i=i+1;
					
				}
				
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}		
		
	}

	@Override
	public void paint(Graphics fieldin){
		
		Graphics field;
		
		field = this.buffer;
				
		this.paint_back(field);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTSUBTITLE);
		field.drawString(InsertScoreScreen.TITLE, 90, 70);
		
		field.setColor(RandomSpell.COLOR4);
		
		field.setFont(RandomSpell.FONTSELECTED);
		field.drawString(InsertScoreScreen.LINE, 90, 110);
		
		field.setFont(RandomSpell.FONTSELECTED);
		field.drawString(InsertScoreScreen.LINE2+RandomSpell.actualpoints, 90, 120);
		
		field.setColor(RandomSpell.COLOR4);
		field.setFont(RandomSpell.FONTTITLE);
		field.drawString(this.name[0]+" "+this.name[1]+" "+this.name[2], 90, 160);
		
		((Graphics2D)fieldin).scale(RandomSpell.scale,RandomSpell.scale);
		
		fieldin.drawImage(this.bi, 0, 0, null);
		
	}
}
