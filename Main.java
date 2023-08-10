package wmich.RajBasnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {

		//Create a new array list using USList class to read and store all the information from  both
		//the files place.txt and road.txt
		USList aListPlace = new USList();
		aListPlace.createList("C:/Users/Raj/Documents/Raj/A_Semester_Ninth/CS 4310 Algorithms/USRoads/place.txt");
		USList aListRoad = new USList();
		aListRoad.createList("C:/Users/Raj/Documents/Raj/A_Semester_Ninth/CS 4310 Algorithms/USRoads/road.txt");

		//Simple loop code to get the total vertices needed for graph
		HashMap<Integer, Integer> vertices = new HashMap<>();

		for(int i = 0; i < aListRoad.size(); i++) {

			int src = Integer.parseInt(aListRoad.getList().get(i).get(0));
			int dest = Integer.parseInt(aListRoad.getList().get(i).get(1));
			vertices.put(src, 0);
			vertices.put(dest, 1);
		}

		//Ask for the user input for the source place name and destination place name
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Source Name");
		String sName = input.nextLine();
		System.out.println("Enter the Destination Name");
		String dName = input.nextLine();
		input.close();

		//Convert the input string to place IDs using the place names
		int source = 0;
		int destination = 0;

		//Loop through the place list to get respective place IDs from the user input place names
		for(int i = 0; i < aListPlace.getList().size(); i++){

			if(sName.equalsIgnoreCase(aListPlace.getList().get(i).get(1))){

				source = Integer.parseInt(aListPlace.getList().get(i).get(0));
			}

			if(dName.equalsIgnoreCase(aListPlace.getList().get(i).get(1))){

				destination = Integer.parseInt(aListPlace.getList().get(i).get(0));
			}
		}

		//Create a new graph structure with given number of vertices
		Graph uGraph = new Graph(vertices.size());

		//Add all the edges required for the graph using road list that contains source place ID,
		//destination place ID and distance between them as well.
		for(int i = 0; i < aListRoad.size(); i++) {

			int src = Integer.parseInt(aListRoad.getList().get(i).get(0));
			int dest = Integer.parseInt(aListRoad.getList().get(i).get(1));
			float distance = Float.parseFloat(aListRoad.getList().get(i).get(2));
			//Add edges two times so that the graph becomes undirected which means each edge in the
			//graph will become bidirectional.
			uGraph.addEdge(src, dest, distance);
			uGraph.addEdge(dest, src, distance);

		}

		//Utilize the Dijkstra's algorithm on the undirected graph
		Algorithm dijkstra = new Algorithm(uGraph);

		//Use the method present in algorithm to store shortest paths in hash map
		HashMap<Integer, Float> map = dijkstra.DijkStra(source, destination);
		HashMap<Integer, Float> actualMap = new HashMap<>();

		//Utilize back tracking to print the only shortest path between source and destination
		int back = destination;
		actualMap.put(back, map.get(back));
		int toggle = 0;

		while(true) {

			//Iterate through the hash map of shortest paths backwards so that only the shortest paths
			//can be printed 
			if(uGraph.list.containsKey(back)) {
				Iterator<Node> it = uGraph.list.get(back).iterator();

				float temp = 0;
				if(map.containsKey(back)){
					temp = map.get(back);
				} else{
					break;
				}

				//Use the distance stored for each path to get the exact vertex while back tarcking
				while(it.hasNext()) {
					Node node  = it.next();
					float tDist = temp - node.distance;
					if(map.containsKey(node.value)) {

						if( Math.round(tDist*100) == Math.round(map.getOrDefault(node.value, (float) -1) * 100) ) {

							back = node.value;
							actualMap.put(back, map.get(back));

						} else {
						}
					}
				}
			}

			//When we reach from destination to source while back tracking the loop stops
			if(back == source) {
				toggle = 1;
				break;
			} else {
				toggle = 0;
			}
		}

		//Utilize LinkedHashMap to sort the actual map that contains only shortest path for displaying
		//it properly from source to destination. 
		//Since, hash map does not store the paths in any order, linked hash map is needed to print the
		//shortest path from source to destination in order.

		LinkedHashMap<Integer, Float> sortedActualMap = new  LinkedHashMap<>();
		actualMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> sortedActualMap.put(x.getKey(), x.getValue()));

		//Create an array list from the sorted actual map to retrieve and print the remaining information
		//for each place and each road such as place names, signs, description etc
		Set<Integer> keySet = sortedActualMap.keySet();
		ArrayList<Integer> placeID = new ArrayList<Integer>(keySet); 

		Collection<Float> values = sortedActualMap.values();
		ArrayList<Float> distance = new ArrayList<Float>(values);

		/*
		 * Utilize road list and place list that contains all the information about each place and 
		 * each road to retrieve those information only for the shortest paths present in sorted actual map.
		 */
		System.out.println("Searching from " + source + "(" + sName + ")" + " to " + destination + "(" + dName + ")");
		for(int i = 0; i < sortedActualMap.size()-1; i++) {

			String way = "";
			for(int j = 0; j < aListRoad.size(); j++){

				if(aListRoad.getList().get(j).size() < 4){
					continue;
				}
				int srcID = Integer.parseInt(aListRoad.getList().get(j).get(0));
				int destID = Integer.parseInt(aListRoad.getList().get(j).get(1));

				//When the place ID of source and place ID of destination present in actual map of 
				//shortest paths match the place ID of source and place ID of destination respectively
				//in the road list, then it retrieves the sign/description for displaying in the output.
				if( srcID == placeID.get(i) && destID == placeID.get(i+1)){
					way = aListRoad.getList().get(j).get(3);
					break;
				}

				//Another if statement for same purpose just in the vice versa was for undirected graph
				//or bidirectional road.
				if( srcID == placeID.get(i+1) && destID == placeID.get(i)){
					way = aListRoad.getList().get(j).get(3);
					break;
				}
			}

			String name1 = "(null)";
			String name2 = "(null)";
			for(int j = 0; j < aListPlace.size(); j++){

				int pID = Integer.parseInt(aListPlace.getList().get(j).get(0));

				//Retrieve the place name using place ID present in actual map for source
				if(pID == placeID.get(i)) {
					name1 = "(" + aListPlace.getList().get(j).get(1) +")";
				}

				//Retrieve the place name using place ID present in actual map for destination
				if(pID == placeID.get(i+1)) {
					name2 = "(" + aListPlace.getList().get(j).get(1) +")";
				}
			}

			//For printing the original distance of the road/edge
			float tempD = Math.round( (distance.get(i+1) - distance.get(i))*100 );
			tempD = tempD/100;
			System.out.print("\t" + (i+1) +": ");
			System.out.println(placeID.get(i) + name1 + " -> " + placeID.get(i+1) + name2 + ", " + way + ", " + tempD + " mi.");
		}

		//This prints only when no path between source and destination is present
		if(toggle == 0) {
			System.out.println("No pathway available!!!");
			System.exit(1);
		}

		//Prints the final statement about total shortest distance using Dijkstra's algotihtm.
		float result = Math.round(dijkstra.result*100);
		result = result/100; 
		System.out.println("It takes " + result +  " miles from " + source + "(" + sName + ")" + " to " + destination + "(" + dName + ").");

	}
}

