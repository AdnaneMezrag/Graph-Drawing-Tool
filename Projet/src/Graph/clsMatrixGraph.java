package Graph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Class to represent a graph using an adjacency matrix
public class clsMatrixGraph implements Serializable {
	
    private static final long serialVersionUID = 1L; // Include a serialVersionUID to control versioning
    private List<Integer> eulerianPath;
    private int numEdges;

	
    public int[][] _adjacencyMatrix;
    public int _numVertices = 0;
    private boolean _IsDirected = false;
    private boolean _IsWeighted = false;
    private ArrayList<clsNode> _ListOfNodes = new ArrayList<>();
    
    
    
    //Djikstra
    public int[] findShortestPaths(int source) {
        // Initialize distance array and visited array
        int[] distance = new int[_numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        boolean[] visited = new boolean[_numVertices];

        // Perform Dijkstra's algorithm
        for (int i = 0; i < _numVertices - 1; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < _numVertices; v++) {
                if (!visited[v] && _adjacencyMatrix[u][v] != 0 && distance[u] != Integer.MAX_VALUE &&
                        distance[u] + _adjacencyMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + _adjacencyMatrix[u][v];
                }
            }
        }

        return distance;
    }

    private int minDistance(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < _numVertices; v++) {
            if (!visited[v] && distance[v] <= minDistance) {
                minDistance = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    
    
    
    
    
    // Function to calculate the number of edges in the graph
    private int calculateNumEdges(int[][] adjacencyMatrix) {
        int numEdges = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                numEdges += adjacencyMatrix[i][j];
            }
        }
        // For an undirected graph, each edge is counted twice
        return numEdges / 2;
    }
    

    // Function to find an Eulerian path or circuit using Fleury's algorithm
    public List<Integer> findEulerianPath() {
        if (!isValidGraph()) {
            return null; // No Eulerian path or circuit exists
        }

        int startVertex = findStartVertex();
        dfs(startVertex);

        // Check if all edges were traversed
        if (eulerianPath.size() != numEdges + 1) {
            return null; // No Eulerian path or circuit exists
        }

        return eulerianPath;
    }
    
    
    // Helper function to perform DFS traversal
    private void dfs(int vertex) {
        for (int nextVertex = 0; nextVertex < _adjacencyMatrix[vertex].length; nextVertex++) {
            if (_adjacencyMatrix[vertex][nextVertex] > 0) {
                // Remove the edge
            	_adjacencyMatrix[vertex][nextVertex]--;
            	_adjacencyMatrix[nextVertex][vertex]--;
                dfs(nextVertex);
            }
        }
        eulerianPath.add(0, vertex);
    }

    // Helper function to check if the graph is connected and has zero or two vertices with odd degree
    private boolean isValidGraph() {
        int numOddDegreeVertices = 0;
        for (int i = 0; i < _adjacencyMatrix.length; i++) {
            int degree = 0;
            for (int j = 0; j < _adjacencyMatrix[i].length; j++) {
                degree += _adjacencyMatrix[i][j];
            }
            if (degree % 2 != 0) {
                numOddDegreeVertices++;
            }
        }

        return numOddDegreeVertices == 0 || numOddDegreeVertices == 2;
    }
     
    // Helper function to find the start vertex for Fleury's algorithm
    private int findStartVertex() {
        for (int i = 0; i < _adjacencyMatrix.length; i++) {
            int degree = 0;
            for (int j = 0; j < _adjacencyMatrix[i].length; j++) {
                degree += _adjacencyMatrix[i][j];
            }
            if (degree % 2 != 0) {
                return i;
            }
        }
        return 0; // If no odd-degree vertex found, return 0
    }
    
    
    public void PrintGraph() {
    	for (int i = 0 ; i < _numVertices ; i++) {
    		System.out.println(_ListOfNodes.get(i).get_NodeValue() + "\t");
    	}
    }
    
