/*
 * 비밀번호가 M 개의 키를 모두 사용한 N 자리수라는 걸 알 때.
 * 가능한 비밀번호의 수를 구하자.
 * 1 <= M <= N <= 100.
 * 결과값이 매우 클 수 있으므로 1,000,000,007로 나눈 나머지 값을 출력한다.
 */

package swe.swe6026;

import java.util.HashMap;
import java.util.Scanner;

class Password{
	private int keyNum;
	private int length;
	
	public Password(int M, int N) {
		keyNum = M;
		length = N;
	}
	
	public int getKeyNum() {
		return keyNum;
	}
	
	public int getLength() {
		return length;
	}
}

public class SWE6026 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			
			Password pass = new Password(M, N);
			HashMap<Integer, Integer> check = new HashMap<>();
			for(int i = 0; i < M; i++) {
				check.put(i, 0);
			}
			
			System.out.println("#" + testCase + " " + solution(pass.getKeyNum(), pass.getLength(), check, 0));
		}
	}
	
	// M 개의 문자가 있다.
	// 길이 N의 문자열을 만든다.
	// -> 만들 수 있는 모든 경우의 수는 M^N.
	// 이때 M 개의 문자 중 하나의 문자라도 사용되지 않은 경우를 제외한다.
	// 그렇다면 M 개의 문자 중 하나의 문자를 뽑는 작업을 N 만큼 수행한다.
	// 그리고 마지막에 M 개의 문자 중 하나의 문자라도 사용되지 않은 문자가 있으면 해당 문자열은 제외한다.
	// 자리 수가 길어질 수록 시간이 너무 오래 걸린다.
	// 버퍼를 사용하거나 수식으로 해결하는 등 다른 방법을 찾아야겠다.
	public static long solution(int M, int N, HashMap<Integer, Integer> check, long answer) {
		final int cut = 1000000007;
		
		// 문자열이 끝났으면.
		if(N == 0) {
			// M 개의 문자 중 사용되지 않은 문자가 없으면 카운트 + 1.
			if(!check.containsValue(0)) answer++;
			return answer;
		}
		
		// M 개의 문자를 순서대로 하나 씩 뽑는다.
		// 하나를 뽑고 다음 문자를 뽑는다.
		for(int i = 0; i < M; i++) {
			check.put(i, check.get(i) + 1);
			answer = solution(M, N - 1, check, answer);
			check.put(i, check.get(i) - 1);
		}
		
		answer %= cut;
				
		return answer;
	}
	
	public static long solution2(int M, int N, int i) {
		return 0;
	}
	
	public static long factorial(long number, long ret) {
		if(number == 0) return 0;
		if(number == 1) return 1;
		ret = number * factorial(number - 1, ret);
		return ret;
	}
	
	public static long combination(long n, long r) {
		if(n == r) return 1;
		long ret = factorial(n, 0) / (factorial(r, 0) * factorial(n - r, 0));
		return ret;
	}
}
