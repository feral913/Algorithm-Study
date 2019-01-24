package programmers.stack_queue.psq4;

class Work{
	private int[] progresses;
	private int[] speeds;
	
	public Work(int[] progresses, int[] speeds) {
		this.progresses = new int[progresses.length];
		System.arraycopy(progresses, 0, this.progresses, 0, progresses.length);

		this.speeds = new int[speeds.length];
		System.arraycopy(speeds, 0, this.speeds, 0, speeds.length);
	}
	
	public int[] getProgresses() {
		return progresses;
	}
	
	public int[] getSpeeds() {
		return speeds;
	}
}

class Solution{
	public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		return answer;
	}
}

public class PSQ4 {
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		Work work = new Work(progresses, speeds);
		Solution sol = new Solution();
		
		System.out.println(sol.solution(work.getProgresses(), work.getSpeeds()));
	}
}
