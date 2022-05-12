package com.mk.algorithm.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author song.shi
 * @since 2018-07-06
 */
public class IDUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(IDUtilsTest.class);

    @Test
    public void getAge() {
        String age = IDUtils.getAge("120106199101019758");
        logger.info(age);
    }
}