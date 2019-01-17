/*
 * 비밀번호가 M 개의 키를 모두 사용한 N 자리수라는 걸 알 때.
 * 가능한 비밀번호의 수를 구하자.
 * 1 <= M <= N <= 100.
 * 결과값이 매우 클 수 있으므로 1,000,000,007로 나눈 나머지 값을 출력한다.
 */

package swe.swe6026;

import java.util.Scanner;

class Password{
	private long keyNum;
	private long length;
	
	public Password(long M, long N) {
		keyNum = M;
		length = N;
	}
	
	public long getKeyNum() {
		return keyNum;
	}
	
	public long getLength() {
		return length;
	}
}

public class SWE6026 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			long M = sc.nextInt();
			long N = sc.nextInt();
			
			Password pass = new Password(M, N);
			
			System.out.println("#" + testCase + " " + solution(pass.getKeyNum(), pass.getLength()));
		}
	}
	
	// M 개의 문자를 모두 이용해서 만들 수 있는 길이 N의 문자열의 수를 구한다.
	// M 개의 문자를 이용해서 만들 수 있는 길이 N의 문자열의 수에서 M 개의 문자 중 하나 이상 사용하지 않고 만들 수 있는 길이 N의 문자열의 수를 뺸다.
	// M 개의 문자 중 하나만 이용해서 만들 수 있는 길이 N의 문자열 = M 개.
	// M 개의 문자 중 두개만 이용해서 만들 수 있는 길이 N의 문자열 = C(M, 2) * 2^N.
	public static long solution(long M, long N) {
		final int cut = 1000000007;
		
		long answer = (long)(Math.pow(M, N));
		for(int i = 1; i < M; i++) {
			answer = answer - (long)(Math.pow(M - i, N) * combination(M, M - i)); 
		}
		
		return answer;
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
