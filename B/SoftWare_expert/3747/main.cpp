#include <iostream>
#include <vector>
using namespace std;
/*
	# 조건
	(1) 주어지는 접시의 상태는 각각 정렬된 상태
	The plates from each manufacturer come stacked, that is, each arranged in a single stack with plates ordered by size (the smallest at the top, the largest at the bottom).

	(2) 쪼개진 스택의 일부를 어느 스택 위에 바로 올릴수 없음. 먼저 분할되고 난 다음 올리는 작업을 한다.
	Note that a portion of any stack may never be put directly on top of another stack.
	It must first be split and then the split portion must be joined with the other stack.
*/
//def
typedef vector <int> vtype;
typedef vector <vtype> vvtype;
typedef struct portion_info {
		portion_info(int _cb, int _ch, int _wt) { curBottom = _cb, curHeight = _ch, whereTo = _wt; }
		int curBottom = -1;//a value of bottom
		int curHeight = -1;//a height
		int whereTo = -1;//a stack index able to join
}Portion;
//var
//func
vvtype file_input();
void file_output(vtype _output);
int solve_algorithm(vvtype _input);
int main()
{
		//intializing
		vtype output;
		int test_case; cin >> test_case;
		//read testcase, solve, save its result
		for (int i = 0; i < test_case; ++i) {
				vvtype input = file_input();
				int answer = solve_algorithm(input);
				output.push_back(answer);
		}
		//print result
		file_output(output);
		return 0;
}
vvtype file_input()
{
		vvtype arrStackPlates;
		int numOfstack; cin >> numOfstack;
		//read from input file
		while (numOfstack-- > 0) {
				int plates, numOfplates; cin >> numOfplates;
				vtype stackPlates;
				while (numOfplates-- > 0) {
						cin >> plates;
						stackPlates.push_back(plates);
				}
				arrStackPlates.push_back(stackPlates);
		}
		return arrStackPlates;
}
void file_output(vtype _output)
{
		for (uint i = 0; i < _output.size(); ++i) {
				cout << '#' <<  i << ' ' << _output[i] << endl;
		}
}
//operation about to move
void join(vtype& _top, vtype& _bottom)
{
		cout << " > join" << endl;
		_top.insert(_top.end(), _bottom.begin(), _bottom.end());
}
//height 위 지점을 반환함.
vtype split(int _height, vtype& _src)
{
		//set bottom
		vtype top;
		for (int i = 0; i < _height; ++i) { top.push_back(_src[i]);}
		//set top
		_src.erase(_src.begin(), _src.begin() + _height);
		return top;
}
int solve_algorithm(vvtype _input)
{
		uint cost = 0;
		uint numOfstack = _input.size();
		//현재 스택수가 하나라면 (다 합쳐지면 그만)
		//1. join 하려고 했는데 안 되면 split
		//2. 다 split 해놓고 join
		while (numOfstack != 1) {
				//1. split
				/*
				* 지금 찢어져야하는 상황인지 알아야함
				*/
				//현재 스택과 다른 스택을 비교하면서 가장 그게 짤리는 부분을 portion으로 기억하고 split
				for (uint src)
				/*
				vtype top_portion = spilit(max_portion, it)
				_src.push_back(top_potion);
				numOfstack ++;
				}
				*/
				/*2. Join
						* 일단 먼저 하나가 다른 위에 올릴수 있는지 판단하고
						* 다른 스택들을 돌면서 올릴수 있는지 확인하고 제일 크게 올릴수 곳을 찾아감.
				*//*
				int max_portion;
				for (vvtype::iterator it = _input.begin(); it != _input.end(); ++it) {
						int sz = it->size();
						if ( 다른 옆에 올릴수 있나 ) {
								* 올릴수 있으면 바로 올리고
								* 가능하면 -> join, numOfstack --
						} else {
								* 못올리면 어디에서 제일 막히는 지 확인하고 기억해라
								* max_potion;
						}
				}
				*/
				//3.join 이던 spilt 이건 둘중 하나는 실행 되니깐,
				cost++;
		}
		return cost;
}
