import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class TreeMap<K extends Comparable<K>,V> {

    private int size;

    private int blackHeight;

    private Node<K, V> root; // корень

    private Comparator<K> comparator;

    private Node<K, V> min;

    private Node<K, V> max;


    public TreeMap(Collection<Node<K,V>> collection) {
        //конструктор, добавляющий все элементы из коллекции
    }

    public TreeMap(Comparator<K> comparator) {
        //конструктор, инициализирующий comparator
    }

    public TreeMap() {
        //конструктор по умолчанию
    }

    public V put(K key, V value) {
        return null;
    }

    public void putAll(Map<K,V> map) {
        // для реализации Java
    }

    public void putAll(TreeMap<K,V> map) {
        // для нашей реализации
    }

    public V remove(K key) {
        return null;
    }

    public V get(K key) {
        return null;
    }

    public boolean containsKey(K key) {
        return false;
    }

    public Collection<V> values() {
        return null;
    }

    public int size() {
        return 0;
    }

    public LinkedList<Node<K,V>> getSortedLinkedList() {
        //возвращает LinkedList<V> который содержит отсортированные по ключу ноды.
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static class Node<K extends Comparable<K>,V> implements Comparable<Node<K,V>> {

        private K key;
        private V value;
        boolean color;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key, V value, boolean color) {
            // конструктор, инициализирующий поля key, value, color
        }

        public Node(K key, V value){
            //конструктор, инициализирующий поля key, value, color - красный
        }

        public Node() {
            // инициализирует всё нуллями
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

        @Override
        public int compareTo(Node<K, V> node) {
            // возвращает compareTo ключей этой ноды с нодой в параметре
            return 0;
        }
    }

}
