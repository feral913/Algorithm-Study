/*
 * 매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
 * Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
 * scoville의 길이는 1 이상 1,000,000 이하입니다.
 * K는 0 이상 1,000,000,000 이하입니다.
 * scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 * ex) scoville = [1, 2, 3, 9, 10, 12], K = 7.
 * 1 + (2 * 2) = 5 -> scoville = [5, 3, 9, 10, 12].
 * 3 + (5 * 2) = 13 -> scoville = [13, 9, 10, 12].
 * end. 
 */
package programmers.heap.ph1;

import java.util.PriorityQueue;

class Food{
	private int[] scoville;
	private int K;
	
	public Food(int[] scoville, int K) {
		this.scoville = new int[scoville.length];
		System.arraycopy(scoville, 0, this.scoville, 0, scoville.length);
		this.K = K;
	}
	
	public int[] getScoville() {
		return scoville;
	}
	
	public int getK() {
		return K;
	}
}

class Solution{
	public int solution(int[] scoville, int K) {
		int answer = 0;
		
		// PriorityQueue 클래스를 이용해서 minimum heap 형태로 데이터를 정렬한다.
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int i : scoville) {
			minHeap.add(i);
		}
		
		// scoville이 제일 작은 음식을 꺼낸다.
		// 꺼낸 제일 작은 값이 K 이상이 될 때까지 작업을 수행한다.
		// 음식이 하나만 남았을 경우 더 이상 섞을 수 없기 때문에 작업을 종료한다.
		int minScoville = minHeap.poll();
		while(minScoville < K && !minHeap.isEmpty()) {
			// 두 번째로 scoville이 가장 작은 음식을 꺼낸다.
			int nextMinScoville = minHeap.poll();
			minHeap.add(minScoville + (nextMinScoville * 2));
			answer++;
			minScoville = minHeap.poll();
		}
		
		// 작업이 끝났지만 scoville이 가장 작은 음식이 K 보다 작은 경우는 음식이 하나밖에 없어 더 이상 섞을 수 없는 경우다.
		// 이 경우 -1을 출력한다.
		if(minScoville < K) answer = -1;
		
		return answer;
	}
}

public class PH1 {
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		
		Food food = new Food(scoville, K);
		Solution sol = new Solution();
		
		System.out.println(sol.solution(food.getScoville(), food.getK()));
	}
}
