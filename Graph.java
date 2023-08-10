package wmich.RajBasnet;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class creates a graph data structure using hash map.
 */
public class Graph {

	private int vertices;
	HashMap<Integer, ArrayList<Node>> list;

	/*
	 * This is the constructor to create a new graph using hash map and total vertices.
	 * 
	 * @param vertices represents total number of vertices of the graph
	 */
	public Graph(int vertices) {

		this.vertices = vertices;
		list = new HashMap<>();
	}

	/*
	 * This method returns the total vertices of the graph.
	 */
	public int getVertices(){

		return vertices;
	}

	/*
	 * This method adds the edge for the graph using source place, destination place and distance between
	 * them.
	 * 
	 * @param source represents the source place ID
	 * @param destination represents the destination place ID
	 * @param distance represents the distance between source and destination
	 */
	public void addEdge(int source, int destination, float distance) {

		if(!list.containsKey(source)){
			list.put(source, new ArrayList<Node>());
		}

		list.get(source).add(new Node(destination, distance));
	}

}

