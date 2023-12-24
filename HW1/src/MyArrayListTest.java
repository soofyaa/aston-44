import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    
    @Test
    void testAdd() {
        MyArrayList<String> myList = new MyArrayList<>();
        assertTrue(myList.add("One"));
        assertTrue(myList.add("Two"));
        assertEquals(2, myList.size());
        assertTrue(myList.contains("One"));
        assertTrue(myList.contains("Two"));
    }

    @Test
    void testAddAll() {
        MyArrayList<String> myList = new MyArrayList<>();
        List<String> otherList = Arrays.asList("One", "Two", "Three");
        assertTrue(myList.addAll(otherList));
        assertEquals(3, myList.size());
        assertTrue(myList.contains("One"));
        assertTrue(myList.contains("Two"));
        assertTrue(myList.contains("Three"));
    }

    @Test
    void testContains() {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("One");
        myList.add("Two");
        assertTrue(myList.contains("One"));
        assertTrue(myList.contains("Two"));
        assertFalse(myList.contains("Three"));
    }

    @Test
    void testGet() {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("One");
        myList.add("Two");
        assertEquals("One", myList.get(0));
        assertEquals("Two", myList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(2));
    }

    @Test
    void testRemoveByIndex() {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("One");
        myList.add("Two");
        assertEquals("One", myList.remove(0));
        assertEquals(1, myList.size());
        assertFalse(myList.contains("One"));
        assertEquals("Two", myList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(1));
    }

    @Test
    void testRemoveByObject() {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("One");
        myList.add("Two");
        assertTrue(myList.remove("One"));
        assertEquals(1, myList.size());
        assertFalse(myList.contains("One"));
        assertEquals("Two", myList.get(0));
        assertFalse(myList.remove("Three"));
    }

    @Test
    void testRemoveAll() {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("One");
        myList.add("Two");
        myList.add("Three");

        List<String> toRemove = Arrays.asList("One", "Three", "Four");
        assertTrue(myList.removeAll(toRemove));

        assertEquals(1, myList.size());
        assertFalse(myList.contains("One"));
        assertTrue(myList.contains("Two"));
        assertFalse(myList.contains("Three"));
    }

    @Test
    void testSize() {
        MyArrayList<String> myList = new MyArrayList<>();
        assertEquals(0, myList.size());
        myList.add("One");
        myList.add("Two");
        assertEquals(2, myList.size());
        myList.remove(0);
        assertEquals(1, myList.size());
    }

    @Test
    void testIsEmpty() {
        MyArrayList<String> myList = new MyArrayList<>();
        assertTrue(myList.isEmpty());
        myList.add("One");
        assertFalse(myList.isEmpty());
        myList.remove(0);
        assertTrue(myList.isEmpty());
    }
}
