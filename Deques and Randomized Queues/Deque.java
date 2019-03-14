import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private void checkNullItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkEmptyList() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void addFirstNode(Node node) {
        head = node;
        tail = head;
    }

    public void addFirst(Item item) {
        checkNullItem(item);

        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            addFirstNode(newNode);
        } else {
            Node oldHead = head;
            newNode.next = oldHead;
            oldHead.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        checkNullItem(item);

        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            addFirstNode(newNode);
        } else {
            Node oldTail = tail;
            newNode.prev = oldTail;
            oldTail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        checkEmptyList();

        Item item = head.item;
        size--;
        if (isEmpty()) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        checkEmptyList();

        Item item = tail.item;
        size--;
        if (isEmpty()) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }

        for (Integer i : deque) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        int n = deque.size();
        for (int i = 0; i < n; i++) {
            StdOut.print(deque.removeLast() + " ");
        }
        StdOut.println();
    }
}
