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

	for (int i = 0; i < count; ++i) {
		Input input = file_input();
		vtype answer = solve_algorithm(input);
		output.push_back(answer);
	}
	file_output(output);
	return 0;
}
Input file_input()
{
		vtype input; string num;
		cin >> num;
		for (uint i = 0; i < num.size(); ++i) { input.push_back(num[i]);}
		int count;
		cin >> count;

		return Input(count, input);
}
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
int findMax(vtype _src)
{
		make_heap(_src.begin(), _src.end());
		return _src.front();
}
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
vtype solve_algorithm(Input _rule)
{
		const char MAX_VAL = findMax(_rule.num);//순열에서 최대값 원소(maximum value in a permutation)
		const char DUP_VAL = findDuplicated(_rule.num, MAX_VAL);//순열에서 최대값이 중복인지 판단(redundant value in a permutation)
		const uint MAX_CUR = _rule.num.size() - 2;//비교시 최대로 접근가능한 인덱스(maximum index during comparison)
		uint cur = 0;
		while(_rule.count > 0) {
				uint max = cur;
				for (uint i = cur + 1; i < _rule.num.size(); ++i)
						if (_rule.num[max] <= _rule.num[i]) { max = i;}
				if (max != cur) {
						swap(_rule.num[cur], _rule.num[max]);
						cur++;
						_rule.count--;
				}
				else if (cur < MAX_CUR ) {cur++;}
				else {
					if (DUP_VAL == -1) {//"no dup is in a raw"
								if (_rule.count % 2 != 0) {
										int tail = _rule.num.size() - 1;
										swap(_rule.num[ tail - 1], _rule.num[ tail ]);
								}
						}
						return _rule.num;
				}
				if (DUP_VAL == MAX_VAL && cur != MAX_CUR && _rule.count == 0) {//"sort not complited"
						_rule.count++;
						cur++;
				}
		}
		return _rule.num;
}
