#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    vector <int> arr;// = {9, 1, 1, 2, 3, 5, 5, 6, 6, 7};
    arr.push_back(9);
    arr.push_back(1);
    arr.push_back(1);
    arr.push_back(2);
    arr.push_back(3);
    arr.push_back(5);
    arr.push_back(5);
    arr.push_back(6);
    arr.push_back(6);
    arr.push_back(7);
    unique(arr.begin(), arr.end());

    for (int i = 0; i < arr.size(); ++i)
      cout << arr[i] << endl;
    return 0;
}
