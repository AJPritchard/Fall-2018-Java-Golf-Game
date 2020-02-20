import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class Polygon2DTest {

	@Test
	public void testInitialization() {
		Polygon2D poly = new Polygon2D();
		
		poly.addPoint(new Point2D(0, 1));
	}
	
	@Test
	public void testAdd() {
		Point2D point = new Point2D(1, 2);
		
		Polygon2D poly = new Polygon2D();

		poly.addPoint(point);
	}
	
	@Test
	public void testGet() {
		Point2D point = new Point2D(1, 2);
		
		Polygon2D poly = new Polygon2D();

		poly.addPoint(point);
		
		assertEquals(1, poly.getPoint(0).getX(), 0.0001);
		assertEquals(2, poly.getPoint(0).getY(), 0.0001);
	}
	
	@Test
	public void testGetEdges() {
		Point2D point0 = new Point2D(0, 0);
		Point2D point1 = new Point2D(2, 0);
		Point2D point2 = new Point2D(2, 2);
		Point2D point3 = new Point2D(0, 2);
		
		Polygon2D poly = new Polygon2D();
		
		Line2D line = new Line2D(new Vector2D(point0, point1), point0);

		poly.addPoint(point0);
		poly.addPoint(point1);
		poly.addPoint(point2);
		poly.addPoint(point3);
		poly.addPoint(point0);

		LinkedList<Line2D> lines = poly.getEdges();
		
		assertEquals(line.getStartPoint().getX(), lines.getFirst().getStartPoint().getX(), 0.0001);	
		
	}
	
	

}
