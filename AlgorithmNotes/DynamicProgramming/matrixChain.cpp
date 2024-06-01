/**
* 矩阵连乘
*/

#include <iostream>
#include <vector>
#include <climits>
#include<string>
#include <iomanip>

/**
 * 输出矩阵次序
 *
 * @param left 左指针
 * @param right 右指针
 * @param disconnection 断开位置
 * @return 连接次序
 */
std::string matrixChainResult(int left, int right, std::vector<std::vector<int>> &disconnection) {
    if (left == right) {
        return "A" + std::to_string(left + 1);
    }

    std::string leftChain = matrixChainResult(left, disconnection[left][right], disconnection);
    std::string rightChain = matrixChainResult(disconnection[left][right] + 1, right, disconnection);

    return "(" + leftChain + rightChain + ")";
}

/**
 * 计算矩阵连乘的顺序 和 最小乘法次数
 * @param matrices 连乘的矩阵(行列值)
 */
void matrixChain(std::vector<std::vector<int>> matrices) {
    std::vector<std::vector<int>> multiplyNum(matrices.size(), std::vector<int>(matrices.size(), 0));
    std::vector<std::vector<int>> disPosition(matrices.size(), std::vector<int>(matrices.size(), 0));

    //从矩阵个数为两个开始枚举
    for (int matrixNum = 1; matrixNum < matrices.size(); matrixNum++) {
        //枚举开始位置的矩阵
        for (int begin = 0; begin < matrices.size() - matrixNum; begin++) {
            //结束位置
            int end = begin + matrixNum;
            multiplyNum[begin][end] = INT_MAX;
            //计算乘法次数
            for (int position = begin; position < end; position++) {
                int calcMultiply =
                        multiplyNum[begin][position] +  //左边的最小次数
                        multiplyNum[position + 1][end] + //右边的最小次数
                        matrices[begin][0] * matrices[position][1] * matrices[end][1];
                if (calcMultiply < multiplyNum[begin][end]) {
                    multiplyNum[begin][end] = calcMultiply;
                    disPosition[begin][end] = position;
                }
            }
        }
    }

    std::cout << "-------- multiply number --------" << std::endl;
    for (auto &arr: multiplyNum) {
        for (auto ele: arr) {
            std::cout << std::setw(6) << ele;
        }
        std::cout << std::endl;
    }

    std::cout << "-------- disconnection position --------" << std::endl;
    for (auto &arr: disPosition) {
        for (auto ele: arr) {
            std::cout << std::setw(2) << ele;
        }
        std::cout << std::endl;
    }

    // 输出最小乘法次数
    std::cout << "=>Minimal amount of computation = " << multiplyNum[0][matrices.size() - 1] << std::endl;
    // 输出最优乘法顺序
    std::cout << "=>Optimal multiplication order : " << matrixChainResult(0, matrices.size() - 1, disPosition);
}


int main() {
    //初始化6个矩阵: 30*35 35*15 15*5 5*10 10*20 20*25
    std::vector<std::vector<int>> matrices = {
            {30, 35},
            {35, 15},
            {15, 5},
            {5,  10},
            {10, 20},
            {20, 25}
    };

    matrixChain(matrices);

    return 0;
}
