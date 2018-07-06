package com.mk.algorithm;

import com.mk.algorithm.boot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 集成测试基础类
 *
 * @author song.shi
 * @since 2018-01-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void test() {
        logger.info("测试基累正确执行，不要改");
    }
}