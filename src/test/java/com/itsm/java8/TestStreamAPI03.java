package com.itsm.java8;

import com.itsm.vo.Coder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 终止操作（终端操作）
 * 一、查找与匹配：
 * 　　allMatch(): 检查是否匹配所有元素
 * 　　anyMatch()：检查是否至少匹配一个元素
 * 　　noneMatch(): 检查是否没有匹配所有元素
 * 　　findFirst()：返回当前流中的第一个元素
 * 　　findAny()：返回当前流中的任意元素
 * 　　count()：返回流中元素的总个数
 * 　　max()：返回流中最大值
 * 　　min()：返回流中最小值
 */
public class TestStreamAPI03 {
    private List<Coder> coderList = new ArrayList<>();

    @Before
    public void init() {
        coderList.add(new Coder("张三", 30, 25000, Coder.Status.BUSY));
        coderList.add(new Coder("李四", 24, 3500, Coder.Status.FREE));
        coderList.add(new Coder("王五", 25, 8000, Coder.Status.VOCATION));
        coderList.add(new Coder("赵六", 36, 35000, Coder.Status.FREE));
        coderList.add(new Coder("田七", 19, 4500, Coder.Status.BUSY));
    }

    @Test
    public void testAllmatch() {
        final boolean b = coderList.stream().allMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("allMatch:" + b);
    }

    @Test
    public void testAnyMatch() {
        final boolean b = coderList.stream().anyMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("anyMatch:" + b);
    }

    @Test
    public void testNoneMatch() {
        //noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
        final boolean b = coderList.stream().noneMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("noneMatch:" + b);
    }

    @Test
    public void testFindFirst() {

    }

}
