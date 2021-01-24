import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Class that implements a Depth-First search algorithm to find a solution for the Blocksworld tile puzzle
 * 
 * @author peter
 *
 */
public class DepthFirstSearch {

	/**
	 * Performs a Depth-First Search starting from a given state
	 * @param startState - the initial starting State
	 * @param goalState - the State the puzzle must end up in
	 */
	public void search(State startState, State goalState) {
		Node rootNode = new Node(startState);

		Stack<Node> stack = new Stack<>();
		stack.add(rootNode);
		int expandedNodes = 0;
		int nodesInMemory = 1;
		int maxNodesInMemory = 1;
		while(!stack.isEmpty()) {
			//Pop top element of stack to expand node
			Node currentNode = stack.pop();
			expandedNodes++;
			//print details of current node
			/*
			System.out.println("Expanded node #"+expandedNodes);
			System.out.println("Depth: "+currentNode.getDepth()+"\nLast move: "+currentNode.getLastMove());
			System.out.println("State: ");
			currentNode.getState().printGrid();
			*/
			
			//check if current state is a goal state
			if(currentNode.getState().isGoalState(goalState)) {	
				//System.out.println("Goal state found");
				Node curNodePath = currentNode;
				String path = curNodePath.getLastMove();
				// Go back up tree to get path from start to goal
				while((curNodePath = curNodePath.getParent()) != null) {
					path = curNodePath.getLastMove() + ", " + path;
				}
				//print solution details
				/*
				System.out.println("Nodes expanded: "+expandedNodes);
				System.out.println("Maximum nodes in memory: "+maxNodesInMemory);
				System.out.println("Depth: "+currentNode.getDepth());
				System.out.println("Path to solution: " + path);
				*/
				System.out.println("Nodes expanded: "+expandedNodes);
				System.out.println("Most nodes in memory: "+maxNodesInMemory);
				return;
			}
			else {
				//add nodes to fringe in random order
				List<String> childList = new ArrayList<>();
				//add directions to a list
				childList.add("UP");
				childList.add("DOWN");
				childList.add("LEFT");
				childList.add("RIGHT");
				Random childPicker = new Random();
				while(!childList.isEmpty()) {
					//pick random direction from list
					//add to fringe if move is legal
					//remove direction from list
					String direction = childList.remove(childPicker.nextInt(childList.size()));
					if(currentNode.getState().checkLegalMove(direction)) {
						State copiedState = new State(currentNode.getState());
						copiedState.moveAgent(direction);
						stack.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, direction));		
						//System.out.println("Add node with last direction "+direction+" to fringe");
						//increment nodes in memory counter, update max value if necessary
						nodesInMemory++;
						if(nodesInMemory > maxNodesInMemory) {
							maxNodesInMemory = nodesInMemory;
						}
					}
				}
			}
			nodesInMemory--;
		}
	}
}
