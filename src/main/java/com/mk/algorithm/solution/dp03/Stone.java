package com.mk.algorithm.solution.dp03;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 0-1 背包的变型，石头粉碎问题
 * <p>
 * 问题：
 * 有一堆石头，每块石头的重量都是正整数。每次从中选出任意两块石头，然后将它们一起粉碎。
 * 假设石头的重量分别为 $x$ 和 $y$，且 $x ≤ y$。那么粉碎的可能结果如下：
 * <p>
 * 如果 $x$ 与 $y$ 相等，那么两块石头都会被完全粉碎；
 * 否则，重量为 $x$ 的石头将会完全粉碎，而重量为 $y$ 的石头的新重量为 $y - x$。
 * 最后，最多只会剩下一块石头。返回此时石头最小的可能重量。如果没有石头剩下，就返回 0。
 * <p>
 * 示例：
 * <p>
 * 输入：[1, 2, 1, 7, 9, 4]
 * 输出：0
 * 解释：
 * <p>
 * Round 1: (2, 4) -> 2, 数组变成 [1, 1, 7, 9, 2]
 * Round 2: (7, 9) -> 2, 数组变成 [1, 1, 2, 2]
 * Round 3: (2, 2) -> 0, 数组变成 [1, 1]
 * Round 4: (1, 1) -> 0, 数组为空，返回 0
 * <p>
 * 分析：
 * 可转化成，将数组中的数分成两组，然后用 一组的和 减去 二组的和， 求两个数字的差，确保这个差最小。
 * 如何确保两组数字之差最小呢？如果一组数字接近所有数字之和的 1/2，那么两组数字之差肯定越小。
 * 比如上面的示例中所有数字之和是 24，所以一组数字是 12，另一组数字也是 12，最后肯定能得到最小值0。
 * 这样就转化为：假设有一个背包，背包的容量是 12（24/2），有一堆的物品，重量分别是 [1, 2, 1, 7, 9, 4]，注意设它的价值与重量相同。
 * 现在我们希望选出的物品放到背包里的价值最大
 *
 * @author shisong
 * @since 2022-05-12
 */
@Slf4j
public class Stone {

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
         * tn: 已经遍历过的物品数量；
         * rw: 背包还能容量的重量。
         */
        // 遍历每一个物品
        for (int tn = 1; tn <= N; tn++) {
            // 背包容量有多大就还要计算多少次
            for (int rw = 1; rw <= W; rw++) {
                if (rw < w[tn - 1]) {
                    // 剩余容量不足，只能放下前 tn-1 件物品
                    dp[tn][rw] = dp[tn - 1][rw];
                } else {
                    // 剩余容量充足，决策是否放（状态转移方程）(注意所有涉及w[]和v[]的，下标都从0开始，因此要-1)
                    dp[tn][rw] = Math.max(dp[tn - 1][rw], dp[tn - 1][rw - w[tn - 1]] + v[tn - 1]);
                }
            }
        }
        return dp[N][W];
    }

    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 2, 1, 7, 9, 4};
        // 物品的价值
        int[] v = {1, 2, 1, 7, 9, 4};
        // 物品的总数，背包能容纳的总重量
        int N = v.length;
        // 石头数组和的一半作为背包容量
        int sum = Arrays.stream(w).sum();
        int W = sum / 2;
        // 最大价值，即为一组数的和
        int maxValue = solve(w, v, N, W);
        // 两组数的差，即为剩下的石头重量
        int result = (sum - maxValue) - maxValue;
        log.info("最后剩下的石头最小重量为:" + result);
    }
}