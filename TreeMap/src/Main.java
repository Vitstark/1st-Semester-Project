public class Main {

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(0, "");
        map.put(8, "aa");
        map.put(-8, "-aa");
        map.put(4, "a");
        map.put(-4, "-a");

        System.out.println(map.values());
    }
}
