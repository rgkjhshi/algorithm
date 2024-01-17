package com.mk.algorithm.solution.dp01;


import java.util.Arrays;

/**
 * 硬币找零问题
 * <p>
 * 问题描述：
 * 给定n种不同面值的硬币，分别记为c[0], c[1], c[2], … c[n]，同时还有一个总金额k，
 * 编写一个函数计算出最少需要几枚硬币凑出这个金额k？
 * 每种硬币的个数不限，且如果没有任何一种硬币组合能组成总金额时，返回 -1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：c[0]=1, c[1]=2, c[2]=5, k=12
 * 输出：3
 * 解释：12 = 5 + 5 + 2
 * <p>
 * 示例 2：
 * <p>
 * 输入：c[0]=5, k=7
 * 输出：-1
 * 解释：只有一种面值为5的硬币，怎么都无法凑出总价值为7的零钱。
 *
 * @author shisong
 * @since 2022-05-12
 */
public class Coin {

    public static int solve(int k, int[] coins) {
        int[] dp = new int[k + 1];
        // max - 1 是为了防止后面硬币数+1时越界
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        // 初始状态
        dp[0] = 0;
        for (int total = 1; total <= k; total++) {
            if (total == 3) {
                dp[total] = dp[total];
            }
            // 遍历所有硬币
            for (int coin : coins) {
                // 剩余值大于硬币面值，作出决策(状态转移方程)
                if (total >= coin) {
                    dp[total] = Math.min(dp[total], dp[total - coin] + 1);
                }
            }
        }
        return dp[k] == (Integer.MAX_VALUE - 1) ? -1 : dp[k];
    }

    public static void main(String[] args) {
        // 硬币面值
        int[] values = {3, 5};
        // 总值
        int total = 11;
        // 计算结果
        int result = solve(total, values);
        System.out.println("最少硬币数:" + result);
    }
}
