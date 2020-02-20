import static org.junit.Assert.*;

import org.junit.Test;

public class Line2DTest {

	@Test
	public void testIntitialization() {
		double x = 2;
		double y = 4;
		
		Point2D point = new Point2D(x, y);
		Vector2D vect = new Vector2D(x, y);
		
		Line2D line = new Line2D(vect, point);
		
		assertEquals(vect.getX(), line.getDirection().getX(), 0.0001);
		assertEquals(y, line.getStartPoint().getY(), 0.0001);
	}
	
	@Test
	public void testGetPointAt() {
		double x = 2;
		double y = 4;
		double t = 0.5;
		
		Point2D point = new Point2D(x, y);
		Vector2D vect = new Vector2D(x, y);
		
		Line2D line = new Line2D(vect, point);
		
		assertEquals(3, line.getPointAt(t).getX(), 0.0001);
		assertEquals(6, line.getPointAt(t).getY(), 0.0001);
	}

	@Test
	public void testGetIntersectAt1() {
		// t = 1
		double x1 = 3;
		double y1 = 3;
		double x2 = 3;
		double y2 = -3;
		
		Point2D point1 = new Point2D(0, 0);
		Vector2D vect1 = new Vector2D(x1, y1);
		
		Line2D line1 = new Line2D(vect1, point1); 
		
		Point2D point2 = new Point2D(3, 3);
		Vector2D vect2 = new Vector2D(x2, y2);
		
		Line2D line2 = new Line2D(vect2, point2);
		
		try {
			assertEquals(1, line1.intersectTime(line2), 0.0001);
		} catch (ParallelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetIntersectAt2() {
		// t larger than 1
		double x1 = 7;
		double y1 = -4;
		double x2 = 3;
		double y2 = -3;
		
		Point2D point1 = new Point2D(0, 0);
		Vector2D vect1 = new Vector2D(x1, y1);
		
		Line2D line1 = new Line2D(vect1, point1); 
		
		Point2D point2 = new Point2D(3, 3);
		Vector2D vect2 = new Vector2D(x2, y2);
		
		Line2D line2 = new Line2D(vect2, point2);
		
		try {
			assertEquals(2, line1.intersectTime(line2), 0.0001);
		} catch (ParallelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetIntersectAt3() {
		// t less than 1
		double x1 = 8;
		double y1 = 0;
		double x2 = 3;
		double y2 = -3;
		
		Point2D point1 = new Point2D(0, 0);
		Vector2D vect1 = new Vector2D(x1, y1);
		
		Line2D line1 = new Line2D(vect1, point1); 
		
		Point2D point2 = new Point2D(1, 0);
		Vector2D vect2 = new Vector2D(x2, y2);
		
		Line2D line2 = new Line2D(vect2, point2);
		
		try {
			assertEquals(0.125, line1.intersectTime(line2), 0.0001);
		} catch (ParallelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
