package com.itsm.java8;

import com.itsm.function.MyFunction;
import com.itsm.function.MyFunction2;
import org.junit.Test;

/**
 * 一、函数式接口@FunctionalInterface:
 * 定义：接口中只有一个方法的接口称为函数式接口
 * 使用注解@FunctionalInterface修饰，可以检查接口是否是函数式接口
 * Lambda表达式需要函数式接口的支持
 */
public class TestFunctionalInterface01 {

    @Test
    public void test01() {
        final String trimStr = convertStr(" Welcom to FunctionalInterface\t\t\t ", (str) -> str.trim());
        System.out.println(trimStr);

        final String upperCaseStr = convertStr(" Welcom to FunctionalInterface\t\t\t ", (str) -> str.toUpperCase());
        System.out.println(upperCaseStr);

    }

    /**
     * 用于处理字符串
     *
     * @param value
     * @return
     */
    private String convertStr(String value, MyFunction myFunction) {
        final String str = myFunction.convert(value);
        return str;
    }

    @Test
    public void test02() {
        final Long value = getCalculatedValue(100l, 200L, (x1, x2) -> x1 + x2);
        System.out.println("加法：" + value);

        final Long value1 = getCalculatedValue(100l, 200l, (x1, x2) -> x1 * x2);
        System.out.println("乘法：" + value1);
    }

    /**
     * 用于计算两个Long型数字操作后的结果
     *
     * @param v1
     * @param v2
     * @param myFunction2
     * @return
     */
    private Long getCalculatedValue(Long v1, Long v2, MyFunction2<Long, Long> myFunction2) {
        final Long value = myFunction2.getCalculatedValue(v1, v2);
        return value;
    }
}
