package com.itsm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Comparable<Person> {
    private String name;
    private Integer age;
    private String email;

    public int compareTo(Person o) {
        //先比较年龄
        final int result = Integer.compare(this.age, o.age);
        if (result == 0) {
            //年龄相同比较姓名
            return this.name.compareTo(o.name);
        }
        return result;
    }
}
