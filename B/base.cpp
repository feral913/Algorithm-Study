#include <iostream>
using namespace std;

T file_input();
void file_output(vtype _output);
int solve_algorithm(Square _input);
int main()
{
    vtype output;
    int test_case;
    cin >> test_case;
    while (test_case-- > 0) {
        T input = file_input();
        int answer = solve_algorithm(input);
        output.push_back(answer);
    }
    file_output(output);
    return 0;
}
T file_input()
{

}
void file_output(vtype _output);
{

}
int solve_algorithm(T _input);
{
}
