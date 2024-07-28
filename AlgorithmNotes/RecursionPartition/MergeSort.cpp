/**
* 合并排序
*/

#include <iostream>
#include <vector>

/**
 * 合并数组
 * @tparam T
 * @param arr
 * @param left
 * @param mid
 * @param right
 */
template<typename T>
void mergeArray(std::vector<T> &arr, int left, int mid, int right) {
    std::vector<T> temp(right - left + 1);

    int leftIndex = left, rightIndex = mid + 1, tempIndex = 0;

    while (leftIndex <= mid && rightIndex <= right) {
        if (arr[leftIndex] <= arr[rightIndex]) {
            temp[tempIndex++] = arr[leftIndex++];
        } else {
            temp[tempIndex++] = arr[rightIndex++];
        }
    }

    while (leftIndex <= mid) {
        temp[tempIndex++] = arr[leftIndex++];
    }

    while (rightIndex <= right) {
        temp[tempIndex++] = arr[rightIndex++];
    }

    for (int mergeIndex = 0; mergeIndex < temp.size(); mergeIndex++) {
        arr[left + mergeIndex] = temp[mergeIndex];
    }
}

/**
 * 归并排序
 * @tparam T
 * @param arr
 * @param left
 * @param right
 */
template<typename T>
void mergeSort(std::vector<T> &arr, int left, int right) {
    if (left >= right)
        return;

    int mid = (left + right) >> 1;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    mergeArray(arr, left, mid, right);
}


int main() {
    std::vector<int> arr = {3, 4, 7, 8, 5, 1, 9, 0, 2, 6, 8, 8, 8};
    mergeSort(arr, 0, int(arr.size()) - 1);

    for (auto element : arr) {
        std::cout << element << " ";
    }
    return 0;
}

