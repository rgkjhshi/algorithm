package com.mk.algorithm.solution.dp02;

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
public class Bag01 {
    public static int bag(int[] weight, int[] value, int w) {
        int n = value.length;
        // 创建备忘录
        int[][] dp = new int[n + 1][w + 1];

        // 初始化状态
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= w; j++) {
            dp[0][j] = 0;
        }

        // 前i件物品
        for (int i = 1; i <= n; i++) {
            // 背包容量
            for (int j = 1; j <= w; j++) {
                if (j < weight[i - 1]) {
                    // 剩余容量不足，只能放下前 tn-1 件物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 剩余容量充足，进一步作出决策（状态转移方程）
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        int w = 5; // 背包能容纳的总重量
        int[] weight = {3, 2, 1}; // 物品的重量
        int[] value = {5, 2, 3}; // 物品的价值
        // 计算结果
        int result = bag(weight, value, w);
        System.out.println("最大能装价值:" + result);
    }
}
