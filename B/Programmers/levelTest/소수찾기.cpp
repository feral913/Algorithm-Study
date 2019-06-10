#include <iostream>

using namespace std;

// 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
//
// 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
// (1은 소수가 아닙니다.)
//
// 제한 조건
// n은 2이상 1000000이하의 자연수입니다.
int solution(int n);
int main() {
    int _in = 0 ;
    cout << ">> 입력 : "; cin >> _in;
    int answer = solution(_in);
    cout << " >> 정답 : " << answer << endl;
    return 0;
}

int solution(int n) {
  int answer = 1;
  if (n == 2) return answer;
  for (int i = 3; i <= n; ++i) {
      if (i % 2 != 0) {
        int j = 2;
        for (; i % j && j <= i; ++j);
        if (j == i) {answer ++;}
      }
  }
  return answer;
}
