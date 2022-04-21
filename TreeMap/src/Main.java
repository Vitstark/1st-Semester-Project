import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

public class Main {

    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>(new MyComparator());
        for (Integer i = 5; i < 25; i++) {
            treeMap.put(i.toString(), i.toString());
        }

        System.out.println(treeMap);

    }
}
