
/**
 * Game Over Exception is thrown when the game is over, as in when the ball is
 * in the cup on the last hole
 * 
 * @author apritchard18
 *
 */
public class GameOverException extends Exception {

	public GameOverException(String message) {
		super(message);
	}
}
