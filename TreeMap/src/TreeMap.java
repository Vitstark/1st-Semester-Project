public class TreeMap<K extends Comparable<K>,V> {

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static class Node<K extends Comparable<K>,V> {

        private K key;
        private V value;
        Node<K,V> leftKid;
        Node<K,V> rightKid;
        Node<K,V> parent;
        boolean color;

        public Node(K key, V value, boolean color) {
            // конструктор, инициализирующий поля
        }

        public K getKey() {
            // возвращает ключ
            return null;
        }

        public V getValue() {
            // возвращает значение
            return null;
        }

        @Override
        public boolean equals(Object o) {
            // сравнивает ноды
            return false;
        }

        @Override
        public String toString() {
            // возвращает строку в формате {ключ - значение}
            return null;
        }

    }

}
