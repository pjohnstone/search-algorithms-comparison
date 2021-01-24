import java.util.LinkedList;
/**
 * Implements Breath-First Search to find a solution to the Blocksworld puzzle
 * @author peter
 *
 */
public class BreadthFirstSearch {

	/**
	 * Runs BFS starting from given state
	 * @param startState - starting state
	 * @param goalState - goal state
	 */
	public void search(State startState, State goalState) {
		Node rootNode = new Node(startState);
		LinkedList<Node> queue = new LinkedList<Node>();
		int expandedNodes = 0;
		queue.add(rootNode);
		int nodesInMemory = 1;
		int maxNodesInMemory = 1;
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			expandedNodes++;
			//print node details
			/*
			System.out.println("Expanded node #"+expandedNodes);
			System.out.println("Depth: "+currentNode.getDepth()+"\nLast move: "+currentNode.getLastMove());
			System.out.println("State: ");
			currentNode.getState().printGrid();
			*/
			
			//check if solution is goal state
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
				System.out.println("Most nodes in memory: "+maxNodesInMemory);
				System.out.println("Depth: "+currentNode.getDepth());
				System.out.println("Path to solution: " + path);
				*/
				System.out.println("Nodes expanded: "+expandedNodes);
				System.out.println("Most nodes in memory: "+maxNodesInMemory);
				return;
			}
			else {
				//add nodes to queue
				if(currentNode.getState().checkLegalMove("UP")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("UP");
					queue.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, "UP"));
					//System.out.println("Add node with last move UP to fringe");
					nodesInMemory++;
					if(nodesInMemory > maxNodesInMemory) {
						maxNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("DOWN")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("DOWN");
					queue.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, "DOWN"));
					//System.out.println("Add node with last move DOWN to fringe");
					nodesInMemory++;
					if(nodesInMemory > maxNodesInMemory) {
						maxNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("LEFT")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("LEFT");
					queue.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, "LEFT"));
					//System.out.println("Add node with last move LEFT to fringe");
					nodesInMemory++;
					if(nodesInMemory > maxNodesInMemory) {
						maxNodesInMemory = nodesInMemory;
					}
				}
				if(currentNode.getState().checkLegalMove("RIGHT")) {
					State copiedState = new State(currentNode.getState());
					copiedState.moveAgent("RIGHT");
					queue.add(new Node(copiedState, currentNode, currentNode.getDepth()+1, "RIGHT"));
					//System.out.println("Add node with last move RIGHT to fringe");
					nodesInMemory++;
					if(nodesInMemory > maxNodesInMemory) {
						maxNodesInMemory = nodesInMemory;
					}
				}
			}
			nodesInMemory--;
		}
	}	
}
