package tests;

import java.util.Random;

import queues.BinaryHeapQueue;
import queues.DHeapQueue;

public class PQueueTest {

  public static void main(String[] args) {
    testBinaryHeapQueue();
    System.out.println("\n-----------------------------------------------------------\n");
    testDHeapQueue();
  }

  public static void testBinaryHeapQueue() {
    BinaryHeapQueue<Integer> h = new BinaryHeapQueue<Integer>();
    
    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      h.insertUnordered(r.nextInt(10));
    }

    System.out.println("un-hepified");
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("heapified");
    h.heapify();
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("remove max");
    h.removeMax();
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("remove 3");
    h.removeNLargest(3);
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("3-largest");
    printIntArray(h.nLargest(3));
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("Max");
    System.out.println("Max: " + h.max());
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("insertArray");
    int [] arr = {1,5,3};
    h.insertUnordered(arr);
    h.printHeapAsString();
    h.printHeapWithSpaces();



    System.out.println("mergeWithOtherHeap");
    BinaryHeapQueue<Integer> h2 = new BinaryHeapQueue<Integer>();
    h2.insertUnordered(7);
    h2.insertUnordered(13);
    h2.insertUnordered(3);
    System.out.print("new heap: ");
    h2.printHeapAsString();
    h.merge(h2);
    h.printHeapAsString();
    h.printHeapWithSpaces();
  }

  public static void testDHeapQueue() {
    DHeapQueue<Integer> h = new DHeapQueue<Integer>(3);
    
    //int [] values = {4, 2, 0, 0, 5, 4, 2, 0, 1, 7};
    //h.insertUnordered(values);

    h.insertUnordered(6);
    h.insertUnordered(0);
    h.insertUnordered(3);
    h.insertUnordered(8);
    h.insertUnordered(1);
    h.insertUnordered(8);
    h.insertUnordered(9);
    h.insertUnordered(1);
    h.insertUnordered(0);
    h.insertUnordered(5);    

    System.out.println("un-hepified");
    h.printHeapAsString();

    System.out.println("\nheapified");
    h.heapify();
    h.printHeapAsString();

    System.out.println("\nremove max");
    h.removeMax();
    h.printHeapAsString();

    System.out.println("\nremove 3");
    h.removeNLargest(3);
    h.printHeapAsString();

    System.out.println("\n3-largest");
    printIntArray(h.nLargest(3));
    h.printHeapAsString();

    System.out.println("\nMax");
    System.out.println("Max: " + h.max());
    h.printHeapAsString();

    System.out.println("\ninsertArray");
    int [] arr = {1,5,3};
    h.insertUnordered(arr);
    h.printHeapAsString();



    System.out.println("\nmergeWithOtherHeap");
    DHeapQueue<Integer> h2 = new DHeapQueue<Integer>(3);
    h2.insertUnordered(7);
    h2.insertUnordered(13);
    h2.insertUnordered(3);
    System.out.print("new heap: ");
    h2.printHeapAsString();
    h.merge(h2);
    h.printHeapAsString();
  }


  private static void printIntArray(int [] arr){
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int s : arr) {
      sb.append(s + "");
      sb.append(" ");
    }
    sb.append("]");
    System.out.println(sb.toString());
  }
}