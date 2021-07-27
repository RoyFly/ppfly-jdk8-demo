package com.itsm.java8;

import com.itsm.predicate.MyPredicate;
import com.itsm.predicate.PredicateEmployeeBySalary;
import com.itsm.vo.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestLambda01 {
    private List<Employee> list = new ArrayList<>();

    @Before
    public void init() {
        list.add(new Employee("张三", 18, 9999.99));
        list.add(new Employee("李四", 34, 8888.88));
        list.add(new Employee("王五", 25, 7777.77));
        list.add(new Employee("赵六", 36, 15555.55));
        list.add(new Employee("田七", 17, 4444.44));
    }

    /**
     * 传统方式
     */
    @Test
    public void test01() {
//        final List<Employee> resultList = filter(list);
        final List<Employee> resultList = filter2(list);
        for (Employee employee : resultList) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式一：
     * 使用设计模式(策略模式)
     */
    @Test
    public void test02() {
//        final List<Employee> reusltList = filter3(list, new PredicateEmployeeByAage());
        final List<Employee> reusltList = filter3(list, new PredicateEmployeeBySalary());
        for (Employee employee : reusltList) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void test03() {
        final List<Employee> reusltList = filter3(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() < 5000;
            }
        });
        for (Employee employee : reusltList) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void test04() {
        final List<Employee> reusltList = filter3(list, (e) -> e.getSalary() < 5000);
        for (Employee employee : reusltList) {
            System.out.println(employee);
        }
    }


    /**
     * 获取年龄大于30的员工
     *
     * @param list
     * @return
     */
    private List<Employee> filter(List<Employee> list) {
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() > 30) {
                newList.add(employee);
            }
        }
        return newList;
    }

    /**
     * 获取工资大于9000的员工
     *
     * @param list
     * @return
     */
    private List<Employee> filter2(List<Employee> list) {
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() > 9000) {
                newList.add(employee);
            }
        }
        return newList;
    }

    /**
     * 只需要定义一个方法
     *
     * @param list
     * @param myPredicate
     * @return
     */
    private List<Employee> filter3(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : list) {
            if (myPredicate.test(employee)) {
                newList.add(employee);
            }
        }
        return newList;
    }

}
