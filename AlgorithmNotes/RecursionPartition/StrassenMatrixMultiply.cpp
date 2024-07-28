/**
 * 实现Strassen矩阵乘法
 */

#include <iostream>
#include <vector>

// 矩阵加法
std::vector<std::vector<int>>
matrixAdd(const std::vector<std::vector<int>> &A, const std::vector<std::vector<int>> &B) {
    // 获取矩阵大小
    int n = A.size();
    // 初始化结果矩阵
    std::vector<std::vector<int>> result(n, std::vector<int>(n, 0));

    // 遍历矩阵元素，执行加法操作
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            result[i][j] = A[i][j] + B[i][j];
        }
    }

    return result;
}

// 矩阵减法
std::vector<std::vector<int>>
matrixSub(const std::vector<std::vector<int>> &A, const std::vector<std::vector<int>> &B) {
    // 获取矩阵大小
    int n = A.size();
    // 初始化结果矩阵
    std::vector<std::vector<int>> result(n, std::vector<int>(n, 0));

    // 遍历矩阵元素，执行减法操作
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            result[i][j] = A[i][j] - B[i][j];
        }
    }

    return result;
}

/**
 * Strassen矩阵乘法的递归函数
 *
 * @param A
 * @param B
 * @return
 */
std::vector<std::vector<int>> strassenMatrixMultiply(const std::vector<std::vector<int>> &A,
                                                     const std::vector<std::vector<int>> &B) {
    // 获取矩阵大小
    int n = int(A.size());

    // 当矩阵大小为1时，直接进行乘法
    if (n == 1) {
        std::vector<std::vector<int>> result(1, std::vector<int>(1, 0));
        result[0][0] = A[0][0] * B[0][0];
        return result;
    }

    // 分割矩阵成四个子矩阵
    int mid = n / 2;
    std::vector<std::vector<int>> A11(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> A12(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> A21(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> A22(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> B11(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> B12(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> B21(mid, std::vector<int>(mid, 0));
    std::vector<std::vector<int>> B22(mid, std::vector<int>(mid, 0));

    // 将原始矩阵分割成四个子矩阵
    for (int i = 0; i < mid; ++i) {
        for (int j = 0; j < mid; ++j) {
            A11[i][j] = A[i][j];
            A12[i][j] = A[i][j + mid];
            A21[i][j] = A[i + mid][j];
            A22[i][j] = A[i + mid][j + mid];

            B11[i][j] = B[i][j];
            B12[i][j] = B[i][j + mid];
            B21[i][j] = B[i + mid][j];
            B22[i][j] = B[i + mid][j + mid];
        }
    }

    // 递归计算临时矩阵
    std::vector<std::vector<int>> P1 = strassenMatrixMultiply(A11, matrixSub(B12, B22));
    std::vector<std::vector<int>> P2 = strassenMatrixMultiply(matrixAdd(A11, A12), B22);
    std::vector<std::vector<int>> P3 = strassenMatrixMultiply(matrixAdd(A21, A22), B11);
    std::vector<std::vector<int>> P4 = strassenMatrixMultiply(A22, matrixSub(B21, B11));
    std::vector<std::vector<int>> P5 = strassenMatrixMultiply(matrixAdd(A11, A22), matrixAdd(B11, B22));
    std::vector<std::vector<int>> P6 = strassenMatrixMultiply(matrixSub(A12, A22), matrixAdd(B21, B22));
    std::vector<std::vector<int>> P7 = strassenMatrixMultiply(matrixSub(A11, A21), matrixAdd(B11, B12));

    // 计算结果矩阵
    std::vector<std::vector<int>> C11 = matrixAdd(matrixSub(matrixAdd(P5, P4), P2), P6);
    std::vector<std::vector<int>> C12 = matrixAdd(P1, P2);
    std::vector<std::vector<int>> C21 = matrixAdd(P3, P4);
    std::vector<std::vector<int>> C22 = matrixSub(matrixSub(matrixAdd(P5, P1), P3), P7);

    // 合并子矩阵为结果矩阵
    std::vector<std::vector<int>> result(n, std::vector<int>(n, 0));
    for (int i = 0; i < mid; ++i) {
        for (int j = 0; j < mid; ++j) {
            result[i][j] = C11[i][j];
            result[i][j + mid] = C12[i][j];
            result[i + mid][j] = C21[i][j];
            result[i + mid][j + mid] = C22[i][j];
        }
    }

    return result;
}

int main() {
    // 示例矩阵
    std::vector<std::vector<int>> A = {{1, 2},
                                       {3, 4}};
    std::vector<std::vector<int>> B = {{5, 6},
                                       {7, 8}};

    // 计算矩阵乘积
    std::vector<std::vector<int>> result = strassenMatrixMultiply(A, B);

    // 输出结果
    std::cout << "Resultant Matrix:" << std::endl;
    for (const auto &row : result) {
        for (int element : row) {
            std::cout << element << " ";
        }
        std::cout << std::endl;
    }

    return 0;
}
