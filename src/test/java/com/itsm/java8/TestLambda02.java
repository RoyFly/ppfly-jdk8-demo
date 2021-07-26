package com.itsm.java8;

import com.itsm.vo.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * һ��Lambda���ʽ���﷨��
 * ���µĲ�������->��:��ͷ��������Lambda������
 * �ڡ�->����Lambda���ʽ��ֳ������֣�
 * ��ࣺ�����б�
 * �ҲࣺLambda��
 * �������﷨��ʽ����һ���޲Σ��޷���ֵ��Runnable��
 * �������﷨��ʽ��������һ���������޷���ֵ��Consumer��
 * ���������������������� �����б�Ĳ�������ʡ�Բ�д��JVM����������ͨ���������ƶϳ��������ͣ������������ƶϡ���
 * ���������������������� ����С���ſ���ʡ�Բ�д
 * �������﷨��ʽ�������������������ϲ������з���ֵ������Lambda�����ж������
 * �����������������������������š�return��������ʡ��
 * �������﷨��ʽ�����ģ��з���ֵ������Lambda����ֻ��һ�����
 * ���������������������������ʡ�Դ����ţ�returnҲ����ʡ��
 * <p>
 * ����Lambda���ʽ���﷨�ܽ᣺
 * ������������һ����ʡ
 * ����������ƶ�����ʡ
 * ��������ʡ��ʡ
 */
public class TestLambda02 {

    @Test
    public void test01() {
        //�ֲ��ڲ���������ڲ�����ʵľֲ�����������final���Σ�java8��ʼ�����Բ���final���η�����ϵͳĬ����ӡ�
        //java��������ܳ�Ϊ��Effectively final ���ܡ�
        //Variable 'num' is accessed from within inner class, needs to be final or effectively final
        int num = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable........anonymous function" + num);
            }
        };
        runnable.run();
        System.out.println("------------------------------");
        Runnable runnable1 = () -> System.out.println("Runnable........lambda" + num);
        runnable1.run();
    }


    @Test
    public void test02() {
        Consumer<String> consumer = (String str) -> System.out.println(str);
        Consumer<String> consumer2 = (str) -> System.out.println(str);
        Consumer<String> consumer3 = str -> System.out.println(str);
        consumer.accept("consumer........���������ƶ�");
        consumer3.accept("consumer2........һ������");
        consumer2.accept("consumer3........һ������С���ſ���ʡ�Բ�д");
    }


    @Test
    public void test03() {
        Comparator<Person> comparator = (p1, p2) -> {
            final int result = Integer.compare(p1.getAge(), p2.getAge());
            if (result == 0) {
                return p1.getName().compareTo(p2.getName());
            }
            return result;
        };
        List<Person> list = Arrays.asList(
                new Person("zhejiang", 19, "zhejiang@163.com"),
                new Person("lisi", 18, "lisi@163.com"),
                new Person("zhaoliu", 19, "zhaoliu@163.com")
        );
        Collections.sort(list, comparator);
        System.out.println(list);
    }

    @Test
    public void test04() {
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        List<Integer> list = Arrays.asList(4, 3, 5, 6, 1);
        Collections.sort(list, comparator);
        System.out.println(list);
    }

    /**
     * jdk1.8�����ƶ�����
     */
    @Test
    public void test05() {
        //jdk1.7����뱨��
        testTypeInference(new HashMap<>());
    }

    private void testTypeInference(Map<String, Integer> map) {

    }

}
