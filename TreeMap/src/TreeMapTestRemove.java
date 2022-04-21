import org.junit.*;

import static org.junit.Assert.*;

public class TreeMapTestRemove {

    private static TreeMap<Integer, Integer> emptyTreeMap;
    private static TreeMap<Integer, Integer> treeMapWith3ElemForDeleteRightNode;
    private static TreeMap<Integer, Integer> treeMapWith3ElemForDeleteLeftNode;
    private static TreeMap<Integer, Integer> treeMapWith3ElemForDeleteRoot;
    private static TreeMap<Integer, Integer> treeMapWith11ElemFor1Delete;
    private static TreeMap<Integer, Integer> treeMapWith11ElemFor2Delete;
    private static TreeMap<Integer, Integer> treeMapWith11ElemFor3Delete;
    private static boolean BLACK = true;
    private static boolean RED = false;
    private static TreeMap.Node<Integer,Integer> LEAVE = new TreeMap.Node<>(null, null, BLACK);

    @Before
    public void init() {
        emptyTreeMap = new TreeMap<>();
        treeMapWith3ElemForDeleteRightNode = new TreeMap<>();
        treeMapWith3ElemForDeleteLeftNode = new TreeMap<>();
        treeMapWith3ElemForDeleteRoot = new TreeMap<>();
        treeMapWith11ElemFor1Delete = new TreeMap<>();
        treeMapWith11ElemFor2Delete = new TreeMap<>();
        treeMapWith11ElemFor3Delete = new TreeMap<>();

        for (Integer i = 1; i < 4; i++) {
            treeMapWith3ElemForDeleteRoot.put(i, i);
            treeMapWith3ElemForDeleteLeftNode.put(i, i);
            treeMapWith3ElemForDeleteRightNode.put(i, i);
        }

        for (Integer i = 1; i < 12; i++) {
            treeMapWith11ElemFor1Delete.put(i, i);
            treeMapWith11ElemFor2Delete.put(i, i);
            treeMapWith11ElemFor3Delete.put(i, i);
        }
    }

    @Test
    public void testEmptyMap() {
        assertNull(emptyTreeMap.remove(1));
        assertNull(emptyTreeMap.getRoot());
    }

    @Test
    public void testMapWith3ElementAfterRightRemove() {
        assertEquals(new Integer(3), treeMapWith3ElemForDeleteRightNode.remove(3));
        assertEquals(2, treeMapWith3ElemForDeleteRightNode.size());
        assertEquals(new TreeMap.Node(2, 2), treeMapWith3ElemForDeleteRightNode.getRoot());
        assertEquals(new TreeMap.Node(1, 1), treeMapWith3ElemForDeleteRightNode.getRoot().left);
        assertEquals(LEAVE, treeMapWith3ElemForDeleteRightNode.getRoot().right);

        assertEquals(BLACK, treeMapWith3ElemForDeleteRightNode.getRoot().color);
        assertEquals(RED, treeMapWith3ElemForDeleteRightNode.getRoot().left.color);

        assertEquals(new TreeMap.Node(1, 1), treeMapWith3ElemForDeleteRightNode.getMin());
        assertEquals(new TreeMap.Node(2, 2), treeMapWith3ElemForDeleteRightNode.getMax());

        assertEquals(treeMapWith3ElemForDeleteRightNode.getRoot(),
                treeMapWith3ElemForDeleteLeftNode.getRoot().right.parent);
    }

    @Test
    public void testMapWith3ElementAfterLeftRemove() {
        assertEquals(new Integer(1), treeMapWith3ElemForDeleteLeftNode.remove(1));
        assertEquals(2, treeMapWith3ElemForDeleteLeftNode.size());
        assertEquals(new TreeMap.Node(2, 2), treeMapWith3ElemForDeleteLeftNode.getRoot());
        assertEquals(new TreeMap.Node(3, 3), treeMapWith3ElemForDeleteLeftNode.getRoot().right);
        assertEquals(LEAVE, treeMapWith3ElemForDeleteLeftNode.getRoot().left);

        assertEquals(BLACK, treeMapWith3ElemForDeleteLeftNode.getRoot().color);
        assertEquals(RED, treeMapWith3ElemForDeleteLeftNode.getRoot().right.color);

        assertEquals(new TreeMap.Node(2, 2), treeMapWith3ElemForDeleteLeftNode.getMin());
        assertEquals(new TreeMap.Node(3, 3), treeMapWith3ElemForDeleteLeftNode.getMax());

        assertEquals(treeMapWith3ElemForDeleteLeftNode.getRoot(),
                treeMapWith3ElemForDeleteLeftNode.getRoot().left.parent);
    }

    @Test
    public void testMapWith3ElementAfterRootRemove() {
        assertEquals(new Integer(2), treeMapWith3ElemForDeleteRoot.remove(2));
        assertEquals(2, treeMapWith3ElemForDeleteRoot.size());
        assertEquals(new TreeMap.Node(3, 3), treeMapWith3ElemForDeleteRoot.getRoot());
        assertEquals(new TreeMap.Node(1, 1), treeMapWith3ElemForDeleteRoot.getRoot().left);
        assertEquals(LEAVE, treeMapWith3ElemForDeleteRoot.getRoot().right);

        assertEquals(BLACK, treeMapWith3ElemForDeleteRoot.getRoot().color);
        assertEquals(RED, treeMapWith3ElemForDeleteRoot.getRoot().left.color);

        assertEquals(new TreeMap.Node(1, 1), treeMapWith3ElemForDeleteRoot.getMin());
        assertEquals(new TreeMap.Node(3, 3), treeMapWith3ElemForDeleteRoot.getMax());

        assertEquals(treeMapWith3ElemForDeleteRoot.getRoot(),
                treeMapWith3ElemForDeleteRoot.getRoot().right.parent);
    }

