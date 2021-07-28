package com.itsm.java8;

import com.itsm.vo.Employee;
import com.itsm.vo.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;

/**
 * 一、方法引用
 * 1、说明：
 * <p>
 * 　　若Lambda表达式的主体仅包含一个表达式，且该表达式仅调用了一个已经存在的方法，我们可以使用方法引用.
 * 　　方法引用的使用在很多情况下简化了lambda表达式
 * 2、前提：
 * 　　方法引用所使用方法的入参和返回值与lambda表达式实现的函数式接口的入参和返回值一致
 * 3、语法格式
 * 　　对象名：实例方法名（特定对象的实例方法引用）
 * 　　类名：静态方法名
 * 　　类名：实例方法名（类的任意对象的实例方法引用）
 * <p>
 * 二、构造器引用
 * 1、前提：
 * 　　需要调用构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 * 2、语法格式
 * 　　类名：new
 * <p>
 * 二、数组引用
 * 1、语法格式
 * 　　类名[]：new
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

        //lambda表达式的第一个入参为实例方法的调用者，后面的入参与实例方法的入参一致时，可以使用类名::实例方法名
        BiPredicate<String, String> biPredicate1 = String::equals;
        final boolean ab1 = biPredicate1.test(a, b);
        final boolean ac1 = biPredicate1.test(a, c);
        System.out.println("【a==b? " + ab1 + "】【a==c? " + ac1 + "】");

        System.out.println("-------------------");
        Function<User, String> function = (user) -> user.getName();
        final String userName = function.apply(new User("张三"));
        System.out.println("userName=" + userName);

        Function<User, List<String>> function1 = User::getHobbies;
        final List<String> hobbies = function1.apply(new User("张三", "乒乓球", "羽毛球", "游泳", "射击"));
        System.out.println("爱好：" + hobbies);
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

        Supplier<List<String>> supplier2 = () -> new ArrayList<String>();
        final List<String> list = supplier2.get();
        System.out.println(list);

        Supplier<List<String>> supplier3 = ArrayList<String>::new;
        final List<String> list1 = supplier3.get();
        System.out.println(list1);
    }

    /**
     * 数组引用
     */
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
