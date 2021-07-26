package com.itsm.java8;

import com.itsm.vo.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {
    /**
     * �ٶ���Integer���ڲ�����ֻ�����������ҿ������ʹ�ü��ϵ� Collections#reverse�������õ���������
     * �ڶ���Person����Ҫ�޸����������߶���Department����Ҫ�������򡣿���ʹ���ⲿ���򣨲������ģʽ��
     */
    @Test
    public void test01() {
        List<Department> list = new ArrayList<>();
        Department dept01 = new Department(1, "code01 ", "��ά��");
        Department dept03 = new Department(3, "code03 ", "�����");
        Department dept02 = new Department(2, "code02 ", "ʵʩ��");
        list.add(dept01);
        list.add(dept03);
        list.add(dept02);
        System.out.println("����֮ǰ��" + list);

        Collections.sort(list, new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                return o1.getId() - o2.getId();
            }
        });

        System.out.println("����֮��" + list);
    }
}
