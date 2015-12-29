/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   test1.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.bianchengsixiang.chapter4;

/**
 * Description.
 * Created on  2015-08-11 上午7:45
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午7:45              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class Test1 {
    Test1(int i ){
        System.out.println("Creating Rock number " + i);


    }

}
class SimpleConstructor{
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new Test1(i);
        }
    }
}
