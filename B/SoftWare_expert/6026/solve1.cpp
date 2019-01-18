#include <iostream>
#include <cmath>
#define MAX_DIVIDE 1000000007
using namespace std;

int pp(int n) {
    int ans = 1;
    for (int i = 1; i <= n; ++i) {
        ans *= i;
    }
    return ans;
}
int cc(int n, int r) {
    int head = 1, tail = 1;
    head = pp(n), tail = pp(n - r) * pp(r);
    int ret = head/tail;
    //cout << n << "C" << r << "(" << ret << ") *";
    return ret;
}
int recursivePR(int n, int m);
int main()
{
    int M, N;
    cin >> M >> N;

    int ans = recursivePR(N, M)/MAX_DIVIDE;
    //cout << " >> ans : " << ans << endl;
    return 0;
}
int recursivePR(int n, int m)
{
    if (m == 1) {
      //cout << " " << m << "^" << n << "(" << 1 << ")" << endl;
      return 1;
    }
    int ret = pow(m, n);//3^5
    //cout << m << "^" << n << "(" << ret << ")" << endl;
    for (int i = m - 1; i > 0; --i) {
        int dup = cc(m, i) * recursivePR(n, i);
        ret -= dup;
    }
    //cout << " >>> "<< m << "^" << n << "(" << ret << ")" << endl;
    return ret;
}
