/**
* 凸多边形最优三角划分
*/

#include <iostream>
#include <iomanip>
#include <vector>
#include <climits>

/**
 * 最优三角划分
 * @param weight 权值
 */
void minWeightTriangulation(std::vector<std::vector<int>> &weight) {
    int points = weight.size();
    std::vector<std::vector<int>> minWeight(points, std::vector<int>(points, 0));
    std::vector<std::vector<int>> off(points, std::vector<int>(points, 0));

    for (int index = 0; index < points; index++) {
        minWeight[index][(index + 1) % points] = weight[index][(index + 1) % points];
    }

    for (int pointNum = 2; pointNum < points; pointNum++) {

        for (int begin = 0; begin < points - pointNum; begin++) {
            int end = begin + pointNum;
            minWeight[begin][end] = INT_MAX;

            for (int offPoint = begin + 1; offPoint < end; offPoint++) {
                int weightTemp =
                        minWeight[begin][offPoint] +
                        minWeight[offPoint][end] +
                        weight[begin][end];
                if (weightTemp < minWeight[begin][end]) {
                    minWeight[begin][end] = weightTemp;
                    off[begin][end] = offPoint;
                }
            }
        }
    }

    std::cout << "Minimum weight of triangulation is: " << minWeight[0][points - 1] << std::endl;
    for (const auto &row : minWeight) {
        for (int val : row) {
            std::cout << std::setw(4) << val;
        }
        std::cout << std::endl;
    }

    std::cout << "Optimal triangulation positions (off array): " << std::endl;
    for (const auto &row : off) {
        for (int val : row) {
            std::cout << std::setw(4) << val;
        }
        std::cout << std::endl;
    }

}

int main() {
    //各点之间的权值
    std::vector<std::vector<int>> weight = {
            {0, 2, 3,  1,  5,  6},
            {2, 0, 3,  4,  8,  6},
            {3, 3, 0,  10, 13, 7},
            {1, 4, 10, 0,  12, 5},
            {5, 8, 13, 12, 0,  3},
            {6, 6, 7,  5,  3,  0}
    };

    std::cout << "The weights of the sides and strings of a polygon : " << std::endl;
    for (auto &arr : weight) {
        for (auto ele : arr) {
            std::cout << std::setw(4) << ele;
        }
        std::cout << std::endl;
    }

    minWeightTriangulation(weight);

    return 0;
}