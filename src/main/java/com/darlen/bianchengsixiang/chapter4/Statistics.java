/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   Statistics.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.bianchengsixiang.chapter4;

import java.util.Hashtable;

/**
 * Description.作为应用散列表的一个例子，可考虑用一个程序来检验Java的Math.random()方法的随机性到底如何。
 * 在理想情况下，它应该产生一系列完美的随机分布数字。但为了验证这一点，我们需要生成数量众多的随机数字，
 * 然后计算落在不同范围内的数字多少。散列表可以极大简化这一工作，因为它能将对象同对象关联起来
 * （此时是将Math.random()生成的值同那些值出现的次数关联起来）
 * Created on  2015-08-27 上午7:57
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午7:57              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class Statistics {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        for(int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            Integer r =
                    new Integer((int)(Math.random() * 20));
            if(ht.containsKey(r))
                ((Counter)ht.get(r)).i++;
            else
                ht.put(r, new Counter());
        }
        System.out.println(ht);
    }

}
class Counter {
    int i = 1;
    public String toString() {
        return Integer.toString(i);
    }
}

