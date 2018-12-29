/*
 * N개의 점이 주어진다.
 * 이 중 세 점을 선택하여 만들어진 삼각형 중에서 넓이가 A/2 이상, B/2 이하인 것의 개수를 구하자.
 */

package swe6742;

import java.util.Scanner;

// 1. 삼각형 넓이 : 밑변과 높이를 구해 삼각형의 넓이를 구하는 것이 문제일 줄 알았는데
// 세 점이 주어졌을 때 삼각형의 넓이를 구하는 공식이 있어서 넓이 구하는 것은 간단하다.
// 2. 세 점 뽑기 : 그렇다면 문제는 여러개의 점이 주어졌을 때 세 개의 점을 뽑아낼 수 있는 모든 경우. 조합을 구현하는 것.
// 점이 몇개가 주어지든 그 중에서 뽑아야 할 점은 세개로 고정이니까. 그냥 3중 for문으로 만든다.

// 으으으으으으음.....런타임 에러가 뜬다.
// 변수 크기는 충분한거 같은데..
// 배열 크기도 점의 최대 입력 개수가 2천개니까..생성 가능한 배열 크기를 확인해봤다.
// int 타입 최대 값만큼이고 만약에 2000개의 점이 입력되었을 때 만들어지는 삼각형의 수는 2000*1999*1998/6으로 int 범위는 안넘는다.
// 근데 문제는 나누기 전에 곱하는 과정에서 범위를 넘어가버려서 N(점 수)과 triNumber(삼각형 수) 변수의 타입만 long으로 바꿔주고 int로 타입캐스트해서 써줬다.
// 하지만 그래도 런타임 에러..
class Triangles{
	private double[][] dots;
	private long triNumber;
	private double[] areas;
	
	public Triangles(long N) {
		dots = new double[(int)N][2];
		
		// 삼각형의 개수. 조합 C(N, 3).
		// (N * (N - 1) * (N - 2)) / (3 * 2 * 1)
		triNumber = (N * (N - 1) * (N - 2)) / 6;
		areas = new double[(int)triNumber];
	}
	
	public double[][] getDots() {
		return dots;
	}
	
	public void setDots(int N, double x, double y) {
		dots[N][0] = x;
		dots[N][1] = y;
	}
	
	public int getTriNumber() {
		return (int)triNumber;
	}
	
	public double[] getAreas() {
		return areas;
	}
	
	// 세 점이 주어졌을 때 삼각형의 넓이
	public void setArea(int i, double[] dot1, double[] dot2, double[] dot3) {
		areas[i] = Math.abs(((dot1[0] * dot2[1]) + (dot2[0] * dot3[1]) + (dot3[0] * dot1[1]))
				- ((dot1[1] * dot2[0]) + (dot2[1] * dot3[0]) + (dot3[1] * dot1[0]))) / 2;
	}
}

public class SWE6742 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			double A = sc.nextInt();
			double B = sc.nextInt();
			Triangles tri = new Triangles(N);
			
			for(int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				tri.setDots(i, x, y);
			}
			
			double[][][] triDots = combination(N, tri);
			
			for(int i = 0; i < tri.getTriNumber(); i++) {
				tri.setArea(i, triDots[i][0], triDots[i][1], triDots[i][2]);
			}
			
			int result = 0;
			
			for(int i = 0; i < tri.getTriNumber(); i++) {
				if(tri.getAreas()[i] >= A / 2 && tri.getAreas()[i] <= B / 2) result++;
			}
			
			System.out.println("#" + testCase + " " + result);
		}
	}
	
	public static double[][][] combination(int N, Triangles tri) {
		int triNumber = tri.getTriNumber();
		double[][][] dots = new double[triNumber][3][2];
		int triIndex = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				for(int k = j + 1; k < N; k++) {
					dots[triIndex][0] = tri.getDots()[i].clone();
					dots[triIndex][1] = tri.getDots()[j].clone();
					dots[triIndex][2] = tri.getDots()[k].clone();
					triIndex++;
				}
			}
		}
		
		return dots;
	}

}
