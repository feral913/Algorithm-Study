package algorithm.combination;

public class Combination {
	public static void main(String[] args) {
		// N 개의 정수로 만들 수 있는 모든 조합.
		int N = 5;
		int[] arr = new int[N];
		for(int i = 0; i <= 5; i++) {
			combination1(arr, 0, 5, i, 0);
		}

		// 주어진 배열로 만들 수 있는 모든 조합.
		int[] arr2 = {1, 8, 3, 4, 7};
		for(int i = 0; i <= 5; i++) {
			combination2(arr, 0, arr2, 0, i);
		}
	}
	
	// 0 부터 n 까지의 정수 중에서 r 개의 정수를 뽑는 조합.
	// arr : 조합을 저장할 배열. index : arr의 크기 혹은 다음 값을 저장할 번호. n : 조합을 구하고자 하는 정수 집합의 크기. r : n 개의 집합에서 뽑으려는 조합의 크기. target : 정수 집합에서 조합을 만들기 위해 확인하는 대상.
	public static void combination1(int[] arr, int index, int n, int r, int target) {
		// r = 0이면 조합의 크기 만큼 다 뽑았다.
		if(r == 0) {
			for(int i = 0; i < index; i++) {
				System.out.print(arr[i]);
			}
			System.out.println("");
		}
		// 뽑고자 하는 크기만큼 다 안뽑았지만 정수 집합의 끝까지 다 확인해서 더이상 조합을 만들 수 없다.
		else if (target == n) return;
		// 정수 집합에서 현재 확인하는 정수를 조합의 배열에 저장하고 다음 정수를 확인하러 가거나,
		// 현재 확인하는 정수를 저장하지 않고(코드상으로는 저장하지만 나중에 덮어쓴다.) 다음 정수를 확인하러 간다.
		else {
			arr[index] = target;
			combination1(arr, index + 1, n, r - 1, target + 1);
			combination1(arr, index, n, r, target + 1);
		}
	}

	// 정수 배열 arr2에서 r개의 정수를 뽑아서 만들 수 있는 조합.
	// arr : 조합을 저장할 배열. index : arr의 크기 혹은 다음 값을 저장할 번호. arr2 : 조합을 구하고자 하는 정수 배열. target : 정수 배열 arr2에서 조합을 만들기 위해 확인하는 대상. r : 정수 배열 arr2에서 뽑으려는 조합의 크기.  
	public static void combination2(int[] arr, int index, int[] arr2, int target, int r) {
		if(r == 0) {
			for(int i = 0; i < index; i++) {
				System.out.print(arr[i]);
			}
			System.out.println("");
		}
		else if (target == arr.length) return;
		else {
			arr[index] = arr2[target];
			combination2(arr, index + 1, arr2, target + 1, r - 1);
			combination2(arr, index, arr2, target + 1, r);
		}
	}
}
