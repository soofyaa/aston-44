import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class MyArrayList<E> implements Iterable<E> {

    // Default capacity for the array
    private static final int DEFAULT_CAPACITY = 10;

    // Array to store elements and current size of the array
    private Object[] elements;
    private int size;

    // Constructor initializes the array with the default capacity
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Adds an element to the end of the list, resizing if necessary
    public boolean add(E e) {
        ensureCapacity();
        elements[size++] = e;
        return true;
    }

    // Adds all elements from a collection to the list
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    // Checks if the list contains a specified object
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return true;
            }
        }
        return false;
    }

    // Gets the element at a specified index
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    // Removes and returns the element at a specified index
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        E removedElement = (E) elements[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            // Shifts elements to fill the gap left by the removed element
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }

        elements[--size] = null;

        return removedElement;
    }

    // Removes the first occurrence of a specified object
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // Removes all elements in the collection from the list
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified |= remove(o);
        }
        return modified;
    }

    // Returns the current size of the list
    public int size() {
        return size;
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Ensures that the array has enough capacity to add elements
    private void ensureCapacity() {
        if (size == elements.length) {
            // Doubles the capacity of the array if it is full
            int newCapacity = elements.length * 2;
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }

    // Iterator implementation for iterating over the elements
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            // Checks if there are more elements in the list
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            // Returns the next element in the iteration
            @Override
            public E next() {
                return (E) elements[currentIndex++];
            }
        };
    }
}
