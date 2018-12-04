package RanYulongUgarteEvanProject2_Maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Solve Maze by BFS
 * 
 * @author Yulong and Evan
 *
 * @param <E>
 */
public class MazeSolverBFS<E> {
	// 2D array of cells
	private Node<E>[][] nodeGraph;

	// Size of the Maze
	private int size;

	/**
	 * Construct a MazeSolver
	 * 
	 * @param nodeGraph
	 *            2D array of nodes structure is Maze
	 * @param size
	 *            dimension of the Maze
	 */
	public MazeSolverBFS(Node<E>[][] nodeGraph, int size) {
		this.nodeGraph = nodeGraph;
		this.size = size;

	}

	/**
	 * Solve Maze by BFS
	 * 
	 */
	public void SolveMazeBFS() {

		// Starting node always set to first cell
		Node<E> starting = nodeGraph[1][1];

		// Beginning cell has no parent
		starting.setParent(null);

		// Empty Queue for DFS
		Queue<Node<E>> q = new LinkedList<Node<E>>();

		// Add first cell to the Queue
		q.add(starting);

		// Set last cell be the path of solution
		nodeGraph[size][size].setIsPath();

		// Reach the last cell or exit of the maze
		boolean findLast = false;

		// Solution Steps
		int steps = 0;

		// Initialize the starting node as step 0
		starting.setSteps(steps);

		// DFS Looping when Queue is not empty and not yet find the exist cell
		while (!q.isEmpty() && !findLast) {

			// Pop one cell from the Queue and set to U
			Node<E> u = q.poll();

			// For every neighbors of cell u
			for (int i = 0; i < getEdges(u).size(); i++) {
				// Get one neighbors of u and assign it to the Node v
				Node<E> v = getEdges(u).get(i);
				if (!findLast) {

					// If this cell have not visited
					if (v.getColor().equals("WHITE")) {

						// Change color to Grey
						v.setColor("GREY");

						// Increment steps
						steps++;

						// Set parent of v to u
						v.setParent(u);

						// Set steps
						v.setSteps(steps);

						/// Add v to the Queue
						q.add(v);
					}

				}
				// If reach the exist cell, terminate the DFS
				if (v.getXCor() == size && v.getYCor() == size) {
					findLast = true;
				}

			}

			// Set u to color black; U have full explored
			u.setColor("BLACK");

		}

		// Reaches the beginning cell
		boolean end = false;

		// Starting from the exist cell
		Node<E> traverse = nodeGraph[size][size];

		// Traverse from the exit cell, finding its parent and set them as solution path
		while (!end) {

			// Set the parent of the traverse cell to IsPath
			traverse.getParent().setIsPath();

			// If not reach the beginning cell
			if (!(traverse.getParent().getXCor() == 1 && traverse.getParent().getYCor() == 1)) {
				// If the parent of the current cell is null
				if (traverse.getParent() == null) {

					// Reach the beginning cell
					end = true;
				} else {

					// Change the traverse cell to its parent and continue traversing
					traverse = traverse.getParent();
				}
			}
			// Reach the beginning cell
			else {

				// Terminate the loop
				end = true;
			}
		}

	}

	/**
	 * Get all neighbors that have all walls up or get Node that have edges connect
	 * to n
	 * 
	 * @param n
	 *            A cell of the Maze
	 * @return An array list contains neighbors that have edges connect to the cell
	 */
	public ArrayList<Node<E>> getEdges(Node<E> n) {

		// Array list contains all neighbors connect by an edges
		ArrayList<Node<E>> l = new ArrayList<Node<E>>();

		// Hash map contains all neighbors connect by an edges
		HashMap<Integer, Node<E>> edges = n.getEdges();

		// If the hash map contains a South edges, add it to the array list
		if (edges.containsKey(2)) {
			l.add(edges.get(2));
		}

		// If the hash map contains a East edges, add it to the array list
		if (edges.containsKey(3)) {
			l.add(edges.get(3));

			// If the hash map contains a North edges, add it to the array list
		}
		if (edges.containsKey(1)) {
			l.add(edges.get(1));
		}

		// If the hash map contains a West edges, add it to the array list
		if (edges.containsKey(4)) {
			l.add(edges.get(4));
		}

		return l;
	}

