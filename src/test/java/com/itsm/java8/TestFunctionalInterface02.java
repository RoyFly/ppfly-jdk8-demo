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
 * java8内置的四大函数式接口
 * 1、Consumer<T> ：消费型接口
 * 　　　　 void accept(T t);
 * 2、Supplier<T>：供给型接口
 * 　　　　  T get();
 * 3、Function<T, R>：函数型接口
 * 　　　　  R apply(T t);
 * 4、Predicate<T>：断言型接口
 * 　　　　  boolean test(T t);
 */
public class TestFunctionalInterface02 {

    @Test
    public void testConsumer() {
        seeADoctor(5000, (money) -> {
            System.out.println("这里是你的其他业务逻辑");
            System.out.println("包了" + money + "元的红包");
        });
    }

    /**
     * 看病
     *
     * @param money
     * @param consumer
     */
    private void seeADoctor(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void testSupplier() {
        //此处的规则是：随机产生
        final List<Integer> integerList = genIntegerList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    /**
     * 按照一定的规则，产生指定个数的整数放入集合中并返回
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
        System.out.println("【"+originalVal + "】按照“转换为大写字母”函数规则转换结果:【" + uperCaseStr+"】");

        final String trimStr = convert(originalVal, (str) -> str.trim());
        System.out.println("【"+originalVal + "】按照“去除左右空格”函数规则转换结果:【" + trimStr+"】");
    }


    /***
     * 按照函数规则转换传入的字符串
     * @param originalVal 传入的字符串
     * @param function 函数规则类
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
     * 将集合中符合断言规则的字符串组装成新的集合返回
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
