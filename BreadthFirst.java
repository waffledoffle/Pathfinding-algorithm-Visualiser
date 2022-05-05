import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//This class is used to perform operations relating to Breadth First algorithm path finding
public class BreadthFirst {
	
	//Declaring variables local to the class
	private NPanel grid;
	private Node startNode;
	private Node currentNode;
	private Queue<Node> nodesQueue = new LinkedList<Node>();
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();
	
	//A parameterized constructor class
	public BreadthFirst(NPanel grid) {
		this.grid = grid;
		startNode = grid.getStartNode();
		nodesQueue.add(startNode);
	}
	
	//This function calculates the path from the start to the end node using the Breadth First algorithm
	public void breadthFirstAlgorithm() {
		while (nodesQueue.peek() != null) {	
			currentNode = nodesQueue.peek();
			nodesQueue.remove();
			visitedNodes.add(currentNode);
			
			if (currentNode == grid.getEndNode()) {
				grid.pathTrace();
				break;
			}
			
			for (Node neighbour : grid.getNeighbours(currentNode)) {
				if (currentNode.getWalkable() == false || visitedNodes.contains(neighbour) || nodesQueue.contains(neighbour)) {
					continue;
			}	
				neighbour.searchedSwitch();
				neighbour.draw();
				neighbour.setParentNode(currentNode);
				nodesQueue.add(neighbour);
			}
		}
	}
}
