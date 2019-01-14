/*
 * N개의 전화번호가 있는 전화번호부가 있다.
 * 전화번호부에 있는 전화번호 중 한 번호가 다른 번호의 접두어진 경우를 확인하자.
 */

package programmers.hash.ph2;

import java.util.HashMap;
import java.util.Scanner;

// String 타입으로 전화번호를 저장하는 전화번호부 객체.
class PhoneBook{
	private String[] phoneNum;
	
	public PhoneBook(int N) {
		phoneNum = new String[N];
	}
	
	public String[] getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(int i, String phoneNum) {
		this.phoneNum[i] = phoneNum;
	}
}

// 문제 카테고리가 hash라서 hash map을 이용해서 풀어보려는데 hash map의 key 혹은 value 값 일부가 동일한 데이터를 찾는것이 가능한가?
// 인터넷에 검색해보니 마찬가지로 hash map을 이용하여 key에 이름적고 value에 번호 적어서 전화번호부 만들고 싶은데 특정 문자를 포함하는 모든 key 값의 value를 찾는게 되느냐는 질문이 있다.
// 답으로는 hash map은 적절하지 않고 tree map을 쓰라고 한다............
// 프로그래머스의 해당 질문의 질문하기를 보니 다른 방법으로 풀고 hash를 왜 써야하는지 혹은 어떻게 써야 하는지 의문을 갖는 사람들이 있는 거 같다.
// 해결 1. 다른 번호를 접두어로 사용하는 번호를 찾는 거니까, 각 번호별로 첫번째 자리부터 1개, 2개, 3개, ... 해서 마지막 자리 수를 제외한 숫자들을 전부 다 hash map에 저장했다.
// 예를 들어 123의 경우 1과 12를 hash map에 저장했다.
// 그리고 123이 있는지 찾는다.
// 만약 123을 접두어로 사용하는 번호가 있었다면 123이 hash map에 저장되었을 것이고 그에 따른 결과를 반환할 수 있다.
// 이 방법으로 채점이 통과가 돼서 놀랐다. 보니까 다른 사람들 중에 이와 비슷한 방법으로 푼 사람도 있긴 하다.
// 해결 2. Hash map을 이용하여 푼 또 다른 사람의 풀이 중 하나는
// hash map에는 원래의 전화번호만 저장하고 각 전화번호의 첫번째 자리부터 잘라서 hash map에 포함되어 있는지 확인한다.
// 내가 푼 방법과 순서만 조금 다른 거 같지만 hash map에 저장하는 데이터가 더 적어서 메모리 사용량이 더 적고
// 나는 모두 쪼개서 저장한 다음 확인했지만, 이 방법은 쪼개서 확인하는 과정에서 결과가 나오기 때문에 경우에 따라 시간도 더 적게 걸릴 거 같다.
class Solution{
	public boolean solution(String[] phone_book) {
		boolean answer = true;
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for(int i = 0; i < phone_book.length; i++) {
			for(int j = 1; j < phone_book[i].length(); j++) {
				if(!hm.containsKey(phone_book[i].substring(0, j))) {
					hm.put(phone_book[i].substring(0, j), 0);
				}
			}
		}

		for(int i = 0; i < phone_book.length; i++) {
			if(hm.containsKey(phone_book[i])) {
				answer = false;
				break;
			}
		}
		
		return answer;
	}
}

public class PH2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 0; testCase < T; testCase++) {
			int N = sc.nextInt();
			PhoneBook pb = new PhoneBook(N);
			
			for(int i = 0; i < N; i++) {
				String phoneNum = sc.next();
				pb.setPhoneNum(i, phoneNum);
			}

			Solution sol = new Solution();
			
			System.out.println(sol.solution(pb.getPhoneNum()));
		}
	}

}
