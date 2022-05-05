import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

//This class is the main class and will initialize the java swing GUI and ensures the buttons and interactive features are working as intended
public class NodeGrid extends JFrame implements ActionListener {
	
	//Declaring variables local to the class
	private String algorithmName = "A*";
	private JComboBox algorithmList;
	private JButton sButton;
	private JButton cButton;
	private NPanel grid;
	private AStar aAlg;
	private Dijkstra dAlg;
	private RandomA rAlg;
	private BreadthFirst bFirst;
	private DepthFirst dFirst;
	
	//A parameterized constructor that adds all swing components to the GUI window
    public NodeGrid(String title) {
        super(title);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        String[] algorithmStrings = {"A*", "Dijkstra", "Random", "Breadth-First", "Depth-First"};
        algorithmList = new JComboBox(algorithmStrings);
        algorithmList.setSelectedIndex(0);
        algorithmList.addActionListener(this);
        add(algorithmList);
        
        grid = new NPanel();
        add(grid);
        
        sButton = new JButton("Start");
        add(sButton);
        sButton.addActionListener(this);
        cButton = new JButton("Clear");
        add(cButton);
        cButton.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    //The main function that runs upon running the program and presents a user with the GUI
    public static void main(String[] args0) {

        NodeGrid nodeGrid = new NodeGrid("Pathfinding Algorithm Visualiser");
    }
    
    //This method ensures button presses and changes to the comboBox selection are listened to and handled properly
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//this section handles the start button
		if (e.getSource() == sButton) {
			switch (algorithmName) {
			case "A*":
				aAlg = new AStar(grid);
				aAlg.aStarAlgorithm();
				break;
			case "Dijkstra":
				dAlg = new Dijkstra(grid);
				dAlg.DijkstraAlgorithm();
				break;
			case "Random":
				rAlg = new RandomA(grid);
				rAlg.randomAlgorithm();
				break;
			case "Breadth-First":
				bFirst = new BreadthFirst(grid);
				bFirst.breadthFirstAlgorithm();
				break;
			case "Depth-First":
				dFirst = new DepthFirst(grid);
				dFirst.depthFirstAlgorithm();
				break;
			default: 
				break;
			}
		}
		//this section handles the clear button
		else if (e.getSource() == cButton) {
			grid.reset();
		}
		//this section handles the algorithmList comboBox
		else if (e.getSource() == algorithmList) {
			grid.reset();
			algorithmName = (String) algorithmList.getSelectedItem();
		}
	}
}
