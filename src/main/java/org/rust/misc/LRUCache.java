package org.rust.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement LRU (Least Recently Used) Cache.
 * @param <K> key type
 * @param <T> value type
 */
public class LRUCache<K, T> {
    private int capacity;
    private Map<K, Node> map;

    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
    }

    public T get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveHead(node);
            return node.data;
        }

        return null;
    }

    public void put(K key, T value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.data = value;
            moveHead(node);
        } else {
            Node node = new Node(key, value);

            map.put(key, node);
            moveTail(node);
            moveHead(node);

            if (map.size() > capacity) {
                map.remove(tail.key);
                removeTail();
            }
        }
    }

    private void moveTail(Node node) {
        if (tail != null)
            tail.next = node;

        node.prev = tail;
        node.next = null;

        tail = node;
    }

    private void moveHead(Node node) {
        // already in head
        if (node.prev == null) {
            head = node;
            return;
        }

        Node prev = node.prev;
        Node next = node.next;

        if (prev != null)
            prev.next = next;

        if (next != null)
            next.prev = prev;

        if (node == tail)
            tail = node.prev;

        node.prev = null;
        node.next = head;

        head.prev = node;

        head = node;
    }

    private void removeTail() {
        Node prev = tail.prev;

        tail.prev = null;

        if (prev != null)
            prev.next = null;

        tail = prev;
    }

    private class Node {
        K key;
        T data;
        Node prev;
        Node next;

        public Node(K key, T data) {
            this.key = key;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1

        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)

        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
