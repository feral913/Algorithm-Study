/*
 * N개의 버스 노선이 있다.
 * P개의 버스 정류장이 있다.
 * i번째 노선은 Ai보다 크고 Bi보다 작은 버스 정류장만 지난다.
 * 각 버스정류장에 몇개의 버스 노선이 지나가는지 구하자.
 */

package SWE.swe6485;

import java.util.Scanner;

// 일반적으로 생각했을 때 버스 노선이랑 정류장은 별개의 소속이니까 두개를 나눠서 객체를 만들어줬다.
// 노선의 시작과 끝 범위 안에 정류장 리스트의 정류장이 포함되어 있으면 카운트 해주는 방식으로 하면 되겠다.

// Ai와 Bi 정보를 갖고 있는 버스 노선 객체.
class BusRoute{
	private int[][] route;
	
	public BusRoute(int N) {
		route = new int[N][2];
	}
	
	// 버스 노선 변수(2차원 배열) 전체 전체 반환.
	public int[][] getRoute() {
		return route;
	}
	
	// 각 버스 노선에 대한 시작 정류장 혹은 끝 정류장 반환.
	public int getRoute(int i, int j) {
		return route[i][j];
	}
	
	public void setRoute(int i, int from, int to) {
		route[i][0] = from;
		route[i][1] = to;
	}
}

// 버스 정류장 번호와 해당 정류장을 지나는 노선의 수를 갖는 버스 정류장 객체.
class BusStop{
	private int[] stop;
	private int[] routeCnt;
	
	public BusStop(int P) {
		stop = new int[P];
		routeCnt = new int[P];
	}
	
	// 버스 정류장 변수 전체 반환.
	public int[] getStop() {
		return stop;
	}
	
	// i번째 버스 정류장 번호 반환.
	public int getStop(int i) {
		return stop[i];
	}
	
	public void setStop(int i, int stop) {
		this.stop[i] = stop;
	}
	
	public int getRouteCnt(int i) {
		return routeCnt[i];
	}
	
	// 해당 버스 정류장을 지나는 노선 수 추가.
	public void setRouteCnt(int i) {
		routeCnt[i]++;
	}
}

public class SWE6485 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			// 버스 노선 인스턴스 생성 및 값 입력.
			int N = sc.nextInt();
			BusRoute br = new BusRoute(N);
			for(int i = 0; i < N; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				br.setRoute(i, from, to);
			}
			
			// 버스 정류장 인스턴스 생성 및 값 입력.
			int P = sc.nextInt();
			BusStop bs = new BusStop(P);
			for(int i = 0; i < P; i++) {
				int stop = sc.nextInt();
				bs.setStop(i, stop);
			}
			
			solve(br, bs);
			
			System.out.printf("#%d", testCase);
			for(int i = 0; i < P; i++) {
				System.out.printf(" %d", bs.getRouteCnt(i));
			}
			System.out.println("");
		}
	}
	
	public static void solve(BusRoute br, BusStop bs) {
		// 각 버스 노선별로 작업을 체크한다.
		// 버스 노선 수만큼 루프.
		for(int i = 0; i < br.getRoute().length; i++) {
			// 해당 노선의 시작 정류장부터 끝 정류장까지 루프.
			for(int j = br.getRoute(i, 0); j <= br.getRoute(i, 1); j++) {
				// 노선이 지나가는 정류장이 현재 버스 정류장이면 routeCnt++;
				for(int k = 0; k < bs.getStop().length; k++) {
					if(bs.getStop(k) == j) bs.setRouteCnt(k);
				}
			}
		}
	}

}
