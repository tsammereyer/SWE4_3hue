package tests;

import java.util.Random;

import queues.BinaryHeapQueue;
import queues.Heap;

public class PQueueTest {

  public static void main(String[] args) {
    testBinaryHeapQueue();
    //testHeap();
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

  public static void testHeap() {
    Heap<Integer> h = new Heap<Integer>();
    System.out.println(h);
    h.enqueue(1);
    System.out.println(h);
    h.enqueue(2);
    System.out.println(h);

    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      h.enqueue(r.nextInt(10));
    }
    System.out.println(h);

    while (!h.isEmpty()) {
      System.out.println(h.dequeue());
    }
    System.out.println(h);
  }


  public static void printIntArray(int [] arr){
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