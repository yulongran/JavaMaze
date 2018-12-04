package RanYulongUgarteEvanProject2_Maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Construct a perfect Maze and solve by DFS and BFS
 * 
 * @author Ugarte Evan and Ran Yulong
 *
 * @param <E>
 */
public class Maze<E> {

	Node<E>[][] nodeGraph; // 2D array of cells
	int nodeCount; // Count the number of Node in 2D array
	int size; // Input Size of the Maze
	private Random myRandGen; // Random Generator

	/**
	 * Initialize the Maze: Construct size*size number of cells
	 * 
	 * @param size
	 */
	public Maze(int size) {

		// Construct a Random Generator
		myRandGen = new java.util.Random();

		// Initialize the Size of the Maze
		this.size = size;

		// NodeCount for counting number of cell in the Maze
		nodeCount = 0;

		// Construct a 2D array with row and column as the size of the Maze
		nodeGraph = new Node[size + 1][size + 1];
		for (int i = 1; i < nodeGraph.length; i++) {

			// Construct an node for each element of 2D array
			// Each node will have the x and y coordinate as the index of the 2D array
			// Each node will contains a number as nodeCount, nodeCount increment as the
			// loop
			for (int j = 1; j < nodeGraph.length; j++) {
				nodeGraph[i][j] = new Node<E>(i, j, nodeCount);
				nodeCount++;
			}
		}
	}

	/**
	 * Initialize the Maze: Construct size*size number of cells, takes an Random
	 * seed to choose wall
	 * 
	 * @param size
	 */
	public Maze(int size, int RandomSeed) {

		// Construct a random generator with a RandomSeed
		myRandGen = new java.util.Random(RandomSeed);

		// Initialize the Size of the Maze
		this.size = size;

		// NodeCount for counting number of cell in the Maze
		nodeCount = 0;

		// Construct an node for each element of 2D array
		// Each node will have the x and y coordinate as the index of the 2D array
		// Each node will contains a number as nodeCount, nodeCount increment as the
		// loop
		nodeGraph = new Node[size + 1][size + 1];
		for (int i = 1; i < nodeGraph.length; i++) {
			for (int j = 1; j < nodeGraph.length; j++) {
				nodeGraph[i][j] = new Node<E>(i, j, nodeCount);
				nodeCount++;
			}
		}
	}

	/**
	 * Generate Maze by DFS
	 */
	public void generateMazeDFS() {

		// Create stack for DFS
		Stack<Node<E>> cellStack = new Stack<Node<E>>();

		// Initialize the starting cell
		Node<E> currentCell = nodeGraph[1][1];

		// Count number of visited cells
		int visitedCells = 1;

		// Looping until visit every cell
		while (visitedCells < nodeCount) {

			// ArrayList neighbors contains all neighbors of current cell
			ArrayList<Node<E>> neighbors = this.getAllNeighbors(currentCell);

			// ArrayList neighborsWithAllWall
			ArrayList<Node<E>> neighborsWithAllWall = new ArrayList<>();

			// Add all neighbors that has allWalls to the neighborsWithAllWall
			for (int l = 0; l < neighbors.size(); l++) {
				if ((neighbors.get(l).hasAllWalls())) {

					neighborsWithAllWall.add(neighbors.get(l));
				}
			}

			// DFS algorithm
			// If the cell has Neighbors with all walls or neighbors has no edges
			if (!neighborsWithAllWall.isEmpty()) {

				// Choose a random neighbor
				Node<E> randomNeighbor = neighborsWithAllWall.get((int) (myRandom() * (neighborsWithAllWall.size())));

				// addEdge between two node or knock down wall
				addEdge(currentCell.getXCor(), currentCell.getYCor(), randomNeighbor.getXCor(),
						randomNeighbor.getYCor());

				// Push the current cell to the stack
				cellStack.push(currentCell);

				// Assign the current cell to its neighbor
				currentCell = randomNeighbor;

				// Increment number of visitiedCell
				visitedCells++;

			}

			// If the cell has no neighbors with all Walls up
			else {

				// Assign a new current cell from the stack
				currentCell = cellStack.pop();
			}
		}

	}

	/**
	 * Print Perfect Maze with println
	 */
	public void printMaze() {
		String space = "   "; // one space

		// One tab
		String tabe = "	";

		// Independently print the beginning cell of the Maze
		System.out.print("+");
		System.out.print(" ");
		//System.out.print(space);

		// For every cell, except the first beginning, print a "+ - "
		for (int i = 1; i < size; i++) {
			System.out.print("+");
			System.out.print("-");
			//System.out.print(tabe);
		}

		// Print the end "+" for first line
		System.out.print("+");

		// Start a new line
		System.out.println();

		// For every horizontal cell
		for (int j = 1; j <= size; j++) {

			// Each row each with "|"
			System.out.print("|");

			// For every horizontal cell
			for (int k = 1; k <= size; k++) {

				// Print one space
				// System.out.print(space);

				System.out.print(" ");

				// Space indent
				// System.out.print(space);

				// If the cell has a east edges, leave an opening for the east neighboring
				if (nodeGraph[j][k].hasEastEdges()) {
					System.out.print(" ");

				}

				// If the cell has no east edges, print a wall "|"
				else {
					System.out.print("|");
				}

			}

			// Start with new line
			System.out.println();

			// Start with the new Line with star
			System.out.print("+");

			// For every horizontal edges looks like "+ - +"
			for (int p = 1; p <= size; p++) {

				// Print a space
				// System.out.print(space);

				// If the cell contains south Edges
				if (nodeGraph[j][p].hasSouthEdges()) {

					// Print empty space or no walls
					System.out.print(" ");

				}

				// If the cell is exist cell, print empty space too
				else if (j == size && p == size) {
					System.out.print(" ");
				}

				// If the cell contains no south neighbors and not last cell, print wall
				else {
					System.out.print("-");
				}

				// Print the end " +"
				// System.out.print(space);
				System.out.print("+");
			}
			System.out.println();
		}

	}

