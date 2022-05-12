package com.mk.algorithm.solution;

import java.util.Stack;

/**
 * @author song.shi
 * @since 2018-07-08
 */
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        // 中序(前中后就在于位置不同)
        System.out.println(root.value);
        inOrder(root.right);
    }

    /**
     * 非递归方式
     */
    public static void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.println(node.value);
                node = node.right;
            }

        }
    }

    /**
     * ---1
     * -+   +
     * 2     3
     * -+     +
     * --4     5
     * -+
     * 6
     */
    public static TreeNode initTree() {
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();
        node1.value = 1;
        node1.left = node2;
        node2.value = 2;
        node2.right = node4;
        node3.value = 3;
        node3.right = node5;
        node4.value = 4;
        node4.left = node6;
        node5.value = 5;
        node6.value = 6;
        return node1;
    }

    public static void main(String[] args) {
        TreeNode root = initTree();
        TreeNode.inOrder(root);
        TreeNode.inOrder2(root);
    }
}
