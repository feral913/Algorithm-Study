package swe6731;

import java.util.Scanner;

class Reversi{
	private char[][] tempDisks;
	private char[][] currentDisks;
	private char[][] goalDisks;
	private int boardSize;
	private boolean isFinish;
	
	public Reversi(int boardSize) {
		this.boardSize = boardSize;
		this.tempDisks = new char[boardSize][boardSize];
		this.currentDisks = new char[boardSize][boardSize];
		this.goalDisks = new char[boardSize][boardSize];
	}
	
	public char getCurrentDisks(int i, int j) {
		return currentDisks[i][j];
	}
	
	public char getGoalDisks(int i, int j) {
		return goalDisks[i][j];
	}
	
	public void setGoalDisks(int i, int j, char goal) {
		goalDisks[i][j] = goal;
		currentDisks[i][j] ='W';
	}
		
	public void turnover(int i, int j) {
		for(int k = 0; k < boardSize; k++) {
			tempDisks[k] = currentDisks[k].clone();
		}
		
		for(int k = 0; k < boardSize; k++) {
			if(currentDisks[i][k] == 'W') currentDisks[i][k] = 'B';
			else currentDisks[i][k] = 'W';
		}
		
		for(int k = 0; k < boardSize; k++) {
			if(k != i) {
				if(currentDisks[k][j] == 'W') currentDisks[k][j] = 'B';
				else currentDisks[k][j] = 'W';
			}
		}
	}
	
	public void rollbackDisks()	{
		for(int i = 0; i < boardSize; i++) {
			currentDisks[i] = tempDisks[i].clone();
		}
	}
	
	public boolean compareCG() {
		isFinish = true;
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(currentDisks[i][j] != goalDisks[i][j]) isFinish = false;
			}
		}
		
		return isFinish;
	}
}

public class SWE6731 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int gameCount = 0;
			int N = sc.nextInt();
			Reversi rvs = new Reversi(N);
			
			for(int i = 0; i < N; i++) {
				String str = sc.next();
				for(int j = 0; j < N; j++) {
					rvs.setGoalDisks(i, j, str.charAt(j));
				}
			}
			
			while(!rvs.compareCG()) {
				for(int x = 0; x < N; x++) {
					for(int y = 0; y < N; y++){
						for(int i = 0; i < N; i++) {
							for(int j = 0; j < N; j++) {
								rvs.turnover(i, j);
								if(rvs.compareCG()) break;
								rvs.rollbackDisks();
							}
							if(rvs.compareCG()) break;
						}
						if(rvs.compareCG()) break;
						//if(x != N-1 && y != N - 1) rvs.rollbackDisks();
						rvs.turnover(x, y);
					}
					if(rvs.compareCG()) break;
				}
				
				gameCount++;
			}
			
			
			System.out.println("#" + testCase + " " + gameCount);
		}
	}

}
