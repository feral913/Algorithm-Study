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
				//1. ���ڿ����� ���� ū ���� �ִ� ���� �ε����� ã�Ƴ�
				uint max = cur;
				for (uint i = cur + 1; //current ������ ������ ����
						i < _rule.num.size(); ++i) {
						//���� �ڿ� �ִ� ���� ū ���� max�� ã�Ե�
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
				// 2. ����
				if (max != cur) {
						//< ���� �õ� >
						cout << " >> try sort " << endl;
						swap(_rule.num[cur], _rule.num[max]);
						cur++;
						_rule.count--;
				}
				//cur�� �ִ� �� ���� ���� ���̶��
				else if (cur < MAX_CUR ){
						cout << " >> cur is not end " << endl;
						//���� cur�� ������ ���� �������� �� �غ��°� ����
						//< ���� �Ϸ� -> ������ Ȯ�� �õ� >
						//78466 6
						cur++;
				}
				//
				//32888 2
				//23888 2
				//
				//
				//cur�� �ִ� �� �������� �����ϸ�
				else {
						//���� cur�� ������ ������ ������ �ٽ� cur�� ó������ ������ �ѹ��� Ȯ��
						// < ���� �Ϸ� + ������ Ȯ�� �Ϸ� >
						//�ߺ����� ã��
						cout << " >> sort done + cur done" << endl;
						bool dup_point = IsDuplicated(_rule.num);
						if (dup_point != false) {
								cout << " >> dup is in a raw" << endl;
								//���ĵ� ������ �ߺ��� �����ϸ� �߰� ���� ����
								//ex) ���İ�� : 54331, ���� ī��Ʈ : 5
								//3, 3���� ��ȯ�� ��� �ɲ��ϱ� �߰� ���� �ǹ̰� ����
								_rule.count = 0;
						} else {
								cout << " >> no dup is in a raw" << endl;
								//���ĵ� ������ �ߺ��� ���ٸ�
								//ex) ���İ�� : 54321, ���� ī��Ʈ : 5
								//�ִ븦 ����� ���Ͽ� �� ���ڸ��� �� �������� �ݺ������� ��ȯ�� ���̹Ƿ�
								if (_rule.count % 2 == 0) {
										cout << " >> no action done" << endl;
								} //¦�����̸� ���ڸ�
								else {
									cout << " >> one swap done " << endl;
									int tail = _rule.num.size() - 1;
									swap(_rule.num[ tail - 1], _rule.num[ tail ]);
								} //Ȧ�����̸� �ѹ��� ��ȯ
								_rule.count = 0;
						}
				}
				for (vtype::iterator it = _rule.num.begin(); it != _rule.num.end(); ++it) { cout << *it;}cout << endl;
				cout << " #### cycle end #### " << endl;
		}
		return _rule.num;
}
