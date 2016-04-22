package org.interviewelements.other;

// based on https://tekmarathon.com/2013/03/11/creating-our-own-hashmap-in-java/
public class CustomHashMap<K, V> {
    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry[] data;
    private int capacity = 16; //power of 2
//    private int size;

    public CustomHashMap() {
        data = new Entry[capacity];
    }

    private int supplementaryHash(int hash) {
        hash ^= (hash >>> 20) ^ (hash >>> 12);
        return hash ^ (hash >>> 7) ^ (hash >>> 4);
    }

    private int position(int hash) {
        return hash & (capacity - 1);
    }

    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("no nulls!!!!!");

        int hash = key.hashCode();
        hash = supplementaryHash(hash);

        int position = position(hash);
        for (Entry entry = data[position]; entry != null; entry = entry.next) {
            if (entry.key == entry.key || entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        data[position] = new Entry(key, value, data[position]);
    }

    public V get(K key) {
        if (key == null)
            throw new NullPointerException("no nulls!!!!!");

        int hash = key.hashCode();
        hash = supplementaryHash(hash);

        int position = position(hash);
        for (Entry<K, V> entry = data[position]; entry != null; entry = entry.next) {
            if (entry.key == entry.key || entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<Integer, String>();
        map.put(10, "Vasya");
        map.put(12, "Petya");

        System.out.println(map.get(10));
        System.out.println(map.get(11));
    }
}
