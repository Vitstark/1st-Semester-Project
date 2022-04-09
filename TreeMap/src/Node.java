class Node<K,V> {

    private K key;
    private V value;
    private int hashCode; // hashCode должен хранить хэш ключа

    public Node(K key, V value) {
        // конструктор, инициализирующий все поля
    }

    public K getKey() {
        // возвращает ключ
    }

    public V getValue() {
        // возвращает значение
    }

    @Override
    public int hashCode() {
        // возвращает hashCode
    }

    @Override
    public boolean equals(Object o) {
        // сравнивает ноды
    }

    @Override
    public String toString() {
        // возвращает строку в формате {ключ - значение}
    }
}
