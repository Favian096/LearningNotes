/**
* 活动安排问题
*/

#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct Activity {
    int id;
    int start;
    int end;

    Activity(int id, int start, int end) {
        this->id = id;
        this->start = start;
        this->end = end;
    }
} Activity;

/**
 * 贪心计算最大兼容活动个数
 *
 * @param activities 活动集
 * @return 兼容活动个数和活动
 */
pair<int, vector<int>> greedySelector(vector<Activity> activities) {
//    已按结束时间排序
    sort(activities.begin(), activities.end(), [](Activity a, Activity b) { return a.end < b.end; });
    vector<int> activitiesId = {activities[0].id};
    int count = 1;

    for (int i = 1, j = 0; i < activities.size(); i++) {
        if (activities[i].start >= activities[j].end) {
            activitiesId.push_back(activities[i].id);
            j = i, count++;
        }
    }
    return pair<int, vector<int>>(count, activitiesId);
}

int main() {
    /**
     * 已按结束时间排序
     */
    vector<Activity> activities = {Activity(1, 0, 6),
                                   Activity(2, 1, 4),
                                   Activity(3, 2, 14),
                                   Activity(4, 3, 5),
                                   Activity(5, 3, 9),
                                   Activity(6, 5, 7),
                                   Activity(7, 5, 9),
                                   Activity(8, 6, 10),
                                   Activity(9, 8, 11),
                                   Activity(10, 8, 12),
                                   Activity(11, 12, 16)};

    pair<int, vector<int>> result = greedySelector(activities);
    cout << "Maximum number of compatible activities: " << result.first << endl;
    cout << "Index of activities: ";
    for_each(result.second.begin(), result.second.end(), [](int index) { cout << index << " "; });
    return 0;
}