/*
 * N���� ���� �־�����.
 * �� �� �� ���� �����Ͽ� ������� �ﰢ�� �߿��� ���̰� A/2 �̻�, B/2 ������ ���� ������ ������.
 */

package swe6742;

import java.util.Scanner;

// 1. �ﰢ�� ���� : �غ��� ���̸� ���� �ﰢ���� ���̸� ���ϴ� ���� ������ �� �˾Ҵµ�
// �� ���� �־����� �� �ﰢ���� ���̸� ���ϴ� ������ �־ ���� ���ϴ� ���� �����ϴ�.
// 2. �� �� �̱� : �׷��ٸ� ������ �������� ���� �־����� �� �� ���� ���� �̾Ƴ� �� �ִ� ��� ���. ������ �����ϴ� ��.
// ���� ��� �־����� �� �߿��� �̾ƾ� �� ���� ������ �����̴ϱ�. �׳� 3�� for������ �����.

// ��������������.....��Ÿ�� ������ ���.
// ���� ũ��� ����Ѱ� ������..
// �迭 ũ�⵵ ���� �ִ� �Է� ������ 2õ���ϱ�..���� ������ �迭 ũ�⸦ Ȯ���غô�.
// int Ÿ�� �ִ� ����ŭ�̰� ���࿡ 2000���� ���� �ԷµǾ��� �� ��������� �ﰢ���� ���� 2000*1999*1998/6���� int ������ �ȳѴ´�.
// �ٵ� ������ ������ ���� ���ϴ� �������� ������ �Ѿ������ N(�� ��)�� triNumber(�ﰢ�� ��) ������ Ÿ�Ը� long���� �ٲ��ְ� int�� Ÿ��ĳ��Ʈ�ؼ� �����.
// ������ �׷��� ��Ÿ�� ����..
class Triangles{
	private double[][] dots;
	private long triNumber;
	private double[] areas;
	
	public Triangles(long N) {
		dots = new double[(int)N][2];
		
		// �ﰢ���� ����. ���� C(N, 3).
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
	
	// �� ���� �־����� �� �ﰢ���� ����
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
