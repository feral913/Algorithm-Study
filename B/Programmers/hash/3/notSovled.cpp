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
vvstype clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

int solution(vector < vstype > clothes);
int main() {
  solution(clothes);
  return 0;
}
int solution(vector < vstype > clothes) {
    map < int, int > countOfClotheType;
    int* category;
    //의상 분류
    int cname = 0, ctype = 1;
    for (int i = 0; i < clothes.size(); ++i) {
        int ckey = hash <string>{}(clothes[i][ctype]);
        if (countOfClotheType.find(ckey) != countOfClotheType.end()) {
            countOfClotheType[ckey]++;
        } else {
            countOfClotheType.insert(make_pair(ckey, 1));
        }
    }
    //순열 계산
    // cout << " >> 파악된 옷 종류 : " << countOfClotheType.size() << endl;
    // for (map < int, int >::iterator it = countOfClotheType.begin();
    //       it != countOfClotheType.end(); ++it) {
    //           cout << "옷 태그( " << it-> first << ") , 개수 : " <<  it -> second << endl;
    //       }
    // int answer = 0;
    //값 계산
    int answer = 0;
    for (map < int , int >::iterator it = countOfClotheType.begin();
        it != countOfClotheType.end(); ++ it ) {
            // cout << " cate1 : " << it -> first << endl;
            // cout << " cate1_num :" << it -> second <<endl;
            answer += (it-> second);
            map < int, int >::iterator it2 = it;
            while(++it2 != countOfClotheType.end()) {
                // cout << "cate2 : " << it2 -> first << endl;
                // cout << "cate2_num : " << it2 -> second << endl;
                answer += (it -> second) * (it2 -> second);
            }
            // cout << ">> current_answer :" << answer << endl;
            // cout << endl;
        }
    // cout << " >> 정답 : " << answer << endl;
    return answer;
}
