/*
 * N * N 크기의 오델로 게임판이 있다.
 * 각 칸에는 한쪽면은 흰색, 반대쪽 면은 검은색인 원판이 놓여있다.
 * 최초의 게임판은 모든 원판이 흰샌면으로 되어있다.
 * i, j를 선택하면 i번째 행과 j번째 열에 해당하는 모든 원판이 뒤집어진다.
 * 게임판의 최종 상태가 주어질 때, 최초의 게임판에서 몇회가 지났을 때 최종 상태가 될 수 있는지 그 최소값을 구하자.
 */
package SWE.swe6731;

import java.util.ArrayList;
import java.util.Scanner;

// 
class Reversi{
	private int boardSize;
	private char[][] currentDisks;
	private char[][] nextDisks;
	private ArrayList<Character[][]> buff;
	
	public Reversi(int boardSize) {
		this.boardSize = boardSize;
		currentDisks = new char[boardSize][boardSize];
		nextDisks = new char[boardSize][boardSize];
		buff = new ArrayList<Character[][]>();
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	
	public char[][] getCurrentDisks() {
		return currentDisks;
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
	
	public ArrayList<Character[][]> getBuff(){
		return buff;
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
	
	public void saveBuffer(char[][] disks) {
		Character[][] d = new Character[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j ++) {
				d[i][j] = new Character(disks[i][j]);
			}
		}
		
		buff.add(d);
	}
	
	public boolean compareInBuffer(char[][] disks) {
		boolean isInBuffer = false;
		
		for(int k = 0; k < buff.size(); k++) {
			isInBuffer = true;
			for(int i = 0; i < boardSize; i++) {
				for(int j = 0; j < boardSize; j++) {
					if(buff.get(k)[i][j].charValue() != disks[i][j]) isInBuffer = false;
				}
			}
		}

		return isInBuffer;
	}
	
	public void printDisks(char[][] disks) {
		for(int i = 0; i < boardSize; i++) {
			System.out.println(disks[i].clone());
		}
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
				
				isFinish = solve(rvs, circle, goalDisks, 100, 100);
			}
			
			long endTime = System.currentTimeMillis();
			
			long duration = (endTime - startTime);

			System.out.println("#" + testCase + " " + gameCount + " " + duration);
		}
	}
	
	public static boolean solve(Reversi rvs, int circle, char[][] goalDisks, int k, int l) {
		int N = rvs.getBoardSize();
		
		if(rvs.compareCG(goalDisks)) return true;
		
		boolean isFinish = false;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(k == i && l == j) continue;
				
				rvs.turnover(i, j);
				
				if(rvs.compareInBuffer(rvs.getNextDisks())) continue;
				else rvs.saveBuffer(rvs.getNextDisks());
				
				if(circle > 1) {
					Reversi trvs = new Reversi(N);
					trvs.setCurrentDisks(rvs.getNextDisks());
					isFinish = solve(trvs, circle - 1, goalDisks, i, j);
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
