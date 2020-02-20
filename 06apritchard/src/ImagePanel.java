import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * ImagePanel draws the image of a fancy course on the left side
 * 
 * @author apritchard18
 */
public class ImagePanel extends JPanel {

	private BufferedImage image;
	private int maxImageWidth;
	private int maxImageHeight;

	Game theGame;

	public ImagePanel(Game theGame) {

		this.theGame = theGame;

		maxImageWidth = MainFrame.MAX_INIT_FRAME_WIDTH - (int) theGame.getCurrentHole().getContainer().getWidth();
		maxImageHeight = MainFrame.MAX_INIT_FRAME_HEIGHT;

		this.setPreferredSize(new Dimension(maxImageWidth, maxImageHeight));
		this.setMaximumSize(new Dimension(maxImageWidth, maxImageHeight));
		this.setMinimumSize(new Dimension(maxImageWidth, maxImageHeight));

		try {
			image = ImageIO.read(new File("Golf_Course.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supers paintcomponent and then draws the image
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, maxImageWidth, maxImageHeight, null);
	}

	public int getMaxImageWidth() {
		return maxImageWidth;
	}

	public void setMaxImageWidth(int maxImageWidth) {
		this.maxImageWidth = maxImageWidth;
	}

	public int getMaxImageHeight() {
		return maxImageHeight;
	}

	public void setMaxImageHeight(int maxImageHeight) {
		this.maxImageHeight = maxImageHeight;
	}
}
