package algorithm.heap;

import java.util.Collections;
//import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Heap {
/*	static class MaxHeapComparator implements Comparator<Integer>{
		public int compare(Integer x, Integer y) {
			return y - x;
		}
	}*/

	public static void main(String[] args) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		//PriorityQueue<Integer> maxHeap = new PriorityQueue<>(100, new MaxHeapComparator());
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(100, Collections.reverseOrder());
		
		Random ran = new Random();
		
		for(int i = 0; i < 100; i++) {
			minHeap.add(ran.nextInt(100));
			maxHeap.add(ran.nextInt(100));
		}
		
		for(int i : minHeap) {
			System.out.print(i + " ");
		}
		System.out.println("");
		for(int i = 0; i < 100; i++) {
			int k = minHeap.poll();
			System.out.print(k + " ");
		}
		
		System.out.println("");

		for(int i : maxHeap) {
			System.out.print(i + " ");
		}
		System.out.println("");
		for(int i = 0; i < 100; i++) {
			int j = maxHeap.poll();
			System.out.print(j + " ");
		}
	}
}
