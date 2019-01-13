/*
 * 문자열 2차원 배열에 의상의 이름과 의상의 종류가 주어진다.
 * 하루에 하나 이상의 의상을 입고, 한번 입은 의상의 조합과 동일한 조합은 더이상 입지 않는다고 할 때
 * 서로 다른 옷의 조합의 수를 구하자.
 * clothes의 각 행은 [의상 이름, 의상 종류]로 이루어져 있다.
 * 의상의 수는 1개 이상 30개 이하이다.
 * 같은 이름을 가진 의상은 존재하지 않는다. -> 중복되는 의상이 없다. 의상의 이름에는 신경 쓸 필요 없다.
 * clothes의 모든 원소는 문자열로 이루어져 있다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_'로만 이루어져 있다.
 */
package programmers.hash.ph3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

// 의상의 이름과 종류를 저장하는 객체.
class Clothes{
	private String[][] clothes;
	
	public Clothes(int N) {
		clothes = new String[N][2];
	}
	
	public String[][] getClothes() {
		return clothes;
	}
	
	public void setClothes(int i, String clothesName, String clothesType) {
		clothes[i][0] = clothesName;
		clothes[i][1] = clothesType;
	}
}

// 만들 수 있는 세트를 찾을 때 hash map을 이용해보자.
// key 값에 의상 이름을 넣고, value 값에 의상 종류를 넣고, 추가하려는 의상의 종류가 이미 hash map에 있는지 확인하고,
// 아니면 key 값에 의상 종류를 넣거나...
// 조합을 만들려면 재귀로 해야하는가...
// 그냥 일반적으로 재귀함수 써서 조합을 만들려니 당연히 시간초과가 뜬다. 덤으로 애초에 답이 틀리는것도 있고..
// 의상의 종류별로 나눠서...
// 오 뭔가 좋은 생각이 떠올랐다.
// 조합을 다 만들어내는 알고리즘이 아니라 부위별로 의상의 개수를 구한 다음에 부위별 의상의 개수를 곱하는 식으로 구해보자.
// 1 = 1.
// 1, 2 = 1 + 2 + 1 * 2.
// 1, 2, 3 = 1 + 2 + 3 + 1 * 2 + 1 * 3 + 2 * 3 + 1 * 2 * 3.
// 1, 2, 3, 4 = 1 + 2 + 3 + 4 + 1 * 2 + 1 * 3 + 1 * 4 + 2 * 3 + 2 * 4 + 3 * 4 + 1 * 2 * 3 + ...
// 그렇게 곱하려면 결국 조합을 만들어야겠다..
// 됐다. 근데 다 되는데 한가지 테스트 케이스에서 시간초과가 난다. 30가지 모두 다른 의상의 종류로 돌려보니 시간이 엄청 오래 걸린다. 어떻게 줄일 수 있을까.
// 와씨 충격적이다. 시간초과에 대한 누군가의 질문에 가능한 모든 조합의 수를 구하는 방법을 올려놨다.
// 특정 옷의 종류에 대해서 옷의 수가 있을 때 해당 종류의 옷을 입지 않는 경우까지 고려해서 옷의 수에 1을 더해준다.
// 그렇게 구해진 각 종류별 옷의 수를 다 곱하면 모든 종류의 옷을 안입는 경우부터 하나만 입는 경우랑 쭉쭉쭉해서 모든 종류의 옷을 다 입는 경우의 수까지 다 합한 값이 나온다.
// 거기서 아무것도 안입는 경우는 없으니 1을 빼면....
// ex) 머리 : 3, 얼굴 : 2, 몸 1 => (3 + 1) * (2 + 1) * (1 + 1) - 1 = 13.
// 헛수고한 거 같지만 그래도 조합공부했으니..
class Solution{
	public int solution(String[][] clothes) {
		int answer = 1;
		
		// HashMap Key에 의상 종류, Value에 의상 수를 저장한다.
		HashMap<String, Integer> hm = new HashMap<>();
		for(int i = 0; i < clothes.length; i++) {
			if(!hm.containsKey(clothes[i][1]))
				hm.put(clothes[i][1], 1);
			else hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
		}

		for(Object o : hm.keySet()) {
			answer *= hm.get(o) + 1;
		}
		answer--;
		
/*		// 의상 종류를 ArrayList에 따로 저장한다. 
		ArrayList<Object> arr = new ArrayList<>();
		for(Object o : hm.keySet()) {
			arr.add(o);
		}
		
		// 가능한 의상 종류 조합에서 의상 종류 별 의상 수를 저장할 배열.
		int[] intArr = new int[hm.size()];
		for(int i = 1; i <= hm.size(); i++) {
			answer = comb(intArr, 0, i, hm, arr, 0, answer);
		}*/
		
		return answer;
	}
	
/*	public int comb(int[] intArr, int index, int r, HashMap<String, Integer> hm, ArrayList<Object> arr, int target, int answer) {
		if(r == 0) {
			int temp = 1;
			for(int i = 0; i < index; i++) {
				temp *= intArr[i];
			}
			answer += temp;
			return answer;
		}
		else if (target == hm.size()) return answer;
		else {
			intArr[index] = hm.get(arr.get(target));
			answer = comb(intArr, index + 1, r - 1, hm, arr, target + 1, answer);
			answer = comb(intArr, index, r, hm, arr, target + 1, answer);
		}
		
		return answer;
	}*/
}

public class PH3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int testCase = 0; testCase < T; testCase++) {
			int N = sc.nextInt();
			Clothes clo = new Clothes(N);
			for(int i = 0; i < N; i++) {
				clo.setClothes(i, sc.next(), sc.next());
			}
			
			Solution sol = new Solution();
			System.out.println(sol.solution(clo.getClothes()));
			
		}
	}
}
