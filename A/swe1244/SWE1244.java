package swe1244;

import java.util.Scanner;

class NumBoard{
	private int numLength;
	private int[] numSrc;
	private int number;
	private int[] numList;
	private int numListCount = 0;
	
	public NumBoard(int numLength, int tradeChance) {
		this.numLength = numLength;
		numSrc = new int[numLength];
		
		int k = 1;
		
		for(int i = 0; i < numLength; i++) {
			k *= numLength - i;
		}
		k *= tradeChance;
		numList = new int[k];
	}
	
	public void setNumSrc(int i, int num) {
		numSrc[i] = num;
	}
	
	public int getNumber() {
		toInteger();
		return number;
	}
	
	public int[] getNumList() {
		return numList;
	}
	
	public void setNumList(int i, int num) {
		this.numList[i] = num;
	}
	
	public void setNumListCount(int numListCount) {
		this.numListCount = numListCount;
	}
	public int getNumListCount() {
		return numListCount;
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

			int result = 0;
			
			for(int i = 0; i < nb.getNumListCount(); i++) {
				if(result < nb.getNumList()[i]) result = nb.getNumList()[i];
				//System.out.println(nb.getNumList()[i]);
			}
			
			System.out.println("#" + testCase + " " + result);
		}

	}
	
	public static void trade(int numLength, int tradeChance, NumBoard nb) {
		for(int i = 0; i < numLength; i++) {
			for(int j = i; j < numLength; j++) {
				nb.tradeBoard(i, j);
				tradeChance--;
				
				if(tradeChance > 0) trade(numLength, tradeChance, nb);
				else {
					nb.setNumList(nb.getNumListCount(), nb.getNumber());
					nb.tradeBoard(i, j);
					nb.setNumListCount(nb.getNumListCount() + 1);
				}
				
				tradeChance++;
			}
		}
	}

}
