import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Implements Iterative Deepening Search to find solution for Blocksworld puzzle
 * @author peter
 *
 */
public class IterativeDeepeningSearch {

	/**
	 * Runs IDS from given state
	 * @param startState - starting state
	 * @param goalState - goal state
	 */
	public void search(State startState, State goalState) {

		int expandedNodes = 0;
		int depthLimit = 0;
		
		while(true) {
			//run a depth-limited search
			/*
			System.out.println("DLS with depth limit "+depthLimit);
			System.out.println("Nodes expanded so far: "+expandedNodes);
			*/
			Node rootNode = new Node(startState);
			Stack<Node> stack = new Stack<>();
			stack.add(rootNode);
			int currentDepth = 0;
			int nodesInMemory = 1;
			int mostNodesInMemory = 1;
			while(!stack.isEmpty()) {
				//pop Node from top of Stack
				Node currentNode = stack.pop();
				expandedNodes++;
				//print node details
				/*
				System.out.println("Expanded node #"+expandedNodes);
				System.out.println("Depth: "+currentNode.getDepth()+"\nLast move: "+currentNode.getLastMove());
				System.out.println("State:");
				//check if Node is goal state
				currentNode.getState().printGrid();
				*/
				if(currentNode.getDepth()!=currentDepth) {
					currentDepth = currentNode.getDepth();
				}

				if(currentNode.getState().isGoalState(goalState)) {	
					//System.out.println("Goal state found");
					Node curNodePath = currentNode;
					String path = curNodePath.getLastMove();

					while((curNodePath = curNodePath.getParent()) != null) {
						path = curNodePath.getLastMove() + ", " + path;
					}
					/*
					System.out.println("Nodes expanded: "+expandedNodes);
					System.out.println("Depth: "+currentNode.getDepth());
					System.out.println("Path to solution: " + path);
					System.out.println("Most nodes in memory: "+mostNodesInMemory);
					*/
					System.out.println("Nodes expanded: "+expandedNodes);
					System.out.println("Most nodes in memory: "+mostNodesInMemory);
					return;
				}
				else {
					//check if past depth limit, expand nodes if so
					if(currentDepth < depthLimit) {
						//add nodes to stack
						//add directions to list	
						List<String> childList = new ArrayList<>();
						childList.add("UP");
						childList.add("DOWN");
						childList.add("LEFT");
						childList.add("RIGHT");
						Random childPicker = new Random();
						while(!childList.isEmpty()) {
							//pick random direction, try to add to stack, remove from list
							String direction = childList.remove(childPicker.nextInt(childList.size()));
							if(currentNode.getState().checkLegalMove(direction)) {
								State copiedState = new State(currentNode.getState());
								copiedState.moveAgent(direction);
								stack.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, direction));				
								nodesInMemory++;
								//System.out.println("Add node with last direction "+direction+" to fringe");
								if(nodesInMemory > mostNodesInMemory) {
									mostNodesInMemory = nodesInMemory;
								}
							}
						}
					}
					nodesInMemory--;
				}
			}
			//increase depth limit
			//System.out.println("No solution found. Increasing depth limit");
			depthLimit ++;
		}
	}
}
