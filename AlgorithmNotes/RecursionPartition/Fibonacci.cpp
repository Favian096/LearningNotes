/**
 * Fibonacci数列的实现
 */
#include <iostream>

/**
 * 递归实现
 * @param n
 * @return
 */
int rFibonacci(int n) {
    if (n == 1) {
        return 1;
    }
    if (n == 2) {
        return 1;
    }
    return rFibonacci(n - 1) + rFibonacci(n - 2);
}

/**
 * 非递归实现
 * @param n
 * @return
 */
int nrFibonacci(int n) {
    int l1 = 1, l2 = 1, result = 0;
    if (n == 1 || n == 2) {
        return 1;
    }
    for (int index = 3; index <= n; index++) {
        result = l1 + l2;
        l1 = l2;
        l2 = result;
    }
    return result;
}


int main() {
    int n = 5;
    std::cout << rFibonacci(n) << std::endl;
    std::cout << nrFibonacci(n) << std::endl;
    return 0;
}