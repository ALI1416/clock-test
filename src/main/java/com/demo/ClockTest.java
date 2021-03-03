package com.demo;

import cn.z.clock.Clock;

/**
 * <h1>高性能时钟测试</h1>
 *
 * <p>
 * createDate 2021/02/24 11:54:46
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class ClockTest {

    public static void main(String[] args) {
        test();
        compare100w();
        compare1000w();
        compare1e();
        compare21e();
    }

    /**
     * 测试
     */
    static void test() {
        System.out.println("现在时间戳为：" + Clock.now());
        System.out.println("现在Date为：" + Clock.date());
        System.out.println("现在Timestamp为：" + Clock.timestamp());
        // 现在时间戳为：1614583563907
        // 现在Date为：Mon Mar 01 15:26:03 CST 2021
        // 现在Timestamp为：2021-03-01 15:26:03.973
    }

    /**
     * 100万次高性能时钟与系统时钟比较
     */
    static void compare100w() {
        compare(1000000);
        // 高性能时钟调用1000000次使用时间为：1毫秒
        // 系统时钟调用1000000次使用时间为：5毫秒
        // 调用1000000次，高性能时钟比系统时钟性能高5.0倍
    }

    /**
     * 1000万次高性能时钟与系统时钟比较
     */
    static void compare1000w() {
        compare(10000000);
        // 高性能时钟调用10000000次使用时间为：2毫秒
        // 系统时钟调用10000000次使用时间为：22毫秒
        // 调用10000000次，高性能时钟比系统时钟性能高11.0倍
    }

    /**
     * 1亿次高性能时钟与系统时钟比较
     */
    static void compare1e() {
        compare(100000000);
        // 高性能时钟调用100000000次使用时间为：3毫秒
        // 系统时钟调用100000000次使用时间为：327毫秒
        // 调用100000000次，高性能时钟比系统时钟性能高109.0倍
    }

    /**
     * 21亿次高性能时钟与系统时钟比较
     */
    static void compare21e() {
        compare(Integer.MAX_VALUE);
        // 高性能时钟调用2147483647次使用时间为：35毫秒
        // 系统时钟调用2147483647次使用时间为：6720毫秒
        // 调用2147483647次，高性能时钟比系统时钟性能高192.0倍
    }

    /**
     * 高性能时钟与系统时钟比较
     *
     * @param count 次数
     */
    static void compare(int count) {
        // 调用一次，并延时1秒，初始化
        Clock.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 高性能时钟
        long a = Clock.now();
        for (int i = 0; i < count; i++) {
            Clock.now();
        }
        long b = Clock.now();
        long ba = b - a;
        System.out.println("高性能时钟调用" + count + "次使用时间为：" + ba + "毫秒");

        // 系统时钟
        long c = Clock.now();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        long d = Clock.now();
        long dc = d - c;
        System.out.println("系统时钟调用" + count + "次使用时间为：" + dc + "毫秒");

        System.out.println("调用" + count + "次，高性能时钟比系统时钟性能高" + dc / (double) ba + "倍");
    }

}
