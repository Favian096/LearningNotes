/**
 * 整数划分问题
 */

#include <iostream>

/**
 * 递归实现
 * 1.n 或 m 为 1 时, 只有一种划分
 * 2.n 和 m 相等时(>1), 有(最大划分等于n) 1 + divide(n, m - 1)中划分
 * 3.m 大于 n 时, 由于划分不大于n故有divide(n,n)中划分
 * 4.m 小于 n 时, 有当n = m + (n-m)的一个划分->也就是最大划分等于m时的划分数
 *               其中的(n-m)的divide(n-m, m)种划分(不大于m是为了不重复)
 *               加上divide(n, m - 1)种划分->也就是最大划分小于m的划分数
 *
 * @param n 整数n
 * @param m 划分的加数不大于m
 * @return 划分数
 */
int divide(int n, int m) {
    if (n == 1 || m == 1) {
        return 1;
    }
    if (n == m) {
        return 1 + divide(n, m - 1);
    }
    if (m > n) {
        return divide(n, n);
    }
    return divide(n, m - 1) + divide(n - m, m);
}

int main() {
    int n = 6;
    std::cout << divide(n, n) << std::endl;
    return 0;
}

