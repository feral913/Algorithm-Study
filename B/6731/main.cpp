#include <iostream>
#include <vector>
#include <stack>
using namespace std;
//
//backtracking problem
//
//def
typedef vector < int > vtype;
typedef vtype::iterator vit_type;
typedef struct point{
    int x;
    int y;
}point;
typedef struct square_matrix {
    square_matrix(int _n, vtype _val) { n = _n, val = _val;}
    bool isBlack(point _p) {return val[n * _p.x + _p.y] == 1? 1 : 0;}
    void setRow(int _row, int _from, int _to) {
        for (int c = _from; c <= _to; ++c) {
            int state = val[n * _row + c];
            state == 1? 0 : 1;  //���� ���°� B�� W�� ��ȯ
        }
    }
    void setCol(int _col, int _from, int _to) {
      for (int r = _from; r <= _to; ++r) {
          int state = val[n * _col + r];
          state == 1? 0 : 1;  //���� ���°� B�� W�� ��ȯ
      }
    }
    vtype val;
    int n;
}Square;
//var
//func
Square file_input();
void file_output(vtype _output);
int solve_algorithm(Square _input);
int main()
{
    vtype output;
    int test_case;
    cin >> test_case;
    while (test_case-- > 0) {
        Square input = file_input();
        int answer = solve_algorithm(input);
        output.push_back(answer);
    }
    file_output(output);
    return 0;
}
int solve_algorithm(Square _target)
{
    //backtracking ����
    //
    //0.�ʱ� ���� stack�� ���� �ΰ� ������ stack ����ų�
    //�ϳ��� �������� �� �����Ұ�
    //1. ���� ���ÿ��� ������ ��ǥ��(= ���õ� ������ ������ ���� �༮��)�� ���ÿ� �߰�
    //2. �߰��� ��ҿ� ���Ͽ� "��� �Ǻ�"(������ ��ĥ ���� ����) = ������� ���ϴ� ����
    //3. �����ϴٸ� ������ ��ǥ���� ���ÿ� �߰��ϰ�, �׷��� �ʴٸ� ������ ���� ���� �����´�.
    //1-3�� �ݺ��Ѵ�.
    // ��������� Ǯ���� ���� ��
    return 0;
}
Square file_input()
{
    vtype input; int n; char in;
    cin >> n;
    for (int i = 0; i < n*n; ++i) {
        cin >> in;
        if (in == 'B') { input.push_back(1);}
        else input.push_back(0);//W
    }
    return Square(n, input);
}
void file_output(vtype _output)
{
    int i = 0;
    for (vit_type it = _output.begin(); it != _output.end(); ++it) {
        cout << "#" << ++i << ' ' << *it << endl;
    }
    return ;
}
