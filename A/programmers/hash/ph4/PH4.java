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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
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

// hashmap에 장르 별 총 재생 수를 저장한다.
// 총 재생 수를 기준으로 장르를 정렬한다.
// 이때 hashmap을 ArrayList에 entry set 형태로 저장하고 entry set의 value값(장르 별 총 재생 수)을 기준으로 정렬한다.
// 정렬 된 hashmap에서 제일 마지막(총 재생 수가 가장 많은) 장르를 대상으로 해당 장르와 동일한 장르의 곡의 번호와 재생 수를 새로운 hashmap에 저장한다.
// 검사하는 장르의 곡들을 재생 수를 기준으로 정렬한다.
// 정렬 된 곡들의 제일 마지막과 그 다음 곡의 번호를 베스트 앨범에 추가한다.
class Solution{
	public int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		ArrayList<Integer> ansArr = new ArrayList<>();
		
		// 장르 별 총 재생 수를 저장할 hashmap.
		HashMap<String, Integer> gpHM = new HashMap<>();
		for(int i = 0; i < genres.length; i++) {
			if(!gpHM.containsKey(genres[i])) {
				gpHM.put(genres[i], plays[i]);
			} else {
				gpHM.put(genres[i], gpHM.get(genres[i]) + plays[i]);
			}
		}
		
		// 장르 별 총 재생 수를 저장한 hashmap을 entry 형태로 arraylist에 저장한다.
		ArrayList<Entry<String, Integer>> gpList = new ArrayList<>(gpHM.entrySet());
		// entry의 value를 기준으로 arraylist를 정렬한다. 오름차순으로 정렬된다.
		gpList.sort(Entry.comparingByValue());
		// arraylist의 마지막 원소(총 재생 수가 가장 큰 장르)부터 확인한다.
		for(int i = gpList.size() - 1; i >= 0; i--) {
			// 현재 검사하는 장르에서 곡 별 재생 수를 저장할 hashmap.
			HashMap<Integer, Integer> spHM = new HashMap<>();
			for(int j = 0; j < genres.length; j++) {
				// 현재 검사하는 장르가 맞으면 hashmap에 곡의 번호와 재생 수를 저장.
				if(genres[j].equals(gpList.get(i).getKey()))
					spHM.put(j, plays[j]);
			}
			
			// 곡 별 재생 수를 저장한 hashmap을 entry 형태로 arraylist에 저장한다.
			ArrayList<Entry<Integer, Integer>> spList = new ArrayList<>(spHM.entrySet());
			// entry의 value를 기준으로 arraylist를 정렬한다. 오름차순으로 정렬된다.
			// 이때 entry의 value(노래 별 재생 수)가 같으면 key(노래 번호)를 기준으로 오름차순 정렬된다.
			// 그렇기 때문에 재생 수가 가장 많은 노래가 여러 곡 있을 때(재생 수가 다 같을 때) 번호가 더 작은 곡을 선택하는 방법을 고려해야한다. 
			spList.sort(Entry.comparingByValue());
			
			// 재생 수가 가장 많은 두 곡을 골라내는 작업을 수행한다.
			for(int j = 0; j < 2; j++) {
				// 곡이 한 곡 밖에 없을 경우 해당 곡을 베스트 앨범에 추가하고 반복문을 종료한다.
				if(spList.size() == 1) {
					ansArr.add(spList.get(0).getKey());
					break;
				}
				// 리스트의 제일 앞에서 부터(재생 수가 가장 작은 곡 부터) 검사한다.
				for(int k = 0; k < spList.size(); k++) {
					// 리스트에서 가장 많은 재생수와 같으면(재생 수가 가장 많으면서 번호가 가장 작으면(빠르면)) 해당 곡의 key(곡 번호)를 베스트앨범에 추가한다.
					// 베스트앨범에 추가한 곡은 리스트에서 삭제한다.
					// 해당 작업을 두번 반복한다.
					int topPlays = spList.get(spList.size() - 1).getValue();
					if(spList.get(k).getValue().equals(topPlays)) {
						ansArr.add(spList.get(k).getKey());
						spList.remove(k);
						break;
					}
				}
			}
		}
		
		answer = new int[ansArr.size()];
		for(int i = 0; i < ansArr.size(); i++) {
			answer[i] = ansArr.get(i);
		}
		
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
			
			int[] answer = sol.solution(his.getGenres(), his.getPlays());
			for(int i = 0; i < answer.length; i++) {
				System.out.printf("%d", answer[i]);
			}
			System.out.println("");
		}
	}
}
