package com.mk.algorithm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 单元测试基础类
 *
 * @author song.shi
 * @since 2017-07-17
 */
public class UnitTest {
    private static final Logger logger = LoggerFactory.getLogger(UnitTest.class);

    @Test
    public void test() {
        logger.info("测试基类正确执行，不要改");
    }

    @Test
    public void testDate() {
        // 夏令时日期
        LocalDate date0 = LocalDate.parse("19880410", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate date = LocalDate.parse("1988-04-10");
        logger.info(date.toString());
        long years = date.until(LocalDate.now(), ChronoUnit.YEARS);
        logger.info("age={}", years);
        LocalTime time = LocalTime.parse("00:30:00");
        logger.info(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
