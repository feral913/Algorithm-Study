/*
 * 라면 공장에서는 하루에 밀가루를 1톤씩 사용합니다. 원래 밀가루를 공급받던 공장의 고장으로 앞으로 k일 이후에야 밀가루를 공급받을 수 있기 때문에 해외 공장에서 밀가루를 수입해야 합니다.
 * 해외 공장에서는 향후 밀가루를 공급할 수 있는 날짜와 수량을 알려주었고, 라면 공장에서는 운송비를 줄이기 위해 최소한의 횟수로 밀가루를 공급받고 싶습니다.
 * 현재 공장에 남아있는 밀가루 수량 stock, 밀가루 공급 일정(dates)과 해당 시점에 공급 가능한 밀가루 수량(supplies), 원래 공장으로부터 공급받을 수 있는 시점 k가 주어질 때, 밀가루가 떨어지지 않고 공장을 운영하기 위해서 최소한 몇 번 해외 공장으로부터 밀가루를 공급받아야 하는지를 return 하도록 solution 함수를 완성하세요.
 * dates[i]에는 i번째 공급 가능일이 들어있으며, amounts[i]에는 dates[i] 날짜에 공급 가능한 밀가루 수량이 들어 있습니다.
 * stock에 있는 밀가루는 오늘(0일 이후)부터 사용됩니다.
 * stock과 k는 2 이상 100,000 이하입니다.
 * dates의 각 원소는 1 이상 k 이하입니다.
 * supplies의 각 원소는 1 이상 1,000 이하입니다.
 * dates와 supplies의 길이는 1 이상 20,000 이하입니다.
 * k일 째에는 밀가루가 충분히 공급되기 때문에 k - 1일에 사용할 수량까지만 확보하면 됩니다.
 * dates에 들어있는 날짜는 오름차순 정렬되어 있습니다.
 * dates에 들어있는 날짜에 공급되는 밀가루는 작업 시작 전 새벽에 공급되는 것을 기준으로 합니다. 예를 들어 9일째에 밀가루가 바닥나더라도, 10일째에 공급받으면 10일째에는 공장을 운영할 수 있습니다.
 * 밀가루가 바닥나는 경우는 주어지지 않습니다.
 */
package programmers.heap.ph2;

class Solution{
	public int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		
		// k일 동안 버틴다.
		while(k == 0) {
			// 재고가 없으면 공급 받는다.
			if(stock == 0) stock +=  
		}
		
		return answer;
	}
}

public class PH2 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = {4, 10, 15};
		int[] supplies = {20, 5, 10};
		int k = 30;
		
		Solution sol = new Solution();
		
		System.out.println(sol.solution(stock, dates, supplies, k));
	}
}
