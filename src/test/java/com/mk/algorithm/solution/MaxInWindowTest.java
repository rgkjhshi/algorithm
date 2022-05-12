package com.mk.algorithm.solution;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author song.shi
 * @since 2018-07-06
 */
public class MaxInWindowTest {
    private static final Logger logger = LoggerFactory.getLogger(MaxInWindowTest.class);

    @Test
    public void maxInWindowTest() {
        int[] A = {2, 3, 4, 2, 6, 2, 5, 1};
        int k = 3;
        ArrayList<Integer> result = MaxInWindow.windowMax(A, k);
        logger.info(result.toString());
        result = MaxInWindow.maxHeap(A, k);
        logger.info(result.toString());
        result = MaxInWindow.deque(A, k);
        logger.info(result.toString());
    }
}