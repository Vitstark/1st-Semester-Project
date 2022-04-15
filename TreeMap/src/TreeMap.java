import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class TreeMap<K extends Comparable<K>,V> {

    private int size;

    private Node<K, V> root; // корень

    private Comparator<K> comparator;

    private Node<K, V> min;

    private Node<K, V> max;


    public TreeMap(Collection<Node<K,V>> collection) {
        //конструктор, добавляющий все эелементы из коллекции
    }

    public TreeMap(Comparator<K> comparator) {
        //конструктор, инициализирующий comparator
    }

    public TreeMap() {
        //конструктор по умолчанию
    }

    private int parentOf(Node<K,V> kid) {
        // доводит ноду до совего родителя и возвращает число чёрных нод на пути
        return 0;
    }

    public V put() {
        return null;
    }

    public void putAll(Map<K,V> map) {
        // для реализации Java
    }

    public void putAll(TreeMap<K,V> map) {
        // для нашей реализации
    }

    public V remove() {
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

    public LinkedList<V> getSortedLinkedListOfValues() {
        //возвращает LinkedList<V> который содержит отсортированные по ключу значения, содержащиеся в нодах.
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static class Node<K extends Comparable<K>,V> {

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

    }

}
