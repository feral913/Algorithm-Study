/*
 * 6자리 이내의 숫자와 교환 가능한 횟수가 주어진다.
 * 주어진 횟수만큼 숫자의 i번째와 j번째(i != j) 자리 숫자를 교환하여 만들 수 있는 가장 큰 수를 찾자.
 */

package SWE.swe1244_BruteForceSearch_DP;

import java.util.Scanner;

// 단순히 완전 탐색으로 해결할 경우 교환 가능 횟수가 많아질수록 시간이 점점 더 오래 걸린다. 결국 시간초과.
// 그럼 어디서 작업(중복)을 줄일 것인가.
// 줄일 수 있는 방법은 특정 수에 대하여 N회의 교환이 이루어졌을 때 만들 수 있는 최대값을 저장해놓고,
// 작업 과정에서 동일한 과정이 한번 더 발생한다면 해당 작업을 수행하지 않고 저장된 값을 바로 불러온다.

// 주어진 숫자, 숫자를 각 자리별로 나눈 배열, 그리고 특정 숫자를 몇회 교환작업하였을 때 구할 수 있는 가장 큰 수의 2차원 배열을 갖는 객체.
class NumBoard{
	// 주어진 숫자와 숫자를 각 자리별로 나눈 배열.
	private int number;
	private int[] numSrc;
	// 숫자 A를 B회 교환하였을 때 만들 수 있는 가장 큰 수를 저장한 배열.
	// ex) 숫자 123을 1회 교환해서 만들 수 있는 가장 큰 수는 321. numList[123][1] = 321.
	private int[][] numList;
	
	// 객체 생성 시 숫자를 string으로 받아오고, 교환 횟수를 받아온다.
	// string으로 받아 온 숫자를 int로 변환하여 저장한다.
	// string으로 받아 온 숫자를 한자리씩 분리하여 숫자 배열에 넣는다.
	// 만들 수 있는 가장 큰 값의 2차원 배열은 최대 6자리 숫자가 입력되기 때문에 999999개의 행에 교환 횟수 만큼의 열을 만들어준다.
	// 0이 입력될 가능성을 고려하여 -1로 초기화해준다.
	public NumBoard(String str, int tradeChance) {
		number = Integer.parseInt(str);
		
		numSrc = new int[str.length()];
		for(int i = 0; i < str.length(); i++) {
			numSrc[i] = str.charAt(i) - '0';
		}
		
		numList = new int[999999][tradeChance + 1];
		for(int i = 0; i < numList.length; i++) {
			for(int j = 0; j < numList[i].length; j++) {
				numList[i][j] = -1;
			}
		}
	}
	
	// 숫자 자리수 반환.
	public int getNumLength() {
		return numSrc.length;
	}
	
	// 현재 숫자 반환.
	public int getNumber() {
		return number;
	}
	
	// 특정 숫자를 주어진 횟수만큼 교환해서 만들 수 있는 최대값을 반환한다.
	public int getNumList(int num, int tradeChance) {
		return numList[num][tradeChance];
	}
	
	// 특정 숫자를 주어진 횟수만큼 교환해서 만들 수 있는 최대값을 저장한다.
	public void setNumList(int num, int tradeChance, int result) {
		numList[num][tradeChance] = result;
	}
	
	// 현재 숫자의 i번쨰 자리 숫자와 j번쨰 자리 숫자를 교환한다.
	public void tradeBoard(int i, int j) {
		if(numSrc[i] != numSrc[j]) {
			int temp = numSrc[i];
			numSrc[i] = numSrc[j];
			numSrc[j] = temp;
		}

		number = 0;
		for(int k = 0; k < numSrc.length; k++) {
			//number += numSrc[numLength - i - 1] * Math.pow(10, i);
			number = number * 10 + numSrc[k];
		}
	}
}

public class SWE1244 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			String str = sc.next();
			int tradeChance = sc.nextInt();
			
			NumBoard nb = new NumBoard(str, tradeChance);

			System.out.println("#" + testCase + " " + solve(nb, tradeChance));
		}

		sc.close();
	}
	
	public static int solve(NumBoard nb, int tradeChance) {
		// 현재 숫자.
		int number = nb.getNumber();
		// 현재 숫자에 대하여 tradeChance 만큼 교환해서 만들 수 있는 가장 큰 수(작업수행 후 저장한 값을 불러온다).
		int result = nb.getNumList(number, tradeChance);
		
		// 불러온 값이 -1이 아니면 이미 한번 작업을 했던 과정이다.
		// 작업을 더이상 수행하지 않고, 결과값을 반환한다.
		if(result != -1) return result;
		
		// 교환 기회가 더이상 없으면 현재 값을 결과값으로 저장하고 반환한다.
		if(tradeChance == 0) {
			nb.setNumList(number, tradeChance, number);
			return nb.getNumber();
		}
		
		int numLength = nb.getNumLength();
		for(int i = 0; i < numLength; i++) {
			for(int j = i + 1; j < numLength; j++) {
				// 현재 숫자의 i번쨰 자리 숫자와 j번째 자리 숫자를 교환한다.
				nb.tradeBoard(i, j);
				int temp = solve(nb, tradeChance - 1);
				// 다음 경우를 확인하기 위해 다시 숫자를 원래대로 돌린다.
				nb.tradeBoard(i, j);
				// 저장되어 있는 결과값보다 현재 숫자가 더 크면 현재 숫자로 결과값을 바꾼다.
				if(nb.getNumList(number, tradeChance) < temp) nb.setNumList(number, tradeChance, temp);
			}
		}
		
		return nb.getNumList(number, tradeChance);
	}

}
