package com.itsm.predicate;

import com.itsm.vo.Employee;

public class PredicateEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 9000;
    }
}
