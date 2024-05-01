/**
* 二分搜索
*/

#include <iostream>

/**
 * 返回搜索到的数据在数组中索引
 * 数组需要已排序(小->大)
 *
 * @tparam T
 * @param arr
 * @param left
 * @param right
 * @param tar
 * @return
 */
template<typename T>
int binarySearch(T arr[], int left, int right, T tar) {
    if (left > right) {
        return -1;
    }
    int middle = (left + right) / 2;
    if (arr[middle] == tar) {
        return middle;
    }
    if (arr[middle] < tar) {
        binarySearch(arr, middle + 1, right, tar);
    } else {
        binarySearch(arr, left, middle - 1, tar);
    }

}

int main() {
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    std::cout << binarySearch(arr, 0, 9, 88) << std::endl;
    return 0;
}
