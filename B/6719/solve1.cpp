#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

//def
typedef vector < int > vector_int;
typedef vector < double > vector_double;
typedef vector_double::iterator iter_double;
typedef struct Input {
    Input(int _n, int _k, vector_int _num_list) : n{_n}, k{_k}, num_list{_num_list} {}
    vector_int num_list;
    int n = 0, k = 0;
}Input;
//var

//func
Input file_input()
{
    int N, K, item;
    vector_int _in;
    cin >> N >> K;
    for ( int i = 0; i < N; ++i)
    {
        cin >> item;
        _in.push_back(item);
    }
    Input __in(N, K, _in);
    return __in;
}
double solve_algorithm(Input _input)
{
    double answer = 0;
    make_heap(_input.num_list.begin(), _input.num_list.end());
    //reverse
    vector_int new_num;
    for (int i = 0; i < _input.k; ++i)
    {
        //cout << " >> item : " << _input.num_list.front() << endl;
        new_num.push_back(_input.num_list.front());
        pop_heap(_input.num_list.begin(), _input.num_list.end());_input.num_list.pop_back();
    }
    //
    //cout << endl;
    for (int i = _input.k - 1; i >= 0 ; --i)
    {
        //cout << " >> in : " << new_num[i] << endl;
        answer += new_num[i];
        answer /= 2;
    }
    //cout << endl;
    return answer;
}
void file_output(vector_double _output)
{
    int i = 0;
    for (iter_double it = _output.begin(); it != _output.end(); ++it)
    {
        cout.precision(6);
        cout << fixed;
        cout << "#" << ++i << " " << fixed << (*it) << endl;
    }
}
int main()
{
    int T;
    cin >> T;
    vector_double output;
    for (int i = 0; i < T; ++i)
    {
        Input input = file_input();
        double answer = solve_algorithm(input);
        output.push_back(answer);
    }
    file_output(output);
    return 0;
}
