import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * DashboardPanel is the panel that holds the information about the course and how you're doing
 * 
 * @author apritchard18
 *
 */
public class DashboardPanel extends JPanel{

	private static final int FONT_SIZE = 40;
	private JLabel lblHoleNumberPrompt;
	private JLabel lblHoleNumber;
	
	private JLabel lblCurrentHolePrompt;
	private JLabel lblCurrentHole;
	
	private JLabel lblShotsPrompt;
	private JLabel lblShots; 
	
	private JLabel lblTotalShotsPrompt;
	private JLabel lblTotalShots; 
	
	private final Font MYFONT = new Font("arial", 0, FONT_SIZE);
	
	private Game myGame;

	public DashboardPanel(Game theGame) {
		
		myGame = theGame;
		
		this.setBackground(Color.PINK);
	
		/*
		 * Initializes all the JLabels
		 */
		lblHoleNumberPrompt = new JLabel("Hole Number:");
		lblHoleNumber = new JLabel("0");  
		
		lblCurrentHolePrompt = new JLabel("Current Hole:");
		lblCurrentHole = new JLabel("N/A"); 
		
		lblShotsPrompt = new JLabel("Shots: ");
		lblShots = new JLabel("0");
		
		lblTotalShotsPrompt = new JLabel("Total Shots: ");
		lblTotalShots = new JLabel("0");
				
		/*
		 * Adds all the JLabels
		 */
		this.add(lblHoleNumberPrompt);
		this.add(lblHoleNumber);
		this.add(lblCurrentHolePrompt);
		this.add(lblCurrentHole);
		this.add(lblShotsPrompt);
		this.add(lblShots);
		this.add(lblTotalShotsPrompt);
		this.add(lblTotalShots);
		
		/*
		 * Sets them all to a larger font
		 */
		lblHoleNumberPrompt.setFont(MYFONT);
		lblHoleNumber.setFont(MYFONT);
		lblCurrentHolePrompt.setFont(MYFONT);
		lblCurrentHole.setFont(MYFONT);
		lblShotsPrompt.setFont(MYFONT);
		lblShots.setFont(MYFONT);
		lblTotalShotsPrompt.setFont(MYFONT);
		lblTotalShots.setFont(MYFONT);
	}
	
	/**
	 * We set the location of each of the labels in this to a nicer location, and we set the text of
	 * the labels that are not prompts
	 * @param g is the Graphics we are painting onto
	 */
	@Override
	public void paintComponent(Graphics g) {		
		
		lblHoleNumberPrompt.setLocation(75, 50);
		
		lblHoleNumber.setText(Integer.toString(myGame.getCurrentHoleNumber()));
		
		lblHoleNumber.setLocation(this.getWidth() / 2, 50);
		
		lblCurrentHolePrompt.setLocation(85, 100);
		
		lblCurrentHole.setText(myGame.getCurrentHole().getHoleName());
		
		lblCurrentHole.setLocation(this.getWidth() / 2, 100);	
		
		lblShotsPrompt.setLocation(205, 150);
		
		lblShots.setText(Integer.toString(myGame.getShotsForCurrentHole()));
		
		lblShots.setLocation(this.getWidth() / 2, 150);
		
		lblTotalShotsPrompt.setLocation(110, 200);
		
		lblTotalShots.setText(Integer.toString(myGame.getNumOfStrokes()));
		
		lblTotalShots.setLocation(this.getWidth() / 2, 200);
		
		super.paintComponent(g);
	}
}
