/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   SortVector.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.bianchengsixiang.chapter8;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Description.
 * Created on  2015-08-28 上午7:51
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午7:51              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class SortVector extends Vector {
    private Compare compare; // To hold the callback
    public SortVector(Compare comp) {
        compare = comp;
    }
    public void sort() {
        quickSort(0, size() - 1);
    }
    //快速排序算法
    private void quickSort(int left, int right) {
        if(right > left) {
            Object o1 = elementAt(right);
            int i = left - 1;
            int j = right;
            while(true) {
                while(compare.lessThan(
                        elementAt(++i), o1))
                    ;
                while(j > 0)
                    if(compare.lessThanOrEqual(
                            elementAt(--j), o1))
                        break; // out of while
                if(i >= j) break;
                swap(i, j);
            }
            swap(i , right);
            quickSort(left, i-1);
            quickSort(i+1, right);
        }
    }
    private void swap(int loc1, int loc2) {
        Object tmp = elementAt(loc1);
        setElementAt(elementAt(loc2), loc1);
        setElementAt(tmp, loc2);
    }

}

class StringSortTest {
    static class StringCompare implements Compare {
        public boolean lessThan(Object l, Object r) {
            return ((String)l).toLowerCase().compareTo(
                    ((String)r).toLowerCase()) < 0;
        }
        public boolean lessThanOrEqual(Object l, Object r) {
            return ((String)l).toLowerCase().compareTo(
                    ((String)r).toLowerCase()) <= 0;
        }
    }
    public static void main(String[] args) {
        SortVector sv =
                new SortVector(new StringCompare());
        sv.addElement("d");
        sv.addElement("A");
        sv.addElement("C");
        sv.addElement("c");
        sv.addElement("b");
        sv.addElement("B");
        sv.addElement("D");
        sv.addElement("a");
        sv.sort();
        Enumeration e = sv.elements();
        while(e.hasMoreElements())
            System.out.println(e.nextElement());
        //to ASCII A:65,a:97
        System.out.println("a".getBytes()[0]);;
        System.out.println("A".getBytes()[0]);;
    }
}

class StrSortVector {
    private SortVector v = new SortVector(
            // Anonymous inner class:
            new Compare() {
                public boolean
                lessThan(Object l, Object r) {
                    return
                            ((String)l).toLowerCase().compareTo(
                                    ((String)r).toLowerCase()) < 0;
                }
                public boolean
                lessThanOrEqual(Object l, Object r) {
                    return
                            ((String)l).toLowerCase().compareTo(
                                    ((String)r).toLowerCase()) <= 0;
                }
            }
    );
    private boolean sorted = false;
    public void addElement(String s) {
        v.addElement(s);
        sorted = false;
    }
    public String elementAt(int index) {
        if(!sorted) {
            v.sort();
            sorted = true;
        }
        return (String)v.elementAt(index);
    }
    public Enumeration elements() {
        if(!sorted) {
            v.sort();
            sorted = true;
        }
        return v.elements();
    }
    // Test it:
    public static void main(String[] args) {
        StrSortVector sv = new StrSortVector();
        sv.addElement("d");
        sv.addElement("A");
        sv.addElement("C");
        sv.addElement("c");
        sv.addElement("b");
        sv.addElement("B");
        sv.addElement("D");
        sv.addElement("a");
        Enumeration e = sv.elements();
        while(e.hasMoreElements())
            System.out.println(e.nextElement());
    }
} ///:~
