import org.junit.*;

import static org.junit.Assert.*;

public class NodeTest {

    private static TreeMap.Node<String, String> node;

    @Before
    public static void init() {
        node = new TreeMap.Node<>("ooo", "acd");
    }

    @Test
    public static void testInitializationNotNull() {
        assertNotNull(node.getKey());
        assertNotNull(node.getValue());
        assertNotEquals(0, node.hashCode());
    }

    @Test
    public static void testInitializationCorrect() {
        assertEquals("ooo", node.getKey());
        assertEquals("acd", node.getValue());
        assertEquals("ooo".hashCode(), node.hashCode());
    }

    @Test
    public static void testEqualsNull() {
        assertFalse(node.equals(null));
    }

    @Test
    public static void testEqualsItSelf() {
        assertTrue(node.equals(node));
    }

    @Test
    public static void testEqualsDifferentKeys() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("1", "acd")));
        assertFalse(node.equals(new TreeMap.Node<Integer, String>(1, "acd")));
    }

    @Test
    public static void testEqualsDifferentValues() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("ooo", "321")));
        assertFalse(node.equals(new TreeMap.Node<String, Integer>("ooo", 1)));
    }

    @Test
    public static void testEqualsDifferentValuesAndKeys() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("asd", "321")));
        assertFalse(node.equals(new TreeMap.Node<String, Integer>("1", 1)));
    }

    @Test
    public static void testToString() {
        assertEquals("{ooo - acd}", node.toString());
    }

}
