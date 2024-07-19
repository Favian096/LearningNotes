/**
* 活动安排问题
*/
#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * 贪心计算最大兼容活动个数
 *
 * @param s 活动开始时间集
 * @param f 活动结束时间集
 * @return 兼容活动个数和活动
 */
pair<int, vector<int>> greedySelector(vector<int> s, vector<int> f) {
    vector<int> activities = {1};
    int count = 1;
    for (int i = 1, j = 0; i < s.size(); i++) {
        if (s[i] >= f[j]) {
            activities.push_back(i + 1);
            j = i;
            count++;
        }
    }
    return pair<int, vector<int>>(count, activities);
}

int main() {
    /**
     * 已按结束时间排序
     */
    vector<vector<int>> S = {{1, 3, 0, 5, 3, 5, 6,  8,  8,  2,  12},
                             {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16}};
    pair<int, vector<int>> result = greedySelector(S[0], S[1]);
    cout << "Maximum number of compatible activities: " << result.first << endl;
    cout << "Index of activities: ";
    for_each(result.second.begin(), result.second.end(), [](int index) { cout << index << " "; });
    return 0;
}