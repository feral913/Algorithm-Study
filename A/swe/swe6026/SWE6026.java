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

class Comb{
	private long[][] comb;
	
	public Comb(int M, int N) {
		comb = new long[M][N];
	}
	
	public long getComb(int M, int N) {
		return comb[M][N];
	}
	
	public void setComb(int M, int N, long num) {
		comb[M][N] = num;
	}
}

class Per{
	private long[] per;
	
	public Per(int M) {
		per = new long[M];
	}
	
	public long getPer(int M) {
		return per[M];
	}
	
	public void setPer(int M, long num) {
		per[M] = num;
	}
}

public class SWE6026 {
	public static Comb comb;
	public static Per per;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			
			Password pass = new Password(M, N);
			
/*			HashMap<Integer, Integer> check = new HashMap<>();
			for(int i = 0; i < M; i++) {
				check.put(i, 0);
			}

			System.out.println("#" + testCase + " " + solution(pass.getKeyNum(), pass.getLength(), check, 0));*/
			
			comb = new Comb(M + 1, N + 1);
			per = new Per(M + 1);
			
			System.out.println("#" + testCase + " " + solution2(pass.getKeyNum(), pass.getLength()));
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
	
	// M개의 문자를 적어도 한번씩 만들 수 있는 길이 N의 문자열의 수 =
	// M개의 문자를 사용해서 만들 수 있는 길이 N의 모든 문자열의 수 -
	// (M개의 문자 중 M - 1개의 문자를 뽑는 경우의 수) * (M - 1개의 문자를 적어도 한번 씩 사용해서 만들 수 있는 길이 N의 문자열의 수) -
	// (M개의 문자 중 M - 2개의 문자를 뽑는 경우의 수) * (M - 2개의 문자를 적어도 한번 씩 사용해서 만들 수 있는 길이 N의 문자열의 수) -
	// ...
	// (M개의 문자 중 0개의 문자를 뽑는 경우의 수) * (0개의 문자를 적어도 한번 씩 사용해서 만들 수 있는 길이 N의 문자열의 수). 
	// f(M, N) = M^N - f(M - 1, N).
	// f(0, N) = 0.
	// f(1, N) = 1^N - C(1, 0) * f(0).
	// f(2, N) = 2^N - C(2, 1) * f(1) - C(1, 0) * f(0).
	// ...
	// f(M, N) = M^N - C(M, M - 1) * f(M - 1, N) - C(M, M - 2) * f(M - 2, N) - ... - C(M, 1) * f(1, N) - C(M, 0) * f(0, N).
	// 근데 문제가 또 발생했다.
	// 첫번째 방식에 비해 상대적으로 시간은 더 적게 걸리지만 그래도 역시 값이 커질수록 시간이 많이 오래 걸린다.
	// 문제 해결 과정에 전부 재귀로 몇번씩 반복되니까 그런거 같다.
	// 이걸 또 어떻게 해결해야할까..
	public static int solution2(int M, int N) {
		final int divide = 1000000007;
		
		// 0개의 문자로 만들 수 있는 길이 N의 문자열의 수 = 0. 
		if(M == 1) return 1;
		
		// M개의 문자를 사용하여 만들 수 있는 길이 N의 문자열의 수.
		long ans = (long)Math.pow(M, N);
		for(int i = M - 1; i > 0; i--) {
			long temp = combination(M, i) * solution2(i, N);
			ans -= temp;
		}
		
		return (int)ans % divide;
	}
	
	// 순열을 이용한 조합.
	public static long combination(int n, int r) {
		if(comb.getComb(n, r) != 0) return comb.getComb(n, r);
		comb.setComb(n, r, permutation(n) / (permutation(r) * permutation(n - r)));
		return comb.getComb(n, r);
	}
	
	public static long permutation(int n) {
		if(per.getPer(n) != 0) return per.getPer(n);
		if(n == 1) {
			per.setPer(n, 1);
			return per.getPer(n);
		}
		per.setPer(n, n * permutation(n - 1));
		return per.getPer(n);
	}

	// 계승을 이용한 조합.
/*	public static long combination(long n, long r) {
		if(n == r) return 1;
		long ret = factorial(n, 0) / (factorial(r, 0) * factorial(n - r, 0));
		return ret;
	}

	public static long factorial(long number, long ret) {
		if(number == 0) return 0;
		if(number == 1) return 1;
		ret = number * factorial(number - 1, ret);
		return ret;
	}*/
}
