import org.jfree.data.xy.XYSeries;

import java.time.Duration;
import java.time.Instant;
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

        Instant start;
        Instant finish;

        long strt;
        long fnsh;

        for (Integer i = 0; i < 100; i++){
            start = Instant.now();
            strt = System.nanoTime();
            map.put(i, i);
            finish = Instant.now();
            fnsh = System.nanoTime();
            System.out.println(i.intValue() + " " + (fnsh - strt));
        }
    }
}
