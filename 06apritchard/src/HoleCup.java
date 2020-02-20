import java.awt.Color;
import java.awt.Graphics;

public class HoleCup {

	public final static int RADIUS = 30;
	public final Color HOLECOLOR = Color.WHITE;

	private Point2D location;

	/**
	 * @param Point2D point is the location of the HoleCup
	 */
	public HoleCup(Point2D point) {
		location = point;
	}

	public void drawOn(Graphics g) {

		g.setColor(HOLECOLOR);

		g.fillOval((int) location.getX() - RADIUS / 2, (int) location.getY() - RADIUS / 2, RADIUS, RADIUS);
	}
	
	public Point2D getLocation() {
		return location;
	}

}
