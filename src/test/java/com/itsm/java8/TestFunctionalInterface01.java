package com.itsm.java8;

import com.itsm.function.MyFunction;
import com.itsm.function.MyFunction2;
import org.junit.Test;

/**
 * һ������ʽ�ӿ�@FunctionalInterface:
 * ���壺�ӿ���ֻ��һ�������Ľӿڳ�Ϊ����ʽ�ӿ�
 * ʹ��ע��@FunctionalInterface���Σ����Լ��ӿ��Ƿ��Ǻ���ʽ�ӿ�
 * Lambda���ʽ��Ҫ����ʽ�ӿڵ�֧��
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
     * ���ڴ����ַ���
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
        System.out.println("�ӷ���" + value);

        final Long value1 = getCalculatedValue(100l, 200l, (x1, x2) -> x1 * x2);
        System.out.println("�˷���" + value1);
    }

    /**
     * ���ڼ�������Long�����ֲ�����Ľ��
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
