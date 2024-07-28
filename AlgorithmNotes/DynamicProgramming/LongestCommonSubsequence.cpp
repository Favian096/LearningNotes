/**
* 最长公共子序列
*/

#include <iostream>
#include <iomanip>
#include <string>
#include <vector>

/**
 * 计算最长公共子序列
 *
 * @param str1 string
 * @param str2 string
 */
void LCS(std::string &str1, std::string &str2) {
    std::vector<std::vector<int>> path(str1.size() + 1, std::vector(str2.size() + 1, 0));

    for (int row = 1; row <= str1.size(); row++) {
        for (int col = 1; col <= str2.size(); col++) {
            if (str1[row - 1] == str2[col - 1]) {
                path[row][col] = path[row - 1][col - 1] + 1;
            } else {
                path[row][col] = std::max(path[row - 1][col], path[row][col - 1]);
            }
        }
    }

    {
        std::cout << "------------------------------------" << std::endl;
        std::cout << std::setw(6) << " ";
        for (auto ele:str2) {
            std::cout << std::setw(4) << ele;
        }
        std::cout << std::endl << std::setw(2) << " ";
        int index = 0;
        for (auto &arr : path) {
            for (auto ele: arr) {
                std::cout << std::setw(4) << ele;
            }
            std::cout << std::endl;
            std::cout << std::setw(2) << str1[index++];
        }
        std::cout << "------------------------------------" << std::endl;
    }


    std::cout << "=>longest Common Subsequence length : " << path[str1.size()][str2.size()];

    //记录最长公共子串
    std::string subsequence;
    for (int index = path[str1.size()][str2.size()], row = str1.size() - 1, col = str2.size() - 1; index > 0;) {
        if (str1[row] == str2[col]) {
            subsequence.insert(0, std::string(1, str1[row]));
            row--, col--, index--;
        } else {
            if (path[row][col + 1] == path[row + 1][col + 1]) {
                row--;
            } else {
                col--;
            }
            continue;
        }
    }
    std::cout << "\n=>longest Common Subsequence : " << subsequence;

}


int main() {
    std::string str1("ABCBDAB"), str2("BDCABA");

    LCS(str1, str2);

    return 0;
}