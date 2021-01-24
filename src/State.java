/**
 * Represents a single state in the search space of the Blocksworld puzzle
 * @author peter
 *
 */
public class State {

	private int [] agentPos; // coordinates of the agent - [0] is y coordinate, [1] is x coordinate
	private char[][] grid;
	private int size;
	private static final char AGENT_CHAR = '*';

	/**
	 * Create start state.
	 * Create NxN grid of inputed size, make all rows but the last empty
	 * Fill the bottom row with lettered blocks in alphabetical order and an agent
	 * Initialise agent coordinates
	 * @param size - the size of the grid
	 */
	public State(int size) {
		this.size = size;
		agentPos = new int[2];
		grid = new char[size][size];
		char[] blocks = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				grid[y][x] = '-';
			}
		}

		for (int x = 0; x < size - 1; x++) {
			grid[size - 1][x] = blocks[x];
		}
		grid[size-1][size-1] = AGENT_CHAR;
		agentPos[0] = size -1;
		agentPos[1] = size -1;
	}
	
	/**
	 * Construct a State by copying a passed in State
	 * @param state
	 */
	public State(State state) {
		this.size = state.size;
		agentPos = new int[2];
		this.agentPos[0] = state.agentPos[0];
		this.agentPos[1] = state.agentPos[1];
		this.grid = new char[state.size][state.size];
		for (int x = 0; x < state.size; x++) {
			for (int y = 0; y < state.size; y++) {
				grid[y][x] = state.grid[y][x];
			}
		}
	}

	/**
	 * Setter for grid. Used for altering start and goal state grids
	 * @param grid - the new grid
	 */
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Getter for grid
	 * @return - grid
	 */
	public char[][] getGrid(){
		return grid;
	}
	
	/**
	 * Setter for agent location on grid
	 * @param posY - Y coordinate
	 * @param posX - X coordinate
	 */
	public void setAgentPos(int posY, int posX) {
		agentPos[0] = posY;
		agentPos[1] = posX;
	}
	
	/**
	 * Getter for size
	 * @return size of grid
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Prints the contents of the grid array
	 */
	public void printGrid() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(grid[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Checks if a given move is valid, or if it will take the agent out of bounds
	 * @param direction - the direction the agent is to move in - up, down, left or right
	 * @return
	 */
	public Boolean checkLegalMove(String direction) {
		switch (direction.toUpperCase()) {
		case "UP":
			return agentPos[0] != 0;
		case "DOWN":
			return agentPos[0] != size-1;
		case "LEFT":
			return agentPos[1] != 0;
		case "RIGHT":
			return agentPos[1] != size-1;
		}
		return null;
	}
	
	/**
	 * Moves agent in specified direction
	 * @param direction - the direction the agent is to be moved in
	 */
	public void moveAgent(String direction) {
		char temp;
		switch (direction.toUpperCase()) {
		case "UP":
			temp =  grid[agentPos[0]-1][agentPos[1]];
			grid[agentPos[0]-1][agentPos[1]] = AGENT_CHAR;
			grid[agentPos[0]][agentPos[1]] = temp;
			agentPos[0]--;
			break;
		case "DOWN":
			temp =  grid[agentPos[0]+1][agentPos[1]];
			grid[agentPos[0]+1][agentPos[1]] = AGENT_CHAR;
			grid[agentPos[0]][agentPos[1]] = temp;
			agentPos[0]++;
			break;
		case "LEFT":
			temp =  grid[agentPos[0]][agentPos[1]-1];
			grid[agentPos[0]][agentPos[1]-1] = AGENT_CHAR;
			grid[agentPos[0]][agentPos[1]] = temp;
			agentPos[1]--;
			break;
		case "RIGHT":
			temp =  grid[agentPos[0]][agentPos[1]+1];
			grid[agentPos[0]][agentPos[1]+1] = AGENT_CHAR;
			grid[agentPos[0]][agentPos[1]] = temp;
			agentPos[1]++;
			break;
		}
	}
	
	/**
	 * Check whether state is a goal state
	 * Check if all letters are in same location on goal state grid
	 * @param goalState
	 * @return
	 */
	public boolean isGoalState(State goalState) {
		
		//iterate over grid
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				char tile = grid[y][x];
				//if current tile is a letter
				if(tile != '*' && tile != '-') {
					//check same position on goal state, return false if that position does not contain the same letter
					if(goalState.grid[y][x] != tile) {
						return false;
					}
				}
			}
		}
		return true;
	}
}

