import  org.junit.*;

import static org.junit.Assert.*;

public class NodeTest {

    private static TreeMap.Node<String, String> node;

    @Before
    public void init() {
        node = new TreeMap.Node<>("ooo", "acd");
    }

    @Test
    public void testInitializationNotNull() {
        assertNotNull(node.getKey());
        assertNotNull(node.getValue());
    }

    @Test
    public void testInitializationCorrect() {
        assertEquals("ooo", node.getKey());
        assertEquals("acd", node.getValue());
    }

    @Test
    public void testEqualsNull() {
        assertFalse(node.equals(null));
    }

    @Test
    public void testEqualsItSelf() {
        assertTrue(node.equals(node));
    }

    @Test
    public void testEqualsDifferentKeys() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("1", "acd")));
        assertFalse(node.equals(new TreeMap.Node<Integer, String>(1, "acd")));
    }

    @Test
    public void testEqualsDifferentValues() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("ooo", "321")));
        assertFalse(node.equals(new TreeMap.Node<String, Integer>("ooo", 1)));
    }

    @Test
    public void testEqualsDifferentValuesAndKeys() {
        assertFalse(node.equals(new TreeMap.Node<String, String>("asd", "321")));
        assertFalse(node.equals(new TreeMap.Node<String, Integer>("1", 1)));
    }

    @Test
    public void testToString() {
        assertEquals("{ooo - acd}", node.toString());
    }

}
