/**
* 流水作业调度
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

struct Job {
public:
    int time;
    int index;
    bool job;
};

/**
 * 计算最短时间
 * @param a m1每项耗时
 * @param b m2每项耗时
 * @param order 项目流水顺序
 * @return 最短耗时和顺序
 */
pair<int, vector<int>> flowShop(vector<int> a, vector<int> b) {
   int time = 0, n = a.size();
   vector<int> order(n, 0);
   vector<Job> jobs(n);

   for(int i= 0; i < n; i++){
      jobs[i].index = i;

      if(a[i] < b[i]) {
          jobs[i].time = a[i];
          jobs[i].job = true;
      }else{
          jobs[i].time = b[i];
          jobs[i].job = false;
      }
   }
    sort(jobs.begin(), jobs.end(), [](Job a, Job b)-> bool {return a.time < b.time;});

    for (int left = 0, right = n - 1, i = 0; i < n; i++) {
        if (jobs[i].job) {
            order[left++] = jobs[i].index;
        } else {
            order[right--] = jobs[i].index;
        }
    }

    for (int tmp = 0, i = 0; i < n; i++) {
        tmp += a[order[i]];
        time = time > tmp? time + b[order[i]] : tmp + b[order[i]];
    }
    return pair<int, vector<int>>(time, order);
}

int main() {
    vector<int> a = {5, 3, 6, 4, 8, 9, 6},
            b = {2, 4, 7, 2, 9, 7, 3};

    pair<int, vector<int>> minOrder = flowShop(a, b);

    cout << "min time : " << minOrder.first << endl;
    cout << "min order : ";
    for_each(minOrder.second.begin(), minOrder.second.end(), [](auto var) -> void { cout << var << " "; });

    return 0;
}
