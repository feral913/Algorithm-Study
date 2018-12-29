#include <iostream>
#include <vector>
using namespace std;
//def
typedef pair <int, int> ptype;
typedef vector <ptype> pvtype;
typedef vector <int> vtype;
typedef struct area_info {
    area_info(){};
    area_info(int _n, int _a, int _b) : n(_n), a(_a), b(_b) {}
    //재귀함수 쓸 준비
    /*
    static double calculateArea(ptype& _p1, ptype& _p2, ptype& _p3) {
        //helon's raw
        int _postive = _p1.first * _p2.second +
                       _p2.first * _p3.second +
                       _p3.first * _p1.second;
        int _negative = _p2.first * _p1.second +
                        _p3.first * _p2.second +
                        _p1.first * _p3.second;
        double doubleArea = abs(_postive - _negative);
        return doubleArea != 0? doubleArea : 0;
    }*/
    // 삼중 for문시
    double calculateArea(int _p1, int _p2, int _p3) {
        //helon's raw
        int _postive = points[_p1].first * points[_p2].second +
                       points[_p2].first * points[_p3].second +
                       points[_p3].first * points[_p1].second;
        int _negative = points[_p2].first * points[_p1].second +
                        points[_p3].first * points[_p2].second +
                        points[_p1].first * points[_p3].second;
        double doubleArea = _postive - _negative;
        return doubleArea != 0? abs(doubleArea) : 0;
    }
    pvtype points;
    double n, a, b;
}Area;
//var
//func
template <typename T>
T file_input();
void file_output(vtype _output);
template <typename T>
int solve_algorithm(T _input);
int main()
{
    vtype output;
    int test_case;
    cin >> test_case;
    while (test_case-- > 0) {
        Area input = file_input<Area>();
        int answer = solve_algorithm(input);
        output.push_back(answer);
    }
    file_output(output);
    return 0;
}
template <typename T>
T file_input()
{
    int i = 0, n, a, b; cin >> n >> a >> b;
    //set input
    T _input(n, a, b);
    //get point
    while(i ++ < _input.n) {
        int x, y;
        cin >> x >> y;
        _input.points.push_back(make_pair(x, y));
    }
    return _input;
}
void file_output(vtype _output)
{
    int i = 0;
    for (auto& answer : _output) {
        cout << '#' << ++i << ' ' << answer << endl;}
}
template <typename T>
int solve_algorithm(T _input)
{
    const double BASE_A = _input.a, BASE_B = _input.b;
    const int psize = _input.points.size();
    //cout << " baseA : " << BASE_A << ", baseB :" << BASE_B << endl;
    int count = 0;
    //for (int i = 0; i < psize - 2; ++i) {
        //for (int j = i + 1; j < psize -1; ++j) {
            //for (int k = j + 1; k < psize; ++k) {
                double w = _input.calculateArea(i, j, k);
                //cout << " > width : " << w << endl;
                if (w != 0 && BASE_A <= w && w <= BASE_B) { count++;}
        //    }
      //  }
  //  }
    return width.size();
}/*
double recersiveWidth(int cur, pvtype& src)
{
    if (cur == src.size() - 2) { return -1;}

}
*/
