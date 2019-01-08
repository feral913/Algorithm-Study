package SWE.swe6719;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SWE6719 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for(int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[] classList = new int[N];
			int[] classList2 = new int[K];
			float pPower = 0;
			
			for(int i = 0; i < N; i++) {
				classList[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					if(classList[i] < classList[j]) {
						int temp = classList[i];
						classList[i] = classList[j];
						classList[j] = temp;
					}
				}
			}
			
			for(int i = 0; i < K; i++) {
				classList2[i] = classList[i];
			}
			
			for(int i = K - 1; i >= 0; i--) {
				pPower = (pPower + classList2[i]) / 2;
			}
			
			//System.out.println("#" + testCase + " " + pPower);
			System.out.printf("#%d %.6f", testCase, pPower);
		}
		
		sc.close();
	}

}
