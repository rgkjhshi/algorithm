package com.mk.algorithm;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logger.info("测试基累正确执行，不要改");
    }

    @Test
    public void testDate() {
        // 夏令时日期
        LocalDate date = LocalDate.parse("19880410", DateTimeFormat.forPattern("yyyyMMdd"));
        logger.info(date.toString("yyyy-MM-dd"));
        int age = Years.yearsBetween(date, LocalDate.now()).getYears();
        logger.info("age={}", age);
        LocalTime time = LocalTime.parse("00:30:00");
        logger.info(time.toString("HH:mm:ss"));
    }

    @Test
    public void createTable() {
        String str = "CREATE TABLE `character_data_%04d` LIKE `character_data_3000`;";
        for (int i = 3001; i < 4000; i++) {
            System.out.println(String.format(str, i));
        }
    }

    @Test
    public void alterTable() {
        String s2 = "drop table `character_data_%03d`;";
//        String str = "alter table `character_data_%03d` modify `character_value` LONGTEXT COMMENT '指标值';";
        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format(s2, i));
//            System.out.println(String.format(str, i));
        }
    }

    @Test
    public void testTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTime start = DateTime.parse("2018-02-11 01:00:00", DateTimeFormat.forPattern(pattern));
        DateTime end = DateTime.parse("2018-02-12 01:00:00", DateTimeFormat.forPattern(pattern));
        logger.info("" + Minutes.minutesBetween(DateTime.now(), start).getMinutes());
        logger.info("" + Minutes.minutesBetween(DateTime.now(), end).getMinutes());
    }

}
