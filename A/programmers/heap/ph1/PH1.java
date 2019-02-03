package programmers.heap.ph1;

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
