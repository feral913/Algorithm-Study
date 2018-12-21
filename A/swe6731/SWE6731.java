package swe6731;

import java.util.Scanner;

class Reversi{
	private char[][] currentDisks;
	private char[][] nextDisks;
	private int boardSize;
	
	public Reversi(int boardSize) {
		this.boardSize = boardSize;
		currentDisks = new char[boardSize][boardSize];
		nextDisks = new char[boardSize][boardSize];
	}
	
	public char getCurrentDisks(int i, int j) {
		return currentDisks[i][j];
	}
	
	public void setCurrentDisks(char[][] disks) {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				currentDisks[i][j] = disks[i][j];
			}
		}
/*		for(int i = 0; i < boardSize; i++) {
			currentDisks[i] = disks[i].clone();
		}*/
	}
	
	public char[][] getNextDisks() {
		return nextDisks;
	}
	
	public void setDisks(int i, int j) {
		currentDisks[i][j] ='W';
		nextDisks[i][j] = 'W';
	}
		
	public void turnover(int i, int j) {
		for(int k = 0; k < boardSize; k++) {
			for(int l = 0; l < boardSize; l++) {
				if(k == i || j == l) {
					if(currentDisks[k][l] == 'W') nextDisks[k][l] = 'B';
					else nextDisks[k][l] = 'W';
				} else nextDisks[k][l] = currentDisks[k][l];
			}
		}
	}
	
	public boolean compareCG(char[][] goalDisks) {
		boolean isSame = true;
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(nextDisks[i][j] != goalDisks[i][j]) isSame = false;
			}
		}
		
		return isSame;
	}
}

public class SWE6731 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			long startTime = System.currentTimeMillis();

			int N = sc.nextInt();
			int gameCount = 0;
			char[][] goalDisks = new char[N][N];
			Reversi rvs = new Reversi(N);
			boolean isFinish = false;
			
			
			for(int i = 0; i < N; i++) {
				String str = sc.next();
				for(int j = 0; j < N; j++) {
					goalDisks[i][j] = str.charAt(j);
					rvs.setDisks(i, j);
				}
			}
			
			isFinish = rvs.compareCG(goalDisks);
			
			while(!isFinish) {
				gameCount++;
				int circle = gameCount;
				
				isFinish = run(N, rvs, circle, goalDisks);
			}
			
			long endTime = System.currentTimeMillis();
			
			long duration = (endTime - startTime);

			System.out.println("#" + testCase + " " + gameCount + " " + duration);
		}
	}
	
	public static boolean run(int N, Reversi rvs, int circle, char[][] goalDisks) {
		boolean isFinish = false;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				rvs.turnover(i, j);
				if(circle > 1) {
					Reversi trvs = new Reversi(N);
					trvs.setCurrentDisks(rvs.getNextDisks());
					circle--;
					
					isFinish = run(N, trvs, circle, goalDisks);
					
					circle++;
				}
				
				if(!isFinish) {
					isFinish = rvs.compareCG(goalDisks);
				} else break;
			}
			if(isFinish) break;
		}
		
		return isFinish;
	}
	
}
