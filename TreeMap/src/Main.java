import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

/*
    2        8
   / \      /
  1   3    4

    2                 2                     2
   / \               / \                   / \
  1   3             1   3                 1   3
       \                 \                     \
        4                 4
                           \
                            8
 */


public class Main {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (Integer i = 1; i < 12; i++) {
            map.put(i,i);
        }

        System.out.println(map);
    }
}