	// Print Solution of the Maze with the path represented as #
	public void printSolutionToFile(String filename) throws IOException {
		File test = new File(filename);

		PrintWriter out = new PrintWriter(new FileWriter(filename, true));
		String space = " "; // one space
		String tab = "	"; // a smaller space, named as tab

		// =================================
		// BEGIN PRINTING WITH STEPS TO FILE
		// =================================

		out.println("BFS SOLUTION WITH STEPS:");
		// Print the top left corner of the Maze with an entry point
		out.print("+");
		// print a gap of the entry point
		out.print(" ");
		// out.print(tab);
		// System.out.print(tab);

		// Print the top line of the maze with + and - as the border
		for (int i = 1; i < size; i++) {
			out.print("+");
			out.print("-");
		}
		// Print the top right corner of the Maze
		out.print("+");
		out.println();

		// This loop prints each row, with a gap in the maze or a # if the node is in
		// the path
		for (int j = 1; j <= size; j++) {
			// Print border of left maze
			out.print("|");
			for (int k = 1; k <= size; k++) {
				// Print a gap from the border to the node's contents
				// out.print(space);

				// Middle char
				if (nodeGraph[j][k].getSteps() != -1) {
					out.print(nodeGraph[j][k].getSteps() % 10);
				} else {
					out.print(" ");
				}
				// out.print(space);

				if (nodeGraph[j][k].hasEastEdges()) {
					out.print(" ");

				} else {
					out.print("|");
				}

			}
			// Print a new line for the next row
			out.println();
			// print left edge of the row with a +
			out.print("+");
			for (int p = 1; p <= size; p++) {

				// out.print(space);
				if (nodeGraph[j][p].hasSouthEdges()) {
					// if the node has an edge from north to south, print a #
					out.print(" ");

				} else if (j == size && p == size) {
					// if this is the bottom right node then print a space
					out.print(" ");
				} else {
					// print a horizontal wall if no connection
					out.print("-");
				}
				// print left edge of row
				// out.print(space);
				out.print("+");
			}
			out.println();

		}
		out.println();

		// =================================
		// BEGIN PRINTING WITH # TO FILE
		// =================================

		out.println("BFS SOLUTION WITH #:");
		// Print the top left corner of the Maze with an entry point
		out.print("+");
		// print a gap of the entry point
		out.print(space);
		// System.out.print(tab);
		// Print the top line of the maze with + and - as the border
		for (int i = 1; i < size; i++) {
			out.print("+");
			out.print("-");

		}
		// Print the top right corner of the Maze
		out.print("+");
		out.println();

		// This loop prints each row, with a gap in the maze or a # if the node is in
		// the path
		for (int j = 1; j <= size; j++) {
			// Print border of left maze
			out.print("|");
			for (int k = 1; k <= size; k++) {
				// Print a gap from the border to the node's contents
				// out.print(space);

				// If the node is in the path print a # or an empty area
				if (nodeGraph[j][k].isPath()) {
					out.print("#");
				} else {
					out.print(" ");
				}
				// out.print(space);

				// If there is an edge between nodes in a horizontal row, print #
				// else print a wall since there is no connection
				if (nodeGraph[j][k].hasEastEdges()) {
					if (nodeGraph[j][k].isPath()) {
						out.print("#");
					} else {
						out.print(space);
					}
				} else {
					out.print("|");
				}

			}
			// Print a new line for the next row
			out.println();
			// print left edge of the row with a +
			out.print("+");
			for (int p = 1; p <= size; p++) {

				// out.print(space);
				if (nodeGraph[j][p].hasSouthEdges()) {
					// if the node has an edge from north to south, print a #
					if (nodeGraph[j][p].isPath())
						out.print("#");
					else
						out.print(space);

				} else if (j == size && p == size) {
					// if this is the bottom right node then print a space
					out.print(" ");
				} else {
					// print a horizontal wall if no connection
					out.print("-");
				}
				// print left edge of row
				// out.print(space);
				out.print("+");
			}
			out.println();
		}
		out.println();
		out.println();
		out.close();
	}
}