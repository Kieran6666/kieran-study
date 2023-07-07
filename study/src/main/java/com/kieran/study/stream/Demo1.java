package com.kieran.study.stream;

import com.kieran.study.utils.TestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * skip(n)：跳过n个数据，可能会有性能问题
 * limit(n)：只取n个数据
 * Stream.of(): 自定义内容
 * Stream.iterate(seed, UnaryOperator): 从某个下标，根据一元函数计算后，遍历
 */
public class Demo1 {

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 6, 7).forEach(System.err::print);
        TestUtils.println();

        // 跳过前n个数据
        Stream.of(1, 2, 3, 4, 6, 7).skip(5).forEach(System.err::print);
        TestUtils.println();

        /*
         * iterate(seed, UnaryOperator)
         * seed: 从一个初始值开始
         * UnaryOperator一元函数 =>  n -> n + 2 ，表示以2为间隔跳跃遍历
         *
         * limit(n): 只遍历n个
         */
        List<Integer> list1 = Stream.iterate(2, n -> n + 2).limit(6).collect(Collectors.toList());
        System.err.print(list1);
        TestUtils.println();

        // 分组测试用例
        List<Integer> exp = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            exp.add(i);
        }

        // 分组值
        int groupNum = 10;
        int times = new BigDecimal(String.valueOf(exp.size()))
                .divide(new BigDecimal(String.valueOf(groupNum)), 1, RoundingMode.UP)
                .setScale(0, RoundingMode.UP).intValue();

        // 以10个数据分组，获取每个分组的第一个数据
        List<Integer> list3 = Stream.iterate(0, n -> n + groupNum).limit(times).collect(Collectors.toList());
        System.err.print(list3);
        TestUtils.println();

        // 以10个数据分组，打印每个分组的所有数据，期望值: [0-9] [10-19] [20-29] [30-39] [40-49] [50]
        List<List<Integer>> list4 = new ArrayList<>();

        // 通过分组值，获取分组后的组数，每组的第一个数据为下标，跳过这个下标，遍历分组值个新组的数据，进行分组
        Stream.iterate(0, n -> n + groupNum).limit(times)
                .forEach(i -> list4.add(exp.stream().skip(i).limit(groupNum).collect(Collectors.toList())));
        System.err.println(list4);

        // TIPS: 该方法可以实现分批查询，与CompletableFutureStream可以实现多线程分批查询
    }
}
