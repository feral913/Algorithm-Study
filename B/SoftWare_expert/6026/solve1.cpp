#include <iostream>
#include <cmath>
#define MAX_DIVIDE 1000000007
using namespace std;

int permutation(int n);//순열(nPr)
int combination(int n, int r);//조합(nCr)
int recursivePR(int n, int m);//재귀 점화식
int solve_algorithm();
void file_output(int _testcase, int* _output);
int main()
{
    int testcase, *output;
    cin >> testcase;
    output = new int[testcase];
    for (int i = 0; i < testcase; ++i) {
        output[i] = solve_algorithm();
    }
    file_output(testcase, output);
    return 0;
}
int solve_algorithm()
{
    int M, N;
    cin >> M >> N;
    int ans = recursivePR(M, N) % MAX_DIVIDE;
    return ans;
}
/*
* recursivePR(m, n)
* F(x) : 키보드 x개만 사용하는 경우의 수
  F(1) = 1^n - 1C1 * F(0), (F(0) = 0)
  F(2) = 2^n - 2C1 * F(1)
  F(3) = 3^n - 3C2 * F(2) - 3C1 * F(1)
  F(m) = m^n - mC(m-1) * F(m-1) - mC(m-2) * F(m-2) ... + mC1 * F(1)
*/
int recursivePR(int m, int n)
{
    if (m == 1) { return 1;}
    int ret = pow(m, n);
    for (int i = m - 1; i > 0; --i) {
        int dup = combination(m, i) * recursivePR(i, n);
        ret -= dup;
    }
    return ret;
}
void file_output(int _testcase, int* _output)
{
    for (int i = 0; i < _testcase; ++i) {
        cout << "#" << i + 1 << ' ' <<  _output[i] << endl;
    }
}

int permutation(int n)
{
    int ans = 1;
    for (int i = 1; i <= n; ++i) { ans *= i;}
    return ans;
}
int combination(int n, int r)
{
    return permutation(n)/(permutation(n - r) * permutation(r));
}
