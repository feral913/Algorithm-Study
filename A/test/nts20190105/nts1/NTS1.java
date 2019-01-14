/*
 * 복리법을 적용하여 원금 p원이 m달 후에 d원 이상이 되도록 하는 최소 월 이율 k%를 찾으려고 한다.
 * 단, k는 가능한 k 값 중 가장 작은 정수. 매달 이자는 소수점 이하를 버린 값이 더해진다고 가정.
 * 원금 p는 1,000,000 이상 1,000,000,000 이하의 자연수이며, 100만 단위로만 주어진다.
 * 목표 달 m은 1 이상 20 이하의 자연수이다.
 * 목표 금액 d는 1,000,000 이상 1,000,000,000 이하의 자연수 (p <= d <= p * 2^m)이며, 100만 단위로만 주어진다.
 */
package test.nts20190105.nts1;

// 시험 당일에는 조금 다르게 풀었던 거 같은데 백업을 안해놔서 잘 모르겠다.
// 지금 생각해보니 문제를 잘못 이해했던거 같다.
// 잘못 이해해서 잘못 풀었었는데 샘플 테스트케이스는 맞았었고...
// 제대로 이해했으면 엄청 간단하게 풀 수 있었지 않았을까..
// 마음이 급해서 문제를 대충 읽는게 문제인 거 같다.
class Solution{
	public int solution(int p, int m, int d) {
		int k = 0;

		int temp = p;
		while(temp < d) {
			temp = p;
			k++;
			temp = (int)(p * Math.pow((1 + (double)k / 100), m));
		}
		
		return k;
	}
}

public class NTS1 {
	public static void main(String[] args) {
		int p[] = {10000000, 1000000};
		int m[] = {9, 3};
		int d[] = {13000000, 1000000};
		
		Solution sol = new Solution();
		
		for(int i = 0; i < 2; i++) {
			System.out.println(sol.solution(p[i], m[i], d[i]));
		}
	}
}
