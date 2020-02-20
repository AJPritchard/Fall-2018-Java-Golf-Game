import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * DisplayPanel is the minigolf game. Its what shows the course
 * 
 * @author apritchard18
 */
public class DisplayPanel extends JPanel {

	private int maxDisplayWidth;
	private int maxDisplayHeight;

	private Game myGame;

	public DisplayPanel(Game theGame) {

		myGame = theGame;

		maxDisplayWidth = (int) myGame.getCurrentHole().getContainer().getWidth();
		maxDisplayHeight = (int) myGame.getCurrentHole().getContainer().getHeight();

		this.setPreferredSize(new Dimension(maxDisplayWidth, maxDisplayHeight));
		this.setMaximumSize(new Dimension(maxDisplayWidth, maxDisplayHeight));
		this.setMinimumSize(new Dimension(maxDisplayWidth, maxDisplayHeight));

		this.setBackground(Color.BLACK);

	}

	/**
	 * paintComponent supers the paintComponent of JPanel and then draws on the game
	 * and draws on the rubberband if available
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		myGame.drawOn(g);
		paintRubberBand(g);
	}

	/**
	 * paintRubberBand paints the rubberband between pressedpoint and released point
	 * if neither of them are null and the ball isnt in play
	 * 
	 * @param g
	 */
	private void paintRubberBand(Graphics g) {

		if (MainFrame.pressedPoint != null && MainFrame.releasedPoint != null && !myGame.isBallInPlay()) {

			if (MainFrame.DEBUGGERMAINFRAME)
				System.out.println(MainFrame.pressedPoint + " <--> " + MainFrame.releasedPoint);

			g.setColor(Color.YELLOW);

			g.drawLine((int) MainFrame.pressedPoint.getX(), (int) MainFrame.pressedPoint.getY(),
					(int) MainFrame.releasedPoint.getX(), (int) MainFrame.releasedPoint.getY());
		}
	}

	public int getMaxDisplayWidth() {
		return maxDisplayWidth;
	}

	public void setMaxDisplayWidth(int maxDisplayWidth) {
		this.maxDisplayWidth = maxDisplayWidth;
	}

	public int getMaxDisplayHeight() {
		return maxDisplayHeight;
	}

	public void setMaxDisplayHeight(int maxDisplayHeight) {
		this.maxDisplayHeight = maxDisplayHeight;
	}
}
