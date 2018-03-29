// file: heap/queues/BinaryHeapQueue.java

// user of this class is responsible for heapify the heap to properly use it
package queues;

import java.util.ArrayList;
import java.util.Arrays;

public class DHeapQueue<T extends Comparable<T>> {
    ArrayList<Integer> values;
    int d;

    public DHeapQueue(int d) {
        values = new ArrayList<Integer>();
        this.d = d;
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
            if ((Integer) values.get(i / d) < ((Integer) values.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return values.size();
    }

    public void merge(DHeapQueue queue) {
        // if you want to use the old queue as a copy
        DHeapQueue newQueue = queue;
        newQueue.heapify();
        for (int i = 0; i < queue.size(); i++) {
            values.add(values.size(), newQueue.removeMax());
        }
    }

    private void checkIfEmpty() {
        if (values.isEmpty())
            throw new IllegalStateException("Queue is emtpy!");
    }

    public void printHeap() {
        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(d, i) && j + Math.pow(d, i) <= values.size(); j++) {
                System.out.print(values.get(j + (int) Math.pow(d, i) - 1) + " ");

            }
            System.out.println();
        }
    }

    public void printHeapWithSpaces() {

        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(d, i) && j + Math.pow(d, i) <= values.size(); j++) {

                if (j > max) {
                    max = j;
                }
            }

        }

        for (int i = 0; i <= values.size(); i++) {
            for (int j = 0; j < Math.pow(d, i) && j + Math.pow(d, i) <= values.size(); j++) {

                for (int k = 0; (k < max / ((int) Math.pow(d, i))); k++) {
                    sb.append(" ");
                }
                sb.append((values.get(j + (int) Math.pow(d, i) - 1) + " "));

            }
            sb.append("\n");

        }
        // Stringbuffer magic, which i don't really understand
        // some fail from my side in the loop, but it works  ¯\_(ツ)_/¯
        sb.setLength(sb.length() - values.size() + 3);
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
        // Heapify all internal nodes starting from last
        // non-leaf node all the way upto the root node
        // and calling restore down on each
        for (int i = (values.size() - 1) / d; i >= 0; i--)
            restoreDown(i, d);

    }

    private void swap(int a, int b) {
        int tmp = values.get(a);
        values.set(a, values.get(b));
        values.set(b, tmp);
    }

    public void restoreDown(int index, int k) {
        // child array to store indexes of all
        // the children of given node
        int[] child = new int[k + 1];

        while (true) {
            // child[i]=-1 if the node is a leaf
            // children (no children)
            for (int i = 1; i <= k; i++)
                child[i] = ((k * index + i) < values.size()) ? (k * index + i) : -1;

            // max_child stores the maximum child and
            // max_child_index holds its index
            int max_child = -1;
            int max_child_index = -1;

            // loop to find the maximum of all
            // the children of a given node
            for (int i = 1; i <= k; i++) {
                if (child[i] != -1 && (Integer) values.get(child[i]) > max_child) {
                    max_child_index = child[i];
                    max_child = values.get(child[i]);
                }
            }

            // leaf node
            if (max_child == -1)
                break;

            // swap only if the key of max_child_index
            // is greater than the key of node
            if ((Integer) values.get(index) < (Integer) values.get(max_child_index))
                swap(index, max_child_index);

            index = max_child_index;
        }
    }
}