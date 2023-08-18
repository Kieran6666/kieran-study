package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * 编译时异常，watch会提示失败
 * 场景：学生人数减少n名，毕业人数增加n名
 *
 * 测试步骤
 * 1. 检查redis中是否有 student、graduate 两个key，有则删除
 * 2. main方法的代码全部保留，并执行一次，此时的结果应该是线程1的打印结果应该是10 2，实际上在redis中已经执行失败了
 * 3. 只保留new Thread(thread1()).start();代码，其他main方法的代码注释掉，并执行1次，此时的结果应该是线程1的打印结果应该是8 4，在redis中执行成功了
 * 4. 说明 毕业人数为4名是因为，在线程2执行时，打断了线程1的事务，并且成功把毕业人数设置为2，因此后续线程1执行是，毕业人数的基准是从2人开始的
 */
public class TestWatchException extends JedisBase {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
//        jedis.set("student", "10");
//        jedis.set("graduate", "0");
//        jedis.close();

        // 此处需要两个线程;
        new Thread(thread1()).start();
//        new Thread(thread2()).start();
    }

    private static Runnable thread1() {
        return () -> {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            Transaction multi = null;

            try {
                String result = jedis.watch("graduate");
                System.err.println(result);

                multi = jedis.multi();
                multi.decrBy("student", 2);
                multi.incrBy("graduate", 2);

                // 模拟此时有一个线程2，在修改graduate的值
                Thread.sleep(8000);

                // TODO:: 如何得知事务执行失败了？如何打印失败信息？
                List<Object> exec = multi.exec();

                if (null == exec) {
                    System.err.println("事务失败");
                } else {
                    for (Object object : exec) {
                        System.err.println(object.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (null != multi) {
                    multi.discard();
                }
                jedis.unwatch();
            } finally {
                System.err.println(jedis.get("student"));
                System.err.println(jedis.get("graduate"));
                jedis.close();
            }
        };
    }

    private static Runnable thread2() {
        return () -> {
//            Jedis jedis = new Jedis("127.0.0.1", 6379);
//            try {
//                Thread.sleep(2000);
//                jedis.set("graduate", "2");
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                jedis.close();
//            }

            // jedis.close(); 的代码简化写法，放在try中，代码执行完时会自动关闭
            try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
                Thread.sleep(2000);
                jedis.set("graduate", "2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
