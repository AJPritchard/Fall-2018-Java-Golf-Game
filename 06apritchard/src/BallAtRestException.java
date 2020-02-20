
/**
 * BallAtRestException is thrown when the balls movement speed is less than 1 and greater than -1
 * 
 * @author apritchard18
 *
 */
public class BallAtRestException extends Exception {

	public BallAtRestException(String message) {
		super(message);
	}
}
