#include <iostream>
#include <vector>
#include <cstring>
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
void swap(char* a, char* b) {
		char temp = *a;
		*b = *a;
		*a = temp;
}
/*
vtype solve_algorithm(Input _rule)
{
		uint cur = 0;
		while(_rule.count > 0) {
				cout << " #### cycle start #### " << endl;
				cout << " > count : " << _rule.count << endl;
				for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
				//
				// 제일 큰 자리수 찾기
				uint max = cur;
				for (uint i = cur + 1; //current 이전의 값들을 무시
						i < _rule.num.size(); ++i) {
						cout << "(" << i << ")cur : " << _rule.num[i];
						if (_rule.num[max] <= _rule.num[i]) {
								max = i;
								cout << ", max : " << _rule.num[max] << endl;
						} else { cout << endl;}
				}
				// 현재 current와 max 스위치
				if (max != cur) {
						cout << " >> find max " << endl;
						cout << " > cur : " << _rule.num[cur] << endl;
						cout << " > max : " << _rule.num[max] << endl;
						if (cur < _rule.num.size() - 2) {
								swap(_rule.num[cur], _rule.num[max]);
								cur++; _rule.count--;
						} else {
								cout << " >> no max " << endl;
						}
						for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
						cout << " #### cycle end #### " << endl;
				}
				else if (cur == _rule.num.size() - 2){
						//마지막 꼬리 바꾸기
						uint sz = _rule.num.size();
						while(_rule.count-- > 0) {
								cout << " # tail start# " << endl;
								for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
								//
								swap(_rule.num[sz - 2], _rule.num[sz - 1]);
								//
								for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
								cout << " # tail end #### " << endl;
						}
				} else {
						cur++;
						cout << " > nothing hapeens" << endl;
						cout << " #### cycle end #### " << endl;
				}
		}

		return _rule.num;
}
*/
vtype solve_algorithm(Input _rule)
{
		uint cur = 0;
		while(_rule.count > 0) {
				cout << " #### cycle start #### " << endl;
				cout << " > count : " << _rule.count << endl;
				for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
				//
				// 제일 큰 자리수 찾기
				uint max = -1;//0;//cur;
				for (uint i = 0;//cur + 1; //current 이전의 값들을 무시
						i < _rule.num.size(); ++i) {
						cout << "(" << i << ")cur : " << _rule.num[i];
						if (_rule.num[max] <= _rule.num[i]) {
								max = i;
								cout << ", max : " << _rule.num[max] << endl;
						} else { cout << endl;}
				}
				// 현재 current와 max 스위치
				if (max != -1) {//cur) {
						cout << " >> find max " << endl;
						//cout << " > cur : " << _rule.num[cur] << endl;
						cout << " > max : " << _rule.num[max] << endl;
						if (cur < _rule.num.size() - 2) {
								swap(_rule.num[cur], _rule.num[max]);
								//cur++; 
								_rule.count--;
						} else {
								cout << " >> no max " << endl;
						}
						for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
						cout << " #### cycle end #### " << endl;
				}
				else if (cur == _rule.num.size() - 2){
						//마지막 꼬리 바꾸기
						uint sz = _rule.num.size();
						while(_rule.count-- > 0) {
								cout << " # tail start# " << endl;
								for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
								//
								swap(_rule.num[sz - 2], _rule.num[sz - 1]);
								//
								for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
								cout << " # tail end #### " << endl;
						}
				} else {
						cur++;
						cout << " > nothing hapeens" << endl;
						cout << " #### cycle end #### " << endl;
				}
		}

		return _rule.num;
}
