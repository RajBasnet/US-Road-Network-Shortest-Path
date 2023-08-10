package wmich.RajBasnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class reads both the file and create an array list for all the information present in each file.
 * For example, for place.txt it reads the file, then store place ID and place name information in the 
 * form of array list.
 */
public class USList {

	private ArrayList<ArrayList<String>> list = new ArrayList<>();

	public ArrayList<ArrayList<String>> getList() {
		return list;
	}

	public void setList(ArrayList<ArrayList<String>> list) {
		this.list = list;
	}

	/*
	 * This methods creates the array list by reading the file and stores all the necessary information.
	 * 
	 * @param FileName the file to be read/parsed for the information.
	 * @return ArrayList<ArrayList<String>> represents the array list that stores the necessary information.
	 */
	public ArrayList<ArrayList<String>> createList(String fileName) throws FileNotFoundException{

		//Open a new file
		File file = new File(fileName);

		//Use scanner to read the file
		Scanner input = new Scanner(file);

		int i = 0;

		//Read each line of the file and parse the necessary information to store them in the list.
		while(input.hasNextLine()){
			list.add(new ArrayList<>());
			String line = input.nextLine();
			String[] lineArray = line.split(",");

			//Parse the data separated by commas
			for(int j = 0; j < lineArray.length; j++){
				list.get(i).add(j, lineArray[j]);
			}
			i++;
		}

		//Close the scanner input
		input.close();
		return list;
	}

	/*
	 * This method return the size of the list.
	 * 
	 * @return int returns the size of the list
	 */
	public int size() {
		return this.getList().size();
	}
}

