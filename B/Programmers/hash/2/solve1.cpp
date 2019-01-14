#include <iostream>
#include <vector>
#include <string>
#include <map>
using namespace std;

//use-bloom filter
bool solution(vector<string> phone_book);
int main() {
  /* test case
    //vector < string > phone_book = {"119", "97674223", "1195524421"};
    //vector < string > phone_book = {"113", "12340", "123440", "12345", "98346"};
    //vector < string > phone_book = {"911", "97625999", "91125426"};
  */
  vector < string > phone_book = {"12232332", "12", "222222"};
  bool a = solution(phone_book);
  cout << a << endl;
  return 0;
}
bool solution(vector < string > phone_book) {
    bool answer = true;
    map < string, string > bloom_filter;
    //make-filter
    for (vector < string >::iterator src = phone_book.begin(); src != phone_book.end(); ++src) {
        size_t start = 0, end = src->size() - 1;
        //insert substring from 0 to (src - 1)
        while (start < end) {
            bloom_filter.insert(make_pair(src->substr(0, ++start), *src));
        }
    }
    //find-target from bloom-filter
    for (vector < string >::iterator dst = phone_book.begin(); answer && dst != phone_book.end(); ++dst) {
        answer = bloom_filter.find(*dst) == bloom_filter.end();
    }
    return answer;
}
