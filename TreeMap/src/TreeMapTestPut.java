import org.junit.*;

import static org.junit.Assert.*;

public class TreeMapTestPut {

    public static TreeMap<Integer, Integer> emptyTreeMap;
    public static TreeMap<Integer, Integer> treeMapWithThreeElem;
    public static TreeMap<Integer, Integer> treeMapWithComparator;
    public static TreeMap<Integer, Integer> treeMapWith11Elem;
    public static TreeMap<Integer, Integer> treeMapWithThreeElemAfterRightTurn;
    public static TreeMap<Integer, Integer> treeMapWithThreeElemAfterLeftTurn;
    public static boolean BLACK = true;
    public static boolean RED = false;
    public static TreeMap.Node<Integer,Integer> LEAVE = new TreeMap.Node<>(null, null, BLACK);

    @Before
    public void init() {
        emptyTreeMap = new TreeMap<>();
        treeMapWithThreeElem = new TreeMap<>();
        treeMapWith11Elem = new TreeMap<>();
        treeMapWithThreeElemAfterRightTurn = new TreeMap<>();
        treeMapWithThreeElemAfterLeftTurn = new TreeMap<>();
        treeMapWithComparator = new TreeMap<>((x, y) -> {
            int first = Math.abs(x.intValue());
            int second = Math.abs(y.intValue());
            int length1 = 0;
            int length2 = 0;
            while (first > 0) {
                first /= 10;
                length1++;
            }
            while (second > 0) {
                second /= 10;
                length2++;
            }
            return length1 - length2;
        });

        treeMapWithThreeElem.put(2, 12);
        treeMapWithThreeElem.put(1, 10);
        treeMapWithThreeElem.put(3, 10);

        for (Integer i = 0; i < 3; i++) {
            treeMapWithThreeElemAfterRightTurn.put(i, i);
            treeMapWithThreeElemAfterLeftTurn.put(-i, i);
        }

        for (Integer i = 1; i < 12; i++) {
            treeMapWith11Elem.put(i, 0);
        }
    }

    @Test
    public void testInitialDefault() {
        assertEquals(0, emptyTreeMap.size());
        assertEquals(null, emptyTreeMap.getRoot());
        assertEquals(null, emptyTreeMap.getMax());
        assertEquals(null, emptyTreeMap.getMin());
    }

    @Test
    public void testInitialWithComparator() {
        assertEquals(0, treeMapWithComparator.size());
        assertEquals(null, treeMapWithComparator.getRoot());
        assertEquals(null, treeMapWithComparator.getMax());
        assertEquals(null, treeMapWithComparator.getMin());
        assertNotEquals(null, treeMapWithComparator.getComparator());
    }

    @Test
    public void testPutInEmpty() {
        emptyTreeMap.put(1, 1);
        assertEquals(1, emptyTreeMap.size());
        assertEquals(new TreeMap.Node<>(1, 1), emptyTreeMap.getRoot());
        assertEquals(new TreeMap.Node<>(1, 1), emptyTreeMap.getMax());
        assertEquals(new TreeMap.Node<>(1, 1), emptyTreeMap.getMin());
    }

    @Test
    public void testThreePutCheckMaxMin() {
        assertEquals(3, treeMapWithThreeElem.size());
        assertEquals(new TreeMap.Node<>(3, 10), treeMapWithThreeElem.getMax());
        assertEquals(new TreeMap.Node<>(1, 10), treeMapWithThreeElem.getMin());
        assertEquals(new TreeMap.Node<>(2, 12), treeMapWithThreeElem.getRoot());
    }

    @Test
    public void testThreePutCheckParents() {
        assertEquals(treeMapWithThreeElem.getRoot(), treeMapWithThreeElem.getMax().parent);
        assertEquals(treeMapWithThreeElem.getRoot(), treeMapWithThreeElem.getMin().parent);
        ;
    }

    @Test
    public void testThreePutCheckKids() {
        assertEquals(treeMapWithThreeElem.getMin(), treeMapWithThreeElem.getRoot().left);
        assertEquals(treeMapWithThreeElem.getMax(), treeMapWithThreeElem.getRoot().right);
    }

    @Test
    public void testThreePutCheckLeaves() {
        assertEquals(treeMapWithThreeElem.getMin().left, new TreeMap.Node<>(treeMapWithThreeElem.getMin()));
        assertEquals(treeMapWithThreeElem.getMin().right, new TreeMap.Node<>(treeMapWithThreeElem.getMin()));
    }

    @Test
    public void testThreePutCheckColors() {
        assertEquals(treeMapWithThreeElem.getRoot().color, BLACK);
        assertEquals(treeMapWithThreeElem.getMin().color, RED);
        assertEquals(treeMapWithThreeElem.getMax().color, RED);
    }

    @Test
    public void testMapAfterTurnCheckMaxMin() {
        assertEquals(new TreeMap.Node(0, 0), treeMapWithThreeElemAfterRightTurn.getMin());
        assertEquals(new TreeMap.Node(2, 2), treeMapWithThreeElemAfterRightTurn.getMax());
        assertEquals(new TreeMap.Node(0, 0), treeMapWithThreeElemAfterLeftTurn.getMax());
        assertEquals(new TreeMap.Node(-2, 2), treeMapWithThreeElemAfterLeftTurn.getMin());
    }

