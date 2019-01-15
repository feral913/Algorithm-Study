#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

//def
typedef vector <int> vtype;
typedef pair <int, int > ptype;
typedef vector <ptype> vptype;
//var
//func
vtype FILE_INPUT();
void FILE_OUTPUT(vptype _output);
ptype SOLVE(vtype _input);

int main()
{
		vptype output;
		int TEST_CASE;
		cin >> TEST_CASE;
		for (int i = 0; i < TEST_CASE; ++i) {
			vtype input = FILE_INPUT();
			ptype answer = SOLVE(input);
			output.push_back(answer);
	}
		FILE_OUTPUT(output);
		return 0;
}
vtype FILE_INPUT()
{
		vtype input;
		int item, count;
		cin >> count;
		while (count-- > 0) {
				cin >> item;
				input.push_back(item);
		}
		return input;
}
void FILE_OUTPUT(vptype _output)
{
		int index = 0;
		for (vptype::iterator it = _output.begin();
				it != _output.end(); ++it) {
				cout << '#' << (++index) << ' '
					<< (*it).first << ' ' << (*it).second << endl;
		}
}
ptype SOLVE(vtype _input)
{
		//cout << "### solve start ###" << endl;
		vtype input_differ;
		vtype::iterator start = _input.begin();

		//1. 계단 높이 차 계산 (Get difference between values)
		for (vtype::iterator next = start + 1; next != _input.end(); ++next) {
				int differ = ((*start) - (*next)) * (-1);
				input_differ.push_back(differ);
				start = next;
		}
		//2. 높은 순대로 힙정렬 (Make heap)
		int mymax = 0, mymin = 0, top = 0;

		//2-1. 제일 큰 '올라가기 값' (get max from up climb)
		make_heap(input_differ.begin(), input_differ.end());
		top = input_differ.front();
		if (top > 0) { mymax = top; }

		//2-2. 제일 큰 '내려오기 값' (get min from down climb)
		sort_heap(input_differ.begin(), input_differ.end());
		top = input_differ.front();
		if (top < 0) { mymin = top * (-1);}

		//cout << "### solve end ###" << endl;
		return ptype(mymax, mymin);
}
