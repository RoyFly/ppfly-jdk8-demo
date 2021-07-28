package com.itsm.java8;

import com.itsm.vo.Employee;
import com.itsm.vo.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * 一、筛选与切片
 * 　　　filter(): 返回结果生成新的流中只包含满足筛选条件的数据
 * 　　　limit()：截断流，使其元素不超过指定数量
 * 　　　skip()：和limit()相反，将前几个元素跳过（取出）再返回一个流，如果流中的元素小于或者等于n，就会返回一个空的流。
 * 　　　distinct()：将流中的元素去重（hasCode()和equals()）之后输出
 * 二、映射
 * 　　　map()：接收Lambda，将元素转换为其他形式或提取信息。就收一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新的元素
 * 　　　flatMap()：扁平化映射，它具体的操作是将多个stream连接成一个stream，这个操作是针对类似多维数组的，比如集合里面包含集合，相当于降维作用。
 */
public class TestStreamAPI02 {
    private List<Employee> list = new ArrayList<>();

    @Before
    public void init() {
        list.add(new Employee("张三", 18, 9999.99));
        list.add(new Employee("李四", 34, 8888.88));
        list.add(new Employee("王五", 25, 7777.77));
        list.add(new Employee("赵六", 36, 15555.55));
        list.add(new Employee("田七", 17, 4444.44));
        list.add(new Employee("田七", 17, 4444.44));
    }

    /**
     * 内部迭代：迭代操作由StreamAPI完成
     */
    @Test
    public void testFilter() {
        final Stream<Employee> stream = list.stream().filter(employee -> {
            System.out.println("中间操作不会执行任何处理，而在终止操作时一次性全部处理，称为“惰性求值”");
            return employee.getSalary() > 8000;
        });
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test() {
        final Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testLimit() {
        list.stream().filter(employee -> {
            System.out.println("短路");
            return employee.getSalary() > 8000;
        }).limit(1).forEach(System.out::println);
    }

    @Test
    public void testSkip() {
        //Statement lambda can be replaced with expression lambda more... (Ctrl+F1)
        list.stream().filter(employee -> {
            return employee.getSalary() > 8000;
        }).skip(1).forEach(System.out::println);
    }

    @Test
    public void testDistinct() {
        list.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<String> stringList = Arrays.asList("stream", "lambda", "java8");
        stringList.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("------------------");
        //Lambda can be replaced with method reference more... (Ctrl+F1)
        list.stream().map(employee -> employee.getName()).forEach(System.out::println);
        System.out.println("------------------");
        list.stream().map(Employee::getName).forEach(System.out::println);
    }

    @Test
    public void testMap02() {
        List<String> list = Arrays.asList("abc", "def", "gh");
        final Stream<String> stream = list.stream();
        final Stream<Stream<Character>> streamStream = stream.map(TestStreamAPI02::getCharStream);//[[a,b,c],[d,e,f],[g,h]]
        streamStream.forEach(smsm -> {
            smsm.forEach(System.out::println);
        });
    }

    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("abc", "def", "gh");
        final Stream<Character> characterStream = list.stream().flatMap(TestStreamAPI02::getCharStream);//[a,b,c,d,e,f,g,h]
        characterStream.forEach(System.out::println);
    }

    /**
     * 将字符串转为Character的Stream
     *
     * @param str
     * @return
     */
    private static Stream<Character> getCharStream(String str) {
        List<Character> characterList = new ArrayList<>();
        for (char c : str.toCharArray()) {
            characterList.add(c);
        }
        final Stream<Character> stream = characterList.stream();
        return stream;
    }

    /**
     * 使用场景：集合里面的元素对象，持有数组或者集合的引用，需要取出来单独处理，然后形成新的集合。
     */
    @Test
    public void testFlatMap02() {
        //当我们要处理的对象是集合里面的元素的时候，map就能帮我们解决。
        //但是当我们要处理的对象，不仅仅是元素，比如这个例子的list3,集合里面包含集合
        //我们需要处理的是集合list3的子集合list1，list2，并且把结果都返回到一个结果的时候，flatMap就可以办到。
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<List<Integer>> list3 = Arrays.asList(list1, list2);

        //遍历得到的是[1, 2, 3]，[4, 5, 6]
        final Stream<List<Integer>> stream = list3.stream();
        stream.forEach(System.out::println);
        System.out.println("----------------------------");

        //Iterable的forEach
        final Stream<List<Integer>> stream1 = list3.stream();
        stream1.forEach(intList -> {
            //terable的forEach
            intList.forEach(System.out::println);
        });
        System.out.println("----------------------------");

        //Stream的forEach
        final Stream<List<Integer>> stream2 = list3.stream();
        stream2.forEach(intList -> {
            //Stream的forEach
            intList.stream().forEach(System.out::println);
        });
        System.out.println("----------------------------");

        //map
        final Stream<List<Integer>> stream3 = list3.stream();
        final Stream<Stream<Integer>> streamStream = stream3.map(list -> list.stream());
        streamStream.forEach(intList -> intList.forEach(System.out::println));
        System.out.println("----------------------------");

        //flatMap
        list3.stream().flatMap(list -> list.stream()).forEach(System.out::println);
    }


    /**
     * 使用场景：集合里面的元素对象，持有数组或者集合的引用，需要取出来单独处理，然后形成新的集合。
     */
    @Test
    public void testFlatMap03() {
        List<User> users = Arrays.asList(
                new User("张三", "吃", "喝", "嫖"),
                new User("李四", "赌", "坑", "蒙"),
                new User("王五", "拐", "骗", "吃")
        );
        //获取所有用户名
        users.stream().map(User::getName).forEach(System.out::println);
        System.out.println("----------------------");

        //获取所有用户的兴趣爱好：[吃, 喝, 嫖][赌, 坑, 蒙][拐, 骗, 吃]
        final Stream<List<String>> listStream = users.stream().map(User::getHobbies);
        listStream.forEach(System.out::print);
        System.out.println("\n----------------------");

        //获取所有用户的兴趣爱好[吃, 喝, 嫖，赌, 坑, 蒙，拐, 骗, 吃]
        final Stream<List<String>> listStream1 = users.stream().map(User::getHobbies);
        listStream1.forEach(hobbies -> hobbies.forEach(System.out::println));

    }


}

