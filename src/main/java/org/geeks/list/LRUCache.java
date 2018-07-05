package org.geeks.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.

 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 *      it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache <K, V> {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.set(1, "hello");
        lruCache.set(2, "bye");
    }

    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity should be > 0");

        this.capacity = capacity;
    }

    private final Map<K, Node> cache = new HashMap<>();
    private final int capacity;
    private Node head;
    private Node tail;

    public K get(int key) {
        if (!cache.containsKey(key))
            return null;

        Node node = cache.get(key);
        head(node);

        return node.key;
    }

    public void set(K key, V value) {
        if (!cache.containsKey(key)) {
            Node node = new Node(key, value);
            add(node);
        } else {
            Node node = cache.get(key);
            node.value = value;
            head(node);
        }
    }

    private void add(Node node) {
        if (cache.size() >= capacity)
            remove(tail);

        cache.put(node.key, node);

        head(node);
    }

    private void remove(Node node) {
        if (node == null)
            return;

        Node prev = node.prev;
        Node next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = null;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }

        cache.remove(node.key);
    }

    private void head(Node node) {
        if (node == head)
            return;

        Node prev = node.prev;
        Node next = node.next;

        if (prev != null)
            prev.next = next;

        if (next != null)
            next.prev = prev;

        node.prev = null;
        node.next = head;

        if (head != null)
            head.prev = node;

        head = node;

        if (tail == null)
            tail = node;
    }
}
