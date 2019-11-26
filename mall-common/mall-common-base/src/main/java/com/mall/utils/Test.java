package com.mall.utils;

import java.util.Arrays;
import java.util.List;

/**
 * program: spring-cloud-mall->Test
 * description: 测试工具
 * author: gerry
 * created: 2019-11-23 10:34
 **/
public class Test {
    public static void main(String[] args) {
        List<Integer> lis = Arrays.asList(1, 2, 3);
        lis.forEach(num -> System.out.println(num));
    }
}
