/**
 * Represents a Node in the search tree
 * Stores a State of the Blocksworld puzzle, a reference to its parent Node, 
 * its depth in the search tree and the last move taken to reach its State
 * @author peter
 *
 */
public class Node {
	
	protected State state;
	protected Node parent;
	protected int depth;
	protected String lastMove;
	
	/**
	 * Construct a Node by initialising instance variables
	 * @param s - the Node's State
	 * @param p - the Node's parent Node
	 * @param d - the current depth of the node in the tree
	 * @param l - the direction taken from the parent's state to get to this Node's state - up, down, left or right
	 */
	public Node(State s, Node p, int d, String l) {
		state = s;
		parent = p;
		depth = d;
		lastMove = l;
	}
	
	/**
	 * Create root node given start state
	 * @param s - start state
	 */
	public Node(State s) {
		state = s;
		parent = null;
		depth = 0;
		lastMove = "START";
	}
	
	/**
	 * Getter for Node's State
	 * @return State of Blocksworld puzzle
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Getter for parent Node
	 * @return parent Node
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Getter for depth of Node in tree
	 * @return depth of Node in search tree
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Getter for last move taken to reach Node's State
	 * @return - last move taken to reach Node's State
	 */
	public String getLastMove() {
		return lastMove;
	}

}
