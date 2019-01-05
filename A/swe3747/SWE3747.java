/*
 * 플레이트가 크기 순으로 피라미드 형태로 쌓여있는 여러 스택이 있다.
 * 해당 스택들을 하나의 스택으로 합치자.
 * 이때 크기 순으로 쌓을 수 있도록 쪼갰다가(split) 합치는(join) 최소 횟수를 구하자. 
 */
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
			
			// 바닥을 가지고 있는 스택의 번호, 바닥 플레이트 번호, 바닥 중 천장 플레이트 번호.
			int[] bottomIndex;
			// 전부 다 쪼개진 스택들을 저장 할 새로운 Platestack 객체 타입 리스트 생성
			List<Platestack> splitPs = new ArrayList<Platestack>();

			int result = 0;
			
			while(ps.size() > 0) {
				bottomIndex = findBottom(ps);
				
				// 이렇게 가져온 번호들로 기존의 스택을 쪼개고
				// 쪼갠 바닥은 새로운 스택 리스트에 집어넣고
				// 남은 윗부분은 기존의 스택 리스트에 기존 스택을 지우고 넣는다.
				// 이 과정을 반복해서 스택을 전부 쪼갠 다음에 순서대로 다시 합친다.
				// 그런데, 이 과정을 언제까지 반복할 것인가. 당연히 더이상 스택을 쪼갤 필요 없을 때 까진데, 그걸 어떻게 확인할 것인가.
				
				if(bottomIndex[2] != 0) result++;
				
				split(bottomIndex, ps, splitPs);
			}
			
			result = join(splitPs, result);
			
			System.out.println("#" + testCase + " " + result);
		}
	}

	// 다 쪼개고 나서 합친다.
	// 쪼갤 경계 찾는 함수, 스플릿 함수, 조인 함수.
	
	// 먼저 다 쪼개기 위해서 스택 별 바닥부터 크기비교 쭉 해서 쪼갤 경계를 찾는다.
	// 하나로 합쳐진 새로운 스택의 바닥부터 부분부분 구해낸다.
	// + 더 이상 쪼갤 필요 없는 경우를 알아낸다.
	// 지름이 가장 큰 플레이트를 가진 스택의 나머지 플레이트 들도 다른 스택의 플레이트들보다 더 크다.
	// 그러면 그 스택은 더 이상 쪼갤 필요 없이 그대로 새로운 스택 리스트에 들어간다.
	public static int[] findBottom(List<Platestack> ps) {
		// 지름이 가장 큰 플레이트의 지름과 번호.
		int bottom = 0;
		int bottomIndex =  -1;
		// 지름이 가장 큰 플레이트를 가지고 있는 스택의 번호.
		int botStackIndex = 0;
		// 바닥이 될 스택의 가장 윗부분 플레이트의 지름과 번호.
		int botTop = 0;
		int botTopIndex = 0;
		int temp = Integer.MAX_VALUE;
		
		// 스택 중 지름이 가장 큰 플레이트를 찾고 해당 스택의 번호 저장.
		for(int i = 0; i < ps.size(); i++) {
			// 현재 스택의 가장 바닥 플레이트 번호.
			int stackBottomIndex = ps.get(i).getStHeight() - 1;
			
			// 제일 밑에 있는 플레이트의 지름이 가장 큰 스택을 찾는다.
			if(bottom <= ps.get(i).getStDiameter()[stackBottomIndex]) {
				bottom = ps.get(i).getStDiameter()[stackBottomIndex];
				
			}
		}
		
		// 지름이 가장 큰 플레이트를 여러 스택들이 동일하게 갖고 있다면
		// 지름이 가장 큰 플레이트 위의 플레이트 지름이 가장 작은 스택을 선택한다.
		// 문제발생. 지름이 가장 큰 플레이트 위에 더 이상 플레이트가 없는 경우를 확인하지 못한다.
		for(int i = 0; i < ps.size(); i++) {
			int stackBottomIndex = ps.get(i).getStHeight() - 1;
			if(ps.get(i).getStDiameter()[stackBottomIndex] < bottom) continue;
			if(bottomIndex == -1) bottomIndex = stackBottomIndex;

			if(ps.get(i).getStDiameter()[0] == bottom) {
				bottomIndex = stackBottomIndex;
				botStackIndex = i;
				break;
			}
			
			if(ps.get(i).getStDiameter()[stackBottomIndex] == bottom) {
				for(int j = ps.get(i).getStHeight() - 1; j >= 0 ; j--) {
					if(ps.get(i).getStDiameter()[j] < bottom) {
						if(temp > ps.get(i).getStDiameter()[j]) {
							temp = ps.get(i).getStDiameter()[j];

							bottomIndex = stackBottomIndex;
							botStackIndex = i;
						}
						break;
					}
				}
			}
		}
		
		// 바닥이 될 스택의 가장 윗부분 플레이트를 찾는다.
		// 지름이 가장 큰 플레이트를 가진 스택의 밑에서부터 다른 스택의 플레이트들과 크기를 비교한다.
		for(int i = ps.get(botStackIndex).getStHeight() - 1; i >= 0; i--) {
			if(i != 0 && ps.get(botStackIndex).getStDiameter()[i - 1] == bottom) continue;
			botTop = ps.get(botStackIndex).getStDiameter()[i];
			if(i != 0 && ps.get(botStackIndex).getStDiameter()[i - 1] == botTop) continue;
			// 같은 게 있는지, 더 큰 게 있는지.
			boolean equalCheck = false;
			boolean biggerCheck = false;
			boolean smallerCheck = false;
			
			for(int j = 0; j < ps.size(); j++) {
				if(j == botStackIndex) continue;
				
				for(int k = ps.get(j).getStHeight() - 1; k >= 0; k--) {
					// 바닥이 될 스택의 현재 플레이트와 지름이 같은 플레이트가 있으면 현재 플레이트를 바닥 스택의 천장 플레이트로 설정
					// 바닥이 될 스택의 현재 플레이트보다 더 큰 지름의 플레이트가 있으면 이전 플레이트를 바닥 스택의 천장 플레이트로 설정
					if(botTop == ps.get(j).getStDiameter()[k]) equalCheck = true;
					else if(botTop < ps.get(j).getStDiameter()[k]) biggerCheck = true;
					else smallerCheck = true;
				}
			}
			
			if(!biggerCheck && equalCheck && smallerCheck) {
				botTopIndex = i;
				break;
			} else if (biggerCheck){
				botTop = ps.get(botStackIndex).getStDiameter()[i + 1];
				botTopIndex = i + 1;
				break;
			} else {
				botTopIndex = i;
			}
		}
		
		// 바닥을 가지고 있는 스택의 번호, 바닥 플레이트의 번호, 바닥의 천장 플레이트의 번호.
		int[] result = {botStackIndex, bottomIndex, botTopIndex};
		
		return result;
	}

	// 쪼갤 스택의 번호와 해당 스택에서 쪼개야 할 플레이트의 바닥과 천장 번호가 있을 때 어떻게 쪼개고 어떤 값을 반환할 것인가.
	// 쪼개어진 스택들만 저장하는 리스트에 새로운 스택을 만들고 쪼개야 할 플레이트의 바닥부터 천장까지 저장한다.
	// 쪼개고 남은 스택의 일부는 초기 스택 리스트에 새롭게 저장하고 쪼개어진 스택은 삭제한다.
	public static void split(int[] bottomIndex, List<Platestack> ps, List<Platestack> splitPs) {
		// 바닥의 번호에서 천장의 번호를 빼서 쪼갤 스택의 높이를 정한다.
		int splitHeight = bottomIndex[1] - bottomIndex[2] + 1;
		
		// 쪼개어진 스택들만 저장하는 리스트에 새로운 스택을 생성한다.
		splitPs.add(new Platestack(splitHeight));
		
		for(int i = 0; i < splitHeight; i++) {
			// 새롭게 생성된 스택에 플레이트 지름 정보 저장
			int splitPlate = ps.get(bottomIndex[0]).getStDiameter()[bottomIndex[1] - i];
			splitPs.get(splitPs.size() - 1).setStDiameter(i, splitPlate);
		}
		
		// 초기 스택 리스트에서 쪼개고 남은 스택을 새롭게 추가한다.
		if(bottomIndex[2] != 0) {
			int remainHeight = ps.get(bottomIndex[0]).getStHeight() - splitHeight;
			ps.add(new Platestack(remainHeight));
			for(int i = 0; i < remainHeight; i++) {
				int remainDiameter = ps.get(bottomIndex[0]).getStDiameter()[i];
				ps.get(ps.size() - 1).setStDiameter(i, remainDiameter);
			}
		}
		// 초기 스택의 리스트에서 쪼개어진 스택을 지운다.
		ps.remove(bottomIndex[0]);
	}
	
	// 다 쪼개어진 스택들이 있는 리스트를 하나의 스택으로 합친다.
	// 리스트에 크기 순으로 정렬되어 있기 때무에 순서대로 쌓기만 하면 된다.
	public static int join(List<Platestack> splitPs, int result) {
		int[] finalStack;
		
		int finStSize = 0;
		for(int i = 0; i < splitPs.size(); i++) {
			for(int j = 0; j < splitPs.get(i).getStHeight(); j++) {
				finStSize++;
			}
			result++;
		}
		
		finalStack = new int[finStSize];
		
		int finStIndex = 0;
		for(int i = 0; i < splitPs.size(); i++) {
			for(int j = 0; j < splitPs.get(i).getStHeight(); j++) {
				finalStack[finStIndex] = splitPs.get(i).getStDiameter()[j];
				finStIndex++;
			}
		}
		
		return result - 1;
	}
	
}
