/**
* 大整数的乘法
*/

#include <iostream>
#include <cmath>

long bigNumMut(int x, int y, int n) {
    if (n == 0) {
        return long(0);
    }
    int sign = (x > 0 ? 1 : -1) * (y > 0 ? 1 : -1);
    x = abs(x);
    y = abs(y);
    if (n == 1) {
        return sign * x * y;
    }

    int half = n / 2;
    int A = x / int(pow(10, half));
    int B = x % int(pow(10, half));
    int C = y / int(pow(10, half));
    int D = y % int(pow(10, half));
    long AC = bigNumMut(A, C, half);
    long BD = bigNumMut(B, D, half);
    return sign * long(AC * pow(10, n)
                       + ((A - B) * (D - C) + AC + BD) * pow(10, half)
                       + BD);
}


int main() {
    int x = 123456;
    int y = 4321;
    std::cout << bigNumMut(x, y, 9) << std::endl;

    return 0;
}




