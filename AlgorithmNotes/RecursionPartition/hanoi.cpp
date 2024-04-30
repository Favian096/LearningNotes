/**
* 汉诺塔问题
*/

#include<iostream>
#include<vector>


void move(std::vector<int> &src, std::vector<int> &tar) {
    tar.push_back(src.back());
    src.pop_back();
}

void hanoi(int n, std::vector<int> &src, std::vector<int> &tar, std::vector<int> &buf) {
    if (1 == n) {
        move(src, tar);
        return;
    }
    hanoi(n - 1, src, buf, tar);
    move(src, tar);
    hanoi(n - 1, buf, tar, src);
}


int main() {
//    使用数组实现
    std::vector<int> src = {1, 2, 3}, tar, buf;
    hanoi((int) src.size(), src, tar, buf);

    auto print = [](const std::vector<int> &arr) {
        std::cout << "=> ";
        for (auto element : arr) {
            std::cout << element << " ";
        }
        std::cout << std::endl;
    };

    print(tar);
    return 0;
}
