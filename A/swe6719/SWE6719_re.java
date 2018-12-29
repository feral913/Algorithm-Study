/*
 * 성수가 오늘 들을 수 있는 강좌들의 수준이 주어진다.
 * 이 중 성수가 오늘 들을 수 있는 강좌의 개수가 주어진다.
 * ( 성수의 기존 실력 + 강좌 수준 ) / 2 의 공식에 따라 성수의 실력이 변한다.
 * 들을 수 있는 강좌 수와 강좌 레벨을 고려하여 성수의 최종 실력이 최대값이 되게 하자.
 */

package swe6719;

import java.util.Scanner;

class Sungsoo{
	private float codingLevel;

	// 최초 성수의 프로그래밍 실력은 0
	public Sungsoo() {
		this.codingLevel = 0;
	}
	
	public float getCodingLevel() {
		return this.codingLevel;
	}
	
	// 강좌를 들을 때마다 '(기존 실력 + 강좌 수준) / 2' 만큼 실력 변화
	public void setCodingLevel(int classLevel) {
		codingLevel = (codingLevel + classLevel) / 2;
	}
}

class TodayClass{
	// 모든 강좌의 수, 오늘 들을 수 있는 강좌의 수
	int allClassNum;
	int selectedClassNum;
	// 모든 강좌의 수준 리스트, 오늘 들을 수 있는 강좌의 수준 리스트
	int[] allClass;
	int[] selectedClass;
	
	// 객체 생성 시 강좌 수 먼저 받아오고 수준은 배열 객체 생성만
	public TodayClass(int allClassNum, int selectedClassNum) {
		this.allClassNum = allClassNum;
		this.selectedClassNum = selectedClassNum;
		this.allClass = new int[allClassNum];
		this.selectedClass = new int[selectedClassNum];
	}
	
	// 모든 강좌 수준 저장
	public void setAllClass(int[] allClass) {
		this.allClass = allClass;
	}
	
	public int getSelectedClass(int i) {
		return this.selectedClass[i];
	}
	
	// 내림차순으로 정렬된 모든 강좌 중 오늘 들을 수 있는 강좌 개수만큼 다시 오늘차순으로 저장
	// - 낮은 수준부터 들어야 계산했을 때 최종 결과가 점점 높아지기 때문
	public void setSelectedClass() {
		for(int i = 0; i < selectedClassNum; i++) {
			selectedClass[i] = allClass[selectedClassNum - i - 1];
		}
	}
	
	// 모든 강좌 내림차순 정렬
	// - 강좌가 높은 수업들만 골라듣기 위해서
	public void sortAllClass() {
		for(int i = 0; i < allClassNum; i++) {
			for(int j = i + 1; j < allClassNum; j++) {
				if(this.allClass[i] < this.allClass[j]) {
					int temp = this.allClass[i];
					this.allClass[i] = this.allClass[j];
					this.allClass[j] = temp;
				}
			}
		}
	}
}

public class SWE6719_re {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		// Test case 수
		int T = sc.nextInt();

		for(int testCase = 1; testCase <= T; testCase++) {
			// 오늘의 총 강좌 수
			int N = sc.nextInt();
			// 오늘 들을 수 있는 강좌 수
			int K = sc.nextInt();
			
			Sungsoo ss = new Sungsoo();
			TodayClass tc = new TodayClass(N, K);
			
			int[] allClass = new int[N];
			for(int i = 0; i < N; i++) {
				allClass[i] = sc.nextInt();
			}
			tc.setAllClass(allClass);
			tc.sortAllClass();
			tc.setSelectedClass();
			for(int i = 0; i < K; i++) {
				ss.setCodingLevel(tc.getSelectedClass(i));
			}

			System.out.printf("#%d %.6f\n", testCase, ss.getCodingLevel());
		}
		
		sc.close();
	}

}
