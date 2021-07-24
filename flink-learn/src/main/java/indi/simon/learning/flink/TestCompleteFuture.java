package indi.simon.learning.flink;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author chenzhuo(zhiyue)
 */
public class TestCompleteFuture {

    public static void main(String[] args) throws Exception {

        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                System.out.println("supplyAsync thread:" + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }

                return 1.1;
            }
        });

        // thenAccept
        cf.thenAccept((result) -> {
            System.out.println("thenAccept thread:" + Thread.currentThread().getName());
            System.out.println("price: " + result);
        });

        // thenAcceptAsync
        cf.thenAcceptAsync(aDouble -> {
            System.out.println("thenAcceptAsync thread:" + Thread.currentThread().getName());
            System.out.println("price: " + aDouble);
        });


        // 如果执行异常:
//        cf.exceptionally((e) -> {
//            e.printStackTrace();
//            return null;
//        });

        System.out.println("waiting");
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(20000);


    }


}
