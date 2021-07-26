package com.itsm.java8;

import com.itsm.vo.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 一、Lambda表达式的语法：
 * ①新的操作符“->”:箭头操作符或Lambda操作符
 * ②“->”将Lambda表达式拆分成两部分：
 * 左侧：参数列表
 * 右侧：Lambda体
 * 　　　语法格式场景一：无参，无返回值（Runnable）
 * 　　　语法格式场景二：一个参数，无返回值（Consumer）
 * 　　　　　　　　　　　 参数列表的参数可以省略不写，JVM编译器可以通过上下文推断出数据类型，即（“类型推断”）
 * 　　　　　　　　　　　 参数小括号可以省略不写
 * 　　　语法格式场景三：有两个及以上参数，有返回值，并且Lambda体中有多条语句
 * 　　　　　　　　　　　　大括号、return都不可以省略
 * 　　　语法格式场景四：有返回值，并且Lambda体中只有一条语句
 * 　　　　　　　　　　　　如果省略大括号，return也必须省略
 * <p>
 * 二、Lambda表达式的语法总结：
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 */
public class TestLambda02 {

    @Test
    public void test01() {
        //局部内部类和匿名内部类访问的局部变量必须由final修饰，java8开始，可以不加final修饰符，由系统默认添加。
        //java将这个功能称为：Effectively final 功能。
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
        consumer.accept("consumer........参数类型推断");
        consumer3.accept("consumer2........一个参数");
        consumer2.accept("consumer3........一个参数小括号可以省略不写");
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
     * jdk1.8类型推断升级
     */
    @Test
    public void test05() {
        //jdk1.7会编译报错
        testTypeInference(new HashMap<>());
    }

    private void testTypeInference(Map<String, Integer> map) {

    }

}
