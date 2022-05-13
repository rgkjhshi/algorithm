package com.mk.algorithm.solution.dp02;

import lombok.extern.slf4j.Slf4j;

/**
 * 0-1 背包问题
 * <p>
 * 问题描述：
 * 给你一个可放总重量为 $W$ 的背包和 $N$ 个物品，对每个物品，有重量 $w$ 和价值 $v$ 两个属性，
 * 那么第 $i$ 个物品的重量为 $w[i]$，价值为 $v[i]$。
 * 现在让你用这个背包装物品，问最多能装的价值是多少？
 * <p>
 * 示例：
 * 输入：
 * W = 5, N = 3
 * w = [3, 2, 1], v = [5, 2, 3]
 * 输出：8
 * <p>
 * 解释：选择 i=0 和 i=2 这两件物品装进背包。它们的总重量 4 小于 W，同时可以获得最大价值 8。
 *
 * @author shisong
 * @since 2022-05-12
 */
@Slf4j
public class Package01 {

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
            // 背包容量有多大就还要计算多少次
            for (int rw = 1; rw <= W; rw++) {
                if (rw < w[tn]) {
                    // 剩余容量不足，只能放下前 tn-1 件物品
                    dp[tn][rw] = dp[tn - 1][rw];
                } else {
                    // 剩余容量充足，决策是否放（状态转移方程）
                    //   1. 放入：那么价值是 dp(tn - 1, rw - w[tn])
                    //   2. 不放入：那么价值是 dp(tn - 1, rw)
                    dp[tn][rw] = Math.max(dp[tn - 1][rw], dp[tn - 1][rw - w[tn]] + v[tn]);
                }
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
        log.info("最大能装价值:" + result);
    }
}
