package Graphical_User_Interface;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Graph.clsMatrixGraph;
import Graph.clsNode;
import Other.RandomNumberGenerator;

public class clsUserInterface extends JFrame implements ActionListener,MouseMotionListener{
	
	ActionListener Listener;
	JButton AddNodeButton;
	JPanel Panel;
	JLabel GraphLabel;
	
	JMenuItem AddNode = new JMenuItem("Add Node");
	JMenuItem AddEdge = new JMenuItem("Add Edge");
	JMenuItem RemoveEdge = new JMenuItem("Remove Edge");
	JMenuItem ExportGraph = new JMenuItem("Export Graph");
	JMenuItem ImportGraph = new JMenuItem("Import Graph");

	JMenuItem ClearScreen = new JMenuItem("Clear Screen");

	JMenuItem IsEulerian = new JMenuItem("Eulerian Graph");
	JMenuItem IsSemiEulerian = new JMenuItem("Semi Eulerian Graph");
	JMenuItem IsConnected = new JMenuItem("Connected Graph");
	JMenuItem EulerianPath = new JMenuItem("Find Eulerian Path/Circuit Hierholzer");
	JMenuItem EulerianPathFleury = new JMenuItem("Find Eulerian Path/Circuit Fleury");
	JMenuItem Djikstra = new JMenuItem("Djikstra Algorithm");

	JMenuItem IsHamiltonian = new JMenuItem("Is The Graph Hamiltonian");


	clsMatrixGraph MatrixGraph = new clsMatrixGraph(20); 
	
	Canvas MyCanvas = new Canvas();
    JPanel PanelOfCanvas = new JPanel();

    ArrayList<clsDrawOval> arrDrawenNodes = new ArrayList<>();
    ArrayList<clsDrawEdge> arrDrawenEdges = new ArrayList<>();	
    
	public void Head() {
        Panel = new JPanel();
        
        GraphLabel = new JLabel("Graph Main Interface Screen");
        GraphLabel.setFont(new Font("Serif",Font.BOLD,30));
        //AddNodeButton = new JButton("Create Node");
                
        Panel.setLocation(100,100);
        
        //AddNodeButton.setToolTipText("Press this button if you want to create a node");
        
        Panel.add(GraphLabel);
        //Panel.add(AddNodeButton);
        
		add(Panel,BorderLayout.PAGE_START);
		
	}
	
	public void MenuBar() {
		JMenuBar Bar = new JMenuBar();
		
		JMenu Edit = new JMenu("Edit");
		JMenu AnalysisAndAlgorithms = new JMenu("Analysis and Algorithms");

		
		//JMenuItem AddNode = new JMenuItem("Add Node");
		//JMenuItem AddEdge = new JMenuItem("Add Edge");

		Edit.add(AddNode);
		Edit.add(AddEdge);
		Edit.add(RemoveEdge);
		Edit.add(ClearScreen);
		Edit.add(ExportGraph);
		Edit.add(ImportGraph);

		
		AnalysisAndAlgorithms.add(IsEulerian);
		AnalysisAndAlgorithms.add(IsSemiEulerian);
		AnalysisAndAlgorithms.add(IsConnected);
		AnalysisAndAlgorithms.add(EulerianPath);
		AnalysisAndAlgorithms.add(EulerianPathFleury);
		AnalysisAndAlgorithms.add(IsHamiltonian);
		AnalysisAndAlgorithms.add(Djikstra);

		
		Bar.add(Edit);
		Bar.add(AnalysisAndAlgorithms);
		
		setJMenuBar(Bar);
	}
	
