package com.itsm.java8;

import com.itsm.vo.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * һ��Stream��������������
 * 1������Stream
 * 2���м������Intermediate Operations��
 * 3����ֹ����/�ն˲�����Terminal Operations��
 */
public class TestStreamAPI01 {
    private List<Employee> list = new ArrayList<>();

    @Before
    public void init() {
        list.add(new Employee("����", 18, 9999.99));
        list.add(new Employee("����", 34, 8888.88));
        list.add(new Employee("����", 25, 7777.77));
        list.add(new Employee("����", 36, 15555.55));
        list.add(new Employee("����", 17, 4444.44));
    }

    /**
     * 1��ͨ��Collectionϵ�м����ṩ��stream()����parallelStream()����
     */
    @Test
    public void test01() {
        final Stream<Employee> stream = list.stream();

    }

    /**
     * 2��ͨ��Arrays���еľ�̬����stream()��ȡ������
     */
    @Test
    public void test02() {
        final Stream<Object> stream = Arrays.stream(list.toArray());
    }


    /**
     * 3��ͨ��Stream���еľ�̬����of()
     */
    @Test
    public void test03() {
        final Stream<Employee> stream = Stream.of(list.get(0), list.get(1), list.get(2));
        final Stream<Object> stream1 = Stream.of(list.toArray());
    }

    /**
     * 4��������������������
     */
    @Test
    public void test04() {
        final Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        //Lambda can be replaced with method reference more... (Ctrl+F1)
//        iterate.forEach((x) -> System.out.println(x));
        iterate
                .limit(10)//�м����
                .forEach(System.out::println);//��ֹ����
    }

    /**
     * 4�����������������ɣ�
     */
    @Test
    public void test05() {
        final Stream<Double> generate = Stream.generate(Math::random);
        generate.limit(10).forEach(System.out::println);
    }

}
