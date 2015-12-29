package com.darlen.bianchengsixiang.chapter8;

/**
 * Description.展示了如何比较两个对象，它将那些“要发生变化的东西”封装在内：
 * Created on  2015-08-28 上午7:50
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午7:50              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public interface Compare {
    boolean lessThan(Object lhs, Object rhs);
    boolean lessThanOrEqual(Object lhs, Object rhs);

}
