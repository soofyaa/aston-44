import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void testPutAndGet() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        assertEquals(1, hashMap.get("one"));
        assertEquals(2, hashMap.get("two"));
        assertNull(hashMap.get("three"));
    }

    @Test
    void testPutOverride() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        assertEquals(1, hashMap.get("one"));

        hashMap.put("one", 100);
        assertEquals(100, hashMap.get("one"));
    }

    @Test
    void testRemove() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        assertEquals(2, hashMap.size());
        hashMap.remove("one");
        assertEquals(1, hashMap.size());
    }

    @Test
    void testSizeAndClear() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        assertTrue(hashMap.isEmpty());
        assertEquals(0, hashMap.size());

        hashMap.put("one", 1);
        hashMap.put("two", 2);
        assertFalse(hashMap.isEmpty());
        assertEquals(2, hashMap.size());

        hashMap.clear();
        assertTrue(hashMap.isEmpty());
        assertEquals(0, hashMap.size());
    }

    @Test
    void testContains() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        assertTrue(hashMap.containsKey("one"));
        assertTrue(hashMap.containsValue(2));
        assertFalse(hashMap.containsKey("three"));
        assertFalse(hashMap.containsValue(3));
    }

    @Test
    void testKeySet() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        Set<String> keySet = hashMap.keySet();
        assertTrue(keySet.contains("one"));
        assertTrue(keySet.contains("two"));
        assertFalse(keySet.contains("three"));
    }

    @Test
    void testValues() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        Set<Integer> valuesSet = Set.copyOf(hashMap.values());
        assertTrue(valuesSet.contains(1));
        assertTrue(valuesSet.contains(2));
        assertFalse(valuesSet.contains(3));
    }

    @Test
    void testEntrySet() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        assertTrue(entrySet.contains(new MyHashMap.Entry<>("one", 1)));
        assertTrue(entrySet.contains(new MyHashMap.Entry<>("two", 2)));
        assertFalse(entrySet.contains(new MyHashMap.Entry<>("three", 3)));
    }
}
