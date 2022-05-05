import java.util.ArrayList;

//This class is used to perform operations relating to A* algorithm path finding
public class AStar {
	
	//Declaring variables local to the class
	private NPanel grid;
	private ArrayList<Node> searchNodes;
	private ArrayList<Node> resolvedNodes;
	private Node currentNode;
	
	//A parameterized constructor
	public AStar(NPanel grid) {
		this.grid = grid;
		searchNodes = new ArrayList<Node>();
		resolvedNodes = new ArrayList<Node>();
		searchNodes.add(grid.getStartNode());
	}
	
	//This function calculates the shortest path from the start to the end node using the A* algorithm
	public void aStarAlgorithm() {
		while (searchNodes.size() > 0) {
			currentNode = searchNodes.get(0);
			
			//This tests to see if the distance of a node in the arrayList searchNodes is shorter than the distance of the currentNode
			for (Node node : searchNodes) {
				if ((node.getFCost() < currentNode.getFCost()) || (node.getFCost() == currentNode.getFCost() && node.getHCost() < currentNode.getHCost())) {
					currentNode = node;
				}
			}
			
			searchNodes.remove(currentNode);
			resolvedNodes.add(currentNode);
			
			//If the endNode is found then this prints out the shortest path from the start node to the end node
			if (currentNode == grid.getEndNode()) {
				grid.pathTrace();
				break;
			}
			
			for (Node neighbour : grid.getNeighbours(currentNode)) {
				if (neighbour.getWalkable() == false || resolvedNodes.contains(neighbour)) {
					continue;
				}
				
				int moveCost = currentNode.getGCost() + grid.getDistance(currentNode, neighbour);
				
				if (moveCost < neighbour.getGCost() || !(resolvedNodes.contains(neighbour))) {
					neighbour.setGCost(moveCost);
					neighbour.setHCost(grid.getDistance(neighbour, grid.getEndNode()));
					
					if (!searchNodes.contains(neighbour)) {
						neighbour.setParentNode(currentNode);
						searchNodes.add(neighbour);
						neighbour.searchedSwitch();
						neighbour.draw();
					}
				}
			}
		}
	}
}
