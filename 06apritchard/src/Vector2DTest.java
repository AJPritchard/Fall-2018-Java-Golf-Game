import static org.junit.Assert.*;

import org.junit.Test;

public class Vector2DTest {

	@Test
	public void testInitialization() {
		double x = 2;
		double y = 4;
		Vector2D vect = new Vector2D(x, y);

		assertEquals(x, vect.getX(), 0.00001);
		assertEquals(y, vect.getY(), 0.00001);

		double newX = 3;
		double newY = 5;

		vect.setX(newX);
		vect.setY(newY);

		assertEquals(newX, vect.getX(), 0.00001);
		assertEquals(newY, vect.getY(), 0.00001);
	}
	
	@Test 
	public void testInitilization2() {
		// Tests second constructor
		
		Point2D point1 = new Point2D(-1, -1);
		Point2D point2 = new Point2D(2, 1);
		
		Vector2D vect = new Vector2D(point1, point2);
		
		assertEquals(2 - -1, vect.getX(), 0.0001);
	}

	@Test
	public void testAddition() {
		double x1 = 3;
		double y1 = 5;
		double x2 = 2;
		double y2 = 4;

		Vector2D vect1 = new Vector2D(x1, y1);
		Vector2D vect2 = new Vector2D(x2, y2);

		assertEquals(x1 + x2, vect1.vectorAddition(vect2).getX(), 0.00001);
		assertEquals(y1 + y2, vect1.vectorAddition(vect2).getY(), 0.00001);

	}

	@Test
	public void testSubtraction() {
		double x1 = 3;
		double y1 = 5;
		double x2 = 2;
		double y2 = 4;

		Vector2D vect1 = new Vector2D(x1, y1);
		Vector2D vect2 = new Vector2D(x2, y2);

		assertEquals(x1 - x2, vect1.vectorSubtraction(vect2).getX(), 0.00001);
		assertEquals(y1 - y2, vect1.vectorSubtraction(vect2).getY(), 0.00001);

	}

	@Test
	public void testScalarMultiplication() {
		double x = 2;
		double y = 4;
		double scalar = 5;

		Vector2D vect = new Vector2D(x, y);
		
		assertEquals(x * scalar, vect.scalarMultiplication(scalar).getX(), 0.00001);
		assertEquals(y * scalar, vect.scalarMultiplication(scalar).getY(), 0.00001);

	}

	@Test
	public void testDotProduct() {
		double x1 = 3;
		double y1 = 5;
		double x2 = 2;
		double y2 = 4;

		Vector2D vect1 = new Vector2D(x1, y1);
		Vector2D vect2 = new Vector2D(x2, y2);

		assertEquals(x1 * x2 + y1 * y2, vect1.dotProduct(vect2), 0.00001);

	}
	
	@Test
	public void testSize() {
		double x = 2;
		double y = 4;

		Vector2D vect = new Vector2D(x, y);
		
		assertEquals(Math.sqrt(x * x + y * y), vect.size(), 0.00001);
	}
	
	@Test
	public void testUnitVector() {
		double x = 2;
		double y = 4;

		Vector2D vect = new Vector2D(x, y);
		
		assertEquals(x / Math.sqrt(x * x + y * y), vect.unitVector().getX(), 0.00001);
		assertEquals(y / Math.sqrt(x * x + y * y), vect.unitVector().getY(), 0.00001);

	}
	
	@Test
	public void testClockwisePerpendicularVector() {
		double x = 2;
		double y = 4;

		Vector2D vect = new Vector2D(x, y);
		
		assertEquals(y, vect.clockwisePerpendicular().getX(), 0.00001);
		assertEquals(-x, vect.clockwisePerpendicular().getY(), 0.00001);

	}
	
	@Test
	public void testCounterClockwisePerpendicularVector() {
		double x = 2;
		double y = 4;

		Vector2D vect = new Vector2D(x, y);
		
		assertEquals(-y, vect.counterClockwisePerpendicular().getX(), 0.00001);
		assertEquals(x, vect.counterClockwisePerpendicular().getY(), 0.00001);

	}

}
