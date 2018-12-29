/*
 * 
 */

package swe1244;

import java.util.Scanner;

class NumBoard{
	private int numLength;
	private int[] numSrc;
	private int number;
	private int largestNum;
	private int[] numList;
	
	public NumBoard(int numLength, int tradeChance) {
		this.numLength = numLength;
		numSrc = new int[numLength];
		// 6자리 숫자를 재배열해서 구할 수 있는 모든 경우의 수
		int temp = 6 * 5 * 4 * 3 * 2 * 1;
		numList = new int[temp];
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
	
	public void setNumList(int i, int num) {
		numList[i] = num;
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
			for(int j = i + 1; j < numLength; j++) {
				if(nb.getNumSrc()[i] == nb.getNumSrc()[j]) continue;
				
				if(tradeChance > 1) {
					nb.tradeBoard(i, j);
					nb.toInteger();
					trade(numLength, tradeChance - 1, nb);
				} else {
					nb.tradeBoard(i, j);
					nb.toInteger();
					nb.setLargestNum(nb.getNumber());
					nb.tradeBoard(i, j);
					nb.toInteger();
				}
			}
		}
	}

}
