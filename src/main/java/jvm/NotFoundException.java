/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   NotFoundException.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * Description.
 * Created on  2015-12-22 下午10:31
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午10:31              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class NotFoundException {

    public static void main(String[] args) {
        try{
            NotFoundException n = new NotFoundException();
            n.getClassLoaderPath();
            Integer integer = (Integer) m.get("a");
            System.out.println(integer);
            Class.forName("abc");
        }catch (Exception e){
            e.printStackTrace();;
        }

    }

    private  void getClassLoaderPath(){
        System.out.println(this.getClass().getClassLoader().getResource(""));

    }

    private static Map m = new HashMap(){{put("a","2");}};
}
