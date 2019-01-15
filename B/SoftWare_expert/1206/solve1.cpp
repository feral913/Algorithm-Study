#include <iostream>
#include <vector>

using namespace std;

//def
typedef vector < int > vtype;
typedef vtype::iterator vit_type;
//var
//func
vtype file_input();
void file_output(vtype _output);
int solve_algorithm(vtype _input);
int main()
{
   vtype output;
   for (int i = 0; i < 10; ++i)
   {
	vtype input = file_input();
	int answer = solve_algorithm(input);
	output.push_back(answer);
   }
   file_output(output);
   return 0;
}
vtype file_input()
{
   vtype _input;
   int count = 0, height = 0;
   cin >> count;
   while(count-- > 0)
   {
	cin >> height;
	_input.push_back(height);
   }
   return _input;
}
void file_output(vtype _output)
{
   for (vit_type it = _output.begin(); it != _output.end(); ++it)
   {
	static int i = 0;
	cout << "#" << ++ i << " " << *it << endl;
   }
}
int solve_algorithm(vtype _building)
{
   int answer = 0, cur_height;
   for (uint i = 0; i < _building.size(); ++i)
   {
	cur_height = _building[i];
	if (cur_height != 0)
	{
	    //Compare the buildings on the left and right.
	    int ltop = _building[i - 2] > _building[i - 1]? _building[i - 2] : _building[i - 1],
	        rtop = _building[i + 1] > _building[i + 2]? _building[i + 1] : _building[i + 2];
	    //Compare them with the center,'_building[i]'
	    int t1 = cur_height - ltop,
	        t2 = cur_height - rtop;
	    //Decide the number of the viewers
	    if (t1 > 0 && t2 > 0)
	    {
		answer += ltop > rtop? t1 : t2;
	    }
   	}
   }
   return answer;
}
