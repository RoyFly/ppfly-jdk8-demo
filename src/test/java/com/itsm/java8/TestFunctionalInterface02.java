package com.itsm.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8���õ��Ĵ���ʽ�ӿ�
 * 1��Consumer<T> �������ͽӿ�
 * �������� void accept(T t);
 * 2��Supplier<T>�������ͽӿ�
 * ��������  T get();
 * 3��Function<T, R>�������ͽӿ�
 * ��������  R apply(T t);
 * 4��Predicate<T>�������ͽӿ�
 * ��������  boolean test(T t);
 */
public class TestFunctionalInterface02 {

    @Test
    public void testConsumer() {
        seeADoctor(5000, (money) -> {
            System.out.println("�������������ҵ���߼�");
            System.out.println("����" + money + "Ԫ�ĺ��");
        });
    }

    /**
     * ����
     *
     * @param money
     * @param consumer
     */
    private void seeADoctor(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void testSupplier() {
        //�˴��Ĺ����ǣ��������
        final List<Integer> integerList = genIntegerList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    /**
     * ����һ���Ĺ��򣬲���ָ���������������뼯���в�����
     */
    private List<Integer> genIntegerList(int num, Supplier<Integer> supplier) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            integerList.add(supplier.get());
        }
        return integerList;
    }

    @Test
    public void testFunction() {
        String originalVal = "\t\t\t wolcome to jdk8  ";
        final String uperCaseStr = convert(originalVal, (str) -> str.toUpperCase());
        System.out.println("��"+originalVal + "�����ա�ת��Ϊ��д��ĸ����������ת�����:��" + uperCaseStr+"��");

        final String trimStr = convert(originalVal, (str) -> str.trim());
        System.out.println("��"+originalVal + "�����ա�ȥ�����ҿո񡱺�������ת�����:��" + trimStr+"��");
    }


    /***
     * ���պ�������ת��������ַ���
     * @param originalVal ������ַ���
     * @param function ����������
     * @return
     */
    private String convert(String originalVal, Function<String, String> function) {
        final String resultVal = function.apply(originalVal);
        return resultVal;
    }


    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("Lambda", "FunctionalInterface", "java8", "StreamAPI");
        final List<String> result = filterStr(list, (str) -> str.length() > 6);
        for (String s : result) {
            System.out.println(s);
        }
    }

    /**
     * �������з��϶��Թ�����ַ�����װ���µļ��Ϸ���
     *
     * @param list
     * @param predicate
     * @return
     */
    private List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }


}