    public void Export() {
        // Serialization: Write the object to a file
        try (FileOutputStream fileOut = new FileOutputStream("object.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(this);
            System.out.println("Object has been serialized");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static clsMatrixGraph Import() {
 	   clsMatrixGraph newObj = null;
        try (FileInputStream fileIn = new FileInputStream("object.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

        	   newObj = (clsMatrixGraph) objectIn.readObject();
               System.out.println("Object has been deserialized");
               //System.out.println("Data: " + newObj.getData());

           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
		return newObj;
    }
    
    
    public boolean hasHamiltonianPath() {
        boolean[] visited = new boolean[_numVertices];
        Arrays.fill(visited, false);

        // Start the DFS from each vertex to check for Hamiltonian path
        for (int i = 0; i < _numVertices; i++) {
            if (dfsHasHamiltonianPath(i, visited, 1)) {
                return true;
            }
        }
        return false;
    }

    // Recursive function to perform DFS and check for Hamiltonian path
    private boolean dfsHasHamiltonianPath(int vertex, boolean[] visited, int count) {
        visited[vertex] = true;

        // If all vertices are visited, it's a Hamiltonian path
        if (count == _numVertices) {
            visited[vertex] = false;
            return true;
        }

        // Recursive DFS for adjacent vertices
        for (int i = 0; i < _numVertices; i++) {
            if (_adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                if (dfsHasHamiltonianPath(i, visited, count + 1)) {
                    visited[vertex] = false;
                    return true;
                }
            }
        }

        // Backtrack
        visited[vertex] = false;
        return false;
    }

    
    
    public static void findEulerianPathUtil(int[][] graph, int v, List<Integer> circuit) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1) {
                graph[v][i] = 0; // remove edge
                graph[i][v] = 0; // remove edge
                findEulerianPathUtil(graph, i, circuit);
            }
        }
        circuit.add(0, v);
    }

    
    public int CalculateDegreeOfVertex(clsNode Node) {
    	
    	//We Consider that the graph is undirected    	
    	if(!DoesNodeExist(Node.get_NodeValue())) {
    		return -1;
    	}
    	int NodeDegree = 0;
    	int NodeIndex = _ListOfNodes.indexOf(Node);
    	for(int i = 0 ; i < _ListOfNodes.size() ; i++) {
    		if(_adjacencyMatrix[NodeIndex][i] == 1) {
    			NodeDegree++;
    		}
    	}
    	
//    	for(int i = 0 ; i < _ListOfNodes.size() ; i++) {
//    		if(_adjacencyMatrix[i][NodeIndex] == 1) {
//    			NodeDegree++;
//    		}
//    	}
//    	
//    	if(_adjacencyMatrix[NodeIndex][NodeIndex] == 1) {
//    		NodeDegree--;
//    	}
    	
    	return NodeDegree;
    }
    
    private void DFS(int startingVertex, boolean[] visited) {
        visited[startingVertex] = true;
        
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < _numVertices; i++) {
            if (_adjacencyMatrix[startingVertex][i]==1 && !visited[i]) {
                DFS(i, visited);
            }
        }
    }
    
    public boolean isConnected() {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[_numVertices];
        Arrays.fill(visited, false);

        // Perform DFS traversal starting from vertex 0
        DFS(0, visited);

        // Check if all vertices are visited after DFS traversal
        for (int i = 0; i < _numVertices; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }
    
    public boolean IsEmpty() {
    	if(_ListOfNodes.isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
    public boolean IsEulerian() {
    	//The Graph has to be connected
    	if (!isConnected()) {
    		return false;
    	}
    	for(int i = 0 ; i < _ListOfNodes.size() ; i ++) {
    		if((CalculateDegreeOfVertex(_ListOfNodes.get(i))%2) != 0) {
    			return false;
    		}
    	}

    	return true;
    }
    
    public boolean IsSemiEulerian() {
       	//The Graph has to be connected
    	if (!isConnected()) {
    		return false;
    	}
    	
    	byte OddDegreeVertices = 0;
    	
    	for(int i = 0 ; i < _ListOfNodes.size() ; i ++) {
    		if((CalculateDegreeOfVertex(_ListOfNodes.get(i))%2) != 0) {
    			OddDegreeVertices++;
    			if(OddDegreeVertices == 3) {
        			return false;
    			}
    		}
    	} 
    	if(OddDegreeVertices < 2) {
    		return false;
    	}

    	return true;
    }
    
    public clsMatrixGraph() {
    	
    }

    public clsMatrixGraph(int MaxNumOfVertices) {
        
        _adjacencyMatrix = new int[MaxNumOfVertices][MaxNumOfVertices];
        
    }
    
    public boolean DoesNodeExist(String NodeName) {
    	if (FindNode(NodeName) == null) {
    		return false;
    	}
    	return true;
    }
    
    public boolean DoesEdgeExist(clsNode StartNode,clsNode EndNode) {
    	//We consider that the graph is undirected
    	if(_adjacencyMatrix[StartNode.get_Positin()][EndNode.get_Positin()]==1) {
    		return true;
    		
    	}
    	return false;
    }
    
    public clsNode FindNode(String NodeName) {
    	for (int i = 0 ; i < _ListOfNodes.size() ; i++) {
    		if (NodeName.equalsIgnoreCase(_ListOfNodes.get(i).get_NodeValue()) ) {
    			return _ListOfNodes.get(i);
    		}
    	}
    	return null;
    }
    
    public void AddNode(clsNode Node) {
    	_ListOfNodes.add(Node);
    	_numVertices++;
    	
    }

    public void addEdge(clsNode source, clsNode destination, int weight) {
    	//We Consider that the graph is undirected    	
        _adjacencyMatrix[source.get_Positin()][destination.get_Positin()] = weight;
        // For undirected graphs, uncomment the following line
        _adjacencyMatrix[destination.get_Positin()][source.get_Positin()] = weight;
    }

    public void removeEdge(clsNode StartNode , clsNode EndNode) {
        _adjacencyMatrix[StartNode.get_Positin()][EndNode.get_Positin()] = 0;
        // For undirected graphs, uncomment the following line
        _adjacencyMatrix[EndNode.get_Positin()][StartNode.get_Positin()] = 0;
    }

    public void ClearGraph() {
    	
    	for (int i = 0 ; i < _ListOfNodes.size() ; i++) {
    		for (int j = 0 ; j < _ListOfNodes.size();j++) {
        		_adjacencyMatrix[i][j] = 0;
    		}
    	}
    	_numVertices = 0;
    	_ListOfNodes.clear();
    }
    
    public void removeNode(clsNode Node) {
    	_ListOfNodes.remove(Node);
    	//for (int i = 0 ; i < _)
    }
    
    public int getWeight(int source, int destination) {
        return _adjacencyMatrix[source][destination];
    }

	public boolean is_IsDirected() {
		return _IsDirected;
	}

	public void set_IsDirected(boolean _IsDirected) {
		this._IsDirected = _IsDirected;
	}

	public int get_numVertices() {
		return _numVertices;
	}

	public void set_numVertices(int _numVertices) {
		this._numVertices = _numVertices;
	}

	public boolean is_IsWeighted() {
		return _IsWeighted;
	}

	public void set_IsWeighted(boolean _IsWeighted) {
		this._IsWeighted = _IsWeighted;
	}

	public ArrayList<clsNode> get_ListOfNodes() {
		return _ListOfNodes;
	}

	public void set_ListOfNodes(ArrayList<clsNode> _ListOfNodes) {
		this._ListOfNodes = _ListOfNodes;
	}
}
