package wmich.RajBasnet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * This class creates the Dijkstra's algorithm to find the shortest path between two places as well as
 * the shortest distance between them.
 * This class utilized the Graph class in which the algorithm is applied.
 */
public class Algorithm{

	private Graph graph;
	public float result;

	public Algorithm(Graph graph) {

		this.graph = graph;
	}
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	/*
	 * This method utilizes the Dijkstra's algorithm to find the shortest path from source to destination
	 * using the priority queue for the shortest distance possible.
	 * For each node stored in priority queue from source it moves towards the shortest path each time
	 * until it reaches the destination.
	 * This method also gives the shortest path distance and store it in the global result variable so
	 * that it can be easily displayed.
	 * 
	 * @param source represents the source place ID to find the shortest path
	 * @param destination represents the destination place ID to find the shortest path
	 * 
	 * @return returns the hash map of the shortest path it follows
	 */
	public HashMap<Integer,Float> DijkStra(int source, int destination) {

		//Creates new hash map to store shortest possible paths
		HashMap<Integer,Float> distanceMap = new HashMap<>();

		//Utilizes priority queue to only check that path which is shortest in distance from the specfied
		//vertex.
		PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparingDouble(node -> node.distance));
		ArrayList<Integer> nd = new ArrayList<>();

		pq.add(new Node(source, 0));
		result = Float.MIN_VALUE;

		//Until the priority queue is empty check for all the possible shortest paths from source to
		//destination.
		while(pq.size() != 0) {
			Node node = pq.remove();
			nd.add(node.value);

			//Only store new node paths using hash map
			if(!distanceMap.containsKey(node.value)) {

				distanceMap.put(node.value, node.distance);
				result = Math.max(result, node.distance);
				int current = node.value;

				//If destination is reached, then just come out from the loop
				if(current == destination) {
					break;
				}

				//For each node path the graph goes, add new node paths according to priority queue
				//only if that node path is not already in hash map
				if(this.graph.list.containsKey(current)){

					for(Node x : this.getGraph().list.get(current)) {

						if(!distanceMap.containsKey(x.value)) {

							pq.add(new Node(x.value, node.distance + x.distance));
						}
					}                 
				}
			}          
		}

		//Returns the hash map that contains the possible shortest paths from source to destination.
		return distanceMap;
	}  
}
