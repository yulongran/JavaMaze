package RanYulongUgarteEvanProject2_Maze;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Maze program runner
 * 
 * @author yulongran
 *
 */
public class NodeTest {
	@Test
	public void test() {
		Node<Integer> testNode = new Node<Integer>(1, 2, 10);
		// Show Constructor set the data to 10, x coordinate to 1, and y coordinate to 2
		assertEquals(testNode.getData(), 10);
		assertEquals(testNode.getXCor(), 1);
		assertEquals(testNode.getYCor(), 2);

		// Show that steps default to -1, and can be set
		assertEquals(testNode.getSteps(), -1);
		testNode.setSteps(3);
		assertEquals(testNode.getSteps(), 3);

		// Show that path boolean defaults to false, and can be set to true
		assertFalse(testNode.isPath());
		testNode.setIsPath();
		assertTrue(testNode.isPath());

		assertEquals(testNode.getColor(), "WHITE");
		testNode.setColor("BLACK");

		assertEquals(testNode.getColor(), "BLACK");
		// Introduce a secondary node and add an edge between both nodes
		Node<Integer> secondaryNode = new Node<Integer>(1, 3, 10);
		// Assert that the testNode has all walls
		assertTrue(testNode.hasAllWalls());
		// Add a wall, then assert that the testNode has walls
		testNode.addEdge(secondaryNode, 2);
		assertFalse(testNode.hasAllWalls());
	}

}
