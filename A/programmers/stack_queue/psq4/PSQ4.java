/*
 * 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
 * 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 * 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
 * 작업 진도는 100 미만의 자연수입니다.
 * 작업 속도는 100 이하의 자연수입니다.
 * 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
 */
package programmers.stack_queue.psq4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

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

// 1. Queue에 작업 진행도와 작업 별 속도를 저장한다.
// 2. 작업들의 기존 진행도에 작업 별 속도를 더해준다.
// 3. 가장 첫 번째 작업 진행도가 100 이상인지 확인한다.
// 4. 가장 첫 번째 작업 진행도가 100 이상이면 해당 기능을 배포한다.
// 다음 작업의 진행도가 100 미만이면 하루를 넘기고 100 이상이면 해당 기능을 배포한다.
// 5. 남은 작업이 없을 때까지 2 ~ 5의 작업을 반복한다.
class Solution{
	public int[] solution(int[] progresses, int[] speeds) {
		// answer : 같은날 배포되는 기능 수의 배열. index : 작업 일. ansArr : answer의 크기를 모르기 때문에 임시로 출력 값을 저장 할 arraylist.
		int[] answer = {};
		int index = 0;
		ArrayList<Integer> ansArr = new ArrayList<>();
		
		// pQueue : 작업 별 진행도(progresses) 저장. sQueue : 작업 별 진행 속도(speeds) 저장.
		Queue<Integer> pQueue = new ArrayDeque<>();
		Queue<Integer> sQueue = new ArrayDeque<>();		
		for(int i = 0; i < progresses.length; i++) {
			pQueue.add(progresses[i]);
			sQueue.add(speeds[i]);
		}
		
		// 모든 기능이 배포될 때까지(모든 작업 진행도가 100 이상이 될 때까지. pQueue가 빌 때 까지.).
		while(!pQueue.isEmpty()) {
			// 모든 작업의 진행도에 작업 별 진행 속도 만큼 더해준다.
			// (pQueue의 제일 앞에 값을 꺼내서 sQueue의 제일 앞에 값을 더한 값을 pQueue의 제일 뒤에 다시 넣어준다.)
			// (계산 후 sQueue의 제일 앞의 값을 제일 뒤로 보낸다.)
			for(int i = 0; i < pQueue.size(); i++) {
				if(pQueue.peek() < 100) pQueue.add(pQueue.poll() + sQueue.peek());
				else pQueue.add(pQueue.poll());
				sQueue.add(sQueue.poll());

			}

			// 첫 번째 작업의 진행도가 100 이상이면(pQueue의 첫 번째 값이 100 이상이면)
			if(pQueue.peek() >= 100) {
				// 첫 번째 기능을 배포한다(pQueue와 sQueue의 첫 번째 값을 출력한다).
				// 오늘 배포하는 기능 수를 카운트 한다(ansArr.add(1)).
				pQueue.poll();
				sQueue.poll();
				ansArr.add(1);
				// 연속되는 작업의 진행도가 100 미만이면 다음날로 넘어간다(break).  
				// 연속되는 작업에 대해서 진행도가 100 이상이면 함께 배포한다.
				// 오늘 배포하는 기능 수를 카운트한다(ansArr.set(index, ansArr.get(index) + 1)).
				for(int i = 0; i < pQueue.size(); i++) {
					if(pQueue.peek() < 100) break;
					else {
						pQueue.poll();
						sQueue.poll();
						ansArr.set(index, ansArr.get(index) + 1);
						i--;
					}
				}
				
				index++;
			}
		}
		
		answer = new int[index];
		for(int i = 0; i < index; i++) {
			answer[i] = ansArr.get(i);
		}
		
		return answer;
	}
}

public class PSQ4 {
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		Work work = new Work(progresses, speeds);
		Solution sol = new Solution();
		
		int[] ret = sol.solution(work.getProgresses(), work.getSpeeds());
		for(int i : ret) {
			System.out.println(i);
		}
	}
}
