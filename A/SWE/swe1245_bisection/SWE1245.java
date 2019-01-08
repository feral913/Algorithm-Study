/*
 * x축에 N개의 자성체가 있다.
 * 각 자성체의 위치와 질량이 주어진다.
 * 이때 임의의 위치에 어떤 물체가 존재하면 아래 공식에 따라 자성체들로부터 인력이 작용한다.
 * F = G * m1 * m2 / (d * d)
 * 물체에 작용하는 양쪽의 힘이 같은 지점을 찾아라.
 * N개의 자성체가 있다면 N-1개의 균형점이 존재한다.
 */
package SWE.swe1245_bisection;

import java.util.Scanner;

// 이분법으로 풀면된다.
// 두 자성체의 가운데 지점을 물체의 위치로 잡고 인력을 확인하고, 인력이 더 큰 쪽으로 다시 물체와 자성체의 가운데 지점을 다시 물체의 위치로 잡는다.
// 이때 문제에서 언급한 것처럼 N개의 자성체가 있을 때, 연속되는 각 자성체의 사이사이마다 균형점이 존재한다.
// 즉 N - 1개의 균형점이 존재한다.
// 그러므로 연속되는 자성체 사이별로 균형점을 찾으면 된다.

// 자성체의 위치와 질량을 갖고 있는 객체.
class MagSub{
	// x : 자성체 위치. mass : 자성체 질량.
	private int[] x;
	private int[] mass;
	
	public MagSub(int subNum) {
		x = new int[subNum];
		mass = new int[subNum];
	}
	
	public int[] getX() {
		return x;
	}
	
	public int getX(int i) {
		return x[i];
	}
	
	public void setX(int i, int x) {
		this.x[i] = x;
	}
	
	public int getMass(int i) {
		return mass[i];
	}
	
	public void setMass(int i, int mass) {
		this.mass[i] = mass;
	}
}

public class SWE1245 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			
			MagSub ms = new MagSub(N);
			
			for(int i = 0; i < N; i++) {
				int x = sc.nextInt();
				ms.setX(i, x);
			}
			for(int i = 0; i < N; i++) {
				int mass = sc.nextInt();
				ms.setMass(i, mass);
			}
			
			double[] result = new double[N - 1];
			
			for(int i = 0; i < N - 1; i++) {
				result[i] = solve(ms, ms.getX(i), ms.getX(i + 1));
			}
			
			System.out.printf("#%d", testCase);
			for(int i = 0; i < N - 1; i++) {
				System.out.printf(" %.10f", result[i]);
			}
			System.out.println("");
		}
	}
	
	// 자성체와 물체 사이의 거리 d, 자성체의 질량 m1, 물체의 질량 m2 일 때
	// 자성체로부터 물체에 작용하는 인력 F = (G * m1 * m2) / (d * d), G는 양의 상수
	// 이때 상수 G1과 자성체의 질량 m1은 모두 동일하기 때문에 무시한다.
	// 결국 각 자성체로부터 물체에 작용하는 인력을 m2 / (d * d) 로 계산하고 비교한다.
	
	// 왼쪽 점과 오른쪽 점의 가운데 지점에 물체를 놓기 위해 왼쪽 점과 오른쪽 점의 위치를 받아온다.
	// 프로그램을 돌려보니 왼쪽 인력과 오른쪽 인력이 완전히 같아지지 않고 일정 값이 반복되는 구간이 있다.
	// 데이터 타입 크기 제한떄문에 그런거 같은데 그 값이 답이다. 함수 초반에 확인하고 결과를 반환하자.
	// 
	public static double solve(MagSub ms, double l, double r) {
		// 물체의 위치
		double subX = (l + r) / 2;
		
		// 계산이 더 이상 불가능한 반복되는 값에서 결과 반환.
		if(l == subX || r == subX) return subX;
		
		double lForce = 0;
		double rForce = 0;
		// 물체를 기준으로 왼쪽 자성체들에 의한 인력과 오른쪽 자성체 들에 의한 인력을 구한다.
		for(int i = 0; i < ms.getX().length; i++) {
			double dist = (double)ms.getX(i) - subX;
			double attForce = (double)ms.getMass(i) / (dist * dist);
			
			if(ms.getX(i) < subX) lForce += attForce;
			else rForce += attForce;	
		}
		// 오른쪽 인력이 더 크면 물체를 왼쪽으로 옮긴다.
		// 즉, 기존의 왼쪽 자성체와 물체의 이전 위치의 가운데 지점에 물체를 놓는다.
		// 왼쪽 인력이 더 크면 물체를 오른쪽으로 옮긴다.
		if(lForce < rForce) {
			subX = solve(ms, l, subX);
		} else if(lForce > rForce) {
			subX = solve(ms, subX, r);
		}
		
		return subX;
	}

}
