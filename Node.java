package wmich.RajBasnet;

/*
 * This class represents the node utilized to create the graph which stores the information 
 * of place ID and distance from it to each edge.
 */
public class Node {

	public int value;
	public float distance;

	/*
	 * This is the constructor for Node to assign place ID and the distance
	 * 
	 * @param value represents the place ID 
	 * @param distance represents the distance
	 */
	public Node(int value, float distance) {

		this.value = value;
		this.distance = distance;
	}
}
