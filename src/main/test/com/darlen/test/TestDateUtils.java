/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   TestDateUtils.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.test;

import com.darlen.common.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Description：测试DateUtils类
 * Created on  2015-07-26 下午12：58
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午12：58              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class TestDateUtils {
    private final static Logger logger = Logger.getLogger(TestDateUtils.class);

    /**
     * 取当前日期的字符串表示,默认是年月日
     *
     * @return 当前日期的字符串 ,如2010-05-28
     */
    @Test
    public void testToday(){
        logger.info("today方法...");
        logger.info(DateUtils.today());
        logger.info(DateUtils.today(DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }

    /**
     * 用指定的time的format获取当前时间的字符串
     *
     * @return 当前时间, 如：21：10：12
     */
    @Test
    public void testCurrentTime(){
        logger.info("测试currentTime方法...");
        logger.info(DateUtils.currentTime());
        logger.info(DateUtils.currentTime(DateUtils.DEFAULT_TIME_FORMAT));
    }

    /**
     * 将java.util.date型按照指定format转为字符串
     *
     * param date   源对象
     * param format 想得到的格式字符串
     * @return 如：2010-05-28
     */
    @Test
    public void testDateToString() {
        logger.info("测试dateToString方法...");
        try {
            SimpleDateFormat sdf = DateUtils.getSimpleDateFormat(DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            String s = "2015-07-26 14:03:03";
            Date date = sdf.parse(s);
            String fmtDateString = DateUtils.dateToString(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            logger.info("经转换Date之后的字符串是：" + fmtDateString);
            Assert.assertEquals(s,fmtDateString);
        } catch (ParseException e) {
            logger.error("testDateToString 发生异常："+e);
        }
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期
     * <br>
     * 欲取得当前日期5天前的日期,可做如下调用：<br>
     * getAddDay("DATE", -5).
     *
     * param field,段,如"year","month","date",对大小写不敏感
     *
     * param amount,增加的数量(减少用负数表示),如5,-1
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws java.text.ParseException
     */
    @Test
    public void getAddDay() throws ParseException {
        logger.info("测试addDate方法...");
        String dateStr = "2015-07-27 14:27:59";
        logger.info("根据现在时间，天数加1："+DateUtils.getAddDay(DateUtils.DEFAULT_DATE,1));
        logger.info("根据现在时间，指定格式，天数加1："+DateUtils.getAddDay(DateUtils.DEFAULT_DATE,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，年数加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_YEAR,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2016-07-27 14:27:59",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_YEAR,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，月份加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_MONTH,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-08-27 14:27:59",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_MONTH,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，天数加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_DATE,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-07-28 14:27:59",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_DATE,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，小时加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_HOUR,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-07-27 15:27:59",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_HOUR,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，分钟加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_MINUTE,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-07-27 14:28:59",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_MINUTE,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

        logger.info("测试2015-07-27 14:27:59，秒数加1："+DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_SECOND,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-07-27 14:28:00",DateUtils.getAddDay(dateStr,DateUtils.DEFAULT_SECOND,1,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }

    /**
     * 强制类型转换 从串到日期
     *
     * param strDate   源字符串，采用yyyy-MM-dd格式
     * param format ps
     * @return 得到的日期对象
     * @throws ParseException
     */
    @Test
    public void testStringToDate() {
        String dateStr = "2015-07-28 14:27:59";
        try {
            logger.info(DateUtils.stringToDate(dateStr, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
            SimpleDateFormat sdf = DateUtils.getSimpleDateFormat(DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            Date date = sdf.parse(dateStr);
            logger.info(date);
            Assert.assertEquals("测试失败", date.getTime(), DateUtils.stringToDate("2015-07-28 14:27:59", DateUtils.DEFAULT_DATETIME_FORMAT_SEC).getTime());
        } catch (ParseException e) {
            logger.error("测试StringToDate失败："+e);
        }



    }

    /**
     * 根据传入的毫秒数和格式，对日期进行格式化输出
     *
     * param millisecond
     * param format
     * @return
     * @version 2011-7-12
     */
    @Test
    public void testMillisecondFmtToString() {
        logger.info(DateUtils.milsecFmtToString(1438064879000l, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("2015-07-28 14:27:59",DateUtils.milsecFmtToString(1438064879000l, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }

    /**
     * 得到当前日期的中国的星期数
     *DAY_OF_WEEK 是7，所以WEEKS必须是从1开始，故WEEKS[0]没有意义
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    @Test
    public void testGetChineseWeekOfMonth() throws ParseException {
        logger.info(DateUtils.getChineseWeekOfMonth());
        logger.info(DateUtils.getChineseMonth());

        Calendar rightNow = Calendar.getInstance();
        logger.info(rightNow.get(Calendar.DAY_OF_WEEK));
        String date = "2015-07-26 10:20:20";
        logger.info(DateUtils.getChineseWeekOfMonth(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("星期日",DateUtils.getChineseWeekOfMonth(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }


    /**
     * 得到当前日期的中国的星期数
     *
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    @Test
    public void testGetChineseMonth() throws ParseException {
        logger.info(DateUtils.getChineseMonth());
        String date = "2015-07-26 10:20:20";
        logger.info(DateUtils.getChineseMonth(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
        Assert.assertEquals("七月",DateUtils.getChineseMonth(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }

    /**
     * 强制类型转换 从串到时间戳
     *
     * #param strDate   源串
     * #param format 遵循格式
     * @return 取得的时间戳对象
     * @throws ParseException .
     */
    @Test
    public void testParseTimestamp() throws ParseException {
        String date = "2015-07-26 10:20:20";
        logger.info(DateUtils.parseTimestamp(date, DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
    }

    /**
     * getCurDate 取当前日期
     *
     * @return java.util.Date型日期
     */
    @Test
    public void testGetCurDate() {
        try {
            logger.info(DateUtils.getCurDate(DateUtils.DEFAULT_DATETIME_FORMAT_SEC));
            logger.info(DateUtils.getCurDate().getTime());
        } catch (Exception e) {
            logger.error("getCurDate"+e);
        }
    }

    /**
     * getCurTimestamp 取当前时间戳
     *
     * @return java.sql.Timestamp
     */
    @Test
    public void getCurTimestamp() {
        logger.info(DateUtils.getCurTimestamp());
    }

    /**
     * Timestamp按照缺省格式转为字符串
     *
     * #param ts 源对象
     * @return 如：2010-05-28
     */
    @Test
    public void timestampToString() {
        Timestamp ts = new Timestamp(1437919775843l);
        logger.info(DateUtils.timestampToString(ts));
        logger.info(DateUtils.timestampToString(ts,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

    }

    /**
     * 将sqldate型按照缺省格式转为字符串
     *
     * #param sqldate 源对象
     * @return 如：2010-05-28
     */
    @Test
    public void sqlDateToString() {
        java.sql.Date date = new java.sql.Date(1437919775843l);//2015-07-26 22:09:35
        logger.info(DateUtils.sqlDateToString(date));
        logger.info(DateUtils.sqlDateToString(date,DateUtils.DEFAULT_DATETIME_FORMAT_SEC));

    }

    /**
     * 计算日期时间之间的差值， date1得时间必须大于date2的时间
     *
     * #param date1
     * #param date2
     * @return {@link java.util.Map} Map的键分别为, day(天), hour(小时),minute(分钟)和second(秒)。
     * @version 2011-7-12
     */
    @Test
    public void testTimeDifference() {
        try {
            Date d1 = DateUtils.stringToDate("2015-07-26 22:09:35", DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            Date d2 = DateUtils.stringToDate("2015-07-24 10:10:10", DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            logger.info(DateUtils.timeDifference(d1, d2));//{minute=59, second=25, hour=11, day=2}
        } catch (ParseException e) {
            logger.error("testTimeDifference失败："+e);
        }
    }

    @Test
    public void testCompareTo() {
        try {
            Date d1 = DateUtils.stringToDate("2015-07-26 22:09:35", DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            Date d2 = DateUtils.stringToDate("2015-07-24 10:10:10", DateUtils.DEFAULT_DATETIME_FORMAT_SEC);
            logger.info(DateUtils.compareTo(d1, d2));//{minute=59, second=25, month=0, year=0, hour=11, day=2}
        } catch (ParseException e) {
            logger.error("testCompareTo失败："+e);
        }
    }

}
