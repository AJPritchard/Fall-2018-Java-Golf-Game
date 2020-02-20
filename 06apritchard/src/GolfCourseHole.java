import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class consists of the contents required for a hole of Golf It also has
 * the static method readGolfCourse, which reads in a file to create new golf
 * courses
 * 
 * @author apritchard18
 */
public class GolfCourseHole {

	private final boolean DEBUGGER = false;
	public static final double tMin = 2;

	private LinkedList<Polygon2D> obstacles; // all the obstacles
	private Polygon2D container; // the surrounding box for the course
	private HoleCup hole; // the hole that the ball goes into
	private int holeNum; // which hole this is
	private String holeName; // the name of this hole
	private Point2D tee; // the starting point
	private Ball golfBall;

	private static int numOfHoles = 0; // total number of holes

	/**
	 * Default Constructor
	 */
	public GolfCourseHole() {
		obstacles = new LinkedList<Polygon2D>();
		container = new Polygon2D();

		numOfHoles++;
		holeNum = numOfHoles;
	}

	/**
	 * Draws the course onto the graphics
	 * 
	 * @param g is the graphics we are painting on
	 */
	public void drawOn(Graphics g) {

		container.drawOn(g, true, Color.GREEN);
		hole.drawOn(g);

		for (Polygon2D obst : obstacles) {
			obst.drawOn(g, true, Color.LIGHT_GRAY);
		}

		golfBall.drawOn(g);

	}

	/**
	 * @param obstacle is the new obstacle added to this golf course hole
	 */
	public void addObstacle(Polygon2D obstacle) {
		obstacles.add(obstacle);
	}

	/**
	 * @return all polygons with the container polygon coming first
	 */
	public LinkedList<Polygon2D> getAllPolygons() {

		LinkedList<Polygon2D> polys = new LinkedList<Polygon2D>();

		polys.add(container);
		polys.addAll(obstacles);

		return polys;
	}

	/**
	 * @param num is the position in the linkedlist that you want to grab from
	 * @return the obstacle at nums position
	 */
	public Polygon2D getObstacle(int num) {
		return obstacles.get(num);
	}

	/**
	 * @return all the edges in a LinkedList in line2D's
	 */
	public LinkedList<Line2D> getEdges() {

		LinkedList<Line2D> edges = new LinkedList<Line2D>();

		for (Line2D edge : container.getEdges()) {
			edges.add(edge);
		}

		for (Polygon2D obsta : obstacles) {
			for (Line2D edge : obsta.getEdges()) {
				edges.add(edge);
			}
		}

		return edges;
	}

	/**
	 * Checks for collisions, and if there is one it calculates the balls new
	 * position
	 */
	public void checkCollisions() {
		LinkedList<Line2D> edges = getEdges();
		Line2D ballLine = new Line2D(golfBall.getVelocity(), golfBall.getLastPosition());
		double time = 1;
		Line2D closestEdge = null;

		/*
		 * Check to see if there is a edge that the golfball intersects If parallel it
		 * simply continues
		 */
		for (Line2D edge : edges) {
			try {
				if (ballLine.intersectTime(edge) < time && ballLine.intersectTime(edge) > 0
						&& edge.intersectTime(ballLine) < time && edge.intersectTime(ballLine) > 0) {
					time = ballLine.intersectTime(edge);
					closestEdge = edge;
				}
			} catch (ParallelException e) {
				// e.printStackTrace();
				continue;
			}
		}

		/*
		 * Skips this if the golfball didnt intersect any edges
		 */
		if (closestEdge != null) {

			if (DEBUGGER)
				System.out.println("Closest Edge" + closestEdge);

			/*
			 * If it did then it finds the point of intersection and then it checks to see
			 * what direction it richochets to. Since whatever way it rebounds should be in
			 * the perpendicular direction vector of the current vector, we just need to
			 * find out if it richochets up or down.
			 */
			Point2D pointAtIntersection = ballLine.getPointAt(time);

			/*
			 * Finds speed for the end vector
			 */
			double speed = golfBall.getVelocity().size();

			/*
			 * Finds the incident vector, or vector c on the pdf
			 */
			Vector2D incidentVector = pointAtIntersection.subtract(golfBall.getLastPosition());

			/*
			 * Finds the normal of the wallLine vector, the line the ball is intersecting
			 */
			
			Vector2D normal;
				normal = closestEdge.getDirection().clockwisePerpendicular();
			
			/*
			 * uses the normal vector and incident vector to find the reflectionvector
			 */
			Vector2D reflectorVector = incidentVector
					.vectorSubtraction(normal.scalarMultiplication(2 * incidentVector.dotProduct(normal)));

			/*
			 * Finds the rebound line and scales the reflectorVector down to what it should
			 * be
			 */
			Line2D reboundLine = new Line2D(reflectorVector.unitVector().scalarMultiplication(speed),
					pointAtIntersection);

			golfBall.setPosition(reboundLine.getPointAt(1 - time));
			golfBall.setVelocity(reboundLine.getDirection());
			checkCollisions();
		}
		
		
	}

