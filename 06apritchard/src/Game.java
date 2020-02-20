import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

/**
 * Controls and holds the game of minigolf
 * 
 * @author apritchard18
 *
 */
public class Game {

	private int currentHole;
	private int numOfStrokes;
	private int shotsForCurrentHole;
	private boolean ballInPlay;

	private LinkedList<GolfCourseHole> golfCourses;

	/**
	 * Default Constructor
	 */
	public Game() {

		currentHole = 0;
		numOfStrokes = 0;
		shotsForCurrentHole = 0;
		ballInPlay = false;

		golfCourses = new LinkedList<GolfCourseHole>();
	}

	/**
	 * Second constructor. Supers the default and only adds golfCourses
	 * 
	 * @param f File is the file that youre reading to get the course
	 */
	public Game(File f) {
		super();

		golfCourses = GolfCourseHole.readGolfCourse(f);
	}

	/**
	 * Calculates and runs the next Frame
	 * 
	 * @throws BallInCupException
	 * @throws BallAtRestException
	 * @throws GameOverException
	 */
	public void nextFrame() throws BallInCupException, BallAtRestException, GameOverException {

		GolfCourseHole current = golfCourses.get(currentHole);

		current.getGolfBall().move(1 / MainFrame.FRAMES_PER_SECOND);

		current.checkCollisions();

		if (current.isBallInCup()) { // checks to see if ball is in the cup
			if (isGameOver()) { // checks to see if its game over
				throw new GameOverException("Game Over");
			} // if its not game over it just throws a basic ballincupexception
			throw new BallInCupException("Ball is in cup");
		}
		if (current.getGolfBall().isAtRest()) { // checks to see if its at rest
			throw new BallAtRestException("Ball is at rest at position " + current.getGolfBall());
		}
	}

	/**
	 * @return true if this is the last hole
	 */
	public boolean isGameOver() {
		if (currentHole == golfCourses.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads in the file and then initializes golfCourses with it
	 * 
	 * @param f File is the file that youre reading to get the course
	 */
	public void initialize(File f) {
		golfCourses = GolfCourseHole.readGolfCourse(f);
	}

	/**
	 * Takes you to the nextHole or back to the first one TODO might need to create
	 * an end game screen
	 */
	public void nextHole() {

		golfCourses.get(currentHole).getGolfBall().resetBall();

		if (currentHole == golfCourses.size() - 1)
			currentHole = 0;
		else
			currentHole++;

		shotsForCurrentHole = 0;
	}

	/**
	 * Restarts you back to the first course and restarts the ball position
	 */
	public void restart() {
		
		golfCourses.get(currentHole).getGolfBall().resetBall();		
		currentHole = 0;
		numOfStrokes = 0;
		ballInPlay = false;
		shotsForCurrentHole = 0;
	}

	/**
	 * Draws on the current course
	 * 
	 * @param g Graphics is the Graphics youre drawing on
	 */
	public void drawOn(Graphics g) {
		golfCourses.get(currentHole).drawOn(g);
	}

	public GolfCourseHole getCurrentHole() {
		return golfCourses.get(currentHole);
	}

	public int getNumOfStrokes() {
		return numOfStrokes;
	}

	public void setNumOfStrokes(int numOfStrokes) {
		this.numOfStrokes = numOfStrokes;
	}

	public int getShotsForCurrentHole() {
		return shotsForCurrentHole;
	}

	public void setShotsForCurrentHole(int shotsForCurrentHole) {
		this.shotsForCurrentHole = shotsForCurrentHole;
	}

	public boolean isBallInPlay() {
		return ballInPlay;
	}

	/*
	 * Switches the ballInPlay Boolean value
	 */
	public void swapBallInPlayValue() {
		if (ballInPlay) {
			ballInPlay = false;
		} else {
			ballInPlay = true;
		}
	}

	public void setCurrentHole(int currentHole) {
		this.currentHole = currentHole;
	}

	/**
	 * @return current hole number + 1
	 */
	public int getCurrentHoleNumber() {
		return currentHole + 1;
	}

	/**
	 * Add 1 to shots and totalShots
	 */
	public void takeShot() {
		shotsForCurrentHole++;
		numOfStrokes++;
	}
}
