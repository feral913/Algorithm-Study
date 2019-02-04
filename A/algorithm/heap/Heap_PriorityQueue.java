/*
 * Heap은 최댓값 및 최솟값을 찾아내는 연산을 빠르게 하기 위해 고안된 완전 이진트리를 기본으로 한 자료구조이다.
 * 최대 힙 - 부모노드의 키 값이 자식노드의 키 값보다 항상 큰 힙.
 * 최소 힙 - 부모노드의 키 값이 자식노드의 키 값보다 항상 작은 힙.
 * 키 값의 대소관계는 오직 부모노드와 자식노드 간에만 성립하면, 형제 사이에는 대소관계가 정해지지 않는다.
 * 각 노드의 자식 노드의 최대개수는 힙의 종류에 따라 다르지만, 대부분의 경우 자식 노드의 개수가 최대 2개인 이진 힙을 사용한다.
 * 이를 응용하여 우선순위 큐와 같은 추상적 자료형을 구현할 수 있다.
 * 
 * Priority Queue
 * Queue의 특별한 형태. 모든 원소가 정해진 규칙(혹은 사용자 규칙(custom comparator))에 따라 정렬이 된다.
 * Queue는 선입선출 방식으로 rear 포인터에서 삽입이, front 포인터에서 삭제가 이루어진다.
 * 이때 Priority Queue는 가장 작은 원소가 front 포인터에 위치하고, 가장 큰 원소가 rear 포인터에 위치한다.
 * 그러므로 제거할 때는 가장 작은 원소가 가장 먼저 제거된다.
 */
package algorithm.heap;

import java.util.Collections;
//import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Heap_PriorityQueue {
	// custome comparator - MaxHeap.
/*	static class MaxHeapComparator implements Comparator<Integer>{
		public int compare(Integer x, Integer y) {
			return y - x;
		}
	}*/

	public static void main(String[] args) {
		// custom comparator that compares two Strings by their length.
/*		Comparator<String> stringLengthComparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		};*/
		
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
