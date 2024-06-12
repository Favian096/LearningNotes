/**
* 多边形游戏
*/

#include <iostream>
#include <utility>
#include <vector>
#include <string>
#include <limits>

/**
 * 计算左右子链最值
 *
 * @param delBord 被删除的边的位置
 * @param size 子链大小
 * @param off 断开位置
 * @param minTmp 最小值tmp
 * @param maxTmp 最大值tmp
 * @param op 运算符数组
 * @param minMaxVal 最大最小值计算数组
 */
void minMax(int delBord, int size, int off, int &minTmp, int &maxTmp,
            int length, std::string &op,
            std::vector<std::vector<std::vector<int>>> &minMaxVal) {

    int tmp[5], r = (delBord + off - 1) % length + 1;
    int a = minMaxVal[delBord][off][0], b = minMaxVal[delBord][off][1],
            c = minMaxVal[r][size - off][0], d = minMaxVal[r][size - off][1];

    if (op[off - 1] == '+') {
        minTmp = a + c;
        maxTmp = b + d;
    } else {
        tmp[1] = a * c;
        tmp[2] = a * d;
        tmp[3] = b * c;
        tmp[4] = b * d;

        minTmp = tmp[1];
        maxTmp = tmp[1];

        for (int index = 2; index < 5; index++) {
            if (tmp[index] < minTmp) {
                minTmp = tmp[index];
            }
            if (tmp[index] > maxTmp) {
                maxTmp = tmp[index];
            }
        }

    }


}

/**
 * 枚举多边形链路长度和断开位置
 *
 * @param arr 节点值
 * @param op 此位置运算符
 * @return 最大值
 */
std::pair<int, int> polygonMax(std::vector<int> &arr, std::string &op) {
    int length = arr.size() - 1, minTmp = INT_MAX, maxTmp = INT_MIN;
    std::vector<std::vector<std::vector<int>>>
            minMaxVal(length + 1, std::vector<std::vector<int>>(length + 1, std::vector<int>(2, 0)));

    for (int index = 1; index <= length; index++) {
        minMaxVal[index][1][0] = arr[index];
        minMaxVal[index][1][1] = arr[index];
    }

    //枚举链长度
    for (int size = 2; size <= length; size++) {
        //枚举被删除的边位置
        for (int delBord = 1; delBord <= length; delBord++) {
            //枚举断开位置
            for (int off = 1; off <= size - 1; off++) {
                minMax(delBord, size, off, minTmp, maxTmp, length, op, minMaxVal);

                if (minTmp < minMaxVal[delBord][size][0]) {
                    minMaxVal[delBord][size][0] = minTmp;
                }
                if (maxTmp > minMaxVal[delBord][size][1]) {
                    minMaxVal[delBord][size][1] = maxTmp;
                }
            }
        }
    }

    std::pair<int, int> bestVal(1, minMaxVal[1][length][1]);
    for (int index = 2; index <= length; index++) {
        if (minMaxVal[index][length][1] > bestVal.second) {
            bestVal.first = index - 1;
            bestVal.second = minMaxVal[index][length][1];
        }
    }
    return bestVal;
}

int main() {
    std::vector<int> arr = {0, 5, -7, 4, 2};
    std::string op("#++**");
    std::pair<int, int> bestVal = polygonMax(arr, op);

    std::cout << "best off position : " << bestVal.first << std::endl << "max value : " << bestVal.second;

    return 0;
}
