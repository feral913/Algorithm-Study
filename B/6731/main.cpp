#include <iostream>
#include <vector>
#include <queue>
using namespace std;
//hold
//
//backtracking problem
//
//def
typedef vector < int > vtype;
typedef vtype::iterator vit_type;
typedef queue < int > qtype;
typedef struct square_matrix {
    square_matrix(int _n) { for (uint i = 0; i < _n; ++i) { val.push_back(0);}}
    square_matrix(int _n, vtype _val) { n = _n, val = _val;}
    bool isBlack(int _x, int _y) { return val [n * _x + _y] == 1? 1 : 0;}
    bool isSame(vtype _dst) {
        uint i;
        for (i = 0; i < _dst.size() && val[i] == _dst[i]; ++i);
        return i == (_dst.size() - 1);
    }
    bool isSame(square_matrix _dst) { return isSame(_dst.val);}
    void setPaint(int _point) {
        int r = _point / n, c = _point % n;
        setRow(r, 0, n - 1);
        setCol(c, 0, n - 1);
        val[n * r + c] == 1? 0 : 1;
    }
    qtype getChildFrom(int _point) {
        int except_r = _point / n, except_c = _point % n;
        int cur_row = -1, cur_col;
        qtype major, minor, child;
        for (uint i = 0; i < val.size(); ++i) {
            cur_col = i % n;
            if (cur_col == 0) { cur_row++;}  //set row
            if (except_r != cur_row || except_c != cur_col) {
                major.push(i);  //major priority
            } else if (cur_row ){ minor.push(i);}//minor priority
        }
        while(!major.empty()) {child.push(major.front());major.pop();}
        while(!minor.empty()) {child.push(minor.front());minor.pop();}
        return child;
    }
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
//reverse-traversal
void backtracking(Square _cur_matrix, qtype _child, Square _target) {
    while (!_child.empty()) {
        int i = _child.front();_child.pop();
        qtype new_child = _cur_matrix.getChildFrom(i);
        _cur_matrix.setPaint(i);
        //check
        if (_cur_matrix.isSame(_target)) {return;}
        else { backtracking(_cur_matrix, new_child, _target);}
    }
}
int solve_algorithm(Square _target)
{   //initilizing
    int count, n = _target.n;
    Square init_square(n);
    qtype init_child;for (int i = 0; i < n; ++i) { init_child.push(i);}
    //backtracking
    for (count = 0; count < n; ++count) {
        backtracking(init_square, init_child, _target);
    }
    return count;
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
