/**
 * Represents a Node in the search tree for an A* search algorithm
 * @author peter
 *
 */
public class AStarNode extends Node implements Comparable{


	private int manhattanDistance;
	
	/**
	 * Constructor that calls the Node superconstructor for a start State and calculates the Manhattan distance
	 * @param s - the start State
	 * @param goalState - the goal State
	 */
	public AStarNode(State s, State goalState) {
		super(s);
		manhattanDistance = calculateManhattanDistance(goalState);
	}
	
	/**
	 * 
	 * @param s
	 * @param p
	 * @param d
	 * @param l
	 * @param goalState
	 */
	public AStarNode(State s, Node p, int d, String l, State goalState) {
		super(s,p,d,l);
		manhattanDistance = calculateManhattanDistance(goalState);
	}
	
	/**
	 * Calculates the sum of Manhattan distances between the locations of blocks on the current node's state and the goal state
	 * Find locations of goal state blocks - how?
	 * get size of grid, find
	 * @param goalState
	 * @return
	 */
	private int calculateManhattanDistance(State goalState) {
		int sum = 0;
		//repeat for every lettered block in the puzzle
		for(int x = 0; x < state.getSize(); x++) {
			for(int y = 0; y < state.getSize(); y++) {
				char tile = state.getGrid()[y][x];
				//if current tile is a letter
				if(tile != '*' && tile != '-') {
					//search goal state for tile
					boolean tileFound = false;
					int i = 0; // x 
					int j = 0;
					while (!tileFound && i < state.getSize()) {
						while(!tileFound && j < state.getSize()) {
							if(goalState.getGrid()[i][j] == tile) {
								int distance = Math.abs(i - y) + Math.abs(j - x);
								sum += distance;
								tileFound = true;
							}
							j++;
						}
						j = 0;
						i++;
					}
				}
			}
		}
		return sum;
	}
	
	/**
	 * Getter for sum of Manhattan distances
	 * @return sum of Manhattan distances
	 */
	public int getManhattanDistance() {
		return manhattanDistance;
	}

	@Override
	/**
	 * Compares estimated costs to goal State with an inputed A* node
	 * The closer Node to the goal is greater than the other Node
	 */
	public int compareTo(Object o) {
		return (this.manhattanDistance + depth) - (((AStarNode) o).manhattanDistance + ((AStarNode) o).depth);
	}

}
