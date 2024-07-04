/**
* 01背包问题
*/

#include <iostream>
#include <vector>

using namespace std;

int knapsack(int c, vector<int> &w, vector<int> &v) {
    w.insert(w.begin(), 1, 0);
    v.insert(v.begin(), 1, 0);
    vector<vector<int>> m(w.size(), vector<int>(c + 1, 0));

    for (int index = 1; index < w.size(); index++) {
        for (int cTmp = 1; cTmp <= c; cTmp++) {
            //放不进去
            if (w[index] > cTmp) {
                m[index][cTmp] = m[index - 1][cTmp];
            } else {
                // 该物品的价值 + 不放入该物品剩余空间的最大价值
                int into = v[index] + m[index - 1][cTmp - w[index]];
                if (into > m[index - 1][cTmp]) {
                    m[index][cTmp] = into;
                } else {
                    m[index][cTmp] = m[index - 1][cTmp];
                }
            }
        }
    }
    return m[w.size() - 1][c];
}


int main() {
//    背包容量c
    int c = 20;
    vector<int> w = {2, 3, 4, 5, 9},
            v = {3, 4, 5, 8, 10};
    cout << "max value: " << knapsack(c, w, v);

    return 0;
}