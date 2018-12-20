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
            state == 1? 0 : 1;  //현재 상태가 B면 W로 변환
        }
    }
    void setCol(int _col, int _from, int _to) {
      for (int r = _from; r <= _to; ++r) {
          int state = val[n * _col + r];
          state == 1? 0 : 1;  //현재 상태가 B면 W로 변환
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
    //backtracking 문제
    //
    //0.초기 시작 stack을 따로 두고 별도의 stack 만들거나
    //하나의 스택으로 다 구현할것
    //1. 현재 스택에서 가능한 좌표들(= 선택된 영역에 속하지 않은 녀석들)을 스택에 추가
    //2. 추가된 요소에 대하여 "요건 판별"(오셀로 색칠 가능 여부) = 결과값과 비교하는 과정
    //3. 가능하다면 가능한 좌표들을 스택에 추가하고, 그렇지 않다면 버리고 다음 값을 꺼내온다.
    //1-3을 반복한다.
    // 재귀적으로 풀수도 있을 듯
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
