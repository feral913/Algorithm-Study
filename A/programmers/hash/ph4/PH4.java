/*
 * N개의 노래가 있다.
 * 각 노래의 장르가 담긴 문자열 배열과 각 노래가 재생된 횟수가 담긴 정수 배열이 주어진다.
 * 장르 별로 가장 많이 재생된 노래를 두 곡씩 모아 베스트 앨범을 만든다.
 * 노래를 수록하는 기준은 다음과 같다.
 * 1. 속한 노래가 많이 재생된 장르를 먼저 수록한다.
 * 2. 장르 내에서 많이 재생된 노래를 먼저 수록한다.
 * 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 번호(배열의 인덱스)가 낮은 노래를 먼저 수록한다.
 * 제한사항.
 * - 곡 수는 1 이상 10,000 이하이다.
 * - 장르 종류는 100개 미만이다.
 * - 장르에 속한 곡이 하나라면, 하나의 곡만 선택한다.
 * - 모든 장르는 재생된 횟수가 다르다.
 */
package programmers.hash.ph4;

import java.util.Scanner;

class History{
	private String[] genres;
	private int[] plays;
	
	public History(int N) {
		genres = new String[N];
		plays = new int[N];
	}
	
	public String[] getGenres() {
		return genres;
	}
	
	public void setGenres(int i, String genre) {
		genres[i] = genre;
	}
	
	public int[] getPlays() {
		return plays;
	}
	
	public void setPlays(int i, int play) {
		plays[i] = play; 
	}
}

class Solution{
	public int[] Solution(String[] genres, int[] plays) {
		int[] answer = {};
		return answer;
	}
}

public class PH4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int testCase = 0; testCase < T; testCase++) {
			int N = sc.nextInt();
			History his = new History(N);
			
			for(int i = 0; i < N; i++) {
				his.setGenres(i, sc.next());
			}
			
			for(int i = 0; i < N; i++) {
				his.setPlays(i, sc.nextInt());
			}
			
			Solution sol = new Solution();
			System.out.println(sol.Solution(his.getGenres(), his.getPlays()));
		}
	}
}
