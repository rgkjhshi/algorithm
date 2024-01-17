package com.mk.algorithm.solution.dp05;

import java.util.Arrays;


public class LongestCommonSequence {

    public static int solveLcs(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 创建备忘录
        int[][] dp = new int[m + 1][n + 1];
        // 初始化状态
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        // 遍历两个数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 下标从1起算，因此text[i-1]表示第i个字符，因为第1个字符下标为0
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // dp[i-1][j-1]表示的是前一个结果，两个i-1含义不一样
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        // 物品的总数，背包能容纳的总重量
        String text1 = "ade";
        // 物品的价值
        String text2 = "abcde";
        // 计算结果
        int result = solveLcs(text1, text2);
        System.out.println("最长公共子序列长度为:" + result);
    }
}
