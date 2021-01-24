/**
 * Contains a main method to run searches
 * contains main method
 * start state
 * goal state
 * call search methods
 * comment / uncomment search methods you want to use
 * @author peter
 *
 */
public class Blocksworld {

	public static void main (String[] args) {
		State startState = new State(4);
		State goalState = new State(4);
		goalState.setGrid(makeGoalStateGrid());
		startState.setGrid(makeStartStateGrid());
		startState.setAgentPos(2,0);
		
		for(int i = 1; i<6;i++) {
			System.out.println("Run "+i);
			BreadthFirstSearch bfs = new BreadthFirstSearch();
			bfs.search(startState, goalState);
			//DepthFirstSearch dfs = new DepthFirstSearch();
			//dfs.search(startState,goalState);
			//IterativeDeepeningSearch ids = new IterativeDeepeningSearch();
			//ids.search(startState, goalState);
			//AStarSearch ass = new AStarSearch();
			//ass.search(startState, goalState);
		}
	
	}
	
	public static char[][] makeGoalStateGrid(){
		char[][] grid = {	{'-','-','-','-'},
							{'-','a','-','-'},
							{'-','b','-','-'},
							{'-','c','-','*'}
						};
		return grid;
	}
	
	public static char[][] makeStartStateGrid(){
		char[][] grid = {	{'-','-','-','-'},
							{'-','a','-','-'},
							{'*','b','-','-'},
							{'-','c','-','-'}
						};
		return grid;
	}
}
