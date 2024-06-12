/**
* 多边形游戏
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define N 100
int n, v[N], m[N][N][2];
char op[N];

void MinMax(int n, int i, int k, int j, int &minf, int &maxf) {//最大值和最小值
    int e[5], r;
    int a = m[i][k][0], b = m[i][k][1];//a是前段链最小值，b是最大值
    r = (i + k - 1) % n + 1;//因为是循环可取余来做
    int c = m[r][j - k][0], d = m[r][j - k][1];//c是后段链最小值，d是最大值
    if (op[k - 1] == '+') {//加的话，最小相加即最小，最大相加即最大
        minf = a + c;
        maxf = b + d;
    } else {//乘可能会出现负负得正的情况，所以需要考虑四种情况
        e[1] = a * c;
        e[2] = a * d;
        e[3] = b * c;
        e[4] = b * d;
        minf = e[1];
        maxf = e[1];
        for (int t = 2; t <= 4; t++) {//遍历寻找最大最小
            if (maxf < e[t]) {
                maxf = e[t];
            }
            if (minf > e[t]) {
                minf = e[t];
            }
        }
    }
}

int main() {
    int i, j, k, minf, maxf;
    printf("请输入多边形顶点数：");
    scanf("%d", &n);

    for (i = 1; i <= n; i++) {
        printf("请输入%d边的操作数和运算符：", i);
        scanf("%d %c", &v[i], &op[i]);
        m[i][1][0] = v[i];
        m[i][1][1] = v[i];
    }

    for (j = 2; j <= n; j++) {
        for (i = 1; i <= n; i++) {//删除的边
            for (k = 1; k <= j - 1; k++) {//断链位置
                MinMax(n, i, k, j, minf, maxf);
                if (m[i][j][0] > minf) {
                    m[i][j][0] = minf;
                }
                if (m[i][j][1] < maxf) {
                    m[i][j][1] = maxf;
                }
            }
        }
    }
    int temp = m[1][n][1], p = 1;
    for (i = 2; i <= n; i++) {
        if (m[i][n][1] > temp) {
            temp = m[i][n][1];
            p = i;
        }
    }
    printf("首断链位置为：%d,最大得分为：%d\n", p, temp);
    return 0;
}
