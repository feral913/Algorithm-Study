#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;
/*
  * 우선 순위 : 장르 > 재생수
  * 프로세스
    (1) 가장 많이 재생된 장르 선별
    (2) 각 장르별 가장 재생이 많이 된 노래 2곡 선별
    (3) 장르별 결과 출력
*/
vector<int> solution(vector<string> genres, vector<int> plays);
int main()
{
    vector <string> gen; vector <int> pla;
    solution(gen, pla);
    return 0;
}
typedef map < int, int > mvtype;
typedef map < int, vector < int > > mvvtype;
vector<int> solution(vector<string> genres, vector<int> plays) {
    genres = {"classic", "pop", "classic", "classic", "pop"};
    plays = {500, 600, 150, 800, 2500};
    vector<int> answer;
    //1. 장르별 재생횟수
    map < int, int > countOfgenres;
    map < int, vector < int > > indexBygenres;
    for (int i = 0; i < genres.size(); ++i) {
        int gkey = hash <string>{}(genres[i]);
        countOfgenres[gkey] += plays[i];
        indexBygenres[gkey].push_back(i);
    }
    for (mvvtype::iterator it = indexBygenres.begin(); it != indexBygenres.end(); ++it) {
        vector < int > s = it->second;
        cout << " >> category_name :" << it -> first << " - ";
        for (int i = 0; i < s.size(); ++i) {
            cout << s[i] << ", ";
        }
        cout << endl;
    }
    //2. 장르내 재생횟수
    vector < pair < int, int > > countOfgenres_temp;
    for (mvtype::iterator it = countOfgenres.begin(); it != countOfgenres.end(); ++it) {
        countOfgenres_temp.push_back(make_pair(it->second, it->first));
    }
    sort(countOfgenres_temp.begin(), countOfgenres_temp.end());
    //
    for (int i = 0; i < countOfgenres_temp.size(); ++i) {
        cout << countOfgenres_temp[i].first << ", " << countOfgenres_temp[i].second << endl;
    }
    //3. 확인 시작
    for (int i = countOfgenres_temp.size() - 1; i >= 0; --i) {
        vector < int > temp = indexBygenres[countOfgenres_temp[i].second];
        // sort(temp.begin(), temp.end());
        //
        // if (temp.size() >= 2) {
        //   answer.push_back(temp[temp.size()-1]);
        //   answer.push_back(temp[temp.size()-2]);
        // }
        // else
        //   answer.push_back(temp[0]);

    }
    cout << " ##################### answer #####################" << endl;
    for (int i = 0; i < answer.size(); ++i)
      cout << answer[i] << ' ';
    cout << endl;
    return answer;
}
