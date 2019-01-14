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
//�Է�(read input)
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
//��� ���(print output)
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
//������(Swap a with b)
void swap(char* a, char* b)
{
		char temp = *a;
		*b = *a;
		*a = temp;
}
//�������� �ִ밪�� ���� ã��(Find the max from '_src')
int findMax(vtype _src)
{
		make_heap(_src.begin(), _src.end());
		return _src.front();
}
//�ִ밪�� �ߺ��������� Ȯ��(Check if the duplicate number is '_target')
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
	# ����
	#1 �� ���ڸ������� ���� ū ���� ������.
	#2 ���� ó�� (�ߺ�����, count Ȧ¦����, ���� ��ġ�� ���� ī��Ʈ ����)
*/
vtype solve_algorithm(Input _rule)
{
		const char MAX_VAL = findMax(_rule.num);//�������� �ִ밪 ����(maximum value in a permutation)
		const char DUP_VAL = findDuplicated(_rule.num, MAX_VAL);//�������� �ִ밪�� �ߺ����� �Ǵ�(redundant value in a permutation)
		const uint MAX_CUR = _rule.num.size() - 2;//�񱳽� �ִ�� ���ٰ����� �ε���(maximum index during comparison)
		uint cur = 0;
		while(_rule.count > 0) {
				uint max = cur;
				//1. ���ڿ����� ���� ū ���� �ִ� ���� �ε����� ã�Ƴ�(Find the location with max value after 'cur')
				for (uint i = cur + 1;
						i < _rule.num.size(); ++i) {
						if (_rule.num[max] <= _rule.num[i]) {//�ִ밪�̸鼭 ���� ���� �༮�� max(Set the index if it is maximum and largest index)
								max = i;
						}
				}

				//2. ���� �۾� (Sort)
				//2-1. cur ���Ŀ� �ִ밪�� �����Ѵٸ� swap(Swap the elements if there is the biggest element after 'cur')
				if (max != cur) {//"swap"
						swap(_rule.num[cur], _rule.num[max]);
						cur++;
						_rule.count--;
				}
				//2-2. cur ���Ŀ� �ִ밪�� ���ٸ� ���� cur�� �̵�(Increase 'cur' if there is no bigger elements than 'cur')
				else if (cur < MAX_CUR ){//"cur is the biggest elemet"
						cur++;
				}
				//2-4. �⺻ ���� �۾� �Ϸ� + cur�� �ִ� �� �������� ���� �Ϸ�
				//2-4. cur ���Ŀ� �ִ밪 ���� cur�� �ִ����� �����ϸ� (all sorting is done within 'count')
				else {//"sort done + cur move done"
						//2-4-1. �ߺ������̶�� ���� (no action if current array is a redundant permutation)
						if (DUP_VAL != -1) {//"dup is in a raw"
								/*
									�ߺ��� ���ҳ��� ��ȯ�� �ǹǷ� �߰� ���� �ǹ̰� ����
									(it is meangless process because it will swap 3 with 3 until 'count' becomes 0)
									ex) ����(array) : 54331, ���� ī��Ʈ(count) : 5
								*/
						}
						//2-4-2. �ߺ������� �ƴ϶�� (if current array is not a redudant permutation)
						else {//"no dup is in a raw"
								/*
									���ĵ� ������ �ߺ��Ǵ� ���Ұ� ���ٸ� �߰� ���� �õ�
									(Swap the tail with the its left value )
									ex) ����(array) : 54321, ���� ī��Ʈ(count) : 5

									�ִ븦 ����� ���Ͽ� �� ���ڸ��� �� �������� �ݺ������� ��ȯ��
									(It will swap 2 with 1 until 'count' becomes 0)
								*/
								//2-4-2-1. ī��Ʈ�� ¦����� swap �ص� ���ڸ� (meangless if the 'count' is even)
								if (_rule.count % 2 == 0) {//"no action done"
										 ;//¦�����̸� ���ڸ�
								}
								//2-4-2-2. ī��Ʈ�� Ȧ����� swap �ѹ��� (it will swap once if the 'count' is odd)
								else {//swap once"
										int tail = _rule.num.size() - 1;
										swap(_rule.num[ tail - 1], _rule.num[ tail ]);
								}
						}
						return _rule.num;
				}

				//3. ���ĵ� ���� ������ ���߿� �ִ밡 �ƴ϶�� �ٽ� ã�´�.(Find again if the sorted array is no the optimal)
				if (DUP_VAL == MAX_VAL && cur != MAX_CUR && _rule.count == 0) {//"sort not complited"
						_rule.count++;
						cur++;
				}
		}
		return _rule.num;
}
