package com.itsm.pattern;

import com.itsm.vo.Employee;

public class PredicateEmployeeByAage implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
