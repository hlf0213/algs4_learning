import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
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

    public void enqueue(Item item) {
        checkNullItem(item);

        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Item dequeue() {
        checkEmptyList();

        int idx = StdRandom.uniform(n);
        Item item = a[idx];
        a[idx] = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Item sample() {
        checkEmptyList();

        int idx = StdRandom.uniform(n);
        return a[idx];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    private class RandomListIterator implements Iterator<Item> {
        private int i;
        private int[] randomIndices;

        public RandomListIterator() {
            i = 0;
            randomIndices = new int[n];
            for (int i = 0; i < n; i++) {
                randomIndices[i] = i;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return a[randomIndices[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }

        for (Integer i : rq) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        int n = rq.size();
        for (int i = 0; i < n; i++) {
            StdOut.print(rq.dequeue() + " ");
        }
        StdOut.println();
    }
}

