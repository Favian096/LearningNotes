/**
* 图像压缩
*/

#include <iostream>
#include <vector>
#include <algorithm>

/**
 * 计算该数字存储需要占用几位
 *
 * @param num 数字
 * @return 占用位数
 */
int maxBit(int num) {
    int bit = 1;
    while (num >>= 1 != 0) {
        bit++;
    }
    return bit;
}

int sqeMaxBit(std::vector<int> arr, int begin, int end) {
    int maxNum = arr[begin];
    while (begin != end) {
        maxNum = std::max(arr[begin + 1], maxNum);
        begin++;
    }
    return maxBit(maxNum);
}

int compress(std::vector<int> &graySqe) {
    int length = graySqe.size();
    graySqe.insert(graySqe.begin(), 1, 0);

    std::vector<int> s(length + 1, 0), b(length + 1, 0), l(length + 1, 0);

    for (int len = 1; len <= length; len++) {
        int maxBitTmp = maxBit(graySqe[len]);
        s[len] = s[len - 1] + maxBitTmp + 11;
        b[len] = maxBitTmp;
        l[len] = 1;

        for (int reIndex = 2; reIndex <= std::min(len, 255); reIndex++) {
            int bitJ = sqeMaxBit(graySqe, len - reIndex + 1, len);
            int t = s[len - reIndex] + bitJ * reIndex + 11;
            if (t < s[len]) {
                s[len] = t;
                b[len] = bitJ;
                l[len] = reIndex;
            }
        }

    }

    return s[length];
}


int main() {
    std::vector<int> graySequence = {10, 12, 15, 255, 1, 2};
    std::cout << "minimum require bits : " << compress(graySequence);

    return 0;
}


