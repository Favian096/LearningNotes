/**
* 流水作业调度
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Job {
public:
    int key, index;
    bool job;
};

/**
 * 计算最短时间
 * @param a m1每项耗时
 * @param b m2每项耗时
 * @param c
 * @return
 */
int flowShop(vector<int> a, vector<int> b, vector<int> c) {
    int j, k, n = a.size();
    vector<Job> d(n);

    for (int i = 0; i < n; i++) {
        d[i].index = i;
        if (a[i] < b[i]) {
            d[i].job = true;
            d[i].key = a[i];
        } else {
            d[i].job = false;
            d[i].key = b[i];
        }
    }

    sort(d.begin(), d.end(), [](Job a, Job b) -> bool { return a.key < b.key; });

    j = 0, k = n - 1;
    for (int i = 0; i < n; i++) {
        if (d[i].job == true)
            c[j++] = d[i].index;
        else
            c[k--] = d[i].index;
    }
    j = a[c[0]];
    k = j + b[c[0]];
    for (int i = 1; i < n; i++) {
        j = j + a[c[i]];
        k = j < k ? k + b[c[i]] : j + b[c[i]];
    }
    return k;
}

int main() {
    vector<int> a = {5, 3, 6, 4, 8, 9, 6},
            b = {2, 4, 7, 2, 9, 7, 3}, c(a.size(), 0);
    cout << flowShop(a, b, c);
    return 0;
}
