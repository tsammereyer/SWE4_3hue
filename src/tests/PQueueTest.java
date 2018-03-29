package tests;

import java.util.Random;

import queues.BinaryHeapQueue;
import queues.DHeapQueue;
import helpers.CSVFileWriter;

public class PQueueTest {

  public static CSVFileWriter writer;

  public static void main(String[] args) {
    initializeCSVWriter();   
    testBinaryHeapQueue();
    System.out.println("\n-----------------------------------------------------------\n");
    testDHeapQueue();
    writer.flushAndClose();
  }

  public static void initializeCSVWriter() {
    String[] fileHeader = { "Type","insertUnordered()", "h.heapify()", "h.removeMax()", "h.removeNLargest(3)", "h.nLargest(3)", "h.max()",
        "h.insertUnordered(arr)", "h.merge(h2)" };
    writer = new CSVFileWriter(fileHeader,"time.csv", ",");
  }

  public static void testBinaryHeapQueue() {
    BinaryHeapQueue<Integer> h = new BinaryHeapQueue<Integer>();
    String[] testBinaryHeapQueueValues = new String[9];
    testBinaryHeapQueueValues[0] = "BinaryHeapQueue";
    long start; 
    long time; 
    
    start = System.nanoTime();
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
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[1] = ""+time / 1000.0;

    System.out.println("un-hepified");
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("heapified");
    start = System.nanoTime();
    h.heapify();
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[2] = ""+time / 1000.0;
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("remove max");
    start = System.nanoTime();
    h.removeMax();
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[3] = ""+time / 1000.0;
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("remove 3");
    start = System.nanoTime();
    h.removeNLargest(3);
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[4] = ""+time / 1000.0;
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("3-largest");
    start = System.nanoTime();
    h.nLargest(3);
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[5] = ""+time / 1000.0;
    printIntArray(h.nLargest(3));
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("Max");
    System.out.println("Max: " + h.max());
    start = System.nanoTime();
    h.max();
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[6] = ""+time / 1000.0;
    h.printHeapAsString();
    h.printHeapWithSpaces();

    System.out.println("insertArray");
    int[] arr = { 1, 5, 3 };
    start = System.nanoTime();
    h.insertUnordered(arr);
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[7] = ""+time / 1000.0;
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
    start = System.nanoTime();
    h.merge(h2);
    time = System.nanoTime() - start;
    testBinaryHeapQueueValues[8] = ""+time / 1000.0;
    h.printHeapAsString();
    h.printHeapWithSpaces();

    writer.writeLine(testBinaryHeapQueueValues);
  }

  public static void testDHeapQueue() {
    DHeapQueue<Integer> h = new DHeapQueue<Integer>(3);
    String[] testDHeapQueueValues = new String[9];
    testDHeapQueueValues[0] = "DHeapQueue";
    long start; 
    long time; 

    //int [] values = {4, 2, 0, 0, 5, 4, 2, 0, 1, 7};
    //h.insertUnordered(values);

    start = System.nanoTime();
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
    time = System.nanoTime() - start;
    testDHeapQueueValues[1] = ""+time / 1000.0;

    System.out.println("un-hepified");
    h.printHeapAsString();

    System.out.println("\nheapified");
    start = System.nanoTime();
    h.heapify();
    time = System.nanoTime() - start;
    testDHeapQueueValues[2] = ""+time / 1000.0;
    h.printHeapAsString();

    System.out.println("\nremove max");
    start = System.nanoTime();
    h.removeMax();
    time = System.nanoTime() - start;
    testDHeapQueueValues[3] = ""+time / 1000.0;
    h.printHeapAsString();

    System.out.println("\nremove 3");
    start = System.nanoTime();
    h.removeNLargest(3);
    time = System.nanoTime() - start;
    testDHeapQueueValues[4] = ""+time / 1000.0;
    h.printHeapAsString();

    System.out.println("\n3-largest");
    start = System.nanoTime();
    h.nLargest(3);
    time = System.nanoTime() - start;
    testDHeapQueueValues[5] = ""+time / 1000.0;
    printIntArray(h.nLargest(3));
    h.printHeapAsString();

    System.out.println("\nMax");
    System.out.println("Max: " + h.max());
    start = System.nanoTime();
    h.max();
    time = System.nanoTime() - start;
    testDHeapQueueValues[6] = ""+time / 1000.0;
    h.printHeapAsString();

    System.out.println("\ninsertArray");
    int[] arr = { 1, 5, 3 };
    start = System.nanoTime();
    h.insertUnordered(arr);
    time = System.nanoTime() - start;
    testDHeapQueueValues[7] = ""+time / 1000.0;
    h.insertUnordered(arr);
    h.printHeapAsString();

    System.out.println("\nmergeWithOtherHeap");
    DHeapQueue<Integer> h2 = new DHeapQueue<Integer>(3);
    h2.insertUnordered(7);
    h2.insertUnordered(13);
    h2.insertUnordered(3);
    System.out.print("new heap: ");
    h2.printHeapAsString();
    start = System.nanoTime();
    h.merge(h2);
    time = System.nanoTime() - start;
    testDHeapQueueValues[8] = ""+time / 1000.0;
    h.printHeapAsString();

    writer.writeLine(testDHeapQueueValues);
  }

  private static void printIntArray(int[] arr) {
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