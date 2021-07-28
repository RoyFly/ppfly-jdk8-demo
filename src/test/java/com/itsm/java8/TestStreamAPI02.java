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
 * �м����
 * һ��ɸѡ����Ƭ
 * ������filter(): ���ؽ�������µ�����ֻ��������ɸѡ����������
 * ������limit()���ض�����ʹ��Ԫ�ز�����ָ������
 * ������skip()����limit()�෴����ǰ����Ԫ��������ȡ�����ٷ���һ������������е�Ԫ��С�ڻ��ߵ���n���ͻ᷵��һ���յ�����
 * ������distinct()�������е�Ԫ��ȥ�أ�hasCode()��equals()��֮�����
 * ����ӳ��
 * ������map()������Lambda����Ԫ��ת��Ϊ������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿһ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ��
 * ������flatMap()����ƽ��ӳ�䣬������Ĳ����ǽ����stream���ӳ�һ��stream�����������������ƶ�ά����ģ����缯������������ϣ��൱�ڽ�ά���á�
 */
public class TestStreamAPI02 {
    private List<Employee> list = new ArrayList<>();

    @Before
    public void init() {
        list.add(new Employee("����", 18, 9999.99));
        list.add(new Employee("����", 34, 8888.88));
        list.add(new Employee("����", 25, 7777.77));
        list.add(new Employee("����", 36, 15555.55));
        list.add(new Employee("����", 17, 4444.44));
        list.add(new Employee("����", 17, 4444.44));
    }

    /**
     * �ڲ�����������������StreamAPI���
     */
    @Test
    public void testFilter() {
        final Stream<Employee> stream = list.stream().filter(employee -> {
            System.out.println("�м��������ִ���κδ���������ֹ����ʱһ����ȫ��������Ϊ��������ֵ��");
            return employee.getSalary() > 8000;
        });
        stream.forEach(System.out::println);
    }

    /**
     * �ⲿ����
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
            System.out.println("��·");
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
     * ���ַ���תΪCharacter��Stream
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
     * ʹ�ó��������������Ԫ�ض��󣬳���������߼��ϵ����ã���Ҫȡ������������Ȼ���γ��µļ��ϡ�
     */
    @Test
    public void testFlatMap02() {
        //������Ҫ����Ķ����Ǽ��������Ԫ�ص�ʱ��map���ܰ����ǽ����
        //���ǵ�����Ҫ����Ķ��󣬲�������Ԫ�أ�����������ӵ�list3,���������������
        //������Ҫ������Ǽ���list3���Ӽ���list1��list2�����Ұѽ�������ص�һ�������ʱ��flatMap�Ϳ��԰쵽��
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<List<Integer>> list3 = Arrays.asList(list1, list2);

        //�����õ�����[1, 2, 3]��[4, 5, 6]
        final Stream<List<Integer>> stream = list3.stream();
        stream.forEach(System.out::println);
        System.out.println("----------------------------");

        //Iterable��forEach
        final Stream<List<Integer>> stream1 = list3.stream();
        stream1.forEach(intList -> {
            //terable��forEach
            intList.forEach(System.out::println);
        });
        System.out.println("----------------------------");

        //Stream��forEach
        final Stream<List<Integer>> stream2 = list3.stream();
        stream2.forEach(intList -> {
            //Stream��forEach
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
     * ʹ�ó��������������Ԫ�ض��󣬳���������߼��ϵ����ã���Ҫȡ������������Ȼ���γ��µļ��ϡ�
     */
    @Test
    public void testFlatMap03() {
        List<User> users = Arrays.asList(
                new User("����", "��", "��", "��"),
                new User("����", "��", "��", "��"),
                new User("����", "��", "ƭ", "��")
        );
        //��ȡ�����û���
        users.stream().map(User::getName).forEach(System.out::println);
        System.out.println("----------------------");

        //��ȡ�����û�����Ȥ���ã�[��, ��, ��][��, ��, ��][��, ƭ, ��]
        final Stream<List<String>> listStream = users.stream().map(User::getHobbies);
        listStream.forEach(System.out::print);
        System.out.println("\n----------------------");

        //��ȡ�����û�����Ȥ����[��, ��, �Σ���, ��, �ɣ���, ƭ, ��]
        final Stream<List<String>> listStream1 = users.stream().map(User::getHobbies);
        listStream1.forEach(hobbies -> hobbies.forEach(System.out::println));

    }


}

