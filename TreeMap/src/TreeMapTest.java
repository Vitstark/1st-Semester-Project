import com.sun.source.tree.Tree;
import org.junit.*;
import static org.junit.Assert.*;

public class TreeMapTest {

    public static TreeMap<Integer, Integer> emptyTreeMap;
    public static TreeMap<Integer, Integer> treeMapWithThreeElem;
    public static TreeMap<Integer, Integer> treeMapWithComparator;

    @Before
    public void init() {
        emptyTreeMap = new TreeMap<>();
        treeMapWithThreeElem = new TreeMap<>();
        treeMapWithThreeElem.put(2, 12);
        treeMapWithThreeElem.put(1, 10);
        treeMapWithThreeElem.put(3, 10);
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
        assertEquals(null, treeMapWithComparator.getComparator());
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
    public void testMultiplyPutCheckMaxMin() {
        assertEquals(3, treeMapWithThreeElem.size());
        assertEquals(new TreeMap.Node<>(3, 3), treeMapWithThreeElem.getMax());
        assertEquals(new TreeMap.Node<>(1, 1), treeMapWithThreeElem.getMin());
        assertEquals(new TreeMap.Node<>(2, 2), treeMapWithThreeElem.getRoot());
    }

    @Test
    public void testMultiplyPutCheckParents() {
        assertEquals(treeMapWithThreeElem.getRoot(), treeMapWithThreeElem.getMax().parent);
        assertEquals(treeMapWithThreeElem.getRoot(), treeMapWithThreeElem.getMin().parent);;
    }

    @Test
    public void testMultiplyPutCheckKids() {
        assertEquals(treeMapWithThreeElem.getMin(), treeMapWithThreeElem.getRoot().left);
        assertEquals(treeMapWithThreeElem.getMax(), treeMapWithThreeElem.getRoot().right);
    }

    @Test
    public void testMultiplyPutCheckLeaves() {
        assertEquals(treeMapWithThreeElem.getMin().left, new TreeMap.Node<>(treeMapWithThreeElem.getMin()));
        assertEquals(treeMapWithThreeElem.getMin().right, new TreeMap.Node<>(treeMapWithThreeElem.getMin()));
    }

    public void testMultiplyPutCheckColors() {
        assertEquals(treeMapWithThreeElem.getRoot().color, true);
        assertEquals(treeMapWithThreeElem.getMin().color, false);
        assertEquals(treeMapWithThreeElem.getMax().color, false);
    }


}
