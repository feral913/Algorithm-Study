#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

//def
typedef vector < char > vtype;
typedef vector < vtype > vvtype;
typedef struct Input_set {
		Input_set(int _count, vtype _num) {count = _count, num = _num;}
		int count;
		vtype num;
}Input;
//var
//func
Input file_input();
void file_output(vvtype _output);
vtype solve_algorithm(Input _input);
int main()
{
	vvtype output;
	int count = 0;
	cin >> count;
	//
	for (int i = 0; i < count; ++i) {
		Input input = file_input();
		vtype answer = solve_algorithm(input);
		output.push_back(answer);
	}
	file_output(output);
	return 0;
}
//입력(read input)
Input file_input()
{
		vtype input; string num;
		//input(number)
		cin >> num;
		for (uint i = 0; i < num.size(); ++i) { input.push_back(num[i]);}

		//input(sorting count)
		int count;
		cin >> count;

		return Input(count, input);
}
//결과 출력(print output)
void file_output(vvtype _output)
{
		unsigned index = 0;
		for (vvtype::iterator it = _output.begin(); it != _output.end(); ++it) {
				cout << '#' << ++index << ' ';
				for (vtype::iterator num = (*it).begin(); num != (*it).end(); ++num)
						cout << *num;
				cout << endl;
		}
		return ;
}
//스와프(Swap a with b)
void swap(char* a, char* b)
{
		char temp = *a;
		*b = *a;
		*a = temp;
}
//순열에서 최대값인 원소 찾기(Find the max from '_src')
int findMax(vtype _src)
{
		make_heap(_src.begin(), _src.end());
		return _src.front();
}
//최대값이 중복순열인지 확인(Check if the duplicate number is '_target')
int findDuplicated(vtype& _from, char _target)
{
		int base = 0;
		for (uint next = base + 1; next < _from.size(); ++next) {
				if (_from[ base ] == _from[ next ] && _from[ base ] == _target) {
						return _from[ base ];
				}
				base = next;
		}
		return -1;
}
/* solve_algorithm
	# 과정
	#1 맨 앞자리수부터 가장 큰 값을 가져옴.
	#2 예외 처리 (중복순열, count 홀짝여부, 현재 위치와 남은 카운트 상태)
*/
vtype solve_algorithm(Input _rule)
{
		const char MAX_VAL = findMax(_rule.num);//순열에서 최대값 원소(maximum value in a permutation)
		const char DUP_VAL = findDuplicated(_rule.num, MAX_VAL);//순열에서 최대값이 중복인지 판단(redundant value in a permutation)
		const uint MAX_CUR = _rule.num.size() - 2;//비교시 최대로 접근가능한 인덱스(maximum index during comparison)
		uint cur = 0;
		while(_rule.count > 0) {
				uint max = cur;
				//1. 숫자열에서 가장 큰 값이 있는 곳의 인덱스를 찾아냄(Find the location with max value after 'cur')
				for (uint i = cur + 1;
						i < _rule.num.size(); ++i) {
						if (_rule.num[max] <= _rule.num[i]) {//최대값이면서 제일 뒤의 녀석을 max(Set the index if it is maximum and largest index)
								max = i;
						}
				}

				//2. 정렬 작업 (Sort)
				//2-1. cur 이후에 최대값이 존재한다면 swap(Swap the elements if there is the biggest element after 'cur')
				if (max != cur) {//"swap"
						swap(_rule.num[cur], _rule.num[max]);
						cur++;
						_rule.count--;
				}
				//2-2. cur 이후에 최대값이 없다면 다음 cur로 이동(Increase 'cur' if there is no bigger elements than 'cur')
				else if (cur < MAX_CUR ){//"cur is the biggest elemet"
						cur++;
				}
				//2-4. 기본 정렬 작업 완료 + cur이 최대 비교 지점까지 도달 완료
				//2-4. cur 이후에 최대값 없고 cur가 최대점에 도달하면 (all sorting is done within 'count')
				else {//"sort done + cur move done"
						//2-4-1. 중복순열이라면 종료 (no action if current array is a redundant permutation)
						if (DUP_VAL != -1) {//"dup is in a raw"
								/*
									중복된 원소끼리 교환이 되므로 추가 정렬 의미가 없음
									(it is meangless process because it will swap 3 with 3 until 'count' becomes 0)
									ex) 정렬(array) : 54331, 남은 카운트(count) : 5
								*/
						}
						//2-4-2. 중복순열이 아니라면 (if current array is not a redudant permutation)
						else {//"no dup is in a raw"
								/*
									정렬된 순열내 중복되는 원소가 없다면 추가 정렬 시도
									(Swap the tail with the its left value )
									ex) 정렬(array) : 54321, 남은 카운트(count) : 5

									최대를 만들기 위하여 맨 끝자리와 그 이전값만 반복적으로 교환됨
									(It will swap 2 with 1 until 'count' becomes 0)
								*/
								//2-4-2-1. 카운트가 짝수라면 swap 해도 제자리 (meangless if the 'count' is even)
								if (_rule.count % 2 == 0) {//"no action done"
										 ;//짝수번이면 제자리
								}
								//2-4-2-2. 카운트가 홀수라면 swap 한번만 (it will swap once if the 'count' is odd)
								else {//swap once"
										int tail = _rule.num.size() - 1;
										swap(_rule.num[ tail - 1], _rule.num[ tail ]);
								}
						}
						return _rule.num;
				}

				//3. 정렬된 값이 가능한 수중에 최대가 아니라면 다시 찾는다.(Find again if the sorted array is no the optimal)
				if (DUP_VAL == MAX_VAL && cur != MAX_CUR && _rule.count == 0) {//"sort not complited"
						_rule.count++;
						cur++;
				}
		}
		return _rule.num;
}