    @Test
    public void testMapWith11Elements1Remove() {
        assertEquals(Integer.valueOf(7), treeMapWith11ElemFor1Delete.remove(7));
        assertEquals(10, treeMapWith11ElemFor1Delete.size());

        assertEquals(new TreeMap.Node(10, 10), treeMapWith11ElemFor1Delete.getRoot().right.right);
        assertEquals(new TreeMap.Node(11, 11), treeMapWith11ElemFor1Delete.getRoot().right.right.right);
        assertEquals(new TreeMap.Node(8, 8), treeMapWith11ElemFor1Delete.getRoot().right.right.left);
        assertEquals(new TreeMap.Node(9, 9), treeMapWith11ElemFor1Delete.getRoot().right.right.left.right);
        assertEquals(LEAVE, treeMapWith11ElemFor1Delete.getRoot().right.right.left.left);

        assertEquals(BLACK, treeMapWith11ElemFor1Delete.getRoot().color);
        assertEquals(BLACK, treeMapWith11ElemFor1Delete.getRoot().right.color);
        assertEquals(BLACK, treeMapWith11ElemFor1Delete.getRoot().right.left.color);
        assertEquals(RED, treeMapWith11ElemFor1Delete.getRoot().right.right.color);
        assertEquals(BLACK, treeMapWith11ElemFor1Delete.getRoot().right.right.left.color);
        assertEquals(BLACK, treeMapWith11ElemFor1Delete.getRoot().right.right.right.color);
        assertEquals(RED, treeMapWith11ElemFor1Delete.getRoot().right.right.left.right.color);

        assertEquals(treeMapWith11ElemFor1Delete.getRoot(), treeMapWith11ElemFor1Delete.getMax().parent.parent.parent);
        assertEquals(treeMapWith11ElemFor1Delete.getRoot(),
                treeMapWith11ElemFor1Delete.getRoot().right.right.left.right.parent.parent.parent.parent);
    }

    @Test
    public void testMapWith11Elements2Remove() {
        assertEquals(Integer.valueOf(7), treeMapWith11ElemFor2Delete.remove(7));
        assertEquals( Integer.valueOf(8), treeMapWith11ElemFor2Delete.remove(8));
        assertEquals(9, treeMapWith11ElemFor2Delete.size());

        assertEquals(new TreeMap.Node(10, 10), treeMapWith11ElemFor2Delete.getRoot().right.right);
        assertEquals(new TreeMap.Node(11, 11), treeMapWith11ElemFor2Delete.getRoot().right.right.right);
        assertEquals(new TreeMap.Node(9, 9), treeMapWith11ElemFor2Delete.getRoot().right.right.left);

        assertEquals(BLACK, treeMapWith11ElemFor2Delete.getRoot().right.left.color);
        assertEquals(RED, treeMapWith11ElemFor2Delete.getRoot().right.right.color);
        assertEquals(BLACK, treeMapWith11ElemFor2Delete.getRoot().right.right.left.color);
        assertEquals(BLACK, treeMapWith11ElemFor2Delete.getRoot().right.right.right.color);

        assertEquals(treeMapWith11ElemFor2Delete.getRoot(),
                treeMapWith11ElemFor2Delete.getRoot().right.right.left.parent.parent.parent);
    }

    @Test
    public void testMapWith11Elements3Remove() {
        assertEquals(Integer.valueOf(7), treeMapWith11ElemFor3Delete.remove(7));
        assertEquals(Integer.valueOf(8), treeMapWith11ElemFor3Delete.remove(8));
        assertEquals(Integer.valueOf(5), treeMapWith11ElemFor3Delete.remove(5));
        assertEquals(8, treeMapWith11ElemFor3Delete.size());

        assertEquals(new TreeMap.Node(10, 10), treeMapWith11ElemFor3Delete.getRoot().right);
        assertEquals(new TreeMap.Node(11, 11), treeMapWith11ElemFor3Delete.getRoot().right.right);
        assertEquals(new TreeMap.Node(6, 6), treeMapWith11ElemFor3Delete.getRoot().right.left);
        assertEquals(new TreeMap.Node(9, 9), treeMapWith11ElemFor3Delete.getRoot().right.left.right);

        assertEquals(BLACK, treeMapWith11ElemFor3Delete.getRoot().right.color);
        assertEquals(BLACK, treeMapWith11ElemFor3Delete.getRoot().right.right.color);
        assertEquals(BLACK, treeMapWith11ElemFor3Delete.getRoot().right.left.color);
        assertEquals(RED, treeMapWith11ElemFor3Delete.getRoot().right.left.right.color);

        assertEquals(treeMapWith11ElemFor3Delete.getRoot(),
                treeMapWith11ElemFor3Delete.getRoot().right.left.right.parent.parent.parent);
        assertEquals(treeMapWith11ElemFor3Delete.getRoot(),
                treeMapWith11ElemFor3Delete.getRoot().right.right.parent.parent);
    }





}
