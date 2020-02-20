import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The mainframe for the minigolf game
 * 
 * @author apritchard18
 */
public class MainFrame extends JFrame {

	/*
	 * The borders of the JFrame
	 */
	public static final int MAX_INIT_FRAME_HEIGHT = 800;
	public static final int MAX_INIT_FRAME_WIDTH = 1000;

	public static final String FILE = "Golf_Course_File2.txt";
	public static final boolean DEBUGGERMAINFRAME = false;

	public static final int FRAMES_PER_SECOND = 15;

	private BorderLayout blom;

	private JPanel pnlCenter;
	private JPanel pnlLeft;
	private JPanel pnlBottom;

	private DisplayPanel pnlDisplay;
	private DashboardPanel pnlDashboard;
	private ImagePanel pnlImage;

	private JButton btnRestart;
	private JButton btnQuit;
	private JButton btnNextHole;

	public static Point2D pressedPoint;
	public static Point2D releasedPoint;

	private GolfMainListener mouse;

	private Game theGame;

	public MainFrame() {
		super("Mini Golf");

		this.setSize(MAX_INIT_FRAME_WIDTH, MAX_INIT_FRAME_HEIGHT);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false); // now user can't resize the window when its open

		this.setLocationRelativeTo(null);

		/*
		 * Initializes the Game
		 */

		theGame = new Game();
		File f = new File(FILE);
		theGame.initialize(f);

		/*
		 * Creates and sets the borderlayout
		 */

		blom = new BorderLayout();
		this.setLayout(blom);

		/*
		 * Sets JPanel Center to the Center of BorderLayout
		 */
		pnlCenter = new JPanel(new BorderLayout());
		this.add(pnlCenter, BorderLayout.CENTER);

		/*
		 * Sets JPanel Left to the West of BorderLayout Also creates pnlImage and sets
		 * it to the left
		 */
		pnlLeft = new JPanel(new BorderLayout());
		this.add(pnlLeft, BorderLayout.WEST);
		pnlImage = new ImagePanel(theGame);
		pnlLeft.add(pnlImage);

		/*
		 * Sets JPanel Bottom to the South of BorderLayout
		 */
		pnlBottom = new JPanel(new BorderLayout());
		pnlCenter.add(pnlBottom, BorderLayout.SOUTH);

		/*
		 * Creates the Display JFrame and places it in the north
		 */
		pnlDisplay = new DisplayPanel(theGame);
		pnlCenter.add(pnlDisplay, BorderLayout.NORTH);

		/*
		 * creates the dashboard jframe and places it in the center
		 */
		pnlDashboard = new DashboardPanel(theGame);
		pnlCenter.add(pnlDashboard, BorderLayout.CENTER);

		/*
		 * initializes buttons and puts them with pnlBottom
		 */

		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new RestartHelper());
		pnlBottom.add(btnRestart, BorderLayout.WEST);

		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new QuitHelper());
		pnlBottom.add(btnQuit, BorderLayout.EAST);

		btnNextHole = new JButton("Next Hole");
		btnNextHole.addActionListener(new NextHoleHelper());
		pnlBottom.add(btnNextHole, BorderLayout.CENTER);

		/*
		 * Sets up the GolfMainListener for the Mouse
		 */
		mouse = new GolfMainListener();
		pnlDisplay.addMouseListener(mouse);
		pnlDisplay.addMouseMotionListener(mouse);

	}

	public static void main(String args[]) {
		/*
		 * Runs the game
		 */
		MainFrame mf;
		mf = new MainFrame();
		mf.setVisible(true);
	}

	private class RestartHelper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * If the game shouldnt be able to go to the next hole until it resets if the
			 * game is over
			 */
			if (!btnNextHole.isEnabled()) {
				btnNextHole.setEnabled(true);
			}
			/*
			 * Restarts the game
			 */
			theGame.restart();
			repaint();
		}
	}

	private class QuitHelper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * Quits
			 */
			System.exit(0);
		}
	}

	private class NextHoleHelper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * Skips to the next hole
			 */
			theGame.nextHole();
			repaint();
		}
	}

	public class GolfMainListener implements MouseListener, MouseMotionListener, ActionListener {

		Timer myTimer;

		public GolfMainListener() {

			myTimer = new Timer(1000 / FRAMES_PER_SECOND, this); // iterates 15 times a second
			myTimer.start();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			/*
			 * If the ball is in motion, I don't want the mouse to affect anything
			 */
			if (!theGame.isBallInPlay()) {

				/*
				 * releasedPoint needs to change with whereever the ball is
				 */
				releasedPoint = new Point2D(e.getX(), e.getY());

				if (DEBUGGERMAINFRAME)
					System.out.println("Mouse Dragged : " + releasedPoint);

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

			/*
			 * If the ball is in motion, I don't want the mouse to affect anything
			 */
			if (!theGame.isBallInPlay()) {

				pressedPoint = theGame.getCurrentHole().getGolfBall().getPosition();

				releasedPoint = null;

				if (DEBUGGERMAINFRAME)
					System.out.println("Mouse Pressed : " + pressedPoint);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			/*
			 * If the ball is in motion, I don't want the mouse to affect anything
			 */
			if (!theGame.isBallInPlay()) {

				if (DEBUGGERMAINFRAME)
					System.out.println("Mouse Released : " + releasedPoint);

				/*
				 * When released it sets the velocity to the vector created by the pressedPoint
				 * and releasedPoint Then sets the BallInPlay variable to true so that the ball
				 * can move
				 */
				theGame.takeShot();
				theGame.getCurrentHole().getGolfBall().setVelocity(new Vector2D(pressedPoint, releasedPoint).scalarMultiplication(0.1));
				theGame.swapBallInPlayValue();

				pressedPoint = null;
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Update ball position and check if it enters the hole
			if (theGame.isBallInPlay()) {
				try {
					/*
					 * Runs the next frame
					 */
					theGame.nextFrame();
				} catch (BallInCupException e) {
					if (DEBUGGERMAINFRAME)
						e.printStackTrace();

					/*
					 * If the ball is in the cup it takes you to the next hole
					 */
					theGame.swapBallInPlayValue();
					theGame.nextHole();

				} catch (BallAtRestException e) {
					if (DEBUGGERMAINFRAME)
						e.printStackTrace();

					/*
					 * Lets you shoot the ball
					 */
					theGame.swapBallInPlayValue();

				} catch (GameOverException e) {
					if (DEBUGGERMAINFRAME)
						e.printStackTrace();

					/*
					 * freezes the game
					 */
					theGame.swapBallInPlayValue();
					btnNextHole.setEnabled(false);

					if (DEBUGGERMAINFRAME) {
						System.err.println("*******************************");
						System.err.println("GAME OVER!!!!");
						System.err.println("Num Of Strokes: " + theGame.getNumOfStrokes());
						System.err.println("*******************************");
					}
				}
			}

			repaint();
		}
	}
}
