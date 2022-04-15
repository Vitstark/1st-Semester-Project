import org.junit.*;
import static org.junit.Assert.*;

public class TreeMapTest {

    public static TreeMap<String, String> treeMap;

    @Before
    public static void init() {
        treeMap = new TreeMap<>();
    }

    @Test
    public static void testInitialization() {
        assertEquals(0, treeMap.size());
    }

}
