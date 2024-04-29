/**
* 数组全排列问题
*/

#include <iostream>

/**
 * 交换两数
 * @tparam T
 * @param a
 * @param b
 */
template<typename T>
void swap(T &a, T &b) {
    T temp = a;
    a = b;
    b = temp;
}

/**
 * 全排列
 * @tparam T
 * @param list
 * @param index
 * @param length
 * @return
 */
template<typename T>
int perm(T list[], int index, int length) {
    if (index == length - 1) {
        for (int i = 0; i < length; i++) {
            std::cout << list[i] << " ";
        }
        std::cout << std::endl;
    } else {
        for (int front = index; front < length; front++) {
            swap(list[index], list[front]);
            perm(list, index + 1, length);
            swap(list[index], list[front]);
        }
    }
}

int main() {
    int list[] = {1, 2, 3};
    int length = sizeof(list) / sizeof(list[0]);
    auto cases = [](int length) {
        int cases = 1;
        for (int i = 0; i < length; i++) {
            cases *= 2;
        }
        return cases;
    };
    std::cout << cases(length) << " cases: " << std::endl;
    perm(list, 0, length);
    return 0;
}