	/**
	 * @return returns true if the ball is in the cup
	 */
	public boolean isBallInCup() {
		return golfBall.getPosition().subtract(hole.getLocation()).size() < HoleCup.RADIUS - golfBall.RADIUS;
	}

	/**
	 * Reads in the golf course from the selected file
	 * @param f is the file you're reading in. It is converted to a filereader and
	 *          is used by bufferedreader
	 * @return a linkedlist of golfcoursehole, used for all the holes in the game.
	 * @throws IOException
	 */
	public static LinkedList<GolfCourseHole> readGolfCourse(File f) {

		LinkedList<GolfCourseHole> wholeGolfCourse = new LinkedList<GolfCourseHole>();

		BufferedReader in = null;

		try {
			/*
			 * Initializes the buffered reader and connects it to the file
			 */
			in = new BufferedReader(new FileReader(f));

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FILENOTFOUND");
		}

		try {

			/*
			 * Initializes the wholeGolfCourse Linked List of GolfCourses
			 */
			wholeGolfCourse = readGolfCourseWork(in);

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FILEISNTPROPER");
		} finally {

			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("FILENOTFOUND");
			}
		}

		return wholeGolfCourse;
	}

	/**
	 * @param in is the buffered reader used to take in lines
	 * @return a linkedlist of golfcoursehole, used for all the holes in the game.
	 */
	private static LinkedList<GolfCourseHole> readGolfCourseWork(BufferedReader in) throws IOException {

		LinkedList<GolfCourseHole> wholeGolfCourse = new LinkedList<GolfCourseHole>();

		GolfCourseHole gch;
		String str;
		int numOfObstacles;

		str = in.readLine();

		/*
		 * Goes through the file until it finds an empty line
		 */
		while (str != null) {

			if (str.equals("")) {
				str = in.readLine();
			}

			/*
			 * Creates a new golfcoursehole
			 */
			gch = new GolfCourseHole();

			/*
			 * For hole num and hole title
			 */
			gch.firstLine(str);

			str = in.readLine();

			/*
			 * for container polygon
			 */
			gch.secondLine(str);

			str = in.readLine();

			/*
			 * tee location
			 */
			gch.thirdLine(str);

			str = in.readLine();

			/*
			 * cup location
			 */
			gch.fourthLine(str);

			str = in.readLine();

			/*
			 * numObstacles
			 */
			numOfObstacles = gch.fifthLine(str);

			for (int i = 0; i < numOfObstacles; i++) {
				str = in.readLine();

				/*
				 * each time it runs it adds the next obstacle to obstacles
				 */
				gch.sixthLine(str);
			}

			/*
			 * adds the new golf course hole to the golf course
			 */
			wholeGolfCourse.add(gch);

			str = in.readLine();
		}

		return wholeGolfCourse;
	}

	/**
	 * Takes in the first line and finds the holenumber and hole name
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 */
	private void firstLine(String str) {

		if (DEBUGGER)
			System.err.println("FIRSTLINE\n");

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == ' ') {

				if (DEBUGGER)
					System.err.println(str.substring(0, i));

				setHoleNum(Integer.parseInt(str.substring(0, i)));

				if (DEBUGGER)
					System.err.println(str.substring(i + 1));

				setHoleName(str.substring(i + 1));
				return;
			}
		}
	}

	/**
	 * Builds the container polygon from the string
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 */
	private void secondLine(String str) {

		if (DEBUGGER)
			System.err.println("\nSECONDLINE\n");

		/*
		 * last space, that way i can continue to increase and I can still find the
		 * starting point for substring
		 */
		int lastSpace = 0;
		String temp; // reads in each section of the str separated by '|' at a time

		if (DEBUGGER)
			System.err.println(str);

		/*
		 * Searches through the String to find |, when it does it takes out that part,
		 * that should have 2 ints in it and it seperates them by where the space is and
		 * then it finds their ints and creates points with them
		 */
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == '|') {

				temp = str.substring(lastSpace, i).trim();

				if (DEBUGGER)
					System.err.println(temp);

				lastSpace = i + 1;

				for (int j = 0; j < temp.length(); j++) {

					if (temp.charAt(j) == ' ') {

						if (DEBUGGER)
							System.err.println(temp.substring(0, j).trim());

						if (DEBUGGER)
							System.err.println(temp.substring(j, temp.length()).trim());

						container.addPoint(new Point2D((double) Integer.parseInt(temp.substring(0, j).trim()),
								(double) Integer.parseInt(temp.substring(j, temp.length()).trim())));

						break;
					}
				}
			}
		}

		/*
		 * Does the same as above but for the last one which doesn't have an | after it.
		 */
		temp = str.substring(lastSpace).trim();

		for (int j = 0; j < temp.length(); j++) {

			if (temp.charAt(j) == ' ') {

				container.addPoint(new Point2D((double) Integer.parseInt(temp.substring(0, j).trim()),
						(double) Integer.parseInt(temp.substring(j, temp.length()).trim())));

			}
		}
	}

	/**
	 * Creates the point2D tee Also initializes GolfBall with tee's location
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 */
	private void thirdLine(String str) {

		if (DEBUGGER)
			System.err.println("\nTHIRDLINE\n");

		str = str.trim();

		if (DEBUGGER)
			System.err.println(str);

		for (int j = 0; j < str.length(); j++) {

			if (str.charAt(j) == ' ') {
				tee = new Point2D((double) Integer.parseInt(str.substring(0, j)),
						(double) Integer.parseInt(str.substring(j + 1, str.length())));
				golfBall = new Ball(tee);
				return;
			}
		}

	}

	/**
	 * Creates the cup location
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 */
	private void fourthLine(String str) {

		if (DEBUGGER)
			System.err.println("\nFOURTHLINE\n");

		if (DEBUGGER)
			System.err.println(str);

		for (int j = 0; j < str.length(); j++) {

			if (str.charAt(j) == ' ') {
				hole = new HoleCup(new Point2D((double) Integer.parseInt(str.substring(0, j)),
						(double) Integer.parseInt(str.substring(j + 1, str.length()))));
				return;

			}
		}
	}

	/**
	 * NumOfObstacles
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 * @return the int that records the numOfObstacles
	 */
	private int fifthLine(String str) {
		if (DEBUGGER)
			System.err.println("\nFIFTHLINE\n");

		if (DEBUGGER)
			System.err.println(str);

		return Integer.parseInt(str);
	}

	/**
	 * Creates a Polygon2D and then adds it to obstacles
	 * 
	 * @param str is the string that reads in. Supposed to be the first line of the
	 *            current hole
	 */
	private void sixthLine(String str) {

		if (DEBUGGER)
			System.err.println("\nSIXTHLINE\n");

		if (DEBUGGER)
			System.err.println(str);

		Polygon2D obsta = new Polygon2D();

		/*
		 * last space, that way i can continue to increase and I can still find the
		 * starting point for substring
		 */
		int lastSpace = 0;
		String temp; // reads in each section of the str separated by '|' at a time

		/*
		 * Searches through the String to find |, when it does it takes out that part,
		 * that should have 2 ints in it and it seperates them by where the space is and
		 * then it finds their ints and creates points with them
		 */
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '|') {

				temp = str.substring(lastSpace, i).trim();

				if (DEBUGGER)
					System.err.println(temp);

				lastSpace = i + 1;

				for (int j = 0; j < temp.length(); j++) {

					if (temp.charAt(j) == ' ') {

						if (DEBUGGER)
							System.err.println(temp.substring(0, j).trim());

						if (DEBUGGER)
							System.err.println(temp.substring(j, temp.length()).trim());

						obsta.addPoint(new Point2D((double) Integer.parseInt(temp.substring(0, j).trim()),
								(double) Integer.parseInt(temp.substring(j, temp.length()).trim())));

						break;
					}
				}
			}
		}

		/*
		 * Does the same as above but for the last one which doesn't have an | after it.
		 */
		temp = str.substring(lastSpace).trim();

		if (DEBUGGER)
			System.err.println(temp);

		for (int j = 0; j < temp.length(); j++) {

			if (DEBUGGER)
				System.err.println(temp.substring(0, j).trim());

			if (DEBUGGER)
				System.err.println(temp.substring(j, temp.length()).trim());

			if (temp.charAt(j) == ' ') {
				obsta.addPoint(new Point2D((double) Integer.parseInt(temp.substring(0, j).trim()),
						(double) Integer.parseInt(temp.substring(j, temp.length()).trim())));

			}
		}

		obstacles.add(obsta);

	}

	/*
	 * ******************************GETTERS SETTERS AND TOSTRING*****************
	 */

	/**
	 * @return is information detailing the container boundaries, hole location,
	 *         number of obstacles and their locations
	 */
	public String toString() {
		String str = "";

		str += "Hole Number: " + holeNum + ". Hole Title: " + holeName;
		str += "\nContainer Boundaries: " + container;
		str += "\nTee location: " + tee;
		str += "\nHole location: " + hole.getLocation();
		str += "\nNumber of Obstacles: " + obstacles.size();
		str += "\nObstacle locations:";

		for (int i = 0; i < obstacles.size(); i++) {
			str += "\n\t" + obstacles.get(i);
		}

		return str;
	}

	public LinkedList<Polygon2D> getObstacles() {
		return obstacles;
	}

	public void setObstacles(LinkedList<Polygon2D> obstacles) {
		this.obstacles = obstacles;
	}

	public Polygon2D getContainer() {
		return container;
	}

	public HoleCup getHole() {
		return hole;
	}

	public int getHoleNum() {
		return holeNum;
	}

	public String getHoleName() {
		return holeName;
	}

	public void setHoleName(String holeName) {
		this.holeName = holeName;
	}

	public static int getNumOfHoles() {
		return numOfHoles;
	}

	public Point2D getTee() {
		return tee;
	}

	public void setTee(Point2D tee) {
		this.tee = tee;
	}

	public void setContainer(Polygon2D container) {
		this.container = container;
	}

	public void setHole(HoleCup hole) {
		this.hole = hole;
	}

	public void setHoleNum(int holeNum) {
		this.holeNum = holeNum;
	}

	public Ball getGolfBall() {
		return golfBall;
	}

	public void setGolfBall(Ball golfBall) {
		this.golfBall = golfBall;
	}
}