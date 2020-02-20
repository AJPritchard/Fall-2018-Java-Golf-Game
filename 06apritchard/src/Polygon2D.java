import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.LinkedList;

/**
 * Holds the points for and draws on the polygon for the graphics
 * 
 * @author apritchard18
 */
public class Polygon2D {

	LinkedList<Point2D> definingPoints;

	/**
	 * Initializes the linkedlist
	 */
	public Polygon2D() {
		definingPoints = new LinkedList<Point2D>();
	}

	/**
	 * @param newPoint the next point to be added. gets added to the end
	 */
	public void addPoint(Point2D newPoint) {
		definingPoints.add(newPoint);
	}

	/**
	 * @param pos is the position in the linkedlist that you want
	 */
	public Point2D getPoint(int pos) {
		return definingPoints.get(pos);
	}

	/**
	 * @return a linkedlist of line2D objects that keep the boundaries of the
	 *         polygon
	 */
	public LinkedList<Line2D> getEdges() {

		LinkedList<Line2D> edges = new LinkedList<Line2D>();

		for (int i = 0; i < definingPoints.size() - 1; i++) {
			edges.add(new Line2D((new Vector2D(getPoint(i), getPoint(i + 1))), getPoint(i)));
		}

		return edges;
	}

	/**
	 * Draws the Polygon object
	 * 
	 * @param g      is a graphics object
	 * @param filled if true fills the polygon
	 * @param c      is the color you want the polygon
	 */
	public void drawOn(Graphics g, boolean filled, Color c) {
		Polygon poly = new Polygon();

		for (Point2D point : definingPoints) {
			poly.addPoint((int) point.getX(), (int) point.getY());
		}

		g.setColor(c);

		if (filled)
			g.fillPolygon(poly);
		else
			g.drawPolygon(poly);
	}

	/**
	 * I am using this to dynamically change the boundaries on my JPanels between holes depending on
	 * their sizes
	 * @return Width of the Polygon
	 */
	public double getWidth() {
		double maxx = definingPoints.getFirst().getX();
		double minx = definingPoints.getFirst().getX();
		
		for(Point2D x : definingPoints) {
			if(x.getX() > maxx) {
				maxx = x.getX();
			}
			if(x.getX() < minx) {
				minx = x.getX();
			}
		}
		
		return maxx - minx;
	}
	
	/**
	 * I am using this to dynamically change the boundaries on my JPanels between holes depending on
	 * their sizes
	 * @return Height of the Polygon
	 */
	public double getHeight() {
		double maxy = definingPoints.getFirst().getY();
		double miny = definingPoints.getFirst().getY();
		
		for(Point2D y : definingPoints) {
			if(y.getX() > maxy) {
				maxy = y.getY();
			}
			if(y.getX() < miny) {
				miny = y.getY();
			}
		}
		
		return maxy - miny;
	}
	
	/**
	 * @return returns every point in the list in a string
	 */
	public String toString() {
		String str = "";

		for (int i = 0; i < definingPoints.size() - 1; i++) {
			str += getPoint(i) + " | ";
		}

		str += definingPoints.getLast();

		return str;
	}
}
