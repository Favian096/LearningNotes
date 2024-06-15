/**
* 电路布线
*/

#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

/**
 * 构造结果边线
 */
void tracePath(vector<vector<int>> &size) {
    int i = size.size() - 1, j = size.size() -1;
    while (size[i][j] != 0) {
        if (size[i][j] == size[i - 1][j]) {
            i--;
            continue;
        }
        if (size[i][j] == size[i][j - 1]) {
            j--;
            continue;
        }
        i--, j--;
        cout << "point " << i+1 << " to " << "point " << j+1 << endl;
    }

}

/**
 * 计算获取结果矩阵
 *
 * @param arr
 */
void MNS(vector<int> &arr) {
    int length = arr.size();
    vector<vector<int>> size(length, vector<int>(length, 0));

//    N(i, j) 中, i = 1时
    for (int i = arr[1]; i < length; i++) {
        size[1][i] = 1;
    }

//    N(i, j) 中, i > 1时
    for (int i = 2; i < length; i++) {
        for (int j = 1; j < arr[i]; ++j) {
            size[i][j] = size[i - 1][j];
        }
        for (int j = arr[i]; j < length; j++) {
            size[i][j] = max(size[i - 1][j], size[i - 1][arr[i] - 1] + 1);
        }
    }

    //结果输出
    cout << setw(3) << " ";
    for (int index = 1; index < length; index++) {
        cout << setw(3) << index;
    }
    cout << endl;
    for (int index = 1; index < length; index++) {
        cout << setw(3) << index;
        for (int valIndex = 1; valIndex < length; valIndex++) {
            cout << setw(3) << size[index][valIndex];
        }
        cout << endl;
    }

    tracePath(size);
}


int main() {
    vector<int> arr = {0, 8, 7, 4, 2, 5, 1, 9, 3, 10, 6};
    MNS(arr);

    return 0;
}