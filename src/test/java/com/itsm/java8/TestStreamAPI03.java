package com.itsm.java8;

import com.itsm.vo.Coder;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ��ֹ�������ն˲�����
 * һ��������ƥ�䣺
 * ����allMatch(): ����Ƿ�ƥ������Ԫ��
 * ����anyMatch()������Ƿ�����ƥ��һ��Ԫ��
 * ����noneMatch(): ����Ƿ�û��ƥ������Ԫ��
 * ����findFirst()�����ص�ǰ���еĵ�һ��Ԫ��
 * ����findAny()�����ص�ǰ���е�����Ԫ��
 * ����count()����������Ԫ�ص��ܸ���
 * ����max()�������������ֵ
 * ����min()������������Сֵ
 * ������Լ
 * ����T reduce(T identity, BinaryOperator<T> accumulator)
 * ����Optional<T> reduce(BinaryOperator<T> accumulator);
 * �����ռ�
 * ����collect(Collector)������ת��Ϊ������ʽ������һ��Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
 */
public class TestStreamAPI03 {
    private List<Coder> coderList = new ArrayList<>();

    @Before
    public void init() {
        coderList.add(new Coder("����", 30, 25000, Coder.Status.BUSY));
        coderList.add(new Coder("����", 24, 3500, Coder.Status.FREE));
        coderList.add(new Coder("����", 25, 8000, Coder.Status.VOCATION));
        coderList.add(new Coder("����", 36, 35000, Coder.Status.FREE));
        coderList.add(new Coder("����", 19, 4500, Coder.Status.BUSY));
    }

    @Test
    public void testAllmatch() {
        final boolean b = coderList.stream().allMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("allMatch:" + b);
    }

    @Test
    public void testAnyMatch() {
        final boolean b = coderList.stream().anyMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("anyMatch:" + b);
    }

    @Test
    public void testNoneMatch() {
        //noneMatch��allMatch�෴���ж��������Ԫ�أ����еĶ����ǣ�����true
        final boolean b = coderList.stream().noneMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("noneMatch:" + b);
    }

    @Test
    public void testFindFirst() {
        final Stream<Coder> stream = coderList.stream().sorted((coder01, coder02) -> Integer.compare(coder01.getAge(), coder02.getAge()));
        final Optional<Coder> first = stream.findFirst();
        System.out.println("������С�ĳ���Ա��" + first.get());
        System.out.println("---------------------------");

        /**
         * @see java.util.Comparator#comparingInt ���ص���һ��Comparator<T>
         */
        final Stream<Coder> stream1 = coderList.stream().sorted(Comparator.comparingInt(new ToIntFunction<Coder>() {
            @Override
            public int applyAsInt(Coder coder) {
                return coder.getAge();
            }
        }));
        System.out.println("������С�ĳ���Ա��" + stream1.findFirst().get());
        System.out.println("---------------------------");


        //Lambda���ʽ
        final Stream<Coder> stream2 = coderList.stream().sorted(Comparator.comparingInt(coder -> coder.getAge()));
        System.out.println("������С�ĳ���Ա��" + stream2.findFirst().get());
        System.out.println("---------------------------");


        //��������
        final Stream<Coder> stream3 = coderList.stream().sorted(Comparator.comparingInt(Coder::getAge));
        System.out.println("������С�ĳ���Ա��" + stream3.findFirst().get());
    }

    @Test
    public void testFindAny() {
        final Coder coder1 = coderList.parallelStream().filter(coder -> coder.getStatus().equals(Coder.Status.FREE))
                .findAny().get();
        System.out.println("���һ������״̬�ĳ���Ա��" + coder1);
    }


    @Test
    public void testGroupFunc() {
        final long count = coderList.stream().count();
        System.out.println("Ա��������" + count);

        final Optional<Coder> max = coderList.stream().max(Comparator.comparingInt(Coder::getAge));
        System.out.println("��������Ա��" + max.get());

        final Optional<Double> min = coderList.stream().map(Coder::getSalary).min(Double::compareTo);
        System.out.println("�������ٵ�Ա���Ĺ���" + min.get());
    }

    @Test
    public void testReduce() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final Integer sum = numList.stream().reduce(0, (x, y) -> x + y);
        System.out.println("�ܺͣ�" + sum);

