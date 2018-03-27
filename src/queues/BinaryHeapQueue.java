// file: heap/queues/BinaryHeapQueue.java

// user of this class is responsible for heapify the heap to properly use it
package queues;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryHeapQueue<T extends Comparable<T>> {
    ArrayList<Integer> values;
    public int total; // helper for heapify stuff

    public BinaryHeapQueue() {
        values = new ArrayList<Integer>();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public void insertUnordered(int elem) {
        values.add(values.size(), elem);
        // stuff below doesn't work - looked good though
        //values.set(values.size(), elem);
        //values[values.size()] = elem;
    }

    public void insertUnordered(int[] elemArray) {
        for (int i = 0; i < elemArray.length; i++) {
            values.add(values.size(), elemArray[i]);
        }
    }

    public int max() {
        checkIfEmpty();
        assert isHeap();
        return (Integer) values.get(0);
    }

    public int removeMax() {
        checkIfEmpty();
        assert isHeap();
        int root = (Integer) values.get(0);
        values.remove(0);
        heapify();
        return root;
    }

    public int[] removeNLargest(int n) {
        checkIfEmpty();
        assert isHeap();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (Integer) values.get(i);
        }
        // ArrayList is MIGHTY!!
        //values.removeRange(0, n); -> to good be true
        values.subList(0, n).clear(); // fits the bill as well
        heapify();
        return result;
    }

    public int[] nLargest(int n) {
        checkIfEmpty();
        assert isHeap();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (Integer) values.get(i);
        }
        return result;
    }

    private boolean isHeap() {
        for (int i = 0; i < values.size(); i++) {
            if ((Integer) values.get(i / 2) < ((Integer) values.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return values.size();
    }

    public void merge(BinaryHeapQueue queue) {
        // if you want to use the old queue as a copy
        BinaryHeapQueue newQueue = queue;
        newQueue.heapify();
        for(int i = 0; i<queue.size();i++){
            values.add(values.size(), newQueue.removeMax());
        }
    }

    // -------------------------------------------- Helper methods
    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private void checkIfEmpty() {
        if (values.isEmpty())
            throw new IllegalStateException("Queue is emtpy!");
    }

    public void printHeap() {
        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= values.size(); j++) {
                System.out.print(values.get(j + (int) Math.pow(2, i) - 1) + " ");

            }
            System.out.println();
        }
    }

    public void printHeapWithSpaces() {

        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= values.size(); j++) {

                if (j > max) {
                    max = j;
                }
            }

        }

        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= values.size(); j++) {

                for (int k = 0; (k < max / ((int) Math.pow(2, i))); k++) {
                    sb.append(" ");
                }
                sb.append((values.get(j + (int) Math.pow(2, i) - 1) + " "));
                
            }
            sb.append("\n");

        }
        // Stringbuffer magic, which i don't really understand
        // some fail from my side in the loop, but it works  ¯\_(ツ)_/¯
        sb.setLength(sb.length()-values.size()+3); 
        System.out.println(sb.toString());

    }

    public void printHeapAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int s : values) {
            sb.append(s + "");
            sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());

    }

    public void heapify() {
        total = values.size() - 1;

        for (int i = total / 2; i >= 0; i--)
            helperHeapify(i);

        for (int i = total; i > 0; i--) {
            swap(0, i);
            total--;
            helperHeapify(0);
        }
    }

    private void helperHeapify(int i) {
        int lft = i * 2;
        int rgt = lft + 1;
        int grt = i;

        if (lft <= total && (Integer) values.get(lft) < ((Integer) values.get(grt)))
            grt = lft;
        if (rgt <= total && (Integer) values.get(rgt) < ((Integer) values.get(grt)))
            grt = rgt;
        if (grt != i) {
            swap(i, grt);
            helperHeapify(grt);
        }
    }

    private void swap(int a, int b) {
        int tmp = values.get(a);
        values.set(a, values.get(b));
        values.set(b, tmp);
    }

}