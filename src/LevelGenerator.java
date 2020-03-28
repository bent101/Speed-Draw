import java.util.ArrayList;

public class LevelGenerator {
	public final static int NUM_LEVELS = 7;

	/*
	KEY						  NOTES
	 * # Block
	 * A BigSpike             Damage you faster; safe to run through up to 2 in a row
	 * ^ SmallSpike           Damage you slower; safe to run through up to 4 in a row
	 * _ Trampoline			  Bounces you up 4 blocks
	 * s Spring				  Bounces you up 3 blocks
	 * 0 Portal				  End of the level
	 * o Player start pos	  Beginning of the level
	 */
	private static String[][] levelData = new String[][] {
		{
			"                ",
			"                ",
			"                ",
			"                ",
			" 0   ^AAA_      ",
			" ############## ",
			"                ",
			" #              ",
			" #o  A    ^^   s",
			" ################"
		},
		{
			"################",
			"   0     #     #",
			"   ##    # #   #",
			"^   #### # #   #",
			"#   #    # #s  #",
			"   ^# #### ##  #",
			"   ##      #   #",
			"^   ########   #",
			"#A  o         _#",
			"################"
		},
		{
			"      0         ",
			"  ^   #         ",
			"  #        ^    ",
			"    #      #    ",
			"      #         ",
			"                ",
			"                ",
			"      ##       _",
			" #o       A   ##",
			"  #       #     "
		},
		{
			"  ############# ",
			"              # ",
			"##            # ",
			" #o A  A  A   # ",
			" ############ # ",
			"    ^  ^  ^   # ",
			" ############## ",
			"                ",
			"#   #   #   #  0",
			"  #   #   #   ##"
		}, 
		{
			"      AAAAAAAA 0",
			"  ##############",
			"                ",
			"##              ",
			" #_A   ^^   A   ",
			" ############## ",
			"_#              ",
			"##              ",
			" o   ^^   ^    _",
			"################"
		},
		{
			"                ",
			"    AA       A^A",
			"   ###     #####",
			"  ##0#          ",
			"   # ##### #####",
			"s o#       #    ",
			"#   ## #   #    ",
			"         #      ",
			" A^__^^^^A^^^^A^",
			"################"
		},
		{
			"0#              ",
			" #      ^       ",
			"        #       ",
			"##            # ",
			" #_             ",
			" ##          #  ",
			"_#        A     ",
			"##        #     ",
			"o            s  ",
			"# # #  #  #  #  "
		}
	};
	
	/**
	 * Generates the levels using the level data
	 * @return The levels
	 */
	public static ArrayList<Level> generateLevels() {
		ArrayList<Level> levels = new ArrayList<>();
		
		for(int i = 0; i < levelData.length; i++) {
        	String[] levelMap = levelData[i];
        	for(int row = 0; row < levelMap.length; row++) {
        		for(int col = 0; col < levelMap[row].length(); col++) {
        			levels.add(new Level());
        			Level level = levels.get(i);
        			Entity e = null;
        			int x = 50 * col, y = 50 * row;
        			switch(levelMap[row].charAt(col)) {
        			case ' ':
        				break;
        			case '#': e = new Block(x,y);
        				break;
        			case 'A': e = new BigSpike(x,y);
        				break;
        			case '^': e = new SmallSpike(x,y);
        				break;
        			case '_': e = new Trampoline(x,y);
        				break;
        			case 's': e = new Spring(x,y);
        				break;
        			case '0': e = new Portal(x,y);
        				break;
        			case 'o': level.setStartPos(x,y);
        				break;
        			}
        			if(e != null) level.addEntity(e);
        		}
        	}
        }
		
		return levels;
	}
}
