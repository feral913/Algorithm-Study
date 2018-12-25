package swe6730;

import java.util.Scanner;

// ��� ��ü
class Block{
	private int blockNum;
	private int[] blockHeight; // ���̰� �ٸ� ��� �迭
	// ���� ���̵� : �ö� �� ���� ���� ���� ��ȭ�� ������ �� ���� ���� ���� ��ȭ
	private int upLevel;
	private int downLevel;
	
	public Block(int blockNum) {
		this.blockNum = blockNum;
		blockHeight = new int[blockNum];
		upLevel = 0;
		downLevel = 0;
	}
	
	// ��� �� ���� ����
	public void setBlockHeight(int i, int blockHeight) {
		this.blockHeight[i] = blockHeight;
	}
	
	public int getUpLevel()	{
		return upLevel;
	}
	
	// �پ� �ִ� ������ ��� ���� - ���� ��� ���� = �ö� �� ����
	// �� �� ���� ū �� ����
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
	
	// �پ� �ִ� ���� ��� ���� - ������ ��� ���� = ������ �� ����
	// ���� ���� ū �� ����
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
