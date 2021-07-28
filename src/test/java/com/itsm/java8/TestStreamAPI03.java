package com.itsm.java8;

import com.itsm.vo.Coder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ֹ�������ն˲�����
 * һ��������ƥ�䣺
 * ����allMatch(): ����Ƿ�ƥ������Ԫ��
 * ����anyMatch()������Ƿ�����ƥ��һ��Ԫ��
 * ����noneMatch(): ����Ƿ�û��ƥ������Ԫ��
 * ����findFirst()�����ص�ǰ���еĵ�һ��Ԫ��
 * ����findAny()�����ص�ǰ���е�����Ԫ��
 * ����count()����������Ԫ�ص��ܸ���
 * ����max()�������������ֵ
 * ����min()������������Сֵ
 */
public class TestStreamAPI03 {
    private List<Coder> coderList = new ArrayList<>();

    @Before
    public void init() {
        coderList.add(new Coder("����", 30, 25000, Coder.Status.BUSY));
        coderList.add(new Coder("����", 24, 3500, Coder.Status.FREE));
        coderList.add(new Coder("����", 25, 8000, Coder.Status.VOCATION));
        coderList.add(new Coder("����", 36, 35000, Coder.Status.FREE));
        coderList.add(new Coder("����", 19, 4500, Coder.Status.BUSY));
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
        //noneMatch��allMatch�෴���ж��������Ԫ�أ����еĶ����ǣ�����true
        final boolean b = coderList.stream().noneMatch(coder -> coder.getStatus().equals(Coder.Status.BUSY));
        System.out.println("noneMatch:" + b);
    }

    @Test
    public void testFindFirst() {

    }

}
