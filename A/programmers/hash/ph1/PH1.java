/*
 * Hash.
 * 마라톤 참가자와 완주자 리스트가 각각 주어진다.
 * 참가자 중 한명만 완주를 하지 못하였다.
 * 완주하지 못한 참자가를 찾아라.
 */

package programmers.hash.ph1;

import java.util.HashMap;
import java.util.Scanner;

// 참가자들의 이름이 저장되는 객체.
class Participant{
	private String[] partName;
	
	public Participant(int N) {
		partName = new String[N];
	}
	
	public String[] getPartName() {
		return partName;
	}

	public void setPartName(int i, String name) {
		partName[i] = name;
	}
}

// 완주자들의 이름이 저장되는 객체.
class Completion{
	private String[] comName;
	
	public Completion(int N) {
		comName = new String[N];
	}
	
	public String[] getComName() {
		return comName;
	}
	
	public void setComName(int i, String name) {
		comName[i] = name;
	}
}

public class PH1 {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0 ; t < T; t++) {
			int N = sc.nextInt();
			Participant p = new Participant(N);
			
			for(int i = 0; i < N; i++) {
				String name = sc.next();
				
				p.setPartName(i, name);
			}
			
			Completion c = new Completion(N - 1);
			for(int i = 0; i < N - 1; i++) {
				String name = sc.next();
				
				c.setComName(i, name);
			}
			
			System.out.println(solution(p.getPartName(), c.getComName()));
		}
	}
	
	// hash를 이용해서 효율적으로 푸는 문제라서 HashMap 클래스를 활용해본다.
	// key값을 선정하는 과정이 중요할 거 같지는 않아서 우선은 중복되지 않게 0부터 1씩 추가하면서 key 값을 대입해준다.
	// hash map에 완주자의 이름을 저장하고, containValue 메쏘드를 이용하여 참가자 중 이름이 없는 사람을 찾는다.
	// 중복되는 이름이 있기 때문에 한번 찾은 이름은 완주자 목록에서 삭제한다.
	// 그렇게 하니까 시간초과 나왔다.
	// 아 이름을 key에다가 넣어야되나....?? 아 그러면 애초에 동일 이름을 중복 저장못하는구나...
	// 해결 : 이중 loop문이 이중 이상 생기지 않도록 고민해보았다.
	// 완주자를 Hash map에 넣어준다. 이때 Hash map의 key 값으로 이름을 넣고 value를 0에서부터 이름이 중복되는 만큼 +1 해주었다.
	// 참가자의 수만큼 loop를 돌면서 완주자의 Hash map에 참석자의 이름이 있는지 확인한다.
	// 이때 참석자와 같은 이름이 한명이상 완주자 hash에 있다면 value 값을 -1 해준다.
	// 참석자의 이름이 완주자 hash에 value 0으로 있다면 완주자 hash에서 해당 이름의 key 값을 삭제한다.
	// 참석자의 이름이 완주자 hash에 없으면 탐색을 종료하고 해당 이름을 반환한다.
	public static String solution(String[] participant, String[] completion) {
		String answer = "";

		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for(int i = 0; i < completion.length; i++) {
			if(hm.containsKey(completion[i])) {
				hm.put(completion[i], hm.get(completion[i]) + 1);
			} else {
				hm.put(completion[i], 0);
			}
		}
		
		for(int i = 0; i< participant.length; i++) {
			String name = participant[i];
			
			if(hm.containsKey(name) && hm.get(name) > 0) {
				hm.put(name, hm.get(name) - 1);
			} else if(hm.containsKey(name) && hm.get(name) == 0) {
				hm.remove(name);
			} else {
				answer = name;
				break;
			}
		}		
		
		return answer;
	}
}
