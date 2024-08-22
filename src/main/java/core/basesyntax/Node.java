package core.basesyntax;

public class Node<K, V> {
    final K key;
    V value;
    public Node<K, V> next;
    Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
