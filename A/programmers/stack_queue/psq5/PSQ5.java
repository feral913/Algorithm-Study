/*
 * 수평 직선에 높이가 서로 다른 탑 N대를 세웠습니다.
 * 모든 탑의 꼭대기에는 신호를 송/수신하는 장치를 설치했습니다.
 * 발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다.
 * 또한, 한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.
 * 예를 들어 높이가 6, 9, 5, 7, 4인 다섯 탑이 왼쪽으로 동시에 레이저 신호를 발사합니다.
 * 그러면, 탑은 다음과 같이 신호를 주고받습니다.
 * 높이가 4인 다섯 번째 탑에서 발사한 신호는 높이가 7인 네 번째 탑이 수신하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신합니다.
 * 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신할 수 없습니다.
 * 맨 왼쪽부터 순서대로 탑의 높이를 담은 배열 heights가 매개변수로 주어질 때 각 탑이 쏜 신호를 어느 탑에서 받았는지 기록한 배열을 return 하도록 solution 함수를 작성해주세요.
 * heights는 길이 2 이상 100 이하인 정수 배열입니다.
 * 모든 탑의 높이는 1 이상 100 이하입니다.
 * 신호를 수신하는 탑이 없으면 0으로 표시합니다.
 */
package programmers.stack_queue.psq5;

import java.util.Stack;

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

// 제일 오른 쪽 탑을 높이를 확인한다. - stack에 탑의 높이를 순서대로 저장 후 뒤에 저장된 탑부터 순서대로 꺼내본다.
// 왼쪽으로 하나씩 다른 탑과 비교 하면서 높이가 더 높은 탑을 찾는다. - stack에서 꺼낸 송신 탑의 높이를  heights 배열의 송신 탑 왼쪽 탑들과 순서대로 비교한다. 이를 위해 현재 송신 탑이 몇 번 인지를 체크한다.
// 처음 발견하는 더 높은 탑이 수신 탑이다. - 해당 탑의 번호를 결과 값을 저장하는 stack에 저장한다. 최종 출력 값에는 가장 왼쪽 탑부터 각 수신 탑의 번호가 들어가기 때문에 stack에 저장한 결과 값을 마지막에 순서대로 배열에 옮겨 넣어준다.
// 송신 탑의 번호를 왼쪽 탑으로 바꾸고 위 과정을 반복한다.
class Solution{
	public int[] solution(int[] heights) {
		// tStack : 탑들의 높이를 저장하는 stack. currTower : 현재 신호를 송신하는 탑의 번호. aStack : 수신 탑을 저장하는 stack(역순으로 answer에 옮겨 담을 결과).
		Stack<Integer> tStack = new Stack<>();
		int currTower = heights.length - 1;
		Stack<Integer> aStack = new Stack<>();
		
		for(int i : heights) tStack.push(i);
		
		while(!tStack.isEmpty()) {
			// currHeight : 현재 신호를 송신하는 탑의 높이.
			int currHeight = tStack.pop();
			
			// 송신 탑이 없을 경우를 고려하여 결과 값으로 0을 먼저 넣어 놓는다.
			// 송신 탑과 연속하는 왼쪽 탑부터 시작하여 순서대로 높이를 비교한다.
			// 더 높은 탑이 발견되면 먼저 넣어 둔 0을 빼고 해당 수신 탑의 번호를 저장한다.
			aStack.push(0);
			for(int i = currTower - 1; i >= 0; i--) {
				if(currHeight < heights[i]) {
					aStack.pop();
					aStack.push(i + 1);
					break;
				}
			}
			
			// 다음 송신할 타워 번호.
			currTower--;
		}
		
		int[] answer = new int[aStack.size()];
		for(int i = 0; i < answer.length; i++) {
			answer[i] = aStack.pop();
		}

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
