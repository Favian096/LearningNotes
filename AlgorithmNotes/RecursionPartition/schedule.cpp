/**
 * 循环赛日程表
 */
#include<iostream>
#include <iomanip>
#include<vector>

/**
 * 展示日程表
 * @param table
 */
void showTable(std::vector<std::vector<int>> &table) {
    for (auto &row : table) {
        for (auto element : row) {
            std::cout << std::setw(2) << element << " ";
        }
        std::cout << std::endl;
    }
}


/**
 * 对角交换区块
 * @param table
 * @param srcRow
 * @param srcCol
 * @param size
 */
void exchangePosition(std::vector<std::vector<int>> &table, int srcRow, int srcCol, int size) {
    for (int row = 0; row < size; ++row) {
        for (int col = 0; col < size; ++col) {
            table[srcRow + size + row][srcCol + size + col] = table[srcRow + row][srcCol + col];
            table[srcRow + size + row][srcCol + col] = table[srcRow + row][srcCol + size + col];
        }
    }
}

/**
 * 根据人数计算日程表
 * @param table
 * @param num
 */
void schedule(std::vector<std::vector<int>> &table, int num) {
    //初始化首行元素
    for (int firstRowElement = 0; firstRowElement < num; firstRowElement++) {
        table[0][firstRowElement] = firstRowElement + 1;
    }
    //变更交换块大小和列索引位置
    int colIndex = 2;
    for (int size = 1; size <= num >> 1; size <<= 1) {
        for (int block = 0; block < num; block += colIndex) {
            exchangePosition(table, 0, block, size);
        }
        colIndex <<= 1;
    }
}

int main() {
    int num = 8;
    std::vector<std::vector<int>> table(num, std::vector<int>(num, 0));

    schedule(table, num);
    showTable(table);

    return 0;
}
