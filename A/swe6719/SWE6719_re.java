package swe6719;

import java.util.Scanner;

class Sungsoo{
	private float codingLevel;

	// ���� ������ ���α׷��� �Ƿ��� 0
	public Sungsoo() {
		this.codingLevel = 0;
	}
	
	public float getCodingLevel() {
		return this.codingLevel;
	}
	
	// ���¸� ���� ������ '(���� �Ƿ� + ���� ����) / 2' ��ŭ �Ƿ� ��ȭ
	public void setCodingLevel(int classLevel) {
		codingLevel = (codingLevel + classLevel) / 2;
	}
}

class TodayClass{
	// ��� ������ ��, ���� ���� �� �ִ� ������ ��
	int allClassNum;
	int selectedClassNum;
	// ��� ������ ���� ����Ʈ, ���� ���� �� �ִ� ������ ���� ����Ʈ
	int[] allClass;
	int[] selectedClass;
	
	// ��ü ���� �� ���� �� ���� �޾ƿ��� ������ �迭 ��ü ������
	public TodayClass(int allClassNum, int selectedClassNum) {
		this.allClassNum = allClassNum;
		this.selectedClassNum = selectedClassNum;
		this.allClass = new int[allClassNum];
		this.selectedClass = new int[selectedClassNum];
	}
	
	// ��� ���� ���� ����
	public void setAllClass(int[] allClass) {
		this.allClass = allClass;
	}
	
	public int getSelectedClass(int i) {
		return this.selectedClass[i];
	}
	
	// ������������ ���ĵ� ��� ���� �� ���� ���� �� �ִ� ���� ������ŭ �ٽ� ������������ ����
	// - ���� ���غ��� ���� ������� �� ���� ����� ���� �������� ����
	public void setSelectedClass() {
		for(int i = 0; i < selectedClassNum; i++) {
			selectedClass[i] = allClass[selectedClassNum - i - 1];
		}
	}
	
	// ��� ���� �������� ����
	// - ���°� ���� �����鸸 ����� ���ؼ�
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
		
		// Test case ��
		int T = sc.nextInt();

		for(int testCase = 1; testCase <= T; testCase++) {
			// ������ �� ���� ��
			int N = sc.nextInt();
			// ���� ���� �� �ִ� ���� ��
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
