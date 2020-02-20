import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents the golfball
 * 
 * @author apritchard18
 *
 */
public class Ball {

	private static final int MAX_VELOCITY = 25;
	private Point2D lastPosition;
	private Point2D position;
	private Vector2D velocity;
	private Point2D originalPosition;
	public static final double DECELERATION = 0.95;
	public static final double RADIUS = 15;
	private static final boolean DEBUGGER = false;

	/**
	 * Default constructor
	 * 
	 * @param startingPoint is the balls tee spot
	 */
	public Ball(Point2D startingPoint) {

		velocity = new Vector2D(0, 0);
		lastPosition = new Point2D(startingPoint.getX(), startingPoint.getY());
		position = new Point2D(startingPoint.getX(), startingPoint.getY());
		originalPosition = new Point2D(startingPoint.getX(), startingPoint.getY());
	}

	/**
	 * Constructor with x and y I tried to super it, but it wouldn't let me for some
	 * reason
	 * 
	 * @param x location of the ball
	 * @param y location of the ball
	 */
	public Ball(double x, double y) {

		velocity = new Vector2D(0, 0);
		lastPosition = new Point2D(x, y);
		position = new Point2D(x, y);
		originalPosition = new Point2D(x, y);
	}

	/**
	 * calculates how far the ball has moved in the given time
	 * 
	 * @param dt is the time elapsed between each frame
	 */
	public void move(double dt) {
		/*
		 * Sets the lastPosition to the current position
		 */
		lastPosition.setX(position.getX());
		lastPosition.setY(position.getY());
		/*
		 * sets the new position based on the formula x = px + (vx*dt) + (0.5*ax*dt*dt)
		 */
		position.setX(lastPosition.getX() + (velocity.getX()));

		position.setY(lastPosition.getY() + (velocity.getY()));

		/*
		 * Sets the new velocity
		 * 
		 * Anything commented out with // is what I tried to before I switched to this
		 * method of deceleration
		 */

//		if (velocity.getX() >= 1)
		velocity.setX(velocity.getX() * DECELERATION);// + DECELERATION * velocity.getX());
//		else if (velocity.getY() <= -1)
//			velocity.setX(velocity.getX() - DECELERATION * velocity.getX());

//		if (velocity.getY() >= 1)
		velocity.setY(velocity.getY() * DECELERATION);// + DECELERATION * velocity.getY());
//		else if (velocity.getY() <= -1)
//			velocity.setY(velocity.getY() - DECELERATION * velocity.getY());

		if (DEBUGGER)
			System.err.println(
					"Last Position : " + lastPosition + ", Position : " + position + ", Velocity : " + velocity);

	}

	/**
	 * @return true if the ball is at rest
	 */
	public boolean isAtRest() {
		if (velocity.getX() < 1 && velocity.getX() > -1 && velocity.getY() < 1 && velocity.getY() > -1)
			return true;

		return false;
	}

	/**
	 * Draws on the ball
	 */
	public void drawOn(Graphics g) {

		g.setColor(Color.YELLOW);

		g.fillOval((int) (position.getX() - RADIUS / 2), (int) (position.getY() - RADIUS / 2), (int) RADIUS,
				(int) RADIUS);
	}

	/**
	 * @return the euclidean distance between the two points
	 */
	public double getLastDistance() {

		return position.subtract(lastPosition).size();
	}

	/**
	 * @return Last Position then position then velocity on separate lines
	 */
	public String toString() {
		return "Last Position : " + lastPosition + "\nPosition : " + position + "\nvelocity" + velocity;
	}

	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	/**
	 * This sets the velocity of the ball, if the velocity is greater than
	 * MAX_VELOCITY it makes it equal to MAX_VELOCITY that way it can never go too
	 * fast
	 * 
	 * @param velocity is the new velocity for the ball
	 */
	public void setVelocity(Vector2D velocity) {

		this.velocity.setX(velocity.getX());
		this.velocity.setY(velocity.getY());

		if (velocity.getX() > MAX_VELOCITY || velocity.getY() > MAX_VELOCITY || velocity.getX() < -MAX_VELOCITY
				|| velocity.getY() < -MAX_VELOCITY) {
			this.velocity = this.velocity.unitVector().scalarMultiplication(MAX_VELOCITY);
		}
	}

	/**
	 * Resets the balls position and velocity to their original values
	 */
	public void resetBall() {

		velocity.setX(0);
		velocity.setY(0);

		position.setX(originalPosition.getX());
		position.setY(originalPosition.getY());

		lastPosition.setX(originalPosition.getX());
		lastPosition.setY(originalPosition.getY());
	}
}
