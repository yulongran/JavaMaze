package RanYulongUgarteEvanProject2_Maze;

import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Maze program runner
// * @author yulongran
// *
// */
//public class MazeTest{
//    public static void main(String[] args){
//        Maze<Integer> maze = new Maze<Integer>(5);
//        MazeSolverBFS BFS = new MazeSolverBFS(maze.getNodeGraph(), maze.size);
//       
//      //  maze.print();
//        maze.DFS();
//        
//        System.out.println("Maze :" );
//        System.out.println();
//        maze.printMaze();
//        
//        //Solve the Maze with DFS
//        BFS.SolveMazeBFS();
//        
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("BFS Solution: ");
//        System.out.println();
//        BFS.printSolution();
//        
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("BFS Solution with Steps: ");
//        System.out.println();
//        BFS.printSolutionWithSteps();
//     
//    }
//    
//}
//

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * JUnit testing for the Maze class
 * 
 * @author Yulong and Evan
 *
 * @param <E>
 */
public class MazeBuilderTest {

	@Test
	// Testing if the Maze is successfully generated and printed
	<E> void GenerateTest() {
		Maze<E> tester = new Maze<E>(4);
		tester.generateMazeDFS();
		tester.printMaze();
		
	}

	@Test
	<E> // JUnit test for addEdge
	void addEdgeTest() {
		// Generate a Maze with dimension 4
		Maze<E> tester = new Maze<E>(4, 0);

		// Add non existence neighbor, expected false
		assertFalse(tester.addEdge(2, 2, -20, 0));

		// Add existence neighbor, expected true
		// Test case- addEdge to the South Neighbor
		assertFalse(!tester.addEdge(2, 2, 2, 1));
		
		// Test if there exist a West edges between Nodes[2][2]
		assertFalse(!tester.nodeGraph[2][2].hasWestEdges());
		
		//Test if there exist a West edges between Nodes[1][2], expected false
		assertFalse(tester.nodeGraph[1][2].hasWestEdges());
		
		// Test if Node[2][2] has a East Edge, expected false
		assertFalse(tester.nodeGraph[2][2].hasEastEdges());

	}
	
	@Test
	<E> //Junit test for getAallNeighbors method
	//Put in order of South East, North and West
	void getAllNeighborsTest()
	{
		// Construct a Maze with dimension 4
		Maze<E> tester = new Maze<E>(4,0);
		
		// Assign cell with x=1 and y=1 to the tester node
		Node<E> t= tester.getNodeGraph()[1][1];
		
		// List of neighbors for test node
		ArrayList<Node<E>> a= tester.getAllNeighbors(t);
		
		// Test node is (1,1); therefore, only have two neighbors
		assertEquals(2, a.size());
		
		// First element in the list should be South neighbor, expected xCoord=2
		assertEquals(2, a.get(0).getXCor());
		
		// Second element in the list should be East neighbor, expected yCoord=2
		assertEquals(2,a.get(1).getYCor());

	}

}
