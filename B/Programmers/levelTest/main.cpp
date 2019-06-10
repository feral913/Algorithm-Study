#include <iostream>
#include <cmath>
using namespace std;

int solution(int n, int a, int b);
int main() {
  int n, a, b;
  cin >> n >> a >> b;
  int answer = solution(n, a, b);
  return 0;
}
/*
  * 첫라운드에서 a번과 b번이 붙게되는 최소의 경기 수
  * a와 b는 붙기전까지 계속 이긴다.
  * 경기수 n은 2^n으로 주어진다. 따라서 부전승이 발생하지 않는다.
  * 예시
  1 2 3 4 5 6 7 8 = 8
  (1) 다음 회차일 때, 현재 놈이 몇번으로 배정되었는지 알아내는 알고리즘
    -> 현재 번호/2 = 몫
    if (현재 회차 % 2 == 0) {
      다음 회차 = 현재 회차/2
    } else {
      다음 회차 = 현자 회차/2 + 1
    }
    0 1 | 1 2 | 2 3 | 3 4
  (2) 언제까지 하나?
    - a와 b가 같은 회차를 가질 때까지
*/
int findNextNumber(int n) {
    if (n % 2 == 0) {
        return n = n/2;
    }
    return n/2 + 1;
    // return n % 2 == 0? n/2 : n/2 + 1;
}
int solution(int n, int a, int b) {
    int answer = 0;
    n = pow(2, n);
    cout << "사람 수 : " << n << endl;
    while (n > 1) {
        cout << "라운드(" << answer << ")-A(" << a << "), B(" << b << ")" << endl;
        int a = findNextNumber(a),
            b = findNextNumber(b);
        cout << ">> 다음라운드(" << answer << ")-A(" << a << "), B(" << b << ")" << endl << endl;
        if (a == b) return answer;
        n = n/2;
        answer++;
        // cout << ">> 다음라운드(" << answer << ")-A(" << a << "), B(" << b << ")" << endl << endl;
    }
    cout << answer << endl;
}
