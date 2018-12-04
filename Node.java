package RanYulongUgarteEvanProject2_Maze;

import java.util.*;

/**
 * Node class
 * 
 * @author Evan and Yulong
 *
 * @param <E>
 */
public class Node<E> {

	// Position of the Node
	private int data;

	// X coordinate of the Node
	private int xCor;

	// Y coordinate of the Node
	private int yCor;

	// HashMap contains all edges of the node with their direction as key
	// North = 1, South = 2, East = 3, West = 4
	HashMap<Integer, Node<E>> edges;
	protected String color;

	// Path of the solution for the Maze
	private boolean isPath;

	// Number of steps takes for the BFS and DFS
	private int steps;

	// Parent of the node
	private Node<E> parent;

	// Constructor
	public Node(int xCor, int yCor, int data) {
		// This constructor sets the steps to -1, path to false, the node's color to
		// white,
		this.steps = -1;
		this.isPath = false;
		this.color = "WHITE";
		// Store the parameters of x and y coordinates to the node's instance variables
		// as well as the passed int data
		this.xCor = xCor;
		this.yCor = yCor;
		this.data = data;
		// When a new node is made, its neighbors are represented as an empty HashMap.
		edges = new HashMap<Integer, Node<E>>();
	}

	/**
	 * Add edge to the Node
	 * 
	 * @param n
	 *            node
	 * @param key
	 *            direction of the edge
	 * @return false if node already exist, true if successfully addEdge into
	 *         HashMap
	 */
	public boolean addEdge(Node<E> n, int key) {
		if (edges.get(key) != null) {
			return false;
		}
		edges.put(key, n);
		return true;
	}

	/**
	 * Check Node has Edges
	 * 
	 * @return true if no Edges, false otherwise
	 */
	public boolean hasAllWalls() {
		if (this.edges.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if node has NorthEdges
	 * 
	 * @return true if has NorthEdges, false no NorthEdges
	 */
	public boolean hasNorthEdges() {
		if (this.getEdges().containsKey(1)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if node has SouthEdges
	 * 
	 * @return true if has SouthEdges, false no SouthEdges
	 */
	public boolean hasSouthEdges() {
		if (this.getEdges().containsKey(2)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if node has WestEdges
	 * 
	 * @return true if has WestEdges, false no WestEdges
	 */
	public boolean hasWestEdges() {
		if (this.getEdges().containsKey(4)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if node has EastEdges
	 * 
	 * @return true if has EastEdges, false no EastEdges
	 */
	public boolean hasEastEdges() {
		if (this.getEdges().containsKey(3)) {
			return true;
		}
		return false;
	}

	/**
	 * Retrieve data held by node object
	 * 
	 * @return data of node
	 */
	public int getData() {
		return data;
	}

	/**
	 * Retrieve y coordinate of node object
	 * 
	 * @return y coordinate of node
	 */
	public int getYCor() {
		return yCor;
	}

	/**
	 * Retrieve x coordinate of node object
	 * 
	 * @return x coordinate of node
	 */
	public int getXCor() {
		return xCor;
	}

	/**
	 * Retrieve Neighbors of node object with hashmap, Key values are North = 1,
	 * South = 2, East = 3, West = 4
	 * 
	 * @return Neighbors of node
	 */
	public HashMap<Integer, Node<E>> getEdges() {
		return edges;
	}

	/**
	 * Set the color of a node
	 * 
	 * @param c,
	 *            the string for the nodes color to be set to
	 */
	public void setColor(String c) {
		this.color = c;
	}

	/**
	 * Retrieve color of node object
	 * 
	 * @return color of node
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Retrieve steps of node object
	 * 
	 * @return steps of node
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * Set the steps of a node
	 * 
	 * @param steps,
	 *            the number of steps for the node to have
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * Retrieve parent of node object
	 * 
	 * @return parent of node
	 */
	public Node<E> getParent() {
		return parent;
	}

	/**
	 * Set the steps of a node
	 * 
	 * @param p,
	 *            the parent for the node to be set to
	 */
	public void setParent(Node<E> p) {
		this.parent = p;
	}

	/**
	 * Set if the node is part of the maze's soultion
	 */
	public void setIsPath() {
		this.isPath = true;
	}

	/**
	 * Get if node is part of solution path
	 * 
	 * @return true or false if node is path.
	 */
	public boolean isPath() {
		return isPath;
	}

}