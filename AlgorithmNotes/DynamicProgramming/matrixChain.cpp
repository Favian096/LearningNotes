/**
* 矩阵连乘
*/

#include <iostream>
#include <vector>
#include <climits>
#include <iomanip>

using namespace std;

/**
 * 回溯输出最优乘法顺序
 * @param i
 * @param j
 * @param n
 * @param disPosition
 * @param t
 */
void MatrixChain_Traceback(int i, int j, int n, const vector<vector<int>> &disPosition, vector<int> &t) {
    if (i == j) return;
    int k = disPosition[i][j];
    MatrixChain_Traceback(i, k, n, disPosition, t);
    MatrixChain_Traceback(k + 1, j, n, disPosition, t);
    if (i != k) {
        t[i * 2]++;
        t[k * 2 + 1]++;
    }
    if (k + 1 != j) {
        t[k * 2 + 2]++;
        t[j * 2 + 1]++;
    }
}

/**
 * 输出最优乘法顺序
 * @param n
 * @param t
 */
void MatrixChain_PrintResult(int n, const vector<int> &t) {
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < t[i * 2]; k++)
            cout << '(';
        cout << 'A' << i + 1;
        for (int k = 0; k < t[i * 2 + 1]; k++)
            cout << ')';
    }
    cout << endl;
}

/**
 * 计算矩阵连乘的顺序 和 最小乘法次数
 * @param matrices
 */
void matrixChain(std::vector<vector<int>> matrices) {
    std::vector<vector<int>> multiplyNum(matrices.size(), vector<int>(matrices.size(), 0));
    std::vector<vector<int>> disPosition(matrices.size(), vector<int>(matrices.size(), 0));

//    for (int r = 2; r <= matrices.size(); ++r) {
//        for (int begin = 0; begin <= matrices.size() - r; ++begin) {
//            int end = begin + r - 1;
//            multiplyNum[begin][end] = INT_MAX;
//            for (int position = begin; position < end; ++position) {
//                int multiplyTimes = multiplyNum[begin][position] + multiplyNum[position + 1][end] +
//                                    matrices[begin][0] * matrices[position][1] * matrices[end][1];
//                if (multiplyTimes < multiplyNum[begin][end]) {
//                    multiplyNum[begin][end] = multiplyTimes;
//                    disPosition[begin][end] = position;
//                }
//            }
//        }
//    }

    //从矩阵个数为两个开始枚举
    for (int matrixNum = 1; matrixNum < matrices.size(); matrixNum++) {
        //枚举开始位置的矩阵
        for (int begin = 0; begin <= matrices.size() - matrixNum - 1; begin++) {
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

    // 输出最小乘法次数
    cout << "Minimal amount of computation = " << multiplyNum[0][matrices.size() - 1] << endl;
    // 输出最优乘法顺序
    cout << "Optimal multiplication order : ";
    vector<int> t(matrices.size() * 2 + 2, 0);
    MatrixChain_Traceback(0, matrices.size() - 1, matrices.size(), disPosition, t);
    MatrixChain_PrintResult(matrices.size(), t);

}


int main() {
    //初始化6个矩阵: 30*35 35*15 15*5 5*10 10*20 20*25
    std::vector<vector<int>> matrices = {
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
