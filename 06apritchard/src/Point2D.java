
/**
 * Represents the points (x,y)
 * 
 * @author apritchard18
 */
public class Point2D {

	private double x;
	private double y;

	/**
	 * Default Constructor, initializes the fields x and y
	 * 
	 * @param x location
	 * @param y location
	 */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param other is a the Point2D that you are subtracting from this one
	 * @return a new Vector2D created from the difference of these two points
	 */
	public Vector2D subtract(Point2D other) {

//		Vector2D solution = new Vector2D(x - other.getX(), y - other.getY()));
//		return solution;

		return (new Vector2D(x - other.getX(), y - other.getY()));
	}

	/**
	 * @param displacement is the amount that you are adding to this point2D
	 * @return a new point2D that is created from the sum of this ones points and the Vector2D's
	 */
	public Point2D add(Vector2D displacement) {
		
//		Point2D solution = new Point2DD(x + displacement.getX(), y + displacement.getY()));
//		return solution;
		
		return (new Point2D(x + displacement.getX(), y + displacement.getY()));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

}
