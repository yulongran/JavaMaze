package RanYulongUgarteEvanProject2_Maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MazeSolverDFS<E> {
	// MazeSolverDFS Instance Variables

	// 2D array of cells
	private Node<E>[][] nodeGraph;

	// Size of the Maze
	private int size;

	// This constructor takes in a 2D array of nodes, and the dimensions of the
	// array
	public MazeSolverDFS(Node<E>[][] nodeGraph, int size) {
		this.nodeGraph = nodeGraph;
		this.size = size;

	}

	// This method returns a node at a specific coordinate in the graph
	public Node<E> getNodeAt(int xCor, int yCor) {
		// ensure that the coordinate parameters are positive and in bounds
		if (xCor <= size && xCor <= size && xCor > 0 && yCor > 0) {
			return nodeGraph[xCor][yCor];
		}
		return null;
	}

	/**
	 * Solve Maze by DFS
	 * 
	 */
	public void SolveMazeDFS() {

		// To start DFS, we set our starting node to be the
		// node at the top left corner
		Node<E> starting = getNodeAt(1, 1);
		// This node doesn't have any parents, so we set it to null.
		starting.setParent(null);
		// This stack will hold all the nodes that we visit with DFS
		Stack<Node<E>> nodes = new Stack<Node<E>>();
		// Push our starting node to the stack
		nodes.push(starting);

		// Set the bottom left cell to be the path of solution
		getNodeAt(size, size).setIsPath();

		// This boolean variable will turn true if we reach the last cell
		boolean findLast = false;

		// Solution Steps
		int steps = 0;

		// Initialize the starting node as step 0
		starting.setSteps(steps);

		// This loop will iterate until we find the final node or empty the stack
		while (!nodes.isEmpty() && !findLast) {
			// Pop the top node off the stack
			Node<E> currNode = nodes.pop();

			// Get all the neighbors of the popped node
			ArrayList<Node<E>> neighbors = getEdges(currNode);

			// This loop iterates through all the neighbors of the popped node
			for (int i = 0; i < neighbors.size(); i++) {
				Node<E> popNeighbor = neighbors.get(i);

				// If the node is white, we set it to gray and it's parent to the popped node
				if (popNeighbor.getColor().equals("WHITE")) {
					popNeighbor.setColor("GREY");
					popNeighbor.setParent(currNode);
					// Only add the node to the stack if it wasn't visited (white)
					nodes.push(popNeighbor);
				}

				// If the node we popped at the top of the while loop had the coordinates of the
				// last neighbor, then we know we found the last one, and set findLast to true
				if (currNode.getXCor() == size && currNode.getYCor() == size) {
					findLast = true;
				}
			}
			// Set the popped node's steps to the current steps
			currNode.setSteps(steps);
			// Increment steps
			steps++;
			// Set the popped node's color to black, since all its neighbors were visited
			currNode.setColor("BLACK");

		}

		// This boolean value will let us know if we are done with specifying the
		// solution path
		boolean end = false;

		// Get the cell at the end of the maze, since we will now get the solution path
		Node<E> currNode = getNodeAt(size, size);

		// Starting from the exit cell, find the cell's parent and set the parent as the
		// solution path
		while (!end) {
			// Set currentNode the path
			currNode.getParent().setIsPath();
			// Get the current node's parent, as long as the x and y coordinates aren't that
			// of the
			// top right (entry) node in the maze
			if (!(currNode.getParent().getXCor() == 1 && currNode.getParent().getYCor() == 1)) {
				// The entry node's parent is the only node who's parent is null.
				// By reaching it we are done, and thus set end to true
				if (currNode.getParent() == null) {
					end = true;
				} else {
					currNode = currNode.getParent();
				}
			} else {
				end = true;
			}
		}

	}

	// Get all the neighbors that a Node has, based on the edges it has connect
	public ArrayList<Node<E>> getEdges(Node<E> n) {
		ArrayList<Node<E>> neighbors = new ArrayList<Node<E>>();
		// The edges of a node are stored in a hashmap, with a number corresponding to
		// North, South, East, West as the key
		HashMap<Integer, Node<E>> edges = n.getEdges();

		// If a node at a specific key exists, we add it to the array list and return it
		// North = 1, South = 2, East = 3, West = 4

		if (edges.containsKey(1)) {
			neighbors.add(edges.get(1));
		}
		if (edges.containsKey(2)) {
			neighbors.add(edges.get(2));
		}
		if (edges.containsKey(4)) {
			neighbors.add(edges.get(4));
		}
		if (edges.containsKey(3)) {
			neighbors.add(edges.get(3));
		}

		return neighbors;
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

		out.println("DFS SOLUTION WITH STEPS:");
		// Print the top left corner of the Maze with an entry point
		out.print("+");
		// print a gap of the entry point
		out.print(space);
		// System.out.print(tab);

		// Print the top line of the maze with + and - as the border
		for (int i = 1; i < size; i++) {
			out.print("+");
			out.print("-");
			//out.print(tab);
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
				//out.print(space);

				// Middle char
				if (getNodeAt(j, k).getSteps() != -1 && getNodeAt(j, k).isPath()) {
					out.print(getNodeAt(j, k).getSteps() % 10);
				} else {
					out.print(" ");
				}
				//out.print(space);

				if (getNodeAt(j, k).hasEastEdges()) {
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

				//out.print(space);
				if (getNodeAt(j, p).hasSouthEdges()) {
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
				//out.print(space);
				out.print("+");
			}
			out.println();
		}

		out.println();

		// =================================
		// BEGIN PRINTING WITH # TO FILE
		// =================================

		out.println("DFS SOLUTION WITH #:");
		// Print the top left corner of the Maze with an entry point
		out.print("+");
		// print a gap of the entry point
		out.print(space);
		// System.out.print(tab);
		// Print the top line of the maze with + and - as the border
		for (int i = 1; i < size; i++) {
			out.print("+");
			out.print("-");
			// out.print(tab);
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
				if (getNodeAt(j, k).isPath()) {
					out.print("#");
				} else {
					out.print(" ");
				}
				// out.print(space);

				// If there is an edge between nodes in a horizontal row, print #
				// else print a wall since there is no connection
				if (getNodeAt(j, k).hasEastEdges()) {
					if (getNodeAt(j, k).isPath()) {
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
				if (getNodeAt(j, p).hasSouthEdges()) {
					// if the node has an edge from north to south, print a #
					if (getNodeAt(j, p).isPath())
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