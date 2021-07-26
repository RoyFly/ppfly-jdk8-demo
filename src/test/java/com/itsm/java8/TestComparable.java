package com.itsm.java8;

import com.itsm.vo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable {

    /**
     * 内部排序
     */
    @Test
    public void test01() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(14);
        list.add(30);
        list.add(3);
        list.add(12);
        /**
         * 内部排序，排序规则由Integer定好，我们无法修改，只能升序排列
         */
        Collections.sort(list);
        System.out.println(list);
    }


    @Test
    public void test02() {
        List<Person> list = new ArrayList<Person>();
        Person zhangsan = new Person("zhangsan", 18, "zhangsan@163.com");
        Person lisi = new Person("lisi", 20, "lisi@163.com");
        Person wangwu = new Person("wangwu", 19, "wangwu@163.com");
        Person zhaoliu = new Person("zhaoliu", 20, "zhaoliu@163.com");
        list.add(zhangsan);
        list.add(lisi);
        list.add(wangwu);
        list.add(zhaoliu);
        Collections.sort(list);
        System.out.println(list);
    }
}
