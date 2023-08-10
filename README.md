# US-Road-Network-Shortest-Path

For this programming assignment that includes finding the shortest path between two 
places on the US road network using Dijkstra’s shortest path algorithm on the graph, Eclipse IDE 
was utilized to write the code, compile it, and run it to display the proper results.

Basically, the main idea was to read all the data present on each file, create an undirected 
weighted graph using the road file in which two connecting roads will behave as edges from 
source to destination. Then, on that graph utilize the Dijkstra’s algorithm that utilizes the priority 
queue to find the shortest path and to determine the shortest path distance between two places.
To explain with further details, both the files place.txt and road.txt were read and separate array
lists were created for both to store all the data related to all the places and all the roads. Then, 
using the road list that had source place ID and destination place ID, each road was supposed to 
be each edge for the graph and hash map was utilized to create the graph data structure 
(undirected weighted graph) for all the roads. Then, the graph was put into the Dijkstra’s 
algorithm that included priority queue to determine the shortest path which returns the separate 
map that includes all the possible paths that it went through and finally returns the shortest path
and shortest path distance. Finally, at the end back tracking was utilized from destination to 
source to print only the shortest paths from source to destination.

This code was written, compiled, and run using Eclipse IDE for Java Developers. To run
the program in Eclipse IDE, the source of the java project to be run needs to be selected. Right 
click on it and select ‘Run As’. Finally, choose ‘Java Application’. My computer had JRE 
System library (JavaSE-14) to run the program in Eclipse. After running the program, it simply 
asks for “Source Name” and “Destination Name” and then prints the respective output result.
