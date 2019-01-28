/*
 * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 유지된 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 * prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
 * prices의 길이는 2 이상 100,000 이하입니다.
 * ex) [498, 501, 470, 489]
 * 1초 시점의 ₩498은 2초간 가격을 유지하고, 3초 시점에 ₩470으로 떨어졌습니다.
 * 2초 시점의 ₩501은 1초간 가격을 유지하고, 3초 시점에 ₩470으로 떨어졌습니다.
 * 3초 시점의 ₩470은 최종 시점까지 총 1초간 가격이 떨어지지 않았습니다.
 * 4초 시점의 ₩489은 최종 시점까지 총 0초간 가격이 떨어지지 않았습니다.
 * [2, 1, 1, 0]
 */
package programmers.stack_queue.psq6;

import java.util.ArrayDeque;
import java.util.Queue;

// 주식 가격 정보를 저장하는 객체.
class Stocks{
	private int[] prices;
	
	public Stocks(int[] prices) {
		this.prices = new int[prices.length];
		System.arraycopy(prices, 0, this.prices, 0, prices.length);
	}
	
	public int[] getPrices() {
		return prices;
	}
}

// 가장 첫번째 주식 가격을 선택한다. - Queue에 가격들을 저장해놓고 저장된 순서대로 가격을 꺼낸다.
// 선택된 가격과 이후의 가격들을 비교한다. - prices 배열에 저장된 가격 중 현재 주식 이후의 가격과 순서대로 비교한다. 이를 위해 현재 주식의 번호를 체크한다.
// 가격을 비교할 때마다 시간을 카운트한다(그 시간만큼 이후의 가격이니).
// 가격이 더 작으면 비교를 종료하고 현재까지 카운트 된 시간을 출력 값에 저장한다.
class Solution{
	public int[] solution(int[] prices) {
		// aQueue : 출력 할 answer 값을 저장하는 queue. pQueue : 주식 가격들을 저장할 queue.
		// 주식 가격들을 queue에 저장한다.
		// currStock : 현재 주식의 번호.
		Queue<Integer> aQueue = new ArrayDeque<>();
		Queue<Integer> pQueue = new ArrayDeque<>();
		for(int i : prices) pQueue.add(i);
		int currStock = 0;
		
		// 마지막 주식까지 작업을 수행한다(pQueue가 빌 때까지).
		while(!pQueue.isEmpty()) {
			// currPrice : 현재 주식의 가격. time : 현재 주식의 가격이 떨어질 때까지의 시간.
			int currPrice = pQueue.poll();
			int time = 0;
			
			// 이후의 주식 가격들과 비교한다.
			// 시간을 더해주면서 비교하다가 가격이 더 작아지면 작업을 종료한다.
			for(int i = currStock + 1; i < prices.length; i++) {
				time++;
				if(currPrice > prices[i]) break;
			}
			
			// 출력 값에 카운트된 시간을 저장한다.
			// 다음 주식으로 넘어간다.
			aQueue.add(time);
			currStock++;
		}
		
		int[] answer = new int[aQueue.size()];
		for(int i = 0; i < answer.length; i++) {
			answer[i] = aQueue.poll();
		}
		
		return answer;
	}
}

public class PSQ6 {
	public static void main(String[] args) {
		int[] prices = {498, 501, 470, 489};
		
		Stocks s = new Stocks(prices);
		Solution sol = new Solution();
		
		int[] answer = sol.solution(s.getPrices());
		for(int i : answer) {
			System.out.println(i);
		}
	}
}
