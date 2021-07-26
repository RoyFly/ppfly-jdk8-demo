package com.itsm.java8;

import com.itsm.vo.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {
    /**
     * ①对于Integer的内部排序，只能升序排序。我可排序后使用集合的 Collections#reverse方法，得到降序排序
     * ②对于Person，想要修改排序规则或者对于Department，想要进行排序。可以使用外部排序（策略设计模式）
     */
    @Test
    public void test01() {
        List<Department> list = new ArrayList<>();
        Department dept01 = new Department(1, "code01 ", "运维组");
        Department dept03 = new Department(3, "code03 ", "监控组");
        Department dept02 = new Department(2, "code02 ", "实施组");
        list.add(dept01);
        list.add(dept03);
        list.add(dept02);
        System.out.println("排序之前：" + list);

        Collections.sort(list, new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                return o1.getId() - o2.getId();
            }
        });

        System.out.println("排序之后：" + list);
    }
}
