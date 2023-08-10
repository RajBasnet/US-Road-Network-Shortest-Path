package wmich.RajBasnet;

/*
 * This class stores the information of the edges of the graph
 */
public class Edge {

	public int source;
	public int destination;
	public float distance;

	/*
	 * This constructor for edge assigns the source, destination and distance between each edge.
	 */
	public Edge(int source, int destination, float distance){

		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
}

