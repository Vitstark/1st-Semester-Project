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
        this();
        if (collection == null) {
            throw new RuntimeNullPointerException("collection is null");
        }
        for (Node<K, V> node : collection) {
            put(node.key, node.value);
        }
    }

    public TreeMap(Comparator<K> comparator) { // Artyom
        // конструктор, инициализирующий comparator
        this();
        if (comparator == null) {
            throw new RuntimeNullPointerException("comparator is null");
        }
        this.comparator = comparator;
    }

    public TreeMap() { // Artyom
        // конструктор по умолчанию
        this.size = 0;
        this.root = null;
        this.comparator = null;
        this.min = null;
        this.max = null;
    }

    private int compare(Node<K, V> first, Node<K, V> second) {
        if (comparator == null) {
            return first.compareTo(second);
        }
        return comparator.compare(first.key, second.key);
    }

    public V put(K key, V value) { // Vitalii
        if (key == null) {
            throw new RuntimeNullPointerException("key can`t be null");
        }

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
            balanceAfterPut(newNode);
        }

        if (compare(newNode, max) >= 0) {
            max = newNode;
        }

        if (compare(newNode, min) < 0) {
            min = newNode;
        }

        return null;
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

    private Node<K, V> getBrother(Node<K, V> node) {
        Node<K, V> brother = node.parent;
        if (brother.left == node) {
            brother = brother.right;
        } else {
            brother = brother.left;
        }
        return brother;
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
        if (oldRoot != root) {
            if (oldRoot.parent.left == oldRoot) {
                oldRoot.parent.left = newRoot;
            } else {
                oldRoot.parent.right = newRoot;
            }
        } else {
            root = newRoot;
        }
        newRoot.parent = oldRoot.parent;
        oldRoot.parent = newRoot;

        oldRoot.left = newRoot.right;
        oldRoot.left.parent = oldRoot;
        newRoot.right = oldRoot;
    }

    private void turnRight(Node<K, V> oldRoot) {
        Node<K, V> newRoot = oldRoot.right;
        if (oldRoot != root) {
            if (oldRoot.parent.left == oldRoot) {
                oldRoot.parent.left = newRoot;
            } else {
                oldRoot.parent.right = newRoot;
            }
        } else {
            root = newRoot;
        }
        newRoot.parent = oldRoot.parent;
        oldRoot.parent = newRoot;

        oldRoot.right = newRoot.left;
        oldRoot.right.parent = oldRoot;
        newRoot.left = oldRoot;
    }

    private void balanceAfterPut(Node<K, V> node) {
        if (getUncle(node).color == RED) {
            do {
                changeColorsOfRelatives(node);
                node = node.parent.parent;
            } while (node.color == RED && node.parent.color == RED && getUncle(node).color == RED);
        }
        if (node.color == RED && node.parent.color == RED && getUncle(node).color == BLACK) {
            if (node.parent == node.parent.parent.left) {
                turnLeft(node.parent.parent);
                node.parent.right.color = RED;
            } else {
                turnRight(node.parent.parent);
                node.parent.left.color = RED;
            }
            node.parent.color = BLACK;
        }
    }

    public V remove(K key) { // Vitalii
        if (root == null) {
            return null;
        }

        Node<K, V> cursor = binarySearch(key);
        if (cursor.equals(LEAVE)) {
            return null;
        }

        V value = cursor.value;
        boolean colorOfDeletedNode = cursor.color;
        size--;

        if (cursor.equals(root) && size == 0) {
            root = null;
            min = null;
            max = null;
            return value;
        }

        if (cursor == max) {
            if (cursor == root) {
                max = max.left;
            } else {
                max = max.parent;
            }
        }

        if (cursor == min) {
            if (cursor == root) {
                min = min.right;
            } else {
                min = min.parent;
            }
        }

        if (cursor.left.equals(LEAVE) && cursor.right.equals(LEAVE)) {
            cursor = deleteNodeWithoutKids(cursor);
        }
        if (!cursor.equals(LEAVE) && (cursor.left.equals(LEAVE) ^ cursor.right.equals(LEAVE))) {
            cursor = deleteNodeWithOneKid(cursor);
        }
        if (!cursor.equals(LEAVE) && (!cursor.left.equals(LEAVE) && !cursor.right.equals(LEAVE))) {
            colorOfDeletedNode = deleteNodeWithTwoKids(cursor);
        }

        if (colorOfDeletedNode == BLACK) {
            balanceAfterRemove(cursor);
        }

        return value;
    }

    private Node<K,V> deleteNodeWithoutKids(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (node.parent.left == node) {
            parent.left = new Node(parent);
            node = parent.left;
        } else {
            parent.right = new Node(parent);
            node = parent.right;
        }
        return node;
    }

    private Node<K,V> deleteNodeWithOneKid(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (parent.left == node) {
            parent.left = node.left.equals(LEAVE) ? node.right : node.left;
            parent.left.parent = parent;
            node = parent.left;
            if (parent.color == RED && parent.left.color == RED) {
                parent.left.color = BLACK;
            }
        } else {
            parent.right = node.left.equals(LEAVE) ? node.right : node.left;
            parent.right.parent = parent;
            node = parent.right;
            if (parent.color == RED && parent.right.color == RED) {
                parent.right.color = BLACK;
            }
        }
        return node;
    }

    private boolean deleteNodeWithTwoKids(Node<K, V> node) {
        Node<K, V> nearestNode = node.right;

        while (!nearestNode.left.equals(LEAVE)) {
            nearestNode = nearestNode.left;
        }

        node.key = nearestNode.key;
        node.value = nearestNode.value;

        if (node != root) {
            node.color = nearestNode.color;
        }

        boolean colorOfDeletedNode = nearestNode.color;
        if (nearestNode.right.equals(LEAVE)) {
            deleteNodeWithoutKids(nearestNode);
        } else {
            deleteNodeWithOneKid(nearestNode);
        }
        node = nearestNode;
        return colorOfDeletedNode;
    }

    private void balanceIfNodeHas3BlackRelatives(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);
        do {
            brother.color = RED;
            node.parent.color = BLACK;
            brother = getBrother(node);
        } while (node != null && node.color == BLACK && brother.color == BLACK
                && brother.left.color == BLACK && brother.right.color == BLACK);
    }

    private void balanceIfOutsideNephewOfLeftBrotherIsRED(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);
        turnLeft(node.parent);

        boolean colorOfBrother = brother.color;

        brother.color = brother.right.color;
        brother.right.color = colorOfBrother;
        brother.left.color = BLACK;
    }

    private void balanceIfOutsideNephewOfRightBrotherIsRED(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);
        turnRight(node.parent);

        boolean colorOfBrother = brother.color;

        brother.color = brother.left.color;
        brother.left.color = colorOfBrother;
        brother.right.color = BLACK;
    }

    private void balanceIfInsideNephewOfLeftBrotherIsRED(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);
        turnLeft(brother);
        brother.color = RED;
        brother.parent.color = BLACK;
        balanceIfOutsideNephewOfLeftBrotherIsRED(node);
    }

    private void balanceIfInsideNephewOfRightBrotherIsRED(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);
        turnRight(brother);
        brother.color = RED;
        brother.parent.color = BLACK;
        balanceIfOutsideNephewOfRightBrotherIsRED(node);
    }

    private void balanceAfterRemove(Node<K, V> node) {
        Node<K, V> brother = getBrother(node);

        if (brother.color == RED) {
            if (node.parent.right == brother) {
                turnRight(node.parent);
            } else {
                turnLeft(node.parent);

            }
            brother.color = RED;
            node.parent.color = RED;
        }

        brother = getBrother(node);
        if (brother.color == BLACK && brother.left.color == BLACK
                && brother.right.color == BLACK) {
            balanceIfNodeHas3BlackRelatives(node);
            return;
        }

        if (brother.color == BLACK) {
            if (node.parent.right == brother) {
                if (brother.right.color == RED) {
                    balanceIfOutsideNephewOfRightBrotherIsRED(node);
                } else if (brother.left.color == RED)
                    balanceIfInsideNephewOfRightBrotherIsRED(node);
            } else {
                if (brother.left.color == RED) {
                    balanceIfOutsideNephewOfLeftBrotherIsRED(node);
                } else if (brother.right.color == RED) {
                    balanceIfInsideNephewOfLeftBrotherIsRED(node);
                }
            }
        }
    }

    private Node<K, V> binarySearch(K key) {
        Node<K, V> cursor = root;
        Node<K, V> necessaryNode = new Node(key, null);
        while (!cursor.equals(LEAVE) && !cursor.key.equals(key)) {
            if (compare(necessaryNode, cursor) < 0) {
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }
        return cursor;
    }

    public void putAll(TreeMap<K, V> map) { // Long
        // для нашей реализации
    }

    public V get(K key) {

        Node<K,V> n = root;

        while (n != LEAVE){

            int cKeyVal = key.compareTo(n.key);

            if (cKeyVal == 0){

                return n.value;

            }
            if (cKeyVal > 0){

                n = n.right;

            }
            if (cKeyVal < 0){

                n = n.left;

            }

        }

        return null;

    }

    public boolean containsKey(K key) {

        Node<K, V> cursor = root;

        while (cursor != LEAVE){

                int cKeyVal = cursor.key.compareTo(key);

                if (cKeyVal == 0){

                    return true;

                }
                if (cKeyVal > 0){

                    cursor = cursor.right;

                }
                if (cKeyVal < 0){

                    cursor = cursor.left;

                }

            }
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
        Stack<Node> stack = new Stack<> ();
        List<Node<K, V>> list = new ArrayList<>(size);

        Node<K, V> node = root; //= new Node<>();

        while (node!=null || !stack.empty()){
            if (!stack.empty()){
                node=stack.pop();
                if (node.getKey() != null) list.add(node);
                if (node.right!=null) node=node.right;
                else node=null;
            }
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
        }

        return list;
    }

    @Override
    public String toString() {
        return getSortedList().toString();
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
            if (this.key == null ^ hihi.key == null || this.value == null ^ hihi.value == null) {
                return false;
            }

            if (this.key == null && hihi.key == null || this.value == null && hihi.value == null) {
                return true;
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
