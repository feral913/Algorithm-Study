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
		//for (liter it = input.num.begin(); it != input.num.end(); ++it) { cout << *it;}
		vtype answer = solve_algorithm(input);
		output.push_back(answer);
	}
	file_output(output);
	return 0;
}
//read input
Input file_input()
{
		vtype input; string num;
		//input(number)
		cin >> num;
		cout << " >> num_size : " << num.size() << endl;
		for (uint i = 0; i < num.size(); ++i) { input.push_back(num[i]);}

		//input(sorting count)
		int count;
		cin >> count;

		return Input(count, input);
}
//print output
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
void swap(char* a, char* b)
{
		char temp = *a;
		*b = *a;
		*a = temp;
}
bool IsDuplicated(vtype& src)
{
		int base = 0;
		for (uint next = base + 1; next < src.size(); ++next) {
				if (src[ base ] == src[ next ] ) {
						return true;
				}
				base = next;
		}
		return false;
}
vtype solve_algorithm(Input _rule)
{
		const uint MAX_CUR = _rule.num.size() - 2;
		const vtype BASE_RAW = sort(_rule.num.begin(), _rule.num.end());
		
		uint cur = 0;
		while(_rule.count > 0) {
				cout << " #### cycle start #### " << endl;
				cout << " > count : " << _rule.count << endl;
				cout << " > cur : " << cur << endl;
				for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
				//
				//1. 숫자열에서 가장 큰 값이 있는 곳의 인덱스를 찾아냄
				uint max = cur;
				for (uint i = cur + 1; //current 이전의 값들을 무시
						i < _rule.num.size(); ++i) {
						//가장 뒤에 있는 가장 큰 값을 max로 찾게됨
						if (_rule.num[max] <= _rule.num[i]) {
								max = i;
						}
				}
				//431159 7
				//931154 6
				//951134 5
				//954131 4
				//954311 3
				//954311 2
				// 2. 정렬
				if (max != cur) {
						//< 정렬 시도 >
						cout << " >> try sort " << endl;
						swap(_rule.num[cur], _rule.num[max]);
						cur++;
						_rule.count--;
				}
				//cur이 최대 비교 도달 지점 전이라면
				else if (cur < MAX_CUR ){
						cout << " >> cur is not end " << endl;
						//만약 cur이 끝가지 도달 안했으면 더 해보는게 맞음
						//< 정렬 완료 -> 끝까지 확인 시도 >
						//78466 6
						cur++;
				}
				//
				//32888 2
				//23888 2
				//
				//
				//cur이 최대 비교 지점까지 도달하면
				else {
						//만약 cur이 끝까지 도달을 했으면 다시 cur을 처음으로 돌려서 한번더 확인
						// < 정렬 완료 + 끝까지 확인 완료 >
						//중복지점 찾기
						cout << " >> sort done + cur done" << endl;
						bool dup_point = IsDuplicated(_rule.num);
						if (dup_point != false) {
								cout << " >> dup is in a raw" << endl;
								//정렬된 순열내 중복이 존재하면 추가 정렬 종료
								//ex) 정렬결과 : 54331, 남은 카운트 : 5
								//3, 3끼리 교환이 계속 될꺼니깐 추가 정렬 의미가 없음
								_rule.count = 0;
						} else {
								cout << " >> no dup is in a raw" << endl;
								//정렬된 순열내 중복이 없다면
								//ex) 정렬결과 : 54321, 남은 카운트 : 5
								//최대를 만들기 위하여 맨 끝자리와 그 이전값이 반복적으로 교환될 것이므로
								if (_rule.count % 2 == 0) {
										cout << " >> no action done" << endl;
								} //짝수번이면 제자리
								else {
									cout << " >> one swap done " << endl;
									int tail = _rule.num.size() - 1;
									swap(_rule.num[ tail - 1], _rule.num[ tail ]);
								} //홀수번이면 한번만 교환
								_rule.count = 0;
						}
				}
				for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
				cout << " #### cycle end #### " << endl;
		}
		return _rule.num;
}
