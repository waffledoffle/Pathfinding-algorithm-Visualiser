import java.util.ArrayList;
import java.util.Random;

//This class is used to perform operations relating to Random algorithm path finding
public class RandomA {
	
	//Declaring variables local to the class
	private NPanel grid;
	private Node startNode;
	private Node currentNode;
	private ArrayList<Node> nodesArray = new ArrayList<Node>();
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();
	
	//A parameterized constructor class
	public RandomA(NPanel grid) {
		this.grid = grid;
		startNode = grid.getStartNode();
		nodesArray.add(startNode);
	}
	
	//This function calculates the path from the start to the end node using the Random algorithm
	public void randomAlgorithm() {
		
		Random rand = new Random();
		while (true) {
			
			int index = rand.nextInt(nodesArray.size());
			currentNode = nodesArray.get(index);
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
				neighbour.setParentNode(currentNode);
				nodesArray.add(neighbour);
			}
		}	
	}
}
