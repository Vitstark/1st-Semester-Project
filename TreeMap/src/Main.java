public class Main {

    public static void main(String[] args) {
        TreeMap<Integer,Integer> map = new TreeMap<>();

        map.put(0, 0);
        map.put(8, 8);
        map.put(-8, -8);
        map.put(4, 4);
        map.put(-4, -4);

        System.out.println(map);
    }
}
