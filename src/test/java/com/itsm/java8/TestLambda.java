package com.itsm.java8;

import com.itsm.vo.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestLambda {

    private List<Department> list = new ArrayList<>();

    @Before
    public void init() {
        Department dept01 = new Department(1, "code01 ", "运维组");
        Department dept03 = new Department(3, "code03 ", "监控组");
        Department dept02 = new Department(2, "code02 ", "实施组");
        list.add(dept01);
        list.add(dept03);
        list.add(dept02);
    }

    @Test
    public void test01() {
        System.out.println("排序之前：" + list);
        Collections.sort(list, (d1, d2) -> d1.getId() - d2.getId());
        System.out.println("排序之后：" + list);
    }

    @Test
    public void test02() {
        System.out.println("排序之前：" + list);
        Collections.sort(list, Comparator.comparingInt(Department::getId));
        System.out.println("排序之后：" + list);
    }
}
