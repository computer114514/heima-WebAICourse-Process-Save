package com.itheima;

public class ThreadLocalTest {
    private static ThreadLocal<String> local=new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("main message");

        new Thread(new Runnable(){
            @Override
            public void run(){
                local.set("ss");
                System.out.println(local.get());
                //只能获取自己的线程的local变量
            }
        }).start();

        System.out.println(local.get());

        local.remove();

        System.out.println(local.get())
        ;
    }
}
