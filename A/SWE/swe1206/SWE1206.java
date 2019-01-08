/*
 * x축은 건물이 놓인 칸, y축은 건물의 높이(층수)이다.
 * 기준 건물의 왼쪽, 오른쪽 각 두칸 내에 있는 건물에 가리지 않는 만큼 해당 건물은 조망권이 확보된다.
 * 테스트 케이스 내 전체 건물에서 조망권이 확보된 총 층의 수를 구하라.
 */

package SWE.swe1206;

import java.util.Scanner;

// 건물 별 높이 정보를 갖는 Building 객체.
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
		Scanner sc = new Scanner(System.in);
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int maxWidth = sc.nextInt();
			int result = 0;
			
			Building bd = new Building(maxWidth);
			
			for(int i = 0; i < maxWidth; i++) {
				bd.setHeight(i, sc.nextInt());
			}

			// 가장 왼쪽과 오른쪽 각각 두칸(0, 1, maxWidth - 1, maxWidth - 2)에는 건물이 없다.
			// 기준 건물에 대해서 좌우 두칸 내에 있는 건물들 중 가장 높은 건물의 높이를 구한다.
			// 만약 기준 건물이 좌우 두칸 내에 있는 건물 중 가장 높은 건물보다 높다면, 기준 건물에서 가장 높은 건물의 높이를 뺀 만큼 기준 건물은 조망권이 확보된 층을 갖고 있다.
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