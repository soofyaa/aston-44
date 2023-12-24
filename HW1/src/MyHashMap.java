import java.util.Map;
import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // Constants for default capacity and load factor
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Instance variables
    private int size;
    private int capacity;
    private List<Entry<K, V>>[] table;

    // Constructor initializes the capacity and table array
    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new LinkedList[capacity];
    }

    // Calculates hash, finds index, and inserts/updates the entry
    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = getIndex(hash);

        // If the bucket is null, create a new LinkedList for it
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        // Check if the key already exists, update value if it does
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        // If the key doesn't exist, add a new entry to the bucket
        table[index].add(new Entry<>(key, value));
        size++;

        // Check if a resize is needed based on load factor
        if ((float) size / capacity > LOAD_FACTOR) {
            resize();
        }

        return null;
    }

    // Adds all entries from another map to this map
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    // Clears the map by setting all buckets to null and size to 0
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    // Removes and returns the value associated with the specified key
    @Override
    public V remove(Object key) {
        int hash = hash(key);
        int index = getIndex(hash);

        // Iterate through the bucket to find and remove the entry
        if (table[index] != null) {
            Iterator<Entry<K, V>> iterator = table[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    iterator.remove();
                    size--;
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    // Returns the size of the map
    @Override
    public int size() {
        return size;
    }

    // Checks if the map contains the specified key
    @Override
    public boolean containsKey(Object key) {
        int hash = hash(key);
        int index = getIndex(hash);

        // Check if the bucket exists and contains the key
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Checks if the map contains the specified value
    @Override
    public boolean containsValue(Object value) {
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Returns the value associated with the specified key
    @Override
    public V get(Object key) {
        int hash = hash(key);
        int index = getIndex(hash);

        // Check if the bucket exists and contains the key
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    // Checks if the map is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns a set of all keys in the map
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keySet.add(entry.getKey());
                }
            }
        }
        return keySet;
    }

    // Returns a collection of all values in the map
    @Override
    public Collection<V> values() {
        List<V> valuesList = new ArrayList<>();

        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    valuesList.add(entry.getValue());
                }
            }
        }

        return valuesList;
    }

    // Returns a set of all entries (key-value pairs) in the map
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                entrySet.addAll(bucket);
            }
        }
        return entrySet;
    }

    // Calculates the hash code for a key, or 0 if key is null
    private int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    // Computes the index in the table for a given hash code
    private int getIndex(int hash) {
        return hash % capacity;
    }

    // Resizes the table when the load factor is exceeded
    private void resize() {
        capacity *= 2;
        LinkedList[] newTable = new LinkedList[capacity];

        // Rehash all entries into the new larger table
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int hash = hash(entry.getKey());
                    int index = getIndex(hash);

                    // If the bucket is null, create a new LinkedList for it
                    if (newTable[index] == null) {
                        newTable[index] = new LinkedList<>();
                    }

                    // Add the entry to the new bucket in the resized table
                    newTable[index].add(entry);
                }
            }
        }

        // Update the table reference to the new resized table
        table = newTable;
    }

    // Nested class representing a key-value entry in the map
    static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        // Entry constructor
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // Getter for the key
        @Override
        public K getKey() {
            return key;
        }

        // Getter for the value
        @Override
        public V getValue() {
            return value;
        }

        // Setter for the value, returns the old value
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        // Equals method for comparing entries
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        // Hash code calculation for the entry
        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
