#include <iostream>
#include <vector>
#include <string>
#include <map>
using namespace std;
/*
  * 문자열 비교를 일일히 하지 말고 해시 처리해서 해보자. 라는 취지의 문제라고 해석함
*/
typedef vector<string> vstype;
typedef vector<vstype> vvstype;
vvstype clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "headgear"}, {"green_turban", "headgear"}};

int solution(vector < vstype > clothes);
int main() {
  solution(clothes);
  return 0;
}
int solution(vector < vstype > clothes) {
    map < int, int > countOfClotheType;
    //1. 의상 분류
    int cname = 0, ctype = 1;
    //해싱값이 더 빠르지 않을까 해서 넣음
    for (int i = 0; i < clothes.size(); ++i) {
        int ckey = hash <string>{}(clothes[i][ctype]);
        countOfClotheType[ckey]++;
    }
    int answer = 1;
    //2. 카운팅
    /*
      * 주머니 A, B 에서 구슬꺼내는 가지수 구하기
      * A 주머니 = 9개
      * B 주머니 = 5개
      * 10 * 6 - 1 = 59가지
      * 구슬 9개 + 하나도 안 꺼내는 수 = 10
      * 구슬 5개 + 하나도 안 꺼내는 수 = 6
      * - 1 = 둘 다 안 꺼내는 수
    */
    for (map < int , int >::iterator it = countOfClotheType.begin();
        it != countOfClotheType.end(); ++ it ) {
            answer *= (it -> second) + 1;
        }
    // cout << " >> 정답 : " << answer << endl;
    return answer - 1;
}
