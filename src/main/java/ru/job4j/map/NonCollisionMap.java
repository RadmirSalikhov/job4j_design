package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }

        int index = getIndexOfBucket(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = getIndexOfBucket(key);
        MapEntry<K, V> entry = table[index];
        if (entry != null && keysEqual(entry.key, key)) {
            return entry.value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndexOfBucket(key);
        MapEntry<K, V> entry = table[index];
        if (entry != null && keysEqual(entry.key, key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private int getIndexOfBucket(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        return indexFor(hash);
    }

    private boolean keysEqual(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2) && Objects.equals(key1, key2);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                int newIndex = getIndexOfBucket(entry.key);
                table[newIndex] = entry;
            }
        }
    }

    private static class MapEntry<K, V> {
        final K key;
        final V value;

        MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
