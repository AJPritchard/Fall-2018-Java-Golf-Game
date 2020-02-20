import static org.junit.Assert.*;

import org.junit.Test;

public class Point2DTest {

	@Test
	public void testInitialization() {
		double x = 2;
		double y = 4;
		Point2D point = new Point2D(x, y);
		
		assertEquals(x, point.getX(), 0.0001);
		assertEquals(y, point.getY(), 0.0001);
	}
	
	@Test 
	public void testSubtract() {
		double x1 = 2;
		double y1 = 4;
		double x2 = 3;
		double y2 = 5;
				
		Point2D point1 = new Point2D(x1, y1);
		Point2D point2 = new Point2D(x2, y2);
		
		assertEquals(x1 - x2, point1.subtract(point2).getX(), 0.0001);
		assertEquals(y1 - y2, point1.subtract(point2).getY(), 0.0001);

		assertEquals(x2 - x1, point2.subtract(point1).getX(), 0.0001);
		assertEquals(y2 - y1, point2.subtract(point1).getY(), 0.0001);
	}
	
	@Test 
	public void testAdd() {
		double x1 = 2;
		double y1 = 4;
		double x2 = 3;
		double y2 = 5;
				
		Point2D point1 = new Point2D(x1, y1);
		Vector2D point2 = new Vector2D(x2, y2);
		
		assertEquals(x1 + x2, point1.add(point2).getX(), 0.0001);
		assertEquals(y1 + y2, point1.add(point2).getY(), 0.0001);

	}

}
