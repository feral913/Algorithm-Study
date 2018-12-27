#include <iostream>
#include <vector>
using namespace std;
/*
	# ����
	(1) �־����� ������ ���´� ���� ���ĵ� ����
	The plates from each manufacturer come stacked, that is, each arranged in a single stack with plates ordered by size (the smallest at the top, the largest at the bottom).

	(2) �ɰ��� ������ �Ϻθ� ��� ���� ���� �ٷ� �ø��� ����. ���� ���ҵǰ� �� ���� �ø��� �۾��� �Ѵ�.
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
//height �� ������ ��ȯ��.
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
		//���� ���ü��� �ϳ���� (�� �������� �׸�)
		//1. join �Ϸ��� �ߴµ� �� �Ǹ� split
		//2. �� split �س��� join
		while (numOfstack != 1) {
				//1. split
				/*
				* ���� ���������ϴ� ��Ȳ���� �˾ƾ���
				*/
				//���� ���ð� �ٸ� ������ ���ϸ鼭 ���� �װ� ©���� �κ��� portion���� ����ϰ� split
				for (uint src)
				/*
				vtype top_portion = spilit(max_portion, it)
				_src.push_back(top_potion);
				numOfstack ++;
				}
				*/
				/*2. Join
						* �ϴ� ���� �ϳ��� �ٸ� ���� �ø��� �ִ��� �Ǵ��ϰ�
						* �ٸ� ���õ��� ���鼭 �ø��� �ִ��� Ȯ���ϰ� ���� ũ�� �ø��� ���� ã�ư�.
				*//*
				int max_portion;
				for (vvtype::iterator it = _input.begin(); it != _input.end(); ++it) {
						int sz = it->size();
						if ( �ٸ� ���� �ø��� �ֳ� ) {
								* �ø��� ������ �ٷ� �ø���
								* �����ϸ� -> join, numOfstack --
						} else {
								* ���ø��� ��𿡼� ���� ������ �� Ȯ���ϰ� ����ض�
								* max_potion;
						}
				}
				*/
				//3.join �̴� spilt �̰� ���� �ϳ��� ���� �Ǵϱ�,
				cost++;
		}
		return cost;
}
