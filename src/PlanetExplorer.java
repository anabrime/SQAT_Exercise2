
// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID:
// Finish time:

public class PlanetExplorer {
	private int planetSizeX;
	private int planetSizeY;
	
	private int positionX = 0;
	private int positionY = 0;
	private Direction direction = Direction.N;
	
	private static enum Direction {
		N,
		E,
		S,
		W;
		
		private static Direction[] vals = values();
		
		public Direction right() {
			int ordinal = (this.ordinal() + 1) % vals.length; 
			return vals[ordinal];
		}
		
		public Direction left() {
			int ordinal = (this.ordinal() - 1) % vals.length;
			if (ordinal < 0) {
				ordinal += vals.length;
			}
			return vals[ordinal];
		}
	}
	
	public PlanetExplorer(int x, int y, String obstacles){
	/*	x and y represent the size of the grid.
	 *  Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
	 *  
		Example use: For a 100x100 grid with two obstacles at coordinates (5,5) and (7,8)
		PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")  
		 
	 */
		this.planetSizeX = x;
		this.planetSizeY = y;
	}
	
	public PlanetExplorer(int x, int y) {
		this(x, y, "");
	}
	
	public String executeCommand(String command){
		
		/* The command string is composed of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Example: 
		 * The explorer is on a 100x100 grid at location (0, 0) and facing NORTH. 
		 * The explorer is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the format: "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)" 
		 * Where pos_x and pos_y are the final coordinates, facing is the current direction the explorer is pointing to (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered obstacles. No white spaces.
		 */
		
		if (command.equals("r")) {
			this.direction = this.direction.right();
		} else if (command.equals("l")) {
			this.direction = this.direction.left();
		} else if (command.equals("f")) {
			this.positionY++;
		}
		
		return "(0," + this.positionY + "," + this.direction + ")";
	}
	
	public String getPlanetSize() {
		return this.planetSizeX + "x" + this.planetSizeY;
	}
}
