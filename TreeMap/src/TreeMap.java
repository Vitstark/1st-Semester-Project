public class TreeMap {
    static class Node<K,V> {

        private K key;
        private V value;
        private int hashCode; // hashCode должен хранить хэш ключа

        public Node(K key, V value) {
            // конструктор, инициализирующий все поля
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
        public int hashCode() {
            // возвращает hashCode
            return 0;
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
