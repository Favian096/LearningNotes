/***
 * 阶乘函数的实现
 */
# include <iostream>

using namespace std;

/**
 * 递归实现
 * @param n
 * @return
 */
int rFactorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * rFactorial(n - 1);
}

/**
 * 非递归实现
 * @param n
 * @return
 */
int nrFactorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}

int main() {
    int n = 5;
    cout << rFactorial(n) << endl;
    cout << nrFactorial(n) << endl;
    return 0;
}
