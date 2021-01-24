import java.util.PriorityQueue;
/**
 * Implements A* search to find a solution to the Blocksworld puzzle
 * Uses the sum of Manhattan distances as a heuristic
 * @author peter
 *
 */
public class AStarSearch{
	
	/**
	 * Performs A* search from given state
	 * @param startState - starting state
	 * @param goalState - goal state
	 */
	public void search(State startState, State goalState) {
		AStarNode rootNode = new AStarNode(startState, goalState);
		PriorityQueue<AStarNode> queue = new PriorityQueue<AStarNode>();
		int expandedNodes = 0;
		queue.add(rootNode);
		int nodesInMemory = 1;
		int mostNodesInMemory = 1;
		while(!queue.isEmpty()) {
			//remove first element of queue
			AStarNode currentNode = queue.poll();
			expandedNodes++;
			//print node info
			/*
			System.out.println("Expanded node #"+expandedNodes);
			System.out.println("Depth: "+currentNode.getDepth()+"\nDistance from goal state: "+currentNode.getManhattanDistance()+"\nLast move: "+currentNode.getLastMove());
			System.out.println("State: ");
			currentNode.getState().printGrid();
			*/
			
			if(currentNode.getState().isGoalState(goalState)) {	
				//print solution details
				//System.out.println("Goal state found");
				Node curNodePath = currentNode;
				String path = curNodePath.getLastMove();
				
				while((curNodePath = curNodePath.getParent()) != null) {
					path = curNodePath.getLastMove() + ", " + path;
				}
				/*
				System.out.println("Nodes expanded: "+expandedNodes);
				System.out.println("Most nodes in memory: "+mostNodesInMemory);
				System.out.println("Depth: "+currentNode.getDepth());
				System.out.println("Path to solution: " + path);
				*/
				System.out.println("Nodes expanded: "+expandedNodes);
				System.out.println("Most nodes in memory: "+mostNodesInMemory);
				return;
			}
			else {
				//add nodes to queue
				if(currentNode.getState().checkLegalMove("UP")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("UP");
					queue.add(new AStarNode(copiedState, currentNode, currentNode.getDepth()+1, "UP", goalState));
					//System.out.println("Add node with last move UP to fringe");
					nodesInMemory++;
					if(nodesInMemory > mostNodesInMemory) {
						mostNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("DOWN")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("DOWN");
					queue.add(new AStarNode(copiedState, currentNode, currentNode.getDepth()+1, "DOWN", goalState));
					//System.out.println("Add node with last move DOWN to fringe");
					nodesInMemory++;
					if(nodesInMemory > mostNodesInMemory) {
						mostNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("LEFT")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("LEFT");
					queue.add(new AStarNode(copiedState, currentNode, currentNode.getDepth()+1, "LEFT", goalState));
					//System.out.println("Add node with last move LEFT to fringe");
					nodesInMemory++;
					if(nodesInMemory > mostNodesInMemory) {
						mostNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("RIGHT")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("RIGHT");
					queue.add(new AStarNode(copiedState, currentNode, currentNode.getDepth()+1, "RIGHT", goalState));
					//System.out.println("Add node with last move RIGHT to fringe");
					nodesInMemory++;
					if(nodesInMemory > mostNodesInMemory) {
						mostNodesInMemory = nodesInMemory;
					}
				}
				//System.out.println();
				nodesInMemory--;
			}
		}
	}

}
