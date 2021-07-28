package com.itsm.java8;

import com.itsm.vo.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 * 1、创建Stream
 * 2、中间操作（Intermediate Operations）
 * 3、终止操作/终端操作（Terminal Operations）
 */
public class TestStreamAPI01 {
    private List<Employee> list = new ArrayList<>();

    @Before
    public void init() {
        list.add(new Employee("张三", 18, 9999.99));
        list.add(new Employee("李四", 34, 8888.88));
        list.add(new Employee("王五", 25, 7777.77));
        list.add(new Employee("赵六", 36, 15555.55));
        list.add(new Employee("田七", 17, 4444.44));
    }

    /**
     * 1、通过Collection系列集合提供的stream()或者parallelStream()方法
     */
    @Test
    public void test01() {
        final Stream<Employee> stream = list.stream();

    }

    /**
     * 2、通过Arrays类中的静态方法stream()获取数组流
     */
    @Test
    public void test02() {
        final Stream<Object> stream = Arrays.stream(list.toArray());
    }


    /**
     * 3、通过Stream类中的静态方法of()
     */
    @Test
    public void test03() {
        final Stream<Employee> stream = Stream.of(list.get(0), list.get(1), list.get(2));
        final Stream<Object> stream1 = Stream.of(list.toArray());
    }

    /**
     * 4、创建无限流（迭代）
     */
    @Test
    public void test04() {
        final Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        //Lambda can be replaced with method reference more... (Ctrl+F1)
//        iterate.forEach((x) -> System.out.println(x));
        iterate
                .limit(10)//中间操作
                .forEach(System.out::println);//终止操作
    }

    /**
     * 4、创建无限流（生成）
     */
    @Test
    public void test05() {
        final Stream<Double> generate = Stream.generate(Math::random);
        generate.limit(10).forEach(System.out::println);
    }

}
