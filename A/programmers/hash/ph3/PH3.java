/*
 * 문자열 2차원 배열에 의상의 이름과 의상의 종류가 주어진다.
 * 하루에 하나 이상의 의상을 입고, 한번 입은 의상의 조합과 동일한 조합은 더이상 입지 않는다고 할 때
 * 서로 다른 옷의 조합의 수를 구하자.
 * clothes의 각 행은 [의상 이름, 의상 종류]로 이루어져 있다.
 * 의상의 수는 1개 이상 30개 이하이다.
 * 같은 이름을 가진 의상은 존재하지 않는다.
 * clothes의 모든 원소는 문자열로 이루어져 있다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_'로만 이루어져 있다.
 */
package programmers.hash.ph3;

import java.util.HashMap;

// 의상의 이름과 종류를 저장하는 객체.
class Clothes{
	private String[][] clothes;
	
	public Clothes(int N) {
		clothes = new String[N][2];
	}
	
	public String[][] getClothes() {
		return clothes;
	}
	
	public void setClothes(String[][] clothes) {
		System.arraycopy(clothes, 0, this.clothes, 0, clothes.length);
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
class Solution{
	public int solution(String[][] clothes) {
		int answer = 0;
		
		HashMap<String, Integer> hm1 = new HashMap<>();
		
		for(int i = 0; i < clothes.length; i++) {
			if(!hm1.containsKey(clothes[i][1]))
				hm1.put(clothes[i][1], 1);
			else hm1.put(clothes[i][1], hm1.get(clothes[i][1]) + 1);
		}

		if(hm1.size() > 1) {
			answer = 1;
			for(Object o : hm1.keySet()) {
				answer *= hm1.get(o);
			}
		}
		answer += clothes.length;
		
		return answer;
	}
}

public class PH3 {
	public static void main(String[] args) {
		Solution sol = new Solution();

		String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blu_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		Clothes clo1 = new Clothes(clothes1.length);
		clo1.setClothes(clothes1);
		System.out.println(sol.solution(clo1.getClothes()));
		
		String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
		//String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face1"}, {"smoky_makeup", "face1"}};
		Clothes clo2 = new Clothes(clothes2.length);
		clo2.setClothes(clothes2);
		System.out.println(sol.solution(clo2.getClothes()));
	}
}
