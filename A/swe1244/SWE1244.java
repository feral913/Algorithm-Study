/*
 * 6개 이하의 숫자판과 숫자판을 교환할 수 있는 횟수가 주어진다.
 * 주어진 교환 횟수 만큼 숫자판들의 자리를 교환하여 만들 수 있는 가장 큰 숫자를 찾자.
 * 
 */

package swe1244;

import java.util.Scanner;

class NumBoard{
	private int numLength;
	private int[] numSrc;
	private int number;
	private int largestNum;
	
	public NumBoard(int numLength, int tradeChance) {
		this.numLength = numLength;
		numSrc = new int[numLength];
	}
	
	public int[] getNumSrc() {
		return numSrc;
	}
	
	public void setNumSrc(int i, int num) {
		numSrc[i] = num;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getLargestNum() {
		return largestNum;
	}
	
	public void setLargestNum(int num) {
		if(largestNum < num) largestNum = num;
	}
	
	public void tradeBoard(int i, int j) {
		int temp = numSrc[i];
		numSrc[i] = numSrc[j];
		numSrc[j] = temp;
	}
	
	public void toInteger() {
		number = 0;
		
		for(int i = 0; i < numLength; i++) {
			number += numSrc[numLength - i - 1] * Math.pow(10, i);
		}
	}
}

public class SWE1244 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			String str = sc.next();
			int tradeChance = sc.nextInt();
			
			NumBoard nb = new NumBoard(str.length(), tradeChance);
			
			for(int i = 0; i < str.length(); i++) {
				nb.setNumSrc(i, str.charAt(i) - '0');
			}

			trade(str.length(), tradeChance, nb);

			System.out.println("#" + testCase + " " + nb.getLargestNum());
		}

	}
	
	public static void trade(int numLength, int tradeChance, NumBoard nb) {
		for(int i = 0; i < numLength; i++) {
			for(int j = i; j < numLength; j++) {
				if(nb.getNumSrc()[i] == nb.getNumSrc()[j]) continue;
				
				nb.tradeBoard(i, j);
				nb.toInteger();
				tradeChance--;
				
				if(tradeChance > 0) trade(numLength, tradeChance, nb);

				nb.setLargestNum(nb.getNumber());
				nb.tradeBoard(i, j);
				nb.toInteger();
				
				tradeChance++;
			}
		}
	}

}
