/*
 * x���� �ǹ��� ���� ĭ, y���� �ǹ��� ����(����)�̴�.
 * ���� �ǹ��� ����, ������ �� ��ĭ ���� �ִ� �ǹ��� ������ �ʴ� ��ŭ �ش� �ǹ��� �������� Ȯ���ȴ�.
 * �׽�Ʈ ���̽� �� ��ü �ǹ����� �������� Ȯ���� �� ���� ���� ���϶�.
 */

package swe1206;

import java.util.Scanner;

// �ǹ� �� ���� ������ ���� Building ��ü.
class Building{
	private int[] height;
	
	public Building(int maxWidth) {
		this.height = new int[maxWidth];
	}
	
	public int getHeight(int i) {
		return height[i];
	}
	
	public void setHeight(int i, int height) {
		this.height[i] = height;
	}
}

public class SWE1206 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int maxWidth = sc.nextInt();
			int result = 0;
			
			Building bd = new Building(maxWidth);
			
			for(int i = 0; i < maxWidth; i++) {
				bd.setHeight(i, sc.nextInt());
			}

			// ���� ���ʰ� ������ ���� ��ĭ(0, 1, maxWidth - 1, maxWidth - 2)���� �ǹ��� ����.
			// ���� �ǹ��� ���ؼ� �¿� ��ĭ ���� �ִ� �ǹ��� �� ���� ���� �ǹ��� ���̸� ���Ѵ�.
			// ���� ���� �ǹ��� �¿� ��ĭ ���� �ִ� �ǹ� �� ���� ���� �ǹ����� ���ٸ�, ���� �ǹ����� ���� ���� �ǹ��� ���̸� �� ��ŭ ���� �ǹ��� �������� Ȯ���� ���� ���� �ִ�.
			for(int i = 2; i < maxWidth - 2; i++) {
				int lTopBuilding = 0;
				int rTopBuilding = 0;
				int topBuilding = 0;
				
				if(bd.getHeight(i - 2) > bd.getHeight(i - 1)) lTopBuilding = i - 2;
				else lTopBuilding = i - 1;
				
				if(bd.getHeight(i + 2) > bd.getHeight(i + 1)) rTopBuilding = i + 2;
				else rTopBuilding = i + 1;
				
				if(bd.getHeight(lTopBuilding) > bd.getHeight(rTopBuilding)) topBuilding = lTopBuilding;
				else topBuilding = rTopBuilding;

				if(bd.getHeight(i) > bd.getHeight(topBuilding)) {
					result += bd.getHeight(i) - bd.getHeight(topBuilding);
				}
			}
			
			System.out.println("#" + testCase + " " + result);
		}
	}

}
