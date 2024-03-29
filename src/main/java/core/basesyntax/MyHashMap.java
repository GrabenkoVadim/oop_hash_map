package core.basesyntax;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    public final int DEFAULT_CAPACITY = 16;
    public final int RESIZE_FACTOR = 2;
    public final double LOAD_FACTOR = 0.75;
    private int size;
    public Node<K, V>[] table;
    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public void put(K key, V value) {
        if (size == table.length * LOAD_FACTOR) {
            resize();
        }
        Node<K, V> nextNode = table[getKeyPosition(key)];
        while (nextNode != null) {
            if (Objects.equals(nextNode.key, key)) {
                nextNode.value = value;
                return;
            }
            if (nextNode.next == null) {
                nextNode.next = new Node<>(key, value, null);
                size++;
                return;
            }
            nextNode = nextNode.next;
        }
        table[getKeyPosition(key)] = new Node<>(key, value, null);
        size++;
    }

    @Override
    public V getValue(K key) {
        Node<K, V> node = table[getKeyPosition(key)];
        while (node != null) {
            if (Objects.equals(key, node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getKeyPosition(K key) {
        if (key != null) {
            return Math.abs(key.hashCode() % table.length);
        }
        return 0;
    }

    public void resize() {
        Node<K, V>[] pastTable = table;
        table = new Node[pastTable.length * RESIZE_FACTOR];
        size = 0;
        for (Node<K, V> node : pastTable) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
}