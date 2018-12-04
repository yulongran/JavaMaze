package RanYulongUgarteEvanProject2_Maze;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Junit Testing for MazeSolveBFS class
 * 
 * @author Yulong and Evan
 *
 */
class MazeSolverTest {

	// Name of the test file
	String test = "file.txt";

	@Test
	void MazeSolverDFSClassTest() {

		Maze<Integer> tester = new Maze<Integer>(4, 0);
		tester.generateMazeDFS();
		MazeSolverDFS example = new MazeSolverDFS(tester.getNodeGraph(), 4);
		// Make sure that the class can return a node at coordinates that are in bounds,
		// and null if out of bounds.
		assertNotNull(example.getNodeAt(1, 1));
		assertNull(example.getNodeAt(0, 0));
		assertNull(example.getNodeAt(100, 100));
		// Show that sending coordinates for a specific node will return the node at the
		// specific coodrinate.
		assertEquals(example.getNodeAt(2, 1).getXCor(), 2);
		assertEquals(example.getNodeAt(2, 1).getYCor(), 1);
	}

	@Test
	// Solve Maze with size 4,6,8
	void SolveMazeDFStest1() throws IOException {

		// Refresh the file each time running program
		try {
			PrintWriter writer = new PrintWriter(test);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
		}

		// =======================================
		// MAZE DIMENSION OF 4 WITH RANDOM SEED 0
		// =======================================

		// Construct a Maze with Size 4
		Maze<Integer> tester = new Maze<Integer>(4, 0);
		tester.generateMazeDFS();

		// Construct a MazeSolverBFS tester
		MazeSolverBFS solveTester1 = new MazeSolverBFS(tester.getNodeGraph(), 4);

		// Solve the Maze by BFS
		solveTester1.SolveMazeBFS();

		// Print the Maze
		try {
			tester.outPutMazeToFile(test);
		} catch (Exception e) {
		}
		// Print the Maze with BFS
		try {
			solveTester1.printSolutionToFile(test);
		} catch (Exception e) {
		}

		Maze<Integer> copy = new Maze<Integer>(4, 0);
		copy.generateMazeDFS();
		// Construct a MazeSolverDFS tester
		MazeSolverDFS solveTester = new MazeSolverDFS(copy.getNodeGraph(), 4);

		// Solve the Maze by DFS
		solveTester.SolveMazeDFS();

		// // Print the Maze with DFS
		try {
			solveTester.printSolutionToFile(test);
		} catch (Exception e) {
		}

		// =======================================
		// MAZE DIMENSION OF 6 WITH RANDOM SEED 0
		// =======================================

		// Construct a Maze with Size 6
		Maze<Integer> testerSize6 = new Maze<Integer>(6, 0);
		testerSize6.generateMazeDFS();

		// Construct a MazeSolverBFS tester
		MazeSolverBFS solveTesterSize6 = new MazeSolverBFS(testerSize6.getNodeGraph(), 6);

		// Solve the Maze by BFS
		solveTesterSize6.SolveMazeBFS();

		// Print the Maze
		try {
			testerSize6.outPutMazeToFile(test);
		} catch (Exception e) {
		}

		// Print the Maze with BFS
		try {
			solveTesterSize6.printSolutionToFile(test);
		} catch (Exception e) {
		}

		Maze<Integer> copy2 = new Maze<Integer>(6, 0);
		copy2.generateMazeDFS();
		// Construct a MazeSolverDFS tester
		MazeSolverDFS solveTesterSize62 = new MazeSolverDFS(copy2.getNodeGraph(), 6);

		// Solve the Maze by DFS
		solveTesterSize62.SolveMazeDFS();

		// // Print the Maze with DFS
		try {
			solveTesterSize62.printSolutionToFile(test);
		} catch (Exception e) {
		}

		// =======================================
		// MAZE DIMENSION OF 8 WITH RANDOM SEED 0
		// =======================================

		// Construct a Maze with Size 8
		Maze<Integer> testerSize8 = new Maze<Integer>(8, 0);

		testerSize8.generateMazeDFS();

		// Construct a MazeSolverBFS tester
		MazeSolverBFS solveTesterSize8 = new MazeSolverBFS(testerSize8.getNodeGraph(), 8);

		// Solve the Maze by BFS
		solveTesterSize8.SolveMazeBFS();

		// Print the Maze
		try {
			testerSize8.outPutMazeToFile(test);
		} catch (Exception e) {
		}

		// Print the Maze with BFS
		try {
			solveTesterSize8.printSolutionToFile(test);
		} catch (Exception e) {
		}

		Maze<Integer> copy3 = new Maze<Integer>(8, 0);

		copy3.generateMazeDFS();
		// Construct a MazeSolverDFS tester
		MazeSolverDFS solveTesterSize82 = new MazeSolverDFS(copy3.getNodeGraph(), 8);

		// Solve the Maze by DFS
		solveTesterSize82.SolveMazeDFS();

		// // Print the Maze with DFS
		try {
			solveTesterSize82.printSolutionToFile(test);
		} catch (Exception e) {
		}
	}

	@Test
	<E> // Test method of getEdges
	// North = 1, South = 2, East = 3, West = 4
	// getEdges add the node in the order of South East North and West
	void getEdgesTest() {
		// Construct a tester node with x coordinate =1 , y coordinate=1 and data =5
		Node<E> tester = new Node<E>(1, 1, 5);

		// Construct a North Neighbor of the tester node with x coordinate =0 , y
		// coordinate=1 and data =3
		Node<E> testerN = new Node<E>(0, 1, 3);

		// Construct a West Neighbor of the tester node with x coordinate =1 , y
		// coordinate=0 and data = 4
		Node<E> testerW = new Node<E>(1, 0, 4);

		// Add North node as edges of tester
		tester.addEdge(testerN, 1);

		// Add South node as edges of tester
		tester.addEdge(testerW, 4);

		// Construct a Maze
		Maze<Integer> t = new Maze<Integer>(4, 0);

		// Generate the 2D array
		t.generateMazeDFS();

		// Generate a Maze Solver
		MazeSolverBFS m = new MazeSolverBFS(t.getNodeGraph(), 4);

		// ArrayList for Testing
		ArrayList<Node<E>> testList = m.getEdges(tester);

		// Test the ArrayList has the size of two
		assertEquals(2, testList.size());

		// Test the first Node has x coordinate of 0
		assertEquals(0, testList.get(0).getXCor());

		// Test the first Node has data of 3
		assertEquals(3, testList.get(0).getData());

		// Test the second Node has data of 4
		assertEquals(4, testList.get(1).getData());
	}

	/**
	 * Compare output file with the test file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	void fileOutPutTest() throws FileNotFoundException, IOException {

		String testFile = "testFile.txt"; // Real file path

		BufferedReader Out = new BufferedReader(new FileReader("file.txt")); // My compute file

		BufferedReader In = new BufferedReader(new FileReader("testFile.txt")); // Test File

		String expectedLine = "";

		while ((expectedLine = In.readLine()) != null) {
			String actualLine = Out.readLine();
			assertEquals(expectedLine, actualLine);
		}

		In.close();
		Out.close();
	}
}
