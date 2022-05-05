import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//This class is used to perform operations relating to Dijkstra algorithm path finding
public class Dijkstra {
		
	//Declaring variables local to the class
	private NPanel grid;
	private Node startNode;
	private ArrayList<Node> nodesArray = new ArrayList<Node>();
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();
	private Node currentNode;
	//A Comparator that allows two Node objects to be compared
	private Comparator<Node> nodeComp = new Comparator<Node>() {
		public int compare(Node n1, Node n2) {
			int dist1 = n1.getGCost();
			int dist2 = n2.getGCost();
				
			return (dist1 - dist2);
		}
	};
	
	//A parameterized constructor
	public Dijkstra(NPanel grid) {
		this.grid = grid;
		startNode = grid.getStartNode();
		startNode.setGCost(0);
		nodesArray.add(startNode);
	}
	
	//This function calculates the shortest path from the start to the end node using the Dijkstra algorithm
	public void DijkstraAlgorithm() {
		while (nodesArray.size() > 0) {
			Collections.sort(nodesArray, nodeComp);
			currentNode = nodesArray.get(0);
			nodesArray.remove(currentNode);
			visitedNodes.add(currentNode);
			
			if (currentNode == grid.getEndNode()) {
				grid.pathTrace();
				break;
			}
			
			for (Node neighbour : grid.getNeighbours(currentNode)) {
				if (currentNode.getWalkable() == false || visitedNodes.contains(neighbour) || nodesArray.contains(neighbour)) {
					continue;
				}	
				neighbour.searchedSwitch();
				neighbour.draw();
				int distance = currentNode.getGCost() + 1;
				neighbour.setGCost(distance);
				neighbour.setParentNode(currentNode);
				nodesArray.add(neighbour);
			}
		}
	}
}