	public void AddNode() {
		
		String NodeName = JOptionPane.showInputDialog("Enter The Node's Name: ");
		
		while (MatrixGraph.DoesNodeExist(NodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Already Exists");
			NodeName = JOptionPane.showInputDialog("Enter The Node's Name Again: ");
		}
		
		int x = RandomNumberGenerator.generateRandomNumber(50, 800);
		int y = RandomNumberGenerator.generateRandomNumber(50, 500);

		clsDrawOval Oval1 = new clsDrawOval(x,y,NodeName);
		add(Oval1);
		arrDrawenNodes.add(Oval1);
		
        validate();

		
		//PanelOfCanvas.add(Oval1);

		clsNode Node = new clsNode();
		Node.set_NodeValue(NodeName);
		Node.set_XCordinate(x);
		Node.set_YCordinate(y);
		Node.set_Positin(MatrixGraph.get_numVertices());
		MatrixGraph.AddNode(Node);

		
	}

	public void AddNode(String NodeName , int x , int y) {
				
//		while (MatrixGraph.DoesNodeExist(NodeName)) {
//			JOptionPane.showMessageDialog(null,"The Node You Enterd Already Exists");
//			NodeName = JOptionPane.showInputDialog("Enter The Node's Name Again: ");
//		}
		

		clsDrawOval Oval1 = new clsDrawOval(x,y,NodeName);
		add(Oval1);
		arrDrawenNodes.add(Oval1);
		
        validate();

		
		//PanelOfCanvas.add(Oval1);

//		clsNode Node = new clsNode();
//		Node.set_NodeValue(NodeName);
//		Node.set_XCordinate(x);
//		Node.set_YCordinate(y);
//		Node.set_Positin(MatrixGraph.get_numVertices());
		//MatrixGraph.AddNode(Node);

		
	}

	public void AddEdge() {
		
		
		String StartNodeName = JOptionPane.showInputDialog("Enter The Node That You Want To Start From: ");
		if (!MatrixGraph.DoesNodeExist(StartNodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
			return;
		}
		String EndNodeName = JOptionPane.showInputDialog("Enter The Node That You Want To End At: ");
		if (!MatrixGraph.DoesNodeExist(EndNodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
			return;
		}
		
		
		clsNode StartNode = new clsNode();
		clsNode EndNode = new clsNode();

		StartNode = MatrixGraph.FindNode(StartNodeName);
		EndNode = MatrixGraph.FindNode(EndNodeName);
		
		if(MatrixGraph.DoesEdgeExist(StartNode, EndNode)) {
			JOptionPane.showMessageDialog(null,"The Edge You Enterd Already Exists");
			return;
		}

		
		int XStart = StartNode.get_XCordinate();
		int YStart = StartNode.get_YCordinate();
		
		int XEnd = EndNode.get_XCordinate();
		int YEnd = EndNode.get_YCordinate();

		MatrixGraph.addEdge(StartNode, EndNode, 1);
		
		clsDrawEdge Edge1 = new clsDrawEdge(XStart,YStart,XEnd,YEnd);
		add(Edge1);
		arrDrawenEdges.add(Edge1);
		
		validate();
	}

	public void AddEdge(String StartNodeName , String EndNodeName) {
		
		
//		if (!MatrixGraph.DoesNodeExist(StartNodeName)) {
//			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
//			return;
//		}
//		if (!MatrixGraph.DoesNodeExist(EndNodeName)) {
//			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
//			return;
//		}
		
		
		clsNode StartNode = new clsNode();
		clsNode EndNode = new clsNode();

		StartNode = MatrixGraph.FindNode(StartNodeName);
		EndNode = MatrixGraph.FindNode(EndNodeName);
		
//		if(MatrixGraph.DoesEdgeExist(StartNode, EndNode)) {
//			JOptionPane.showMessageDialog(null,"The Edge You Enterd Already Exists");
//			return;
//		}

		
		int XStart = StartNode.get_XCordinate();
		int YStart = StartNode.get_YCordinate();
		
		int XEnd = EndNode.get_XCordinate();
		int YEnd = EndNode.get_YCordinate();

		//MatrixGraph.addEdge(StartNode, EndNode, 1);
		
		clsDrawEdge Edge1 = new clsDrawEdge(XStart,YStart,XEnd,YEnd);
		add(Edge1);
		arrDrawenEdges.add(Edge1);
		
		validate();
	}
	
	public boolean DoesGraphExist(){
		if(MatrixGraph.IsEmpty()) {
			JOptionPane.showMessageDialog(null," There is no Graph " );
			return false;
		}
		return true;
	}
	
	public void IsEulerianGraph() {
		if (!DoesGraphExist()) return;
		String Answer = "";
		if(MatrixGraph.IsEulerian()) {
			Answer = "Eulerian";
		}else {
			Answer = "Not Eulerian";
		}
		JOptionPane.showMessageDialog(null,"The Graph is: " + Answer);
	}
	
	public void IsSemiEulerianGraph() {
		if (!DoesGraphExist()) {
			return;
		}
		String Answer = "";
		if(MatrixGraph.IsSemiEulerian()) {
			Answer = "Semi Eulerian";
		}else {
			Answer = "Not Semi Eulerian";
		}
		JOptionPane.showMessageDialog(null,"The Graph is: " + Answer);
	}
	
	public void IsConnectedGraph() {
		if (!DoesGraphExist()) return;
		String Answer = "";
		if(MatrixGraph.isConnected()) {
			Answer = "Connected";
		}else {
			Answer = "Not Connected";
		}
		JOptionPane.showMessageDialog(null,"The Graph is: " + Answer);
	}
	
	public void ClearScreen() throws InterruptedException {

		for(int i = 0 ; i < arrDrawenNodes.size() ; i ++) {
			arrDrawenNodes.get(i).clear();
		}

		for(int i = 0 ; i < arrDrawenEdges.size() ; i ++) {
			arrDrawenEdges.get(i).clear();
		}

		MatrixGraph.ClearGraph();
	}

	public void RemoveEdge() {
		
		String StartNodeName = JOptionPane.showInputDialog("Enter The Node That You Want To Start From: ");
		if (!MatrixGraph.DoesNodeExist(StartNodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
			return;
		}
		String EndNodeName = JOptionPane.showInputDialog("Enter The Node That You Want To End At: ");
		if (!MatrixGraph.DoesNodeExist(EndNodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Doesen't Exist");
			return;
		}
		
		
		clsNode StartNode = new clsNode();
		clsNode EndNode = new clsNode();

		StartNode = MatrixGraph.FindNode(StartNodeName);
		EndNode = MatrixGraph.FindNode(EndNodeName);
		
		if(!MatrixGraph.DoesEdgeExist(StartNode, EndNode)) {
			JOptionPane.showMessageDialog(null,"The Edge You Enterd Dosen't Exist");
			return;
		}
		MatrixGraph.removeEdge(StartNode, EndNode);
		for (int i = 0 ; i < arrDrawenEdges.size() ; i++) {
			if (arrDrawenEdges.get(i).get_XStart() == StartNode.get_XCordinate() && arrDrawenEdges.get(i).get_YStart() ==
			StartNode.get_YCordinate() && arrDrawenEdges.get(i).get_XEnd() == EndNode.get_XCordinate() && 
			arrDrawenEdges.get(i).get_YEnd() == EndNode.get_YCordinate()) {
				arrDrawenEdges.get(i).clear();
			}
		}
		
	}
	
	public void RemoveNode() {
		
		String NodeName = JOptionPane.showInputDialog("Enter The Node's Name: ");
		
		while (!MatrixGraph.DoesNodeExist(NodeName)) {
			JOptionPane.showMessageDialog(null,"The Node You Enterd Does Not Exist");
			NodeName = JOptionPane.showInputDialog("Enter Another Node: ");
		}
	}
	
	public void SwitchFromMatrixToGUI(clsMatrixGraph G) {
		for (int i = 0 ; i < G.get_ListOfNodes().size() ; i++) {
			int x = G.get_ListOfNodes().get(i).get_XCordinate();
			int y = G.get_ListOfNodes().get(i).get_YCordinate();

			AddNode(G.get_ListOfNodes().get(i).get_NodeValue(),x,y);
		}
		for (int i = 0 ; i < MatrixGraph._numVertices ; i++) {
			for (int j = 0 ; i < MatrixGraph._numVertices ; i++) {
				if(MatrixGraph._adjacencyMatrix[i][j]==1) {
					AddEdge(MatrixGraph.get_ListOfNodes().get(i).get_NodeValue() , MatrixGraph.get_ListOfNodes().get(j).get_NodeValue());
				}
			}
		}
	}
	
	public void EulerianPathFleury() {
		List<Integer> circuit = new ArrayList<>();
		   //     List<Integer> eulerianPath = MatrixGraph.findEulerianPath();

//				MatrixGraph.findEulerianPathUtil(circuit);
				int vertex = 0;
		        clsMatrixGraph.findEulerianPathUtil(MatrixGraph._adjacencyMatrix,vertex,circuit);
		        System.out.println("Eulerian Path or Circuit Using Fleury:");
		        String EulerianPath = "Eulerain Path: ";
		        for (Integer ver: circuit) {
		            EulerianPath += ver + " ";
		        }
		        JOptionPane.showMessageDialog(null, EulerianPath);
	}
	
	public void IsHamiltonian() {
		String Answer = "";
		if (MatrixGraph.hasHamiltonianPath()) {
			Answer = "The Graph is Hamiltonian";
		}
		else {
			Answer = "The Graph is not Hamiltonian";
		}
		JOptionPane.showMessageDialog(null, Answer);
	}
	
	public void ExportGraph() {
		MatrixGraph.Export();
		JOptionPane.showMessageDialog(null, "The Graph Exported Successfully");

	}
	
	public void ImportGraph() {
		MatrixGraph = clsMatrixGraph.Import();
		JOptionPane.showMessageDialog(null, "The Graph Imported Successfully");
		MatrixGraph.PrintGraph();
		SwitchFromMatrixToGUI(MatrixGraph);
	}
	
	public void FindEulerianPath() {
		List<Integer> circuit = new ArrayList<>();
		   //     List<Integer> eulerianPath = MatrixGraph.findEulerianPath();

//				MatrixGraph.findEulerianPathUtil(circuit);
				int vertex = 0;
		        clsMatrixGraph.findEulerianPathUtil(MatrixGraph._adjacencyMatrix,vertex,circuit);
		        System.out.println("Eulerian Path or Circuit Using Hierholzer:");
		        String EulerianPath = "Eulerain Path: ";
		        for (Integer ver: circuit) {
		            EulerianPath += ver + " ";
		        }
		        JOptionPane.showMessageDialog(null, EulerianPath);
	}
	
	public void Djikstra() {
		int NodeIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter The Node's index to find shortest path (Djikstra): "));
		int []path = MatrixGraph.findShortestPaths(NodeIndex);
		String Path = "Node Indexes: ";
		for (int i = 0 ; i < path.length ; i++) {
			Path += path[i] + "->";
		}
		
		JOptionPane.showMessageDialog(null, Path);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == AddEdge) {
			
			AddEdge();
			
		}else if(e.getSource() == AddNode) {
			
			AddNode();
			
		}else if(e.getSource() == IsEulerian) {
			IsEulerianGraph();
			
		}else if(e.getSource() == IsSemiEulerian) {
			IsSemiEulerianGraph();
			
		}else if(e.getSource() == IsConnected) {
			IsConnectedGraph();
		
		}else if(e.getSource() == ClearScreen) {
			try {
				ClearScreen();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == RemoveEdge) {
			RemoveEdge();
			}
		else if(e.getSource() == EulerianPath) {
			FindEulerianPath();
		}
		else if(e.getSource() == IsHamiltonian) {
			IsHamiltonian();
		}
		else if (e.getSource() == ExportGraph) {
			ExportGraph();
		}
		else if (e.getSource() == ImportGraph) {
			ImportGraph();
		}
		else if (e.getSource() == EulerianPathFleury) {
			EulerianPathFleury();
		}
		else if(e.getSource() == Djikstra) {
			Djikstra();
		}
		
	}
	
	public clsUserInterface() {
		setTitle("Graph Draw Application");
		setLocation(200,200);

		setSize(800,500);
		//getContentPane().setBackground(Color.black);
		
        MenuBar();
		Head();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        
        
        //PanelOfCanvas.setSize(50, 50);
        //PanelOfCanvas.setBackground(Color.WHITE);
        //MyCanvas.setSize(500, 50);
        //MyCanvas.setBackground(Color.black);
        //PanelOfCanvas.add(MyCanvas);
        //add(MyCanvas,BorderLayout.CENTER);
        
        AddEdge.addActionListener(this);
        AddNode.addActionListener(this);
        ClearScreen.addActionListener(this);
        IsEulerian.addActionListener(this);
        IsSemiEulerian.addActionListener(this);
        IsConnected.addActionListener(this);
        RemoveEdge.addActionListener(this);
        EulerianPath.addActionListener(this);
        IsHamiltonian.addActionListener(this);
        ExportGraph.addActionListener(this);
        ImportGraph.addActionListener(this);
        EulerianPathFleury.addActionListener(this);
        Djikstra.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clsUserInterface UserInterface = new clsUserInterface();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