	/**
	 * Output a Perfect Maze to the file
	 * 
	 * @throws IOException
	 */
	public void outPutMazeToFile(String filename) throws IOException {

		File test = new File(filename);

		PrintWriter out = new PrintWriter(new FileWriter(filename, true));
		String space = "   "; // one space

		// One tab
		String tabe = "	";

		out.println("Graph Size: " + size);
		out.println("MAZE:");

		// Independently print the beginning cell of the Maze
		out.print("+");
		out.print(" ");

		// For every cell, except the first beginning, print a "+ - "
		for (int i = 1; i < size; i++) {
			out.print("+");
			out.print("-");
		}

		// Print the end "+" for first line
		out.print("+");

		// Start a new line
		out.println();

		// For every horizontal cell
		for (int j = 1; j <= size; j++) {

			// Each row each with "|"
			out.print("|");

			// For every horizontal cell
			for (int k = 1; k <= size; k++) {

				// Print one space
				// out.print(space);

				out.print(" ");

				// If the cell has a east edges, leave an opening for the east neighboring
				if (nodeGraph[j][k].hasEastEdges()) {
					out.print(" ");

				}

				// If the cell has no east edges, print a wall "|"
				else {
					out.print("|");
				}

			}

			// Start with new line
			out.println();

			// Start with the new Line with star
			out.print("+");

			// For every horizontal edges looks like "+ - +"
			for (int p = 1; p <= size; p++) {

				// If the cell contains south Edges
				if (nodeGraph[j][p].hasSouthEdges()) {

					// Print empty space or no walls
					out.print(" ");

				}

				// If the cell is exist cell, print empty space too
				else if (j == size && p == size) {
					out.print(" ");
				}

				// If the cell contains no south neighbors and not last cell, print wall
				else {
					out.print("-");
				}

				// Print the end " +"
			//	out.print(space);
				out.print("+");
			}
			out.println();
		}

		out.println();
		out.println();
		out.close();

	}

	/**
	 * Add edged between two cells
	 * 
	 * @param x1
	 *            row position of Cell 1
	 * @param y1
	 *            column of Cell 1
	 * @param x2
	 *            row of Cell 2
	 * @param y2
	 *            column of Cell 2
	 * @return true if two cells are neighbors, else return false
	 */
	public boolean addEdge(int x1, int y1, int x2, int y2) {
		int key = 0; // Key is the position of Node2 respect Node1
		int key2 = 0; // Key is the position of Node1 respect to Node2

		// get both nodes from 2D array
		if (x2 == x1 && y2 == y1 + 1) {

			// To the right (east), key value of 3
			key = 3;
			key2 = 4;
		} else if (x2 == x1 && y2 == y1 - 1) {

			// To the left (west), key value of 4
			key = 4;
			key2 = 3;
		} else if (y2 == y1 && x2 == x1 - 1) {
			// Above (north), key value of 1
			key = 1;
			key2 = 2;
		} else if (y2 == y1 && x2 == x1 + 1) {
			// Below (south), key value of 2
			key = 2;
			key2 = 1;
		} else {
			// Coordinates are invalid
			return false;
		}

		// node1.addEdge(node2) and node2 addEdge(node1)
		return nodeGraph[x1][y1].addEdge(nodeGraph[x2][y2], key) && nodeGraph[x2][y2].addEdge(nodeGraph[x1][y1], key2);
	}

	/**
	 * Get all nodes have edges with given node
	 * 
	 * @param currNode
	 *            input Node
	 * @return neighbors arrayList of neighbors
	 */
	public ArrayList<Node<E>> getAllNeighbors(Node<E> currNode) {

		// ArrayList of neighbors or node have edges connect with currNode
		ArrayList<Node<E>> neighbors = new ArrayList<Node<E>>();

		// The X coordinate of the node
		int currX = currNode.getXCor();

		// The Y coordinate of the node
		int currY = currNode.getYCor();

		// If the South neighbor exist, add it to the neighbor list
		if (currX + 1 != nodeGraph.length) {
			neighbors.add(nodeGraph[currX + 1][currY]);
		}

		// If the East neighbor exist, add it to the neighbor list
		if (currY + 1 != nodeGraph.length) {

			neighbors.add(nodeGraph[currX][currY + 1]);
		}

		// If the North neighbor exist, add it to the neighbor list
		if (currX - 1 != 0) {
			neighbors.add(nodeGraph[currX - 1][currY]);
		}

		// If the West neighbor exist, add it to the neighbor list
		if (currY - 1 != 0) {
			neighbors.add(nodeGraph[currX][currY - 1]);
		}

		return neighbors;
	}

	/**
	 * Get Random number
	 * 
	 * @return random number
	 */
	public double myRandom() {
		return myRandGen.nextDouble();
	}

	/**
	 * Get number of node in the 2D array
	 * 
	 * @return nodeCount number of the node in the array
	 */
	public int getNodeCount() {
		return this.nodeCount;
	}

	/**
	 * Get 2D array of node
	 * 
	 * @return nodeGraph 2D array of node
	 */
	public Node<E>[][] getNodeGraph() {
		return nodeGraph;
	}

	/**
	 * Get dimension of the Maze
	 * 
	 * @return size side length of the Maze
	 */
	public int getMazeDimension() {
		return size;
	}
}