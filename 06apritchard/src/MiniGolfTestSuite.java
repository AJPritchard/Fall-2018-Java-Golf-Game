import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Vector2DTest.class, Point2DTest.class, Line2DTest.class, Polygon2DTest.class,
		GolfCourseHoleTest.class })
public class MiniGolfTestSuite {
}
