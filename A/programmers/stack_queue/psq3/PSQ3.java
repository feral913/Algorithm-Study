/*
 * 트럭 여러 대가 강을  가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.
 * 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
 * 트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
 * 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.
 * 예를 들어, 길이가 2대까지, 무게 10kg까지 견디는 다리가 있습니다.
 * 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.
 * 경과 시간	다리를 지난 트럭 		다리를 건너는 트럭 	대기 트럭
 * 0		[]				[]				[7, 4, 5, 6]
 * 1~2		[]				[7]				[4, 5, 6]
 * 3		[7]				[4]				[5, 6]
 * 4		[7]				[4, 5]			[6]
 * 5		[7, 4]			[5]				[6]
 * 6~7		[7, 4, 5]		[6]				[]
 * 8		[7, 4, 5, 6]	[]				[]
 * 따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.
 * solution 함수의 매개변수로 다리 길이 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭별 무게 truck_weights가 주어집니다.
 * 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solutin 함수를 완성하세요.
 * bridge_length는 1 이상 10,000 이하입니다.
 * weight는 1 이상 10,000 이하입니다.
 * truck_weights의 길이는 1 이상 10,000 이하입니다.
 * 모든 트럭의 무게는 1 이상 weight 이하입니다.
 */
package programmers.stack_queue.psq3;

class Bridge{
	private int length;
	private int weight;
	
	public Bridge(int length, int weight) {
		this.length = length;
		this.weight = weight;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWeight() {
		return weight;
	}
}

class Trucks{
	private int[] weights;
	
	public Trucks(int N) {
		weights = new int[N];
	}
	
	public int[] getWeights() {
		return weights;
	}
	
	public void setWeights(int[] weights) {
		System.arraycopy(weights, 0, this.weights, 0, weights.length);
	}
}

class Solution{
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        return answer;
    }
}

public class PSQ3 {
	public static void main(String[] args) {
		int[] bridge_lengths = {2, 100, 100};
		int[] weights = {10, 100, 100};
		int[][] truck_weights = {{7, 4, 5, 6}, {10}, {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}};

		for(int i = 0; i < bridge_lengths.length; i++) {
			Bridge b = new Bridge(bridge_lengths[i], weights[i]);
			Trucks t = new Trucks(truck_weights[i].length);
			t.setWeights(truck_weights[i]);
			
			Solution sol = new Solution();
			
			System.out.println(sol.solution(b.getLength(), b.getWeight(), t.getWeights()));
		}
	}
}
