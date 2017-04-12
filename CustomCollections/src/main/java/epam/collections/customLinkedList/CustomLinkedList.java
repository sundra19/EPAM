package epam.collections.customLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomLinkedList<T> implements List<T> {

    private Node<T> head = new Node<>(null);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

    @Override
    public boolean contains(Object o) {
        Node<T> node = head;
        while (node.hasNext()) {
            node = node.next;
            if (node.value == null) {
                if (o == null) {
                    return true;
                }
            } else if (node.value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current.hasNext();
            }
            @Override
            public T next() {
                current = current.next;
                return current.value;
            }
        };
    }
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        return toArray(array);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        int index = 0;
        while (current.hasNext()) {
            current = current.next;
            a[index] = (T1)current.value;
            index += 1;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        Node<T> iterator = head;

        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new Node<>(t);
        size++;
        return false;

    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = head.next;
        Node<T> prev = head;
        while (current != null) {
            if (o.equals(current.value)) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = new Node<>(null);
        size = 0;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        getNodeByIndex(index).value = element;
        return getNodeByIndex(index).value;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> previous = getNodeByIndex(index - 1);
        Node<T> insertNode = new Node<T>(element);

        insertNode.next = previous.next;
        previous.next = insertNode;

        size++;
    }

    @Override
    public T remove(int index) {
        Node<T> current = getNodeByIndex(index - 1);
        size--;
        T value = current.next.value;
        current.next = current.next.next;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private Node<T> getNodeByIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


    private class Node<T> {

        private Node<T> next;
        private T value;

        Node(T value) {
            this.value = value;
        }

        boolean hasNext() {
            return next != null;
        }

    }
}
