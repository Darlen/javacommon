/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   IPPTest.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.mianshi;

import org.apache.log4j.Logger;

/**
 * Description. 这是一个有关i++面试问题的集合,这是一款最经典也是最常见的问题
 * 这里我总结下我的个人方法：从左到右法，到哪哪就是多少
 * Created on  2015-08-08 下午11:02
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:02              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class IPPTest {
    private static Logger logger = Logger.getLogger(IPPTest.class);

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * 分析：从左到右法
     * 1.i++,从左往右，首先是i，然后是++，故首先i=1，++之后就是2了
     * 2.++i,目前i已经是2，那么,是++，故i变成了3，
     * 3. 1结果+2结果那么b=4
     */
    public static void test1() {
        logger.info("开始执行test1方法...");
        int i = 1;
        int b = i++ + ++i;
        logger.debug("i=" + i + ";b=" + b);//i=3;b=4
        logger.info("结束执行test1方法...");
    }

    /**
     * from :http://www.oschina.net/question/562215_173831
     2015-08-08 23:24:05,562 [DEBUG] 1：i=1;i=1
     2015-08-08 23:24:05,562 [DEBUG] 2：i=1;i++=1
     2015-08-08 23:24:05,563 [DEBUG] 3：i=1;++i=2
     2015-08-08 23:24:05,563 [DEBUG] 4：i++=1;i=1
     2015-08-08 23:24:05,563 [DEBUG] 5：i++=1;i++=2
     2015-08-08 23:24:05,563 [DEBUG] 6：i++=1;++i=3
     2015-08-08 23:24:05,564 [DEBUG] 7：++i=2;i=2
     2015-08-08 23:24:05,564 [DEBUG] 8：++i=2;i++=2
     2015-08-08 23:24:05,565 [DEBUG] 9：++i=2 ++i = 3
     */
    public static void test2() {
        logger.info("开始执行test2方法...");
        int i;
        i = 1;
        logger.debug("1：i=" + i + ";i=" + i);//1 1
        i = 1;
        logger.debug("2：i=" + i + ";i++=" + i++);//1 1
        i = 1;
        logger.debug("3：i=" + i + ";++i=" + ++i);//1 2
        i = 1;
        logger.debug("4：i++=" + i + ";i=" + i);//1 2
        i = 1;
        logger.debug("5：i++=" + i++ + ";i++=" + i++);//1 2
        i = 1;
        logger.debug("6：i++=" + i++ + ";++i=" + ++i);//1 3
        i = 1;
        logger.debug("7：++i=" + ++i + ";i=" + i);//2 2
        i = 1;
        logger.debug("8：++i=" + ++i + ";i++=" + i++);//2 2
        i = 1;
        logger.debug("9：++i=" + ++i + " ++i = " + ++i);//2 3
        logger.info("结束执行test2方法...");
    }
}
