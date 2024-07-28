#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <limits>

using namespace std;

//定义点
struct Point {
    double x, y;
};

// 比较函数，用于按照x坐标排序点集
bool compareX(Point a, Point b) {
    return a.x < b.x;
}

// 比较函数，用于按照y坐标排序点集
bool compareY(Point a, Point b) {
    return a.y < b.y;
}

// 计算两点之间的距离
double distance(Point p1, Point p2) {
    return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
}

// 暴力方法计算最接近点对的距离和点对
pair<double, pair<Point, Point>> bruteForce(vector<Point> &points, int left, int right) {
    double minDist = numeric_limits<double>::max();
    pair<Point, Point> closestPair;

    for (int i = left; i < right; i++) {
        for (int j = i + 1; j < right; j++) {
            double dist = distance(points[i], points[j]);
            if (dist < minDist) {
                minDist = dist;
                closestPair = make_pair(points[i], points[j]);
            }
        }
    }

    return make_pair(minDist, closestPair);
}

// 分治算法，用于计算最接近点对的距离和点对
pair<double, pair<Point, Point>>
closestPairUtil(vector<Point> &pointsByX, vector<Point> &pointsByY, int left, int right) {
    if (right - left <= 3) {
        // 如果点的数量小于等于3个，使用暴力方法计算最接近点对的距离和点对
        return bruteForce(pointsByX, left, right);
    }

    int mid = (left + right) / 2; // 中间点的索引
    Point midPoint = pointsByX[mid];

    // 分别在左右子集中递归计算最接近点对的距离和点对
    pair<double, pair<Point, Point>> leftPair = closestPairUtil(pointsByX, pointsByY, left, mid);
    pair<double, pair<Point, Point>> rightPair = closestPairUtil(pointsByX, pointsByY, mid + 1, right);

    // 取左右子集的最小距离和点对
    pair<double, pair<Point, Point>> minPair = (leftPair.first < rightPair.first) ? leftPair : rightPair;
    double minDist = minPair.first;

    // 创建一个临时向量，用于存储距离中线不超过minDist的点
    vector<Point> strip;
    for (int i = left; i < right; i++) {
        if (abs(pointsByY[i].x - midPoint.x) < minDist) {
            strip.push_back(pointsByY[i]);
        }
    }

    // 在strip中查找更小的距离
    int stripSize = strip.size();
    for (int i = 0; i < stripSize; i++) {
        for (int j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < minDist; j++) {
            double dist = distance(strip[i], strip[j]);
            if (dist < minDist) {
                minDist = dist;
                minPair = make_pair(minDist, make_pair(strip[i], strip[j]));
            }
        }
    }

    return minPair;
}

// 主函数，用于计算最接近点对的距离和点对
pair<double, pair<Point, Point>> closestPair(vector<Point> &points) {
    int n = points.size();

    // 根据x坐标和y坐标分别对点集进行排序
    vector<Point> pointsByX = points;
    vector<Point> pointsByY = points;
    sort(pointsByX.begin(), pointsByX.end(), compareX);
    sort(pointsByY.begin(), pointsByY.end(), compareY);

    return closestPairUtil(pointsByX, pointsByY, 0, n);
}

int main() {
    vector<Point> points = {{1, 2},
                            {3, 4},
                            {5, 6},
                            {7, 8},
                            {9, 10}};

    pair<double, pair<Point, Point>> result = closestPair(points);
    double minDist = result.first;
    pair<Point, Point> closestPair = result.second;

    cout << "closest distance : " << minDist << endl;
    cout << "closest points: (" << closestPair.first.x << ", " << closestPair.first.y
         << ") and ("
         << closestPair.second.x
         << ", " << closestPair.second.y << ")" << endl;

    return 0;
}
