/**
* 快速排序
*/

#include<iostream>
#include<vector>

/**
 * 快速排序递归实现
 * @tparam T
 * @param arr
 * @param left
 * @param right
 */
template<typename T>
void quickSort(std::vector<T> &arr, int left, int right) {
    if (left >= right) {
        return;
    }

    T temp = arr[left];
    int start = left, end = right;

    while (start < end) {
        while (start < end && arr[end] >= temp) {
            end--;
        }
        arr[start] = arr[end];

        while (start < end && arr[start] <= temp) {
            start++;
        }
        arr[end] = arr[start];
    }

    arr[start] = temp;

    quickSort(arr, left, start - 1);
    quickSort(arr, start + 1, right);
}

int main() {
    std::vector<int> arr = {3, 8, 4, 7, 8, 5, 1, 9, 0, 2, 6};

    quickSort(arr, 0, int(arr.size()) - 1);

    for (auto element : arr) {
        std::cout << element << " ";
    }

    return 0;
}