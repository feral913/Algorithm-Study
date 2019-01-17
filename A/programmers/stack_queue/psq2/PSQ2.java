/*
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다.
 * 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다.
 * 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2라면 C D A B 순으로 인쇄하게 됩니다.
 * 내가 인쇄를 요청한 무서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번쨰로 인쇄됩니다.
 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록에서 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때,
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return하도록 solution 함수를 작성해주세요.
 * 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
 * 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 * lcation은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
 */

package programmers.stack_queue.psq2;

import java.util.ArrayDeque;
import java.util.Queue;

// 인쇄 대기열의 우선순위를 저장하는 배열과 요청한 문서의 위치 정보를 갖는 객체.
class PrintQueue{
	private int[] priorities;
	private int location;
	
	public PrintQueue(int N) {
		priorities = new int[N];
	}
	
	public int[] getPriorities() {
		return priorities;
	}
	
	public void setPriorities(int[] priorities) {
		System.arraycopy(priorities, 0, this.priorities, 0, priorities.length);
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
}

// 제일 처음에 넣은 순서대로 뽑아본다고 생각했을 때 queue를 사용해야 될 거 같다.
// poll로 첫 번째 인쇄물의 우선순위를 뽑아 보고 그 숫자보다 큰 숫자가 queue 안에 있으면 다시 add로 제일 뒤에 넣는다.
// 그렇다면 더 큰 수가 있는지 확인은 어떻게 하는가
// 현재 인쇄물의 우선순위를 변수에 저장하고, 또 다른 변수에 다음 인쇄물의 우선순위를 뽑아서 확인하고 더 작으면 제일 뒤에 다시 넣고 하는 방식으로 남은 인쇄물을 전부 다 확인하면 될 거 같다.
// 뽑고자 하는 인쇄물의 위치를 추적해야 하니 인쇄물의 우선순위를 담은 queue와 함께 인쇄물의 번호를 담은 queue도 만들어서 같이 움직이도록 해야겠다.
class Solution{
	public int solution(int[] priorities, int location) {
		int answer = 0;
		
		// pQueue : 인쇄물의 우선순위를 담은 queue. iQueue : 인쇄물의 번호를 담은 queue.
		Queue<Integer> pQueue = new ArrayDeque<>();
		Queue<Integer> iQueue = new ArrayDeque<>();
		
		for(int i = 0; i < priorities.length; i++) {
			pQueue.add(priorities[i]);
			iQueue.add(i);
		}
		
		// print : 현재 제일 앞에 있는 인쇄물의 우선순위가 가장 높다면 해당 인쇄물의 번호를 출력한다.
		// do~while을 이용해서 처음에는 조건 없이 작업을 시작하고 작업이 끝난 후 출력되는 인쇄물의 번호가 내가 인쇄를 요청한 인쇄물(location)이면 작업을 종료한다.
		// 인쇄물이 출력될 때마다 인쇄 횟수(answer)를 카운트한다.
		int print = -1;
		do {
			boolean isCurrMax = true;
			int pCurr = pQueue.poll();
			int iCurr = iQueue.poll();

			// 현재 제일 앞에 있는 인쇄물의 우선순위와 남아있는 모든 인쇄물과의 우선순위를 비교한다.
			// 남아있는 인쇄물을 앞에서부터 하나씩 뽑아서 비교하고 제일 뒤에 다시 넣는 과정을 끝까지 반복하기 때문에 결과적으로 인쇄물의 순서에는 변화가 없다.
			// 그러므로 인쇄물의 번호를 담은 queue는 별다른 작업을 하지 않는다.
			for(int i = 0; i < pQueue.size(); i++) {
				int pNext = pQueue.poll();
				if(pCurr < pNext) isCurrMax = false;
				pQueue.add(pNext);
			}
			// 현재 제일 앞에 있는 인쇄물보다 더 높은 우선순위의 인쇄물이 뒤에 있다면
			// 제일 앞에 있는 인쇄물을 queue의 제일 뒤로 보낸다.
			if(!isCurrMax) {
				pQueue.add(pCurr);
				iQueue.add(iCurr);
			}
			// 현재 제일 앞에 있는 인쇄물이 우선순위가 가장 높다면 출력한다.
			else{
				print = iCurr;
				answer++;
			}
		}while(print != location);
		
		return answer;
	}
}

public class PSQ2 {
	public static void main(String[] args) {
		int[][] priorities = {{2, 1, 3, 2}, {1, 1, 9, 1, 1, 1}};
		int[] location = {2, 0};
		
		Solution sol = new Solution();
		for(int i = 0; i < priorities.length; i++) {
			PrintQueue pq = new PrintQueue(priorities[i].length);
			pq.setPriorities(priorities[i]);
			pq.setLocation(location[i]);
			
			System.out.println(sol.solution(pq.getPriorities(), pq.getLocation()));
		}
	}
}
