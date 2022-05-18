package com.mk.algorithm.solution.dp05;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 最长公共子序列（Longest Common Subsequence，LCS）问题
 * <p>
 * 问题描述：
 * 给定两个字符串 $text1$ 和 $text2$，返回这两个字符串的最长公共子序列的长度。
 * 若这两个字符串没有公共子序列，则返回 0。其中：
 * <p>
 * 1 ≤ text1.length ≤ 1000；
 * 1 ≤ text2.length ≤ 1000；
 * 输入的字符串只含有小写英文字符。
 * <p>
 * 示例1：
 * 输入：text1 = "abcde", text2 = "ade"
 * 输出：3
 * 解释：最长公共子序列是 "ade"，它的长度为 3。
 * <p>
 * 示例2:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：显然，两个字符串没有公共子序列，返回 0。
 *
 * @author shisong
 * @since 2022-05-12
 */
@Slf4j
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
        log.info("最长公共子序列长度为:" + result);
    }
}
