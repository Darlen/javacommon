/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   StaticInitialization.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.bianchengsixiang.chapter4;

/**
 * Description.
 * static初始化只有在必要的时候才会进行。如果不创建一个Table对象，而且永远都不引用Table.b1或Table.b2，那么static Bowl b1和b2永远都不会创建。然而，只有在创建了第一个Table对象之后（或者发生了第一次static访问），它们才会创建。在那以后，static对象不会重新初始化。
 初始化的顺序是首先static（如果它们尚未由前一次对象创建过程初始化），接着是非static对象。大家可从输出结果中找到相应的证据。
 在这里有必要总结一下对象的创建过程。请考虑一个名为Dog的类：
 (1) 类型为Dog的一个对象首次创建时，或者Dog类的static方法／static字段首次访问时，Java解释器必须找到Dog.class（在事先设好的类路径里搜索）。
 (2) 找到Dog.class后（它会创建一个Class对象，这将在后面学到），它的所有static初始化模块都会运行。因此，static初始化仅发生一次——在Class对象首次载入的时候。
 (3) 创建一个new Dog()时，Dog对象的构建进程首先会在内存堆（Heap）里为一个Dog对象分配足够多的存储空间。
 (4) 这种存储空间会清为零，将Dog中的所有基本类型设为它们的默认值（零用于数字，以及boolean和char的等价设定）。
 (5) 进行字段定义时发生的所有初始化都会执行。
 (6) 执行构建器。正如第6章将要讲到的那样，这实际可能要求进行相当多的操作，特别是在涉及继承的时候。
 * Created on  2015-08-12 上午8:08
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午8:08              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class StaticInitialization {
    public static void main(String[] args) {
        System.out.println(
                "Creating new Cupboard() in main");
        new Cupboard();
        System.out.println(
                "Creating new Cupboard() in main");
        new Cupboard();
        t2.f2(1);
        t3.f3(1);
    }
    static Table t2 = new Table();
    static Cupboard t3 = new Cupboard();

}

class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }
    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Table {
    static Bowl b1 = new Bowl(1);
    Table() {
        System.out.println("Table()");
        b2.f(1);
    }
    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }
    static Bowl b2 = new Bowl(2);
}

class Cupboard {
    Bowl b3 = new Bowl(3);
    static Bowl b4 = new Bowl(4);
    Cupboard() {
        System.out.println("Cupboard()");
        b4.f(2);
    }
    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }
    static Bowl b5 = new Bowl(5);
}

/**
 * Bowl(1)
 Bowl(2)
 Table()
 f(1)
 Bowl(4)
 Bowl(5)
 Bowl(3)
 Cupboard()
 f(2)
 Creating new Cupboard() in main
 Bowl(3)
 Cupboard()
 f(2)
 Creating new Cupboard() in main
 Bowl(3)
 Cupboard()
 f(2)
 f2(1)
 f3(1)

 */
