import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Main {
 
	private static JFrame mainFrame;
    private static JFrame frame;
    private static JTextField startCityTextField;
    private static JTextField destinationCityTextField;
    private static JTextField resultTextArea;
    private static JTextField resultTextArea2;
        

	 public static void main(String[] args) {
		 

		 SwingUtilities.invokeLater(() -> {
			 createAndShowMainMenu();
	        });
		 
	    }


private static void createAndShowMainMenu() {

    mainFrame = new JFrame("Shortest Path Finder");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Main Panel
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JButton DFS_Distance = new JButton("Distance Find by DFS ");
    

    DFS_Distance.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	mainFrame.dispose();
        	DFS_FINDING();
        	
        }
    });
    
    JButton BFS_Distance = new JButton("Distance Find by BFS ");

    BFS_Distance.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	mainFrame.dispose();
        	BFS_FINDING();	
        }
    });
       
    JButton exitButton = new JButton("Exit");

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {System.exit(0);}});

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10); // Padding
    panel.add(DFS_Distance, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.insets = new Insets(10, 10, 10, 10); // Padding
    panel.add(BFS_Distance, gbc);

    gbc.gridy = 2;
    panel.add(exitButton, gbc);

    mainFrame.getContentPane().add(BorderLayout.CENTER, panel);
    mainFrame.setSize(300, 200);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
}

