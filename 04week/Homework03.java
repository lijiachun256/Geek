package weather.sunny.geek;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {

    private static Homework03 hh = new Homework03();
    private static volatile Integer result6 = 0;

    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),new ThreadPoolExecutor.CallerRunsPolicy());

        //1
//        Integer result = test1(threadPool);
// 2       Integer result = test2(threadPool);
// 3       Integer result = test3(threadPool);
// 4       Integer result = test4();
// 5        Integer result = test5();
        //6
//        test6();
        //7
       /* CyclicBarrier cyclicBarrier = new CyclicBarrier(2,() -> {
            System.out.println("大家一起来,第7种");
        });
        test7(cyclicBarrier,threadPool);
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // 8
        test8();
        synchronized (hh) {
            try {
                hh.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //9


        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result6);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


        // 然后退出main线程
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    private static Integer test1(ThreadPoolExecutor threadPool) {
        //这是得到的返回值
        final Integer[] result = {0};
        threadPool.execute(() -> {
            result[0] = sum();
            //这是得到的返回值
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
    }

    private static Integer test2(ThreadPoolExecutor threadPool) {
        AtomicInteger result = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        threadPool.execute(() -> {
            //这是得到的返回值
            result.set(sum());
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    private static Integer test3(ThreadPoolExecutor threadPool) {
        AtomicInteger result = new AtomicInteger(0);
        Future<Integer> submit = threadPool.submit(() -> {
            //这是得到的返回值
            result.set(sum());
            return result.get();
        });
        try {
            return submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int test4() {
        AtomicInteger result = new AtomicInteger(0);
        CompletableFuture<Integer> runAsync = CompletableFuture.supplyAsync(() -> {
            result.set(sum());
            return result.get();
        });
        try {
            return runAsync.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int test5() {
        AtomicInteger result = new AtomicInteger(0);
        FutureTask<Integer> task = new FutureTask<>(() -> {
            result.set(sum());
            return result.get();
        });
        new Thread(task).start();
        try {
            return task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void test6() {
        Thread thread = new Thread(() -> {
            result6 = sum();
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test7(CyclicBarrier cyclicBarrier,ThreadPoolExecutor threadPool) {
        threadPool.execute(() -> {
            result6 = sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test8() {
        Thread thread = new Thread(() -> {
            result6 = sum();
            synchronized (hh) {
                hh.notifyAll();
            }
        });
        thread.start();
    }
}
