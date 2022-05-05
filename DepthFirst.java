import java.util.ArrayList;
import java.util.Stack;

//This class is used to perform operations relating to Depth First algorithm path finding
public class DepthFirst {
	//Declaring variables local to the class
	private NPanel grid;
	private Node startNode;
	private Node currentNode;
	private Stack<Node> nodesStack = new Stack<Node>();
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();
	
	//A parameterized constructor class
	public DepthFirst(NPanel grid) {
		this.grid = grid;
		startNode = grid.getStartNode();
		nodesStack.push(startNode);
	}
	
	//This function calculates the path from the start to the end node using the Depth First algorithm
	public void depthFirstAlgorithm() {
		while (!nodesStack.empty()) {	
			
			currentNode = nodesStack.peek();
			nodesStack.pop();
			visitedNodes.add(currentNode);
			currentNode.searchedSwitch();
			currentNode.draw();
			
			if (currentNode == grid.getEndNode()) {
				grid.pathTrace();
				break;
			}
			
			for (Node neighbour : getNeighbours(currentNode)) {
				if (currentNode.getWalkable() == false || visitedNodes.contains(neighbour)) {
					continue;
			}	
				neighbour.setParentNode(currentNode);
				nodesStack.push(neighbour);
			}
		}
	}
	
	//Makes an arrayList of neighbours to a given node and returns this arrayList
	public ArrayList<Node> getNeighbours(Node node) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		int nRow = node.getRow();
		int nCol = node.getColumn();
		if (startNode.getRow() > grid.getEndNode().getRow()) {
			
			//Gets the node above the given node
			if (nRow > 0) {
				neighbours.add(grid.getNodes()[nRow - 1][nCol]);
			}
		
			//Gets the node to the right of the given node
			if (nCol < (grid.getNodes()[0].length - 1)) {
				neighbours.add(grid.getNodes()[nRow][nCol + 1]);
			}
			
			//Gets the node to the left of the given node
			if (nCol > 0) {
				neighbours.add(grid.getNodes()[nRow][nCol - 1]);
			}
		
		}
		else {
		
			//Gets the node below the given node
			if (nRow < (grid.getNodes().length - 1)) {
				neighbours.add(grid.getNodes()[nRow + 1][nCol]);
			}
			
			//Gets the node to the right of the given node
			if (nCol < (grid.getNodes()[0].length - 1)) {
				neighbours.add(grid.getNodes()[nRow][nCol + 1]);
			}
			
			//Gets the node to the left of the given node
			if (nCol > 0) {
				neighbours.add(grid.getNodes()[nRow][nCol - 1]);
			}
		}
		
		return neighbours;
	}
}
