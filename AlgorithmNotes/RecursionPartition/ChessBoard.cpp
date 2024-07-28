/**
* 磁盘覆盖问题
*/

#include <iostream>
#include <iomanip>

//定义全局变量
#define BOARDSIZE 8
int tile = 1;        // 骨牌序号
int board[BOARDSIZE][BOARDSIZE]; // 二维数组模拟棋盘

/**
 * 递归分治
 *
 * (tr,tc)表示棋盘的左上角坐标(即确定棋盘位置)
 * (dr,dc)表示特殊方块的位置
 *  size=2^k确定棋盘大小
 * */
void chessBoard(int tr, int tc, int dr, int dc, int size) {
    if (size == 1) {
        return;
    }

    int s = size / 2; //分割棋盘
    int t = tile++;   //t记录本层骨牌序号

    // 判断特殊方格在不在左上棋盘
    if (dr < tr + s && dc < tc + s) {
        chessBoard(tr, tc, dr, dc, s);
    } else {
        board[tr + s - 1][tc + s - 1] = t;             // 用t号的L型骨牌覆盖右下角
        chessBoard(tr, tc, tr + s - 1, tc + s - 1, s); // 递归覆盖其余方格
    }

    // 判断特殊方格在不在右上棋盘
    if (dr < tr + s && dc >= tc + s) {
        chessBoard(tr, tc + s, dr, dc, s);
    } else {
        board[tr + s - 1][tc + s] = t;
        chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
    }

    // 判断特殊方格在不在左下棋盘
    if (dr >= tr + s && dc < tc + s) {
        chessBoard(tr + s, tc, dr, dc, s);
    } else {
        board[tr + s][tc + s - 1] = t;
        chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
    }

    // 判断特殊方格在不在右下棋盘
    if (dr >= tr + s && dc >= tc + s) {
        chessBoard(tr + s, tc + s, dr, dc, s);
    } else {
        board[tr + s][tc + s] = t;
        chessBoard(tr + s, tc + s, tr + s, tc + s, s);
    }
}

int main() {
    chessBoard(0, 0, 4, 5, BOARDSIZE);

    for (auto &row : board) {
        for (auto value : row) {
            if (value < 10) {
                std::cout << std::setw(2) << 0 << value << " ";
            } else {
                std::cout << std::setw(3) << value << " ";
            }
        }
        std::cout << std::endl;
    }

    return 0;
}
