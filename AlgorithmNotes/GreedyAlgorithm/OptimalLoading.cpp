/**
* 最优装载
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * 贪心算法求最优装载个数
 * @param capacity 总容量
 * @param weights 物品重量集
 * @return 可装载个数
 */
int optimalLoading(int capacity, std::vector<int> weights) {
    int itemCount = 0, index = 0;
    sort(weights.begin(), weights.end(), [](int a, int b) { return a < b; });
    while(capacity > weights[index]){
        capacity -= weights[index];
        itemCount++, index++;
    }
    return itemCount;
}


int main() {
    int capacity = 10;
    vector<int> weights = {4, 3, 5, 9, 1, 7};
    cout << "The number of optimal loadable item: " << optimalLoading(capacity, weights) << endl;

    return 0;
}