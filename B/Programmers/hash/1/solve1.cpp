#include <iostream>
#include <vector>
#include <string>
#include <map>
using namespace std;

string solution(vector<string> participant, vector<string> completion);
int main ()
{
    vector < string > a = {"leo", "kiki", "eden"};
    vector < string > b = {"eden", "kiki"};
    string answer = solution(a, b);
    cout << " >> answer : " << answer << endl;
    return 0;
}
string solution(vector<string> participant, vector<string> completion)
{
    string answer = "";
    //hash
    map < string, int > namelist;
    //In-participatient
    for (vector < string >::iterator it = participant.begin();
        it != participant.end(); ++it) {
            //cout << " > name : " << *it << endl;
            if (namelist.find(*it) == namelist.end())
              namelist.insert(make_pair(*it, 1));
              else {
                //cout << " >>> same name ! " << endl;
                //same-name participant occurs
                namelist[*it]++;
            }
        }
    //Check-completion
    for (vector < string >::iterator it = completion.begin();
        it != completion.end(); ++it) {
            namelist[*it]--;
            //cout << " > name_val :" << namelist[*it] << endl;
        }
    //Check-namelist
    //if namelist's value is not zero, it will be answer
    map < string, int >::iterator it = namelist.begin();
    while(it != namelist.end()) {
        if (it->second != 0) {
            //cout << " > anser : " << it->first << endl;
            answer = it->first;
            break;
        }
        ++it;
    }
    return answer;
}
