import java.util.*;

public class TreeMap<K extends Comparable<K>, V> {

    private int size;

    private Node<K, V> root; // корень

    private Comparator<K> comparator;

    private Node<K, V> min;

    private Node<K, V> max;

    private final Node<K, V> LEAVE = new Node(null, null, BLACK);

    public TreeMap(Collection<Node<K, V>> collection) { // Artyom
        // конструктор, добавляющий все элементы из коллекции
    }

    public TreeMap(Comparator<K> comparator) { // Artyom
        // конструктор, инициализирующий comparator
    }

    public TreeMap() { // Artyom
        // конструктор по умолчанию
    }

    private int compare(Node<K, V> first, Node<K, V> second) {
        if (comparator == null) {
            return first.compareTo(second);
        }
        return comparator.compare(first.key, second.key);
    }

    private Node<K, V> getUncle(Node<K, V> node) {
        Node<K, V> uncle = node.parent.parent;
        if (uncle.left == node.parent) {
            uncle = uncle.right;
        } else {
            uncle = uncle.left;
        }
        return uncle;
    }

    private void changeColorsOfRelatives(Node<K, V> node) {
        node.parent.color = BLACK;
        getUncle(node).color = BLACK;
        if (node.parent.parent != root) {
            node.parent.parent.color = RED;
        }
    }

    private void turnLeft(Node<K, V> oldRoot) {
        Node<K, V> newRoot = oldRoot.left;
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

    private void turnRight(Node<K, V> oldRoot) {
        Node<K, V> newRoot = oldRoot.right;
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

    private void balance(Node<K, V> node) {
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

        if (compare(newNode, max) >= 0) {
            max = newNode;
        }

        if (compare(newNode, min) < 0) {
            min = newNode;
        }

        return null;
    }

    private void deleteNodeWithOneKid(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (parent.left == node) {
            parent.left = node.left.equals(LEAVE) ? node.right : node.left;
            if (parent.color == RED && parent.left.color == RED) {
                parent.left.color = BLACK;
            }
        } else {
            parent.right = node.left.equals(LEAVE) ? node.right : node.left;
            if (parent.color == RED && parent.right.color == RED) {
                parent.right.color = BLACK;
            }
        }
    }

    private void deleteNodeWithTwoKids(Node<K, V> node) {
        Node<K, V> nearestNode = node.right;
        while (!nearestNode.left.equals(LEAVE)) {
            nearestNode = nearestNode.left;
        }

        node.key = nearestNode.key;
        node.value = nearestNode.value;
        if (node != root) {
            node.color = nearestNode.color;
        }
        nearestNode.parent.left = new Node<>(nearestNode.parent);

    }

    private void balanceAfterRemove(Node<K, V> node) {

    }

    public V remove(K key) {
        if (root == null) {
            return null;
        }

        Node<K, V> cursor = root;

        V value;

        while (!cursor.equals(LEAVE) && !cursor.key.equals(key)) {
            if (compare(new Node<>(key, null), cursor) < 0) {
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }

        if (cursor.equals(LEAVE)) {
            return null;
        }

        value = cursor.value;
        size--;

        if (cursor.equals(root) && size == 1) {
            root = null;
            return value;
        }

        if (cursor.left.equals(LEAVE) && cursor.right.equals(LEAVE)) {
            if (cursor.parent.left == cursor) {
                cursor.parent.left = null;
            } else {
                cursor.parent.right = null;
            }
        }

        if (cursor.left.equals(LEAVE) ^ cursor.right.equals(LEAVE)) {
            deleteNodeWithOneKid(cursor);
        }

        if (!cursor.left.equals(LEAVE) && !cursor.right.equals(LEAVE)) {
            deleteNodeWithTwoKids(cursor);
        }

        balanceAfterRemove(cursor);

        size--;
        return null;
    }

    public void putAll(TreeMap<K, V> map) { // Long
        // для нашей реализации
    }

    public V get(K key) {
        return null;
    } // Danya

    public boolean containsKey(K key) {
        return false;
    } // Danya

    public Collection<V> values() { // Long
        // попробуй реализовать с помощью getSortedList()
        return null;
    }

    public int size() {
        return size;
    }

    public List<Node<K, V>> getSortedList() { // Sanya
        // возвращает LinkedList<V> который содержит отсортированные по ключу ноды.
        return null;
    }

    @Override
    public String toString() {
        return null;
    } // Sanya

    public Node<K, V> getRoot() {
        return root;
    }

    public Node<K, V> getMin() {
        return min;
    }

    public Node<K, V> getMax() {
        return max;
    }

    public Comparator<K> getComparator() {
        return comparator;
    }

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> { // Dat

        private K key;
        private V value;
        boolean color;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, boolean color) {
            // конструктор, инициализирующий поля key, value, color
            this.key = key;
            this.value = value;
            this.color = color;

        }

        public Node(K key, V value) {
            // конструктор, инициализирующий поля key, value, color - красный
            this(key, value, RED);
        }

        public Node() {
            // констурктор, инициализирует всё нуллями
        }

        public Node(Node<K, V> parent) {
            // конструктор, инициализирует только родителя и цвет в чёрный (лист)
            this.key = null;
            this.value = null;
            this.color = BLACK;
            this.parent = parent;

        }

        public K getKey() {
            // возвращает ключ
            return key;
        }

        public V getValue() {
            // возвращает значение
            return value;
        }

        @Override
        public boolean equals(Object o) {
            // сравнивает ноды по ключу и значению
            if (this == o || this == null && o == null) {
                return true;
            }
            if ((this == null ^ o == null) || this.getClass() != o.getClass()) {
                return false;
            }

            Node<K, V> hihi = (Node<K, V>) o;
            if (this.key == null ^ hihi.key == null) {
                return false;
            }
            if (this.value == null ^ hihi.value == null) {
                return false;
            }

            if (this.key.equals(hihi.key) && this.value.equals(hihi.value)) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            // возвращает строку в формате {ключ - значение}
            return "{" + this.key + " - " + this.value + "}";
        }

        @Override
        public int compareTo(Node<K, V> node) {
            // возвращает compareTo ключей этой ноды с нодой в параметре
            return key.compareTo(node.getKey());
        }
    }

}
