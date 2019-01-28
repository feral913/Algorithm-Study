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

class Solution{
	public int[] solution(int[] prices) {
		int[] answer = {};
		
		
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
