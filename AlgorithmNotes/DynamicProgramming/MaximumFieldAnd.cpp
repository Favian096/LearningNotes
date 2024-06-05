/**
* 最大字段和实现
*/


#include <iostream>
#include <vector>

/**
 * 简单实现
 * @param arr 源数组
 */
void maxSum(std::vector<int> &arr) {
    int maxSum = 0, bestLeft = 0, bestRight = 0;
    for (int left = 0; left < arr.size(); left++) {
        int currentSum = 0;
        for (int right = left; right < arr.size(); right++) {
            currentSum += arr[right];
            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestLeft = left;
                bestRight = right;
            }
        }
    }

    std::cout << "=> simple algo max sum : " << maxSum << " , best index: " << bestLeft << " " << bestRight
              << std::endl;
}

/**
 * 分治实现
 * =>将arr划分为左右两个子数组, 递归求解:
 * - maxSum在左边数组
 * - maxSum在右边数组
 * - maxSum在交接位置(计算从中间位置开始向左和向右的maxSum再相加即可)
 * @param arr 源数组
 * @param left 左指针
 * @param right 右指针
 * @return maxSum
 */
int maxSubSum(std::vector<int> &arr, int left, int right) {
    if (left == right) {
        return arr[left] > 0 ? arr[left] : 0;
    }
    int mid = (left + right) / 2;

    int leftMaxSubSum = maxSubSum(arr, left, mid);
    int rightMaxSubSum = maxSubSum(arr, mid + 1, right);

    int midToLeftSum = 0, currentMidToLeftSum = 0;
    for (int midToLeftIndex = mid; midToLeftIndex >= left; midToLeftIndex--) {
        currentMidToLeftSum += arr[midToLeftIndex];
        if (currentMidToLeftSum > midToLeftSum) {
            midToLeftSum = currentMidToLeftSum;
        }
    }

    int midToRightSum = 0, currentMidToRightSum = 0;
    for (int midToRightIndex = mid + 1; midToRightIndex <= right; midToRightIndex++) {
        currentMidToRightSum += arr[midToRightIndex];
        if (currentMidToRightSum > midToRightSum) {
            midToRightSum = currentMidToRightSum;
        }
    }

    int midMaxSubSum = midToLeftSum + midToRightSum;

    return midMaxSubSum > leftMaxSubSum ?
           midMaxSubSum > rightMaxSubSum ? midMaxSubSum : rightMaxSubSum :
           leftMaxSubSum > rightMaxSubSum ? leftMaxSubSum : rightMaxSubSum;
}

/**
 * 动态规划求解
 *
 * @param arr 源数组
 */
void maxDPSum(std::vector<int> &arr) {
    int sum = 0, temp = 0;
    for (int ele : arr) {
        if (temp > 0) {
            temp += ele;
        } else {
            temp = ele;
        }

        if (temp > sum) {
            sum = temp;
        }
    }
    std::cout << "=> dp algo max sum : " << sum << std::endl;
}

int main() {
    std::vector<int> arr = {-2, 1, -2, 11, -4, 13, -5, -2};
    maxSum(arr);
    std::cout << "=> sub algo max sum : " << maxSubSum(arr, 0, int(arr.size() - 1)) << std::endl;
    maxDPSum(arr);

    return 0;
}