        //todo ������Hadoop map-reduceģʽ
        final Optional<Double> salaryOptional = coderList.stream().map(Coder::getSalary).reduce(Double::sum);
        System.out.println("�ϰ�һ���µ�֧����" + salaryOptional.get());
    }


    @Test
    public void testCollect01() {
        final List<String> list = coderList.stream().map(Coder::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------");

        final Set<Coder.Status> set = coderList.stream().map(Coder::getStatus).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------");


        final TreeSet<String> treeSet = coderList.stream().map(Coder::getName).sorted().collect(Collectors.toCollection(TreeSet::new));
        treeSet.forEach(System.out::println);
        System.out.println("treeSet��----------------");

        //Redundant 'sorted' call: subsequent 'toCollection' call doesn't depend on the sort order more... (Ctrl+F1)
        final HashSet<String> hashSet = coderList.stream().map(Coder::getName).sorted().collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
        System.out.println("hashSet��----------------");
    }


    @Test
    public void testCollect02() {
        final Long count = coderList.stream().collect(Collectors.counting());
        System.out.println("����ʦ������" + count);

        final Double averageAge = coderList.stream().collect(Collectors.averagingDouble(Coder::getAge));
        System.out.println("����ʦƽ�����䣺" + averageAge);

        final Double sumSalary = coderList.stream().collect(Collectors.summingDouble(Coder::getSalary));
        System.out.println("�ϰ�һ���µĿ�����" + sumSalary);

        final Optional<Coder> maxAgeCoder = coderList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Coder::getAge)));
        System.out.println("��������Ա��" + maxAgeCoder.get());

        final Optional<Integer> minAge = coderList.stream().map(Coder::getAge).collect(Collectors.minBy(Integer::compare));
        System.out.println("������С��Ա������" + minAge.get());
    }

    /**
     * ����
     */
    @Test
    public void testCollect03() {
        final Map<Coder.Status, List<Coder>> map = coderList.stream().collect(Collectors.groupingBy(Coder::getStatus));
        // ��ͳ��Map������ʽ
        for (Map.Entry<Coder.Status, List<Coder>> entry : map.entrySet()) {
            System.out.println("k=" + entry.getKey());
            System.out.println("v=" + entry.getValue());
        }
        System.out.println("--------------------");

        //JDK8�ĵ�����ʽ
        map.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });
    }

    /**
     * �༶����
     */
    @Test
    public void testCollect04() {
        final Map<Coder.Status, Map<String, List<Coder>>> mapMap = coderList.stream().collect(Collectors.groupingBy(Coder::getStatus, Collectors.groupingBy(coder -> {
            if (coder.getSalary() >= 25000) {
                return "�߼�����Ա";
            } else {
                return "��������Ա";
            }
        })));

        mapMap.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((key, val) -> {
                System.out.println("\t" + key);
                System.out.println("\t\t" + val);
            });
            System.out.println("----------------------------------------");
        });
    }

    /**
     * ����
     */
    @Test
    public void testCollect05() {
        final Map<Boolean, List<Coder>> map = coderList.stream().collect(Collectors.partitioningBy(coder -> coder.getSalary() >= 25000));
        map.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("\tv=" + v);
        });
    }

    /**
     * ����summarizing
     */
    @Test
    public void testCollect06() {
        final DoubleSummaryStatistics summaryStatistics = coderList.stream().collect(Collectors.summarizingDouble(Coder::getSalary));
        System.out.println("summaryStatistics.getAverage()=" + summaryStatistics.getAverage());
        System.out.println("summaryStatistics.getCount()=" + summaryStatistics.getCount());
        System.out.println("summaryStatistics.getMax()=" + summaryStatistics.getMax());
        System.out.println("summaryStatistics.getMin()=" + summaryStatistics.getMin());
        System.out.println("summaryStatistics.getSum()=" + summaryStatistics.getSum());
    }

    /**
     *
     */
    @Test
    public void testCollect07() {
        final String result = coderList.stream().map(Coder::getName).collect(Collectors.joining(" "));
        System.out.println(result);
        final String result1 = coderList.stream().map(Coder::getName).collect(Collectors.joining(",", "prefix.", ".suffix"));
        System.out.println(result1);
    }

}
