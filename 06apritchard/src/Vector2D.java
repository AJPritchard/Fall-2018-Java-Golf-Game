
/**
 * Represents a Vector (x,y)
 * 
 * @author apritchard18
 */
public class Vector2D {

	private double x; 
	private double y;

	/**
	 * @param x is the x location of the vector
	 * @param y is the y location of the vector
	 */
	public Vector2D(double x, double y) {

		this.x = x;
		this.y = y;
	}
	
	/**
	 * Finds both the x and y of the vector in this way
	 * x = p1.getX() - p2.getX();
	 * @param p1 is the first point
	 * @param p2 is the second point
	 */
	public Vector2D(Point2D p1, Point2D p2) {
		
		this.x = p2.getX() - p1.getX();
		this.y = p2.getY() - p1.getY();
	}

	/**
	 * @param other is a new Vector
	 * @return a new vector whose x's and y's are added together
	 */
	public Vector2D vectorAddition(Vector2D other) {

//		Vector2D solution = new Vector2D(x + other.getX(), y + other.getY());
//		return solution;

		return (new Vector2D(x + other.getX(), y + other.getY()));
	}

	/**
	 * @param other is the vector that being subtracted by the other one
	 * @return the new vector whose x is subtracted from the other x and whose y is
	 *         subtracted from other y
	 */
	public Vector2D vectorSubtraction(Vector2D other) {

//		Vector2D solution = new Vector2D(x - other.getX(), y - other.getY());
//		return solution;

		return (new Vector2D(x - other.getX(), y - other.getY()));
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

	/**
	 * toString returns (x, y)
	 */
	public String toString() {
		return "v = " + x + "i" + " + " + y + "j";
	}

	/**
	 * @param scalar is the double that multiplies x and y
	 */
	public Vector2D scalarMultiplication(double scalar) {

//		Vector2D solution = new Vector2D(x * scalar, y * scalar);
//    	return solution;

		return (new Vector2D(x * scalar, y * scalar));
	}

	/**
	 * @param other is the other Vector that is multiplying against this one
	 * @return a new vector that is the dot product of this one and other
	 */
	public double dotProduct(Vector2D other) {

		return x * other.getX() + y * other.getY();
	}

	/**
	 * @return the magnitude of the vector sqrt(x^2 + y^2)
	 */
	public double size() {

		return Math.sqrt((x * x) + (y * y));
	}

	/**
	 * @return the unit vector of this vector which is x / magnitude and y /
	 *         magnitude
	 */
	public Vector2D unitVector() {

//		Vector2D solution = new Vector2D(x / size(), y / size());
//		return solution;

		return (new Vector2D(x / size(), y / size()));
	}

	
	/**
	 * @return a perpendicular vector in the clockwise direction
	 */
	public Vector2D clockwisePerpendicular() {
		
		return (new Vector2D(y, -x));
	}
	
	/**
	 * @return a perpendicular vector in the counter clockwise direction
	 */
	public Vector2D counterClockwisePerpendicular() {
		
		return (new Vector2D(-y, x));
	}
}
