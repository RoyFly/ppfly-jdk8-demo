package com.itsm.java8;

import com.itsm.vo.Employee;
import com.itsm.vo.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;

/**
 * һ����������
 * 1��˵����
 * <p>
 * ������Lambda���ʽ�����������һ�����ʽ���Ҹñ��ʽ��������һ���Ѿ����ڵķ��������ǿ���ʹ�÷�������.
 * �����������õ�ʹ���ںܶ�����¼���lambda���ʽ
 * 2��ǰ�᣺
 * ��������������ʹ�÷�������κͷ���ֵ��lambda���ʽʵ�ֵĺ���ʽ�ӿڵ���κͷ���ֵһ��
 * 3���﷨��ʽ
 * ������������ʵ�����������ض������ʵ���������ã�
 * ������������̬������
 * ����������ʵ���������������������ʵ���������ã�
 * <p>
 * ��������������
 * 1��ǰ�᣺
 * ������Ҫ���ù������Ĳ����б�Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б���һ��
 * 2���﷨��ʽ
 * ����������new
 * <p>
 * ������������
 * 1���﷨��ʽ
 * ��������[]��new
 */
public class TestMethodRef {

    /**
     * ��������ʵ��������
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
     * ��������ʵ��������
     */
    @Test
    public void test02() {
        Employee employee = new Employee("����", 25, 5500d);
        Supplier<String> supplier = () -> employee.getName();
        final String name = supplier.get();
        System.out.println("name=" + name);

        Supplier<Integer> supplier1 = employee::getAge;
        final Integer age = supplier1.get();
        System.out.println("age=" + age);
    }

    /**
     * ��������̬������
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
     * ������ʵ��������
     */
    @Test
    public void test04() {
        String a = "methodref";
        String b = "hellow world";
        String c = "methodref";
        BiPredicate<String, String> biPredicate = (str1, str2) -> str1.equals(str2);
        final boolean ab = biPredicate.test(a, b);
        final boolean ac = biPredicate.test(a, c);
        System.out.println("��a==b? " + ab + "����a==c? " + ac + "��");

        //lambda���ʽ�ĵ�һ�����Ϊʵ�������ĵ����ߣ�����������ʵ�����������һ��ʱ������ʹ������::ʵ��������
        BiPredicate<String, String> biPredicate1 = String::equals;
        final boolean ab1 = biPredicate1.test(a, b);
        final boolean ac1 = biPredicate1.test(a, c);
        System.out.println("��a==b? " + ab1 + "����a==c? " + ac1 + "��");

        System.out.println("-------------------");
        Function<User, String> function = (user) -> user.getName();
        final String userName = function.apply(new User("����"));
        System.out.println("userName=" + userName);

        Function<User, List<String>> function1 = User::getHobbies;
        final List<String> hobbies = function1.apply(new User("����", "ƹ����", "��ë��", "��Ӿ", "���"));
        System.out.println("���ã�" + hobbies);
    }

    /**
     * ����������
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
        final Employee employee2 = function.apply("����");
        System.out.println(employee2);

        Function<String, Employee> function1 = Employee::new;
        final Employee employee3 = function1.apply("����");
        System.out.println(employee3);

        BiFunction<String, Integer, Employee> biFunction = (name, age) -> new Employee(name, age);
        final Employee employee4 = biFunction.apply("����", 23);
        System.out.println(employee4);

        BiFunction<String, Integer, Employee> biFunction1 = Employee::new;
        final Employee employee5 = biFunction1.apply("����", 23);
        System.out.println(employee5);

        Supplier<List<String>> supplier2 = () -> new ArrayList<String>();
        final List<String> list = supplier2.get();
        System.out.println(list);

        Supplier<List<String>> supplier3 = ArrayList<String>::new;
        final List<String> list1 = supplier3.get();
        System.out.println(list1);
    }

    /**
     * ��������
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