private static void DFS_FINDING() {
    frame = new JFrame("City Path Finder with DFS");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JMenuBar menuBar = new JMenuBar();
    			
    frame.setJMenuBar(menuBar);

    // Main Panel
    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    JLabel startLabel = new JLabel("Enter the starting city:");
    startCityTextField = new JTextField(20);  // Set the preferred width

    JLabel destinationLabel = new JLabel("Enter the destination city:");
    destinationCityTextField = new JTextField(20);  // Set the preferred width

    
    JButton findPathButton = new JButton("Find Shortest Path");


    JTextArea textArea = new JTextArea(1, 30); // 10 satır, 30 sütun
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    
    textArea.append("");
    
    JTextArea textArea2 = new JTextArea(30, 30); // 10 satır, 30 sütun
    textArea2.setLineWrap(true);
    textArea2.setEditable(false);
    
    textArea2.append("");
    
    resultTextArea2 = new JTextField(20);  // Set the preferred width
    resultTextArea2.setEditable(false);

    //String result = null;
    findPathButton.addActionListener(new ActionListener() {
        @Override

        public void actionPerformed(ActionEvent e) {

        	onFindPathDFSButtonClick(textArea,textArea2);

        }
    });
    // Output Label
    JLabel outputLabel = new JLabel("RESULT");



    JButton exitButton = new JButton("BACK");

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	createAndShowMainMenu();
        	frame.dispose();

       
        }
    });

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(startLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    panel.add(startCityTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(destinationLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    panel.add(destinationCityTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    panel.add(findPathButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 2;
    
    panel.add(outputLabel, constraints);
    
    JScrollPane resultScrollPane = new JScrollPane(textArea);
    resultScrollPane.setPreferredSize(new Dimension(300, 50));
    constraints.gridx = 0;
    constraints.gridy = 7;
    constraints.gridwidth = 5;
    constraints.gridheight = 1;
    panel.add(resultScrollPane, constraints);

    JScrollPane resultScrollPane2 = new JScrollPane(textArea2);
    resultScrollPane2.setPreferredSize(new Dimension(300, 200));
    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.gridwidth = 5;
    constraints.gridheight = 1;
    panel.add(resultScrollPane2, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 10;
    constraints.gridwidth = 2;
    panel.add(exitButton, constraints);

    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

private static void onFindPathDFSButtonClick(JTextArea textArea,JTextArea textArea2) {
    
	String filePath = "C:\\Users\\ASUS\\Downloads\\Turkish cities (1).csv";
	String startCity = startCityTextField.getText().trim();
    String destinationCity = destinationCityTextField.getText().trim();
    

    Graph cityGraph = new Graph(filePath);

    
    int depthLimit = 4;
    
    DFS.dfs(cityGraph, startCity, destinationCity, depthLimit, textArea, textArea2);
    
    
    return;
    }

private static void BFS_FINDING() {
	 frame = new JFrame("City Path Finder with BFS");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	    JMenuBar menuBar = new JMenuBar();
	    			
	    frame.setJMenuBar(menuBar);

	    // Main Panel
	    JPanel panel = new JPanel(new GridBagLayout());

	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.insets = new Insets(5, 5, 5, 5);

	    JLabel startLabel = new JLabel("Enter the starting city:");
	    startCityTextField = new JTextField(20);  // Set the preferred width

	    JLabel destinationLabel = new JLabel("Enter the destination city:");
	    destinationCityTextField = new JTextField(20);  // Set the preferred width

	    
	    JButton findPathButton = new JButton("Find Shortest Path");

	    //JLabel alo = new JLabel("OUTPUT");
	    resultTextArea = new JTextField(20);  // Set the preferred width
	    resultTextArea.setEditable(false);
	    
	    JTextArea textArea = new JTextArea(1, 30); // 10 satır, 30 sütun
        textArea.setLineWrap(true);
        textArea.setEditable(false);
	    
	    textArea.append("");
	    
	    JTextArea textArea2 = new JTextArea(30, 30); // 10 satır, 30 sütun
        textArea2.setLineWrap(true);
        textArea2.setEditable(false);
	    
	    textArea2.append("");
	    
	    resultTextArea2 = new JTextField(20);  // Set the preferred width
	    resultTextArea2.setEditable(false);

	    //String result = null;
	    findPathButton.addActionListener(new ActionListener() {
	        @Override

	        public void actionPerformed(ActionEvent e) {

	        	onFindPathBFSButtonClick(textArea,textArea2);

	        }
	    });

	    // Output Label
	    JLabel outputLabel = new JLabel("RESULT");

	    JButton exitButton = new JButton("BACK");

	    exitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	createAndShowMainMenu();
	        	frame.dispose();
	        	
	        }
	    });

	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    panel.add(startLabel, constraints);

	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    panel.add(startCityTextField, constraints);

	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    panel.add(destinationLabel, constraints);

	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    panel.add(destinationCityTextField, constraints);

	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.gridwidth = 2;
	    panel.add(findPathButton, constraints);

	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.gridwidth = 2;
	    
	    panel.add(outputLabel, constraints);
	    
	    JScrollPane resultScrollPane = new JScrollPane(textArea);
	    resultScrollPane.setPreferredSize(new Dimension(300, 50));
	    constraints.gridx = 0;
	    constraints.gridy = 4;
	    constraints.gridwidth = 5;
	    constraints.gridheight = 1;
	    panel.add(resultScrollPane, constraints);
	    
	    JScrollPane resultScrollPane2 = new JScrollPane(textArea2);
	    resultScrollPane2.setPreferredSize(new Dimension(300, 200));
	    constraints.gridx = 0;
	    constraints.gridy = 8;
	    constraints.gridwidth = 5;
	    constraints.gridheight = 1;
	    panel.add(resultScrollPane2, constraints);

	    constraints.gridx = 0;
	    constraints.gridy = 10;
	    constraints.gridwidth = 2;
	    panel.add(exitButton, constraints);

	    frame.getContentPane().add(BorderLayout.CENTER, panel);
	    frame.setSize(800, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
}

private static void onFindPathBFSButtonClick(JTextArea textArea, JTextArea textArea2) {
    
	String filePath = "C:\\Users\\ASUS\\Downloads\\Turkish cities (1).csv";
	String startCity = startCityTextField.getText().trim();
    String destinationCity = destinationCityTextField.getText().trim();
    

    System.out.println("Starting to read user.csv file");
    Graph cityGraph = new Graph(filePath);
    
    BFS.bfs(cityGraph, startCity, destinationCity, textArea, textArea2);
    

    return;    

}

}
