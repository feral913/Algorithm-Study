package programmers.stack_queue.psq5;

class Towers{
	private int[] heights;
	
	public Towers(int[] heights){
		this.heights = new int[heights.length];
		System.arraycopy(heights, 0, this.heights, 0, heights.length);
	}
	
	public int[] getHeights() {
		return heights;
	}
}

class Solution{
	public int[] solution(int[] heights) {
		int[] answer = {};
		return answer;
	}
}

public class PSQ5 {
	public static void main(String[] args) {
		int[][] heights = {{6, 9, 5, 7, 4}, {3, 9, 9, 3, 5, 7, 2}, {1, 5, 3, 6, 7, 6, 5}};
		
		for(int i = 0; i < heights.length; i++) {
			Towers t = new Towers(heights[i]);
			Solution sol = new Solution();
			
			for(int j : sol.solution(t.getHeights())) {
				System.out.printf("%d ", j);
			}
			System.out.println("");
		}
	}
}
