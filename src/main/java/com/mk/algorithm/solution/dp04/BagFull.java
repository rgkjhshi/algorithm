package com.mk.algorithm.solution.dp04;

public class BagFull {

    public static int solve(int[] w, int[] v, int N, int W) {
        // 创建备忘录
        int[][] dp = new int[N + 1][W + 1];
        // 初始化状态
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= W; j++) {
            dp[0][j] = 0;
        }
        /*
         * 状态参数
         * tn: 已经遍历过的物品；
         * rw: 背包还能容量的重量。
         */
        // 遍历每一个物品
        for (int tn = 1; tn <= N; tn++) {
            for (int rw = 1; rw <= W; rw++) {
                dp[tn][rw] = dp[tn - 1][rw];
                // 如果可以放入，则尝试放入第tn件物品
                if (w[tn] <= rw) {
                    // 完全背包不同点在于，状态转移方程是考虑的 tn-1 和 tn
                    dp[tn][rw] = Math.max(dp[tn][rw], dp[tn][rw - w[tn]] + v[tn]);
                }
                /**
                 * 这种解法实际上是转化成了01背包问题来解。
                for (int k = 0; k <= (rw / w[tn]); k++) {
                    // 类似于01背包，全都由 tn-1得来
                    dp[tn][rw] = Math.max(dp[tn][rw], dp[tn - 1][rw - k * w[tn]] + k * v[tn]);
                }
                 */
            }
        }

        return dp[N][W];
    }

    public static void main(String[] args) {
        // 物品的总数，背包能容纳的总重量
        int N = 3, W = 5;
        // 物品的重量
        int[] w = {0, 3, 2, 1};
        // 物品的价值
        int[] v = {0, 5, 2, 3};
        // 计算结果
        int result = solve(w, v, N, W);
        System.out.println("最大能装价值:" + result);
    }
}
