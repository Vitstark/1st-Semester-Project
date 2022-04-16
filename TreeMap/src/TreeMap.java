import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class TreeMap<K extends Comparable<K>, V> {

    private int size;

    private int blackHeight;

    private Node<K, V> root; // корень

    private Comparator<Node<K, V>> comparator;

    private Node<K, V> min;

    private Node<K, V> max;

    private final Node<K, V> LEAVE = new Node(null, null, BLACK);

    public TreeMap(Collection<Node<K, V>> collection) {
        //конструктор, добавляющий все элементы из коллекции
    }

    public TreeMap(Comparator<K> comparator) {
        //конструктор, инициализирующий comparator
    }

    public TreeMap() {
        //конструктор по умолчанию
    }

    private int compare(Node<K, V> first, Node<K, V> second) {
        if (comparator == null) {
            return first.compareTo(second);
        }
        return comparator.compare(first, second);
    }

    private Node<K,V> getUncle(Node<K,V> node) {
        Node<K,V> uncle = node.parent.parent;
        if (uncle.left == node.parent) {
            uncle = uncle.right;
        } else {
            uncle = uncle.left;
        }
        return uncle;
    }

    private void changeColorsOfRelatives(Node<K,V> node) {
        node.parent.color = BLACK;
        getUncle(node).color = BLACK;
        if (node.parent.parent != root) {
            node.parent.parent.color = RED;
        }
    }

    private void turnLeft(Node<K,V> oldRoot) {
        Node<K,V> newRoot = oldRoot.left;
        if (oldRoot.parent.left == oldRoot) {
            oldRoot.parent.left = newRoot;
        } else {
            oldRoot.parent.right = newRoot;
        }
        newRoot.parent = oldRoot.parent;
        oldRoot.parent = newRoot;

        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;

        newRoot.color = BLACK;
        oldRoot.color = RED;
    }

    private void turnRight(Node <K,V> oldRoot) {
        Node<K,V> newRoot = oldRoot.right;
        if (oldRoot.parent.left == oldRoot) {
            oldRoot.parent.left = newRoot;
        } else {
            oldRoot.parent.right = newRoot;
        }
        newRoot.parent = oldRoot.parent;
        oldRoot.parent = newRoot;

        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;

        newRoot.color = BLACK;
        oldRoot.color = RED;
    }

    private void balance(Node<K,V> node) {
        if (getUncle(node).color == RED) {
            do {
                changeColorsOfRelatives(node);
                node = node.parent.parent;
            } while (node.parent.color == RED && getUncle(node).color == RED);
        } else {
            if (node.parent == node.parent.parent.left) {
                turnLeft(node.parent.parent);
            } else {
                turnRight(node.parent.parent);
            }
        }

    }

    public V put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value, BLACK);
            size++;
            blackHeight += 2;
            min = root;
            max = root;
            root.left = new Node<>(root);
            root.right = new Node<>(root);
            return null;
        }

        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> cursor = root;

        while (!cursor.equals(LEAVE)) {
            if (compare(newNode, cursor) > 0) {
                cursor = cursor.right;
            } else if (compare(newNode, cursor) < 0) {
                cursor = cursor.left;
            } else if (compare(newNode, cursor) == 0) {
                if (newNode.getKey().equals(cursor.getKey())) {
                    if (newNode.equals(cursor)) {
                        return null;
                    }
                    V resultValue = cursor.value;
                    cursor.value = newNode.value;
                    return resultValue;
                } else {
                    cursor = cursor.right;
                }
            }
        }
        cursor = cursor.parent;

        if (compare(newNode, cursor) < 0) {
            cursor.left = newNode;
        } else {
            cursor.right = newNode;
        }
        newNode.color = RED;
        newNode.left = new Node<>(newNode);
        newNode.right = new Node<>(newNode);
        newNode.parent = cursor;

        size++;

        if (newNode.parent.color == RED) {
            balance(newNode);
        }

        return null;
    }

    public void putAll(Map<K, V> map) {
        // для реализации Java
    }

    public void putAll(TreeMap<K, V> map) {
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

    public LinkedList<Node<K, V>> getSortedLinkedList() {
        //возвращает LinkedList<V> который содержит отсортированные по ключу ноды.
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {

        private K key;
        private V value;
        boolean color;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, boolean color) {
            // конструктор, инициализирующий поля key, value, color
        }

        public Node(K key, V value) {
            // конструктор, инициализирующий поля key, value, color - красный
        }

        public Node() {
            // констурктор, инициализирует всё нуллями
        }

        public Node(Node<K, V> parent) {
            // конструктор, инициализирует только родителя и цвет в чёрный (лист)
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
