package com.mk.algorithm.solution.dp04;

import lombok.extern.slf4j.Slf4j;

/**
 * 完全背包问题
 * <p>
 * 问题描述：
 * 给你一个可放总重量为 $W$ 的背包和 $N$ 个物品，对每个物品，有重量 $w$ 和价值 $v$ 两个属性，
 * 那么第 $i$ 个物品的重量为 $w[i]$，价值为 $v[i]$。
 * 现在让你用这个背包装物品，每种物品都可以选择任意多个，问最多能装的价值是多少？
 * <p>
 * 示例：
 * 输入：
 * W = 5, N = 3
 * w = [3, 2, 1], v = [5, 2, 3]
 * 输出：15
 * <p>
 * 解释：选择 i=2 时，选取 5 次，总价值为 5 * 3 = 15。
 * <p>
 * 分析：
 * 与0-1背包相比，完全背包问题只在原来的基础上多加了一句话：“每种物品都可以选择任意多个”。除此之外，完全相同。
 * 由于每种物品的数量是无限制的，因此就像前面给出的示例那样，我们可以将同一种物品多次放入背包。
 * 因此，对于第 $tn$ 种物品，我们有 k 种选择（其中 0 ≤ k * $w[tn]$ ≤ W）：我们可以从 0 开始，拿第 0 件、第 1 件、第 2 件……直到第 ($W / w[tn]$) 件物品为止。
 * 然后在这么多子问题下，选择最优的那一种情况。
 *
 * @author shisong
 * @since 2022-05-12
 */
@Slf4j
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
        log.info("最大能装价值:" + result);
    }
}
