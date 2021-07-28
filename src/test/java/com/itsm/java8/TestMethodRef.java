package com.itsm.java8;

import com.itsm.vo.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * 一、方法引用
 * 1、说明：
 * 　　若Lambda体中的内容已经有方法实现了，我们可以使用方法引用.
 * 　　方法引用的使用在很多情况下简化了lambda表达式
 * 2、前提：
 * 　　Lambda表达式中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * 3、语法格式
 * 　　对象名：实例方法名
 * 　　类名：静态方法名
 * 　　类名：实例方法名
 * <p>
 * 二、构造器引用
 * 1、前提：
 * 　　需要调用构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 * 2、语法格式
 * 　　对象名：实例方法名
 * <p>
 * 二、数组引用
 * 1、语法格式
 * 　　对象名：实例方法名
 */
public class TestMethodRef {

    /**
     * 对象名：实例方法名
     */
    @Test
    public void test01() {
        final PrintStream out = System.out;
        Consumer<String> consumer = (str) -> out.println(str);
        consumer.accept("test01...1");

        Consumer<String> consumer1 = out::println;
        consumer1.accept("test01...2");

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("test01...3");
    }

    /**
     * 对象名：实例方法名
     */
    @Test
    public void test02() {
        Employee employee = new Employee("张三", 25, 5500d);
        Supplier<String> supplier = () -> employee.getName();
        final String name = supplier.get();
        System.out.println("name=" + name);

        Supplier<Integer> supplier1 = employee::getAge;
        final Integer age = supplier1.get();
        System.out.println("age=" + age);
    }

    /**
     * 类名：静态方法名
     */
    @Test
    public void test03() {
        List<Integer> list = Arrays.asList(3, 6, 4, 5, 2);
        Comparator<Integer> comparator = (x, y) -> -Integer.compare(x, y);
        Collections.sort(list, comparator);
        System.out.println(list);

        Comparator<Integer> comparator1 = Integer::compare;
        Collections.sort(list, comparator1);
        System.out.println(list);
    }

    /**
     * 类名：实例方法名
     */
    @Test
    public void test04() {
        String a = "methodref";
        String b = "hellow world";
        String c = "methodref";
        BiPredicate<String, String> biPredicate = (str1, str2) -> str1.equals(str2);
        final boolean ab = biPredicate.test(a, b);
        final boolean ac = biPredicate.test(a, c);
        System.out.println("【a==b? " + ab + "】【a==c? " + ac + "】");

        //若Lambda表达式参数列表的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用类名::实例方法名
        BiPredicate<String, String> biPredicate1 = String::equals;
        final boolean ab1 = biPredicate1.test(a, b);
        final boolean ac1 = biPredicate1.test(a, c);
        System.out.println("【a==b? " + ab1 + "】【a==c? " + ac1 + "】");
    }

    /**
     * 构造器引用
     */
    @Test
    public void test05() {
        Supplier<Employee> supplier = () -> new Employee();
        final Employee employee = supplier.get();
        System.out.println(employee);

        Supplier<Employee> supplier1 = Employee::new;
        final Employee employee1 = supplier1.get();
        System.out.println(employee1);

        Function<String, Employee> function = (name) -> new Employee(name);
        final Employee employee2 = function.apply("张三");
        System.out.println(employee2);

        Function<String, Employee> function1 = Employee::new;
        final Employee employee3 = function1.apply("张三");
        System.out.println(employee3);

        BiFunction<String, Integer, Employee> biFunction = (name, age) -> new Employee(name, age);
        final Employee employee4 = biFunction.apply("张三", 23);
        System.out.println(employee4);

        BiFunction<String, Integer, Employee> biFunction1 = Employee::new;
        final Employee employee5 = biFunction1.apply("张三", 23);
        System.out.println(employee5);
    }

    @Test
    public void test06() {
        String[] arr = new String[2];
        arr[0] = "hellow";
        arr[1] = "world";
        System.out.println(arr[0] + " " + arr[1]);

        Function<Integer, String[]> function = (x) -> new String[x];
        final String[] array = function.apply(10);
        System.out.println(array.length);

        Function<Integer, String[]> function2 = String[]::new;
        final String[] array2 = function2.apply(20);
        System.out.println(array2.length);

        Function<Integer, Employee[]> function3 = Employee[]::new;
        final Employee[] array3 = function3.apply(5);
        System.out.println(array3.length);
    }
}
