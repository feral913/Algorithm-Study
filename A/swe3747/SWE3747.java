package swe3747;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 고민. 스택별로 인스턴스를 각자 생성할 것인가. 하나의 인스턴스에 모든 스택의 정보를 저장할 것인가.
// 하나의 인스턴스에 모든 스택의 정보를 저장하려니 스택별 크기도 다르고 정보도 별도로 입려되니 깔끔하지 못하다.
// 스택별로 인스턴스를 생성한다면 어떻게 생성할 것인가.
// 방법. 리스트를 Platestack 객체 타입으로 생성한다. 
class Platestack{
	int stHeight;
	int[] stDiameter;
	
	public Platestack(int stHeight) {
		this.stHeight = stHeight;
		stDiameter = new int [stHeight];
	}
	
	public int getStHeight() {
		return stHeight;
	}
	
	public int[] getStDiameter() {
		return stDiameter;
	}
	
	public void setStDiameter(int i, int stDiameter) {
		this.stDiameter[i] = stDiameter;
	}
}

public class SWE3747 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 테스트 케이스 수 입력
		int T = sc.nextInt();
		
		// 테스트 케이스 수행
		for(int testCase = 1; testCase <= T; testCase++) {
			// 스택 수 입력
			int N = sc.nextInt();
			
			// Platestack 객체 타입의 리스트 생성
			List<Platestack> ps = new ArrayList<Platestack>();
			for(int i = 0; i < N; i++) {
				// 스택 높이 입력
				int H = sc.nextInt();
				// 인스턴스 생성 및 리스트에 추가
				ps.add(new Platestack(H));
				
				for(int j = 0; j < H; j++) {
					// 플레이트 지름 입력
					int D = sc.nextInt();
					ps.get(i).setStDiameter(j, D);
				}
			}
			
			// 바닥을 가지고 있는 스택의 번호, 바닥 플레이트 번호, 바닥 중 가장 위 플레이트 번호.
			int[] bottomIndex;
			bottomIndex = findBottom(ps);
			
			// 이렇게 가져온 번호들로 기존의 스택을 쪼개고
			// 쪼갠 바닥은 새로운 스택 리스트에 집어넣고
			// 남은 윗부분은 기존의 스택 리스트에 기존 스택을 지우고 넣는다.
			// 이 과정을 반복해서 스택을 전부 쪼갠 다음에 순서대로 다시 합친다.
			
			// 전부 다 쪼개진 스택들을 저장 할 새로운 Platestack 객체 타입 리스트 생성
			List<Platestack> splitPs = new ArrayList<Platestack>();
			
		}
	}

	// 다 쪼개고 나서 합친다.
	// 쪼갤 경계 찾는 함수, 스플릿 함수, 조인 함수.
	
	// 먼저 다 쪼개기 위해서 스택 별 바닥부터 크기비교 쭉 해서 쪼갤 경계를 찾는다.
	// 하나로 합쳐진 새로운 스택의 바닥부터 부분부분 구해낸다.
	public static int[] findBottom(List<Platestack> ps) {
		// 지름이 가장 큰 플레이트의 지름과 인덱스.
		int bottom = 0;
		int bottomIndex = 0;
		// 지름이 가장 큰 플레이트를 가지고 있는 스택의 인덱스.
		int botStackIndex = 0;
		// 바닥이 될 스택의 가장 윗부분 플레이트의 지름과 인덱스.
		int botTop = 0;
		int botTopIndex = 0;
		
		// 스택 중 지름이 가장 큰 플레이트를 찾고 해당 스택의 인덱스 저장.
		for(int i = 0; i < ps.size(); i++) {
			// 현재 스택의 가장 바닥 플레이트 번호.
			int stackBottomIndex = ps.get(i).getStHeight() - 1;
			
			if(bottom < ps.get(i).getStDiameter()[stackBottomIndex]) {
				bottom = ps.get(i).getStDiameter()[stackBottomIndex];
				botStackIndex = i;
				bottomIndex = stackBottomIndex;
			}
		}
		
		// 바닥이 될 스택의 가장 윗부분 플레이트를 찾는다.
		// 지름이 가장 큰 플레이트를 가진 스택의 밑에서부터 다른 스택의 플레이트들과 크기를 비교한다.
		for(int i = ps.get(botStackIndex).getStHeight() - 1; i >= 0; i--) {
			botTop = ps.get(botStackIndex).getStDiameter()[i];
			// 같은 게 있는지, 더 큰 게 있는지.
			boolean equalCheck = false;
			boolean biggerCheck = false;
			
			for(int j = 0; j < ps.size(); j++) {
				if(j == botStackIndex) continue;
				
				for(int k = ps.get(j).getStHeight() - 1; k >= 0; k--) {
					// 바닥이 될 스택의 현재 플레이트와 지름이 같은 플레이트가 있으면 현재 플레이트를 바닥 스택의 가장 위 플레이트로 설정
					// 바닥이 될 스택의 현재 플레이트보다 더 큰 지름의 플레이트가 있으면 이전 플레이트를 바닥 스택의 가장 위 플레이트로 설정
					if(botTop == ps.get(j).getStDiameter()[k]) equalCheck = true;
					else if(botTop < ps.get(j).getStDiameter()[k]) biggerCheck = true;
				}
			}
			
			if(equalCheck) {
				botTopIndex = i;
				break;
			} else if (biggerCheck){
				botTop = ps.get(botStackIndex).getStDiameter()[i + 1];
				botTopIndex = i + 1;
				break;
			}
		}
		
		// 바닥을 가지고 있는 스택의 번호, 바닥 플레이트의 번호, 바닥의 가장 위 플레이트의 번호.
		int[] result = {botStackIndex, bottomIndex, botTopIndex};
		
		return result;
	}

	public static void split() {
		
	}
	
	public static void join() {
		
	}
	
}
