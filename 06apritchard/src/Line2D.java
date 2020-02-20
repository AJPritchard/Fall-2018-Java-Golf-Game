
/**
 * Represents a parametric form of a line (P + vt)
 * 
 * @author apritchard18
 */
public class Line2D {

	private Vector2D direction;
	private Point2D location;
	
	/**
	 * @param direction is the Vector that is responsible for the direction of this line
	 * @param location is the starting point of this line
	 */
	public Line2D(Vector2D direction, Point2D location) {
		this.direction = direction;
		this.location = location;
	}
	
	/**
	 * @param t is the scalar multiplied to direction that is supposed to resemble time passed
	 * @return the point2D of its location at that time
	 */
	public Point2D getPointAt(double t) {
		
		return (new Point2D(location.getX() + direction.scalarMultiplication(t).getX(),
				location.getY() + direction.scalarMultiplication(t).getY()));
	}
	
	/**
	 * @return the end Point2D, or Point2D at time = 1. So its the direction added to the location
	 */
	public Point2D getEndPoint() {
		
		return getPointAt(1);
	}
	
	/**
	 * @param anotherLine
	 * @return
	 * @throws ParallelException 
	 */
	public double intersectTime(Line2D anotherLine) throws ParallelException {
		
		if(direction.getX() == anotherLine.getDirection().getX() && 
				direction.getY() == anotherLine.getDirection().getY()) {
			
			throw new ParallelException("Parallel");
		}
		
		Vector2D dp = anotherLine.getCcwPerp();
		Vector2D c = new Vector2D(anotherLine.getStartPoint().getX() - location.getX(), 
				anotherLine.getStartPoint().getY() - location.getY());
			
		return (dp.dotProduct(c) / dp.dotProduct(direction));
	}
	
	public Vector2D getCcwPerp() {
		return direction.counterClockwisePerpendicular();
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public Point2D getStartPoint() {
		return location;
	}

	public void setStartPoint(Point2D location) {
		this.location = location;
	}
	
	public String toString() {
		return "Point : " + location + ", Vector : " + direction;
	}
	
	
}
