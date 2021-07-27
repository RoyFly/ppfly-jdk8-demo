package com.itsm.function;

@FunctionalInterface
public interface MyFunction2<T, R> {
    R getCalculatedValue(T t1, T t2);
}
