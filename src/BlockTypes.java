import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BlockTypes {
	
	public static final int BRICK1 = 1;
	public static final int BRICK2 = 2;
	public static final int BRICK3 = 3;
	public static final int BRICK4 = 4;
	public static final int BRICK5 = 5;
	public static final int PLAYER = 6;
	public static final int CARD = 7;
	public static final int ENEMY1 = 8;
	public static final int ENEMY2 = 9;
	public static final int ENEMY3 = 10;
	public static final int TREE = 11;
	
	private static final int SIZE = 9;
	
	public static void get_level_from_file_to_Array(String file,ArrayList<Sprites> list){
		
		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		String line;
		String[] words;
		
		int x;
		int y;
		
		boolean start;
		
		start = false;
				
		GameScreen.cards = 0;
		
		y=0;
		
		try {

			fi=new FileInputStream(file);
			
			is = new InputStreamReader(fi);
			
			br = new BufferedReader(is);

			try {
				
				line = br.readLine();
				
				
				while(line != null){
					
					if(start == false){
						
						words=line.split(" ");
						

						
						if(words[0].equals("data=")){
														
							start=true;
							
						}
						
						
					}else{
						
						words=line.split(",");
						
						int i;
						
						i=0;
						
						x=0;
						
						while(i < words.length){
							
							if(words[i].length() > 0){
								
								if(!words[i].equals("0")){
								
									list.add(BlockTypes.newElement(x,y,new Integer(words[i])));
								
								}
							}
							
							x=x+BlockTypes.SIZE;
							
							i=i+1;
							
						}
						
						y=y+BlockTypes.SIZE;
						
					}
					
					line = br.readLine();
				
				}
									
				br.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return;
	
	}
	
	public static ArrayList<Sprites> get_level_from_file(String file){
		
		ArrayList<Sprites> list;
		
		list = new ArrayList<Sprites>();
		
		BlockTypes.get_level_from_file_to_Array(file, list);
		
		return list;
		
	}

	private static Sprites newElement(int x, int y, Integer input) {
		
		int element;
		
		element = input;
		
		switch(element){
			case BlockTypes.BRICK1:
			{
				return new SimpleBrick(x, y);
			}
			case BlockTypes.BRICK2:
			{
				return new SimpleBrick2(x, y);
			}
			case BlockTypes.BRICK3:
			{
				return new ComplexBrick1Left(x, y);
			}
			case BlockTypes.BRICK4:
			{
				return new ComplexBrick1Center(x, y);
			}
			case BlockTypes.BRICK5:
			{
				return new ComplexBrick1Right(x, y);
			}
			case BlockTypes.PLAYER:
			{
				return new Player(x, y-23);
			}
			case BlockTypes.CARD:
			{
				GameScreen.cards = GameScreen.cards +1;
				return new Card(x, y-11);
			}
			case BlockTypes.ENEMY1:
			{
				return new Enemy1(x, y-22);
			}
			case BlockTypes.ENEMY2:
			{
				return new Enemy2(x, y-22);
			}
			case BlockTypes.ENEMY3:
			{
				return new Enemy3(x, y-23);
			}
			case BlockTypes.TREE:
			{
				return new Tree(x, y);
			}
		}
		
		return null;
	}

}
