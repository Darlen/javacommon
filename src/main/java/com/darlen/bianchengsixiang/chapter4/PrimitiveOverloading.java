/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   PrimitiveOverloading.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.bianchengsixiang.chapter4;

/**
 * Description.若观察这个程序的输出，就会发现常数值5被当作一个int值处理。所以假若可以使用一个过载的方法，
 * 就能获取它使用的int值。在其他所有情况下，若我们的数据类型“小于”方法中使用的自变量，
 * 就会对那种数据类型进行“转型”处理（如f5（5）--》long）。char获得的效果稍有些不同，
 * 这是由于假期它没有发现一个准确的char匹配，就会转型为int。
 若我们的自变量“大于”过载方法期望的自变量，这时又会出现什么情况呢？请看Demotion.java
 * Created on  2015-08-11 上午7:58
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午7:58              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class PrimitiveOverloading {
    // boolean can't be automatically converted
    static void prt(String s) {
        System.out.println(s);
    }

    void f1(char x) { prt("f1(char)"); }
    void f1(byte x) { prt("f1(byte)"); }
    void f1(short x) { prt("f1(short)"); }
    void f1(int x) { prt("f1(int)"); }
    void f1(long x) { prt("f1(long)"); }
    void f1(float x) { prt("f1(float)"); }
    void f1(double x) { prt("f1(double)"); }

    void f2(byte x) { prt("f2(byte)"); }
    void f2(short x) { prt("f2(short)"); }
    void f2(int x) { prt("f2(int)"); }
    void f2(long x) { prt("f2(long)"); }
    void f2(float x) { prt("f2(float)"); }
    void f2(double x) { prt("f2(double)"); }

    void f3(short x) { prt("f3(short)"); }
    void f3(int x) { prt("f3(int)"); }
    void f3(long x) { prt("f3(long)"); }
    void f3(float x) { prt("f3(float)"); }
    void f3(double x) { prt("f3(double)"); }

    void f4(int x) { prt("f4(int)"); }
    void f4(long x) { prt("f4(long)"); }
    void f4(float x) { prt("f4(float)"); }
    void f4(double x) { prt("f4(double)"); }

    void f5(long x) { prt("f5(long)"); }
    void f5(float x) { prt("f5(float)"); }
    void f5(double x) { prt("f5(double)"); }

    void f6(float x) { prt("f6(float)"); }
    void f6(double x) { prt("f6(double)"); }

    void f7(double x) { prt("f7(double)"); }

    void testConstVal() {
        prt("Testing with 5");
        f1(5);f2(5);f3(5);f4(5);f5(5);f6(5);f7(5);
    }
    void testChar() {
        char x = 'x';
        prt("char argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testByte() {
        byte x = 0;
        prt("byte argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testShort() {
        short x = 0;
        prt("short argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testInt() {
        int x = 0;
        prt("int argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testLong() {
        long x = 0;
        prt("long argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testFloat() {
        float x = 0;
        prt("float argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    void testDouble() {
        double x = 0;
        prt("double argument:");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
    }
    //主（数据）类型能从一个“较小”的类型自动转变成一个“较大”的类型
    public static void main(String[] args) {
        PrimitiveOverloading p =
                new PrimitiveOverloading();
        p.testConstVal();//int-->long-->float-->double
        p.testChar();//char-->int-->long-->float-->double
        p.testByte();//byte-->short-->int-->long-->float-->double
        p.testShort();//short-->int-->long-->float-->double
        p.testInt();//int-->long-->float-->double
        p.testLong();//long-->float-->double
        p.testFloat();//float-->double
        p.testDouble();//double


    }

}
/*
       结果：
        D:\Usage\Java\jdk1.7.0_71\bin\java -Didea.launcher.port=7534 "-Didea.launcher.bin.path=D:\Usage\JetBrains\IntelliJ IDEA 12.1.6\bin" -Dfile.encoding=UTF-8 -classpath "D:\Usage\Java\jdk1.7.0_71\jre\lib\charsets.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\deploy.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\javaws.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\jce.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\jfr.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\jfxrt.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\jsse.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\management-agent.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\plugin.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\resources.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\rt.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\access-bridge.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\dnsns.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\jaccess.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\localedata.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\sunec.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\sunjce_provider.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\sunmscapi.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\sunpkcs11.jar;D:\Usage\Java\jdk1.7.0_71\jre\lib\ext\zipfs.jar;F:\java_workspace\javacommon\target\classes;D:\Usage\Maven\repo\m2\junit\junit\4.12\junit-4.12.jar;D:\Usage\Maven\repo\m2\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;D:\Usage\Maven\repo\m2\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\Usage\Maven\repo\m2\com\alibaba\fastjson\1.2.4\fastjson-1.2.4.jar;D:\Usage\Maven\repo\m2\commons-io\commons-io\2.4\commons-io-2.4.jar;D:\Usage\Maven\repo\m2\commons-beanutils\commons-beanutils\1.9.2\commons-beanutils-1.9.2.jar;D:\Usage\Maven\repo\m2\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;D:\Usage\Maven\repo\m2\commons-collections\commons-collections\3.2.1\commons-collections-3.2.1.jar;D:\Usage\Maven\repo\m2\commons-lang\commons-lang\2.6\commons-lang-2.6.jar;D:\Usage\JetBrains\IntelliJ IDEA 12.1.6\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain com.darlen.bianchengsixiang.chapter4.PrimitiveOverloading
Testing with 5
f1(int)
f2(int)
f3(int)
f4(int)
f5(long)
f6(float)
f7(double)
char argument:
f1(char)
f2(int)
f3(int)
f4(int)
f5(long)
f6(float)
f7(double)
byte argument:
f1(byte)
f2(byte)
f3(short)
f4(int)
f5(long)
f6(float)
f7(double)
short argument:
f1(short)
f2(short)
f3(short)
f4(int)
f5(long)
f6(float)
f7(double)
int argument:
f1(int)
f2(int)
f3(int)
f4(int)
f5(long)
f6(float)
f7(double)
long argument:
f1(long)
f2(long)
f3(long)
f4(long)
f5(long)
f6(float)
f7(double)
float argument:
f1(float)
f2(float)
f3(float)
f4(float)
f5(float)
f6(float)
f7(double)
double argument:
f1(double)
f2(double)
f3(double)
f4(double)
f5(double)
f6(double)
f7(double)

Process finished with exit code 0

         */
