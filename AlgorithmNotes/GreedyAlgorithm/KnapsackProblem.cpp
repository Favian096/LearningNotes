/**
* 背包问题
*/

#include<iostream>
#include<iomanip>
#include<utility>
#include<algorithm>
#include<vector>

using namespace std;

struct Item {
    int weight, value;

    Item(int wight, int value) {
        this->weight = wight;
        this->value = value;
    }
};

/**
 * 贪心计算最大背包价值和物品转载
 * @param capacity 背包容量
 * @param items 物品集合
 * @return 最大容量和物品
 */
pair<double, vector<double>> knapsack(int capacity, std::vector<Item> &items) {
    double value = 0;
    std::vector<double> itemRatio(items.size(), 0);
    sort(items.begin(), items.end(),
         [](Item a, Item b) { return a.value / a.weight > b.value / b.weight; });

    for (int index = 0; index < items.size(); index++) {
        if (items[index].weight > capacity) {
            double ratio = double(capacity) / items[index].weight;
            itemRatio[index] = ratio;
            value += ratio * items[index].value;
            break;
        }
        itemRatio[index] = 1.0;
        value += items[index].value;
        capacity -= items[index].weight;
    }
    return std::pair<int, vector<double>>(value, itemRatio);
}

int main() {
    //背包容量
    int capacity = 143;
    //物品重量和价值
    vector<Item> items = {
            Item(35, 10),
            Item(30, 40),
            Item(60, 30),
            Item(50, 50),
            Item(40, 35),
            Item(10, 40),
            Item(25, 30)
    };

    pair<double, vector<double>> result = knapsack(capacity, items);

    cout << "Max value of knapsack: " << result.first << endl;

    cout << "Ratio of Each item to be placed is as follows: " << endl;
    for (int index = 0; index < items.size(); index++) {
        cout << "weight: " << setw(3) << items[index].weight << ", ratio: " << result.second[index] << endl;
    }

    return 0;
}