import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//This class is used to store variables and return when needed while also performing operations such as changing background color of each node
class Node extends JPanel implements Comparable<Node> { 

	//Declaring variables local to the class
	private int column, row, size;
	private boolean walkable = true;
	private boolean start, search, searched, path = false;
	private int gCost, hCost = 0;
	private Node parentNode = null;
	
	//A parameterized constructor
	public Node(int x, int y, int size) {
		row = x;
		column = y;
		this.size = size;
	}
	
	//Switches the boolean value of variable walkable to the opposite of what it currently is
	public void walkableSwitch() {
		walkable = !walkable;
	}
	
	//Switches the boolean value of variable path to the opposite of what it currently is
	public void pathSwitch() {
		path = !path;
	}
	
	//Switches the boolean value of variable start to the opposite of what it currently is
	public void startSwitch() {
		start = !start;
	}
	
	//Switches the boolean value of variable search to the opposite of what it currently is
	public void searchSwitch() {
		search = !search;
	}
	
	//Switches the boolean value of variable searched to the opposite of what it currently is
	public void searchedSwitch() {
		searched = !searched;
	}
	
	//Sets the background color of a node and adds borders to give the node a more defined square look
	public void draw() {
		if (start == true) {
			setBackground(Color.GREEN);
		}
		else if (walkable == false) {
			setBackground(Color.BLACK);
		}
		else if (path == true) {
			setBackground(Color.YELLOW);
		}
		else if (searched == true) {
			setBackground(Color.RED);
		}
		else if (search == true) {
			setBackground(Color.BLUE);
		}
		else {
			setBackground(Color.WHITE);
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
	
	//Returns the size variable of a node
	public int getNSize(){
		return size;
	}
	
	//Returns the column variable of a node
	public int getColumn() {
		return column;
	}
	
	//Returns the row variable of a node
	public int getRow() {
		return row;
	}
	
	//Returns the walkable variable of a node
	public boolean getWalkable() {
		return walkable;
	}
	
	//Returns the start variable of a node
	public boolean getStart() {
		return start;
	}
	
	//Returns the search variable of a node
	public boolean getSearch() {
		return search;
	}
	
	//Returns the searched variable of a node
	public boolean getSearched() {
		return searched;
	}
	
	//Returns the path variable of a node
	public boolean getPath() {
		return path;
	}
	
	//Returns the gCost variable of a node
	public int getGCost() {
		return gCost;
	}
	
	//Returns the hCost variable of a node
	public int getHCost() {
		return hCost;
	}
	
	//Returns the parentNode variable of a node
	public Node getParentNode() {
		return parentNode;
	}

	//Sets the hCost variable of a node using a given integer
	public void setGCost(int gCost) {
		this.gCost = gCost;
	}
	
	//Sets the hCost variable of a node using a given integer
	public void setHCost(int hCost) {
		this.hCost = hCost;
	}
	
	//Sets the parentNode variable of a node using a given Node object
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	//Returns the sum of the gCost and hCost variables of a node
	public int getFCost() {
		return gCost + hCost;
	}
	
	//A function to compare a nodes gCost against a given Node objects gCost
	@Override
	public int compareTo(Node node) {
		int dist = node.getGCost();
		return this.getGCost() - dist;
	}
	    
}
