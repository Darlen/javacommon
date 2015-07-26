/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName JAVA COMMON
 *    File Name   DateUtils.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.common;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description.
 * Month 月份的起始值为０而不是１，e.g.要设置八月时，我们用７而不是８。calendar.set(Calendar.MONTH,８-１);
 * Created on  2015-07-23 下午11:00
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:00              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class DateUtils {
    private final static Logger logger = Logger.getLogger(DateUtils.class);
    /**
     * 缺省日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 缺省月格式
     */
    public static final String DEFAULT_MONTH = "MONTH";

    /**
     * 缺省年格式
     */
    public static final String DEFAULT_YEAR = "YEAR";

    /**
     * 缺省日格式
     */
    public static final String DEFAULT_DATE = "DAY";

    /**
     * 缺省小时格式
     */
    public static final String DEFAULT_HOUR = "HOUR";

    /**
     * 缺省分钟格式
     */
    public static final String DEFAULT_MINUTE = "MINUTE";

    /**
     * 缺省秒格式
     */
    public static final String DEFAULT_SECOND = "SECOND";

    /**
     * 缺省长日期格式
     */
    public static final String DEFAULT_DATETIME_FORMAT_MIN = "yyyy-MM-dd HH-mm";

    /**
     * 缺省长日期格式,精确到秒
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * 星期数组
     */
    public static final String[] WEEKS = {"","星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 月份组
     */
    public static final String MONTHS[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    /**
     * 取当前日期的字符串表示,默认是年月日
     *
     * @return 当前日期的字符串 ,如2010-05-28
     */
    public static String today() {
        return today(DEFAULT_DATE_FORMAT);
    }

    /**
     * 根据输入的格式得到当前日期的字符串
     *
     * @param strFormat 日期格式
     * @return
     */
    public static String today(String strFormat) {
        return dateToString(new Date(), strFormat);
    }

    /**
     * 取当前时间的字符串
     *
     * @return 当前时间, 如:21:10:12
     */
    public static String currentTime() {
        return currentTime(DEFAULT_TIME_FORMAT);
    }

    /**
     * 根据输入的格式获取时间的字符串表示
     *
     * @param strFormat 输出格式,如'hh:mm:ss'
     * @return 当前时间, 如:21:10:12
     */
    public static String currentTime(String strFormat) {
        return dateToString(new Date(), strFormat);
    }

    /**
     * 将java.util.date型按照指定格式转为字符串
     *
     * @param date   源对象
     * @param format 想得到的格式字符串
     * @return 如：2010-05-28
     */
    public static String dateToString(Date date, String format) {
        return getSimpleDateFormat(format).format(date);
    }

    /**
     * 获取格式化对象
     *
     * @param strFormat 格式化的格式,可为空， 如"yyyy-MM-dd"
     * @return 格式化对象
     */
    public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
        if (strFormat != null && !"".equals(strFormat.trim())) {
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期
     * <br>
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DATE", -5).
     *
     * @param field,段,如"year","month","date",对大小写不敏感
     *
     * @param amount,增加的数量(减少用负数表示),如5,-1
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws java.text.ParseException
     */

    public static String getAddDay(String field, int amount) throws ParseException {
        return getAddDay(field, amount, null);
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期,按指定格式输出
     * <p/>
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DATE", -5,'yyyy-mm-dd hh:mm').
     *
     * @param field,字段,如"year","month","date",对大小写不敏感
     *
     * @param amount,增加的数量(减少用负数表示),如5,-1
     * @param strFormat,输出格式,如"yyyy-mm-dd","yyyy-mm-dd hh:mm:ss"
     *
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws ParseException
     */
    public static String getAddDay(String field, int amount, String strFormat) throws ParseException {
        return getAddDay(null, field, amount, strFormat);
    }

    /**
     * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * @param date      String 要改变的日期
     * @param field     int 日期改变的字段，YEAR,MONTH,DAY
     * @param amount    int 改变量
     * @param strFormat 日期返回格式
     * @return
     * @throws ParseException
     */
    public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
        if (strFormat == null) {
            strFormat = DEFAULT_DATETIME_FORMAT_SEC;
        }
        Calendar rightNow = Calendar.getInstance();
        if (date != null && !"".equals(date.trim())) {
            rightNow.setTime(stringToDate(date, strFormat));
        }
        if (field == null) {
            return dateToString(rightNow.getTime(), strFormat);
        }
        rightNow.add(getCalendarDateField(field), amount);
        return dateToString(rightNow.getTime(), strFormat);
    }

    /**
     * 获取Calendar中的时间Field
     *
     * @param field 时间间隔类型
     * @return 日历的字段
     */
    protected static int getCalendarDateField(String field) {
        String tmpField = field.toUpperCase();
        if (tmpField.equals(DEFAULT_YEAR)) {
            return Calendar.YEAR;
        } else if (tmpField.equals(DEFAULT_MONTH)) {
            return Calendar.MONTH;
        } else if (tmpField.equals(DEFAULT_DATE)) {
            return Calendar.DATE;
        } else if (DEFAULT_HOUR.equals(tmpField)) {
            return Calendar.HOUR;
        } else if (DEFAULT_MINUTE.equals(tmpField)) {
            return Calendar.MINUTE;
        } else {
            return Calendar.SECOND;
        }
    }

    /**
     * 强制类型转换 从串到日期
     *
     * @param strDate   源字符串，采用yyyy-MM-dd格式
     * @param format ps
     * @return 得到的日期对象
     * @throws ParseException
     */
    public static Date stringToDate(String strDate, String format) throws ParseException {
        return getSimpleDateFormat(format).parse(strDate);
    }

    /**
     * 根据传入的毫秒数和格式，对日期进行格式化输出
     *
     * @param millisecond
     * @param format
     * @return
     * @version 2011-7-12
     */
    public static String milsecFmtToString(Long millisecond, String format) {
        if (millisecond == null || millisecond <= 0) {
            throw new IllegalArgumentException(String.format("传入的时间毫秒数[%s]不合法", "" + millisecond));
        }
        if (format == null || "".equals(format.trim())) {
            format = DEFAULT_DATE_FORMAT;
        }
        return dateToString(new Date(millisecond), format);
    }

    /**
     * 得到当前日期的中国的星期数
     *
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    public static String getChineseWeekOfMonth() throws ParseException {
        return getChineseWeekOfMonth(null, null);
    }

    /**
     * 根据日期的到给定日期的在当月中的中国的星期数
     * DAY_OF_WEEK 是7，所以WEEKS必须是从1开始，故WEEKS[0]没有意义
     * @param date 给定日期
     * @param format
     * @return
     * @throws ParseException
     */
    public static String getChineseWeekOfMonth(String date, String format) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        // rightNow.add(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        if (date != null && !"".equals(date.trim())) {
            rightNow.setTime(stringToDate(date, format));
        }
        // logger.info(rightNow.get(Calendar.WEEK_OF_MONTH)+";"+rightNow);
        return WEEKS[rightNow.get(Calendar.DAY_OF_WEEK)];
    }

    /**
     * 得到当前日期的中国的星期数
     *
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    public static String getChineseMonth() throws ParseException {
        return getChineseMonth(null, null);
    }

    /**
     * 根据日期的到给定日期的中国的月份
     *
     * @param date 给定日期
     * @param format
     * @return
     * @throws ParseException
     */
    public static String getChineseMonth(String date,String format) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        if (date != null && !"".equals(date.trim())) {
            rightNow.setTime(stringToDate(date, format));
        }

        return MONTHS[rightNow.get(Calendar.MONTH)];
    }

    /**
     * 强制类型转换 从串到时间戳
     *
     * @param strDate   源串
     * @param format 遵循格式
     * @return 取得的时间戳对象
     * @throws ParseException .
     */
    public static Timestamp parseTimestamp(String strDate, String format) throws ParseException {
        Date utildate = getSimpleDateFormat(format).parse(strDate);
        return new Timestamp(utildate.getTime());
    }

    /**
     * getCurDate 取当前日期
     *
     * @return java.util.Date型日期
     */
    public static Date getCurDate() {
        return (new Date());
    }

    /**
     * getCurTimestamp 取当前时间戳
     *
     * @return java.sql.Timestamp
     */
    public static Timestamp getCurTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * getCurTimestamp 取遵循格式的当前时间
     *
     * @param format 遵循格式
     * @return java.sql.Timestamp
     */
    public static Date getCurDate(String format) throws Exception {
        return getSimpleDateFormat(format).parse(dateToString(new Date(), format));
    }

    /**
     * Timestamp按照缺省格式转为字符串
     *
     * @param ts 源对象
     * @return 如：2010-05-28
     */
    public static String timestampToString(Timestamp ts) {
        return timestampToString(ts, DEFAULT_DATE_FORMAT);
    }

    /**
     * Timestamp按照指定格式转为字符串
     *
     * @param timestamp 源对象
     * @param format    ps（如yyyy.mm.dd）
     * @return 如：2010-05-28 或2010-05-281 13:21
     */
    public static String timestampToString(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return "";
        }
        return dateToString(new Date(timestamp.getTime()), format);
    }

    /**
     * 将sqldate型按照缺省格式转为字符串
     *
     * @param sqldate 源对象
     * @return 如：2010-05-28
     */
    public static String sqlDateToString(java.sql.Date sqldate) {
        return sqlDateToString(sqldate, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将sqldate型按照指定格式转为字符串
     *
     * @param sqldate 源对象
     * @param sFormat ps
     * @return 如：2010-05-28 或2010-05-28 00:00
     */
    public static String sqlDateToString(java.sql.Date sqldate, String sFormat) {
        if (sqldate == null) {
            return "";
        }
        return dateToString(new Date(sqldate.getTime()), sFormat);
    }

    /**
     * 计算日期时间之间的差值， date1得时间必须大于date2的时间
     *
     * @param date1
     * @param date2
     * @return {@link java.util.Map} Map的键分别为, day(天), hour(小时),minute(分钟)和second(秒)。
     * @version 2011-7-12
     */
    public static Map<String, Long> timeDifference(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException("date1 and date2 can't null");
        }
        long mim1 = date1.getTime();
        long mim2 = date2.getTime();
        if (mim1 < mim2) {
            throw new IllegalArgumentException(String.format("date1[%s] not be less than date2[%s].", mim1 + "", mim2 + ""));
        }
        long m = (mim1 - mim2 + 1) / 1000l;
        long mday = 24 * 3600;
        final Map<String, Long> map = new HashMap<String, Long>();
        map.put("day", m / mday);
        m = m % mday;
        map.put("hour", (m) / 3600);
        map.put("minute", (m % 3600) / 60);
        map.put("second", (m % 3600 % 60));
        return map;
    }

    public static Map<String, Integer> compareTo(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            return null;
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long time = Math.max(time1, time2) - Math.min(time1, time2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("year", (calendar.get(Calendar.YEAR) - 1970) > 0 ? (calendar.get(Calendar.YEAR) - 1970) : 0);
        map.put("month", (calendar.get(Calendar.MONTH) - 1) > 0 ? (calendar.get(Calendar.MONTH) - 1) : 0);
        map.put("day", (calendar.get(Calendar.DAY_OF_MONTH) - 1) > 0 ? (calendar.get(Calendar.DAY_OF_MONTH) - 1) : 0);
        map.put("hour", (calendar.get(Calendar.HOUR_OF_DAY) - 8) > 0 ? (calendar.get(Calendar.HOUR_OF_DAY) - 8) : 0);
        map.put("minute", calendar.get(Calendar.MINUTE) > 0 ? calendar.get(Calendar.MINUTE) : 0);
        map.put("second", calendar.get(Calendar.SECOND) > 0 ? calendar.get(Calendar.SECOND) : 0);
        return map;
    }

    //TODO
    //1.两个日期的大小比较
    //2.两个日期相差多少年，月，日，时，分，秒
    //3.获取前多少年，月，日，时，分，秒的时间，比如是前6个月，2015.07.28-->2015.02.28
    //4.获取后多少年，月，日，时，分，秒的时间













    public static void main(String[] args) throws InterruptedException, ParseException {
        Calendar c1 = Calendar.getInstance();
        //logger.info(c1.getTimeZone());

        //testCalendar();
        //test2();
        String date = "2015-07-26 10:20:20";
        // SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC);
        logger.info(stringToDate(date, DEFAULT_DATETIME_FORMAT_SEC));

        logger.info(getChineseWeekOfMonth(date, DEFAULT_DATETIME_FORMAT_SEC));

    }

    /**
     * 测试Calendar 为了性能原因对 set() 方法采取延缓计算的方法,
     */
    public static void test1() throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        cal1.set(2000, 7, 31, 0, 0 , 0); //2000-8-31
        cal1.set(Calendar.MONTH, Calendar.SEPTEMBER); //应该是 2000-9-31，也就是 2000-10-1
        cal1.set(Calendar.DAY_OF_MONTH, 30); //如果 Calendar 转化到 2000-10-1，那么现在的结果就该是 2000-10-30
        logger.info(cal1.getTime()); //输出的是2000-9-30，说明 Calendar 不是马上就刷新其内部的记录
    }

    /**
     * Calendar 对象的容错性，Lenient 设置
     我们知道特定的月份有不同的日期，当一个用户给出错误的日期时，Calendar 如何处理的呢？
     */
    public static void test2(){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2000, 1, 32, 0, 0, 0);
        logger.info(cal1.getTime());
        cal1.setLenient(false);
        cal1.set(2000, 1, 32, 0, 0, 0);
        logger.info(cal1.getTime());
    }

    public static void testCalendar() throws InterruptedException {
        //创建Calendar的方式
        Calendar now1 = Calendar.getInstance();
        Calendar now2 = new GregorianCalendar();
        Calendar now5 = new GregorianCalendar(2007, 10, 30, 16, 55, 44);//陷阱:Calendar的月份是0~11
        Calendar now6 = new GregorianCalendar(Locale.US);
        Calendar now7 = new GregorianCalendar(TimeZone.getTimeZone("GMT-8:00"));

        //通过日期和毫秒数设置Calendar
        now2.setTime(new Date());
        logger.info(now2);
        now2.setTimeInMillis(new Date().getTime());
        logger.info(now2);


        //定义日期的中文输出格式,并输出日期,hh小写是12小时制，HH大写是24小时制
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E", Locale.CHINA);
        logger.info("获取日期中文格式化化输出：" + df.format(now5.getTime()));
        System.out.println();

        logger.info("--------通过Calendar获取日期中年月日等相关信息--------");
        logger.info("获取年：" + now1.get(Calendar.YEAR));
        logger.info("获取月(月份是从0开始的)：" + (now1.get(Calendar.MONTH) + 1));
        logger.info("获取日：" + now1.get(Calendar.DAY_OF_MONTH));
        logger.info("获取时：" + now1.get(Calendar.HOUR));
        logger.info("获取分：" + now1.get(Calendar.MINUTE));
        logger.info("获取秒：" + now1.get(Calendar.SECOND));
        logger.info("获取上午、下午：" + (now1.get(Calendar.AM_PM) == 0 ? "上午" : "下午"));
        logger.info("获取星期数值(星期是从周日1开始的)：" + (now1.get(Calendar.DAY_OF_WEEK) - 1));
        System.out.println();

        logger.info("---------通用星期中文化转换，从一开始---------");
        String dayOfWeek[] = {"", "日", "一", "二", "三", "四", "五", "六"};
        logger.info("now1对象的星期是:" + dayOfWeek[now1.get(Calendar.DAY_OF_WEEK)]);
        System.out.println();

        logger.info("---------通用月份中文化转换,从0开始---------");
        String months[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        logger.info("now1对象的月份是: " + months[now1.get(Calendar.MONTH)]);
    }
}
