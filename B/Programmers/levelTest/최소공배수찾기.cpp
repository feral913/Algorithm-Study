#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 해결
// 10 = 2 * 5, 25 = 5 * 5
// 2,4, 6, 10, 12
int main() {
    vector <int> arr{1, 2, 3};
    //2수 찾고
    int answer = 1;
    sort(arr.begin(), arr.end());
    for (uint i = 0; i < arr.size() - 1; ++i) {
        cout << arr[i] << "와 " << arr[i+1] << "의 최소공배수 :";
        //case1.오른쪽이 왼쪽으로 나눠지면 오른쪽이 최소 공배수
        if (arr[i + 1] % arr[i] == 0) {
          answer = arr[i + 1];
        }
        //case2.오른쪽이랑 왼쪽을 소인수 분해해서 없는 것끼리 곱하면 끝
        else {
            int base = arr[i], init_num = arr[i]/2;
            while(init_num > 1) {
                if (arr[i+1] % init_num == 0
                    && arr[i] % init_num == 0)
                {
                    arr[i + 1] /= init_num;
                    arr[i] /= init_num;
                }
                init_num--;
            }
            answer = arr[i+1] * base;
            arr[i + 1] = answer;
        }
        cout << answer << endl;
    }
    return 0;
}
