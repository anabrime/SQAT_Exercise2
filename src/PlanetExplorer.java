import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID: 110
// Finish time: 16:02 (started at 14:07)

public class PlanetExplorer {
	private int planetSizeX;
	private int planetSizeY;
	
	private int positionX = 0;
	private int positionY = 0;
	private Direction direction = Direction.N;
	
	private Set<Pair<Integer, Integer>> obstacles = new HashSet<Pair<Integer, Integer>>();
	private String encounteredObstacles = "";
	
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
	
	public PlanetExplorer(int x, int y, String obstacles) throws PlanetExplorerException {
		/* x and y represent the size of the grid.
		 * Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
		 
		 * Example use: For a 100x100 grid with two obstacles at coordinates (5,5) and (7,8)
		 * PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")
		 */
		this(x, y);
		
		// No obstacles
		if (obstacles.equals("")) {
			return;
		}
		
		obstacles = obstacles.replaceFirst("\\(", "");
		obstacles = obstacles.replaceFirst("\\)$", "");
		String[] obstacleCoordinates = obstacles.split("\\)\\(");
		
		for (int i = 0; i < obstacleCoordinates.length; i++) {
			String[] coords = obstacleCoordinates[i].split(",");
			int obstacleX = Integer.parseInt(coords[0]);
			int obstacleY = Integer.parseInt(coords[1]);
			
			if (obstacleX + 1 > x || obstacleY + 1 > y) {
				throw new PlanetExplorerException();
			}
			
			this.obstacles.add(new Pair<Integer, Integer>(obstacleX, obstacleY));
		}
	}
	
	public PlanetExplorer(int x, int y) {
		this.planetSizeX = x;
		this.planetSizeY = y;
	}
	
	public String executeCommand(String command) {
		/* The command string is composed of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Example: 
		 * The explorer is on a 100x100 grid at location (0, 0) and facing NORTH. 
		 * The explorer is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the format: "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)" 
		 * Where pos_x and pos_y are the final coordinates, facing is the current direction the explorer is pointing to (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered obstacles. No white spaces.
		 */
		
		if (command.length() > 1) {
			this.executeCommand(command.substring(0, 1));
			return this.executeCommand(command.substring(1, command.length()));
		}
		
		if (command.equals("r")) {
			this.direction = this.direction.right();
		}
		
		if (command.equals("l")) {
			this.direction = this.direction.left();
		}
		
		if (command.equals("f")) {
			if (this.direction == Direction.N) {
				this.positionY++;
			} else if (this.direction == Direction.E) {
				this.positionX++;
			} else if (this.direction == Direction.S) {
				this.positionY--;
			} else {
				this.positionX--;
			}
		}
		
		if (command.equals("b")) {
			if (this.direction == Direction.N) {
				this.positionY--;
			} else if (this.direction == Direction.E) {
				this.positionX--;
			} else if (this.direction == Direction.S) {
				this.positionY++;
			} else {
				this.positionX++;
			}
		}
		
		// Wrapping
		if (this.positionX < 0) {
			this.positionX += this.planetSizeX;
		} else if (this.positionY < 0) {
			this.positionY += this.planetSizeY;
		} else if (this.positionX >= this.planetSizeX) {
			this.positionX -= this.planetSizeX;
		} else if (this.positionY >= this.planetSizeY) {
			this.positionY -= this.planetSizeY;
		}
		
		if (this.checkForObstacle()) {
			this.encounterObstacle();
			if (command.equals("f")) {
				return this.executeCommand("b");
			} else if (command.equals("b")) {
				return this.executeCommand("f");
			}
		}
		
		return "("
				+ this.positionX
				+ ","
				+ this.positionY
				+ ","
				+ this.direction
				+ ")"
				+ this.encounteredObstacles;
	}
	
	public String getPlanetSize() {
		return this.planetSizeX + "x" + this.planetSizeY;
	}
	
	private boolean checkForObstacle() {
		return this.obstacles.contains(new Pair<Integer, Integer>(this.positionX, this.positionY));
	}
	
	private void encounterObstacle() {
		String obstacle = "(" + this.positionX + "," + this.positionY + ")";
		if (!this.encounteredObstacles.contains(obstacle)) {
			this.encounteredObstacles += obstacle;
		}
	}
}
