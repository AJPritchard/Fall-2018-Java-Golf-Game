import java.io.File;
import java.util.LinkedList;

import org.junit.Test;

public class GolfCourseHoleTest {

	@SuppressWarnings("unused")
	@Test
	public void testConstructor() {

		GolfCourseHole gch = new GolfCourseHole();
	}
	
	@Test
	public void testReader() {
		
		File f;
		
		f = new File("Golf_Course_File.txt");
		
		System.out.println(GolfCourseHole.readGolfCourse(f));
		
		
	}
	
	@Test
	public void testReader2() {
		
		File f;
		
		f = new File("Golf_Course_File2.txt");
		
		LinkedList<GolfCourseHole> gch = GolfCourseHole.readGolfCourse(f);
		
		for(GolfCourseHole g : gch) {
			System.out.println("\n" + g);
		}
		
	}

}
