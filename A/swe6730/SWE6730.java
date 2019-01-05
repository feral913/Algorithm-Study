/*
 * N개의 연속된 블록의 높이가 주어진다.
 * 왼쪽에서 오른쪽으로 블록을 타고 넘어갈 때,
 * 다음 블록의 높이가 더 높으면 올라가야 하고, 더 낮으면 내려가야 한다.
 * 올라가야 하는 높이 변화 중 가장 큰 값이 올라갈 때의 난이도이다.
 * 내려가야 하는 높이 변화 중 가장 큰 값이 내려갈 때의 난이도이다.
 * 각각의 난이도를 구하자.
 */

package swe6730;

import java.util.Scanner;

// 블록 객체
class Block{
	private int blockNum;
	private int[] blockHeight; // 높이가 다른 블록 배열
	// 경주 난이도 : 올라갈 때 가장 심한 높이 변화와 내려갈 때 가장 심한 높이 변화
	private int upLevel;
	private int downLevel;
	
	public Block(int blockNum) {
		this.blockNum = blockNum;
		blockHeight = new int[blockNum];
		upLevel = 0;
		downLevel = 0;
	}
	
	// 블록 별 높이 저장
	public void setBlockHeight(int i, int blockHeight) {
		this.blockHeight[i] = blockHeight;
	}
	
	public int getUpLevel()	{
		return upLevel;
	}
	
	// 붙어 있는 오른쪽 블록 높이 - 왼쪽 블로 높이 = 올라갈 때 높이
	// 그 중 가장 큰 값 저장
	public void setUpLevel() {
		int diff;
		
		for(int i = 0; i < blockNum - 1; i++) {
			diff = blockHeight[i + 1] - blockHeight[i];
			if(upLevel < diff) upLevel = diff;
		}
	}
	
	public int getDownLevel() {
		return downLevel;
	}
	
	// 붙어 있는 왼쪽 블록 높이 - 오른쪽 블록 높이 = 내려갈 때 높이
	// 그중 가장 큰 값 저장
	public void setDownLevel() {
		int diff;
		
		for(int i = 0; i < blockNum - 1; i++) {
			diff = blockHeight[i] - blockHeight[i + 1];
			if(downLevel < diff) downLevel = diff;
		}
	}
}

public class SWE6730 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			Block bl = new Block(N);
			
			for(int i = 0; i < N; i++) {
				bl.setBlockHeight(i, sc.nextInt());
			}
			
			bl.setUpLevel();
			bl.setDownLevel();
			
			System.out.println("#" + testCase + " " + bl.getUpLevel() + " " + bl.getDownLevel());
		}
	}

}