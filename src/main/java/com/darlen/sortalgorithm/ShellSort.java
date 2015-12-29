/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   ShellSort.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.sortalgorithm;

import org.apache.log4j.Logger;


/**
 * Refer: http://blog.csdn.net/qy1387/article/details/7752973
 * Description. 希尔排序
 * <p>
 * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
 * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）
 * 对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
 * </p>
 * Created on  2015-12-29 下午8:45
 * -------------------------------------------------------------------------
 * 版本     修改时间        作者         修改内容 
 * 1.0.0        下午8:45              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class ShellSort<T> {
    private static Logger logger = Logger.getLogger(ShellSort.class);

    public static void main(String[] args) {
        int a[] = {1, 54, 6, 5, 20};
        shellSortAlgorithm(a);
    }

    public static void shellSortAlgorithm(int[] a) {
        int temp;
        double d1 = a.length;
        int n = 1;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            logger.info("\t#第" + n + "次遍历前，d = "+d+", 当前的数组是：" + arrayToString(a, ","));
            for (int x = 0; x < d; x++) {
                logger.info("x = " + x);
                /*
                *   i 指的是两个比较的值中最后一个坐标，j 表示两个比较值中前一个坐标，
                *   temp记录最后一个坐标的值，每次向前移动d个位置，直到大坐标的值大于小坐标
                 */
                for (int i = x + d; i < a.length; i += d) {
                    int j = i - d;
                    temp = a[i];
                    logger.info("\t##i = " + i + ",j = " + j + ",d = " + d + ", a[j] = " + a[j] + ", a[j+d] = " + a[j + d] + ", temp=" + temp + ", temp < a[j] : " + (temp < a[j]));
                    /*
                    * 如果最后一个坐标的值小于前一个，则执行交换，并使坐标向后（小坐标）移动d个位置，
                    * 每次拿大坐标跟小坐标相比较，直到小坐标的值比大坐标的值小为止
                     */
                    for (; j >= 0 && temp < a[j]; j -= d) {
                        logger.info("\t\t###交换前的值，a[j+d = " + (j + d) + "] = " + a[j + d] + ", a[j = " + j + "] = " + a[j] + ", d = " + d);
                        a[j + d] = a[j];
                        a[j] = temp;
                        logger.info("\t\t###交换后的值，a[j+d = " + (j + d) + "] = " + a[j + d] + ", a[j = " + j + "] = " + a[j] + ", d = " + d);
                    }
                }
            }
            logger.info("\t#第" + n + "次遍历后，当前的数组是：" + arrayToString(a, ","));
            logger.info("============" + n + "================");
            n++;
            if (d == 1) {
                break;
            }
        }
        logger.info("最终的数组是：" + arrayToString(a, ","));

    }

    public static String arrayToString(int[] values, String delim) {
        String sRet = "";
        if (values == null || values.length == 0) {
            return sRet;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            sb.append(delim).append(values[i]);
        }
        sRet = sb.toString();
        return sRet;
    }

}

/**
 * 参考分析结果
 2015-12-29 23:12:20,122 [INFO] 	#第1次遍历前，d = 3, 当前的数组是：1,54,6,5,20
 2015-12-29 23:12:20,127 [INFO] x = 0
 2015-12-29 23:12:20,129 [INFO] 	##i = 3,j = 0,d = 3, a[j] = 1, a[j+d] = 5, temp=5, temp < a[j] : false
 2015-12-29 23:12:20,130 [INFO] x = 1
 2015-12-29 23:12:20,130 [INFO] 	##i = 4,j = 1,d = 3, a[j] = 54, a[j+d] = 20, temp=20, temp < a[j] : true
 2015-12-29 23:12:20,130 [INFO] 		###交换前的值，a[j+d = 4] = 20, a[j = 1] = 54, d = 3
 2015-12-29 23:12:20,131 [INFO] 		###交换后的值，a[j+d = 4] = 54, a[j = 1] = 20, d = 3
 2015-12-29 23:12:20,131 [INFO] x = 2
 2015-12-29 23:12:20,131 [INFO] 	#第1次遍历后，当前的数组是：1,20,6,5,54
 2015-12-29 23:12:20,131 [INFO] ============1================
 2015-12-29 23:12:20,132 [INFO] 	#第2次遍历前，d = 2, 当前的数组是：1,20,6,5,54
 2015-12-29 23:12:20,132 [INFO] x = 0
 2015-12-29 23:12:20,132 [INFO] 	##i = 2,j = 0,d = 2, a[j] = 1, a[j+d] = 6, temp=6, temp < a[j] : false
 2015-12-29 23:12:20,133 [INFO] 	##i = 4,j = 2,d = 2, a[j] = 6, a[j+d] = 54, temp=54, temp < a[j] : false
 2015-12-29 23:12:20,133 [INFO] x = 1
 2015-12-29 23:12:20,133 [INFO] 	##i = 3,j = 1,d = 2, a[j] = 20, a[j+d] = 5, temp=5, temp < a[j] : true
 2015-12-29 23:12:20,137 [INFO] 		###交换前的值，a[j+d = 3] = 5, a[j = 1] = 20, d = 2
 2015-12-29 23:12:20,137 [INFO] 		###交换后的值，a[j+d = 3] = 20, a[j = 1] = 5, d = 2
 2015-12-29 23:12:20,137 [INFO] 	#第2次遍历后，当前的数组是：1,5,6,20,54
 2015-12-29 23:12:20,137 [INFO] ============2================
 2015-12-29 23:12:20,138 [INFO] 	#第3次遍历前，d = 1, 当前的数组是：1,5,6,20,54
 2015-12-29 23:12:20,138 [INFO] x = 0
 2015-12-29 23:12:20,138 [INFO] 	##i = 1,j = 0,d = 1, a[j] = 1, a[j+d] = 5, temp=5, temp < a[j] : false
 2015-12-29 23:12:20,138 [INFO] 	##i = 2,j = 1,d = 1, a[j] = 5, a[j+d] = 6, temp=6, temp < a[j] : false
 2015-12-29 23:12:20,139 [INFO] 	##i = 3,j = 2,d = 1, a[j] = 6, a[j+d] = 20, temp=20, temp < a[j] : false
 2015-12-29 23:12:20,139 [INFO] 	##i = 4,j = 3,d = 1, a[j] = 20, a[j+d] = 54, temp=54, temp < a[j] : false
 2015-12-29 23:12:20,139 [INFO] 	#第3次遍历后，当前的数组是：1,5,6,20,54
 2015-12-29 23:12:20,139 [INFO] ============3================
 2015-12-29 23:12:20,140 [INFO] 最终的数组是：1,5,6,20,54
 */
