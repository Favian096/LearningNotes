/**
* 线性时间选择: 第k小的元素
*/

#include<iostream>
#include<vector>
#include<random>
#include <ctime>

/**
 * 交换两数位置
 */
template<typename T>
void swap(T &src, T &tar) {
    T buf = src;
    src = tar;
    tar = buf;
}

/**
 * 产生指定范围的随机整数
 */
int randomInt(int start, int end) {
    srand((unsigned) time(nullptr));
    return start + rand() % (end - start);
}

/**
 * 设置基数左边比基数小, 右边比基数大
 *
 * @tparam T
 * @param arr
 * @param start
 * @param end
 * @return
 */
template<typename T>
int partition(std::vector<T> &arr, int start, int end) {
    T temp = arr[start];
    int leftIndex = start, rightIndex = end + 1;
    while (true) {
        while (leftIndex < end && arr[++leftIndex] <= temp);

        while (rightIndex > start && arr[--rightIndex] > temp);

        if (leftIndex >= rightIndex)
            break;

        swap(arr[leftIndex], arr[rightIndex]);
    }
    arr[start] = arr[rightIndex];
    arr[rightIndex] = temp;
    return rightIndex;
}

/**
 * 随机指定一个元素作为基数
 */
template<typename T>
int randomizedPartition(std::vector<T> &arr, int start, int end) {
    swap(arr[start], arr[randomInt(start, end)]);
    partition(arr, start, end);
}

/**
 * randomizedSelect算法实现
 * @tparam T
 * @param arr
 * @param start
 * @param end
 * @param k
 * @return
 */
template<typename T>
T randomizedSelect(std::vector<T> &arr, int start, int end, int k) {
    if (start == end) {
        return arr[start];
    }
    int tar = randomizedPartition(arr, start, end);
    if (tar == k) {
        return arr[tar];
    }
    if (tar > k) {
        randomizedSelect(arr, start, tar, k);
    } else {
        randomizedSelect(arr, tar + 1, end, k - tar);
    }
}

//-----------------------------------select实现----------------------------------
int n, k, len;

void Swap(int &a, int &b) {
    int temp;
    temp = a;
    a = b;
    b = temp;
}

void SelectSort(int a[], int p, int r) {
    for (int i = p; i < r; ++i) {
        int index = i;
        for (int j = i + 1; j <= r; ++j) {
            if (a[j] < a[index]) {
                index = j;
            }
        }
        Swap(a[i], a[index]);
    }
}

int Partition(int a[], int p, int r, int x) {
    //i指向首元素的前一个位置，j指向尾元素的后一个位置
    int i = p - 1, j = r + 1;
    while (1) {
        //i从基准数右边的元素开始找，直到找到第一个大于等于基准数的元素
        while (a[++i] < x && i < r);
        //j从尾元素开始找，直到找到第一个小于等于基准数的元素
        while (a[--j] > x && j > p);
        //若i>=j，说明基准数的位置已找到，为j
        if (i >= j) {
            break;
        }
        //交换两个元素，使得基准数左边的数均不大于它，右边的数均不小于它
        Swap(a[i], a[j]);
    }
    //返回基准数的位置
    return j;
}

int SearchMid(int a[], int p, int r) {
    //建立与数组a同等大小的数组b
    int *b = new int[r - p + 1];
    //用数组b存放数组a（注意此时b的首地址为0，而a的首地址为p）
    for (int i = p; i <= r; ++i) {
        b[i - p] = a[i];
    }
    //将数组b排序，b[(r-p+1)/2]为中位数
    SelectSort(b, 0, r - p);
    for (int i = p; i <= r; ++i) {
        if (a[i] == b[(r - p + 1) / 2]) {
            return i;
        }
    }
    delete[]b;
    return 0;
}

int Select(int a[], int p, int r, int k) {
    if (r - p < 5) {
        SelectSort(a, p, r);
        return a[p + k - 1];
    }
    //分成n/5组，每组5个，找到每组的中位数并将它放到数组首元素的位置
    for (int i = 0; i <= (r - p - 4) / 5; ++i) {
        int mid = SearchMid(a, p + 5 * i, p + 5 * i + 4);
        Swap(a[mid], a[p + i]);
    }
    //找到各组中位数的中位数
    int x = Select(a, p, p + (r - p - 4) / 5, (r - p - 4) / 10 + 1);
    //按照中位数划分
    int i = Partition(a, p, r, x);
    //求较小数数组的长度
    len = i - p + 1;
    //若较小数数组的长度小于等于k，说明第k小的元素在这个数组内，将其递归
    if (k <= len) {
        return Select(a, p, i, k);
    }
        //否则，说明第k小的元素在较大数数组，将其递归
    else {
        return Select(a, i + 1, r, k - len);
    }

}

int main() {
    std::vector<int> arr = {3, 8, 4, 7, 5, 1, 9, 0, 2, 6};
    //RandomizedSelect
    std::cout << randomizedSelect(arr, 0, int(arr.size()) - 1, 4) << std::endl;

    //Select实现
    int array[] = {3, 8, 4, 7, 5, 1, 9, 0, 2, 6};
    std::cout << Select(array, 0, 9, 4) << std::endl;


    return 0;
}