package com.mk.algorithm.solution;


import java.util.*;

/**
 * 问题描述: 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
 * 如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3
 * 滑动窗口有6个: {[2,3,4],2,6,2,5,1}, {2,[3,4,2],6,2,5,1}, {2,3,[4,2,6],2,5,1}, {2,3,4,[2,6,2],5,1}, {2,3,4,2,[6,2,5],1}, {2,3,4,2,6,[2,5,1]}
 * 滑动窗口的最大值分别为{4,4,6,6,6,5}
 *
 * @author song.shi
 * @since 2018-07-06
 */
public class MaxInWindow {

    /**
     * 简单方法: 求每个窗口的最大值
     * 计算窗口的最大值需O(w)，移动n-w+1次，时间复杂度为O(nw)
     *
     * @param array      数组
     * @param windowSize 窗口大小
     * @return 滑动窗口最大值
     */
    public static ArrayList<Integer> windowMax(int[] array, int windowSize) {
        ArrayList<Integer> result = new ArrayList<>();
        // 窗口滑动
        for (int i = 0; i <= (array.length - windowSize); i++) {
            // 最大值的下标
            int max = array[i];
            // 查找滑动窗口最大值
            for (int j = i; j < windowSize + i; j++) {
                if (array[j] > array[max]) {
                    max = j;
                }
            }
            // 滑动窗口最大值
            result.add(array[max]);
        }
        return result;
    }

    /**
     * 大根堆法: 构建一个大小的为w的大根堆(借助PriorityQueue)
     * 时间复杂度: 往堆中插入数据为O(lgw)，若数组有序则为O(lgn), 因为滑动过程中没有元素从堆中被删除，滑动n-w+1次，复杂度为O(nlgn)
     *
     * @param array      数组
     * @param windowSize 窗口大小
     * @return 滑动窗口最大值
     */
    public static ArrayList<Integer> maxHeap(int[] array, int windowSize) {
        // 记录窗口最大值
        ArrayList<Integer> result = new ArrayList<>();
        // 队列里存的是下标
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // 遍历所有元素
        for (int i = 0; i < array.length; i++) {
            // 若队头元素不在滑动窗口中了，就删除头元素
            while (!queue.isEmpty() && queue.peek().getIndex() + windowSize <= i) {
                queue.poll();
            }
            // 入队
            queue.offer(new Pair(i, array[i]));
            // 队头元素一定在窗口中且队头元素一定最大, 窗口充满时(i>=windowSize-1)才可取窗口最大值
            if (i >= windowSize - 1) {
                result.add(queue.peek().getValue());
            }
        }
        return result;
    }


    /**
     * 双端队列法: 滑动窗口的最大值总是保存在队列首部，队列里面的数据总是从大到小排列
     * 若队列为空, 或当前元素小于队尾, 则当前元素入队
     * 若当前元素大于队尾, 则将所有小于等于当前元素的队尾元素出队, 然后当前元素入队
     * (无论如何当前元素都会入队, 只是要保证前面的都大于队尾)
     * 每次移动的时候需要判断队头是否在窗口中, 若不在, 则需要将其从队列中删除
     * 时间复杂度: 由于每个元素最多进队和出队各一次，因此该算法时间复杂度为O(N)
     *
     * @param array      数组
     * @param windowSize 窗口大小
     * @return 滑动窗口最大值
     */
    public static ArrayList<Integer> deque(int[] array, int windowSize) {
        // 记录窗口最大值
        ArrayList<Integer> result = new ArrayList<>();
        // 队列里存的是下标
        Deque<Integer> deque = new LinkedList<>();

        // 遍历所有元素
        for (int i = 0; i < array.length; i++) {
            // 若队头元素不在滑动窗口中了，就删除头元素
            if (!deque.isEmpty() && deque.peekFirst() + windowSize <= i) {
                deque.pollFirst();
            }
            // 若当前元素大于队尾, 则将所有小于等于当前元素的队尾元素出队, 保证队列里的元素都大于当前元素
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
                deque.pollLast();
            }
            // 无论如何当前元素都会入队
            deque.offerLast(i);
            // 队头元素一定在窗口中且队头元素一定最大, 窗口充满时(i>=windowSize-1)才可取窗口最大值
            if (i >= windowSize - 1) {
                result.add(array[deque.peekFirst()]);
            }
        }
        return result;
    }

    static class Pair {
        private int index;
        private int value;

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
