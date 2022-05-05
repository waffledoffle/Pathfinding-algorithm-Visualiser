import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.Timer;

//This class initializes and manages the actual grid of the GUI
public class NPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	//Declaring variables local to the class
	private Node startNode, endNode;
	private int count = 0;
	private Node[][] nodes = new Node[50][50];
	private ArrayList<Node> switchedN = new ArrayList<Node>();
	private static final int ROWS = 50, COLS = 50,  PIXLE_SIZE = 10, GAP = 0;
	
	//A parameterized constructor
	public NPanel() {
		super();
		setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
		
		//Adds Node objects to the panel to create the grid
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                Node newNodeObject = new Node(i, j, PIXLE_SIZE);
                add(newNodeObject);
                nodes[i][j] = newNodeObject;
                newNodeObject.draw();
            }
        }
        
        setSize(new Dimension(ROWS*(PIXLE_SIZE), COLS*(PIXLE_SIZE)));
        addMouseListener(this);
        addMouseMotionListener(this);
	}
	
	//A method that keeps track of how many right clicks a user makes to ensure there is only one start node and one end node represented on the grid
	public void addCount(Node node) {
		if (count == 0) {
			count = 1;
			startNode = node;
		}
		else if (count == 1) {
			count = 2;
			endNode = node;
		}
		else if (count == 2) {
			count = 1;
			startNode.startSwitch();
			startNode.draw();
			endNode.startSwitch();
			endNode.draw();
			startNode = node;
			endNode = null;
		}
	}
	
	//Returns the ROWS variable of the grid
	public int getRows() {
		return ROWS;
	}
	
	//Returns the COLS variable of the grid
	public int getCols() {
		return COLS;
	}
	
	//Returns the startNode variable of the grid
	public Node getStartNode() {
		return startNode;
	}
	
	//Returns the endNode variable of the grid
	public Node getEndNode() {
		return endNode;
	}
	 
	//Returns the 2 dimensional array of nodes
	public Node[][] getNodes() {
		return nodes;
	}
	
	//Makes an arrayList of neighbours to a given node and returns this arrayList
	public ArrayList<Node> getNeighbours(Node node) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		int nRow = node.getRow();
		int nCol = node.getColumn();
		
		//Gets the node to the left of the given node
		if (nRow > 0) {
			neighbours.add(nodes[nRow - 1][nCol]);
			nodes[nRow - 1][nCol].searchSwitch();
			nodes[nRow - 1][nCol].draw();
		}
		
		//Gets the node to the right of the given node
		if (nRow < (nodes.length - 1)) {
			neighbours.add(nodes[nRow + 1][nCol]);
			nodes[nRow + 1][nCol].searchSwitch();
			nodes[nRow + 1][nCol].draw();
		}
		
		//Gets the node above the given node
		if (nCol < (nodes[0].length - 1)) {
			neighbours.add(nodes[nRow][nCol + 1]);
			nodes[nRow][nCol + 1].searchSwitch();
			nodes[nRow][nCol + 1].draw();
		}
		
		//Gets the node below the given node
		if (nCol > 0) {
			neighbours.add(nodes[nRow][nCol - 1]);
			nodes[nRow][nCol - 1].searchSwitch();
			nodes[nRow][nCol - 1].draw();
		}
		
		return neighbours;
	}
	
	//This method visualizes the shortest path on the grid
	public void pathTrace() {
		ArrayList<Node> path = new ArrayList<Node>();
		Node currentNode = endNode;
		
		while (currentNode != startNode) {
			path.add(currentNode);
			currentNode = currentNode.getParentNode();
		}
		
		Collections.reverse(path);
		
		for (Node node : path) {
			node.pathSwitch();
			node.draw();
		}
	}
	
	//Removes existing Node objects from the panel and then adds them to reset the grid
	public void reset() {
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLS; j++){
				remove(nodes[i][j]);
				Node newNodeObject = new Node(i, j, PIXLE_SIZE);
	            add(newNodeObject);
	            nodes[i][j] = newNodeObject;
	            newNodeObject.draw();
	        }
	    }
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
   	public void mouseEntered(MouseEvent e) {
   	}
	
	@Override
  	public void mouseExited(MouseEvent e) {
   	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	//This method deals with ensuring the right nodes change color and are registered upon a users mouse click
	@Override
	public void mousePressed(MouseEvent e) {
		int x = (e.getX() / 12);
		int y = (e.getY() / 12);
		
		//Deals with what happens when a user left clicks
		if (e.getButton() == MouseEvent.BUTTON1) {
			nodes[y][x].walkableSwitch();
		}
		//Deals with what happens when a user right clicks
		else if (e.getButton() == MouseEvent.BUTTON3) {
			addCount(nodes[y][x]);
			nodes[y][x].startSwitch();
		}
		nodes[y][x].draw();
	}
	
	//This method ensures the right nodes change color and are registered upon a users mouse drag
	@Override
	public void mouseDragged(MouseEvent e) {
		int b1 = e.BUTTON1_DOWN_MASK;
		int b2 = e.BUTTON2_DOWN_MASK;
		
		if ((e.getModifiersEx() & (b1 | b2)) == b1) {
			int x = (e.getX() / 12);
			int y = (e.getY() / 12);
			if (!(switchedN.contains(nodes[y][x])) || !(nodes[y][x] == startNode) || !(nodes[y][x] == endNode)) {
				nodes[y][x].walkableSwitch();
				nodes[y][x].draw();
				switchedN.add(nodes[y][x]);
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	//This method gets the distance between two given Node objects and returns it
	public int getDistance(Node a, Node b) {
		int distX = Math.abs(a.getRow() - b.getRow());
		int distY = Math.abs(a.getColumn() - b.getColumn());
			
		if (distX > distY) {
			return (14 * distY + 10 * (distX - distY));
		}
		else {
			return (14 * distX + 10 * (distY - distX));
			}
	}
};