    @Test
    public void testMapAfterTurnCheckHierarchy() {
        assertEquals(new TreeMap.Node(1, 1), treeMapWithThreeElemAfterRightTurn.getRoot());
        assertEquals(new TreeMap.Node(-1, 1), treeMapWithThreeElemAfterLeftTurn.getRoot());
        assertEquals(new TreeMap.Node(0, 0), treeMapWithThreeElemAfterRightTurn.getRoot().left);
        assertEquals(new TreeMap.Node(-2, 2), treeMapWithThreeElemAfterLeftTurn.getRoot().left);
        assertEquals(new TreeMap.Node(2, 2), treeMapWithThreeElemAfterRightTurn.getRoot().right);
        assertEquals(new TreeMap.Node(0, 0) ,treeMapWithThreeElemAfterLeftTurn.getRoot().right);
    }

    @Test
    public void testMapAfterTurnCheckColors() {
        assertEquals(BLACK, treeMapWithThreeElemAfterLeftTurn.getRoot().color);
        assertEquals(BLACK, treeMapWithThreeElemAfterRightTurn.getRoot().color);
        assertEquals(RED, treeMapWithThreeElemAfterLeftTurn.getRoot().left.color);
        assertEquals(RED, treeMapWithThreeElemAfterRightTurn.getRoot().left.color);
        assertEquals(RED, treeMapWithThreeElemAfterLeftTurn.getRoot().right.color);
        assertEquals(RED, treeMapWithThreeElemAfterRightTurn.getRoot().right.color);
    }

    @Test
    public void testMapAfterTurnCheckParents() {
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getRoot(), treeMapWithThreeElemAfterLeftTurn.getMin().parent);
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getRoot(), treeMapWithThreeElemAfterLeftTurn.getMax().parent);
        assertEquals(treeMapWithThreeElemAfterRightTurn.getRoot(), treeMapWithThreeElemAfterRightTurn.getMin().parent);
        assertEquals(treeMapWithThreeElemAfterRightTurn.getRoot(), treeMapWithThreeElemAfterRightTurn.getMax().parent);
    }

    @Test
    public void testMapAfterTurnCheckLeaves() {
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMin().left, LEAVE);
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMin().right, LEAVE);
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMax().left, LEAVE);
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMax().right, LEAVE);

        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMin().left.parent, treeMapWithThreeElemAfterLeftTurn.getMin());
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMin().right.parent, treeMapWithThreeElemAfterLeftTurn.getMin());
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMax().left.parent, treeMapWithThreeElemAfterLeftTurn.getMax());
        assertEquals(treeMapWithThreeElemAfterLeftTurn.getMax().right.parent, treeMapWithThreeElemAfterLeftTurn.getMax());

        assertEquals(treeMapWithThreeElemAfterRightTurn.getMin().left, LEAVE);
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMin().right, LEAVE);
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMax().left, LEAVE);
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMax().right, LEAVE);

        assertEquals(treeMapWithThreeElemAfterRightTurn.getMin().left.parent, treeMapWithThreeElemAfterRightTurn.getMin());
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMin().right.parent, treeMapWithThreeElemAfterRightTurn.getMin());
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMax().left.parent, treeMapWithThreeElemAfterRightTurn.getMax());
        assertEquals(treeMapWithThreeElemAfterRightTurn.getMax().right.parent, treeMapWithThreeElemAfterRightTurn.getMax());
    }

    @Test
    public void testMapWith11ElemCheckRoot() {
        assertEquals(new TreeMap.Node<>(4, 0), treeMapWith11Elem.getRoot());
    }

    @Test
    public void testMapWith11ElemCheckMaxMin() {
        assertEquals(new TreeMap.Node<>(1, 0), treeMapWith11Elem.getMin());
        assertEquals(new TreeMap.Node<>(11, 0), treeMapWith11Elem.getMax());
    }

    @Test
    public void testMapWith11ElemCheckParents() {
        assertEquals(treeMapWith11Elem.getRoot(), treeMapWith11Elem.getMin().parent.parent);
        assertEquals(treeMapWith11Elem.getRoot(), treeMapWith11Elem.getMax().parent.parent.parent.parent);
        assertEquals(treeMapWith11Elem.getRoot(), treeMapWith11Elem.getRoot().left.right.parent.parent);
        assertEquals(treeMapWith11Elem.getRoot(), treeMapWith11Elem.getRoot().right.right.left.parent.parent.parent);
        assertEquals(treeMapWith11Elem.getRoot(), treeMapWith11Elem.getRoot().right.left.parent.parent);
    }

    @Test
    public void testMapWith11ElemCheckLeaves() {
        assertEquals(LEAVE, treeMapWith11Elem.getMin().left);
        assertEquals(LEAVE, treeMapWith11Elem.getMin().right);
        assertEquals(LEAVE, treeMapWith11Elem.getMax().left);
        assertEquals(LEAVE, treeMapWith11Elem.getMax().right);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().left.right.left);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().left.right.right);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.left.left);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.left.right);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.left.left);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.left.right);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.right.left.left);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.right.left.right);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.right.right.left);
        assertEquals(LEAVE, treeMapWith11Elem.getRoot().right.right.right.right.right);
    }

    @Test
    public void testMapWith11ElemCheckColors() {
        assertEquals(BLACK, treeMapWith11Elem.getRoot().color);

        assertEquals(BLACK, treeMapWith11Elem.getRoot().left.color);
        assertEquals(BLACK, treeMapWith11Elem.getRoot().left.left.color);
        assertEquals(BLACK, treeMapWith11Elem.getRoot().left.right.color);

        assertEquals(BLACK, treeMapWith11Elem.getRoot().right.color);
        assertEquals(RED, treeMapWith11Elem.getRoot().right.right.color);
        assertEquals(BLACK, treeMapWith11Elem.getRoot().right.right.right.color);
        assertEquals(RED, treeMapWith11Elem.getRoot().right.right.right.right.color);
        assertEquals(BLACK, treeMapWith11Elem.getRoot().right.left.color);
        assertEquals(BLACK, treeMapWith11Elem.getRoot().right.right.left.color);
    }

}